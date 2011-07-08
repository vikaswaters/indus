<%@page import="com.indus.IndusUtility"%>
<%@page import="com.indus.core.SelectedItem"%>
<%@page import="com.indus.core.ShoppingCart"%>
<%@page import="com.indus.dao.hibernate.Catalog"%>
<%@page import="com.lbr.SubcategoryWrapper"%>
<%@page import="com.lbr.LbrConstants"%>
<%@page import="com.lbr.LbrUtility"%>
<%@page import="com.lbr.dao.specificdao.DaoUtilities"%>
<%@page import="com.indus.dao.hibernate.Shipping"%>
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

<%
	ShoppingCart shoppingcart = (ShoppingCart)request.getSession().getAttribute("SHOPPING_CART");
	%>
	
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

<div id="shoppingcartDiv" style="position: relative; top:100px; left: 200px; width: 800px;" class="boundarybox">
<p style="font: 16px Verdana, Helvetica, sans-serif;">
Thank you for shopping with Indusaura!!<br/>
Your order  with the details below is confirmed, subject to payment received.</b><br/>
Your order details has been mailed to <b><%=shoppingcart.getCustomer().getEmail() %></b><br/>
Your transaction has been completed, and a receipt for your purchase has been emailed to you by Paypal. You may log into your account at www.paypal.com to view details of this transaction.<br/><br/><br/>
<b>Order and transaction Details:</b><br/>
Order ID: <b><%=shoppingcart.getOrder().getOrderid() %> </b><br/>
Payment ID: <b><%=shoppingcart.getOrder().getPayment().getPaymentid() %> </b><br/>
Transaction ID:<b><%=shoppingcart.getOrder().getPayment().getTransactionid() %> </b><br/>
</p>
</div>	
</html:form>
</body>
</html>