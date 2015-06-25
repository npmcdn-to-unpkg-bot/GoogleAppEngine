<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details Page</title>
</head>
<body>
Thank you! Profile created -<p>
Name: <s:property value="userName" /><br>
Gender: <s:property value="gender" /><br>
Country: <s:property value="country" /><br>
About You: <s:property value="about" /><br>
Account Type: <s:property value="community" /><br>
Mailing List: <s:property value="mailingList" />
<p>
An email has been sent to you and will arrive shortly. Please follow the instruction in the email to activate the account.

Once activated, please follow a similar link like the following to login -<p>

<a href="/gu/member_business_owner.jsp">For business owner account</a><br>
or<br>
<a href="/gu/member_personal.jsp">For personal account</a>

</body>
</html>