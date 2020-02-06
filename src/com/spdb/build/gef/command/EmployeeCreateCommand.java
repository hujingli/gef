package com.spdb.build.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Service;

public class EmployeeCreateCommand extends Command {

	private Service service;
	private Employee employee;

	public EmployeeCreateCommand() {
		super();
	}

	public void setEmployee(Object e) {
		if (e instanceof Employee) {
			this.employee = (Employee) e;
		}
	}

	public void setService(Object s) {
		if (s instanceof Service) {
			this.service = (Service) s;
		}
	}

	public void setLayout(Rectangle r) {
		if (employee != null) {
			employee.setLayout(r);
		}
	}

	@Override
	public boolean canExecute() {
		if (service == null || employee == null) {
			return false;
		}
		return true;
	}

	@Override
	public void execute() {
		service.addChild(employee);
	}
	
	@Override
	public boolean canUndo() {
		if (service == null || employee == null) {
			return false;
		}
		return service.contains(employee);
	}
	
	@Override
	public void undo() {
		service.removeChild(employee);
	}
}
