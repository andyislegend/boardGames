package com.softserveinc.edu.boardgames.service.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserRoles;
import com.softserveinc.edu.boardgames.persistence.enumeration.UserStatus;
import com.softserveinc.edu.boardgames.service.UserService;

/**
 * Used for provide UserDetailsService to SprinSecurityConfiguration
 * 
 * @author Andrii Petryk
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private final Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserService userService;

	/**
	 * Load UserDetails into security
	 * 
	 * @param usernmae
	 *            is used for searching a user and providing its password, role,
	 *            state
	 * 
	 */
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUser(username);
		if (user == null) {
			logger.error("Username not found");
			throw new UsernameNotFoundException("Username not found");
		}
		// return new
		// org.springframework.security.core.userdetails.User(user.getUsername(),
		// user.getPassword(),
		// isEnabled(user), true, true, true, getGrantedAuthorities(user));

		return new CustomUserDetails(user.getUsername(), user.getPassword(), isEnabled(user), true, true, true,
				getGrantedAuthorities(user), user.getState());
	}

	public static class CustomUserDetails extends org.springframework.security.core.userdetails.User {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2342743095894600060L;

		private String userSatus;

		public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
				boolean credentialsNonExpired, boolean accountNonLocked,
				Collection<? extends GrantedAuthority> authorities, String userStatus) {
			super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			this.userSatus = userStatus;

		}

		public String getUserSatus() {
			return userSatus;
		}

		public void setUserSatus(String userSatus) {
			this.userSatus = userSatus;
		}

	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserRoles userRoles : user.getUserRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userRoles.toString()));
		}

		logger.debug(authorities);
		return authorities;
	}

	private boolean isEnabled(User user) {

		if (user.getState().equals(UserStatus.DELETED) || user.getState().equals(UserStatus.INACTIVE)) {

			return false;
		} else {

			return true;
		}
	}

}
