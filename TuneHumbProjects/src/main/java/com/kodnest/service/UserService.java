package com.kodnest.service;

import com.kodnest.entity.User;

public interface UserService {
	
		public String addUse(User user);
		public boolean emailExists(String email);
		public boolean validateUser(String email, String password) ;
		public String getRole(String email);
		
		public User getUser(String email) ;
		public void updateUser(User user);
			
		

	}
