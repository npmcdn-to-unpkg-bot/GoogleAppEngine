<%@ page import="java.lang.*, java.util.*,java.text.*" %>

<%
String teststr = "MyTest";
session.setAttribute( "AppContextKey", teststr  );
String stringObj = "This test my Application Context From the Viewer";
session.setAttribute( "AppContextValue", stringObj );
String reportName = request.getParameter("rpt");
if(reportName != null && !reportName.trim().equals("")) {
//String redirectURL = "/run?__report=report/" + reportName + ".rptdesign";
String redirectURL = "/frameset?__report=report/" + reportName + ".rptdesign";
response.sendRedirect(redirectURL);
//RequestDispatcher view = request.getRequestDispatcher(redirectURL);
//view.forward(request, response);  
} else {
%>
Report name is needed!
<%
}
%>
