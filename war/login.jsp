<%--<%@ page session="true"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.appspot.cloudserviceapi.common.Constants" %>

<html>
<head>
    <title>C7i100k</title>
    <meta name="viewport" content="initial-scale=2.3, user-scalable=no"> <!-- source: https://developer.apple.com/library/ios/DOCUMENTATION/AppleApplications/Reference/SafariWebContent/UsingtheViewport/UsingtheViewport.html -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<style>
        body {
            margin-left: 10px;
        }
        h2 {
            margin-top: 0px;
            margin-left: 5px;
        }

        /* iPhone 2G-4S in portrait & landscape (source: http://stephen.io/mediaqueries/) */
        @media only screen
        and (min-device-width : 320px)
        and (max-device-width : 480px) {
            body {
                -webkit-text-size-adjust:80%;
                margin-left: 15px;
            }
            h2 {
                font-size: .9em;
                margin-top: 10px;
            }
            td, input {
                font-size:.82em
            }
        }
        /* IE styles only */
        @-ms-viewport {
                width: device-width;
		}
	</style>
	<script type="text/javascript" src='//ajax.googleapis.com/ajax/libs/jquery/1.10.0/jquery.js'></script>
	<script>
		jQuery.noConflict();	//http://wiki.apache.org/tapestry/Tapestry5HowToIntegrateJQuery

		jQuery(function() {
		        jQuery('#usernameField').focus();
		});

        /** this has to be BEFOFE requireJS to avoid reloading it twice! */
        if ("https:" === document.location.protocol) {
            /* secure */
        } else {
            /* unsecure */
            if(location.hostname.indexOf('localhost') === -1) {
                window.location= "https://" + location.hostname + location.pathname;
            }
        }
	</script>
</head>

<body onload='document.loginForm.j_username.focus();'>
<div data-role="page" data-theme="c">
	<div data-role="header" data-nobackbtn="true">
<% if(session.getAttribute("data-icon-back") != null) { %>
		<a href="<%= com.appspot.cloudserviceapi.common.Constants.MAIN_URL %>" data-icon="back"><%=session.getAttribute("data-icon-back")%></a>
<% } %>
		<h2>Welcome!</h2>
	</div>
	<div data-role="content" data-theme="c">

<form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
        <table>
          <tr><td>ID</td><td><input size="5" name="password" autocapitalize="off" autocorrect="off" id="usernameField" type="text" /></td></tr>
          <tr><td>Password</td><td><input size="5" autocomplete="off" id="passwordField" type="password" name="j_password" /></td></tr>
          <tr><td colspan="4" align="right"><input style="margin-top:10px;width:100%;height:100%;" type="submit" value="Submit" /></td></tr>
        </table>
</form>

	</div>
</div>
</body>

</html>