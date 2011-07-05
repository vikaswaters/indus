package com.lbr.web.struts.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.context.ApplicationContext;
import org.apache.log4j.Logger;
import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.SubcategoryWrapper;
import com.lbr.core.EventRecommendationVO;
import com.lbr.core.Recommendable;
import com.lbr.core.RecommendationEngine;
import com.lbr.core.Recommendations;
import com.lbr.dao.hibernate.domain.Category;
import com.lbr.dao.hibernate.domain.Events;
import com.lbr.dao.hibernate.domain.Locations;
import com.lbr.dao.hibernate.domain.Subcategory;
import com.lbr.dao.hibernate.domain.Users;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.utils.ApplicationContextProvider;
import com.lbr.web.struts.form.UserPreferenceForm;

// USer Preferences action class
public class UserPreferenceAction extends Action {
	private static final Logger logger = Logger.getLogger(UserPreferenceAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		 		ActionMessages errors = new ActionMessages();
		 		UserPreferenceForm objForm = (UserPreferenceForm) form;

				//Users user = ((Users)request.getSession().getAttribute("USERVO"));
				String currUserID = ((Users)request.getSession().getAttribute("USERVO")).getUserName(); // MUST BE AVAIL  to proceed further

		        String primarySelection = request.getParameter("category");
		        if(primarySelection == null)
		        	primarySelection = "1";
		        logger.debug("User selected CatID="+primarySelection);

/*		        String ajax = request.getParameter("ajax");
		        if(ajax!=null && ajax.equals("yes")){  // =====================  AJAX call ONLY
		        	logger.debug("**** AJAX request received for Subcategory change **********");
		        	objForm.populateSecondaryDropdown(primarySelection);
			        LbrUtility.sendAjaxResponse(objForm.toXml(), request, response);
			        //logger.debug("AJAX response XML: "+objForm.toXml());
		        	return null;
		        }				
				*/
			
		        //=============================  conditional code =====================
		     // Delete selected userpreferences
		     // Show selected userpreferences
/*
		        System.out.print("User selected SubCatIDs: ");
		        for (int i = 0; i < userSubCategorySelection.length; i++) {
		        	 System.out.print(userSubCategorySelection[i]+"  ");
				}
		        logger.debug();*/

	      	    // errors.add("login",new ActionMessage("error.login.invalid"));
	             saveErrors(request,errors);
	      	     request.setAttribute("PrefSaved", true);

	     		if (!errors.isEmpty())
	    		{
	    			saveErrors(request, errors);
	    			return (mapping.findForward("failure"));
	    		}
	             return mapping.findForward("userHomeJSP");  // success
	 }


}


