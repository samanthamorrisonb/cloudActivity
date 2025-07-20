/**
 * UserInterface.java
 * Cody Crosby, 2025
 * GCU : CST-339
 */
package cloud.main.data;

import cloud.main.data.entity.UserEntity;

/**
 * UserInterface
 * This interface defines the methods for accessing the user data from the database.
 * It is used by the UserAccess class.
 * @param <T> 
 */
public interface UserInterface <T> {
	/**
	 * Get a user by ID
	 * @param t
	 * @return
	 */
	public T getUserById(int t);											// Get a user by ID
	/**
	 * Update a user 
	 * @param t
	 * @return
	 */
	public boolean updateUser(T t);										// Update an existing user
	/**
	 * Get a user by username
	 * @param t
	 * @return
	 */
	public boolean deleteUser(T t);										// Delete a user
	boolean updateUser(UserEntity t);

}
