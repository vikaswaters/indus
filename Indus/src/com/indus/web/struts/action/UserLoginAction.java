package com.indus.web.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.indus.SelectionBean;
import com.indus.core.SelectedItem;
import com.indus.core.ShoppingCart;
import com.indus.dao.hibernate.Country;
import com.indus.dao.hibernate.Customer;
import com.indus.web.struts.form.AddressForm;
import com.indus.web.struts.form.UserLoginForm;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.action.UserPreferenceAction;

public class UserLoginAction extends IndusAction {
	private static final Logger logger = Logger.getLogger(UserPreferenceAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		 		ActionMessages errors = new ActionMessages();
		 		UserLoginForm objForm = (UserLoginForm) form;
		 		System.out.println("======== Action: "+objForm.getFormAction()); 
		 		ShoppingCart shoppingCart = null;
		 		if(request.getSession().getAttribute("SHOPPING_CART")!=null)
		 			shoppingCart = (ShoppingCart)request.getSession().getAttribute("SHOPPING_CART");

		        String primarySelection = request.getParameter("category");
		        if(primarySelection == null)
		        	primarySelection = "1";
		        logger.debug("User selected CatID="+primarySelection);

		        //=============================  conditional code =====================

		        if(objForm.getFormAction()!=null && objForm.getFormAction().equals("CANCEL")){
		        	return mapping.findForward("mainJSP");
		        }
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equals("SIGNIN")){
		        	if(objForm.getUsertype().equals("1")){ //new user
		        		if(objForm.getEmailid()!=null && !objForm.getEmailid().equals("") && DaoUtilities.getCustomerByID(objForm.getEmailid()) !=null){
		        			errors.add("login",new ActionMessage("error.invalidUsername.invalid"));
		        			saveErrors(request,errors);
		        		}
		        		else
		        			return mapping.findForward("register");
		        	}
		        	else if(objForm.getUsertype().equals("2")){ // existing user
				        String emailid=objForm.getEmailid();
				      	String strPassword=objForm.getPassword();
				      	boolean loginStatus = DaoUtilities.checkCustomerLogin(emailid, objForm.getPassword());
				      	//boolean loginStatus = DaoUtilities.checkCustomerLogin(strUserid, IndusUtility.generatePassword(objForm.getPassword()));
				      	   if(loginStatus==true){
				      		   // ################  Store the user details in the session....it will be  used thruout  ##############
							   HttpSession  session = request.getSession();
							   Customer user = DaoUtilities.getCustomerByIDSmartCall(request, emailid);
							   shoppingCart.setCustomer(user);
							   IndusAction.setThreadLocalUserValue(user);
							   session.setAttribute("USERVO", user);
							   session.setAttribute("EXISTING_USER_LOGGED_IN", user);
							   AddressForm prefForm = (AddressForm) session.getAttribute("AddressForm");
							   if(prefForm==null){
								   prefForm = new AddressForm();
								   session.setAttribute("AddressForm", prefForm);
							   }
							   prefForm.setName(user.getAddressByShipaddressid().getName());
							   prefForm.setAddressline1(user.getAddressByShipaddressid().getLine1());
							   prefForm.setAddressline2(user.getAddressByShipaddressid().getLine2());
							   prefForm.setCity(user.getAddressByShipaddressid().getCity());
							   prefForm.setState(user.getAddressByShipaddressid().getState());
							   prefForm.setZip(user.getAddressByShipaddressid().getZip().toString());
							   if(user.getPhone()!=null)
								   prefForm.setPhone(user.getPhone().toString());								
							   prefForm.setCountryID(user.getAddressByShipaddressid().getCountry().getCountryid().toString());
								logger.info("********* User logged in :"+user.getName());
				         } else {
				      	     errors.add("login",new ActionMessage("error.login.invalid"));
				             saveErrors(request,errors);
				             return (new ActionForward(mapping.getInput()));
				         }		        		
		        		return mapping.findForward("shippingaddressAction");
		        	}
		        }
/*		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CREATE_NEW_ACCOUNT")){
		        	Customer customer = this.populateCustomerData(objForm, null);
		        	boolean status = DaoUtilities.updateCustomerByIDSmartCall(request, customer);
		        	if(status){
		        		shoppingCart.setCustomer(customer);
		        	}
		        	objForm.setFormAction(null);
		        	return mapping.findForward("shippingaddressAction");

		        }*/
		        
	             saveErrors(request,errors);

	     		if (!errors.isEmpty())
	    		{
	    			saveErrors(request, errors);
	    			//return mapping.findForward((mapping.getInput()));
	    			return mapping.findForward("failure");
	    		}
	     		objForm.setFormAction(null);
	             return mapping.findForward("signin");  // success
	 }

	 private void addSelectedItemToShoppingCart(UserLoginForm objForm, ShoppingCart shoppingCart){
     	SelectedItem selectedItem = new SelectedItem(DaoUtilities.catalogCache.get(objForm.getSelectedItemID()), new Integer(objForm.getQuantity()), objForm.getSizeID());
    	shoppingCart.getSelectedItems().add(selectedItem);
    	float currentItemcost = selectedItem.getItem().getPrice() * new Float(objForm.getQuantity());
    	shoppingCart.setTotalItemCost(shoppingCart.getTotalItemCost()+ currentItemcost);
    	shoppingCart.setTotalItems(shoppingCart.getTotalItems() + selectedItem.getQuantity());
    	objForm.setFormAction(null);

	 }
	 private void populateCountryDropdownList(UserLoginForm objForm){
	 	List<SelectionBean> countries = objForm.getCountryList();
		if(countries==null){
			countries =  new ArrayList<SelectionBean>();
			List<Country> countriesCached = (List<Country>)DaoUtilities.staticCache.get("ALL_COUNTRIES");
			for (Iterator iterator = countriesCached.iterator(); iterator.hasNext();) {
				Country country = (Country) iterator.next();
				countries.add(new SelectionBean(country.getCountryid(), country.getName()));
			}
			objForm.setCountryList(countries);
		}else
			return;
	 }

}