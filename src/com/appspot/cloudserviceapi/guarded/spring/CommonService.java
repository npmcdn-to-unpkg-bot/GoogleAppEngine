package com.appspot.cloudserviceapi.guarded.spring;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class CommonService {

	/**
	 * http://stackoverflow.com/questions/5810608/how-to-know-if-current-web-visitor-logged-in-with-spring-security-3-0
	 */
	public static String getUsername() {
		Object principal = null;
		try {
			principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if (principal == null)
			return null;
		if (principal instanceof String)
			return (String) principal;
		if (principal instanceof User)
			return ((User) principal).getUsername();
		return null;
	}
	
	/** 
	 * This does not check for authorization!!!
	 */
	public static boolean isAuthenticated() {
		boolean retVal = false;		//by default, not authenticated
		if(getUsername() != null && !"anonymousUser".equals(getUsername())) {
			retVal = true;
		}
		return retVal;
	}
}
