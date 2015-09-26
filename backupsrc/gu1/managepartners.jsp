<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="titleDiv"><s:text name="application.title.partner"/></div>
<h1><s:text name="label.partner"/></h1>
<s:url id="url" action="crudInput"/>
<a href="<s:property value="#url"/>">Add New Partner</a>
<br/><br/>
<table class="borderAll">
    <tr>
        <th><s:text name="label.business"/></th>
        <th>...&nbsp;</th>
    </tr>
    <s:iterator value="partners" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
            <td class="nowrap"><s:property value="business.name"/></td>
            <td class="nowrap"><s:property value="id"/></td>
            <td class="nowrap"><s:property value="addBy"/></td>
            <td class="nowrap"><s:property value="date"/></td>
            <td class="nowrap"><s:property value="name"/></td>
            <td class="nowrap"><s:property value="userId"/></td>
            <td class="nowrap"><s:property value="position"/></td>
            <td class="nowrap"><s:property value="disabled"/></td>
            <td class="nowrap"><s:property value="ipAddress"/></td>
            <td class="nowrap"><s:property value="nationalId"/></td>
            <td class="nowrap"><s:property value="nationality"/></td>
            <td class="nowrap"><s:property value="homeAddress"/></td>
            <td class="nowrap"><s:property value="state"/></td>
            <td class="nowrap"><s:property value="postalCode"/></td>
            <td class="nowrap"><s:property value="country"/></td>
            <td class="nowrap"><s:property value="phoneNumber"/></td>
            <td class="nowrap"><s:property value="homeNumber"/></td>
            <td class="nowrap"><s:property value="email"/></td>
            <td class="nowrap"><s:property value="password"/></td>
            <td class="nowrap">
                <s:url action="crudInput" id="url">
                    <s:param name="partner.id" value="id"/>
                </s:url>
                <a href="<s:property value="#url"/>">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <s:url action="crudDelete" id="url">
                    <s:param name="partner.id" value="id"/>
                </s:url>
                <a href="<s:property value="#url"/>" onclick="return confirm('<s:text name="confirmDelete.message"/>');"><s:text name="confirmDelete.title"/></a>
            </td>
        </tr>
    </s:iterator>
    </table>
</body>
</html>