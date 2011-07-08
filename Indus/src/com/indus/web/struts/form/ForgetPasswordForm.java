package com.indus.web.struts.form;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.indus.IndusUtility;
import com.lbr.web.struts.form.UserPreferenceForm;

public class ForgetPasswordForm extends org.apache.struts.validator.ValidatorForm{
	private static final Logger logger = Logger.getLogger(ForgetPasswordForm.class);

	private String formAction = "";
	//////////////////////////////////
	private String emailid;

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	public ForgetPasswordForm() {
		super();
		//this.countryID = "1";
		
	}
	


	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		if(this.formAction.equals("MAIL_MY_PASSWORD")){ 
			if(emailid==null || emailid.equals("") || !IndusUtility.isValidEmailAddress(emailid))
				actionErrors.add("", new ActionMessage("label.emailid.invalid"));
		}
		
		if (!actionErrors.isEmpty())
			this.setFormAction("");
		return actionErrors;		
	}

	 
}
