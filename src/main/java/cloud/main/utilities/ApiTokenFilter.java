/**
 * ApiTokenFilter.java
 * Cody Crosby
 * GCU, 2025
 */
package cloud.main.utilities;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This filter checks for a specific API token in the request header.
 * If the token is valid, it sets the authentication in the security context.
 * If the token is missing or invalid, it returns a 401 Unauthorized response.
 */
public class ApiTokenFilter extends OncePerRequestFilter {

	private static final String API_TOKEN = "SuperSecretPassword";
	
	/**
	 * Filter to check for the API token in the request header
	 * @param request
	 * @param response 
	 * @param filterChain
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		
		// Check if the request is for the API service
		if (path.startsWith("/service/")) {
			
			String authHeader = request.getHeader("Authorization"); // Get the Authorization header
			if (authHeader == null 
					|| !authHeader.startsWith("Bearer ") // Check if the header starts with "Bearer "
					|| !API_TOKEN.equals(authHeader.substring(7))) { // Check if the token is valid
				
				// Return 401 if the token is missing or invalid
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or Inavalid token");
				
				return;
			}
			
			// If token is valid, set the authentication in the security context
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	                "apiuser", null, List.of(new SimpleGrantedAuthority("ROLE_API"))); // or your desired roles
	        SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		 
		// Continue the filter chain
		filterChain.doFilter(request, response);
		
	}

}
