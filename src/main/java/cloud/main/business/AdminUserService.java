/**
 * AdminUserService.java
 * This class provides the business logic for managing all users.
 * Samantha Beauchamp
 * CST-339
 * 5/5/2025
 */
package cloud.main.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.main.data.AdminUserAccess;
import cloud.main.data.entity.AdminUserEntity;
import cloud.main.model.RegistrationModel;
import cloud.main.utilities.Utilities;

/**
 * AdminUserService class implements the AdminUserServiceInterface and provides methods to manage users.
 * It interacts with the AdminUserAccess data access layer to perform CRUD operations on user data.
 */
@Service
public class AdminUserService implements AdminUserServiceInterface {

	private String errPrefix = "Admin User Service-> ";					 // Error prefix for logging
	@Autowired
	private AdminUserAccess adminUserAccess;					 // Access to the admin user table in the database
	
	
	/**
	 * Find all Users on the app 
	 * @return List of RegistrationModel objects representing all users
	 * 
	 */
	@Override
	public List<RegistrationModel> findAllUsers() {
		try {
			List<AdminUserEntity> users = adminUserAccess.findAll(); 						// Call the findAll method from the data access layer
			List<RegistrationModel> userModels = new ArrayList<>(); 						// Create a list to hold the converted models
			for(int i=0; i<users.size(); i++) {												// Loop through the list of users
				AdminUserEntity user = users.get(i); 										// Get the current user entity
				RegistrationModel model = new RegistrationModel(); 							// Create a new model
				model.setFirstName(user.getFirstName()); 									// Set the first name
				model.setLastName(user.getLastName()); 										// Set the last name
				model.setEmail(user.getEmail()); 											// Set the email
				model.setPhone(user.getPhone()); 											// Set the phone number
				model.setCreatedDate(user.getCreatedDate()); 								// Set the created date
				model.setUpdatedDate(user.getUpdatedDate()); 								// Set the updated date
				model.setUsername(user.getUsername()); 										// Set the username
				model.setPassword(user.getPassword()); 										// Set the password
				model.setRole(user.getRole()); 												// Set the role
				model.setUserId(user.getUser_id()); 										// Set the user ID
				userModels.add(model); 														// Add the model to the list
			}
			return userModels; 																// Placeholder for actual implementation
		} catch (Exception e) {
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error retrieving users: " + e.getMessage())); 				// Print error message to console
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * Find a user by ID
	 * @param id The ID of the user to find
	 * @return RegistrationModel object representing the user with the given ID
	 */
	@Override
	public RegistrationModel findById(int id) {
		try {
			AdminUserEntity user = adminUserAccess.findById(id); 							// Call the findAll method from the data access layer
			RegistrationModel model = new RegistrationModel(); 								// Create a new model
			
			if(user != null) { 																// Check if the user is null
				
				model.setFirstName(user.getFirstName()); 									// Set the first name
				model.setLastName(user.getLastName()); 										// Set the last name
				model.setEmail(user.getEmail()); 											// Set the email
				model.setPhone(user.getPhone()); 											// Set the phone number
				model.setCreatedDate(user.getCreatedDate()); 								// Set the created date
				model.setUpdatedDate(user.getUpdatedDate()); 								// Set the updated date
				model.setUsername(user.getUsername()); 										// Set the username
				model.setPassword(user.getPassword()); 										// Set the password
				model.setConfirmPassword(user.getPassword()); 								// Set the confirm password
				model.setUserId(id); 														// Set the user ID	
				model.setRole(user.getRole()); 												// Set the role
				model.setUserId(user.getUser_id()); 										// Set the user ID
				return model; 	
			} else { 																		// If the user is null
				System.out.println(Utilities.errColor(errPrefix + "User not found")); 		// Print error message to console
				
			}
																				
																		
		} catch (Exception e) {
			System.out.println(Utilities.successColor(errPrefix + 
							   "Error finding user by ID: " + e.getMessage())); 			// Print error message to console
			e.printStackTrace();															// Print the stack trace for debugging	
		}
		return null;																		// Return null if the user is not found
	}
	/**
	 * Delete a user by id
	 * @param id The id of the user to find
	 * @return boolean indicating whether the user was deleted successfully
	 */
	@Override
	public boolean deleteUser(int id) {
		try {
			return adminUserAccess.delete(id); 												// Call the delete method from the data access layer
		} catch (Exception e) {
			System.out.println(Utilities.successColor(errPrefix + 
							   "Error deleting user: " + e.getMessage())); 					// Print error message to console
			e.printStackTrace();															// Print the stack trace for debugging
		}
		return false;																		// Return false if the user is not deleted		
	}

}
