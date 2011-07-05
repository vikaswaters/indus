<%@page import="com.indus.core.SelectedItem"%>
<%@page import="com.indus.IndusUtility"%>
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

<font color="red"><html:errors /></font>
<%@ include file="../top.jsp"%> 	

								<%--    PAGE  SPECIFIC CODE --%>
<%
	MainForm prefForm = (MainForm) session.getAttribute("MainForm");
%>
<html:form action="/Main" method="post">
<div id="shoppingDetailsDiv" style="position: absolute; top: 100px; left: 1100px;">
<%@ include file="cart.jsp"%> 
</div>
<div id="targetDiv"	style="position: absolute; width: 500px; top: 100px; left: 20px;">
<b class="boldheaders">Select Category</b><br/>
<html:select property="targetID" styleClass="selectCatalog" onchange="loadTargetCatalogData('MainForm', 'CHANGE_TARGET_CATALOG') ">
		<html:optionsCollection name="MainForm" property="targetList" label="targetName" value="targetID" />
</html:select><%=prefForm.getCatalogList().size()%>&nbsp;items
</div>

<input type="hidden" name="formAction" value="" />
<input type="hidden" name="previousformAction" value="" />
<input type="hidden" name="selectedItemID" value="" />
<div id="catalogDiv" style="position: absolute; width: 1050px; height: 450px; top: 140px; left: 20px;">
<table  cellpadding="5" cellspacing="20" border="0" width="1050">
	<tr>
	</tr>
		<%
		int ii = 0;
		String delimiter = "|";
		String firstImage = null;
				List<Catalog> catalogs = prefForm.getCatalogList();
				for (Iterator iterator = catalogs.iterator(); iterator.hasNext();) {
					Catalog catalog = (Catalog) iterator.next();
					String imagesStr = catalog.getIcon();
					if(imagesStr.indexOf(delimiter)!=-1){
						List<String> images = IndusUtility.parseStringWithDelimiter(imagesStr, delimiter);
						firstImage = images.get(0);
					}else
						firstImage = catalog.getIcon();
						
					if(ii++ % 4 ==0) {
					%>
					</tr>
					<tr>
					<% } %>
					<td>
					<!-- 
						<a HREF="javascript:DisplayItemDetails('MainForm', <%=catalog.getItemId()%>)"><img onmouseover="zoom(99,100,'images/BestBuy.JPG','in')" height="200" width="200" src="images/catalog/<%=firstImage%>" TITLE="<%=catalog.getName()%>"></a>
						<a HREF="javascript:DisplayItemDetails('MainForm', <%=catalog.getItemId()%>)"><img style="z-index:0" onmouseover="larger(this,160,120)" onmouseout="smaller(this,160,120) height="200" width="200" src="images/catalog/<%=firstImage%>" TITLE="<%=catalog.getName()%>"></a>
						<a class="MagicZoom" href="images/Xerox_Alto.jpg" onclick="javascript:DisplayItemDetails('MainForm', <%=catalog.getItemId()%>)"><img height="200" width="200" src="images/catalog/<%=firstImage%>"></a>
						<a href="javascript:DisplayItemDetails('MainForm', <%=catalog.getItemId()%>)"><img data-zoomsrc="images/catalog/<%=firstImage%>" id="<%=firstImage%>" height="200" width="200" src="images/catalog/<%=firstImage%>"></a>
						MojoZoom.makeZoomable(document.getElementById("myimage2"), "kendwa_big.jpg",  document.getElementById("myimage2_zoom"),150, 300,true);

						<a href="javascript:DisplayItemDetails('MainForm', <%=catalog.getItemId()%>)">
						  <img
						  onmouseover="MojoZoom.makeZoomable(document.getElementById('<%=firstImage%>'), 'images/catalog/<%=firstImage%>',  null,300, 300,false);" 
						  id="<%=firstImage%>" height="200" width="200" src="images/catalog/<%=firstImage%>">
						</a>	
						<a class="MagicZoom" href="images/catalog/<%=firstImage%>" onclick="javascript:DisplayItemDetails('MainForm', <%=catalog.getItemId()%>)"><img height="200" width="200" src="images/catalog/<%=firstImage%>"></a>
											
						-->
						<a class="MagicZoom" href="../images/catalog/low_<%=firstImage%>" onclick="javascript:DisplayItemDetails('MainForm', <%=catalog.getItemId()%>)"><img height="190" width="150" src="../images/catalog/low_<%=firstImage%>"></a>

						<br/><%=catalog.getName() %>
						<br/>USD&nbsp;<%=catalog.getPrice()%>
						<br/>ID : <%=catalog.getItemId() %>
					</td>
	<% } %>
	</tr>
</table>
</div>
</html:form>


