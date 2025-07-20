/**
 * UserRepository
 * Cody Crosby, 2025
 * GCU : CST-339
 */
package cloud.main.data.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cloud.main.data.entity.LoginEntity;

/**
 * LoginRepository
 * @param username the username to set
 */
public interface LoginRepository extends CrudRepository<LoginEntity, Integer> {
	
	@Query(value = "SELECT * FROM user_credentials WHERE username = :username AND password = :password")		// This query will select a user by their username and password
	public LoginEntity findByLoginInfo(@Param("username")String username, @Param("password")String password);
		
		
	@Query(value = "SELECT * FROM user_credentials WHERE user_id = :userId")									// This query will select a user by their user ID
	public LoginEntity findByUserId(@Param("userId")int userId);
	
	@Query(value = "SELECT username FROM user_credentials WHERE user_id = :userId")		// This query will select a user by their username and password
	public String findUserNameByUserId(@Param("userId")int userId);
	
	@Query(value = "SELECT roles_id FROM user_roles WHERE user_id = :userId")
	public int findRoleIdByUserId(@Param("userId")int userId);
	
	public LoginEntity findByUsername(String username);
	
	
}
