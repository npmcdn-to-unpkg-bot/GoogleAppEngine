package org.usaopengov.action.security;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ognl.OgnlRuntime;

/*
 * http://programmingpanda.blogspot.com/2009/07/struts-2-ongl-issue-on-google-app.html
 * http://code.google.com/p/usaopengov/source/browse/trunk/src/org/usaopengov/action/security/OgnlFixListener.java
 */
public class OgnlFixListener implements ServletContextListener,
		HttpSessionListener, HttpSessionAttributeListener {

	public OgnlFixListener() {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		OgnlRuntime.setSecurityManager(null);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	}

}