package com.amanuel.app.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amanuel.app.models.User;

@Controller
public class Users {
	  @RequestMapping("/registration")
	    public String registerForm(@Valid @ModelAttribute("user") User user) {
	        return "registrationPage";
	    }
	    
	    @RequestMapping("/login")
	    public String login() {
	        return "loginPage";
	    }
	
}
