package com.indus.web.struts.form;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.indus.IndusUtility;
import com.lbr.web.struts.form.UserPreferenceForm;

public class RegisterForm extends org.apache.struts.validator.ValidatorForm{
	private static final Logger logger = Logger.getLogger(UserPreferenceForm.class);

	private String formAction = "";
	private String previousformAction = "";
	//////////////////////////////////
	private String emailid;
	private String usertype;
	private String password;
	private String name;
	private String emailid2;
	private String phone;   
	private String mobile; 
	private String password2;
	
	
	////////////////////////////////////////////

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	public RegisterForm() {
		super();
		//this.countryID = "1";
		
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid2() {
		return emailid2;
	}

	public void setEmailid2(String emailid2) {
		this.emailid2 = emailid2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPreviousformAction() {
		return previousformAction;
	}

	public void setPreviousformAction(String previousformAction) {
		this.previousformAction = previousformAction;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		if(this.formAction.equals("CREATE_NEW_ACCOUNT")){ 
		      if(phone!=null && phone!=""){
		    	  try{
		    		  new Long(phone);
		    	  }catch (Exception e) {
		    		  actionErrors.add("password", new ActionMessage("error.register.mobilno.invalid"));
				}
		      }
			 if (!password.equals(password2)) {
				actionErrors.add("password", new ActionMessage("error.updateprofile.password.retype.dissimilar"));
			 }
			if(emailid==null || emailid.equals("") || !IndusUtility.isValidEmailAddress(emailid))
					actionErrors.add("", new ActionMessage("label.emailid.invalid"));
		}
		
		if (!actionErrors.isEmpty())
			this.setFormAction("");
		return actionErrors;		
	}

	 
}
