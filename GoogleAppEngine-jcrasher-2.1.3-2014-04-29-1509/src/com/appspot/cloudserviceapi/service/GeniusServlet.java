package com.appspot.cloudserviceapi.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.cloudserviceapi.common.CodesionUtil;
//import com.appspot.cloudserviceapi.common.JSLintUtil;
//import com.appspot.cloudserviceapi.common.PMDUtil;
//import com.appspot.cloudserviceapi.test.CodesionSVNTest;
//import com.newatlanta.commons.vfs.provider.gae.GaeVFS;

@SuppressWarnings("serial")
public class GeniusServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
//	    GaeVFS.setRootPath(config.getServletContext().getRealPath("/"));
	}
	
	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		
		String id = request.getParameter("s");
		String resp = null;
		if(id != null) {
			try {
//				GeniusDAO r = new GeniusDAO();
//				Genius u = null;
//				u = (Genius)r.findById(id);
//				if(u != null) {
//					System.out.println("genuis request id '" + id + "' found '" + u.toString() + "'");
//
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			resp = "<html>Any service must be specified with request parameter \"s\". [" + id + "]</html>";
			response.getWriter().println(resp);
		}
	}


}