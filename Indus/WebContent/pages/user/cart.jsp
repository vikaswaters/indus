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

<div id="shoppingcartsummary" style="width: 200; position: relative; top: 40px; left: 0px" class="boundarybox">
<%
	if(request.getSession().getAttribute("SHOPPING_CART")==null || ((ShoppingCart)request.getSession().getAttribute("SHOPPING_CART")).isEmpty()){
%>
	<b>Your <img alt="help" height="40" width="30" src="/images/shopping_cart.png" TITLE="Empty"/> is Empty</b>
<% }else{
	ShoppingCart shoppingcart = (ShoppingCart)request.getSession().getAttribute("SHOPPING_CART");
	int numItems= shoppingcart.getSelectedItems().size();
	%>
	<b>Your <img alt="help" height="50" width="40" src="/images/shopping-cart-full.png" TITLE="Empty"/> has <%=numItems %> items<br/><br/>Cost(USD): <%=shoppingcart.getTotalItemCost() %></b>
</div>	
	
	<div id="checkout"	style="width: 100; position: relative; top: -42px; left: 250px">
		<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'CHECKOUT')">
			<bean:message key="label.common.html.select.button.checkout" />
		</html:submit>
	</div>	
	<a style="position: relative; top: 20px; left: 300px; id="shoppingDetailsHrefID" HREF="javascript:ToggleCartDetails('shopppingDetailsDiv')">Hide Details</a>	
	<div id="shopppingDetailsDiv" style="position: relative; top: 0px; left: 0px;  width:400px; visibility:visible">
		<%@ include file="shoppingcontentsonly.jsp"%> 
	</div>
	
<% } %>
	    
