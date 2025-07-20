/**
 * AdminUserServiceInterface.java
 * Interface for AdminUserService
 */
package cloud.main.business;

import java.util.List;

import cloud.main.model.RegistrationModel;

/**
 * Interface for AdminUserService
 * Provides methods to manage admin users
 */
public interface AdminUserServiceInterface  {

	public List<RegistrationModel> findAllUsers();
	public RegistrationModel findById(int id);
	public boolean deleteUser(int id);
}
