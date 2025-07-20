/**
 * RegistrationServiceInterface
 * Cody Crosby, 2025
 * GCU : CST-339
 * This interface defines the contract for the registration service.
 */
package cloud.main.business;

import cloud.main.model.LoginModel;
import cloud.main.model.RegistrationModel;
import cloud.main.model.UserModel;

/**
 * This interface defines the contract for the registration service.
 */
public interface RegistrationServiceInterface {
	
	/**
	 * Register a new user
	 * @param registration
	 * @return
	 */
	public boolean registerUser(RegistrationModel registration);	// Register a new user
	/**
	 * Check if the user already exists
	 * @param registration
	 * @return
	 */
	public boolean checkUserExists(RegistrationModel registration); // Check if the user already exists
	/**
	 * Check if the email already exists
	 * @param registration
	 * @return
	 */
	public boolean checkEmailExists(RegistrationModel registration); // Check if the email already exists
	/**
	 * Retrieve a specific user by ID
	 * @param userId
	 * @return
	 */
	public RegistrationModel getUserById(int userId); // Get a user by their ID
	/**
	 * Update an existing user
	 * @param registration
	 * @return
	 */
	public boolean updateUser(RegistrationModel registration); // Update an existing user
	
	public boolean updateUserInfo(UserModel user);
	public boolean updateUserPasword(LoginModel user);
	public boolean updateUserLogin(LoginModel user);
}
