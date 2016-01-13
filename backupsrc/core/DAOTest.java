package passwordchange.core;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;


/**
 * Setup: Do not forget to add the following 4 jar files to avoid
 * "No API environment is registered for this thread" exception:
 * ${SDK_ROOT}/lib/impl/appengine-api.jar
 * ${SDK_ROOT}/lib/impl/appengine-api-labs.jar
 * ${SDK_ROOT}/lib/impl/appengine-api-stubs.jar
 * ${SDK_ROOT}/lib/testing/appengine-testing.jar
 * 
 * http://stackoverflow.com/questions/5405211/initialize-local-datastore-
 * exception-no-api-environment-is-registered-for-this
 * 
 * Useful commands:
 * 
 * https://github.com/vakuum/tcptunnel
 * 
 * ./tcptunnel --local-port=4433 --remote-port=443 --remote-host=api.parse.com --log --stay-alive
 * 
 */
public class DAOTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	// @Test
	public void checkValidUser() {
	}

	@Test
	public void changePassword() {
		String username = "ie";
		String password = "ie";
		String newPassword = "ie111";
		System.out.println("changePassword user username [" + username
				+ "] newPassword[" + newPassword + "]");

		DAO changeDAO = new DAO();
		Result passwordChangeResult = changeDAO.changePassword(username, password, newPassword);
	}
	
	@Test
	public void resetPassword() {
		String username = "ie";
		String password = "ie111";
		String newPassword = "ie";
		System.out.println("resetPassword user username [" + username
				+ "] newPassword[" + newPassword + "]");

		DAO changeDAO = new DAO();
		try {
			changeDAO.getConnection(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Result passwordChangeResult = changeDAO.resetPassword(username, newPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
