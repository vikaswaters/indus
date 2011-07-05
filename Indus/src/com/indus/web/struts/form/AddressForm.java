package com.indus.web.struts.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.indus.IndusConstants;
import com.indus.IndusUtility;
import com.indus.SelectionBean;
import com.indus.TARGET_TYPE;

public class AddressForm extends org.apache.struts.validator.ValidatorForm{
	private static final Logger logger = Logger.getLogger(UserLoginForm.class);

	//private String formUserAction = "";
	private String formAction = "";
	private String name;
	private String addressline1;
	private String addressline2;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String  shippingSameAsBilling;

	private List<SelectionBean> shippingOptionList;
	private String shippingOptionID;
	private String countryID;
	private List<SelectionBean> countryList;

	

	public AddressForm() {
		super();
		this.populateCountryDropdownList();
		
	}
	
	public List<SelectionBean> getShippingOptionList() {
		return shippingOptionList;
	}

	public void setShippingOptionList(List<SelectionBean> shippingOptionList) {
		this.shippingOptionList = shippingOptionList;
	}

	public String getShippingOptionID() {
		return shippingOptionID;
	}

	public void setShippingOptionID(String shippingOptionID) {
		this.shippingOptionID = shippingOptionID;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<SelectionBean> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<SelectionBean> countryList) {
		this.countryList = countryList;
	}

	public String getShippingSameAsBilling() {
		return shippingSameAsBilling;
	}

	public void setShippingSameAsBilling(String shippingSameAsBilling) {
		this.shippingSameAsBilling = shippingSameAsBilling;
	}
/*
	public String getFormUserAction() {
		return formUserAction;
	}

	public void setFormUserAction(String formAction) {
		this.formUserAction = formAction;
	}*/

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}
	 private void populateCountryDropdownList(){
		 	List<SelectionBean> countries = this.getCountryList();
			if(countries==null)
				this.setCountryList(IndusUtility.populateCountryDropdownList());
			else
				return;
	 }	 

	}



