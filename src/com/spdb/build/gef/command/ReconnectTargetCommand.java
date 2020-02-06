package com.spdb.build.gef.command;

import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.AbstractConnectionModel;

public class ReconnectTargetCommand extends Command{

	private AbstractConnectionModel connection;
	
	private Service target;
	private Service old;
	
	public ReconnectTargetCommand(AbstractConnectionModel connection, Service newTarget) {
		super();
		this.connection = connection;
		this.target = newTarget;
	}
	
	@Override
	public void execute() {
		old = connection.getTarget();
		connection.deattachTarget();
		connection.setTarget(target);
		connection.attachTarget();
	}
	
	@Override
	public void undo() {
		target = connection.getTarget();
		connection.deattachTarget();
		connection.setTarget(old);
		connection.attachSource();
	}
	
}
