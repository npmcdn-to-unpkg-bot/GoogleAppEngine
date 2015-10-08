package com.appspot.cloudserviceapi.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
//import org.esxx.js.protocol.GAEConnectionManager;
//import org.hibernate.jdbc.ConnectionManager;

import teamthree.tvnow.apache.GaeHttpClientFactory;

import com.appspot.cloudserviceapi.common.SVNRedirectHandler;
import com.appspot.cloudserviceapi.common.SettingsDBUtils;
import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;
import com.appspot.cloudserviceapi.security.spring.UserSecurityDAO;
//import com.google.appengine.repackaged.com.google.common.util.Base64;
import com.google.gdata.util.common.util.Base64;


public class BigTimeTest {
	
	private String fromDate;
	private String toDate;
	
    public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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
	
	public ThreadSafeClientConnManager getCM() {
//		GAEConnectionManager cm = new GAEConnectionManager();
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(
		         new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(
		         new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

		ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);

		return cm;
	}
	
	/*
	 * Source: http://esxx.blogspot.com/2009/06/using-apaches-httpclient-on-google-app.html
	 */
	public String fetch(String userId, String password, String svnHost, String uri) throws ClientProtocolException, IOException {
		String retVal = null;
        String encoding = HTTP.UTF_8;

        DefaultHttpClient httpclient = new DefaultHttpClient(getCM());
//        RedirectHandler customRedirectHandler = new SVNRedirectHandler();
//        httpclient.setRedirectHandler(customRedirectHandler);
        HttpPost httpPost = null;
        
        try {
			httpPost = new HttpPost(new URI("http://" + svnHost + "/BigTime/EAPSA.ASP"));
//			httpPost = new HttpPost(new URI(host1));
					
            httpPost.setHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
            httpPost.setHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.3");
            httpPost.setHeader("Accept-Encoding","gzip,deflate");
            httpPost.setHeader("Accept-Language","en-us,en;q=0.8");
            httpPost.setHeader("Cache-Control","max-age=0");
            httpPost.setHeader("Connection","keep-alive");
            //httpPost.setHeader("Content-Length","50");
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Host",SettingsDBUtils.getSettings("main.bigtime.host"));
            httpPost.setHeader("Origin","http://"+SettingsDBUtils.getSettings("main.bigtime.host"));
            httpPost.setHeader("Referer","http://"+ SettingsDBUtils.getSettings("main.bigtime.host") + "/BigTime/");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.127 Safari/534.16");
            httpclient.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);

            List nvps = new ArrayList ();
			nvps.add(new BasicNameValuePair("fldUNM", userId));
			nvps.add(new BasicNameValuePair("fldPWD", password));
			nvps.add(new BasicNameValuePair("cmdGO.x", "18"));
			nvps.add(new BasicNameValuePair("cmdGO.y", "14"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

			HttpResponse response = httpclient.execute(httpPost);
//			System.out.println("0.93 logged in = '" + response + "'");

			//scott's report
//			uri = "/BigTime/EAPSA_MGMT.ASP?WCI=eaMain&WCE=tplBasic&HTML=ReportCustom6.htm&ObjectType=EAReportCustom&ItemID=1009&PkgRptSID=80&PRJ_SID=";
//			//for (int i = 0; i < 3; i++) {
//			String tempHost = "http://" + svnHost + uri;

uri = "/BigTime/EAPSA_MGMT.ASP?WCI=eaMAIN&WCE=tplTableDef&ObjectType=EAReportPkg&ItemID=7&HTML=Report_PkgList.htm";
HttpGet httpget = new HttpGet("http://" + svnHost + uri);
httpget.setHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
httpget.setHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.3");
httpget.setHeader("Accept-Encoding","gzip,deflate");
httpget.setHeader("Accept-Language","en-us,en;q=0.8");
httpget.setHeader("Cache-Control","max-age=0");
httpget.setHeader("Connection","keep-alive");
//httpget.setHeader("Content-Length","50");
httpget.setHeader("Content-Type", "application/x-www-form-urlencoded");
httpget.setHeader("Host",SettingsDBUtils.getSettings("main.bigtime.host"));
httpget.setHeader("Origin","http://"+SettingsDBUtils.getSettings("main.bigtime.host"));
httpget.setHeader("Referer","http://"+ SettingsDBUtils.getSettings("main.bigtime.host") + "/BigTime/");
httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.127 Safari/534.16");
//System.out.println("executing GET request: " + httpget.getRequestLine());
response = httpclient.execute(httpget);

HttpEntity entity = response.getEntity();
//System.out.println("----------------------------------------");
//System.out.println(response.getStatusLine());
//if (entity != null) {
//    System.out.println("response content length: " + entity.getContentLength());
//	System.out.println("parsing response string ...");
//	retVal = handleResponse(response).toString();
//	System.out.println("done = " + retVal);
//}
EntityUtils.consume(entity);

                
uri = "/BigTime/EAPSA_MGMT.ASP?WCI=eaMain&WCE=tplBasic&HTML=ReportCustom6.htm&ObjectType=EAReportCustom&ItemID=1009&PkgRptSID=80&PRJ_SID=";
//for (int i = 0; i < 3; i++) {
String tempHost = "http://" + svnHost + uri;
//System.out.println("1.2 tempHost = '" + tempHost + "'");
httpPost = new HttpPost(new URI(tempHost));
	httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
	httpPost.setHeader("Referer", "http://"+SettingsDBUtils.getSettings("main.bigtime.host"));
	nvps = new ArrayList ();
//nvps.add(new BasicNameValuePair("WCI", "eaMain"));
//nvps.add(new BasicNameValuePair("WCE", "tplBasic"));
//nvps.add(new BasicNameValuePair("HTML", "ReportCustom6.htm"));
//nvps.add(new BasicNameValuePair("ObjectType", "EAReportCustom"));
//nvps.add(new BasicNameValuePair("ItemID", "1009"));
//nvps.add(new BasicNameValuePair("PkgRptSID", "80"));
//nvps.add(new BasicNameValuePair("PRJ_SID", ""));

	nvps.add(new BasicNameValuePair("fldDt_st", fromDate));
	nvps.add(new BasicNameValuePair("fldDt_end", toDate));
	nvps.add(new BasicNameValuePair("cboDt", "0"));
	nvps.add(new BasicNameValuePair("fldPrjSID", "314"));
	//nvps.add(new BasicNameValuePair("LK_SHOW_fldPrjSID", "??? Internal:R&D"));
	nvps.add(new BasicNameValuePair("LK_SHOW_fldPrjSID", "Internal:R&D"));
	nvps.add(new BasicNameValuePair("PRJSEEKVAL", ""));
	nvps.add(new BasicNameValuePair("IsEmptyRequest", "1"));
	nvps.add(new BasicNameValuePair("PkgSID", "0"));
	nvps.add(new BasicNameValuePair("PkgSID2", "0"));
	nvps.add(new BasicNameValuePair("DELETEPKG", "0"));
	httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
	response = httpclient.execute(httpPost);

//System.out.println("2 Present cookies: ");
////Get all the cookies
//List<Cookie> cookies = httpclient.getCookieStore().getCookies(); 
//if (cookies.isEmpty()) { 
//	System.out.println("None"); 
//} else { 
//	for (int i = 0; i < cookies.size(); i++) { 
//	System.out.println("- " + cookies.get(i).toString()); 
//	} 
//}
	
	
    entity = response.getEntity();
//    System.out.println("----------------------------------------");
//    System.out.println(response.getStatusLine());
    if (entity != null) {
//        System.out.println("response content length: " + entity.getContentLength());
//		System.out.println("parsing response string ...");
		retVal = handleResponse(response).toString();
//		System.out.println("done");
   }
    EntityUtils.consume(entity);

    
		} catch (Exception e) {
			System.out.println("BigTimeTest:fetch error: " + e);
			//e.printStackTrace();
        } finally {
        	/*
        	 * When HttpClient instance is no longer needed, shut down the connection manager to ensure immediate deallocation of all system resources
        	 */
        	httpclient.getConnectionManager().shutdown();
        }

 		return retVal;
	}
	
	public String fetchOld(String userId, String password, String svnHost, String uri) throws ClientProtocolException, IOException {
		
		String retVal = null;
        HttpHost targetHost = new HttpHost(svnHost, 80, "http");

        DefaultHttpClient httpclient = new DefaultHttpClient(getCM());
//        RedirectHandler customRedirectHandler = new SVNRedirectHandler();
//        httpclient.setRedirectHandler(customRedirectHandler);
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

HttpPost httpPost = null;
httpPost = new HttpPost(new URI("http://" + svnHost + "/EAPSA.ASP"));
HttpResponse response = httpclient.execute(httpPost,localcontext);
            
            
uri = "/EAPSA_MGMT.ASP?WCI=eaMain&WCE=tplTableDef&HTML=Daily_TimeSheetList.htm&Page=1&ObjectType=EAStaff&ItemID=13&TableView=2";
            
        	//handle file that has space(s) as part of filename
            uri = uri.replaceAll(" ", "%20");
            uri = uri.replaceAll("%2F", "/");
            HttpGet httpget = new HttpGet(uri);

            System.out.println("executing GET request: " + httpget.getRequestLine());
            System.out.println("to target: " + targetHost);
            
            //for (int i = 0; i < 3; i++) {
                response = httpclient.execute(targetHost, httpget, localcontext);
            
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

        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	/*
        	 * When HttpClient instance is no longer needed, shut down the connection manager to ensure immediate deallocation of all system resources
        	 */
        	httpclient.getConnectionManager().shutdown();
        }

 		return retVal;
	}
	
	public String fetchr(String userId, String password, String svnHost, String uri) throws ClientProtocolException, IOException {
		String retVal = null;
        String encoding = HTTP.UTF_8;

        DefaultHttpClient httpclient = new DefaultHttpClient(getCM());
//        RedirectHandler customRedirectHandler = new SVNRedirectHandler();
//        httpclient.setRedirectHandler(customRedirectHandler);
        HttpPost httpPost = null;
        
        try {
			httpPost = new HttpPost(new URI("http://" + svnHost + "/EAPSA.ASP"));
//			httpPost = new HttpPost(new URI(host1));
					
            httpPost.setHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
            httpPost.setHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.3");
            httpPost.setHeader("Accept-Encoding","gzip,deflate");
            httpPost.setHeader("Accept-Language","en-us,en;q=0.8");
            httpPost.setHeader("Cache-Control","max-age=0");
            httpPost.setHeader("Connection","keep-alive");
            //httpPost.setHeader("Content-Length","50");
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Host",SettingsDBUtils.getSettings("main.bigtime.host"));
            httpPost.setHeader("Origin","http://"+ SettingsDBUtils.getSettings("main.bigtime.host") + "");
            httpPost.setHeader("Referer","http://"+ SettingsDBUtils.getSettings("main.bigtime.host") + "/BigTime/");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.127 Safari/534.16");
            httpclient.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);

            List nvps = new ArrayList ();
			nvps.add(new BasicNameValuePair("fldUNM", userId));
			nvps.add(new BasicNameValuePair("fldPWD", password));
			nvps.add(new BasicNameValuePair("cmdGO.x", "18"));
			nvps.add(new BasicNameValuePair("cmdGO.y", "14"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

			HttpResponse response = httpclient.execute(httpPost);
//			System.out.println("0.93 logged in = '" + response + "'");

			//scott's report
//			uri = "/BigTime/EAPSA_MGMT.ASP?WCI=eaMain&WCE=tplBasic&HTML=ReportCustom6.htm&ObjectType=EAReportCustom&ItemID=1009&PkgRptSID=80&PRJ_SID=";
//			//for (int i = 0; i < 3; i++) {
//			String tempHost = "http://" + svnHost + uri;

uri = "/EAPSA_MGMT.ASP?WCI=eaMain&WCE=tplTableDef&HTML=Daily_TimeSheetList.htm&Page=1&ObjectType=EAStaff&ItemID=13&TableView=2";
HttpGet httpget = new HttpGet("http://" + svnHost + uri);
httpget.setHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
httpget.setHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.3");
httpget.setHeader("Accept-Encoding","gzip,deflate");
httpget.setHeader("Accept-Language","en-us,en;q=0.8");
httpget.setHeader("Cache-Control","max-age=0");
httpget.setHeader("Connection","keep-alive");
//httpget.setHeader("Content-Length","50");
httpget.setHeader("Content-Type", "application/x-www-form-urlencoded");
httpget.setHeader("Host",SettingsDBUtils.getSettings("main.bigtime.host"));
httpget.setHeader("Origin","http://"+SettingsDBUtils.getSettings("main.bigtime.host"));
httpget.setHeader("Referer","http://"+ SettingsDBUtils.getSettings("main.bigtime.host") + "/BigTime/");
httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.127 Safari/534.16");
//System.out.println("executing GET request: " + httpget.getRequestLine());
response = httpclient.execute(httpget);

HttpEntity entity = response.getEntity();
//System.out.println("----------------------------------------");
//System.out.println(response.getStatusLine());
//if (entity != null) {
//    System.out.println("response content length: " + entity.getContentLength());
//	System.out.println("parsing response string ...");
	retVal = handleResponse(response).toString();
//	System.out.println("done = " + retVal);
//}
EntityUtils.consume(entity);

    
		} catch (Exception e) {
			System.out.println("BigTimeTest:fetch error: " + e);
			//e.printStackTrace();
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

	public StringBuffer handleEntity(HttpEntity entity)
	throws ClientProtocolException, IOException {

		StringBuffer sb = new StringBuffer();
		InputStream in = null;
			
		if (entity != null) {
			in = entity.getContent();
			// This works
			for (int i;(i = in.read()) >= 0;) {
				//System.out.print((char)i);
				sb.append((char)i);
			}
		}
		
		return sb;
	}
	
	public StringBuffer handleHeaders(Header[] headers)
	throws ClientProtocolException, IOException {

		StringBuffer sb = new StringBuffer();
		if (headers != null) {
			for (int i=0;i < headers.length;i++) {
				sb.append(headers[i]);
			}
		}
		
		return sb;
	}

	public static void main(String[] args) {
		String resp = null;
		try {
			BigTimeTest t = new BigTimeTest();
			UserSecurityDAO gaeu = new UserSecurityDAO();
			List svnUsers = gaeu.getUserByRole("ROLE_ADMIN_CODESION");
			GaeUserDetails u = null;
			if(svnUsers == null && svnUsers.size() > 0) {
				u = (GaeUserDetails)svnUsers.get(0);
				String userId = u.getUserId();
				String password = u.getPassword();
//				System.out.println("bigtime admin u'" + userId + "' p'" + password + "'");
				String fd = "01/01/2011";
				String td = "12/31/2011";
				t.setFromDate(fd);
				t.setToDate(td);
				resp = t.fetch(userId, password, SettingsDBUtils.getSettings("main.bigtime.host"), "/BigTime/EAPSA_MGMT.ASP%3FWCI%3DeaMain%26WCE%3DtplBasic%26HTML%3DReportCustom6.htm%26ObjectType%3DEAReportCustom%26ItemID%3D1009%26PkgRptSID%3D80%26PRJ_SID%3D");
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
		System.out.println(resp);
	}

}
