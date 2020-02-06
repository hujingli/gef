package com.spdb.build.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 视图
 * @author huh20
 *
 */
public class EmployeeFigure extends Figure{

	public static final int EMPLOYEE_FIGURE_DEFWIDTH = 60;
	public static final int EMPLOYEE_FIGURE_DEFHEIGHT = 35;
	
	private Label labelName = new Label();
	private Label labelFirstName = new Label();

	public EmployeeFigure() {
		// 设置布局管理器
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);
		
		// 设置背景颜色
		labelFirstName.setForegroundColor(ColorConstants.black);
		add(labelFirstName, ToolbarLayout.ALIGN_CENTER);
		labelName.setForegroundColor(ColorConstants.darkGray);
		add(labelName, ToolbarLayout.ALIGN_CENTER);
		
		
		setForegroundColor(ColorConstants.darkGray);
		setBackgroundColor(ColorConstants.lightGray);
		
		// 设置边框
		setBorder(new LineBorder(1));
		setOpaque(true);
	}
	
	public void setName(String text) {
		labelName.setText(text);
	}
	
	public void setFirstName(String text) {
		labelFirstName.setText(text);
	}
	
	public void setLayout(Rectangle rect) {
		getParent().setConstraint(this, rect);
	}

	
}
