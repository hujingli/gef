package com.spdb.build.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.spdb.build.gef.editpolicy.AppDeletePolicy;
import com.spdb.build.gef.editpolicy.AppEditLayoutPolicy;
import com.spdb.build.gef.editpolicy.AppRenamePolicy;
import com.spdb.build.gef.figure.EmployeeFigure;
import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Node;

public class EmployeeEditPart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		return new EmployeeFigure();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}
	
	
	// 刷新视图
	protected void refreshVisuals() {
		
		EmployeeFigure  figure = (EmployeeFigure)getFigure();
		Employee model= (Employee) getModel();
//		
		figure.setName(model.getName());
//		figure.setFirstName(model.getPrenom());
		figure.setLayout(model.getLayout());
//		super.refreshVisuals();
		
	}
	
	public List<Node> getModelChildren(){
		
		return new ArrayList<Node>();
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) {
			refreshVisuals();
		}
		if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) {
			refreshChildren();
		}
		if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) {
			refreshChildren();
		}
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) {
			refreshVisuals();
		}
		if (evt.getPropertyName().equals(Employee.PROPERTY_FIRSTNAME)) {
			refreshVisuals();
		}
	}
	
	
	
	
}
