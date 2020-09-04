package com.spdb.build.gef.editpart.line;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.spdb.build.gef.editpolicy.AppDeleteConnectionPolicy;
import com.spdb.build.gef.model.Node;

public class MyAbstractConnectionEditPart extends AbstractConnectionEditPart implements PropertyChangeListener{

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEndpointEditPolicy());

		// 删除连线
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new AppDeleteConnectionPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void activate() {
		super.activate();
		Node model = (Node) getModel();
		model.addPropertyChangeListener(this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		Node model = (Node) getModel();
		model.removePropertyChangeListener(this);
	}
	

}
