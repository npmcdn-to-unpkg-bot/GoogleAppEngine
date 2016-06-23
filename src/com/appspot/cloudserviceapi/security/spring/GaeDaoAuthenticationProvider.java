package com.appspot.cloudserviceapi.security.spring;

import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class GaeDaoAuthenticationProvider extends DaoAuthenticationProvider {

//	private void runAuthenticationChecks(GaeUserDetails user) throws Exception {
//		if (!user.isAccountNonLocked()) {
//			throw new LockedException("User account is locked");
//		}
//
//		if (!user.isEnabled()) {
//			throw new Exception("User account is disabled");
//		}
//
//		if (!user.isAccountNonExpired()) {
//			throw new Exception("User account is expired");
//		}
//
//		if (!user.isCredentialsNonExpired()) {
//			throw new CredentialsExpiredException("User credentials have expired");
//		}
//	}

	// @Autowired
	UserDetailsDAO userDetailsDAO;

	public void setUserDetailsDAO(UserDetailsDAO userDetailsDAO) {
		this.userDetailsDAO = userDetailsDAO;
	}

	// @Autowired
	// @Qualifier("userDetailsService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);

			String username = authentication.getName();
			handleReset(authentication, username);

			return auth;

		} catch (BadCredentialsException e) {

			String username = authentication.getName();
			handleReset(authentication, username);
			throw e;

		} catch (LockedException e) {

			String username = authentication.getName();
			handleReset(authentication, username);

			// this user is locked!
			String error = "";
//			UserAttempts userAttempts = userDetailsDAO.getUserAttempts(authentication.getName());
//
//			if (userAttempts != null) {
//				Date lastAttempts = userAttempts.getLastModified();
				error = "User account [" + authentication.getName() + "] is locked!"
//						+ "<br>Last Attempts : " + lastAttempts
				;
//			} else {
//				error = e.getMessage();
//			}

			throw new LockedException(error);
		}

	}

	private void handleReset(Authentication authentication, String username) {
		GaeCache attempts = userDetailsDAO.getUserAttempts();
		int attempted = attempts.get(username);
		// reset the user_attempts only if the maximum retries were reached and yet it has been unlocked!
		GaeUserDetails user = userDetailsDAO.loadUserByUsername(username);
		boolean locked = !user.getAccountNonLocked();
		if(!locked && attempted >= UserDetailsDAOImpl.MAX_ATTEMPTS) {
			//=== reset the cache to 0 as it is no longer locked
			userDetailsDAO.resetFailAttempts(authentication.getName());
		} else {
			if(attempted < UserDetailsDAOImpl.MAX_ATTEMPTS) {
				// invalid login, update to user_attempts
				userDetailsDAO.updateFailAttempts(authentication.getName());
			}
		}
	}

}