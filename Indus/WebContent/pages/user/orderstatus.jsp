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
<body>
<%@ include file="../top.jsp"%> 
<html:form action="/Main" method="post">
<input type="hidden" name="formAction" value="" />
<input type="hidden" name="selectedItemID" value="" />
<%
	ShoppingCart shoppingcart = (ShoppingCart)request.getSession().getAttribute("SHOPPING_CART");
	int numItems= shoppingcart.getSelectedItems().size();
	%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your <img alt="help" height="50" width="40" src="images/shopping-cart-full.png" TITLE="Empty"/> has <%=numItems %> items<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cost : <%=shoppingcart.getTotalItemCost() %>
	<br/><br/>
	<div id="shopppingDetailsDiv" style="position: absolute; top: 200px; left: 20px; width: 800px;">
		<%@ include file="shoppingcontentsonly.jsp"%> 
	</div>
	<div id="shippingDetailsDiv" style="position: relative; top:50px; left: 0px; width: 800px;" >
	<b>Select Shipping Options</b><br/>
	<html:select property="countryID" onchange="loadTargetCatalogData('MainForm', 'GET_SHIPPING_OPTIONS_FOR_COUNTRY') ">
			<html:optionsCollection name="MainForm" property="countryList" label="targetName" value="targetID" />
	</html:select>

	<html:select property="shippingOptionID">
			<html:optionsCollection name="MainForm" property="shippingOptionList" label="targetName" value="targetID" />
	</html:select>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'SELECT_SHIPPING_OPTION')">
		<bean:message key="label.common.html.select.button.selectshipping" />
	</html:submit>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'CANCEL')">
				<bean:message key="label.common.html.cancel.button" />
	</html:submit>	
</div>	
<div id="userDiv" style="position: relative; top:100px; left: 0px; width: 500px;" class="boundarybox">
	<table width=500px;>
	<tr>
		<td><b>Enter your e-mail address:</b></td>
		<td class="alignleft"><html:text property="emailid"></html:text></td>
	</tr>
	<tr>
		<td class="alignright"><html:radio property="usertype" value=""></html:radio></td>
		<td class="alignleft"><b>I am a new customer.</b><br/> (You'll have to create a password later)</td>
	</tr>
	<tr>
		<td class="alignright"><html:radio property="usertype" value=""></html:radio></td>
		<td class="alignleft"><b>I am a returning customer,<br/> and my password is:</b></td>
	</tr>
	<tr>
		<td/>
		<td class="alignleft"><html:password property="password"></html:password></td>
	</tr>
	<tr>
		<td/>
		<td class="alignleft">	<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'SIGNIN')">
					<bean:message key="label.common.html.signin.button" />
		</html:submit>	</td>
	</tr>
	<tr>
		<td/>
		<td class="alignleft">	<html:link action="UserLocation.do?formActionOriginatedFrom=UserPreferenceModule">Forgot your password? Click here
		</html:link>	</td>
	</tr>
	</table>
</div>
	
</html:form>
</body>
</html>