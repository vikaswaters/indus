<%@page import="com.indus.IndusConstants"%>

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<%if(IndusConstants.INDUS_LOGIN){ %>
	<logic:redirect forward="userLoginJsp"/>
<%}else{ %>
	<logic:redirect forward="main"/>
<%} %>
<!--  

<logic:redirect forward="signin"/>
-->


<%--

Redirect default requests to Welcome global ActionForward.
By using a redirect, the user-agent will change address to match the path of our Welcome ActionForward. 

--%>
