package ru.ics.engineerService.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ru.ics.engineerService.persistence.entity.User;
import ru.ics.engineerService.persistence.rowmappers.UserMapper;


/**
 * Data access object for User entity.
 * 
 * @author <a href="mailto:mlinnik@itcwin.com">Linnik Mihail</a>
 */

public class UserDao extends JdbcTemplate {

	private final static Logger logger = Logger.getLogger(UserDao.class);

	private UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	private AuthenticationManager authenticationManager;

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	// -----------------------------------------------------------
	// -----------------------------------------------------------
	// -----------------------------------------------------------
	public User getUser(Integer id) {
		try {
			return (User) this.queryForObject("select * from users as u where u.id_user = ?",
					new Object[] { id }, getUserMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public User getUserByUsername(String username) {
		return get(username);
	}

	public User get(String username) {

		try {
			return (User) this.queryForObject("select * from users as u where u.username = ? and u.enabled = true limit 1", new Object[] { username },
					getUserMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			throw new EmptyResultDataAccessException(0);
		}
	}

	public void deleteUser(User user) {
		deleteUser(user.getId_user());
	}

	// -----------------------------------------------------------
	public void deleteUser(Integer id) {
		this.update("delete from users where id_user = ?", new Object[] { id });
	}

	// -----------------------------------------------------------
	public User addUser(String username, String password, Boolean enabled, Integer id_authority) {
		password = getMD5(password);

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEnabled(enabled);
		user.setId_authority(id_authority);
		addUser(user);

		return user;
	}

	public Integer addUser(User user) throws DuplicateKeyException, DataIntegrityViolationException {
		return this.queryForInt(
				"INSERT INTO users(id_authority, id_publisher, id_country, last_name, first_name, username, "
						+ " password, address, phone, city, state, street, postal, is_receive, enabled) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id_user ",
				new Object[] { user.getId_authority(), 
						user.getLast_name(), user.getFirst_name(), user.getUsername(), user.getPassword(),
						user.getCity(), user.getState(), user.getStreet(),
						user.getPostal(), user.getIs_receive(), user.getEnabled() });
	}

	// -----------------------------------------------------------
	public void updateUser(User user) {
		this.update(
				"UPDATE users SET id_authority=?, id_publisher=?, last_name=?, first_name=?, "
						+ " username=?, password=?, address=?, phone=?, id_country=?, "
						+ " city=?, state=?, street=?, postal=?, is_receive=?, enabled=? " + " WHERE id_user=? ",
				new Object[] { user.getId_authority(),
						user.getLast_name(), user.getFirst_name(), user.getUsername(), user.getPassword(),
						user.getCity(), user.getState(), user.getStreet(),
						user.getPostal(), user.getIs_receive(), user.getEnabled(), user.getId_user() });
	}

	// -----------------------------------------------------------
	public String getMD5(String password) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String md5 = passwordEncoder.encodePassword(password, null);
		return md5;
	}

	// -----------------------------------------------------

	public void save(User user) {
		if (getUser(user.getId_user()) == null) {
			addUser(user);
		} else {
			updateUser(user);
		}
	}

	// -----------------------------------------------------
	public User getCurrentUser() {
		String username = getCurrentUsername();
		logger.info("Current user:" + username);
		if (username != null && !username.equals(""))
			return getUserByUsername(username);
		else
			return null;
	}

	// -----------------------------------------------------
	public List<User> getAllUsers() {
		return this.query("select * from users as u order by u.username", getUserMapper());
	}

	// -----------------------------------------------------
	public List<User> getUsersOverIp(String ip) {
		return this.query("select *"
				+ " from users as u inner join ip_access as ip on u.id_user = ip.id_user where ip.host = ?",
				new Object[] { ip }, getUserMapper());
	}

	// -----------------------------------------------------
	public String getCurrentUsername() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (a == null)
			return null;
		Object principal = a.getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		return username;
	}

	public boolean setCurrentUser(String login, String password) {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(login, password);
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (BadCredentialsException e) {
			return false;
		}
		return true;
	}

	// -----------------------------------------------------
	// -----------------------------------------------------
	// -----------------------------------------------------
}
