/**
 * RegistrationAccess.java
 * Cody Crosby, 2025
 * GCU : CST-339
 */
package cloud.main.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cloud.main.data.entity.*;
import cloud.main.data.repository.LoginRepository;
import cloud.main.mapper.UserEntityRowMapper;
import cloud.main.utilities.Utilities;

/**
 * RegistrationAccess.java
 * This class is responsible for accessing the registration data from the database.
 * It implements the LoginAccessInterface and UserInterface interfaces.
 * @param <T>
 */
@Service
public class RegistrationAccess implements LoginAccessInterface<LoginEntity>, UserInterface<UserEntity> {
	private String errPrefix = "Registration Access";	// Error prefix for logging
	
	@Autowired
	private LoginRepository loginRepository;				// Repository for login entities
	@SuppressWarnings("unused")
	private DataSource dataSource;							// Data source for the database connection
	private JdbcTemplate jdbcTemplate;						// JdbcTemplate for executing SQL queries
	
	/**
	 * Constructor
	 * @param loginRepository
	 * @param userRepository
	 * @param dataSource
	 */
	public RegistrationAccess(LoginRepository loginRepository, DataSource dataSource) {
		this.loginRepository = loginRepository;				// Initialize the login repository
		this.dataSource = dataSource;						// Initialize the data source
		this.jdbcTemplate = new JdbcTemplate(dataSource);	// Initialize the JdbcTemplate
	}
	/**
	 * Get a list of all users
	 * @return a list of UserEntity objects
	 */
	public List<UserEntity> getAllUsers() {													// Method to get all users from the database
		String sql = "SELECT * FROM user";													// SQL query to select all users
		List<UserEntity> users = jdbcTemplate.query(sql, new UserEntityRowMapper());		// Execute the query and map the results to UserEntity objects
		return users;																		// Return the list of users
	}
	/**
	 * Register a new user
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param createdDate
	 * @param updatedDate
	 * @return true if the user was registered successfully, false otherwise
	 */
	@Transactional
	public boolean registerUser(String username, String password, 
								String firstName, String lastName, 
								String email, String phone, 
								String createdDate, String updatedDate) {
		String methodName = "registerUser";													// Method name for logging
		int newUserId = -1;																	// Initialize new user ID
		try {	
			LoginEntity loginEntity = loginRepository
									  .save(new LoginEntity(username, password));			// Save the login entity to the database
			System.out.println(Utilities.successMessage(errPrefix, methodName,  
							   "Login credentials created successfully: " + 
							   loginEntity.getUsername()));									// Print success message
			
			newUserId = loginEntity.getUserId();											// Get the new user ID from the login entity
			
				String sql1 = "INSERT INTO `user` "
							+ "(`user_id`, `first_name`, `last_name`, "
							+ "`email`, `phone`, `created_date`, `update_date`) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)";									// SQL query to insert the user into the database
				
				String sql2 = "INSERT INTO user_roles (roles_id, user_id) VALUES (?, ?)";		// SQL query to insert the user roles into the database
																							// try		
					jdbcTemplate.update(sql1, newUserId, firstName, lastName, 
										email, phone, createdDate, updatedDate);				// Execute the query to insert the user into the database
					System.out.println(Utilities.successMessage(errPrefix, methodName, 
							   "User created successfully: " + firstName + " " + lastName));	// Print success message
				
					jdbcTemplate.update(sql2, 2, newUserId);									// Execute the query to insert the user roles into the database	
					System.out.println(Utilities.successMessage(errPrefix, methodName, 
							   "User roles created successfully for user ID: " + newUserId));	// Print success message
				
				
				
				System.out.println(Utilities
							   	   	.successMessage(errPrefix, 
							   	   					methodName, 
							   	   					"User created Successfully"));				// Print success message
				return true;																	// Return true if the user was registered successfully
			
		} catch (Exception e) {																// Catch any exceptions
			System.out.println(Utilities.errorMessage(errPrefix, methodName, "Error creating user: " + e.getMessage()));					// Print error message
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();			// Rollback the transaction
			return false;																	// Return false if the user was not registered successfully	
		}
	}
	/**
	 * Update an existing user
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param updatedDate
	 * @return true if the user was updated successfully, false otherwise
	 */
	@Transactional
	public boolean updateUser(String username, String password, 
								String firstName, String lastName, 
								String email, String phone, 
								String updatedDate) {
		try {																				// try
			LoginEntity loginEntity = loginRepository.findByUsername(username);				// Save the login entity to the database
			int userId = loginEntity.getUserId();											// Get the new user ID from the login entity	
			String sql1 = "UPDATE `user` "
						+ "SET `first_name` = ?, `last_name` = ?, "
						+ "`email` = ?, `phone` = ?, `update_date` = ? "
						+ "WHERE `user_id` = ?";											// SQL query to insert the user into the database
						
			
			String sql2 = "UPDATE user_credentials "
						+ "SET password = ?, first_name = ? "
						+ "WHERE user_id = ?";												// SQL query to insert the user roles into the database	
					
			jdbcTemplate.update(sql1, firstName, lastName, email, phone, updatedDate, userId);		// Execute the query to insert the user into the database
			
			jdbcTemplate.update(sql2, password, username, userId);									// Execute the query to insert the user roles into the database
			
			System.out.println(Utilities.successColor(errPrefix + 
							   "User registered successfully: " + 
							   firstName + " " + lastName));									// Print success message
			return true;																	// Return true if the user was registered successfully
		} catch (Exception e) {																// Catch any exceptions	
			System.out.println(Utilities.errColor(errPrefix + "Error creating user: " + e.getMessage()));		// Print error message
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();			// Rollback the transaction
			return false;																	// Return false if the user was not registered successfully
		}
	}
	/**
	 * Check if the user already exists
	 * @param username
	 * @return true if the user exists, false otherwise
	 */
	public boolean findByUsername(String username) {
		try {																				// try
			return loginRepository.findByUsername(username) != null;						// Check if the user exists in the database
		} catch (Exception e) {																// Catch any exceptions	
			System.out.println(Utilities.errColor(errPrefix + "Error retrieving user: " + e.getMessage()));					// Print error message
			return false;																	// Return false if the user was not found
		}
	}
	
	/**
	 * Check if the email already exists
	 * @param email
	 * @return true if the email exists, false otherwise
	 */
	public boolean findByEmail(String email) {
		try {
			String sql = "SELECT * FROM user WHERE email = ?";									// SQL query to check if the email exists
			List<UserEntity> users = jdbcTemplate.query(sql, new UserEntityRowMapper(), email);	// Execute the query and map the results to UserEntity objects
			return !users.isEmpty();															// Return true if the list is not empty, indicating that the email exists
		} catch (Exception e) {																	// Catch any exceptions	
			System.out.println(Utilities.errColor(errPrefix + "Error retrieving email: " + e.getMessage()));		// Print error message
			return false;																		// Return false if the email was not found	
		}
	}
	
	/**
	 * Get a list of all users
	 * @return a list of UserEntity objects
	 */
	@Override
	public boolean create(LoginEntity t) {
		try {																					// try	
			loginRepository.save(t); 															// Save the login entity to the database
			return true;																		// Return true if the user was created successfully	
		} catch (Exception e) {																	// Catch any exceptions	
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error creating user: " + 
							   e.getMessage()));													// Print error message
			return false;																		// Return false if the user was not created successfully		
		} 
	}
	
	/**
	 * Get a user by ID
	 * @param t
	 */
	@Override
	public UserEntity getUserById(int t) {
		try {																	// try				
			UserEntity user = jdbcTemplate
							.queryForObject("SELECT * "
										  + "FROM user "
										  + "WHERE user_id = ?", 
										  new UserEntityRowMapper(), t);		// Get the user by ID
			return user;														// Return the user
		} catch (Exception e) {													// Catch any exceptions	
			System.out.println(Utilities.errColor(errPrefix + e.getMessage()));						// Print error message
		}
		return null;															// Return null if the user was not found	
	}
	/**
	
	/**
	 * Delete a user entity
	 * @param t
	 */
	@Override
	public boolean deleteUser(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Get a user by username
	 * @param t
	 */
	@Override
	public int getIdByUsername(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Get a user by ID
	 * @param t
	 */
	@Override
	public LoginEntity getUserLoginById(int t) {
		try {
			LoginEntity user = loginRepository
					.findById(t).orElse(null);					// Get the user by ID
			return user;										// Return the user
		} catch (Exception e) {									// Catch any exceptions
			System.out.println(Utilities.errColor(errPrefix + e.getMessage()));		// Print error message
		}
		return null;
	}
	/**
	 * Get a list of all login entities
	 * @return a list of LoginEntity objects
	 */
	@Override
	public List<LoginEntity> getAll() {
		List<LoginEntity> loginEntities = (List<LoginEntity>) loginRepository.findAll();	// Get all login entities from the database
		return loginEntities;																// Return the list of login entities
	}
	/**
	 * Get a login entity by ID
	 * @param t
	 */
	@Override
	public LoginEntity getById(LoginEntity t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateUser(UserEntity t) {
		try {																				// try
			jdbcTemplate.update("UPDATE user "
								+ "SET first_name = ?, last_name = ?, "
								+ "email = ?, phone = ? "
								+ "WHERE user_id = ?", 
								t.getFirstName(), t.getLastName(), 
								t.getEmail(), t.getPhone(), t.getId());						// Execute the query to update the user
			return true;																	// Return true if the user was updated successfully
		} catch (Exception e) {																// Catch any exceptions	
			System.out.println(Utilities.errColor(errPrefix + e.getMessage()));									// Print error message
			return false;																	// Return false if the user was not updated successfully
		}
		
	}
	@Override
	public boolean update(LoginEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(LoginEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateUserInfo(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

}
