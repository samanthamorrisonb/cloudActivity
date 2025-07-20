/**
 * UserRepository
 * Cody Crosby, 2025
 * GCU : CST-339
 */
package cloud.main.data.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cloud.main.data.entity.UserEntity;

/**
 * UserRepository interface for CRUD operations on UserEntity
 * This interface extends CrudRepository to provide basic CRUD operations
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	@Query(value = "SELECT * FROM user WHERE user_id = :userId")			// This query will select a user by their user ID
	public String findByUserId(@Param("userId") int userId);

}
