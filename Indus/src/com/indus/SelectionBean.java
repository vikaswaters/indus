package com.indus;

public class SelectionBean {

	private int targetID;
	private String targetName;
	
	
	public SelectionBean(int targetID, String targetName) {
		super();
		this.targetID = targetID;
		this.targetName = targetName;
	}
	public int getTargetID() {
		return targetID;
	}
	public void setTargetID(int targetID) {
		this.targetID = targetID;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	
}
