package com.kodnest.serviceimpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.kodnest.entity.User;
import com.kodnest.repository.UserRepository;
import com.kodnest.service.UserService;


@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

		@Autowired
		UserRepository userrepository;
		@Override
		public String addUse(User user) {
			userrepository.save(user);
			return "Add success...";
			
		}

		//Logic to checking duplicate entry
		@Override
		public boolean emailExists(String email) {
			if(userrepository.findByEmail(email)!=null){
				return true;
			}
			else {
				return false;
			}
			
		}

		@Override
		public boolean validateUser(String email, String password) {
			User user = userrepository.findByEmail(email);
			String pas = user.getPassword();
			if(password.equals(pas)) {
				return true;
			}
			else {
			return false;
			}
		}

		@Override
		public String getRole(String email) {
			User user = userrepository.findByEmail(email);
			return user.getRole();
		}
		@Override
		public User getUser(String email) {
			return userrepository.findByEmail(email);
			
		}
		@Override
		public void updateUser(User user) {
			userrepository.save(user);
		}

		


		
	}


