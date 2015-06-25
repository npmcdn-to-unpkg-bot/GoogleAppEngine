package com.appspot.cloudserviceapi.data;

import javax.servlet.ServletContext;

import com.google.appengine.api.utils.SystemProperty;

public class AppEngine {

	public static String getName() {
		return SystemProperty.applicationId.get();
	}
	
	public static boolean isProduction(ServletContext context) {
		boolean onGoogleAppEngine = false;
		
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			onGoogleAppEngine = true;
		}
		
		return onGoogleAppEngine;
	}
	
	public static String getHostName() {
		String retVal = "localhost:8888";	//TBD - need a better way to get the host + port
		
		if(AppEngine.getName() != null && !AppEngine.getName().equals("")) {
			retVal = SystemProperty.applicationId.get() + ".appspot.com";
		}
		
		return retVal;
	}
	
	
}
