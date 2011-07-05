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
int numItems= shoppingcart.getSelectedItems().size();
float shippingcost = 0;
AddressForm prefForm = (AddressForm) session.getAttribute("AddressForm");
 String shippingid = prefForm.getShippingOptionID();
 Shipping ship = (Shipping)DaoUtilities.staticCache.get("SHIPPINGID_"+shippingid);
 if(ship!=null)
 	shippingcost = ship.getCost()+ship.getDuty()+ship.getTaxes();
	
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

<div id="shoppingcartDiv" style="position: relative; top:20px; left: 20px; width: 600px;">
<% request.setAttribute("EDIT_CART_DISABLED", ""); %>
<%@ include file="shoppingcontentsonly.jsp"%> 

	<div id="totalcostDiv" style="position: relative; top:40px; left: 0px; width: 600px;">
	<b class="boldheaders">Order and cost Summary:</b>
		<table cellpadding="5" cellspacing="2" border="8" width="300">
				<tr><td>Items cost: <%=shoppingcart.getTotalItemCost() %></td></tr>
				<tr><td>Shipping and Handling cost: <%=shippingcost%></td></tr>
				<tr><td class="spaceUnderSmall">Other taxes: 0</td></tr>
				<tr><td><b>Total order cost: <%=(shoppingcart.getTotalItemCost()+shippingcost)%></b></td></tr>
	    </table>				
		<div id="shipping address" style="position: relative; top:30px; left: 0px; width: 300px;" class="boundarybox">
		<b class="boldheaders">Shipping address:</b>
		<br/><br/>
		<%=IndusUtility.printAddressHTML(shoppingcart.getOrder().getCustomer().getAddressByShipaddressid())%>
		<%if(request.getAttribute("ORDER_CONFIRMED")==null){ 
		%>
		<html:submit style="position: relative; top:60px; left: 0px;" styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'CONFIRM_ORDER')">
			<bean:message key="label.common.html.select.button.confirmorder" />
		</html:submit>	
		<%} %>
		</div>
	</div>
</div>		
</html:form>

<%if(request.getAttribute("ORDER_CONFIRMED")!=null){ %>
	<div id="paypal" style="position: absolute; top:170px; left: 900px; width: 300px;" class="boundarybox">
	<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
	<input type="hidden" name="cmd" value="_s-xclick">
	<input type="hidden" name="hosted_button_id" value="VFD4LS8XCZNFS">
	<input type="image" src="https://www.paypalobjects.com/en_GB/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online.">
	<img alt="" border="0" src="https://www.paypalobjects.com/en_GB/i/scr/pixel.gif" width="1" height="1">
	</form>
	</div>
<%} %>
</body>
</html>