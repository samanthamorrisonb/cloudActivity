package cloud.main.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

import cloud.main.data.entity.UserEntity;

/**
 * This class is responsible for mapping the result set from the database to a UserEntity object.
 * It implements the RowMapper interface provided by Spring JDBC.
 */
public class UserEntityRowMapper implements RowMapper<UserEntity> {
		
	/**
	 * Maps the result set to a UserEntity object
	 * @param rs The result set from the database
	 * @param rowNum The row number
	 * @return A UserEntity object
	 * @throws SQLException If there is an error accessing the result set
	 */
	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Format for the date and time
		
		String createDate = rs.getTimestamp("created_date")			// Get the created date from the result set
				.toLocalDateTime()
				.format(formatter);
		
		String updateDate = rs.getTimestamp("update_date")			// Get the updated date from the result set	
				.toLocalDateTime()
				.format(formatter);
		
		UserEntity user = new UserEntity(rs.getInt("user_id"),		// Get the user ID from the result set
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getString("email"),
				rs.getString("phone"),
				createDate,
				updateDate);
		return user;
	}

}
