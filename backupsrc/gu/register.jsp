<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<p>Welcome to Your Points Group!<p>

Please sign up to start earning reward points -<br>
<s:form action="Register" theme="xhtml">
	<s:textfield name="userName" label="User Name" />
	<s:password name="password" label="Password" />
	<s:textfield name="userName" label="Email" /><br>
	<s:radio name="gender" label="Gender" list="{'Male','Female'}" />
	<s:select name="country" list="countryList" listKey="countryId"
		listValue="countryName" headerKey="0" headerValue="Country"
		label="Select a country" />
	<s:textarea name="about" label="About You" />
	<s:radio list="communityList" name="community" label="Account" /><br>
	<s:checkbox name="mailingList"
		label="Yes, I would like to join your mailing list." />
	<s:submit />
</s:form>
</body>
</html>