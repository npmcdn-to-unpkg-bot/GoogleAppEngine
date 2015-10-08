package com.appspot.cloudserviceapi.sci.services;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tapp.model.sci.RepositoryData;

import com.appspot.cloudserviceapi.common.CodesionUtil;
import com.appspot.cloudserviceapi.common.JSLintUtil;
import com.appspot.cloudserviceapi.common.PMDUtil;
import com.appspot.cloudserviceapi.common.SettingsDBUtils;
import com.appspot.cloudserviceapi.sci.dao.RepositoryDataDAO;
import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;
import com.appspot.cloudserviceapi.security.spring.UserSecurityDAO;
import com.appspot.cloudserviceapi.test.CodesionSVNTest;
import com.google.appengine.api.datastore.Text;

import org.apache.commons.lang3.StringEscapeUtils;
//import org.apache.commons.vfs.FileSystemManager;
//import org.apache.commons.vfs.FileObject;
//import org.apache.commons.vfs.RandomAccessContent;
//import org.apache.commons.vfs.util.RandomAccessMode;
import org.apache.tools.ant.util.FileUtils;

//import com.newatlanta.commons.vfs.provider.gae.GaeVFS;

@SuppressWarnings("serial")
public class CodesionSVNServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
//	    GaeVFS.setRootPath(config.getServletContext().getRealPath("/"));
	}
	
	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		response.setContentType("text/plain");
		
		String uri = request.getParameter("uri");
		String kssToken = request.getParameter("token");
		String type = request.getParameter("type");
		String resp = null;
			try {
				CodesionSVNTest t = new CodesionSVNTest();
				UserSecurityDAO gaeu = new UserSecurityDAO();
				List svnUsers = gaeu.getUserByRole("ROLE_ADMIN_CODESION");
				GaeUserDetails u = null;
				if(svnUsers != null && svnUsers.size() > 0 && "pgs100".equals(kssToken)) {
					u = (GaeUserDetails)svnUsers.get(0);
					String userId = u.getUserId();
					String password = u.getPassword();
					System.out.println("codesion admin u'" + userId + "' p'" + password + "'");
					resp = t.fetch(userId, password, SettingsDBUtils.getSettings("main.svn.host"), uri);
					if("html".equalsIgnoreCase(type)) {
						response.setContentType("text/html");
						resp = resp.trim();
					}
					else
					if(uri != null && uri.lastIndexOf(".java") > -1) {
					    try {
//						    FileSystemManager fsManager = GaeVFS.getManager();
//						    FileObject tmpFolder = fsManager.resolveFile("gae://WEB-INF/tmp");
//						    FileObject tempFile = GaeVFS.resolveFile( "gae://WEB-INF/tmp" + uri + ".tmp" );
//						    tempFile.createFile();
					    	String advice = String.valueOf(PMDUtil.runPMD(resp, null));
//							resp = "PMD advice (http://pmd.sourceforge.net/rules) " + advice + "<br><br><![CDATA[" + CodesionUtil.prefixLineNumbers(resp) + "]]>";
							resp = advice + "\n\n" + CodesionUtil.prefixLineNumbers(resp) + "\nAnaylzed by PMD (http://pmd.sourceforge.net/rules)";
					    } finally {
//					        GaeVFS.clearFilesCache(); // this is important!
					    }
					} else 
					if(uri != null && ((uri.lastIndexOf(".htm") > -1) || (uri.lastIndexOf(".css") > -1) || (uri.lastIndexOf(".js")) > -1)) {
				    	//resp = StringEscapeUtils.escapeHtml(resp);
					    String advice = String.valueOf(JSLintUtil.run(resp));
//						resp = "JSLINT advice (http://www.jslint.com/lint.html) " + advice + "<br><br><![CDATA[" + CodesionUtil.prefixLineNumbers(resp) + "]]>";
						resp = "" + advice + "\n\n" + CodesionUtil.prefixLineNumbers(resp) + "\nAnalyzed by JSLINT (http://www.jslint.com/lint.html)";
					}
				} else {
					//do nothing
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		response.getWriter().println(resp);
		System.out.println("Codesion SVN: " + resp);
	}

}