<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page import="com.lbr.dao.hibernate.domain.*"%>   
<%@page import="com.indus.dao.hibernate.*"%>           
          
<LINK rel="stylesheet" type="text/css" href="<html:rewrite page='/css/mojozoom.css'/>">
<LINK rel="stylesheet" type="text/css" href="<html:rewrite page='/css/magiczoom.css'/>">        
<LINK rel="stylesheet" type="text/css" href="<html:rewrite page='/css/style.css'/>">
<LINK rel="stylesheet" type="text/css" href="<html:rewrite page='/css/base.css'/>">
<script type="text/javascript" language="javascript" src="ajax.js"></script>
<script type="text/javascript" language="javascript" src="pages/lbr.js"></script>
<script type="text/javascript" src="pages/magiczoom.js"></script>
<script type="text/javascript" src="pages/mojozoom.js"></script>

<table class="headerTable"> 
		<tr>
			<td align="left" colspan="4" width="100%">
					<h1 style="color: #660033"><font size="6" face="Monotype Corsiva">INDUS AURA</font></h1>	
					<!--  
					<p style="color: #660033"><font size="8" face="Monotype Corsiva">Location Based Recommendation System</font></p>
					-->	
			</td>			     
 		</tr>
 		<% if(true) {%>
          <tr>
          <%-- 
                <td class="headerTableRowData">
                       <html:link styleClass="headerTableRowDataLink" page="/pages/user/userRegister.jsp">Register</html:link>
				</td>
				<td class="headerTableRowData"  colspan="1">
					<html:link styleClass="headerTableRowDataLink" page="/Logout.do"><img height="30" width="130" src="../images/home_btn.jpg" TITLE="Logout"></html:link>
				</td>				

			    <td class="headerTableRowData" colspan="1">
                     <html:link styleClass="headerTableRowDataLink" page="/Main.do"><img height="30" width="130" src="/images/home_btn.jpg" TITLE="Home"></html:link>
				</td>				
				<td class="headerTableRowData"  colspan="1">
					<html:link styleClass="headerTableRowDataLink" page="/pages/user/aboutus.jsp"><img height="30" width="130" src="/images/aboutus_btn.jpg" TITLE="About us"></html:link>
				</td>				
			    <td class="headerTableRowData" colspan="1">
                     <html:link styleClass="headerTableRowDataLink" page="/pages/user/contactus.jsp"><img height="30" width="130" src="/images/contact_btn.jpg" TITLE="Contact Us"></html:link>
				</td>
				--%>
			    <td class="headerTableRowData" colspan="1">
                     <html:link styleClass="headerTableRowDataLink" page="/Main.do">Home</html:link>
				</td>				
				<td class="headerTableRowData"  colspan="1">
					<html:link styleClass="headerTableRowDataLink" page="/pages/user/aboutus.jsp">About</html:link>
				</td>				
			    <td class="headerTableRowData" colspan="1">
                     <html:link styleClass="headerTableRowDataLink" page="/pages/user/contactus.jsp">Contact</html:link>
				</td>				
        </tr>
         <%} else {%>
          <tr>
		          <% if(false) {%>
					    <td class="headerTableRowData">
							<html:link styleClass="headerTableRowDataLink" page="/UserPreference.do">Home</html:link>
						</td>
			<%-- 			
		                <td class="headerTableRowData">
		                       <html:link styleClass="headerTableRowDataLink" page="/pages/user/userRegister.jsp">Register</html:link>
						</td>
			--%>									
		                <td class="headerTableRowData" colspan="2">
							 	<html:link styleClass="headerTableRowDataLink" page="/UserRegister.do?action=update">Update Profile</html:link> 
						</td>
						<td class="headerTableRowData">
		                      		<html:link styleClass="headerTableRowDataLink" page="/Logout.do">Logout</html:link> 
						</td>						
				  <% } else { %>		
		                <td class="headerTableRowData"  colspan="2">
							 	<html:link styleClass="headerTableRowDataLink" page="/UserRegister.do?action=update">Update Profile</html:link> 
						</td>
						<td class="headerTableRowData" colspan="2">
		                      		<html:link styleClass="headerTableRowDataLink" page="/Logout.do">Logout</html:link> 
						</td>
				 <% } %>	
        </tr>        
         <% } %>
       
		
		<tr> <td colspan="4">&nbsp;</td></tr>
</table>
  
	  
