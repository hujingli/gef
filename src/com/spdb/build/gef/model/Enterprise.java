package com.spdb.build.gef.model;

/**
 * 企业模型  需要继承node
 * @author huh20
 *
 */
public class Enterprise extends Node{
	
	public static final String PROPERTY_CAPITAL = "EnterpriseCapital"; 

	private String address;
	private int capital;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCapital() {
		return capital;
	}
	public void setCapital(int capital) {
		this.capital = capital;
	}
	
	
	
}
