package app.common;

public class TestScriptHelper {

	public static final String encodeSelector(String v) {
		String ret = v;
		
		ret = v.replaceAll(" > ", ">");
		
		return ret;
	}
	
}
