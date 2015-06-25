<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<div id="messages">
<s:actionerror />
</div>

<s:push value="user">
	<s:hidden name="id" />
	
    <p>
        <s:label key="label.name" />
        <s:textfield name="name" />
        <s:fielderror fieldName="name" />
    </p>
    <p>
        <s:label key="label.passw" />
        <s:textfield name="passw" />
        <s:fielderror fieldName="passw" />
    </p>
    <p>
        <s:label key="label.registration" />
        <sj:datepicker name="registration" displayFormat="dd/mm/yy" />
        <s:fielderror fieldName="registration" />
    </p>

	<p>
	    <s:hidden name="save" value="true" />
	    <s:submit key="label.save" name="" />
	    <s:submit key="form.cancel" onclick="window.location.href = './index.action';return false;" />
	</p>
</s:push>
