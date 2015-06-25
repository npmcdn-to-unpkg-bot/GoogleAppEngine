<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="/WEB-INF/tld/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%@ page import="passwordchange.core.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
		<!-- Build 1.0 RC7e -->
        <title>1.0 <%=Constants.RESET_TITLE%></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/> <!-- source: https://developer.apple -->
		<div style="display:none;position:absolute;">
 			<a href="#skip">
  			<img src="/passwordchange/images/skipnav.gif" border="0" height="1" width="1" alt="Skip Navigation" title="Skip Navigation" />
	 		</a>
		</div>

        <meta http-equiv="Content-Language" content="en-us">
        <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=WINDOWS-1252">
        <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
        <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <LINK href="/passwordchange/css/passwordchange.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src='//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js'></script>
        <script type="text/javascript" src='../../jquery/purl.js'></script>
        <script type="text/javascript" src='https://www.parsecdn.com/js/parse-1.2.7.min.js'></script>
        <script>var require = { urlArgs: 'a001' };</script>
        <script type="text/javascript" src='../../js/main-config.js'></script>    <!-- load the App in it -->
        <script>
        Parse.initialize("Ld70ODLxjXFkhhRux6kQqVCiJ4rQeXU8dISafNJa", "dLHhDNKnLimXHzSzxvQcGuwle5iLwnn3bFahDS9q");
        window.console && console.log("Parse initialized.");

        function send(email) {
            Parse.User.requestPasswordReset();
            Parse.User.requestPasswordReset(email , {
                    success: function () {
                        alert('Password reset sent to '+ email + '!');
                        delete self;
                    },
                    error: function (error) {
                        // Show the error message somewhere
                        alert('Password reset error: '+ error.message + '');
                    }
            });
        }

        $(function(){
            if(Parse && Parse.User && Parse.User.current()) {
                var email = Parse.User.current().getEmail();
                //alert(email);
                var loginInfo;
                if(email !== undefined) {
                    loginInfo = Parse.User.current().getUsername() + " (" + Parse.User.current().getEmail() + ")";
                    $("#emailRequestNotice1").html("<a href='javascript:void(0);' onclick='send(Parse.User.current().getEmail());'>Email Me the Password Reset!</a>");
                } else {
                    loginInfo = Parse.User.current().getUsername() + " (No email address currently provided)";
                    $("#emailRequestNotice2").html("Note: You can send reset email once you have provided a valid email.");
                }

                if(App.isValidSession()) {
                    var uid  = Parse.User.current().getUsername();
                } else {
                    alert(App.session_expired_msg);
                    location.href = App.login_url;
                }
                <%--if(uid === undefined) {--%>
                    <%--uid = $.url().param('username');	//TODO security risk for non-local account!!!--%>
                <%--}--%>
                var temp1 = '/html/migrate.html?username=' + uid + '&logintype=1';
                var temp = "<a href='" + temp1 + "'>Migrate My Data</a>";
                $("#emailRequestNotice1").html(temp);
                $("#currentEmail").html('You logged in as ' + loginInfo + '<p>');
            }
        });

        </script>
    </head>

	<body>
		<%
//			MainServlet.initProperties();
			String errorMessage = (String)session.getAttribute("ErrorMessage");
		  			if (errorMessage == null)
		  				errorMessage = "";
			session.setAttribute("ErrorMessage", "");
            //begin KISS store username - just for display not for real stuff to avoid security risk!
            String userID4DisplayOnly = "";
            if(request.getParameter("username") != null && !request.getParameter("username").equals("undefined") && !request.getParameter("username").equals("null")) {
                session.setAttribute("username", request.getParameter("username"));
                userID4DisplayOnly = request.getParameter("username");
            } else {
                if(session.getAttribute("uid") != null && !session.getAttribute("uid").equals("undefined") && !session.getAttribute("uid").equals("null")) {
                    userID4DisplayOnly = (String)session.getAttribute("uid");
                }
            }
            //end KISS store username - just for display not for real stuff to avoid security risk!
		%>

	    <table class="secttable"><colgroup></colgroup><tbody class="secttbody" /><tr><td align="center">

		<a name="skip" id="skip"></a>

		<form name="LoginForm" action="/passwordchange/login" method="POST" focus="userid" title="Use this screen to change the password">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value/>"/>

            <%
            	if (errorMessage.equals("")) {
            %>
        		<p class=std><div id="currentEmail"></div>Please choose the following option to manage your account:</p>
            <%
            	} else {
            %>
					<strong class="accessible-error-text" align="center"><%=errorMessage%></strong>
            <%
            	}
            %>

        	<table summary="Login credentials for the Password Change Station.">
<!--        	
            	<tr>
<td><img id="img1" accesskey="C" tabindex="1" onclick="javascript:ForgotPasswordSubmit();" src="/passwordchange/images/change-enroll-glow100x_purple.gif" alt="Setup Security Questions" style="border-width:0px;">&nbsp;&nbsp;</td>
<td style="width:50px;"></td>
<td><img id="img1" accesskey="C" tabindex="2" onclick="javascript:ForgotPasswordSubmit();" src="/passwordchange/images/changepw-100x_purple.gif" alt="Change Password" style="border-width:0px;">&nbsp;&nbsp;</td>
<td style="width:50px;"></td>
<td><img id="img2" accesskey="R" tabindex="3" onclick="javascript:ForgotPasswordSubmit();" src="/passwordchange/images/forgot-100x_purple.gif" alt="Forgot My Password" style="border-width:0px;">&nbsp;&nbsp;</td>
<td style="width:50px;"></td>
<td><img id="img3" accesskey="U" tabindex="4" onclick="javascript:ForgotPasswordSubmit();" src="/passwordchange/images/unlock-100x_purple.gif" alt="Unlock My Account" style="border-width:0px;"></td><p>
            	</tr>
-->
             	<tr>
                    <td>
                        <div style="margin-right: auto; margin-left: 10px;">
    <a href="javascript:void(0);" onclick="location.href='<%=Constants.SIMPLE_CHANGE_PASSWORD_URL%>?username=<%=userID4DisplayOnly%>&logintype=' + $.url().param('logintype');" alt="Change Password">Just Change My Password</a>
                        </div>
                    </td>
            	</tr>
                <tr>
                    <td>
                        <div style="margin-right: auto; margin-left: 10px;">
    <a href="javascript:void(0);" onclick="location.href='<%=Constants.SIMPLE_CHANGE_EMAIL_URL%>?username=<%=userID4DisplayOnly%>&logintype=' + $.url().param('logintype');" alt="Change Email">Update My Email Address</a>
                        </div>
                    </td>
                </tr>
                <tr>

                    <td>
                        <div style="margin-right: auto; margin-left: 10px;" id="emailRequestNotice1"></div>
                    </td>
                </tr>
             	<tr>
            	</tr>
                <tr>
                </tr>
                <tr>
                </tr>
                <tr>
		            <td style="text-align:center;white-space:nowrap;">
                        <a href="javascript:void(0);" onclick="location.href='/mcrud/movie.html?username=' + $.url().param('username') + '&logintype=' + $.url().param('logintype');" alt="Exit">Exit</a>
                    </td>
                </tr>
        	</table>
    	</form>
    	
        	<p>
            <div><span id='emailRequestNotice2'></span></div>
        	<!--
        	Note: Future release might require security questions to be setup in order to reset the password.
			-->
			
    	</td></tr></table>

	</body>

</html>
