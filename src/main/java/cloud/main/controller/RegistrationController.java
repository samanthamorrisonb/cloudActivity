/**
 * RegistrationController.java
 * Cody Crosby, 2025
 * GCU : CST-339
 * This class is the controller for the registration page.
 * It handles the GET and POST requests for the registration page.
 */
package cloud.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import cloud.main.business.RegistrationService;
import cloud.main.model.*;
import cloud.main.utilities.Utilities;

/**
 * This class is the controller for the registration page.
 * It handles the GET and POST requests for the registration page.
 */
@Controller										// Controller annotation
@RequestMapping("/registration")				// Request mapping for /registration 
public class RegistrationController {	
	
	@Autowired									// Autowired annotation
	private RegistrationService service;	// Registration service object
	
	/**
	 * Constructor
	 * @param registrationService
	 */
	public RegistrationController(RegistrationService registrationService) {	// Constructor
		this.service = registrationService;							// Set the registration service
	}
	
	/**
	 * Display the Registration Form
	 */
	@GetMapping("/")
	public String display(Model model) {
		// Display Registration Form View
		model.addAttribute("title", "Registration Form");		// adds a title attribute to the model - Registration Form
		model.addAttribute("registration", new RegistrationModel());			// adds a new UserModel object to the model		
		return "registration";									// returns view - registration
	}
	
	/**
	 * Process the Registration Form
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/doRegister")
	public String doRegister(@Valid @ModelAttribute("registration") RegistrationModel registration, 
			BindingResult bindingResult,
			Model model) {
		
		// Check for validation errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Registration Form");	// adds a title attribute to the model - Registration Form
			return "registration";								// returns view - registration
		}
		

		if(!registration.getPassword().equals(registration.getConfirmPassword())) { // Check if the password and confirm password match
			System.out.println(Utilities.warningColor("Passwords do not match: " + registration.getUsername()));
			model.addAttribute("title", "Registration Form"); // adds a title attribute to the model - Registration Form
			model.addAttribute("error", "Passwords do not match!"); // Add error message to the model
			return "registration"; // Return to the registration page if passwords do not match
		}
		
		if(service.checkUserExists(registration)) { // Check if the user already exists
			System.out.println(Utilities.warningColor("Username already exists: " + registration.getUsername()));
			model.addAttribute("error", "Username already exists!"); // Add error message to the model
			return "registration"; // Return to the registration page if user already exists
		}
		
		if(service.checkEmailExists(registration)) { // Check if the email already exists
			System.out.println(Utilities.warningColor("Email already exists: " + registration.getEmail()));
			model.addAttribute("error", "Email already exists!"); // Add error message to the model
			return "registration"; // Return to the registration page if email already exists
		} 
		
		if(service.registerUser(registration)) { // Call the registration service to register the user
			System.out.println(Utilities.successColor("User registered successfully: " + registration.getUsername()));
			model.addAttribute("success", "User registered successfully!"); // Add success message to the model
			return "redirect:/login/";	// Redirect to login page after successful registration
		} else {
			System.out.println(Utilities.errColor("User registration failed: " + registration.getUsername()));
			model.addAttribute("error", "User registration failed!"); // Add error message to the model
			return "registration"; // Return to the registration page if registration fails
		}
		
	}
}
