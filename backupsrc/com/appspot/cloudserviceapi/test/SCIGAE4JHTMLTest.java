package com.appspot.cloudserviceapi.test;

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
public class SCIGAE4JHTMLTest {
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
	public void FiOS_SCITrafficWeather() throws Exception {
		selenium.open("/url?target=traffic&value=11102");
		selenium.open("/url?target=weather&value=20170");
	}

	//@Ignore
	@Test
	public void GW_SCIPayment() throws Exception {
		selenium.open("/sci/jsp/payment-form.jsp");
	}

	//@Ignore
	@Test
	public void PC_Tapestry() throws Exception {
		selenium.open("/helloworld");
		// selenium.open("/manager");
	}

	//@Ignore
	@Test
	public void Mobile_SCITV() throws Exception {
		selenium.open("/sci/jsp/tv/main.jsp?decorator=mobile&confirm=true");
	}

	//@Ignore
	@Test
	public void FiOS_SCITokenUpdate() throws Exception {
		selenium.open("/sci/cr");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Create / update");
		selenium.waitForPageToLoad("30000");
		selenium.click("firstName");
		selenium.type("firstName", "j");
		selenium.type("lastName", "t");
		selenium.type("magicKey", "3ff3c2207965359ba8f0108d88410490");
		selenium.click("//input[@value='Create/Update']");
		selenium.waitForPageToLoad("30000");
	}

	//@Ignore
	@Test
	public void FiOS_SCITokenCreateUpdate() throws Exception {
		selenium.open("/sci/tokenregistrationstart");
		selenium.click("link=Create / update");
		selenium.waitForPageToLoad("30000");
		selenium.click("firstName");
		selenium.type("firstName", "j");
		selenium.type("lastName", "t");
		selenium.type("magicKey", "a");
		selenium.click("//input[@value='Create/Update']");
		selenium.waitForPageToLoad("30000");
	}

	//@Ignore
	@Test
	public void GAEJEmail_SCI() throws Exception {
		selenium
				.open("/sci/email?email_to=gaejtest@hotmail.com&email_subject=Hi&email_body=Test");
		assertEquals("Success: Email has been delivered.", selenium
				.getText("xpath=//*"));
	}

	//@Ignore
	@Test
	public void CodesionPreCommit_SCISaveNonIncludedProjectWithoutTicket() throws Exception {
		String bigChanged = "/branches/Web/media/L05/XXXX_L05_07_STDActivity.swf /branches/Web/media/L05/audio/XXXX_L05_07_Girl_DirectContact.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_ExchangeFluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Intro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_MotherBaby.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro2.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3AND.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_CORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_INCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_PARTCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Questions.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_classify_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_consq_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_symptoms_std.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_way_avoid_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_ways_spread_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_direct_co ntact_skin.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_exchange_infected_fluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_hugging_person_w_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_transfer_infect_mombaby.mp3";
		selenium
				.open("/sci/codesionprecommit?service=test1&author=test2&project=test3&organization=test4&youngest=test5&log=test6&changed=test7_"
						+ bigChanged);
		String resp = selenium.getText("xpath=//*");
		System.out.println("resp = '" + resp + "'");
		assertEquals("{action:'allow'}", resp);
	}

	// //@Ignore
	@Test
	public void CodesionPreCommit_SCISaveIncludedProjectWithTicket() throws Exception {
		//use this for manual test 
		//- /sci/codesionprecommit?service=test1&author=test2&project=sci_tvalet&organization=test4&youngest=test5&log=test6%5Bo%3Agadiant%2C%20p%3Atvalet%2C%20s%3Abugz%2C%20t%3A123%2C%20r%3A11%5D&changed=test7
		String bigChanged = "/branches/Web/media/L05/XXXX_L05_07_STDActivity.swf /branches/Web/media/L05/audio/XXXX_L05_07_Girl_DirectContact.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_ExchangeFluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Intro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_MotherBaby.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro2.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3AND.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_CORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_INCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_PARTCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Questions.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_classify_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_consq_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_symptoms_std.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_way_avoid_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_ways_spread_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_direct_co ntact_skin.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_exchange_infected_fluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_hugging_person_w_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_transfer_infect_mombaby.mp3";
		selenium
				.open("/sci/codesionprecommit?service=test1&author=test2&project=tvalet&organization=test4&youngest=test5&log=test6" + URLEncoder.encode("sample='comment[o:gadiant, p:tvalet, s:bugz, t:123, r:11]") + "&changed=test7_"
						+ bigChanged);
		String resp = selenium.getText("xpath=//*");
		System.out.println("resp = '" + resp + "'");
		assertEquals("{action:'allow'}", resp);
	}

	//@Ignore
	@Test
	public void CodesionPreCommit_SCISaveIncludedProjectWithoutTicket() throws Exception {
		String bigChanged = "/branches/Web/media/L05/XXXX_L05_07_STDActivity.swf /branches/Web/media/L05/audio/XXXX_L05_07_Girl_DirectContact.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_ExchangeFluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Intro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_MotherBaby.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro2.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3AND.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_CORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_INCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_PARTCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Questions.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_classify_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_consq_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_symptoms_std.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_way_avoid_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_ways_spread_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_direct_co ntact_skin.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_exchange_infected_fluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_hugging_person_w_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_transfer_infect_mombaby.mp3";
		selenium
				.open("/sci/codesionprecommit?service=test1&author=test2&project=tvalet&organization=test4&youngest=test5&log=test6&changed=test7_"
						+ bigChanged);
		String resp = selenium.getText("xpath=//*");
		System.out.println("resp = '" + resp + "'");
		assertEquals("{action:'deny', reason:'Your either have not supplied any ticket number or it is invalid! Please add one in the format like comment[t:123,s:bugz] together with text (if any) or in this case, verify that the ticket ID 123 exists in the Bugzilla ticketing system.'}", resp);
	}

	//@Ignore
	@Test
	public void CodesionPreCommit_SCIJavaPMDCheck() throws Exception {
		String bigChanged = "/branches/Web/media/L05/XXXX_L05_07_STDActivity.swf /branches/Web/media/L05/audio/XXXX_L05_07_Girl_DirectContact.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_ExchangeFluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Intro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_MotherBaby.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro2.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Outro3AND.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_CORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_INCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Q1_PARTCORRECT.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_Girl_Questions.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_classify_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_consq_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_symptoms_std.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_way_avoid_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_desc_ways_spread_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_direct_co ntact_skin.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_exchange_infected_fluids.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_hugging_person_w_stds.mp3 /branches/Web/media/L05/audio/XXXX_L05_07_transfer_infect_mombaby.mp3";
		selenium
				.open("/sci/svn?token=pgs100&uri=/sci_tvalet/DOCS/BadCodeMockup.java");
		String resp = selenium.getText("xpath=//*");
		System.out.println("resp = '" + resp + "'");
		//assertEquals("PMD Feedback [[TooManyFields] Violation @line: 3 and column: 8, [UselessStringValueOf] Violation @line: 3 and column: 8, [StringInstantiation] Violation @line: 3 and column: 8, [EmptyCatchBlock] Violation @line: 39 and column: 19, [EmptyIfStatement] Violation @line: 42 and column: 34]", resp);
	}
	
	//@Ignore
	@Test
	public void CodesionPostCommit_SCINoCheck() throws Exception {
		selenium
				.open("/sci/codesionpostcommit?service=test1&author=test2&project=tvalet&organization=test4&youngest=test5&log=test6&changed=/branches/Web/media/L05/XXXX_L05_07_STDActivity.swf");
	}
		
	//@Ignore
	@Test
	public void BigTime_SCIRnD() throws Exception {
		selenium
				.open("/sci/bigtime?token=bt100&fd=02%2F10%2F2011&td=09%2F31%2F2011&uri=/BigTime/EAPSA_MGMT.ASP%3FWCI%3DeaMain%26WCE%3DtplBasic%26HTML%3DReportCustom6.htm%26ObjectType%3DEAReportCustom%26ItemID%3D1009%26PkgRptSID%3D80%26PRJ_SID%3D");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// super.tearDown();
		// selenium.close();
		selenium.stop();
	}
}
