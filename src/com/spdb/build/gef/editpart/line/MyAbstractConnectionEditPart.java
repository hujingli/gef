package com.spdb.build.gef.editpart.line;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

public class MyAbstractConnectionEditPart extends AbstractConnectionEditPart{

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEndpointEditPolicy());
	}

}
