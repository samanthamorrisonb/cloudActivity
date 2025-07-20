/**
 * UserModel
 * Cody Crosby, 2025
 * GCU : CST-339
 * This class represents the user model for the application.
 * It contains fields for first name, last name, email, phone number, username, and password.
 * It also includes validation annotations to ensure that the fields are not null or blank.
 */
package cloud.main.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * This class represents the user model for the application.
 * It contains fields for first name, last name, email, phone number, username, and password.
 * It also includes validation annotations to ensure that the fields are not null or blank.
 */
public class UserModel {
	
	@NotBlank(message="First name is required")										// Not null validation for first name
	private String firstName;	
	
	@NotBlank(message="Last name is required")										// Not null validation for last name	
	private String lastName;
	
	@NotBlank(message="Email is required")											// Not null validation for email
	@Email(message="Email is not valid")											// Email validation for email
	private String email;
	
	@NotBlank(message="Phone number is required")									// Not null validation for phone number
	private String phone;
	
	private int role;															// Role ID	
	private int userId;															// User ID
	private String createdDate;													// Created date
	private String updatedDate;													// Updated date
	
	/*************************************** Constructors  *******************************************/
	/**
	 * Default Constructor for UserModel
	 * Initializes the firstName, lastName, email, phone, username and password fields to empty strings.
	 */
	public UserModel() {
		this.userId = 0;				// User ID
		this.firstName = "";		// First name
		this.lastName = "";			// Last name
		this.email = "";			// Email
		this.phone = "";			// Phone
		this.role = 0;			// Role
		
		//this.userId = 1;			// User ID TODO will be set by the database
	}
	
	/**
	 * Constructor for UserModel with parameters
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param username
	 * @param password
	 */
	public UserModel(String firstName, String lastName, String email, 
			String phone, String username, String password) {
		this.userId = 0;				// User ID
		this.firstName = firstName;			// First name
		this.lastName = lastName;			// Last name
		this.email = email;					// Email
		this.phone = phone;					// Phone
		this.role = 0;					// Role
		
	}	
	
	/*************************************** Getters and Setters  *******************************************/
	public String getFirstName() { return firstName;} 							// Getter for firstName
	public void setFirstName(String firstName) { this.firstName = firstName;}	// Setter for firstName
	
	public String getLastName() { return lastName; }							// Getter for lastName
	public void setLastName(String lastName) { this.lastName = lastName; }		// Setter for lastName

	public String getEmail() { return email; }									// Getter for email
	public void setEmail(String email) { this.email = email; }					// Setter for email

	public String getPhone() { return phone; }									// Getter for phone
	public void setPhone(String phone) { this.phone = phone; }					// Setter for phone

	public int getRole() { return role; }										// Getter for role
	public void setRole(int role) { this.role = role; }								// Setter for role
	
	public int getUserId() { return userId; }									// Getter for userId
	public void setUserId(int userId) { this.userId = userId; }					// Setter for userId

	public String getCreatedDate() { return createdDate; }						// Getter for createdDate
	public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }	// Setter for createdDate
	public String getUpdatedDate() { return updatedDate; }						// Getter for updatedDate
	public void setUpdatedDate(String updatedDate) { this.updatedDate = updatedDate; }	// Setter for updatedDate
	
	
	
}
