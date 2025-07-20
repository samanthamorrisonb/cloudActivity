/**
 * DashboardController.java
 * Cody Crosby, 2025
 * GCU : CST-339
 * This is the controller for the dashboard page.
 * It handles the GET request for the dashboard page and returns the dashboard view.
 */
package cloud.main.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller for the dashboard page.
 * It handles the GET request for the dashboard page and returns the dashboard view.
 */
@Controller
@RequestMapping("/user/dashboard")
public class DashboardController {
	
	/**
	 * Display the dashboard page
	 * @param model
	 * @return dashboard page
	 */
	@GetMapping("/")
    public String displayDashboard(Model model, Authentication authentication) {
		
		authentication = SecurityContextHolder.getContext().getAuthentication();			// Get the authentication object
		String username = authentication.getName();											// Get the username of the authenticated user
        
		model.addAttribute("title", "My Dashboard");										// Add a title attribute to the model - Admin Dashboard	
        model.addAttribute("message", "Welcome " + username + " to the Dashboard!");		// Add a message to the model
        model.addAttribute("username", username);												// Add a message to the model
        return "dashboard"; 																// Looks for dashboard.html in /templates/
    }

}