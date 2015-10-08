package com.appspot.cloudserviceapi.service;

/*
 * https://groups.google.com/forum/#!topic/google-appengine-java/PYpoELG7ZZ4
 */
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class WarmingServlet extends HttpServlet {
	private static Logger log = Logger.getLogger("com.appspot.cloudserviceapi.service");

	@Override
	public void log(String msg) {
		log.finest(msg);
		System.out.println("WarmingServlet:log");
	}

	@Override
	public void init() throws ServletException {
		log("Warming init ");
		System.out.println("WarmingServlet:init");
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		log("Warming service");
		System.out.println("WarmingServlet:service");
	}

}