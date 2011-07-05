package com.indus.core;

import java.util.ArrayList;
import java.util.List;

import com.indus.dao.hibernate.Customer;
import com.indus.dao.hibernate.Orders;
import com.indus.dao.hibernate.Payment;
import com.indus.dao.hibernate.Shipping;

public class ShoppingCart {
	List<SelectedItem> selectedItems;
	private float totalItemCost;
	private int totalItems;
	
	Orders order; // MAIN. order has reference to CUstomer, Payment and Shipping
	
/*	Customer customer;
	Payment payment;
	Shipping shipping;
	*/
	
	public ShoppingCart() {
		super();
		selectedItems = new ArrayList<SelectedItem>();
		order = new Orders();
	}

	public Customer getCustomer() {
		return order.getCustomer();
	}

	public void setCustomer(Customer customer) {
		order.setCustomer(customer);
	}

	public Payment getPayment() {
		return order.getPayment();
	}

	public void setPayment(Payment payment) {
		order.setPayment(payment);
	}
	public Shipping getShipping() {
		return order.getShipping();
	}	
	
	public void setShipping(Shipping shipping) {
		order.setShipping(shipping);
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public float getTotalItemCost() {
		return totalItemCost;
	}

	public void setTotalItemCost(float totalCost) {
		this.totalItemCost = totalCost;
	}

	public List<SelectedItem> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItem> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public boolean isEmpty(){
		return (this.selectedItems==null || this.selectedItems.size()==0) ? true : false;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
}
