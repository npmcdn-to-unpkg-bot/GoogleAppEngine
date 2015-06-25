package com.appspot.cloudserviceapi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class provides implementation for Google App Engine friendly to connect
 * to other services on the cloud.
 * 
 * Reference: http://code.google.com/appengine/docs/java/urlfetch/overview.html
 * 
 */
public class URLServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		StringBuffer sb = new StringBuffer();
		StringBuffer type = new StringBuffer();
		try {
			String target = req.getParameter("target");
			String value = req.getParameter("value");
			URL url = new URL(handleRealService(target, value, type));
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;

			while ((line = reader.readLine()) != null) {
//				resp.getWriter().print(line);
				sb.append(line);
			}
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//resp.setContentType("text/plain");	//cause widget in Iris 1.0 to fail with "Getting HTTP Post response failed."
		String temp = handleText(type.toString(), sb.toString());
		int size = temp.length();
		System.out.println("URL Servlet content size '" + size + "'");
		resp.setContentLength(size);
		try {
			resp.getWriter().print(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}
	
	private String handleText(String type, String text) {
		StringBuffer retVal = new StringBuffer("");
		if("traffic".equals(type)) {
			StringBuffer temp = new StringBuffer(grabText(text, "<Title>", "</Title>"));
			temp.insert(30, '\n');
			retVal.append(temp);
		} else
		if("weather".equals(type)) {
				retVal.append("City: " + grabText(text, "<city data=\"", "\"/><postal_code data")).append("\n");
				retVal.append("Temperature: " + grabText(text, "<temp_f data=\"", "\"/><temp_c data")).append("F \n");
				retVal.append("Weather: " + grabText(text, "<condition data=\"", "\"/><temp_f data")).append("\n");
				retVal.append("Day of Week: " + grabText(text, "<day_of_week data=\"", "\"/><low data=")).append("\n");
				retVal.append("Low: " + grabText(text, "<low data=\"", "\"/><high data")).append("F \n");
				String temp = grabText(text, "\"/><high data", "xml_api_reply>");
				retVal.append("High: " + grabText(temp, "=\"", "\"/><icon") + "F");
		} else {
			retVal.append(text);
		}
		
		return retVal.toString();
	}
	
	private String grabText(String text, String begin, String end) {
		String retVal = text;
		
		if(text != null) {
			int b = text.indexOf(begin)+begin.length();
			int e = text.indexOf(end);
			
			try {
				retVal = text.substring(b, e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		return retVal;
	}
	
	private String handleRealService(String target, String value, StringBuffer type) {
		String retVal = target;
		
		if("traffic".equals(target)) {
			type.append("traffic");
			retVal = "http://local.yahooapis.com/MapsService/V1/trafficData?appid=KA27.oTV34EWikLcN2UvFhjoMRzAnvAMqOv4omWkUXZCANI3XK66lXSiB862kbdZzOc-&zip=" + value;
		} else if("weather".equals(target)) {
			type.append("weather");
			retVal = "http://www.google.com/ig/api?weather=" + value;
		} else if("web".equals(target)) {
			retVal = value;
		}
		
		return retVal;
	}
}
