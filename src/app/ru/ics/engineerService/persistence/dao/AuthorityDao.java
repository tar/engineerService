package ru.ics.engineerService.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import ru.ics.engineerService.persistence.entity.Authority;
import ru.ics.engineerService.persistence.rowmappers.AuthorityMapper;


/**
 * Data access object for Authority entity.
 * 
 * @author <a href="mailto:mlinnik@itcwin.com">Linnik Mihail</a>
 */

public class AuthorityDao extends JdbcTemplate {
	private final static Logger logger = Logger.getLogger(AuthorityDao.class);

	private AuthorityMapper authorityMapper;

	public void setAuthorityMapper(AuthorityMapper authorityMapper) {
		this.authorityMapper = authorityMapper;
	}

	public AuthorityMapper getAuthorityMapper() {
		return authorityMapper;
	}

	public Authority getAuthority(Integer id) {

		try {
			return (Authority) this.queryForObject(
					"SELECT a.id_authority, a.role_name FROM authority as a where a.id_authority = ?",
					new Object[] { id }, getAuthorityMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public void deleteAuthority(Authority authority) {
		deleteAuthority(authority.getId_authority());
	}

	public void deleteAuthority(Integer id) {
		this.update("delete from users where id_user = ?", new Object[] { id });
	}

	public Authority addAuthority(String title) {
		Authority auth = new Authority();
		auth.setRole_name(title);
		auth.setId_authority(addAuthority(auth));
		return auth;
	}

	public Integer addAuthority(Authority authority) {
		try {
			return this.queryForInt("INSERT INTO authority(role_name) VALUES (?) returning id_authority",
					new Object[] { authority.getRole_name() });
		} catch (DuplicateKeyException e) {
			logger.error(e.getMessage());
			return null;
		}

	}

	public void updateAuthority(Authority authority) {
		this.update("UPDATE authority role_name=? WHERE SET id_authority=?", new Object[] { authority.getRole_name(),
				authority.getId_authority() });
	}

	public List<Authority> getAllAuthority() {
		return this.query("SELECT a.id_authority, a.role_name FROM authority as a order by a.role_name",
				getAuthorityMapper());
	}

	// -----------------------------------------------------
}
