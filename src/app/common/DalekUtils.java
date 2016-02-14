package app.common;

import java.util.StringTokenizer;

import org.datanucleus.util.StringUtils;

public class DalekUtils {

	public static String header = "var u = require('./l.js');"+
"var multipleBackspaces = '\uE023' + (new Array(500).join('\uE003'));"+
"module.exports = {"+
"        'DalekJS Hi Res Test for cdecurate': function (test) {";

	public static String footer = ".done();"+
"        }"+
"};";

	public String toScript(String v) {
		StringBuffer sb = new StringBuffer();
		if(!StringUtils.isEmpty(v)) {
			String cmd = null; String sel = null; String val = null;
			StringTokenizer st = new java.util.StringTokenizer (v, " ");
			while (st.hasMoreElements()) {
				cmd = (String) st.nextElement();
				try {
					sel = (String) st.nextElement();
					sel = sel.replaceAll("css=", "");
					val = (String) st.nextElement();
				} catch (Exception e) {
					//e.printStackTrace();	//TODO bad we know!
				}
				//=== parse command first
				if(cmd.equals("open")) {
					cmd = cmd.replaceAll("open", ";test.open('{{}}').resize({width: 1216, height: 935})");
					cmd = cmd.replaceAll("\\{\\{\\}\\}", sel);
				} else
				if(cmd.equals("click")) {
					cmd = cmd.replaceAll("click", ".click('{{}}')");
				} else
				if(cmd.equals("waitForElementPresent")) {
					cmd = cmd.replaceAll("waitForElementPresent", ".wait(5000)");
				} else
				if(cmd.equals("assertText")) {
					cmd = cmd.replaceAll("assertText", ".assert.attr('{{}}', 'value', '{{text}}')");
				} else
				if(cmd.equals("type")) {
					cmd = cmd.replaceAll("type", ".waitForElement('{{}}', 32000).type('{{}}', '{{text}}')");
				}
				//=== process command interpolation
				cmd = cmd.replaceAll("\\{\\{\\}\\}", sel);
				cmd = cmd.replaceAll("\\{\\{text\\}\\}", val);
				
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
//				System.out.print(t);
//				System.out.print(" ---> ");
				t1 = toScript(t);
//				System.out.println(t1);
				sb.append(t1).append("\n\n");
			}

		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		DalekUtils d = new DalekUtils();
		String host = "https://chudoon3t.appspot.com";
		String s = "open " + host + "/n" + "\n" +
		"type css=input[type=\"text\"] test" + "\n" +
		"type css=input[type=\"password\"] test1234" + "\n" +
		"click css=input[type=\"submit\"]" + "\n" +
		"waitForElementPresent css=a.pull-right" + "\n" +
		"assertText css=input[type=\"submit\"] *Login*";
		String finalScript = null;
		finalScript = DalekUtils.header + d.parse(s) + DalekUtils.footer;
//		System.out.print("finalScript = [");
		System.out.print(finalScript);
//		System.out.println("]");
	}
}
