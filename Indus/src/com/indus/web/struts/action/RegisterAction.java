package com.indus.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.indus.Emailer;
import com.indus.core.ShoppingCart;
import com.indus.dao.hibernate.Customer;
import com.indus.web.struts.form.AddressForm;
import com.indus.web.struts.form.RegisterForm;
import com.indus.web.struts.form.UserLoginForm;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.action.UserPreferenceAction;

public class RegisterAction extends IndusAction {
	private static final Logger logger = Logger.getLogger(UserPreferenceAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		 		ActionMessages errors = new ActionMessages();
		 		RegisterForm objForm = (RegisterForm) form;
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
		        if(objForm.getFormAction()!=null && objForm.getFormAction().equals("")){
		        	UserLoginForm userLoginForm = (UserLoginForm) request.getSession().getAttribute("UserLoginForm");
		        	objForm.setEmailid(userLoginForm.getEmailid());
		        	objForm.setEmailid2(userLoginForm.getEmailid());
		        	return mapping.findForward("register");
		        }		        
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CREATE_NEW_ACCOUNT")){
	        		if(objForm.getEmailid()!=null && !objForm.getEmailid().equals("") && DaoUtilities.getCustomerByID(objForm.getEmailid()) !=null){
	        			errors.add("login",new ActionMessage("error.invalidUsername.invalid"));
	        			saveErrors(request,errors);
	        		}else{	
		        		Customer customer = this.populateCustomerData(objForm, null);
			        	boolean status = DaoUtilities.updateCustomerByIDSmartCall(request, customer);
			        	if(status){
			        		shoppingCart.setCustomer(customer);
			        	}
			        	objForm.setFormAction(null);
			        	return mapping.findForward("shippingaddressAction");
	        		}
		        }
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("MAIL_MY_PASSWORD")){
		        	Customer custom = DaoUtilities.getCustomerByID(objForm.getEmailid());
	        		if(custom !=null){
	        			errors.add("login",new ActionMessage("error.invalidUsername.invalid"));
	        			saveErrors(request,errors);
	        		}
	        		else{
		        		Emailer email = new Emailer();
		        		StringBuffer sb = new StringBuffer();
		        		sb.append("Login: ");
		        		sb.append(custom.getEmail());
		        		sb.append("<br/>");
		        		sb.append("Password: ");
		        		sb.append(custom.getPassword());
		        		email.sendEmail("Password for Indusaura", sb.toString(), custom.getEmail());
		        		request.setAttribute("PASSWORD_MAILED", "");
	        		}
		        	objForm.setFormAction(null);
		        	return mapping.findForward("forgetpassword");
		        }		        
	             saveErrors(request,errors);

	     		if (!errors.isEmpty())
	    		{
	    			saveErrors(request, errors);
	    			return (new ActionForward(mapping.getInput()));
	    		}
	     		objForm.setFormAction(null);
	            return mapping.findForward("signin");  // success
	 }


	 private Customer populateCustomerData(RegisterForm objForm, Customer existingCustomer){
		 if(existingCustomer == null)
			 existingCustomer = new Customer();
		 
		 existingCustomer.setName(objForm.getName());
		 existingCustomer.setEmail(objForm.getEmailid());
		 if(objForm.getPhone()!=null && objForm.getPhone()!="")
			 existingCustomer.setPhone(new Long(objForm.getPhone()));
		 existingCustomer.setPassword(objForm.getPassword());
		 if(objForm.getMobile()!=null && !objForm.getMobile().equals(""))
			 existingCustomer.setMobile(new Long(objForm.getMobile()));
		 return existingCustomer;
	 } 
}