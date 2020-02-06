package com.spdb.build.gef.factory.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.spdb.build.gef.editpart.tree.EmployeeTreeEditPart;
import com.spdb.build.gef.editpart.tree.EnterpriseTreeEditPart;
import com.spdb.build.gef.editpart.tree.ServiceTreeEditPart;
import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Service;

public class AppTreeEditPartFactory implements EditPartFactory{

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		
		if (model instanceof Enterprise) {
			part = new EnterpriseTreeEditPart();
		}else if (model instanceof Service) {
			part = new ServiceTreeEditPart();
		}else if (model instanceof Employee) {
			part = new EmployeeTreeEditPart();
		}
		
		if (part != null) {
			part.setModel(model);
		}
		
		return part;
	}

}
