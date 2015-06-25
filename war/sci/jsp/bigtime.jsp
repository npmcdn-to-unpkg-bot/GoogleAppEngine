<%@ page import="com.google.appengine.api.urlfetch.HTTPHeader" %>
<%@ page import="com.google.appengine.api.urlfetch.HTTPRequest" %>
<%@ page import="com.google.appengine.api.urlfetch.HTTPResponse" %>
<%@ page import="com.google.appengine.api.urlfetch.URLFetchService" %>
<%@ page import="com.google.appengine.api.urlfetch.URLFetchServiceFactory" %>
<%@ page import="java.net.URL" %>

<%
	String urlString = "http://ts.scigrp.com/bigtime/";
	URL url = new URL(urlString);
	HTTPRequest httpRequest = new HTTPRequest(url);
	httpRequest.getFetchOptions().doNotFollowRedirects();
	URLFetchService urlFetchService = URLFetchServiceFactory.getURLFetchService();
	HTTPResponse httpResponse = urlFetchService.fetch(httpRequest);

	for (HTTPHeader header : httpResponse.getHeaders() ) {
	  out.println("<p>" + header.getName() + "<br/>" + header.getValue() + "</p>");
	}
%>