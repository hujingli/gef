package com.spdb.build.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.spdb.build.gef.model.Service;

/**
 * 在{@link AppEditLayoutPolicy.class} 类中被调用
 * @author exphuhong
 *
 */
public class ServiceChangeLayoutCommand extends AbstractLayoutCommand{

	private Service model;
	private Rectangle layout;
	
	/**
	 * 为undo使用
	 */
	private Rectangle oldLayout;
	
	public void execute() {
		model.setLayout(layout);
	}
	
	@Override
	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	@Override
	public void setModel(Object model) {
		this.model = (Service) model;
		this.oldLayout = this.model.getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
