package com.spdb.build.gef.editpart.line;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

/**
 * 带箭头连线的editpart
 * @author exphuhong
 *
 */
public class ArrowConnectionEditPart extends MyAbstractConnectionEditPart{

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 不使用缺省的连线，在该方法中设置为带箭头的连线
	 */
	@Override
	protected IFigure createFigure() {
		PolylineConnection connection = (PolylineConnection) super.createFigure();
		PolygonDecoration decoration = new PolygonDecoration();
		connection.setTargetDecoration(decoration);
		return connection;
	}

	
}
