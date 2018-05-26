package com.amanuel.app.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amanuel.app.models.User;
import com.amanuel.app.services.UserService;
import com.amanuel.app.validator.UserValidator;

@Controller
public class Users {
		private UserService userService;
		 // NEW
	    private UserValidator userValidator;
	    
	    // NEW
	    public Users(UserService userService, UserValidator userValidator) {
	        this.userService = userService;
	        this.userValidator = userValidator;
	    }
		
	    @RequestMapping("/registration")
	    public String registerForm(@Valid @ModelAttribute("user") User user) {
	        return "registrationPage";
	    }
	    @PostMapping("/registration")
	    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
	    		// validating
	        userValidator.validate(user, result);
		    	if(result.hasErrors()) {
		    		return "registrationPage";
		    	}
		    	userService.saveWithAdminRole(user);
		    	return "redirect:/login";
		    }
	    // Admin
	    @RequestMapping("/admin")
	    public String adminPage(Principal principal, Model model) {
	        String username = principal.getName();
	        model.addAttribute("currentUser", userService.findByUsername(username));
	        return "adminPage";
	    }
	    @RequestMapping("/login")
	    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
	    	 if(error != null) {
	             model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
	         }
	         if(logout != null) {
	             model.addAttribute("logoutMessage", "Logout Successful!");
	         }
	        return "loginPage";
	    }
	    @RequestMapping(value = {"/", "/home"})
	    public String home(Principal principal, Model model) {
	        // 1
	        String username = principal.getName();
	        model.addAttribute("currentUser", userService.findByUsername(username));
	        return "homePage";
	    }
	
}
