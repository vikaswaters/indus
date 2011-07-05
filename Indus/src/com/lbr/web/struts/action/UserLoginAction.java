package com.lbr.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.indus.dao.hibernate.Customer;
import com.indus.web.struts.action.IndusAction;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.form.UserLoginForm;


public class UserLoginAction  extends Action{
	private static final Logger logger = Logger.getLogger(UserLoginAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		        ActionMessages errors = new ActionMessages();
		        UserLoginForm objForm = (UserLoginForm) form;
		        String strUserid=objForm.getUserid();
		      	String strPassword=objForm.getPassword();
/*		      	boolean loginStatus = DaoUtilities.checkUserLogin(strUserid, LbrUtility.generaPassword(objForm.getPassword()));
		      	   if(loginStatus==true){
		      		   // ################  Store the user details in the session....it will be  used thruout  ##############
		      		   HttpSession  session = request.getSession();
		      		   Users user = DaoUtilities.getUserByIDSmartCall(request, strUserid);
		      		   LbrAction.setThreadLocalUserValue(user);
		  			   session.setAttribute("USERVO", user);
		  			   logger.info("********* User logged in :"+user.getUserName());
		  			 if(user.getUserpermissions().getBasicModulePermission().booleanValue())
		  				 return mapping.findForward("userHome");   // success for normal users
		  			 else
		  				return mapping.findForward("eventsHome");  // success for Events Entry users
		         } */
		      	boolean loginStatus = DaoUtilities.checkCustomerLogin(strUserid, objForm.getPassword());
		      	//boolean loginStatus = DaoUtilities.checkCustomerLogin(strUserid, IndusUtility.generatePassword(objForm.getPassword()));
		      	   if(loginStatus==true){
		      		   // ################  Store the user details in the session....it will be  used thruout  ##############
					   HttpSession  session = request.getSession();
					   Customer user = DaoUtilities.getCustomerByIDSmartCall(request, strUserid);
					   IndusAction.setThreadLocalUserValue(user);
					   session.setAttribute("ADMIN_USERVO", user);	
					   return mapping.findForward("main");
		      	   }
		      	   else {
		      	   errors.add("login",new ActionMessage("error.login.invalid"));
		             saveErrors(request,errors);
		             return mapping.findForward("failure");
		         }
	       }
	}






