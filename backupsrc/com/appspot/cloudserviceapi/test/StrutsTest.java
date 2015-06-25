package com.appspot.cloudserviceapi.test;

/**
 * @Reference: http://seleniumhq.org/docs/05_selenium_rc.html
 */
import static org.junit.Assert.*;

import java.net.URLEncoder;

import com.thoughtworks.selenium.*;
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
public class StrutsTest {
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
	public void Index() throws Exception {
		selenium.open("/gu/homeRegister.action");
        assertTrue("Struts page is not rendered properly.", selenium.isTextPresent("Home"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// super.tearDown();
		// selenium.close();
		selenium.stop();
	}
}
