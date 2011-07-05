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
<html:form action="/UserLogin" method="post">
<input type="hidden" name="formAction" value="" />
<input type="hidden" name="selectedItemID" value="" />

<div id="userDiv" style="position: relative; top:100px; left: 0px; width: 600px;" class="boundarybox">
<b>Enter the shipping address for this order</b><br/>
Please enter a shipping address for this order. Please also indicate whether your billing address is the same as the shipping address entered.  When finished, click the "Continue" button.  Or, if you're sending items to more than one address, click the "Add another address" button to enter additional addresses.
<br/><br/><b>Enter the shipping address for this order</b><br/>
When finished, click the "Continue" button.
<br/><br/>

	<table width=600px;>
<%@ include file="address.jsp"%>
	<tr>
		<td colspan="2"><b>Is this address also your billing address (the address that appears on your credit card or bank statement)?	</b></td>
	</tr>	
	<tr>
	<td/>
		<td class="alignleft"><html:radio property="usertype" value="1">Yes</html:radio></td>
	</tr>	
	<tr>
	<td/>
		<td class="alignleft"><html:radio property="usertype" value="2">No (If not, we'll ask you for it in a moment.)</html:radio></td>
	</tr>
	</table>
			<a style="position: relative; top:3px; left: 500px;" HREF="javascript:SetActionSubmitForm('UserLoginForm', 'CREATE_NEW_SHIPPING_ADDRESS')">
				<img height="25" width="100" src="images/continue.gif" TITLE="Create account">
			</a>
</div>
</html:form>
</body>
</html>