package com.appspot.cloudserviceapi.google;

public class LoggerUtil {

	/**
	 * Created resulted from the issue http://code.google.com/p/googleappengine/issues/detail?id=4591.
	 */
	public static final boolean isDevelopment() {
		return System.getProperty("com.google.appengine.runtime.environment").equals("Development");
	}
	
}
