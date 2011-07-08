package com.indus.dao.hibernate;

// Generated 09-Jul-2011 00:48:35 by Hibernate Tools 3.4.0.Beta1

/**
 * OrderitemsId generated by hbm2java
 */
public class OrderitemsId implements java.io.Serializable {

	private Integer quantity;
	private String color;
	private String size;
	private Integer itemId;
	private Integer orderid;

	public OrderitemsId() {
	}

	public OrderitemsId(Integer quantity, String color, String size,
			Integer itemId, Integer orderid) {
		this.quantity = quantity;
		this.color = color;
		this.size = size;
		this.itemId = itemId;
		this.orderid = orderid;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderitemsId))
			return false;
		OrderitemsId castOther = (OrderitemsId) other;

		return ((this.getQuantity() == castOther.getQuantity()) || (this
				.getQuantity() != null && castOther.getQuantity() != null && this
				.getQuantity().equals(castOther.getQuantity())))
				&& ((this.getColor() == castOther.getColor()) || (this
						.getColor() != null && castOther.getColor() != null && this
						.getColor().equals(castOther.getColor())))
				&& ((this.getSize() == castOther.getSize()) || (this.getSize() != null
						&& castOther.getSize() != null && this.getSize()
						.equals(castOther.getSize())))
				&& ((this.getItemId() == castOther.getItemId()) || (this
						.getItemId() != null && castOther.getItemId() != null && this
						.getItemId().equals(castOther.getItemId())))
				&& ((this.getOrderid() == castOther.getOrderid()) || (this
						.getOrderid() != null && castOther.getOrderid() != null && this
						.getOrderid().equals(castOther.getOrderid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getQuantity() == null ? 0 : this.getQuantity().hashCode());
		result = 37 * result
				+ (getColor() == null ? 0 : this.getColor().hashCode());
		result = 37 * result
				+ (getSize() == null ? 0 : this.getSize().hashCode());
		result = 37 * result
				+ (getItemId() == null ? 0 : this.getItemId().hashCode());
		result = 37 * result
				+ (getOrderid() == null ? 0 : this.getOrderid().hashCode());
		return result;
	}

}
