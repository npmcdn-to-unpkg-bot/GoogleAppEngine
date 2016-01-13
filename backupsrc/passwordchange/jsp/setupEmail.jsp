<%@ page isELIgnored ="false" %>
<%@ taglib uri="/WEB-INF/tld/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="passwordchange.core.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><%=Constants.CHANGE_EMAIL_TITLE %></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/> <!-- source: https://developer.apple -->
		<div style="position:absolute;">
 			<a href="#skip">
  			<img src="/passwordchange/images/skipnav.gif" border="0" height="1" width="1" alt="Skip Navigation" title="Skip Navigation" />
	 		</a>
		</div>

        <meta http-equiv="Content-Language" content="en-us">
        <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
        <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
        <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <LINK href="/passwordchange/css/passwordchange.css" rel="stylesheet" type="text/css">

		<script language="JavaScript" type="text/JavaScript">
		function callLogout(){
		     document.LogoutForm.submit();
		}
		</script>

    </head>

	<body>

		<%
            // this shouldn't happen, make the user start over
//			if (session.getAttribute("username") == null) {
//                response.sendRedirect(Constants.LOGGEDOUT_URL);
//                return;
//            }
			String errorMessage = (String)session.getAttribute("ErrorMessage");
		  			if (errorMessage == null)
		  				errorMessage = "";
			session.setAttribute("ErrorMessage", "");

			String userMessage = (String)session.getAttribute("UserMessage");
		  			if (userMessage == null)
		  				userMessage = "";
			session.setAttribute("UserMessage", "");
		%>

		<form name="LogoutForm" method="post" action="../../passwordchange/logout"></form>

    	<table class="secttable"><colgroup></colgroup><tbody class="secttbody" /><tr><td>

		<table><tr><td align="center\"><p class="ttl18\"><h3  style="margin-left:300px;"><%= Constants.CHANGE_EMAIL_TITLE %></h3><p></td></tr></table>

		<a name="skip" id="skip"></a>

<h3>Email Setup:</h3>
    <table>
        <td class=face style="WIDTH: 617px;" colspan=2>
        <ul>Your email address will be used to reset your password. It will not be used for any other purpose other than the password reset.</ul>
    </td>
    </table>

		<form name="PasswordChangeForm" action="../../passwordchange/changeEmail" method="POST" focus="userid" title="Use this screen to change your email address">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value/>"/>
            <%
            	if (errorMessage.equals("")) {
                    if (userMessage.equals("")) {
            %>
        				<p class=std>Use this screen to add/change your email address.</p>
        			<%
        				} else {
        			%>
        				<p class=std><%=userMessage%></p>
        			<%
        				}
        			%>
            <%
            	} else {
            %>
                    <strong class="accessible-error-text" align="center"><%=errorMessage%></strong>
    <%
            	}
            %>
        	<table summary="Login credentials and new email address to change password.">
            <tr>
            <td valign="middle"><label for="LoginID" class=bstd>Login ID:</label>
            <p></td>
            <td valign="middle"><input id="LoginID" type="text"
            name="userid" value="<%= request.getParameter("username") %>" style="width: 3.75in"
            class="std"></td>
            </tr>
    <tr>
    <td valign="middle"><label for="OldPassword" class=bstd>Current Password:</label>
    <p></td>
    <td valign="middle"><input id="OldPassword" type="password"
    name="pswd" value="" style="width: 3.75in" class="std"
    autocomplete="off"></td>
    </tr><tr>
            <tr>
                <td valign="middle"><label for="NewEmail" class=bstd>Email Address:</label></td><p>
                <td valign="middle"><input id="NewEmail" type="email" name="newemail1" value="" style="width: 3.75in" class="std" autocomplete="off"></td>
            </tr><tr>
            <tr>
                <td valign="middle"><label for="NewEmailRepeat" class=bstd>Email Address (repeated):</label></td><p>
                <td valign="middle"><input id="NewEmailRepeat" type="email" name="newemail2" value="" style="width: 3.75in" class="std" autocomplete="off"></td>
            </tr><tr>
                <td colspan="2" valign="middle"><p class="bstd" style="text-align: center; margin-top: 8pt; margin-bottom: 8pt" id="msg">Please provide a valid email address that we can reach you (repeated to avoid typos).</p></td>
            </tr><tr>
                <td valign="bottom">
                <input type="submit" name="changeEmail" value="Change" style="text-align: center" class="but2">
                <input type="submit" name="cancel" value="Cancel" style="text-align: center" class="but2">
                </td>
            </tr><tr>
            <!--
                <td colspan="2" valign="middle"><%=Constants.PWD_RESTRICTIONS%></td>
            -->
            </tr>
        	</table>
    	</form>
            
    	</td></tr></table>

	</body>

</html>
