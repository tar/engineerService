package ru.ics.engineerService.persistence.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import ru.ics.engineerService.persistence.dao.UserDao;
import ru.ics.engineerService.persistence.entity.User;


/**
 * Special class for the organization of Spring Security.
 * 
 * @author <a href="mailto:mlinnik@itcwin.com">Linnik Mihail</a>
 */

public class UserDetailsDaoService implements UserDetailsService {

	private final UserDao userDao;

	public UserDetailsDaoService(UserDao userDao) {
		Assert.notNull(userDao);
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = null;

		// System.out.println(username);

		try {
			user = userDao.get(username);
		} catch (EmptyResultDataAccessException e) {
			throw new UsernameNotFoundException("Couldn't login.");
		}
//		if(userDao.getCurrentUsername()==user.getUsername()){
//			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
//					true, false, true, getAuthorities(user));
//		}
		// System.out.println(user);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
				true, true, true, getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl(user.getAuthority().getRole_name()));
		return authorities;
	}

	

}
