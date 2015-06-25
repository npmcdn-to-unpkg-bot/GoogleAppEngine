<%@ page isELIgnored ="false" %>
<%@ taglib uri="/WEB-INF/tld/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="passwordchange.core.Constants" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><%=Constants.SETUP_TITLE %> - questions saved</title>
        <html:base />
        <meta http-equiv="Content-Language" content="en-us">
        <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=WINDOWS-1252">
        <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
        <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <LINK href="/passwordchange/css/passwordchange.css" rel="stylesheet" type="text/css">
       
    </head>

	<body>

	    <table class="secttable"><colgroup></colgroup><tbody class="secttbody" /><tr><td align="center">

		<table><tr><td align=\"center\"><p class=\"ttl18\"><h3><%=Constants.SETUP_TITLE %></h3></p></td></tr></table>

		<center> Questions and answers are saved successfully. </center>
 
        <p><a href="<%=Constants.LANDING_URL%>">Back to password change logon</a>

	    </td></tr></table>

	</body>

</html>
