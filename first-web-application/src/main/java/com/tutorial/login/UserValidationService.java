package com.tutorial.login;

import org.springframework.stereotype.Service;

//add @Service annotation. the Spring framework will manage(create) this class (bean) now.
//this class will be ready for use wherever there is a match  
@Service
public class UserValidationService {

	public boolean isUserValid(String user, String password) {
		if (user.equals("Alisa") && password.equals("mypwd"))
			return true;
		
		return false; 
	}
}
