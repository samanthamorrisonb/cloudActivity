/**
 * LoginController.java
 * Samantha Beauchamp, 2025
 * GCU : CST-339
 * This class is the controller for the login page.
 * It handles the GET and POST requests for the login page.
 * It uses the LoginModel class to bind the form data.
 * It uses the @Controller and @RequestMapping annotations to define the controller and the request mapping.
 * 
 */
package cloud.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cloud.main.model.LoginModel;

/**
 * This class is the controller for the login page.
 * It handles the GET and POST requests for the login page.
 * It uses the LoginModel class to bind the form data.
 * It uses the @Controller and @RequestMapping annotations to define the controller and the request mapping.

 */
@Controller										// Controller annotation
@RequestMapping("/login")						// Request mapping for /login
public class LoginController {
	
	/**
	 * Display the login form
	 * @param model 
	 * @return login page
	 */
	@GetMapping("/")										// Get Mapping for /root
	public String display(Model model) {					// Method to display the login form
		model.addAttribute("title", "Login");				// adds a title attribute to the model - Login
		model.addAttribute("loginModel", new LoginModel());	// adds a new LoginModel object to the model
		return "login";										// returns view - login
	}
	
}
