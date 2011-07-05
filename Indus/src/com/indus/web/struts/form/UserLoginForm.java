package com.indus.web.struts.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.indus.IndusConstants;
import com.indus.IndusUtility;
import com.indus.SelectionBean;
import com.indus.TARGET_TYPE;
import com.indus.dao.hibernate.Catalog;
import com.lbr.SubcategoryWrapper;
import com.lbr.Vicinity;
import com.lbr.core.EventRecommendationVO;
import com.lbr.dao.hibernate.domain.Category;
import com.lbr.dao.hibernate.domain.Subcategory;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.form.UserPreferenceForm;

public class UserLoginForm extends ActionForm{
	private static final Logger logger = Logger.getLogger(UserPreferenceForm.class);

	private List<Catalog> catalogList;
	private String formAction = "";
	private String previousformAction = "";
	private String selectedItemID = "";
	private List<SelectionBean> targetList;
	private List<SelectionBean> shippingOptionList;
	private String targetID;
	private String shippingOptionID;
	private List<SelectionBean> countryList;
	
	private String sizeID;
	private List<SelectionBean> availSizeList;
	private String quantity;
	private String currcost;
	
	//////////////////////////////////
	private String emailid;
	private String usertype;
	private String password;
/*	private String name;
	private String emailid2;
	private String phone;   
	private String mobile; */
	private String password2;
	
	
	// address
/*	private String addressline1;
	private String addressline2;
	private String zip;
	private String state;
	private String countryID;
	private String city;*/
	
	////////////////////////////////////////////
	private String category;
	private String[] subcategory;
	private String categories;
	private String subCategories;
	private List<Category> categoryList;
	private List<Subcategory> subcategoryList;
	//private Map<Integer, List<Subcategory>> cacheSubcategoryList;
	//private List<Subcategory> userPreferences;
	private List<SubcategoryWrapper> userPreferencesWithLevels;
	private List<EventRecommendationVO> recommendations;
	private String startDate;
	private String endDate;
	private String currentLocationStr = "Not specified";

	private int vicinitypolicyID;
	private List<Vicinity> vicinityPolicyList;
	private String[] subcatLevels;

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	public UserLoginForm() {
		super();
		targetList = new ArrayList<SelectionBean>();
		targetList.add(new SelectionBean(1, TARGET_TYPE.HIS.getTARGET_TYPEName()));
		targetList.add(new SelectionBean(2, TARGET_TYPE.HER.getTARGET_TYPEName()));
		targetList.add(new SelectionBean(3, TARGET_TYPE.SWEET_HOME.getTARGET_TYPEName()));
		this.setTargetID(IndusConstants.DEFAULT_CATALOG_TARGET+"");
		this.quantity ="1";
		usertype = "1";
		//this.countryID = "1";
		
	}
	
/*	public String getMobile() {
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
	}*/

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

	public List<SelectionBean> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<SelectionBean> countryList) {
		this.countryList = countryList;
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

	public String getCurrcost() {
		return currcost;
	}

	public void setCurrcost(String currcost) {
		this.currcost = currcost;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSizeID() {
		return sizeID;
	}

	public void setSizeID(String sizeID) {
		this.sizeID = sizeID;
	}

	public List<SelectionBean> getAvailSizeList() {
		return availSizeList;
	}

	public void setAvailSizeList(List<SelectionBean> availSizeList) {
		this.availSizeList = availSizeList;
	}

	public String getTargetID() {
		return targetID;
	}

	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}


	public List<SelectionBean> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<SelectionBean> targetList) {
		this.targetList = targetList;
	}

	public String getSelectedItemID() {
		return selectedItemID;
	}

	public void setSelectedItemID(String selectedItemID) {
		this.selectedItemID = selectedItemID;
	}

	public List<Catalog> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<Catalog> catalogList) {
		this.catalogList = catalogList;
	}

	public String getCurrentLocationStr() {
		return currentLocationStr;
	}

	public void setCurrentLocationStr(String currentLocationID) {
		this.currentLocationStr = currentLocationID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<EventRecommendationVO> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<EventRecommendationVO> recommendations) {
		this.recommendations = recommendations;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String[] getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String[] subcategory) {
		this.subcategory = subcategory;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getCategories() {
		return categories;
	}


	public void setCategories(String categories) {
		this.categories = categories;
	}


	public String getSubCategories() {
		return subCategories;
	}


	public void setSubCategories(String subCategories) {
		this.subCategories = subCategories;
	}


	public List<Category> getCategoryList() {
		return categoryList;
	}


	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}


	public List<Subcategory> getSubcategoryList() {
		return subcategoryList;
	}

	public void setSubcategoryList(List<Subcategory> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}

/*	public Map<Integer, List<Subcategory>> getCacheSubcategoryList() {
		return cacheSubcategoryList;
	}

	public void setCacheSubcategoryList(Map<Integer, List<Subcategory>> cacheSubcategoryList) {
		this.cacheSubcategoryList = cacheSubcategoryList;
	}*/

/*	  public List<Subcategory> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(List<Subcategory> userPreferences) {
		this.userPreferences = userPreferences;
	}*/

	
	public int getVicinitypolicyID() {
		return vicinitypolicyID;
	}

	public List<SubcategoryWrapper> getUserPreferencesWithLevels() {
		return userPreferencesWithLevels;
	}

	public void setUserPreferencesWithLevels(
			List<SubcategoryWrapper> userPreferencesWithLevels) {
		this.userPreferencesWithLevels = userPreferencesWithLevels;
	}

	public void setVicinitypolicyID(int vicinitypolicyID) {
		this.vicinitypolicyID = vicinitypolicyID;
	}

	public List<Vicinity> getVicinityPolicyList() {
		return vicinityPolicyList;
	}

	public void setVicinityPolicyList(List<Vicinity> vicinityPolicyList) {
		this.vicinityPolicyList = vicinityPolicyList;
	}

	public String[] getSubcatLevels() {
		return subcatLevels;
	}

	public void setSubcatLevels(String[] subcatLevels) {
		this.subcatLevels = subcatLevels;
	}

	public String toXml() {
		    StringBuffer xml = new StringBuffer();
		    xml.append("<?xml version=\"1.0\"?>\n");
		    xml.append("<lbr generated=\""+System.currentTimeMillis()+"\">\n");
		    xml.append("<subcategory>\n");
		    for (Iterator iterator = subcategoryList.iterator(); iterator.hasNext();) {
				Subcategory data = (Subcategory) iterator.next();
				String catID = data.getSubCatId().toString();
				String catName = data.getSubCatName();
			      xml.append("<name>");
			      xml.append(catName);
			      xml.append("</name>\n");
			      xml.append("<catid>");
			      xml.append(catID);
			      xml.append("</catid>\n");
		    }
		    xml.append("</subcategory>\n");
		    xml.append("</lbr>\n");
    	    return xml.toString();
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
		
		if(this.formAction.equals("SIGNIN")){    
			if (usertype!=null && usertype.equals("2")) { // existing user
				if(emailid==null || emailid.equals("")|| !IndusUtility.isValidEmailAddress(emailid))
					actionErrors.add("", new ActionMessage("label.emailid.invalid"));
				if(password==null || password.equals(""))
					actionErrors.add("", new ActionMessage("label.password.blank"));
			}

		}

		if (!actionErrors.isEmpty())
			this.setFormAction("");

		return actionErrors;
	}

/*	 public void populateSecondaryDropdown(String primarySelectionCatID){
		 //Map<Integer, List<Subcategory>> categoryNSubCubcategoryCache = DaoUtilities.categoryNSubCubcategoryCache;
		 List<Subcategory> subcategoryList  = null;
		 if (((Map)this.getCacheSubcategoryList()) == null){
			 DaoUtilities.categoryNSubCubcategoryCache = new HashMap<Integer, List<Subcategory>>();
			 this.setCacheSubcategoryList(DaoUtilities.categoryNSubCubcategoryCache);
		 }
		 else
			 DaoUtilities.categoryNSubCubcategoryCache = this.getCacheSubcategoryList();

		 int selection = new Integer(primarySelectionCatID);
		 if (DaoUtilities.categoryNSubCubcategoryCache.get(primarySelectionCatID) == null)
			 subcategoryList = DaoUtilities.getAllSubCategoryForCatID(new Integer(selection));

		 this.setSubcategoryList(subcategoryList);
		 DaoUtilities.categoryNSubCubcategoryCache.put(new Integer(primarySelectionCatID), subcategoryList);
		 this.setCategory(primarySelectionCatID);
	 }*/

	 public void populateSecondaryDropdown(String primarySelectionCatID){
		 List<Subcategory> subcategoryList  = null;
		 if (DaoUtilities.categoryNSubCubcategoryCache == null){
			 DaoUtilities.categoryNSubCubcategoryCache = new HashMap<Integer, List<Subcategory>>();
		 }

		 Integer selection = new Integer(primarySelectionCatID);
		 if(DaoUtilities.categoryNSubCubcategoryCache.get(selection) == null){ // make DB call ONLY if SubCategoryList is not available, then cache it
			 subcategoryList = DaoUtilities.getAllSubCategoryForCatID(new Integer(selection));
			 DaoUtilities.categoryNSubCubcategoryCache.put(selection, subcategoryList);
		 }else{
			 subcategoryList = DaoUtilities.categoryNSubCubcategoryCache.get(selection);
		 }
		 this.setSubcategoryList(subcategoryList);
		 this.setCategory(primarySelectionCatID);
	 }
	 
}
