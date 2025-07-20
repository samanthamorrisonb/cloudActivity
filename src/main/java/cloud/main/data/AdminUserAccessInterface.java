/**
 * AdminUserAccessInterface.java
 * Samantha Beauchamp
 * CST-339
 * 5/5/2025
 */
package cloud.main.data;

import java.util.List;

import cloud.main.data.entity.AdminUserEntity;

/**
 * Interface for AdminUser data access operations
 * @param <T> The type of the entity
 */
public interface AdminUserAccessInterface <T> {
	
	public boolean create(T adminUser);
	public boolean update(T adminUser);
	public boolean delete(int id);
	public AdminUserEntity findById(int id);
	public List<T> findAll();
	public T findByUsername(String username);
	public T findByEmail(String email);

	
}
