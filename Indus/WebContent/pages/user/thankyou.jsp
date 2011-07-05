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

<div id="shoppingcartDiv" style="position: relative; top:50px; left: 0px; width: 300px;" class="boundarybox">
<p style="font: 14px;">
Your order has been confirmed.<br/>
Your order # is <%=shoppingcart.getOrder().getOrderid() %> <br/>
Your odder details has been mailed to <%=shoppingcart.getCustomer().getEmail() %> <br/>
Thank you !!
</p>
</div>	
</html:form>
</body>
</html>