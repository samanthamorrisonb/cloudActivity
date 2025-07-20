/**
 * AdminUserRepository.java
 * Samantha Beauchamp
 * This is the repository interface for accessing the admin user data in the database.
 */
package cloud.main.data.repository;

import org.springframework.data.repository.CrudRepository;

import cloud.main.data.entity.AdminUserEntity;

/**
 * AdminUserRepository
 * This is the repository interface for accessing the admin user data in the database.
 * It extends the CrudRepository interface provided by Spring Data, which provides
 * basic CRUD operations for the AdminUserEntity.
 */
public interface AdminUserRepository extends CrudRepository<AdminUserEntity, Integer> {

}
