/**
 * AdminController.java
 */
package cloud.main.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class handles requests for the admin dashboard.
 * It displays the admin dashboard and handles requests to view the dashboard by username.
 */
@Controller
@RequestMapping("/admin/dashboard")													// Request mapping for /admin`
public class AdminController {

	/**
	 * This method handles GET requests to the /admin/dashboard URL.
	 * @param model
	 * @param authentication
	 * @return admin dashboard view
	 */
	@GetMapping("/")																// Request mapping for /admin/
	public String display(Model model, Authentication authentication) {				// Method to display the admin page
		authentication = SecurityContextHolder.getContext().getAuthentication();	// Get the authentication object
		String username = authentication.getName();									// Get the username of the authenticated user
		
		
		model.addAttribute("title", "Admin Dashboard");								// adds a title attribute to the model - Admin Dashboard
		model.addAttribute("message", "Welcome to the Admin Dashboard");			// adds a message attribute to the model - Welcome to the Admin Dashboard
		model.addAttribute("username", username);
		
		return "admindashboard";													// returns view - admin
	}
	
}
