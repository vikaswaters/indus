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
	float shippingcost = 0;
	MainForm prefForm = (MainForm) session.getAttribute("MainForm");
	 String shippingid = prefForm.getShippingOptionID();
	 Shipping ship = (Shipping)DaoUtilities.staticCache.get("SHIPPINGID_"+shippingid);
	 if(ship!=null)
	 	shippingcost = ship.getCost();
	
	%>

	<div id="shopppingDetailsDiv" style="position: absolute; top: 180px; left: 20px; width: 800px;">
    Your <img alt="Cart" height="50" width="40" src="../images/shopping-cart-full.png" TITLE="Empty"/> has <%=numItems %> items<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cost : <%=shoppingcart.getTotalItemCost() %>
    <%@ include file="shoppingcontentsonly.jsp"%>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%-- 
				<a style="position: relative; top:5px; left: 645px;" HREF="javascript:SetActionSubmitForm('MainForm', 'PROCEED_TO_SIGNIN')"><img height="30" width="130" src="../images/proceedcheckout.JPG" TITLE="Proceed to Signin"></a>
				--%>
			<div id="shopppingDetailsButton" style="position: relative; top: -10px; left: 0px; width: 200px;">	
				<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'PROCEED_TO_SIGNIN')">
					<bean:message key="label.common.html.proceedsignin.button" />
				</html:submit>	
			</div>
</div>
	
</html:form>
</body>
</html>