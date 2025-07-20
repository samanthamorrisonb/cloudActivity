/**
 * LoginService.java
 * Samantha Beauchamp
 * CST-339
 * 4/3/2025
 */
package cloud.main.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cloud.main.data.LoginAccess;
import cloud.main.data.entity.LoginEntity;
import cloud.main.model.LoginModel;
import cloud.main.model.UserModel;
import cloud.main.utilities.Utilities;
/**
 * LoginService class implements the LoginServiceInterface to provide
 * functionality for user login and logout.
 */
@Service
public class LoginService implements LoginServiceInterface, UserDetailsService {
	private String errPrefix = "Login Service-> ";			// Error prefix for logging
	@Autowired
	private LoginAccess loginAccess;	// LoginRepository object
	
	public LoginService(LoginAccess loginAccess) {
		this.loginAccess = loginAccess;
	}
	
	/**
	 * Gets the username by user ID.
	 * @param userId the ID of the user
	 * @return the username of the user
	 */
	@Override
	public String getUserNameById(int userId) {
		try {
			LoginEntity user = loginAccess.getUserLoginById(userId);		// Get the user by ID
			LoginModel userModel = new LoginModel();						// Create a new user model
			
			userModel.setUsername(user.getUsername());						// Set the username

			return userModel.getUsername();									// Return the user
		} catch (Exception e) {												// Catch any exceptions that occur
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error getting user by ID: " + 
							   e.getMessage()));								// Print error message to console
			e.printStackTrace();											// Print the stack trace for debugging
		}
		return null;														// Return null if user not found
	}
	/**
	 * Gets a user by username
	 * @param userId the ID of the user
	 * @return
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    LoginEntity user = loginAccess.findByUsername(username);			// Find the user by username
	    if (user == null) {													// If user not found
	        throw new UsernameNotFoundException(
	        				Utilities.warningColor(errPrefix + 
	        				"User not found: " + 
	        				username));										// Throw exception
	    }

	    List<String> roles = loginAccess
	    		.findRoleIdByUserId(user.getUserId());						// Get the roles for the user

	    List<GrantedAuthority> authorities = roles.stream()					// Convert roles to GrantedAuthority
	            .map(SimpleGrantedAuthority::new)							// Create a new SimpleGrantedAuthority for each role
	            .collect(Collectors.toList());								// Collect the authorities into a list

	    return User.withUsername(user.getUsername())						// Create a new User object
	               .password(user.getPassword())							// Set the password
	               .authorities(authorities)								// Set the authorities	
	               .build();												// Build the User object	
	}
	/**
	 * Gets the user ID by username.
	 * @param username the username of the user
	 * @return the ID of the user
	 */
	@Override
	public int getUserId(String username) {
        return loginAccess.getIdByUsername(username);						// Get the user ID by username
    }
	
	/**
	 * Gets the user by ID.
	 * @param userId the ID of the user
	 * @return the user model
	 */
	@Override
	public UserModel getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Gets the user login by id
	 * @param userId
	 * @return
	 */
	@Override
	public LoginModel getUserLoginById(int userId) {
		try {																//try to get the user login by ID
			LoginEntity userLog = loginAccess.getUserLoginById(userId);		// Get the user login by ID
			LoginModel userLogModel = new LoginModel(); 					// Create a new instance of the LoginModel class
			userLogModel.setUsername(userLog.getUsername()); 				// Set the username
			userLogModel.setPassword(userLog.getPassword()); 				// Set the password
			
			return userLogModel; 											// Return the user login model
		} catch (Exception e) {												// Catch any exceptions that occur
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error getting user login by ID: " + 
							   e.getMessage()));								// Print error message to console
			e.printStackTrace();											// Print the stack trace for debugging	
		}
		return null;														// Return null if user login not found	
	}

}
