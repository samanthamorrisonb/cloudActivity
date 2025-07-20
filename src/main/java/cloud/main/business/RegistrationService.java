/**
 * RegistrationService
 * Cody Crosby, 2025
 * GCU : CST-339
 * This class will handle the registration service for the application.
 * It implements the RegistrationServiceInterface and provides the logic for registering a new user.
 */
package cloud.main.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cloud.main.data.RegistrationAccess;
import cloud.main.data.entity.UserEntity;
import cloud.main.model.LoginModel;
import cloud.main.model.RegistrationModel;
import cloud.main.model.UserModel;
import cloud.main.utilities.Utilities;

/**
 * This class provides the business logic for the registration service.
 * It handles user registration, checking if a user or email already exists,
 * updating user information, and retrieving user details.
 */
@Service
public class RegistrationService implements RegistrationServiceInterface {
	private String errPrefix = "Registration Service-> "; 	// Error prefix for logging
	private Utilities util; 								// Create a new instance of the Utilities class
	@Autowired
	private LoginService loginService; 						// Create a new instance of the LoginService class
	@Autowired
	private RegistrationAccess registrationAccess;		 	// Create a new instance of the RegistrationAccess class
	
	/**
	 * Register a new user
	 * @param registration
	 * @return
	 */
	@Override
	public boolean registerUser(RegistrationModel registration) {
		
		@SuppressWarnings("static-access")
		String time = util.getCurrentTime(); 				// Set the date and time
		
		try {
			if (registrationAccess
					.registerUser(registration.getUsername(), 
						new BCryptPasswordEncoder().encode(registration.getPassword()), 
						registration.getFirstName(), registration.getLastName(), 
						registration.getEmail(), registration.getPhone(), time, time)) {			
				
				System.out.println(Utilities.successColor(errPrefix + 
								"User " + registration.getUsername() + 
								" registered successfully @ " + time));				// If the user was registered successfully
				return true;														// Return true	
			} else { 																// If the user was not registered successfully
				System.out.println(Utilities.successColor(errPrefix + 
								"User " + registration.getUsername() + 
								" failed to register."));							// If the user was not registered successfully
				return false;														// Return false
			}
		} catch (Exception e) {														// If there was an error registering the user
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error creating user: " + 
							   e.getMessage()));									// Log the error	
			return false;															// Return false	
		}
	}
	
	/**
	 * Check if the user already exists
	 * @param registration
	 * @return
	 */
	@Override
	public boolean checkUserExists(RegistrationModel registration) {
		return registrationAccess.findByUsername(registration.getUsername()); 	// Check if the username already exists
	}

	/**
	 * Check if the email already exists
	 * @param registration
	 * @return
	 */
	@Override
	public boolean checkEmailExists(RegistrationModel registration) {
		return registrationAccess.findByEmail(registration.getEmail()); 		// Check if the email already exists
	}

	/**
	 * 	Update all fields of the user
	 * @param registration
	 * @return
	 */
	@Override
	public boolean updateUser(RegistrationModel registration) {
		@SuppressWarnings("static-access")
		String time = util.getCurrentTime(); 									// Set the date and time
		
		try {
			if (registrationAccess
					.updateUser(registration.getUsername(), 
						new BCryptPasswordEncoder().encode(registration.getPassword()), // Encrypt the password
						registration.getFirstName(), registration.getLastName(), 
						registration.getEmail(), registration.getPhone(), time)) {		// Update the user
				
				System.out.println(Utilities.successColor(errPrefix + 
								"User " + registration.getUsername() + 
								" updated successfully @ " + time));					// If the user was registered successfully
				return true;
			} else { // If the user was not registered successfully
				System.out.println(Utilities.errColor(errPrefix + 
								"User " + registration.getUsername()+
								" failed to update."));									// If the user was not registered successfully	
				return false;															// Return false	
			}
		} catch (Exception e) {															// If there was an error registering the user	
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error updaing user: " + 
							   e.getMessage()));											// Log the error	
			return false;																// Return false	
		}
		
	}

	/**
	 * Get user by ID
	 * @param userID
	 * @return
	 */
	@Override
	public RegistrationModel getUserById(int userId) {
		RegistrationModel userFullInfo = new RegistrationModel(); 			// Create a new instance of the RegistrationModel class
		UserEntity userEnt = registrationAccess.getUserById(userId); 		// Get the user by ID
		LoginModel loginMod = loginService.getUserLoginById(userId);		// Get the user login by ID
		userFullInfo.setUsername(loginMod.getUsername()); 					// Set the username
		userFullInfo.setPassword(loginMod.getPassword()); 					// Set the password
		userFullInfo.setFirstName(userEnt.getFirstName()); 					// Set the first name
		userFullInfo.setLastName(userEnt.getLastName()); 					// Set the last name
		userFullInfo.setEmail(userEnt.getEmail()); 							// Set the email
		userFullInfo.setPhone(userEnt.getPhone()); 							// Set the phone number
		userFullInfo.setCreatedDate(userEnt.getCreatedDate()); 				// Set the created date
		userFullInfo.setUpdatedDate(userEnt.getUpdatedDate()); 				// Set the updated date
		
		try {
			
			System.out.println(Utilities.successColor(errPrefix + 
							"User " + userFullInfo.getUsername() +
							"retrieved successfully.")); 					// If the user was retrieved successfully
			return userFullInfo;											// Return the user	
		} catch (Exception e) {												// If there was an error retrieving the user	
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error retrieving user: " +
							   e.getMessage()));								// Log the error	
		}
		return userFullInfo;												// Return the user
	}
	/**
	 * Update user information
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUserInfo(UserModel user) {
		UserEntity userEnt = new UserEntity(); 								// Create a new instance of the UserEntity class
		userEnt.setId(user.getUserId()); 								// Set the user ID
		userEnt.setFirstName(user.getFirstName()); 							// Set the first name
		userEnt.setLastName(user.getLastName()); 							// Set the last name
		userEnt.setEmail(user.getEmail()); 									// Set the email
		userEnt.setPhone(user.getPhone()); 									// Set the phone number
		userEnt.setCreatedDate(user.getCreatedDate()); 						// Set the created date
		userEnt.setUpdatedDate(user.getUpdatedDate()); 						// Set the updated date
		if(registrationAccess.updateUser(userEnt)) {
			System.out.println(Utilities.successColor(errPrefix + 
							  "User updated successfully"));						// If the user was registered successfully
			return true; 														// Return true
		} else { 															// If the user was not registered successfully
			System.out.println(Utilities.successColor(errPrefix + 
							   "User failed to update"));					// If the user was not registered successfully
		}
		return false;
	}
	/**
	 * Update user password
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUserPasword(LoginModel user) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Update user login
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUserLogin(LoginModel user) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
