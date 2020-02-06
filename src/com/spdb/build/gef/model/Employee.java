package com.spdb.build.gef.model;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 雇员模型 继承node
 * 
 * @author huh20
 *
 */
public class Employee extends Node {

	public static final String PROPERTY_FIRSTNAME = "EmployeePrenom";

	private String prenom;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Employee employee = new Employee();
		employee.setName(this.getName());
		employee.setParent(this.getParent());
		employee.setPrenom(this.prenom);
		employee.setLayout(new Rectangle(getLayout().x +10, getLayout().y+10, getLayout().width, getLayout().height));
		return employee;
	}

}
