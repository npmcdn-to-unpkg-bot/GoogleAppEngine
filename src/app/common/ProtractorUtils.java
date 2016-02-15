package app.common;

import java.util.StringTokenizer;

import org.datanucleus.util.StringUtils;

public class ProtractorUtils {

	public static boolean debug = true;
//	public static boolean debug = false;
	
	public static String header = "var u = require('l.js');var fs = require('fs');" + "\n" +
			"describe('protractor e2e tests', function() {" + "\n" +
			"browser.manage().timeouts().pageLoadTimeout(60000);" + "\n" +
			"browser.manage().timeouts().implicitlyWait(30000);" + "\n" +
			"jasmine.DEFAULT_TIMEOUT_INTERVAL = 6000000;" + "\n" +
			"/* for non-angular page */" + "\n" +
			"browser.ignoreSynchronization = true; /* set this false for AngularJS app */" + "\n" +
			"beforeEach(function() {" + "\n";

	public static String footer = "  });" + "\n" +
				"});" +  "\n" +
				"        }" + "\n" +
				"};";

	public String toScript(String v) {
		StringBuffer sb = new StringBuffer();
		if(!StringUtils.isEmpty(v)) {
			String cmd = null; String sel = null; String val = null;
			StringTokenizer st = new java.util.StringTokenizer (v, " \t");
			while (st.hasMoreElements()) {
				cmd = (String) st.nextElement();
				cmd = cmd.trim();

				if(cmd == null || cmd.trim().length() < 2 || cmd.trim().indexOf("//") == 0) {
					continue;	//if anything not supported or a comment, ignore it!
				}
				try {
					sel = (String) st.nextElement();
					sel = sel.replaceAll("css=", "");
					sel = sel.trim();
					val = (String) st.nextElement();
					val = val.trim();
				} catch (Exception e) {
					//e.printStackTrace();	//TODO bad we know!
				}
				//=== parse command first
				if(cmd.equals("open")) {
					cmd = cmd.replaceAll("open", "browser.driver.manage().window().setSize(1216, 935); /*required by pjs*/ browser.get('{{}}');");
					if(sel != null) {
						cmd = cmd.replaceAll("\\{\\{\\}\\}", sel);
					}
				} else
				if(cmd.equals("click")) {
					cmd = cmd.replaceAll("click", "element(by.css('{{}}')).click();");
				} else
				if(cmd.equals("waitForElementPresent")) {
					cmd = cmd.replaceAll("waitForElementPresent", "browser.sleep(5000);");
				} else
				if(cmd.equals("assertText")) {
					cmd = cmd.replaceAll("assertText", "it('assert: has text', function () {" + "\n" +
						"expect(element(by.cssContainingText('body', '{{text}}')).getText()).toContain('{{text}}');});" + "\n" +
						"});");
				} else
				if(cmd.equals("type")) {
					cmd = cmd.replaceAll("type", "browser.wait(element(by.css('{{}}')).isPresent(), 32000);element(by.css('{{}}')).sendKeys('{{val}}');");
				} else {
					continue;	//if anything not supported, ignore it!
				}
				
				//=== process command interpolation
				if(sel != null) {
					cmd = cmd.replaceAll("\\{\\{\\}\\}", sel);
				}
				if(val != null) {
					cmd = cmd.replaceAll("\\{\\{text\\}\\}", val);
				}
				
				sb.append(cmd);
			}
		}
		return sb.toString();
	}
	
	public String parse(String seleniumString) {
		StringBuffer sb = new StringBuffer();
		if(!StringUtils.isEmpty(seleniumString)) {
			String t = null; String t1 = null;
			StringTokenizer st = new java.util.StringTokenizer (seleniumString, "\t\n", true);
			while (st.hasMoreElements()) {
				t = (String) st.nextElement();
				if(debug) {
					System.out.print(t);
					System.out.print(" ---> ");
				}
				t1 = toScript(t);
				if(debug) {
					System.out.println(t1);
				}
				if(t1 != null && t1.trim() != "" && t1.trim().length() > 1 && t1.trim().indexOf("//") > 0) {
					sb.append(t1).append("\n\n");
				}
			}

		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ProtractorUtils p = new ProtractorUtils();
		String host = "https://chudoon3t.appspot.com";
		String s = 
//				"open https://chudoon3t.appspot.com/n\n"+
//					"waitForPageToLoad\n"+
//					"\n"+
//					"waitForElementPresent css=input[type=\"text\"]\n"+
//					"click css=input[type=\"text\"]\n"+
//					"waitForPageToLoad				\n";
		"open " + host + "/n" + "\n" +
		"\n" +
		"type css=input[type=\"text\"] test" + "\n" +
		"\n" +
		"type css=input[type=\"password\"] test1234" + "\n" +
		"\n" +
		"click css=input[type=\"submit\"]" + "\n" +
		"\n" +
		"waitForElementPresent css=a.pull-right" + "\n" +
		"assertText css=input[type=\"submit\"] *Login*";
		String finalScript = null;
		finalScript = ProtractorUtils.header + p.parse(s) + ProtractorUtils.footer;
//		System.out.print("finalScript = [");
		System.out.print(finalScript);
//		System.out.println("]");
	}
}
