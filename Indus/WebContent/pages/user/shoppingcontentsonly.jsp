<%@page import="com.indus.core.SelectedItem"%>
<%@page import="com.indus.core.ShoppingCart"%>
<%@page import="com.indus.dao.hibernate.Catalog"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="selectedDeleteItemID" value="" />
   <%
	ShoppingCart shoppingcartX = (ShoppingCart)request.getSession().getAttribute("SHOPPING_CART");
	%>
		<br/><b class="boldheaders">Summary of items:</b>
		<table cellpadding="5" cellspacing="2" border="8">
				<tr>
					<th>Name</th>
					<th>Unit Price</th>
					<th>Quantity</th>
					<th>Price</th>
					<%if(request.getAttribute("EDIT_CART_DISABLED")==null){ %>
					<th></th>
					<% } %>
				</tr>				
				<% for (Iterator<SelectedItem> iterator = shoppingcartX.getSelectedItems().iterator(); iterator.hasNext();) {
					SelectedItem selItem = (SelectedItem) iterator.next();
					Catalog catalogX = selItem.getItem();
				%>
					<tr>
						<td><%=catalogX.getName() %></td>
						<td><%=catalogX.getPrice() %></td>
						<td><%=selItem.getQuantity() %></td>
						<td><%=catalogX.getPrice() * selItem.getQuantity() %></td>
						<%if(request.getAttribute("EDIT_CART_DISABLED")==null){ %>
						<td class="tableDataEdit"><a HREF="javascript:DeleteItem('MainForm', <%=catalogX.getItemId()%>)"><img alt="help" height=13" width="13" src="/images/del.gif" TITLE="Delete item"></a></td>
					   <% } %>
					</tr>
				<% }%>	
					<tr class="tableLastRow">
						<th colspan=2>Total</th>
						<th><%=shoppingcartX.getTotalItems() %></th>
						<th><%=shoppingcartX.getTotalItemCost()%></th>
						<%if(request.getAttribute("EDIT_CART_DISABLED")==null){ %>
						<th></th>
						<% } %>
					</tr>							
		</table>
			