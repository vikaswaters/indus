package com.indus.core;

import com.indus.dao.hibernate.Catalog;

public class SelectedItem {
	private Catalog item;
	private int quantity;
	private String size;
	private String color;
	public Catalog getItem() {
		return item;
	}
	
	public SelectedItem(Catalog item, int quantity, String size) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setItem(Catalog item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
	
	
}
