package com.tutorial.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") //generic/shared fields across the entire application
public class LoginController { //this is spring mvc controller. it will handle web requests
	
	//by adding @Service annotation to UserValidationService we make Spring framework to manage that class(bean).
	//the framework will create an instance of that class and it will be available for use.
	//to use the class we are using @Autowired annotation in the Controller. we don't have to create it ourselves
	@Autowired
	private UserValidationService loginService;
	
	//@ResponseBody //say to dispatcher that we are returning the body of response (not url, name of view, etc)
	//if we do not say @ResponseBody then by default Controller will look for login.jsp view, BUT
	//for correct redirect to login.jsp we must match "login" to "login.jsp" location by using ViewResolver
	
	//if using 
	//@RequestMapping(value = "/login") - the method will handle any (GET and POST) request
	//by specifying method we restrict given method to process only specified method types
	@RequestMapping(value = "/login", method = RequestMethod.GET) //available via 8080/login
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST) //available via 8080/login
	//for @RequestParam the variable name must be same as name of element on the html page 
	public String handleLoginPostRequest(
			@RequestParam String name,
			@RequestParam String password, 
			ModelMap model) { 				//aka display user credentials
		
		if (!loginService.isUserValid(name, password)) {
			model.put("errorMessage", "Invalid credentials");
			return "login";
		}
		
		model.put("name", name); 			//doing this we ensure that ${name} is available in View
		model.put("password",  password);
		return "welcome";
		
	}
}
