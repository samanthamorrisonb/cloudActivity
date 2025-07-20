/**
 * AccountController.java
 *  Samantha Beauchamp, 2025
 * This class is the controller for the account page. It handles the requests for the account page and updates the user information.
 */
package cloud.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cloud.main.business.RegistrationService;
import cloud.main.data.LoginAccess;
import cloud.main.model.RegistrationModel;
import cloud.main.model.UserModel;
import cloud.main.utilities.Utilities;

import jakarta.validation.Valid;

/**
 * This class is the controller for the account page. It handles the requests for the account page and updates the user information.
 */
@Controller
@RequestMapping("/user/account")													// Request mapping for /account
public class AccountController {
	private String errPrefix = "Account Controller-> "; 	// Error prefix for logging
	@Autowired
	LoginAccess loginAccess; 														// LoginAccess object
	
	@Autowired											
	private RegistrationService regService;											// Registration service object
	
	Authentication authentidation;													// Authentication object
	 
	/** 
	  * Constructor
	 * @param registrationService
	 */
	public AccountController(RegistrationService registrationService) {	
		this.regService = registrationService;											// Set the registration service
	}
	/**
	 * Display the account page
	 * @return account page
	 */
	@GetMapping("/")																// Get Mapping for /root
	public String display(Authentication authentication, 							// Get the authentication object
						  Model model) {											// Get Model object

		authentication = SecurityContextHolder.getContext().getAuthentication();		// Get the authentication object
		String username = authentication.getName();										// Get the username of the authenticated user
		int userId = loginAccess.getIdByUsername(username);								// Find the user by username
		System.out.println(Utilities.successColor(errPrefix + "User ID: " + userId));						// Log the user ID
		
		RegistrationModel userAcct = regService.getUserById(userId);					// Get the user by username
		model.addAttribute("userAcct", userAcct);									// adds a new UserModel object to the model
		model.addAttribute("title", "Account");										// adds a title attribute to the model - Account
		model.addAttribute("username", username);									// adds a name attribute to the model - Account
		return "account_edit";														// returns view - account
	}
	
	/**
	 * Update the account page
	 * @param user
	 * @param bindingResult
	 * @param authentication
	 * @param redirectAttributes
	 * @param model
	 * @return account page
	 */	
	@PostMapping("/update/")																// Post Mapping for /root
	public String updateAccount(@Valid @ModelAttribute("userAcct" ) UserModel user,		// Get the registration object
								BindingResult bindingResult,							// Get the binding result
								Authentication authentication, 							// Get the authentication object
								RedirectAttributes redirectAttributes,					// Redirect attributes	
								Model model) {											// Method to display the account form
		
		authentication = SecurityContextHolder.getContext().getAuthentication();		// Get the authentication object
		String username = authentication.getName();										// Get the username of the authenticated user
		int userId = loginAccess.getIdByUsername(username);								// Find the user by username
		user.setUserId(userId);															// Set the user ID
		
		if(bindingResult.hasErrors()) {												// If there are errors in the binding result
			System.out.println(Utilities.warningColor(errPrefix + 
							   "Error in binding result"));				// If there are errors in the binding result
			return "account_edit";													// returns view - account
		}
		if(regService.updateUserInfo(user)) {
			System.out.println(Utilities.successColor(errPrefix + 
							   "User updated successfully"));			// If the user was registered successfully
		} else { 																	// If the user was not registered successfully
			System.out.println(Utilities.errColor(errPrefix + 
							   "User failed to update"));				// If the user was not registered successfully
			return "account_edit";												// returns view - account
		}
		
		redirectAttributes.addFlashAttribute("userAcct", user);						// Add the user object to the redirect attributes
		
		redirectAttributes.addFlashAttribute("success", "Account updated successfully");	// Add a success message to the redirect attributes
		model.addAttribute("username", authentication.getName());
		return "redirect:/user/dashboard/";											// returns view - account
	}
}
