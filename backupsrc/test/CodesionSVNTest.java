package com.appspot.cloudserviceapi.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.mail.internet.MimeUtility;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.esxx.js.protocol.GAEConnectionManager;

import com.appspot.cloudserviceapi.common.SVNRedirectHandler;
import com.appspot.cloudserviceapi.common.SettingsDBUtils;
import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;
import com.appspot.cloudserviceapi.security.spring.UserSecurityDAO;
//import com.google.appengine.repackaged.com.google.common.util.Base64;
import com.google.gdata.util.common.util.Base64;
	
public class CodesionSVNTest {

	/**
	 * Source: http://stackoverflow.com/questions/1341081/using-http-basic-auth-with-google-app-engine-urlfetch-service
	 */
	@Deprecated
	public static String fetch1(String userId, String password, String svnUrl, String uri) {
		URL url;
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(svnUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			setBasicAuth(connection, userId, password);
			reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;
			sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				//resp.getWriter().print(line);
				sb.append(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Deprecated
    public static byte[] encode(byte[] b) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream b64os = MimeUtility.encode(baos, "base64");
        b64os.write(b);
        b64os.close();
        return baos.toByteArray();
    }
    
    /**
     * Preemptively set the Authorization header to use Basic Auth.
     * @param connection The HTTP connection
     * @param username Username
     * @param password Password
     * @throws Exception 
     */
	@Deprecated
    public static void setBasicAuth(HttpURLConnection connection,
                String username, String password) throws Exception {
        StringBuilder buf = new StringBuilder(username);
        buf.append(':');
        buf.append(password);
        byte[] bytes = null;
        try {
                bytes = buf.toString().getBytes("ISO-8859-1");
        } catch (Exception e) {
        	throw e;
        }

        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-US; rv:1.8.1.14) Gecko/20080404 Firefox/2.0.0.14");
        connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        connection.setRequestProperty("Keep-Alive", "300");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("Accept", "text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        connection.setRequestProperty("Cache-Control", "max-age=0");
        String header = "Basic " + Base64.encode(bytes);
        connection.setRequestProperty("Authorization", header);
    }	
	
	/*
	 * Source: http://esxx.blogspot.com/2009/06/using-apaches-httpclient-on-google-app.html
	 */
	public String fetch(String userId, String password, String svnHost, String uri) throws ClientProtocolException, IOException {
		
		String retVal = null;
        HttpHost targetHost = new HttpHost(svnHost, 443, "https");

        DefaultHttpClient httpclient = new DefaultHttpClient(new GAEConnectionManager());
        RedirectHandler customRedirectHandler = new SVNRedirectHandler();
        httpclient.setRedirectHandler(customRedirectHandler);
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                    new UsernamePasswordCredentials(userId, password));

            // Create AuthCache instance
            AuthCache authCache = new BasicAuthCache();
            // Generate BASIC scheme object and add it to the local
            // auth cache
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(targetHost, basicAuth);

            // Add AuthCache to the execution context
            BasicHttpContext localcontext = new BasicHttpContext();
            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

        	//handle file that has space(s) as part of filename
            uri = uri.replaceAll(" ", "%20");
            uri = uri.replaceAll("%2F", "/");
            HttpGet httpget = new HttpGet(uri);

            System.out.println("executing request: " + httpget.getRequestLine());
            System.out.println("to target: " + targetHost);
            
            //for (int i = 0; i < 3; i++) {
                HttpResponse response = httpclient.execute(targetHost, httpget, localcontext);
            
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("response content length: " + entity.getContentLength());
            		System.out.println("parsing response string ...");
            		retVal = handleResponse(response).toString();
            		System.out.println("done");
               }
                EntityUtils.consume(entity);
            //}

        } finally {
        	/*
        	 * When HttpClient instance is no longer needed, shut down the connection manager to ensure immediate deallocation of all system resources
        	 */
        	httpclient.getConnectionManager().shutdown();
        }

 		return retVal;
	}

	public StringBuffer handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {

		int statusCode = response.getStatusLine().getStatusCode();
		InputStream in = null;
		StringBuffer sb = new StringBuffer();
		
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpResponseException(statusCode, null);
		} else {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				in = entity.getContent();
				// This works
				for (int i;(i = in.read()) >= 0;) {
					//System.out.print((char)i);
					sb.append((char)i);
				}
			}
		}
		
		return sb;
	}
	
	public static void main(String[] args) {
		String resp = null;
		try {
			CodesionSVNTest t = new CodesionSVNTest();
			UserSecurityDAO gaeu = new UserSecurityDAO();
			List svnUsers = gaeu.getUserByRole("ROLE_ADMIN_CODESION");
			GaeUserDetails u = null;
			if(svnUsers == null && svnUsers.size() > 0) {
				u = (GaeUserDetails)svnUsers.get(0);
				String userId = u.getUserId();
				String password = u.getPassword();
				System.out.println("codesion admin u'" + userId + "' p'" + password + "'");
				resp = t.fetch(userId, password, SettingsDBUtils.getSettings("main.svn.host"), "/"+SettingsDBUtils.getSettings("uri1.project")+"/DOCS/readme.txt");
			} else {
				//do nothing
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Codesion SVN: " + resp);
	}

}
