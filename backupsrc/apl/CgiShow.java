package edu.jhu.apl;

import java.util.ArrayList;
import java.util.List;

import com.appspot.cloudserviceapi.common.BigTimeUtil;
import com.appspot.cloudserviceapi.common.SettingsDBUtils;
//import com.appspot.cloudserviceapi.common.FileHelper;
import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;
import com.appspot.cloudserviceapi.test.BigTimeTest;

// This appears in Core Web Programming from
// Prentice Hall Publishers, and may be freely used
// or adapted. 1997 Marty Hall, hall@apl.jhu.edu.

public class CgiShow {
  public static void main(String[] args) {
    CgiShow app = new CgiShow("CgiShow", args, "TEST");
    app.printFile();
  }

  protected String name;
  protected String[] args;
  protected String type;

  public CgiShow(String name, String[] args,
		 String type) {
    this.name = name;
    this.args = args;
    this.type = type;
  }
  
  public void printFile() {
    printHeader();
    printBody(args);
    printTrailer();
  }

  protected void printHeader() {
    System.out.println
      ("Content-Type: text/html\n" +
       "\n" +
       "<!DOCTYPE HTML PUBLIC " +
         "\"-//W3C//DTD HTML 3.2//EN\">\n" +
       "<HTML>\n" +
       "<HEAD>\n" +
       "<TITLE>The " + name + " Program</TITLE>\n" +
       "<STYLE>\n" +
       "<!--");
    printStyleRules();
    System.out.println
      ("-->\n" +
       "</STYLE>\n" +
       "</HEAD>\n" +
       "\n" +
       "<BODY>\n" +
       "<H1>The <CODE>" + name + "</CODE> Program" +
       "</H1>");
  }

  protected void printStyleRules() {
    System.out.println
      ("H1 { text-align: center;\n" +
       "     font-family: Arial, sans-serif }");
  }
  
  protected void printBody(String[] data) {
    System.out.println("(Generic CgiShow)");
  }

  protected void printTrailer() {
    System.out.println("</BODY>\n</HTML>");
  }
  
  protected void start(String fd, String td) {
		String resp = null;
		try {
			BigTimeTest t = new BigTimeTest();
//			UserSecurityDAO gaeu = new UserSecurityDAO();
//			List svnUsers = gaeu.getUserByRole("ROLE_ADMIN_CODESION");
//			GaeUserDetails u = null;
//			if(svnUsers == null && svnUsers.size() > 0) {
//				u = (GaeUserDetails)svnUsers.get(0);
//				String userId = u.getUserId();
//				String password = u.getPassword();
				String userId = "jtan";
				String password = "changeme";
//				System.out.println("bigtime admin u'" + userId + "' p'" + password + "'");
//				String fd = "01/01/2011";
//				String td = "12/31/2011";
				t.setFromDate(fd);
				t.setToDate(td);
				resp = t.fetch(userId, password, SettingsDBUtils.getSettings("main.bigtime.host"), "/BigTime/EAPSA_MGMT.ASP%3FWCI%3DeaMain%26WCE%3DtplBasic%26HTML%3DReportCustom6.htm%26ObjectType%3DEAReportCustom%26ItemID%3D1009%26PkgRptSID%3D80%26PRJ_SID%3D");
				resp = BigTimeUtil.grabHTMLHoursBody(resp);
				resp = BigTimeUtil.removeRedundantHTML(resp);
//				BigTimeUtil.store(resp, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Float tHours = BigTimeUtil.getTotalHours("Overall Totals:", "</span></p></td>", resp);
		System.out.println("R&D Hours: " + tHours);
		System.out.println(resp);
	}

  protected void startr(String fd, String td) {
		String resp = null;
		try {
			BigTimeTest t = new BigTimeTest();
				String userId = "jtan";
				String password = "changeme";
				fd = "12/01/2010";
				td = "12/31/2012";
				t.setFromDate(fd);
				t.setToDate(td);
				resp = t.fetchr(userId, password, SettingsDBUtils.getSettings("backup1.bigtime.host"), "/EAPSA_MGMT.ASP?WCI=eaMain&WCE=tplTableDef&HTML=Daily_TimeSheetList.htm&Page=1&ObjectType=EAStaff&ItemID=13&TableView=2");
				resp = BigTimeUtil.removeRedundantHTMLr(resp);
//				BigTimeUtil.store(resp, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(resp);
	}
  
}
