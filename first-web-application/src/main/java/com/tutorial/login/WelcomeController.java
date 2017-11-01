package com.tutorial.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController { //this is spring mvc controller. it will handle web requests

	@RequestMapping(value = "/", method = RequestMethod.GET) //available via 8080/login
	public String showLoginPage(ModelMap model) {
		model.put("name", "Alisa");
		return "welcome";
	}

}
