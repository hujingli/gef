package com.spdb.build.gef.editpart.line;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

public class ArrowConnectionEditPart extends MyAbstractConnectionEditPart{

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected IFigure createFigure() {
		PolylineConnection connection = (PolylineConnection) super.createFigure();
		PolygonDecoration decoration = new PolygonDecoration();
		connection.setTargetDecoration(decoration);
		return connection;
	}

	
}
