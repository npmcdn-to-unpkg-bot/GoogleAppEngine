<%@ page isELIgnored ="false" %>
<%@ taglib uri="/WEB-INF/tld/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="passwordchange.core.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><%=CommonUtil.getPageHeader((String)request.getSession().getAttribute("action"))%> - password changed</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/> <!-- source: https://developer.apple -->
        <meta http-equiv="Content-Language" content="en-us">
        <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=WINDOWS-1252">
        <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
        <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <LINK href="/passwordchange/css/passwordchange.css" rel="stylesheet" type="text/css">
    </head>

	<body>

	    <table class="secttable"><colgroup></colgroup><tbody class="secttbody" /><tr><td align="center">

		<table><tr><td align=\"center\"><p class=\"ttl18\"><h3><%=CommonUtil.getPageHeader((String)request.getSession().getAttribute("action"))%></h3></p></td></tr></table>

		<center> Password change successful. You can login using the new password right away. </center>
<p>
<form name="form-1" id="form-1" action="<%=Constants.SIMPLE_LANDING_URL%>" method="post">
			<button id="field-2" name="field-2" value="field-2-value" type="submit">Done</button>
</form>

	    </td></tr></table>

	</body>

</html>
