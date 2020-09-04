package com.spdb.build.gef.editpart.line;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.Request;

import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.model.line.ArrowConnectioinModel;

/**
 * 带箭头连线的editpart
 * @author exphuhong
 *
 */
public class ArrowConnectionEditPart extends MyAbstractConnectionEditPart{

	private Label label = new Label();
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
	}
	
	/**
	 * 不使用缺省的连线，在该方法中设置为带箭头的连线
	 */
	@Override
	protected IFigure createFigure() {
		PolylineConnection connection = (PolylineConnection) super.createFigure();
		PolygonDecoration decoration = new PolygonDecoration();
		connection.setTargetDecoration(decoration);
		connection.add(label, new MidpointLocator(connection, 0));
		ArrowConnectioinModel model = (ArrowConnectioinModel) getModel();
		String desc = model.getSrc().getName() + "到" + model.getTarget().getName();
		label.setText(model.getName());
		return connection;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) {
			refreshVisuals();
		}
	}
	
	@Override
	public void performRequest(Request req) {
		if (req.getType().equals(REQ_OPEN)) {
			ArrowConnectioinModel model = (ArrowConnectioinModel) getModel();
			model.setName("测试");
		}
		
	}
}
