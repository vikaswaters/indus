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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body <% if(request.getAttribute("PASSWORD_MAILED")!=null){ %> onload="alert('Email containing the password has been sent!')" <%} %>>
<%
UserLoginForm prefForm = (UserLoginForm) session.getAttribute("UserLoginForm");
%>
<%@ include file="../top.jsp"%> 
<font color="red"><html:errors /></font>
<html:form action="/UserLogin" method="post">
<input type="hidden" name="formAction" value="" />
<input type="hidden" name="selectedItemID" value="" />

<div id="userDiv" style="position: relative; top:20px; left: 20px; width: 500px;" class="boundarybox">
	<table width=500px;>
	<tr>
		<td class="alignleft"><b>Select either:</b></td>
		<td/>
	</tr>		
	<tr class="spaceUnderSmall">
		<td class="alignright"><html:radio property="usertype" value="1" onclick="PreparePasswordField(this.value)"></html:radio></td>
		<td class="alignleft"><b>I am a new customer</b><br/> (You'll have to register later)</td>
	</tr>
	<tr class="spaceUnderSmall">
		<td class="alignright"><html:radio property="usertype" value="2" onclick="PreparePasswordField(this.value)"></html:radio></td>
		<td class="alignleft"><b>I am a returning customer</b></td>
	</tr>
	<tr id="emailidrow" <%if(prefForm==null || prefForm.getUsertype().equals("1")){ %> style="visibility:hidden" <%} %>>
		<td class="alignleft"><b>Enter your e-mail address:</b></td>
		<td class="alignleft"><html:text property="emailid" size="30" maxlength="30"></html:text></td>
	</tr>	
	<tr id="passwordrow" <%if(prefForm==null || prefForm.getUsertype().equals("1")){ %> style="visibility:hidden" <%} %>>
		<td class="alignleft"><b>Enter your password:</b></td>
		<td class="alignleft">
			<html:password  property="password" size="16" maxlength="16"></html:password>
		</td>
	</tr>
	<tr id="signupbutton" <%if(prefForm!=null && prefForm.getUsertype().equals("2")){ %> style="visibility:hidden" <%} %>>
		<td/>
		<td class="alignleft">	
			<html:submit styleClass="btn" onclick="ValidateSignInFormAndSubmit('UserLoginForm', 'SIGNIN')">
						<bean:message key="label.common.html.signup.button" />
			</html:submit>	
		</td>
	</tr>
	<tr id="signinbutton" <%if(prefForm==null || prefForm.getUsertype().equals("1")){ %> style="visibility:hidden" <%} %>>
		<td/>
		<td class="alignleft">	
			<html:submit styleClass="btn" onclick="ValidateSignInFormAndSubmit('UserLoginForm', 'SIGNIN')">
						<bean:message key="label.common.html.signin.button" />
			</html:submit>	
		</td>
	</tr>
	<tr id="forgetpasswordrow" <%if(prefForm==null || prefForm.getUsertype().equals("1")){ %> style="visibility:hidden"<%} %>>
		<td/>
		<td class="alignleft">	
			<html:link page="/pages/user/forgetpassword.jsp">Forgot your password? Click here</html:link>	
		</td>
	</tr>
	</table>
</div>
	
</html:form>
</body>
</html>