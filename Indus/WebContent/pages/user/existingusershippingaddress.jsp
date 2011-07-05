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
<html:form action="/Address" method="post">

<b  class="boldheaders" style="position: relative; top:20px; left: 0px; width: 600px;">Shipping address</b>
<div id="userDiv" style="position: relative; top:30px; left: 0px; width: 600px;" class="boundarybox">
<b >This is the shipping address in our records</b><br/></br>
Please confirm the shipping address for this order.<br/>Otherwise please modify the shipping address<br/>

<br/><br/>

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
	<tr >
	<td/>
		<td class="alignleft"><html:radio property="shippingSameAsBilling" value="0">No (If not, we'll ask you for it in a moment.)</html:radio></td>
	</tr>
	 --%>
	</table>
	<div id="userDiv" style="position: relative; top:25px; left: 0px; width: 600px;">
			<html:submit styleClass="btn" onclick="SetActionSubmitFormWithStrutsValidation('AddressForm', 'MODIFY_SHIPPING_ADDRESS', validateAddressForm)">
				<bean:message key="label.common.html.select.button.modifyshippingaddress" />
			</html:submit>
			<html:submit styleClass="btn" onclick="SetActionSubmitFormWithStrutsValidation('AddressForm', 'USE_CURRENT_SHIPPING_ADDRESS', validateAddressForm)">
				<bean:message key="label.common.html.select.button.useexistingaddress" />
			</html:submit>	
	</div>		
			<!--  	
			<a style="position: relative; top:3px; left: 500px;" HREF="javascript:SetActionSubmitForm('AddressForm', 'MODIFY_SHIPPING_ADDRESS')">
				<img height="25" width="100" src="images/continue.gif" TITLE="Create account">
			</a>
			<a style="position: relative; top:3px; left: 400px;" HREF="javascript:SetActionSubmitForm('AddressForm', 'USE_CURRENT_SHIPPING_ADDRESS')">
				<img height="25" width="100" src="images/continue.gif" TITLE="Create account">
			</a>
			-->			
</div>
</html:form>
</body>
</html>