/**
 * LoginModel
 * Samantha Beauchamp, 2025
 * GCU : CST-339
 * This class represents the login model for the application.
 * It contains the username and password fields, along with validation annotations.
 * It is used in the LoginController to bind the login form data.
 */
package cloud.main.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * This class represents the login model for the application.
 * It contains the username and password fields, along with validation annotations.
 * It is used in the LoginController to bind the login form data.
 */
public class LoginModel {
	
	@NotNull(message = "Username is required")											// Not null validation for username
	@Size(min = 1, max=20, message = "User name must be between 1 and 20 characters")	// Size validation for username
	private String username;
	
	@NotNull(message = "Password is required")											// Not null validation for password
	@Size(min = 1, max=20, message = "Password must be between 1 and 20 characters")	// Size validation for password
	private String password;
	
	/**
	 * Default constructor for LoginModel
	 * Initializes the username and password fields to empty strings.
	 */
	public LoginModel() {				// default constructor
		this.username = "";				// default username
		this.password = "";				// default password
	}
	/**
	 * Constructor for LoginModel with parameters
	 * @param username
	 * @param password
	 */
	public LoginModel(String username, String password) {	// constructor with parameters username and password
		this.username = username;							// username
		this.password = password;							// password	
	}
	/*************************************** Getters and Setters  *******************************************/
	public String getUsername() {
		return username;									// getter for username
	}
	public void setUsername(String username) {
		this.username = username;							// setter for username	
	}
	public String getPassword() {
		return password;									// getter for password	
	}
	public void setPassword(String password) {
		this.password = password;							// setter for password	
	}

}
