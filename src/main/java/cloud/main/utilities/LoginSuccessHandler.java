/**
 * LoginSuccessHandler.java
 * Cody Crosby
 * GCU, 2025
 */
package cloud.main.utilities;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class handles successful authentication events.
 * It redirects users to different pages based on their roles.
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * This method is called when authentication is successful.
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @param authentication the Authentication object
	 * @throws IOException if an I/O error occurs
	 * @throws ServletException if a servlet error occurs
	 */
    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication authentication) 
										throws IOException, ServletException {
		
		if(authentication
					.getAuthorities()
					.toString()
					.contains("ROLE_admin")) {										// Check if the user has the admin role
			response.sendRedirect("/admin/dashboard/");					// Redirect to the admin dashboard
		} else if(authentication
					.getAuthorities()
					.toString()
					.contains("ROLE_user")) {										// Check if the user has the user role
			response.sendRedirect("/user/dashboard/");					// Redirect to the user dashboard
		}
	}

	
}