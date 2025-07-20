/**
 * LoginAccess.java
 * Samantha Beauchamp
 * CST-339
 * 4/10/2025 
 */
package cloud.main.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cloud.main.data.entity.LoginEntity;
import cloud.main.data.entity.UserEntity;
import cloud.main.data.repository.LoginRepository;
import cloud.main.mapper.LoginMapper;
import cloud.main.utilities.Utilities;

/**
 * LoginAccess
 * This class implements the LoginAccessInterface and provides methods for accessing the login data from the database.
 * It is used by the LoginController class.
 * @param <T> 
 */
@Service
@Primary
public class LoginAccess implements LoginAccessInterface<LoginEntity> {
	private String errPrefix = "Login Access-> ";				// Error prefix for logging
	@Autowired
	private LoginRepository loginRepository;					// Login repository for accessing the database
	
	
	@SuppressWarnings("unused")
	private DataSource dataSource;								// Data source for accessing the database	
	private JdbcTemplate jdbcTemplate;							// JdbcTemplate for executing SQL queries

	/**
	 * Constructor
	 * @param loginRepository
	 * @param dataSource
	 */
	public LoginAccess(LoginRepository loginRepository, DataSource dataSource) {
		this.loginRepository = loginRepository;					// Initialize the login repository
		this.dataSource = dataSource;							// Initialize the data source
		this.jdbcTemplate = new JdbcTemplate(dataSource);		// Initialize the JdbcTemplate
	}
	
	/**
	 * Get login information by ID
	 * @return a list of LoginEntity objects
	 */
	@Override
	public LoginEntity getById(LoginEntity t) {
		return loginRepository.findByUserId(t.getUserId());		// Get the user by ID
	}
	/**
	 * Get a user by ID
	 * @return a list of LoginEntity objects
	 */
	@Override
	public LoginEntity getUserLoginById(int t) {
		return loginRepository.findByUserId(t);					// Get the user Login by ID
	}
	/**
	 * Create a new user
	 * @param t
	 */
	@Override
	public boolean create(LoginEntity t) {
		try {													// try to create a new user
			loginRepository.save(t);							// Save the user to the database
			return true;										// Return true if successful
		} catch (Exception e) {									// Catch any exceptions
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error creating user: " + 
							   e.getMessage()));				// Log the error
		} 
		return false;											// Return false if unsuccessful	
	}
	/**
	 * Get the user ID by username
	 * @param name
	 * @return the user ID
	 */
	@Override
	public int getIdByUsername(String name) {
		
		if(name == null || name.isEmpty()) {					// Check if the username is null or empty
			System.out.println(Utilities.errColor(errPrefix + 
							  "Username is null or empty"));	// Log the error
			return 0;											// Return 0 if the username is null or empty
		} else {
			LoginEntity user = loginRepository.findByUsername(name);	// Get the user by username
			
			int userid = user.getUserId();								// Get the user ID
			return userid;
		}
		
														// Return the user ID
	}
	/**
	 * Get a list of all users
	 * @return a list of LoginEntity objects
	 */
	@Override
	public List<LoginEntity> getAll() {
		
			String sql = "SELECT * FROM user_credentials";								// SQL query to select all users
			List<LoginEntity> users = jdbcTemplate.query(sql, new LoginMapper());		// Execute the query and map the results to UserEntity objects
			return users;																// Return the list of users
	}
	/**
	* Checks a users role by their ID
	* @param userId The ID of the user whose role is to be checked
	* @return A list of role names associated with the user
	*/
	public List<String> findRoleIdByUserId(int userId) {
		List<String> roleName = new ArrayList<String>();								// Initialize the role name
		int role = loginRepository.findRoleIdByUserId(userId);							// Get the user by username
		if(role == 1) {																	// Check if the user is an admin	
			roleName.add("ROLE_admin");													// Check if the user is an admin
		} else if(role == 2) {															// Check if the user is a user	
			roleName.add("ROLE_user");													// Check if the user is a user	
		} 							
		return roleName;																// Return the user  role
	}
	/**
	 * Get a user by username
	 * @param username
	 * @return the user
	 */
	public LoginEntity findByUsername(String username) {
		return loginRepository.findByUsername(username);								// Get the user by username	
	}
	
	/**
	 * Update a user
	 * @param t
	 */
	@Override
	public boolean update(LoginEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Delete a user
	 * @param t
	 */
	@Override
	public boolean delete(LoginEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Update a user
	 * @param t
	 */
	@Override
	public boolean updateUserInfo(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

}
