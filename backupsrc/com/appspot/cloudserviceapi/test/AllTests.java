package com.appspot.cloudserviceapi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Please do not forget to add server jar (e.g. lib/selenium-server-standalone-2.25.0.jar
 * into your classpath before running this.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	CoreGAE4JHTMLTest.class,
	StrutsTest.class,
	SGCGAE4JHTMLTest.class,
	EOGAE4JHTMLTest.class,
	SCIGAE4JHTMLTest.class,
	WicketTest.class
})

public class AllTests {
	AllTests() {
		String defaultPath = "C:/GoogleAppEngine/lib/webdriver";	//where chromedriver.exe exists!
		System.setProperty("webdriver.chrome.driver", defaultPath);
	}
}


