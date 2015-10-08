package com.appspot.cloudserviceapi.sci.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.cloudserviceapi.common.BigTimeUtil;
import com.appspot.cloudserviceapi.common.SettingsDBUtils;
import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;
import com.appspot.cloudserviceapi.security.spring.UserSecurityDAO;
import com.appspot.cloudserviceapi.test.BigTimeTest;
//import com.newatlanta.commons.vfs.provider.gae.GaeVFS;

@SuppressWarnings("serial")
public class BigTimeServlet extends HttpServlet {

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
		
		String uri = request.getParameter("uri");
		String kssToken = request.getParameter("token");
		String fd = request.getParameter("fd"); //"01/01/2011";
		String td = request.getParameter("td"); //"12/31/2011";
		String resp = null;
			try {
				BigTimeTest t = new BigTimeTest();
				UserSecurityDAO gaeu = new UserSecurityDAO();
				List svnUsers = gaeu.getUserByRole("ROLE_ADMIN_CODESION");
				GaeUserDetails u = null;
				if(svnUsers != null && svnUsers.size() > 0 && "bt100".equals(kssToken)) {
					u = (GaeUserDetails)svnUsers.get(0);
					String userId = u.getUserId();
					String password = u.getPassword();
					System.out.println("bigtime admin u'" + userId + "' p'" + password + "'");
					t.setFromDate(fd);
					t.setToDate(td);
					resp = t.fetch(userId, password, SettingsDBUtils.getSettings("main.bigtime.host"), uri);
					resp = BigTimeUtil.grabHTMLHoursBody(resp);
					resp = BigTimeUtil.removeRedundantHTML(resp);
				} else {
					//do nothing
					System.out.println("no valid bigtime user/role found, was it setup?");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		response.getWriter().println("<html>" + resp + "</html>");
		System.out.println("R&D Team Hours: " + BigTimeUtil.getTotalHours("<td class=\"cList-overall\" valign=\"top\" align=\"right\" NOWRAP ><p class=\"list-item\"><span class=\"clist-head1\">", "</span></p></td>", resp));
	}

}