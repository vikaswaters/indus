<%@page import="com.lbr.web.struts.form.UserPreferenceForm"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<tr>
		<td><b>Full Name:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="name" size="30" maxlength="30"></html:text></td>
	</tr>
	<tr>
		<td><b>Address Line1:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="addressline1" size="30" maxlength="50"></html:text></td>
	</tr>
	<tr>
		<td><b>Address Line2: </b></td>
		<td class="alignleft"><html:text property="addressline2" size="30" maxlength="50"></html:text>(optional)</td>
	</tr>	
	<tr>
		<td><b>City:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="city" size="30" maxlength="30"></html:text></td>
	</tr>		
	<tr>
		<td><b>State/Province/Region:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="state" size="30" maxlength="30"></html:text></td>
	</tr>	
	<tr>
		<td><b>ZIP:<font color="#FF0000">*</font></b></td>
		<td class="alignleft"><html:text property="zip" size="8" maxlength="7"></html:text></td>
	</tr>		
	<tr>
		<td><b>Country:<font color="#FF0000">*</font></b></td>
		<td class="alignleft">
		<html:select property="countryID">
				<html:optionsCollection name="AddressForm" property="countryList" label="targetName" value="targetID" />
		</html:select>		
		</td>
	</tr>

