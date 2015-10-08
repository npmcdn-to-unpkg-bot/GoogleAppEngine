<%@taglib uri="/struts-tags" prefix="s"%>

<h1>Add User</h1>

<div>
    <s:form action="add" namespace="/user">
        <s:include value="form.jsp" />
    </s:form>
</div>
