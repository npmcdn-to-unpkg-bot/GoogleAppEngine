package app.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public class ParseHelper {

	private static String parseAuthURL = "https://api.parse.com/1/sessions/";

	public static boolean isSessionValid(String parseUserId, String targetSecurityToken) {
		boolean retVal = true;

		try {
			//=== https://cloud.google.com/appengine/docs/java/urlfetch/
		    URL url = new URL(parseAuthURL + parseUserId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestProperty("X-Parse-Application-Id", "Ld70ODLxjXFkhhRux6kQqVCiJ4rQeXU8dISafNJa");
		    conn.setRequestProperty("X-Parse-REST-API-Key", "NErsElPN2oSpmEM0RJD6yTnpCH4FnblRVs4f4DpK");
		    conn.setRequestProperty("X-Parse-Session-Token", targetSecurityToken);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		    String line;

		    while ((line = reader.readLine()) != null) {
		    	if(StringUtils.contains(line, "invalid session token")) {
		    		retVal = false;
		    		break;
		    	}
		    }
		    reader.close();
		} catch(Exception e) {
			retVal = false;
			e.printStackTrace();
		}
		
        return retVal;
	}
}