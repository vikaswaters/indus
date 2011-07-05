package com.indus.web.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.indus.Emailer;
import com.indus.IndusUtility;
import com.indus.SelectionBean;
import com.indus.core.SelectedItem;
import com.indus.core.ShoppingCart;
import com.indus.dao.hibernate.Catalog;
import com.indus.dao.hibernate.Orderitems;
import com.indus.dao.hibernate.OrderitemsId;
import com.indus.dao.hibernate.Payment;
import com.indus.dao.hibernate.Shipping;
import com.indus.web.struts.form.MainForm;
import com.lbr.dao.specificdao.DaoUtilities;
import com.lbr.web.struts.action.UserPreferenceAction;

public class MainAction extends IndusAction {
	private static final Logger logger = Logger.getLogger(UserPreferenceAction.class);

	 public ActionForward execute(
			    ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response) throws Exception{

		 		ActionMessages errors = new ActionMessages();
		 		MainForm objForm = (MainForm) form;

		 		List<Integer> arrls = new ArrayList<Integer>();
		 		arrls.add(new Integer(1));
		 		arrls.add(new Integer(2));
		 		IndusUtility.TestReferences(arrls);
		 		
				//Users user = ((Users)request.getSession().getAttribute("USERVO"));
		 		
        		//Emailer emailer = new Emailer();
        		//emailer.sendEmail("Order confirmation", "Order details", "vikazsinha@gmail.com");
		 		System.out.println("==============MainAction called ===========");
		 		ShoppingCart shoppingCart = null;
		 		if(request.getSession().getAttribute("SHOPPING_CART")!=null)
		 			shoppingCart = (ShoppingCart)request.getSession().getAttribute("SHOPPING_CART");
		 		
		        String primarySelection = request.getParameter("category");
		        if(primarySelection == null)
		        	primarySelection = "1";
		        logger.debug("User selected CatID="+primarySelection);
		        
		        List<Catalog> catalogs = DaoUtilities.getAllCatelogForTarget(new Integer(objForm.getTargetID()));
		        int size = catalogs.size();
		        objForm.setCatalogList(catalogs);
		        
/*		        for (Iterator iterator = catalogs.iterator(); iterator.hasNext();) {
					Catalog catalog = (Catalog) iterator.next();
					catalog.getIcon();
					
				}*/
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

		        if(objForm.getFormAction()!=null && objForm.getFormAction().equals("CANCEL")){
		        	objForm.setFormAction(null);
		        	return mapping.findForward("mainJSP");
		        }		        
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equals("SHOW_DETAILS")){
		        	objForm.setFormAction(null);
		        	String selectedItemID = objForm.getSelectedItemID();
		        	Catalog catalog = DaoUtilities.catalogCache.get(selectedItemID);
		        	objForm.setPreviousformAction(objForm.getFormAction());
			        return mapping.findForward("showItemDetails");
		        }
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("ADD_TO_CART")){
		        	if(shoppingCart==null){
		        		shoppingCart= new ShoppingCart();
		        		request.getSession().setAttribute("SHOPPING_CART", shoppingCart);
		        	}
		        	if(true){
		        		Catalog catalog = DaoUtilities.catalogCache.get(objForm.getSelectedItemID());
		        		List<String> availSizesStr = IndusUtility.createAvailableSizeListForCatelog(catalog);
		        		List<SelectionBean> availSizes = new ArrayList<SelectionBean>();
		        		int count = 1;
		        		for (Iterator iterator = availSizesStr.iterator(); iterator.hasNext();) {
							String string = (String) iterator.next();
							availSizes.add(new SelectionBean(count++, string));
						}
		        		objForm.setAvailSizeList(availSizes);
		        	}
		        	//objForm.setFormAction(null);
		        	return mapping.findForward("showItemDetails");
		        }
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("ADDITEM_AND_CHECKOUT")){
		        	addSelectedItemToShoppingCart(objForm, shoppingCart);
		        	//this.populateCountryDropdownList(objForm);
		        	//this.populateShippingDropdownList(objForm);
		        	objForm.setPreviousformAction(objForm.getFormAction());
		        	//objForm.setFormAction(null);
		        	return mapping.findForward("preparecheckout");
		        }
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CHECKOUT")){
		        	//this.populateCountryDropdownList(objForm);
		        	//this.populateShippingDropdownList(objForm);
		        	objForm.setPreviousformAction(objForm.getFormAction());
		        	objForm.setFormAction(null);
		        	return mapping.findForward("preparecheckout");
		        }	
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("GET_SHIPPING_OPTIONS_FOR_COUNTRY")){
		        	//this.populateShippingDropdownList(objForm);
		        	objForm.setFormAction(null);
		        	return mapping.findForward("checkout");
		        }			       
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("SELECT_SHIPPING_OPTION")){
		        	objForm.setFormAction(null);
		        	// TODO..
		        }		        
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("DELETE_SELECTED_ITEM")){
		        	List<SelectedItem> selectedItems = shoppingCart.getSelectedItems();
		        	SelectedItem itemToBeDeleted = null;
		        	for (Iterator iterator = selectedItems.iterator(); iterator.hasNext();) {
						itemToBeDeleted = (SelectedItem) iterator.next();
						if(itemToBeDeleted.getItem().getItemId().equals(new Integer(objForm.getSelectedDeleteItemID())))
							break;
					}
		        	float currentItemcost = itemToBeDeleted.getItem().getPrice() * new Float(itemToBeDeleted.getQuantity());
		        	shoppingCart.setTotalItemCost(shoppingCart.getTotalItemCost()- currentItemcost);
		        	shoppingCart.setTotalItems(shoppingCart.getTotalItems() - itemToBeDeleted.getQuantity());
		        	selectedItems.remove(itemToBeDeleted);
		        	//return mapping.findForward("mainJSP");
		        	if(objForm.getPreviousformAction()!=null){
			        	if(objForm.getPreviousformAction().equalsIgnoreCase("CHECKOUT") || objForm.getPreviousformAction().equalsIgnoreCase("ADDITEM_AND_CHECKOUT"))
			        		return mapping.findForward("checkout");
			        	else if(objForm.getPreviousformAction().equalsIgnoreCase("SHOW_DETAILS"))
			        		return mapping.findForward("showItemDetails");
		        	}
		        		
		        }			        
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("ADDITEM_AND_SHOPMORE")){
		        	addSelectedItemToShoppingCart(objForm, shoppingCart);
		        	objForm.setQuantity("1");
		        	//return mapping.findForward("mainJSP");
		        	
		        }		       
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("PROCEED_TO_SIGNIN")){
		        	objForm.setFormAction(null);
		        	return mapping.findForward("signin");
		        	
		        }
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CHANGE_TARGET_CATALOG")){
			        List<Catalog> catalogsForTarget = DaoUtilities.getAllCatelogForTarget(new Integer(objForm.getTargetID()));
			        int sizex = catalogsForTarget.size();
			        objForm.setCatalogList(catalogsForTarget);		        	
		        }
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CONFIRM_ORDER")){
		        	objForm.setFormAction(null);
		        	request.setAttribute("ORDER_CONFIRMED", "");
		        	return mapping.findForward("reviewandconfirmorder");
		        }		        
		        else if(objForm.getFormAction()!=null && objForm.getFormAction().equalsIgnoreCase("CONFIRM_ORDER_AFTER_PAYMENT_SUCCEEDED")){
		        	Shipping ship= shoppingCart.getShipping();
		        	float shippingcost = ship.getCost()+ship.getDuty()+ship.getTaxes();
		        	float totalOrderCost = shippingcost + shoppingCart.getTotalItemCost();
		        	shoppingCart.getOrder().setAmount(totalOrderCost);
		        	shoppingCart.getOrder().setOrderdate(new Date());
		        	Payment payment = new Payment();
		        	payment.setAmount(totalOrderCost);
		        	payment.setPaymentdetails("XYZ");
		        	payment.setStatus(0);
		        	payment.setType(0);
		        	boolean statusPayment = DaoUtilities.createOrUpdatePayment(payment);
		        	
		        	shoppingCart.getOrder().setPayment(payment);
		        	shoppingCart.getOrder().setStatus(0);
		        	boolean statusOrder = DaoUtilities.createNewOrder(shoppingCart.getOrder());
		        	
		        	//now add the individual items inthe order 
		        	List<SelectedItem> selectedItems = shoppingCart.getSelectedItems();
		        	for (Iterator iterator = selectedItems.iterator(); iterator.hasNext();) {
						SelectedItem selectedItem = (SelectedItem) iterator.next();
						Orderitems anItem = new Orderitems();
						OrderitemsId itemid = new OrderitemsId();
						itemid.setItemId(selectedItem.getItem().getItemId());
						itemid.setColor(selectedItem.getColor());
						itemid.setSize(selectedItem.getSize());
						itemid.setOrderid(shoppingCart.getOrder().getOrderid());
						itemid.setQuantity(selectedItem.getQuantity());
						anItem.setId(itemid);
						boolean statusOrderitem = DaoUtilities.createNewOrderItem(anItem);
					}
		        	// TODO ..
		        	//send mail to user. ..also check if all the items are in STOCK before finializing order.
	        		Emailer email = new Emailer();
	        		email.sendEmail("Order confirmation", "Order details for your shopping at Indusaura....(TODO)", shoppingCart.getCustomer().getEmail());
		        	objForm.setFormAction(null);
		        	return mapping.findForward("thankyou");
		        	
		        }		        
	      	    // errors.add("login",new ActionMessage("error.login.invalid"));
	             saveErrors(request,errors);
	      	     request.setAttribute("PrefSaved", true);

	     		if (!errors.isEmpty())
	    		{
	    			saveErrors(request, errors);
	    			return (mapping.findForward("failure"));
	    		}
	     		 objForm.setFormAction(null);
	             return mapping.findForward("mainJSP");  // success
	 }
	 
	 private void addSelectedItemToShoppingCart(MainForm objForm, ShoppingCart shoppingCart){
     	SelectedItem selectedItem = new SelectedItem(DaoUtilities.catalogCache.get(objForm.getSelectedItemID()), new Integer(objForm.getQuantity()), objForm.getSizeID());
    	shoppingCart.getSelectedItems().add(selectedItem);
    	float currentItemcost = selectedItem.getItem().getPrice() * new Float(objForm.getQuantity());
    	shoppingCart.setTotalItemCost(shoppingCart.getTotalItemCost()+ currentItemcost);
    	shoppingCart.setTotalItems(shoppingCart.getTotalItems() + selectedItem.getQuantity());
    	objForm.setFormAction(null);
		 
	 }
	 private void populateCountryDropdownList(MainForm objForm){
	 	List<SelectionBean> countries = objForm.getCountryList();
		if(countries==null)
			objForm.setCountryList(IndusUtility.populateCountryDropdownList());
		else
			return;
	 }

 
}