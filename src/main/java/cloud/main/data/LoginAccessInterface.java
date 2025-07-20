/**
 * loginAccessInterface.java
 * Samantha Beauchamp
 * CST-339
 */
package cloud.main.data;

import java.util.List;

import cloud.main.data.entity.UserEntity;

/**
 * LoginAccessInterface
 * This interface defines the methods for accessing the login data from the database.
 * It is used by the LoginAccess class.
 * @param <T> 
 */
public interface LoginAccessInterface <T> {

	public List<T> getAll();												// Get all users
	/**
	 * Get a user by ID
	 * @param t
	 * @return
	 */
	public T getById(T t);											// Get a user by ID
	public T getUserLoginById(int t);									// Get a user by ID
	/**
	 * Get a user by login information
	 * @param t
	 * @return
	 */
	public int getIdByUsername(String name);									// Get a user by username
	/**
	 * create user 
	 * @param t
	 * @return
	 */
	public boolean create(T t);										// Create a new user
	/**
	 * Update a user 
	 * @param t
	 * @return
	 */
	public boolean update(T t);										// Update an existing user
	/**
	 * Get a user by username
	 * @param t
	 * @return
	 */
	public boolean delete(T t);										// Delete a user
	boolean updateUserInfo(UserEntity t);
	
}
