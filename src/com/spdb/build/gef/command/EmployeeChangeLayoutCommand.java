package com.spdb.build.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.spdb.build.gef.model.Employee;

/**
 * 在{@link AppEditLayoutPolicy.class} 类中被调用
 * @author exphuhong
 *
 */
public class EmployeeChangeLayoutCommand extends AbstractLayoutCommand{

	private Employee model;
	private Rectangle layout;
	
	/**
	 * 为undo使用
	 */
	private Rectangle oldLayout;
	
	public void execute(){
		model.setLayout(layout);
	}
	
	@Override
	public void setConstraint(Rectangle rect) {
		this.layout = rect;
		
	}

	@Override
	public void setModel(Object model) {

		this.model = (Employee) model;
		this.oldLayout = this.model.getLayout();
	}

	public void undo() {
		this.model.setLayout(this.oldLayout);
	}
	
}
