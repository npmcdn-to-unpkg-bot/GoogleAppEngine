<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<p>Business Owner Logon<p>

<s:form action="Register" theme="xhtml">
	<s:textfield name="userName" label="User ID" />
	<s:password name="password" label="Password" />
	<s:password name="vcode" label="Verification Code" />
<!--	<s:radio name="mode" list="{'Multiple','Single'}" /> -->
	<s:submit />
</s:form>

</body>
</html>