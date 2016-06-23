package com.appspot.cloudserviceapi.security.spring;

public interface UserDetailsDAO {

	public void trackFailAttempts(String username);

	public void resetFailAttempts(String username);

	UserAttempts getUserAttempts(String username);

	GaeUserDetails loadUserByUsername(String username);

}