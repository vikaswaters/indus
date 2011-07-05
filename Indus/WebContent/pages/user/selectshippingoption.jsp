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

<body <% if(request.getAttribute("ADDRESS_SAVED")!=null){ %> onload="alert('Address updated successfully!')" <%} %>>
<%@ include file="../top.jsp"%> 
<html:form action="/Address" method="post">
<input type="hidden" name="formAction" value="" />
<input type="hidden" name="formUserAction" value="" />
<input type="hidden" name="selectedItemID" value="" />
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
	
	<div  id="shopppingDetailsDiv" style="position: absolute; top: 170px; left: 20px; width: 800px;">
	Your <img alt="Cart" height="50" width="40" src="/images/shopping-cart-full.png" TITLE="Empty"/> has <%=numItems %> items<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cost : <%=shoppingcart.getTotalItemCost() %>
	<br/><br/>
		<div  id="shopppingDetailsDiv" style="position: relative; top: 0px; left: 20px; width: 800px;">
		<% request.setAttribute("EDIT_CART_DISABLED", ""); %>
			<%@ include file="shoppingcontentsonly.jsp"%> 
	
			<div id="shippingDetailsDiv" style="position: relative; top:40px; left: 0px; width: 800px; hidden: true;">
				<b class="boldheaders">Select Shipping Options</b><br/>
				<html:select property="countryID" disabled="true" onchange="SetActionSubmitForm('AddressForm', 'GET_SHIPPING_OPTIONS_FOR_COUNTRY')">
						<html:optionsCollection name="AddressForm" property="countryList" label="targetName" value="targetID" />
				</html:select>
			
				<html:select property="shippingOptionID" onchange="SetActionSubmitForm('AddressForm', 'CALCULATE_TOTAL_ORDER_COST')">
						<html:optionsCollection name="AddressForm" property="shippingOptionList" label="targetName" value="targetID" />
				</html:select>
		
					<div id="totalcostDiv" style="position: relative; top:40px; left: 0px; width: 300px;">
					<b class="boldheaders">Order and cost Summary:</b>
						<table cellpadding="5" cellspacing="2" border="8" width="300">
								<tr><td>Items cost: <%=shoppingcart.getTotalItemCost() %></td></tr>
								<tr><td>Shipping and Handling cost: <%=shippingcost%></td></tr>
								<tr><td class="spaceUnderSmall">Other taxes: 0</td></tr>
								<tr><td><b>Total order cost: <%=(shoppingcart.getTotalItemCost()+shippingcost)%></b></td></tr>
					    </table>				
				<div id="totalcostDiv" style="position: relative; top:10px; left: 0px; width: 300px;" >		
					<html:submit styleClass="btn" onclick="SetActionSubmitForm('AddressForm', 'CONFIRM_SHIPPING_OPTION')">
								<bean:message key="label.common.html.button.confirmshipping" />
					</html:submit>	
				</div>
				<!--  			
						<a style="position: relative; top:2px; left: 175px;" HREF="javascript:SetActionSubmitForm('AddressForm', 'CONFIRM_SHIPPING_OPTION')"><img height="25" width="100" src="images/continue.gif" TITLE="Proceed to signin"></a>
				-->	
					</div>		
			</div>
		</div>	
</div>
</html:form>
</body>
</html>