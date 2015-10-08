<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="partner==null || partner.id == null">
	<s:set name="title" value="%{'Add new partner'}"/>
</s:if>
<s:else>
	<s:set name="title" value="%{'Update partner'}"/>
</s:else>

<html>
<head>
    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
    <style>td { white-space:nowrap; }</style>
    <title><s:property value="#title"/></title>
</head>
<body>
<div class="titleDiv"><s:text name="application.title.partner"/></div>
<h1><s:property value="#title"/></h1>
<s:actionerror />
<s:actionmessage />
<s:form action="crudSave" method="post" validate="true">

    <s:select name="partner.business.name" value="%{partner.business.name}" label="%{getText('label.business.name')}"
    			list="businesses" listKey="id" listValue="name" emptyOption="true" />
    <s:textfield name="partner.firstName" value="%{partner.firstName}" label="%{getText('label.firstName')}" size="40"/>
    <s:textfield name="partner.lastName" value="%{partner.lastName}" label="%{getText('label.lastName')}" size="40"/>
    <s:textfield name="partner.addedBy" value="%{partner.addedBy}" label="%{getText('label.addedBy')}" size="40"/>
    <s:textfield name="partner.date" value="%{partner.date}" label="%{getText('label.date')}" size="40"/>
    <s:textfield name="partner.userId" value="%{partner.userId}" label="%{getText('label.userId')}" size="40"/>
    <s:textfield name="partner.position" value="%{partner.position}" label="%{getText('label.position')}" size="40"/>
    <s:textfield name="partner.disabled" value="%{partner.disabled}" label="%{getText('label.disabled')}" size="40"/>
    <s:textfield name="partner.ipAddress" value="%{partner.ipAddress}" label="%{getText('label.ipAddress')}" size="40"/>
    <s:textfield name="partner.nationalId" value="%{partner.nationalId}" label="%{getText('label.nationalId')}" size="40"/>
    <s:textfield name="partner.nationality" value="%{partner.nationality}" label="%{getText('label.nationality')}" size="40"/>
    <s:textfield name="partner.homeAddress" value="%{partner.homeAddress}" label="%{getText('label.homeAddress')}" size="40"/>
    <s:textfield name="partner.state" value="%{partner.state}" label="%{getText('label.state')}" size="40"/>
    <s:textfield name="partner.postalCode" value="%{partner.postalCode}" label="%{getText('label.postalCode')}" size="40"/>
    <s:textfield name="partner.country" value="%{partner.country}" label="%{getText('label.country')}" size="40"/>
    <s:textfield name="partner.phoneNumber" value="%{partner.phoneNumber}" label="%{getText('label.phoneNumber')}" size="40"/>
    <s:textfield name="partner.homeNumber" value="%{partner.homeNumber}" label="%{getText('label.homeNumber')}" size="40"/>
    <s:textfield name="partner.email" value="%{partner.email}" label="%{getText('label.email')}" size="40"/>
    <s:textfield name="partner.password" value="%{partner.password}" label="%{getText('label.password')}" size="40"/>
    
    <s:hidden name="partner.id" value="%{partner.id}"/>
    <s:submit value="%{getText('button.label.submit')}"/>
    <s:submit action="cancelPartner" value="%{getText('button.label.cancel')}" name="cancel" onclick="form.onsubmit=null" />
</s:form>
</body>
</html>