package com.appspot.cloudserviceapi.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Disclaimer:
 * 
 * I could not make this piece of codes work against Apache DS 1.5.7 on Mac OS X 10.6 using ldapsearch. Please use Apache Directory Studio as the tool for 
 * development and troubleshooting.
 *
 */
public class ShiroLDAPTest {
    /**
     * Useful information about Apache DS:
     * http://www.ibm.com/developerworks/web/library/wa-apacheshiro/
     * https://cwiki.apache.org/DIRxSRVx10/13-installing-and-starting-the-server.htm
	 * http://directory.apache.org/studio/download/download-macosx.html
	 * http://www.infoq.com/articles/apache-shiro
     */
    public static void main(String[] args) {

    	//=== make sure you start your Apache DS first e.g.
    	//$sudo /usr/local/apacheds-1.5.7/bin/apacheds.init start default
    	//=== to test if it is up, you can use something like the following -
    	//$ldapsearch -h localhost -p 10389 -D "uid=admin,ou=system" -w secret -b "ou=people,o=sevenSeas" -s sub "(&(objectClass=person)(givenName=William))" uid mail
        
    	// Using the IniSecurityManagerFactory, which will use the an INI file
        // as the security file.
        Factory<org.apache.shiro.mgt.SecurityManager> factory = 
        		new IniSecurityManagerFactory("war/WEB-INF/actived.ini");

        // Setting up the SecurityManager...
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        System.out.println("Connected to LDAP server.");
        Subject user = SecurityUtils.getSubject();
        System.out.println("Anonymous user is authenticated:  " + user.isAuthenticated());

        String realm = "cn=Cornelius Buckley,ou=people,o=sevenSeas";
        UsernamePasswordToken token = new UsernamePasswordToken(realm, "whatever");
        try {
			user.login(token);
		} catch (AuthenticationException e) {
			System.err.println("Not able to authenticat user " + realm);
		}
        System.out.println(realm + " user is authenticated:  " + user.isAuthenticated());

        realm = "uid=admin,ou=system";
        token = new UsernamePasswordToken(realm, "secret");
        try {
			user.login(token);
		} catch (AuthenticationException e) {
			System.err.println("Not able to authenticat user " + realm);
		}
        System.out.println(realm + " user is authenticated:  " + user.isAuthenticated());
    
    
    }
}