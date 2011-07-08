package com.indus.dao.hibernate;

// Generated 09-Jul-2011 00:48:35 by Hibernate Tools 3.4.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Cat generated by hbm2java
 */
public class Cat implements java.io.Serializable {

	private Integer categoryid;
	private String categoryname;
	private Set catalogs = new HashSet(0);

	public Cat() {
	}

	public Cat(String categoryname) {
		this.categoryname = categoryname;
	}

	public Cat(String categoryname, Set catalogs) {
		this.categoryname = categoryname;
		this.catalogs = catalogs;
	}

	public Integer getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Set getCatalogs() {
		return this.catalogs;
	}

	public void setCatalogs(Set catalogs) {
		this.catalogs = catalogs;
	}

}
