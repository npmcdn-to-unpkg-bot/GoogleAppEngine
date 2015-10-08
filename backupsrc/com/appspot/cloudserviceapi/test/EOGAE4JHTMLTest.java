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
public class EOGAE4JHTMLTest {
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
	public void PG_EOActivity() throws Exception {
		selenium.open("/eo/html/index.html");
		selenium.click("link=Setting Goal");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=here");
		selenium.waitForPageToLoad("30000");
//		selenium.click("link=exact:Add a new student*");
//		selenium.waitForPageToLoad("30000");
//		selenium.select("details", "label=Three Hours A Week");
//		selenium.select("name", "label=Regular Tutoring");
//		selenium.click("//input[@value='Accept']");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("link=Back");
//		selenium.waitForPageToLoad("30000");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// super.tearDown();
		// selenium.close();
		selenium.stop();
	}
}
