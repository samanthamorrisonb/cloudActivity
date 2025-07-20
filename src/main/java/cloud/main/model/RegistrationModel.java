/**
 * RegistrationModel
 * Cody Crosby, 2025
 * GCU : CST-339
 * This class will handle the registration model for the application.
 * It contains fields for first name, last name, email, phone number, username, and password.
 * It also includes validation annotations to ensure that the fields are not null or blank.
 */
package cloud.main.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * This class represents the registration model for the application.
 * It contains fields for first name, last name, email, phone number, username, and password.
 * It also includes validation annotations to ensure that the fields are not null or blank.
 */
public class RegistrationModel {

	@NotBlank(message="First name is required")										// Not null validation for first name
	private String firstName;	
	
	@NotBlank(message="Last name is required")										// Not null validation for last name	
	private String lastName;
	
	@NotBlank(message="Email is required")											// Not null validation for email
	@Email(message="Email is not valid")											// Email validation for email
	private String email;
	
	@NotBlank(message="Phone number is required")									// Not null validation for phone number
	private String phone;
	
	@NotNull(message="Username is required")										// Not null validation for username
	@Size(min=1, max=32, message="User name must be between 1 and 32 characters")	// Size validation for username
	private String username;
	
	@NotNull(message="Password is required")										// Not null validation for password
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")	// Size validation for password
	private String password;
	
	@NotBlank(message="Confirm password is required")								// Not null validation for confirm password
    private String confirmPassword;
	private String createdDate;
	private String updatedDate;
	private int userId;																// User ID
	private int role;																// Role ID
	/*************************************** Constructors  *******************************************/
	/**
	 * Default Constructor for RegistrationModel
	 * Initializes the firstName, lastName, email, phone, username and password fields to empty strings.
	 */
	public RegistrationModel() {
		this.userId = 0;				// User ID
		this.firstName = "";		// First name
		this.lastName = "";			// Last name
		this.email = "";			// Email
		this.phone = "";			// Phone
		this.username = "";			// Username
		this.password = "";			// Password
		this.confirmPassword = "";	// Confirm password
		this.createdDate = "";		// Created date
		this.updatedDate = "";		// Updated date
		this.role = 2;				// Role
	}
	
	/**
	 * Constructor for RegistrationModel with parameters
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param username
	 * @param password
	 */
	public RegistrationModel(String firstName, String lastName, String email, 
			String phone, String username, String password, String confirmPassword) {
		this.userId = 0;				// User ID
		this.firstName = firstName;			// First name
		this.lastName = lastName;			// Last name
		this.email = email;					// Email
		this.phone = phone;					// Phone
		this.username = username;			// Username
		this.password = password;			// Password
		this.confirmPassword = "";			// Confirm password
		this.createdDate = "";				// Created date
		this.updatedDate = "";				// Updated date
		this.role = 2;						// Role
	}	
	
	/*************************************** Getters and Setters  *******************************************/
	public String getFirstName() {
		return firstName;					// Getter for firstName
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;			// Setter for firstName
	}
	
	public String getLastName() {
		return lastName;					// Getter for lastName
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;			// Setter for lastName
	}

	public String getEmail() {
		return email;						// Getter for email
	}

	public void setEmail(String email) {
		this.email = email;					// Setter for email
	}

	public String getPhone() {
		return phone;						// Getter for phone
	}

	public void setPhone(String phone) {
		this.phone = phone;					// Setter for phone
	}

	public String getUsername() {
		return username;					// Getter for username
	}

	public void setUsername(String username) {
		this.username = username;			// Setter for username
	}

	public String getPassword() {
		return password;					// Getter for password
	}

	public void setPassword(String password) {
		this.password = password;			// Setter for password
	}
	
	public String getConfirmPassword() {
		return confirmPassword;					// Getter for password
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;			// Setter for password
	}
	public int getUserId() {
		return userId;						// Getter for userId
	}
	public void setUserId(int userId) {
		this.userId = userId;					// Setter for userId
	}
	public String getCreatedDate() {
		return createdDate;					// Getter for createdDate
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;			// Setter for createdDate
	}
	public String getUpdatedDate() {
		return updatedDate;					// Getter for updatedDate
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;			// Setter for updatedDate
	}
	public int getRole() {
		return role;						// Getter for role
	}
	public void setRole(int role) {
		this.role = role;						// Setter for role
	}
	
}
