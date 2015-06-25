package com.appspot.cloudserviceapi.test;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * $$$$$$$$$$$$$
 * If you are running this directly and not from say AllTests,
 * DO NOT forget to add selenium server jar (e.g. selenium-java-2.25.0.jar) while running this!
 * $$$$$$$$$$$$$
 */
public class SGCGAE4JHTMLTest {

	private static Selenium selenium;

	@BeforeClass
	public static void init() {
//		selenium = new DefaultSelenium("localhost", 4444, "*chrome",
//				"http://localhost:8888/");
//		selenium.start();
		WebDriver driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, "http://localhost:8888");
	}

	//@Ignore
	@Test
	public void PC_SearchEngine() {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.type("q", "launch");
		selenium.keyPress("q", "13");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
	}
	
	//@Ignore
	@Test
	public void PC_SGCDBInit() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
		selenium.type("usernameField", "a");
		selenium.type("passwordField", "test123");
		selenium.keyPress("passwordField", "13");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Administrator");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Initialize Database");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Back to Main");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Log Out Portal");	//just in case already logged on, quick and dirty approach
	}

	//@Ignore
	@Test
	public void PC_SGCCreateEmployee() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
//		selenium.type("usernameField", "a");
//		selenium.type("passwordField", "test123");
//		selenium.keyPress("passwordField", "13");
//		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manager");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manage Employee Account");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Create a new Employee");
		selenium.waitForPageToLoad("30000");
		selenium.type("userId", "alyssa@yahoo.com");
		selenium.type("name", "Alyssa");
		selenium.type("salary", "100");
		selenium.click("//input[@value='Create/Update']");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Back to Main");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Log Out Portal");	//just in case already logged on, quick and dirty approach
	}
	
	//@Ignore
	@Test
	public void PC_SGCCreateWorkOrder() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
//		selenium.type("usernameField", "a");
//		selenium.type("passwordField", "test123");
//		selenium.keyPress("passwordField", "13");
//		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manager");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manage Work Order");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Create a new work order");
		selenium.waitForPageToLoad("30000");
		selenium.type("phone", "7034375049");
		selenium.type("lastName", "James");
		selenium.type("firstName", "T");
		selenium.type("email", "james@yahoo.com");
		selenium.click("//input[@value='Create/Update']");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Back to Main");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Back to Main");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Log Out Portal");	//just in case already logged on, quick and dirty approach
	}
	
	//@Ignore
	@Test
	public void PC_SGCGuestManagerAdministrator() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Sign Guestbook");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Request Cleaning");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Close Request");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
		selenium
				.click("link=View My Report");
		selenium.waitForPageToLoad("30000");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
//		selenium.type("usernameField", "a");
//		selenium.type("passwordField", "test123");
//		selenium.keyPress("passwordField", "13");
//		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manager");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Service Report");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Administrator");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Log Out Portal");	//just in case already logged on, quick and dirty approach
	}
	
	//@Ignore
	@Test
	public void PC_SGCSignGuestbookRequestCleaningViewMyReport() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Sign Guestbook");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Log in");
//		selenium.waitForPageToLoad("30000");
//		selenium.type("email", "jemtan");
//		selenium.click("isAdmin");
//		selenium.click("action");
//		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Request Cleaning");
		selenium.waitForPageToLoad("30000");
		selenium.select("input_15", "label=Mr/Mrs");
		selenium.type("input_3", "James");
		selenium.type("input_4", "Tan");
		selenium.type("input_8_area", "703");
		selenium.type("input_8_phone", "4375049");
		selenium.type("input_6", "jamestanms@hotmail.com");
		selenium.click("collapse-text_58");
		selenium.type("input_33", "one is 8 yr old");
		selenium.click("input_41_0");
		selenium.select("input_38", "label=Long Haired Dog");
		selenium.select("input_39", "label=Small Mammal (Rabbit,Ferret,etc)");
		selenium.select("input_40",
				"label=Exotic Animal(please specify in comments)");
		selenium.type("input_57",
				"lots of fur\n\none of them poop everywhere :(");
		selenium.click("input_34_0");
		selenium.type("input_42", "pollen");
		selenium.click("input_14_1");
		selenium.type("input_47", "this one");
		selenium.type("input_48", "n/a");
		selenium.type("input_49", "n/a");
		selenium.select("input_51", "label=Move In/Out");
		selenium
				.type("input_53", "first one needs deep cleaning at the corner");
		selenium.type("input_53",
				"first one needs deep cleaning at the corners");
		selenium.click("input_55_1");
		selenium.click("input_55_3");
		selenium.click("input_55_4");
		selenium.type("input_56", "clothes are everywhere");
		selenium.click("input_9_pick");
		selenium.type("input_56", "clothes are everywhere :0");
		selenium.type("hour_9", "3");
		selenium.type("hour_9", "4");
		selenium.type("min_9", "10");
		selenium.select("ampm_9", "label=PM");
		selenium.type("input_7_addr_line1", "659 Pemberton Ct");
		selenium.type("input_7_city", "Herndon");
		selenium.type("input_7_state", "VA");
		selenium.type("input_7_postal", "20170");
		selenium.select("input_7_country", "label=United States");
		selenium.type("input_25", "n/a");
		selenium.type("month_9", "10");
		selenium.type("day_9", "11");
		selenium.type("year_9", "2010");
		selenium.type("hour_9", "4");
		selenium.type("min_9", "15");
		selenium.select("ampm_9", "label=PM");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Close Request");
		selenium.waitForPageToLoad("30000");
//		selenium.type("input_10", "1");		//employee with ID 1 must exits, otherwise this test will fail (if you purge employee id 1, you might not get it back, so this test can never pass and this will not be a critical error)
//		selenium.type("input_2", "703");
//		selenium.type("input_11", "4375049");
//		selenium.click("input_9_0");
//		selenium.click("input_9_1");
//		selenium.click("input_9_2");
//		selenium.click("input_9_3");
//		selenium.click("input_9_4");
//		selenium.type("input_4", "9");
//		selenium.click("input_6");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Back to Main");
//		selenium.waitForPageToLoad("30000");
//		selenium
//				.click("link=View My Report");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Back to Main");
//		selenium.waitForPageToLoad("30000");
	}

	//@Ignore
	@Test
	public void PC_SGCCreatePortalUser() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Administrator");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manage Users");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Create a new user");
//		selenium.waitForPageToLoad("30000");
//		selenium.type("userId", "test");
//		selenium.type("password", "test123");
//		selenium.click("//input[@value='Create/Update']");
//		selenium.waitForPageToLoad("30000");
	}

	//@Ignore
	@Test
	public void PC_SGCExportDatabase() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
//		selenium.type("usernameField", "a");
//		selenium.type("passwordField", "test123");
//		selenium.keyPress("passwordField", "13");
//		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manager");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Export Datastore");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Log Out Portal");	//just in case already logged on, quick and dirty approach
	}
	
/*	public void PC_SGC_PHP1() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Manager");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manage Client Account");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=New Row");
		selenium.waitForPageToLoad("30000");
		selenium.type("customerusername", "alyssa");
		selenium.type("customerpassword", "Tan22");
		selenium.type("emailaddress", "alyssatan@hotmail.com");
		selenium.type("firstname", "Alyssa");
		selenium.type("lastname", "Tan");
		selenium.type("city", "Herndon");
		selenium.type("country", "USA");
		selenium.type("timezone", "EST");
		selenium.click("//input[@value='Add Row']");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back To Listing");
		selenium.waitForPageToLoad("30000");
	}
		
	public void PC_SGC_PHP2() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Manager");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manage Employee Account");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=New Row");
		selenium.waitForPageToLoad("30000");
		selenium.type("first_name", "ansalette");
		selenium.type("last_name", "draper");
		selenium.type("first_name", "john");
		selenium.type("username", "johnd");
		selenium.type("password", "test");
		selenium.type("supervisor_flag", "y");
		selenium.click("//input[@value='Add Row']");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back To Listing");
		selenium.waitForPageToLoad("30000");
	}
	
	public void PC_SGC_PHP3() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Manager");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Manage Work Order");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=New Row");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("//input[@value='Add Row']");
//		selenium.waitForPageToLoad("30000");
	}

	public void IPhone_SGCLevel1() throws Exception {
		selenium.open("/sgc/html/iphone");
		selenium.click("link=About");
		selenium.click("backButton");
		selenium.click("link=Cleaning Request");
		selenium.click("backButton");
		selenium.click("link=Close Request");
		selenium.click("backButton");
		selenium.click("link=User's Report");
		selenium.click("link=Service Report");
	}

	public void IPhone_SGCLevel2() throws Exception {
		selenium.open("/sgc/html/iphone");
		selenium.click("link=Cleaning Request");
		selenium.select("input_15", "label=Dr");
		selenium.type("input_3", "Tan");
		selenium.type("input_3", "James");
		selenium.type("input_4", "Bond");
		selenium.type("input_8_area", "703");
		selenium.type("input_8_phone", "4375049");
		selenium.type("input_6", "jamestanms@hotmail.com");
		selenium.type("input_26", "1");
		selenium.type("input_27", "1");
		selenium.type("input_33", "8");
		selenium.click("input_41_0");
		selenium.select("input_38", "label=Exotic Animal(please specify in comments)");
		selenium.type("input_57", "furry creature known as marrie");
		selenium.click("input_34_0");
		selenium.type("input_42", "pollen");
		selenium.type("input_20", "4");
		selenium.type("input_19", "2");
		selenium.type("input_53", "n/a");
		selenium.type("input_56", "n/a");
		selenium.type("input_7_addr_line1", "659 Pemberton Ct");
		selenium.type("input_7_city", "Herndon");
		selenium.type("input_7_state", "VA");
		selenium.type("input_7_postal", "20170");
		selenium.select("input_7_country", "label=United States");
		selenium.type("input_25", "n/a");
		selenium.click("input_2");
		selenium.click("backButton");
		selenium.click("backButton");
		selenium.click("link=Close Request");
		selenium.type("input_10", "1");
		selenium.type("q2_clientPhone", "703");
		selenium.type("input_11", "4375049");
		selenium.click("input_9_0");
		selenium.click("input_9_1");
		selenium.type("q4_hoursSpent4", "1");
		selenium.click("//button[@id='input_6']");
		selenium.click("backButton");
		selenium.click("backButton");
	}
*/	
	
	//@Ignore
	@Test
	public void PC_SGCDBDestroy() throws Exception {
		selenium.open("/sgc/jsp/main.jsp");
		selenium.click("link=Management Console");
		selenium.waitForPageToLoad("30000");
//		selenium.type("usernameField", "a");
//		selenium.type("passwordField", "test123");
//		selenium.click("//input[@value='Login']");
//		selenium.waitForPageToLoad("30000");
		selenium.click("link=Administrator");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Drop Database");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to Main");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Log Out Portal");	//just in case already logged on, quick and dirty approach
	}
		
	@AfterClass
	public static void tearDown() throws Exception {
		// super.tearDown();
		// selenium.close();
		selenium.stop();
	}
}
