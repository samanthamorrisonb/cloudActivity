/**
 * AdminUserAccess.java
 * 
 * This class provides access to the admin user data in the database.
 * It implements the AdminUserAccessInterface and provides methods to create, update, delete, and find admin users.
 * 
 * @author Samantha Beauchamp
 * CST-339
 * 5/5/2025
 * @version 1.0
 */
package cloud.main.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cloud.main.data.entity.AdminUserEntity;
import cloud.main.data.repository.AdminUserRepository;
import cloud.main.mapper.AdminUserMapper;
import cloud.main.utilities.Utilities;

/**
 * AdminUserAccess class provides access to the admin user data in the database.
 * It implements the AdminUserAccessInterface and provides methods to create, update, delete, and find admin users.
 */
@Service
public class AdminUserAccess implements AdminUserAccessInterface<AdminUserEntity> {
	
	String errPrefix = "Admin Access-> ";				// Error prefix for logging
	
	@SuppressWarnings("unused")							// Suppress unused warning for the dataSource variable
	private DataSource dataSource;						// DataSource object for database connection	
	private JdbcTemplate jdbcTemplate;					// JdbcTemplate object for executing SQL queries
	
	/**
	 * Constructor
	 * @param adminUserRepository The repository for admin user data access
	 * @param dataSource The DataSource object for database connection
	 */
	public AdminUserAccess(AdminUserRepository adminUserRepository, 
			   			   DataSource dataSource) {
		this.dataSource = dataSource;										// Initialize the DataSource object
		this.jdbcTemplate = new JdbcTemplate(dataSource);					// Initialize the JdbcTemplate object
	}
	/**
	 * Create a new admin user
	 * @param adminUser The AdminUserEntity object representing the new user
	 */	
	@Override
	public boolean create(AdminUserEntity adminUser) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Update an existing admin user
	 * @param adminUser The AdminUserEntity object representing the user to update
	 * @return true if the user was updated successfully, false otherwise
	 */
	@Override
	public boolean update(AdminUserEntity adminUser) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Delete a user by ID
	 * @param id The ID of the user to delete
	 * @return true if the user was deleted successfully, false otherwise
	 */
	@Override
	public boolean delete(int id) {
		//List<Integer> catalogs = catalogAccess.getCatalogIdsByUserId(id);	// Get the list of catalog IDs associated with the user ID
		/*if (catalogs != null) {												// Check if the list of catalog IDs is not null
			for (Integer catalogId : catalogs) {							// Loop through the list of catalog IDs
				catalogAccess.delete(catalogId);							// Delete the catalog by ID
				storageService.deleteCatalogImages(catalogId);				// Delete the catalog images associated with the catalog ID
			}
		}*/
		
		
		String[] sqlTables = {"user", "user_roles", "user_credentials"};	// Initialize the array with the SQL queries

		for (String sql : sqlTables) {										// Loop through the SQL queries
			String query = "DELETE FROM " + sql + " WHERE user_id = ?";		// SQL query to delete a user
			try {															// try 
				jdbcTemplate.update(query, id);								// Execute the SQL query
			} catch (Exception e) {											// Catch any exceptions that occur
				System.out.println(Utilities.errColor(errPrefix + 
								   "Error deleting user: " + id));			// Print error message
				e.printStackTrace(); 										// Print the stack trace for debugging
			}
		}
		return false;														// Return false if the user was not deleted successfully	
	}
	
	/**
	 * Find all users
	 * @return List of AdminUserEntity objects representing all users
	 */
	@Override
	public List<AdminUserEntity> findAll() {
		String sql = "SELECT * "
					+ "FROM `user_credentials` "
					+ "JOIN `user` AS USERINFO "
					+ "JOIN `user_roles` AS USERROLE "
					+ "WHERE `user_credentials`.`user_id` = USERINFO.user_id "
					+ "AND `user_credentials`.`user_id` = USERROLE.user_id"; 			// SQL query to select all users
		try {
			List<AdminUserEntity> users = jdbcTemplate
											.query(sql, new AdminUserMapper()); 		// Call the query method from the JdbcTemplate class
			return users; 																// Return the list of users
		} catch (Exception e) {															// Catch any exceptions that occur
			System.out.println(Utilities.errColor(errPrefix + 
							   "Error finding all users: " + e.getMessage())); 			// Print error message
			e.printStackTrace(); 														// Print the stack trace for debugging
		}
		return null;																	// Return null if the list of users is empty	
	}

	/**
	 * Find a user by username
	 * @param username The username of the user to find
	 */
	@Override
	public AdminUserEntity findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find a user by email
	 * @param email The email of the user to find
	 */
	@Override
	public AdminUserEntity findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find a user by ID
	 * @param id The ID of the user to find
	 */
	@SuppressWarnings("null")
	@Override
	public AdminUserEntity findById(int id) {
		String sql = "SELECT * "
				+ "FROM `user_credentials` "
				+ "JOIN `user` AS USERINFO "
				+ "JOIN `user_roles` AS USERROLE "
				+ "WHERE `user_credentials`.`user_id` = USERINFO.user_id "
				+ "AND `user_credentials`.`user_id` = USERROLE.user_id "
				+ "AND `user_credentials`.`user_id` = ?"; 										// SQL query to select a user by ID
	try {
		
		AdminUserEntity user = jdbcTemplate
									.queryForObject(sql, new AdminUserMapper(), id);		// Call the queryForObject method from the JdbcTemplate class
		System.out.println(Utilities.successColor(errPrefix + 
						   "User found: " + user.getUsername()));			// Print the username of the found user
		return user;																		// Return the user object
	} catch (Exception e) {																	// Catch any exceptions that occur
		System.out.println(Utilities.errColor(errPrefix + 
						   "Error finding user by ID: " + id + e.getMessage())); 			// Print error message
		e.printStackTrace(); 																// Print the stack trace for debugging
	}
	return null;																			// Return null if the user is not found	
		
	}

}
