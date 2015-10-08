<%@taglib uri="/struts-tags" prefix="s"%>

<h1>Edit User ${userId}</h1>

<div>
	<s:if test="user != null">
		<s:form action="edit" namespace="/user">
		    <s:include value="form.jsp" />
		</s:form>
	</s:if>
	<s:else>
		<s:text name="error.notFound" />
	</s:else>
</div>
