<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html> 
<head> 
    <link href="skin.css" rel="stylesheet" type="text/css"> 
    <title><s:text name="address.form.title"/></title> 
</head> 
 
<body> 
<div class="form-container"> 
    <h1><s:text name="address.form.title"/></h1> 
    <div class="error"><s:fielderror /></div> 
    <s:form action="AddressBook" theme="simple"> 
    <table>
    <tr> 
        <th> 
            <s:label key="getText('address.name')"/> 
        </th><td> 
            <s:textfield size="24" key="address.name" name="address.name"/> 
        </td> 
    </tr>
    <tr> 
        <th> 
            <s:label key="getText('address.address')"/> 
        </th><td> 
            <s:textfield size="32" key="address.address" name="address.address"/> 
        </td> 
    </tr><tr> 
        <th> 
            <s:label key="getText('address.city')"/> 
        </th><td> 
            <s:textfield size="18" key="address.city" name="address.city"/> 
        </td> 
    </tr><tr> 
        <th> 
            <s:label key="getText('address.state')"/> 
        </th><td> 
            <s:textfield size="2" key="address.state" name="address.state"/> 
        </td> 
    </tr><tr> 
        <th> 
            <s:label key="getText('address.zipcode')"/> 
        </th><td> 
            <s:textfield size="5" key="address.zipcode" name="address.zipcode"/> 
        </td> 
    </tr><tr> 
        <td class="buttonbar" colspan="2"> 
            <s:submit cssClass="button" key="button.reset" method="reset"/> 
            <s:submit cssClass="button" key="button.save" method="update"/> 
        </td> 
    </tr></table> 
    </s:form> 
    <div class="statusbar"> 
        <s:property value="message"/> 
    </div> 
</div> 
</body> 
</html>