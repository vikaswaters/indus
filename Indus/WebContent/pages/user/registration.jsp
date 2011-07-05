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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../top.jsp"%>
<font color="red"><html:errors /></font>
<html:form action="/Register" method="post">
<input type="hidden" name="formAction" value="" />

<div id="userDiv" style="position: relative; top:40px; left: 0px; width: 500px;" class="boundarybox">
<b>Registration</b>
	<table width=500px;>
	<tr>
		<td><b>My name is:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="name"></html:text></td>
	</tr>
	<tr>
		<td><b>My e-mail address is:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="emailid"></html:text></td>
	</tr>
	<%-- 
	<tr>
		<td><b>Type it again:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="emailid2"></html:text></td>
	</tr>	
	--%>
	<tr>
		<td><b>My mobile phone number is:</b></td>
		<td class="alignleft"><html:text property="phone"></html:text> (Optional)</td>
	</tr>		
	<tr>
		<td><b>Enter a new password:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="password"></html:text></td>
	</tr>	
	<tr>
		<td><b>Type the password again:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="password2"></html:text></td>
	</tr>		

	</table>
	<!--  
			<a style="position: relative; top:2px; left: 375px;" onclick="javascript:SetActionSubmitFormWithStrutsValidation('RegisterForm', 'CREATE_NEW_ACCOUNT', validateRegisterForm)">
				<img height="25" width="100" src="/images/create-account.gif" TITLE="Create account">
			</a>
				-->
			<html:submit styleClass="btn" onclick="SetActionSubmitFormWithStrutsValidation('RegisterForm', 'CREATE_NEW_ACCOUNT', validateRegisterForm)">
						<bean:message key="label.common.html.account.button" />
			</html:submit>				
</div>
	
</html:form>
<html:javascript formName="RegisterForm" staticJavascript="true" />
