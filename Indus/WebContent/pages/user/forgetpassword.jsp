<%@page import="com.indus.core.SelectedItem"%>
<%@page import="com.indus.core.ShoppingCart"%>
<%@page import="com.indus.dao.hibernate.Catalog"%>
<%@page import="com.lbr.SubcategoryWrapper"%>
<%@page import="com.lbr.LbrConstants"%>
<%@page import="com.lbr.LbrUtility"%>
<%@page import="com.lbr.web.struts.form.UserPreferenceForm"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>

<%@page import="com.lbr.web.struts.action.*"%>
<%@page import="com.indus.web.struts.form.*"%>
<%@page import="java.util.*"%>
<%@page import="com.lbr.dao.hibernate.domain.*"%>
<%@page import="com.lbr.core.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<script type="text/javascript" language="javascript">
function SetActionSubmitFormWithStrutsValidation(formName, action, validateForm){
	oForm = document.forms[formName];
	oForm.formAction.value = action;
	//alert('SetActionSubmitFormWithStrutsValidation 2 called ......');
	ret = validateForm(oForm);
	//alert(ret);
	if(ret){
		//alert('Sumbitting form');
		oForm.submit();
	}
	//else 
		//alert('Ignoring form submit');
	return null;
}

</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body <% if(request.getAttribute("PASSWORD_MAILED")!=null){ %> onload="alert('Email containing the password has been sent!')" <%} %>>
<%@ include file="../top.jsp"%>
<font color="red"><html:errors /></font>
<html:form action="/ForgetPassword" method="post">
<input type="hidden" name="formAction" value="" />
	<div id="userDiv" style="position: relative; top: 20px; left: 20px; width: 500px;" class="boundarybox">
	<table width=500px;>
		<tr>
			<td align="center" colspan="2"><font size="5">Access Your
			Username and password</font></td>
		</tr>
		<tr>
			<td colspan="2">Enter your e-mail address as in our records:</td>
		</tr>
		<tr>
			<td align="right">Email:</td>
			<td><html:text property="emailid" size="30" maxlength="30" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<p>&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			<html:submit styleClass="btn" onclick="SetActionSubmitFormWithStrutsValidation('ForgetPasswordForm', 'MAIL_MY_PASSWORD', validateForgetPasswordForm)">
				<bean:message key="label.common.html.mailmypassword.button" />
			</html:submit>
			</td>
		</tr>
	</table>
	</div>
</html:form>
</body>
<html:javascript formName="ForgetPasswordForm" staticJavascript="false" />
