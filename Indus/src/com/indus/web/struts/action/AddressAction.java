package com.indus.web.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.indus.SelectionBean;
import com.indus.core.ShoppingCart;
import com.indus.dao.hibernate.Address;
import com.indus.dao.hibernate.Country;
import com.indus.dao.hibernate.Customer;
import com.indus.dao.hibernate.Shipping;
import com.indus.web.struts.form.AddressForm;
import com.indus.web.struts.form.MainForm;
import com.indus.web.struts.form.UserLoginForm;
import com.lbr.dao.specificdao.DaoUtilities;


public class AddressAction  extends IndusAction{
	private static final Logger logger = Logger.getLogger(UserLoginAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{
		 System.out.println("==============ADDRESS Action called ===========");
	 		ActionMessages errors = new ActionMessages();
	 		AddressForm objForm = (AddressForm) form;
	 		ShoppingCart shoppingCart = null;

	  		if(request.getSession().getAttribute("SHOPPING_CART")!=null)
	 			shoppingCart = (ShoppingCart)request.getSession().getAttribute("SHOPPING_CART");


	        //=============================  conditional code =====================

	        if(objForm.getFormAction()!=null && objForm.getFormAction().equals("CANCEL")){
	        	return mapping.findForward("mainJSP");
	        }
	        else if(objForm.getFormAction()!=null && objForm.getFormAction().equals("CREATE_NEW_ACCOUNT")){
	        	/* for some reasons the action is set to CREATE_NEW_ACCOUNT after submission to UserLoginAction
	        	 * Dont  need to saveanything here. New User has a;ready been created in UserLoginAction.  JUST bypass to the shippingaddress.jsp
	        	 */
	        	UserLoginForm loginForm = (UserLoginForm) request.getSession().getAttribute("UserLoginForm");
	        	MainForm mainForm = (MainForm) request.getSession().getAttribute("MainForm");
	        	//objForm.setName(loginForm.getName());
	        	objForm.setCountryID(mainForm.getCountryID());
	        }
	        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CREATE_NEW_SHIPPING_ADDRESS")){
	        	Customer customer = DaoUtilities.getCustomerByIDSmartCall(request, null);
	        	Address  shippingAddress = this.populateShippingAddressData(objForm, null);
	        	customer.setAddressByShipaddressid(shippingAddress);
	        	boolean success = DaoUtilities.createOrUpdateAddress(shippingAddress);
	        	if(success)
	        		request.setAttribute("ADDRESS_SAVED", "");
	        	//if(objForm.getShippingSameAsBilling().equals("1")){ //billing same as shipping
	        		customer.setAddressByAddressid(shippingAddress);
	        		DaoUtilities.updateCustomerByIDSmartCall(request, customer);
	        		populateShippingDropdownList(objForm);
	        		return mapping.findForward("selectshippingoption");
/*	        	}
	        	else if(objForm.getShippingSameAsBilling().equals("0")){ // different  ?? not required as of now
	        		return mapping.findForward("billingaddress");
	        	}*/
	        }
	        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("MODIFY_SHIPPING_ADDRESS")){
	        	Customer existingcustomer = DaoUtilities.getCustomerByIDSmartCall(request, null);
	        	this.populateShippingAddressData(objForm, existingcustomer.getAddressByShipaddressid());
	        	boolean success = DaoUtilities.createOrUpdateAddress(existingcustomer.getAddressByShipaddressid());
	        	if(success)
	        		request.setAttribute("ADDRESS_SAVED", "");
	        	populateShippingDropdownList(objForm);
	        	objForm.setFormAction(null);
	        	// UPDATE the user address
	        	return mapping.findForward("selectshippingoption");
	        }
	        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("USE_CURRENT_SHIPPING_ADDRESS")){
	        	populateShippingDropdownList(objForm);
	        	objForm.setFormAction(null);
	        	return mapping.findForward("selectshippingoption");
	        }
	        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CALCULATE_TOTAL_ORDER_COST")){
	        	objForm.setFormAction(null);
	        	return mapping.findForward("selectshippingoption");
	        }
	        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CONFIRM_SHIPPING_OPTION")){
	        	shoppingCart.setShipping((Shipping)DaoUtilities.staticCache.get("SHIPPINGID_"+objForm.getShippingOptionID()));
	        	return mapping.findForward("reviewandconfirmorder");
	        }

             saveErrors(request,errors);

     		if (!errors.isEmpty())
    		{
    			saveErrors(request, errors);
    			return (mapping.findForward("failure"));
    		}
     		objForm.setFormAction(null);
             return mapping.findForward("shippingaddress");  // success
	       }

	 private void populateShippingDropdownList(AddressForm objForm){
		 	List<SelectionBean> shippingOptions = objForm.getShippingOptionList();
			if(shippingOptions==null)
				shippingOptions =  new ArrayList<SelectionBean>();
			shippingOptions.clear();
			List<Shipping> shippingOptionsCached = (List<Shipping>)DaoUtilities.staticCache.get("ALL_SHIPPING");
			for (Iterator iterator = shippingOptionsCached.iterator(); iterator.hasNext();) {
				Shipping shipp = (Shipping) iterator.next();
				//System.out.println(shipp.getCountry().getCountryid()+"     "+ objForm.getCountryID());
				if(shipp.getCountry().getCountryid().toString().equals(objForm.getCountryID()))
					shippingOptions.add(new SelectionBean(shipp.getShippingid(), shipp.getName()));
			}
			objForm.setShippingOptionList(shippingOptions);
			if(shippingOptions!=null && shippingOptions.size()>0){
				objForm.setShippingOptionID(objForm.getShippingOptionList().get(0).getTargetID()+"");
			}
	 }
	 private Address populateShippingAddressData(AddressForm objForm, Address existingAddress){
		 if(existingAddress == null)
			 existingAddress = new Address();

		 existingAddress.setName(objForm.getName());
		 existingAddress.setLine1(objForm.getAddressline1());
		 existingAddress.setLine2(objForm.getAddressline2());
		 existingAddress.setCity(objForm.getCity());
		 existingAddress.setState(objForm.getState());
		 existingAddress.setZip(new Integer(objForm.getZip()));
		 existingAddress.setCountry((Country)DaoUtilities.staticCache.get("COUNTRYID_"+objForm.getCountryID()));
		 return existingAddress;
	 }

	}






