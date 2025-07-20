/**
 * LoginServiceInterface.java
 * Samantha Beauchamp
 * CST-339
 * 4/3/2025
 * 
 */
package cloud.main.business;

import cloud.main.model.LoginModel;
import cloud.main.model.UserModel;

/**
 * This interface defines the methods for the LoginService
 */
public interface LoginServiceInterface {

	/**
	 * Get the user by id
	 * @param userId
	 * @return
	 */
	public UserModel getUserById(int userId);
	/**
	 * Get the user name by id
	 * @param userId
	 * @return
	 */
	public String getUserNameById(int userId);
	/**
	 * Get login credentials by ID
	 * @param userId
	 * @return
	 */
	public LoginModel getUserLoginById(int userId);
	/**
	 * Get user ID by username
	 * @param username
	 * @return
	 */
	public int getUserId(String username);
}
