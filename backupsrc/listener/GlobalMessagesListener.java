package com.mkyong.common.listener;
 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
import com.opensymphony.xwork2.util.LocalizedTextUtil;
 
/**
 * http://www.mkyong.com/struts2/how-to-configure-global-resource-bundle-in-struts-2/
 */
public class GlobalMessagesListener implements ServletContextListener {
 
	  private static final String DEFAULT_RESOURCE = "package";
 
	  public void contextInitialized(ServletContextEvent arg0) {
	    LocalizedTextUtil.addDefaultResourceBundle(DEFAULT_RESOURCE);
	  }
 
	  public void contextDestroyed(ServletContextEvent arg0) {
	  }
}