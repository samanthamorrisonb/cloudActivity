/**
 * AdminUserMapper.java
 * 
 * This class is responsible for mapping the result set from the database to the AdminUserEntity object.
 * It implements the RowMapper interface provided by Spring JDBC.
 * 
 * @author GCU Team
 * @version 1.0
 */
package cloud.main.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

import cloud.main.data.entity.AdminUserEntity;

/**
 * This class is responsible for mapping the result set from the database to the AdminUserEntity object.
 * It implements the RowMapper interface provided by Spring JDBC.
 */
public class AdminUserMapper implements RowMapper<AdminUserEntity> {
	@Override
	public AdminUserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Format for the date and time
		
		String createDate = rs.getTimestamp("created_date")			// Get the created date from the result set
				.toLocalDateTime()
				.format(formatter);
		
		String updateDate = rs.getTimestamp("update_date")			// Get the updated date from the result set	
				.toLocalDateTime()
				.format(formatter);
		AdminUserEntity adminUser = new AdminUserEntity();
		adminUser.setUser_id(rs.getInt("user_id"));
		adminUser.setFirstName(rs.getString("first_name"));
		adminUser.setLastName(rs.getString("last_name"));
		adminUser.setEmail(rs.getString("email"));
		adminUser.setPhone(rs.getString("phone"));
		adminUser.setCreatedDate(createDate);
		adminUser.setUpdatedDate(updateDate);
		adminUser.setUsername(rs.getString("username"));
		adminUser.setPassword(rs.getString("password")); 			// TODO decrypt password
		adminUser.setRole(rs.getInt("roles_id"));
		
		return adminUser;
	}

	
}
