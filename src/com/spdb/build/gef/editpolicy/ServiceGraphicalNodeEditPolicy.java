package com.spdb.build.gef.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.spdb.build.gef.command.AddConnectionCommand;
import com.spdb.build.gef.command.ReconnectSoourceCommand;
import com.spdb.build.gef.command.ReconnectTargetCommand;
import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.AbstractConnectionModel;

public class ServiceGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy{

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		AddConnectionCommand command  = (AddConnectionCommand) request.getStartCommand();
		command.setTarget((Service)getHost().getModel());
		return command;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		AddConnectionCommand command = new AddConnectionCommand((AbstractConnectionModel)request.getNewObject());
		command.setSrc((Service)getHost().getModel());
		request.setStartCommand(command);
		return command;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectSoourceCommand command = new ReconnectSoourceCommand((
				AbstractConnectionModel)request.getConnectionEditPart().getModel(),
				(Service)getHost().getModel());
		
		
		return command;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		ReconnectTargetCommand command = new ReconnectTargetCommand((
				AbstractConnectionModel)request.getConnectionEditPart().getModel(),
				(Service)getHost().getModel());
		return command;
	}

}
