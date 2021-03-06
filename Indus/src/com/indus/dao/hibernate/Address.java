package com.indus.dao.hibernate;

// Generated 09-Jul-2011 00:48:35 by Hibernate Tools 3.4.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Address generated by hbm2java
 */
public class Address implements java.io.Serializable {

	private Integer addressid;
	private Country country;
	private String name;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private Integer zip;
	private Set customersForAddressid = new HashSet(0);
	private Set customersForShipaddressid = new HashSet(0);

	public Address() {
	}

	public Address(Country country, String name, String line1, String line2,
			String city, String state, Integer zip, Set customersForAddressid,
			Set customersForShipaddressid) {
		this.country = country;
		this.name = name;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.customersForAddressid = customersForAddressid;
		this.customersForShipaddressid = customersForShipaddressid;
	}

	public Integer getAddressid() {
		return this.addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLine1() {
		return this.line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return this.line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return this.zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public Set getCustomersForAddressid() {
		return this.customersForAddressid;
	}

	public void setCustomersForAddressid(Set customersForAddressid) {
		this.customersForAddressid = customersForAddressid;
	}

	public Set getCustomersForShipaddressid() {
		return this.customersForShipaddressid;
	}

	public void setCustomersForShipaddressid(Set customersForShipaddressid) {
		this.customersForShipaddressid = customersForShipaddressid;
	}

}
