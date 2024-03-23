package com.kodnest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.entity.Song;
import com.kodnest.entity.User;
import com.kodnest.serviceimpl.SongServiceImpl;
import com.kodnest.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class UserController {

		@Autowired
		UserServiceImpl userserviceImpl;
		
		@Autowired
		SongServiceImpl songServiceImpl;
		
		@PostMapping(value = "/register")
		public String addUse(@ModelAttribute User user) {
			
			String email = user.getEmail();
			//Chckinge email database present or not present
			 boolean status= userserviceImpl.emailExists(email);
			if(status==false) {
			userserviceImpl.addUse(user);
			System.out.println("Add User..");
		     }
			else {
				System.out.println("User already exist");
			}
			return "login";
			
		}
		@PostMapping("/validate")
		public String validate(@RequestParam("email")String email,
				@RequestParam("password")String password ,HttpSession session ,Model model ) {
			if(userserviceImpl.validateUser(email,password)==true){
				String role=userserviceImpl.getRole(email);
				session.setAttribute("email", email);
				if(role.equals("admin")) {
				return "adminhome";
				}
				else {
					
					User user = userserviceImpl.getUser(email);
					boolean userstatus = user.isIspremium();
					List<Song> list = songServiceImpl.fetchAllSong();
					model.addAttribute("songs", list);
					model.addAttribute("ispremium", userstatus);
					return "customerhome";
				}
			}
			else {
			return "login";
			}
		}
		@GetMapping("/logout")
		public String logout(HttpSession session ) {
			session.invalidate();
			return "login";
		}
		
     
	}
