<%@page import="com.indus.core.ShoppingCart"%>
<%@page import="com.indus.IndusUtility"%>
<%@page import="com.lbr.dao.specificdao.DaoUtilities"%>
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
	
<script type="text/javascript" language="javascript" src="ajax.js"></script>
<script type="text/javascript" language="javascript" src="pages/lbr.js"></script>
<%@ include file="../top.jsp"%> 

<h2></h2>
<%
	MainForm prefForm = (MainForm) session.getAttribute("MainForm");
	Catalog catalog = DaoUtilities.catalogCache.get(prefForm.getSelectedItemID());
%>

<html:form action="/Main" method="post" >
<input type="hidden" name="formAction" value="" />
<input type="hidden" name="rate" value="<%=catalog.getPrice()%>" />

<div id="shoppingDetailsDiv" style="position: absolute; top: 100px; left: 1100px;">
<%@ include file="cart.jsp"%> 
</div>	

<%  
	if(prefForm.getFormAction()!=null && ((prefForm.getFormAction().equalsIgnoreCase("ADD_TO_CART") || prefForm.getFormAction().equalsIgnoreCase("ADDITEM_AND_SHOPMORE") || prefForm.getFormAction().equalsIgnoreCase("ADDITEM_AND_CHECKOUT"))
			  			|| (request.getParameter("validationerror"))!=null)){
		//objForm.setFormAction(null);
%>
<div id="selectDiv"	style="position: absolute; top: 150px; left: 560px;">
	<table cellpadding="3" cellspacing="2" border="8" width="300">
				<tr><td><b>Select Quantity</b></td><td><html:text property="quantity" size="5" maxlength="2" onchange="calculateCost(this.value)"></html:text></td></tr>
				<%if(prefForm.getAvailSizeList()!=null && prefForm.getAvailSizeList().size() >0){ %>  
				<tr><td>Select Size </td>
					<td>
						<html:select property="sizeID" onchange="">
								<html:optionsCollection name="MainForm" property="availSizeList" label="targetName" value="targetID" />
						</html:select>			
					</td>
				</tr>
				<% } %>
				<tr><td><b>Cost @ <%=catalog.getPrice()%> USD </b></td> <td><html:text property="currcost" value="<%=catalog.getPrice().toString()%>" disabled="true"></html:text></td>
				</tr>
				<tr class="spaceUnderSmall">
					<td colspan="1"><html:submit styleClass="btn" onclick="SetActionSubmitFormWithStrutsValidation('MainForm', 'ADDITEM_AND_CHECKOUT',validateMainForm)">
										<bean:message key="label.common.html.select.button.addandcheckout" />
									</html:submit>
					</td>
					<td colspan="1"><html:submit styleClass="btn" onclick="SetActionSubmitFormWithStrutsValidation('MainForm', 'ADDITEM_AND_SHOPMORE', validateMainForm)">
										<bean:message key="label.common.html.select.button.addandshopmore" />
									</html:submit>									
					</td>	
			    </tr>				
	</table>	
</div>
<%
	}
%>
<div id="myDiv"	style="position: absolute; width: 400px; height: 450px; top: 500px; left: 10px;">
			<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'ADD_TO_CART')">
				<bean:message key="label.common.html.select.button.addtocart" />
			</html:submit>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        
			<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'CANCEL')">
				<bean:message key="label.common.html.cancel.button"/>
			</html:submit>
			
<table cellpadding="5" cellspacing="2" border="8" width="400">
				<%
					int numImages = 0;
					String imagesStr = catalog.getImage().trim().replaceAll("( )+", "");

					List<String> images =  null;
					String delimiter = "|";
					String firstImage = null;	
					boolean multipleImages = false;
					if(imagesStr.indexOf(delimiter)!=-1){
					    images = IndusUtility.parseStringWithDelimiter(imagesStr, delimiter);
						firstImage = images.get(0);
						multipleImages = true;
						numImages = images.size();
					}else
						firstImage = catalog.getImage();			
				%>
			<% if (multipleImages) {  %>
			<tr>
			<%
	        	for (int ii = 0; ii < numImages; ii++) {
			%>
				<% 
					if(ii % 2 ==0) {
						%>
						</tr>
						<tr>
				<% } %>
			<td><a class="MagicZoom" href="/images/catalog/low_<%=images.get(ii)%>"><img height="500" width="500" style="padding:0px;border:0px;" src="/images/catalog/low_<%=images.get(ii)%>"/></a></td>	
			<% } %>
			 </tr>
			<% } else { %>
			<tr>
			<td colspan="2"><a class="MagicZoom" href="/images/catalog/low_<%=catalog.getImage()%>"><img height="500" width="500" style="padding:0px;border:0px;" src="/images/catalog/low_<%=catalog.getImage()%>"/></a></td>
			</tr>
			<% } %>

		</table>
		<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'ADD_TO_CART')">
			<bean:message key="label.common.html.select.button.addtocart"/>
		</html:submit>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        
		<html:submit styleClass="btn" onclick="SetActionSubmitForm('MainForm', 'CANCEL')">
			<bean:message key="label.common.html.cancel.button" />
		</html:submit>		
	</div>
				
	<div id="myDiv"	style="position: absolute; width: 500px; height: 300px; top: 150px; left: 10px;">	
	<table style="width: 500px; height: 300px; cellpadding="5" cellspacing="2" border="8">		
		<tr><td colspan="2"><b><%=catalog.getName() %></b></td></tr>
		<tr><td><b>Price</b></td><td>USD <%=catalog.getPrice() %></td></tr>
		<tr><td><b>Stock</b></td><td><%=catalog.getStock() %></td></tr>
		<tr><td><b>Color</b></td><td><%=catalog.getColor() %></td></tr>
		<tr><td><b>Material </b></td><td><%=catalog.getMaterial() %></td></tr>
		<tr><td><b>Size</b></td><td><%=catalog.getSize() %></td></tr>
		<tr><td><b>Weight</b></td><td><%=catalog.getWeight() %></td></tr>
		<tr><td rowspan="8"><b>Description :</b></td><td><%=catalog.getDescription() %></td></tr>		
	</table>
</html:form>
<html:javascript formName="MainForm" staticJavascript="true" />

