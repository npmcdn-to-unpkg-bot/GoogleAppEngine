package com.appspot.cloudserviceapi.security.spring;

public interface UserDetailsDAO {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

//	UserAttempts getUserAttempts(String username);
	
	GaeCache getUserAttempts();
	

	GaeUserDetails loadUserByUsername(String username);

}