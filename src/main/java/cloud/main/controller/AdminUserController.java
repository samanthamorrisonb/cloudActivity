/**
 * AdminUserController.java
 * Samantha Beauchamp
 * 
 */
package cloud.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cloud.main.business.AdminUserServiceInterface;
import cloud.main.data.LoginAccess;
import cloud.main.model.RegistrationModel;
import cloud.main.utilities.Utilities;

import jakarta.validation.Valid;

/**
 * This class handles the requests for the admin user management page.
 * It allows the admin to view, edit, and delete users.
 */
@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

	private String errPrefix = "Admin User Controller-> ";	// Error prefix for logging
	@Autowired
	private LoginAccess loginAccess;	// Access to the login table in the database
	@Autowired
	private AdminUserServiceInterface userService;
	
	/**
	 * Display the list of users
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/")
	public String listUsers(Model model,
							Authentication authentication) {
		
		authentication = SecurityContextHolder.getContext().getAuthentication();		// Get the authentication object
		String username = authentication.getName();										// Get the username of the authenticated user
		int adminId = loginAccess.getIdByUsername(username);								// Find the user by username
		
		List<RegistrationModel> users = userService.findAllUsers();							// Get all users from the database
		model.addAttribute("users", users);													// Add the list of users to the model
		model.addAttribute("adminId", adminId);												// Add the user ID to the model
		model.addAttribute("username", username);											// Add the username to the model
		model.addAttribute("title", "User List");											// Add the title to the model
		
		return "userlist";
	}
	/**
	 * Display the edit user page
	 * @param userinfo
	 * @param userId
	 * @param redirectAttributes
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/edit/")
	public String editUser(@Valid @ModelAttribute RegistrationModel userinfo,				// Catalog object
						   @RequestParam(name="user_id", required=true) int userId, 
						   RedirectAttributes redirectAttributes,							// Redirect attributes
						   Model model,
						   Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();			// Get the authentication object
		String username = authentication.getName();											// Get the username of the authenticated user
		int adminId = loginAccess.getIdByUsername(username);								// Find the user by username
		try {
			RegistrationModel user = userService.findById(userId);								// Get the user by ID
			model.addAttribute("userinfo", user);												// Add the user to the model
			model.addAttribute("adminId", adminId);												// Add the user ID to the model
			model.addAttribute("username", username);											// Add the username to the model
			model.addAttribute("title", "Edit User");											// Add the title to the model
		} catch (Exception e) {
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error retrieving user: " + e.getMessage()));				// Print error message to console
			redirectAttributes.addFlashAttribute("error", "Error retrieving user");			// Add error message
			return "redirect:/admin/users/";												// Redirect to the user list page
		}
		
		return "admin_user_edit";
	}
	/**
	 * Delete a user
	 * @param userinfo
	 * @param redirectAttributes
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/delete/")
	public String deleteUser(@RequestParam(name="user_id", required=true) int userId, 
							 RedirectAttributes redirectAttributes,							// Redirect attributes
							 Model model,
							 Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();			// Get the authentication object
		String username = authentication.getName();											// Get the username of the authenticated user
		int adminId = loginAccess.getIdByUsername(username);								// Find the user by username
		if(userId == adminId) {																// Check if the user ID is not equal to the admin ID
			redirectAttributes.addFlashAttribute("error", "You cannot delete yourself");	// Add error message
			System.out.println(errPrefix + "You cannot delete yourself");					// Log error message
			return "redirect:/admin/users/";												// Redirect to the user list page
		}
		System.out.println(Utilities.warningColor(errPrefix + 
						   "Deleting user with ID: " + userId));							// Log the user ID being deleted
		if(userService.deleteUser(userId)) {												// Delete the user
			redirectAttributes.addFlashAttribute("success", "User deleted successfully");	// Add success message
			System.out.println(Utilities.successColor(errPrefix + 
							   "User deleted successfully"));								// Log success message
		} else {
			redirectAttributes.addFlashAttribute("error", "Error deleting user");			// Add error message
			System.out.println(Utilities.successColor(errPrefix + 
							   "Error deleting user"));										// Log error message
		}
			return "userlist";																// Redirect to the user list page
	}
	
	
	
}
