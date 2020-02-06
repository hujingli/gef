package com.spdb.build.gef.editpolicy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.spdb.build.gef.command.AbstractLayoutCommand;
import com.spdb.build.gef.command.EmployeeChangeLayoutCommand;
import com.spdb.build.gef.command.EmployeeCreateCommand;
import com.spdb.build.gef.command.ServiceChangeLayoutCommand;
import com.spdb.build.gef.command.ServiceCreateCommand;
import com.spdb.build.gef.editpart.EmployeeEditPart;
import com.spdb.build.gef.editpart.EnterpriseEditPart;
import com.spdb.build.gef.editpart.ServiceEditpart;
import com.spdb.build.gef.figure.EmployeeFigure;
import com.spdb.build.gef.figure.ServiceFigure;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {

		// 创建布局命令
		AbstractLayoutCommand command = null;

		if (child instanceof EmployeeEditPart) {
			command = new EmployeeChangeLayoutCommand();
		} else if (child instanceof ServiceEditpart) {
			command = new ServiceChangeLayoutCommand();
		}
		if (command != null) {
			command.setModel(child.getModel());
			command.setConstraint((Rectangle) constraint);
		}

		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getType() == REQ_CREATE && getHost() instanceof EnterpriseEditPart) {
			ServiceCreateCommand cmd = new ServiceCreateCommand();
			cmd.setEnterprise(getHost().getModel());
			cmd.setService(request.getNewObject());

			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0)? ServiceFigure.SERVICE_FIGURE_DEFWIDTH: constraint.width;
			constraint.height = (constraint.height <= 0)? ServiceFigure.SERVICE_FIGURE_DEFHEIGHT: constraint.height;
			
			cmd.setLayout(constraint);
			return cmd;
		}else if (request.getType() == REQ_CREATE && getHost() instanceof ServiceEditpart) {
			EmployeeCreateCommand cmd = new EmployeeCreateCommand();
			cmd.setService(getHost().getModel());
			cmd.setEmployee(request.getNewObject());

			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0)? EmployeeFigure.EMPLOYEE_FIGURE_DEFWIDTH: constraint.width;
			constraint.height = (constraint.height <= 0)? EmployeeFigure.EMPLOYEE_FIGURE_DEFHEIGHT: constraint.height;
			
			cmd.setLayout(constraint);
			return cmd;
		}

		return null;
	}

}
