package com.kodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.entity.Song;
import com.kodnest.serviceimpl.SongServiceImpl;

@Controller
public class SongController {
	@Autowired
	SongServiceImpl songServiceImpl;
	
	
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song) {
		boolean songStatus=songServiceImpl.songExists(song.getName());
		if(songStatus==false) {
		 songServiceImpl.addsong(song);
		 System.out.println("Song add sucessfully..");
		}
		else {
			System.out.println("Song already exist");
		}
		 return "adminhome";	
	}
	@GetMapping("/viewsong")
	public String viewsong(Model model) {
		List<Song> songList=songServiceImpl.fetchAllSong();
		model.addAttribute("songs", songList);
		return "displaysong";
	}
	
	@GetMapping("/playsong")
	public String playsong(Model model) {
		boolean premium=true;
		if(premium) {
		List<Song> songList=songServiceImpl.fetchAllSong();
		model.addAttribute("songs", songList);
		return "displaysong";
		}
		else {
			return "subscriptionform";
		}
	}
	

}
