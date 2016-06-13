package com.appspot.cloudserviceapi.security.spring;

import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;

public class GaeDaoAuthenticationProvider {

	private void runAuthenticationChecks(GaeUserDetails user) throws Exception {
		if (!user.isAccountNonLocked()) {
			throw new LockedException("User account is locked");
		}

		if (!user.isEnabled()) {
			throw new Exception("User account is disabled");
		}

		if (!user.isAccountNonExpired()) {
			throw new Exception("User account is expired");
		}
		
		if (!user.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException("User credentials have expired");
		}
	}

}