package com.appspot.cloudserviceapi.sgc;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Protect {

	public static boolean isAdmin() {
		boolean retVal = false;
		
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    String userID = null;
	    if (user != null) {
	    	userID = user.getNickname();
	    	if("jemtan".equals(userID) || "soapygreencleaning".equals(userID)) {
	    		retVal = true;
	    	}
	    }
	    
	    return retVal;
	}
}
