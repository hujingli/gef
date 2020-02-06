package com.spdb.build.gef.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.spdb.build.gef.editpart.EmployeeEditPart;
import com.spdb.build.gef.editpart.EnterpriseEditPart;
import com.spdb.build.gef.editpart.ServiceEditpart;
import com.spdb.build.gef.editpart.line.ArrowConnectionEditPart;
import com.spdb.build.gef.editpart.line.PlainConnectionEditPart;
import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.ArrowConnectioinModel;
import com.spdb.build.gef.model.line.PlainConnectionModel;

/**
 * 管理editpart对象的工厂类
 * @author huh20
 *
 */
public class AppEditPartFactory implements EditPartFactory{

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		
		AbstractGraphicalEditPart part = null;

		if(model instanceof Enterprise) {
			part = new EnterpriseEditPart();
		}else if(model instanceof Service) {
			part = new ServiceEditpart();
		}else if(model instanceof Employee) {
			part = new EmployeeEditPart();
		}
		
		if (model instanceof PlainConnectionModel) {
			PlainConnectionEditPart editPart = new PlainConnectionEditPart();
			editPart.setModel(model);
			return editPart;
		}
		
		if (model instanceof ArrowConnectioinModel) {
			ArrowConnectionEditPart editPart = new ArrowConnectionEditPart();
			editPart.setModel(model);
			return editPart;
		}
		
		
		
		if (model!=null) {
			part.setModel(model);
		}
		return part;
	}

}
