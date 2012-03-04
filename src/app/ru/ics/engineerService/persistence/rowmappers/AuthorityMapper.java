package ru.ics.engineerService.persistence.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.ics.engineerService.persistence.entity.Authority;


public class AuthorityMapper implements RowMapper<Authority> {

	@Override
	public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
		Authority auth = new Authority();

		auth.setId_authority(rs.getInt("id_authority"));
		auth.setRole_name(rs.getString("role_name"));

		return auth;
	}

}
