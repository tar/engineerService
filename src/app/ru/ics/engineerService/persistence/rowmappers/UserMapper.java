package ru.ics.engineerService.persistence.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.ics.engineerService.persistence.dao.AuthorityDao;
import ru.ics.engineerService.persistence.entity.User;


public class UserMapper implements RowMapper<User> {

	private AuthorityDao authorityDao;

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	public AuthorityDao getAuthorityDao() {
		return authorityDao;
	}

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		user.setId_user(rs.getInt("id_user"));
		user.setId_authority(rs.getInt("id_authority"));
		user.setLast_name(rs.getString("last_name"));
		user.setFirst_name(rs.getString("first_name"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setCity(rs.getString("city"));
		user.setState(rs.getString("state"));
		user.setStreet(rs.getString("street"));
		user.setPostal(rs.getString("postal"));
		user.setIs_receive(rs.getBoolean("is_receive"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setAuthority(authorityDao.getAuthority(user.getId_authority()));

		return user;
	}

}
