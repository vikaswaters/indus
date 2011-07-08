package com.indus.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.indus.Emailer;
import com.indus.core.ShoppingCart;
import com.indus.dao.hibernate.Customer;
import com.indus.web.struts.form.ForgetPasswordForm;
import com.indus.web.struts.form.RegisterForm;
import com.indus.web.struts.form.UserLoginForm;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.action.UserPreferenceAction;

public class ForgetPasswordAction extends IndusAction {
	private static final Logger logger = Logger.getLogger(ForgetPasswordAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		 		ActionMessages errors = new ActionMessages();
		 		ForgetPasswordForm objForm = (ForgetPasswordForm) form;
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
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("MAIL_MY_PASSWORD")){
		        	Customer custom = DaoUtilities.getCustomerByID(objForm.getEmailid());
	        		if(custom ==null){
	        			errors.add("login",new ActionMessage("error.emailid.doesnotexist"));
	        			saveErrors(request,errors);
	        			 return (new ActionForward(mapping.getInput()));
	        		}
	        		else{
		        		Emailer email = new Emailer();
		        		StringBuffer sb = new StringBuffer();
		        		sb.append("Yout login credentials for Indusaura\n");
		        		sb.append("Login: ");
		        		sb.append(custom.getEmail());
		        		sb.append("\nPassword: ");
		        		sb.append(custom.getPassword());
		        		email.sendEmail("Password for Indusaura", sb.toString(), custom.getEmail());
		        		request.setAttribute("PASSWORD_MAILED", "");
	        		}
	        		UserLoginForm loginForm = (UserLoginForm) request.getSession().getAttribute("UserLoginForm");
	        		loginForm.setUsertype("2");
		        	objForm.setFormAction(null);
		        	return mapping.findForward("signin");
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

}