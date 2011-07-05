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
<html:form action="/Address" method="post">
<input type="hidden" name="formAction" value="" />
<input type="hidden" name="selectedItemID" value="" />

<% if(session.getAttribute("EXISTING_USER_LOGGED_IN")!=null){ %>
	<%@ include file="existingusershippingaddress.jsp"%> 
<% } else { %>

<div id="userDiv" style="position: relative; top:20px; left: 20px; width: 600px;" class="boundarybox">
<b>Enter the shipping address for this order</b><br/>
Please enter a shipping address for this order. When finished, click the "Continue" button.

	<table width=600px;>
<%@ include file="address.jsp"%>
<%-- 
	<tr>
		<td colspan="2"><b>Is this address also your billing address (the address that appears on your credit card or bank statement)?	</b></td>
	</tr>	
	<tr>
	<td/>
		<td class="alignleft"><html:radio property="shippingSameAsBilling" value="1">Yes</html:radio></td>
	</tr>	
	<tr>
	<td/>
		<td class="alignleft"><html:radio property="shippingSameAsBilling" value="0">No (If not, we'll ask you for it in a moment.)</html:radio></td>
	</tr>
--%>	
	</table>
			<a style="position: relative; top:3px; left: 500px;" onclick="javascript:SetActionSubmitFormWithStrutsValidation('AddressForm', 'CREATE_NEW_SHIPPING_ADDRESS', validateAddressForm)">
				<img height="25" width="100" src="/images/continue.gif" TITLE="Continue">
			</a>
</div>
<% } %>
</html:form>
<html:javascript formName="AddressForm" staticJavascript="true" />
</body>
</html>