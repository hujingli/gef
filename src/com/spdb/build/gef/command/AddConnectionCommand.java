package com.spdb.build.gef.command;

import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.AbstractConnectionModel;

public class AddConnectionCommand extends Command{

	private AbstractConnectionModel connectionModel;
	
	public AddConnectionCommand(AbstractConnectionModel connectionModel) {
		super();
		this.connectionModel = connectionModel;
	}
	
	public void setSrc(Service service) {
		connectionModel.setSrc(service);
	}
	
	public void setTarget(Service target) {
		connectionModel.setTarget(target);
	}
	
	public void setConnectionModel(AbstractConnectionModel connectionModel) {
		this.connectionModel = connectionModel;
	}
	
	@Override
	public void execute() {
		connectionModel.attachSource();
		connectionModel.attachTarget();
	}
	
	@Override
	public void undo() {
		connectionModel.deattachSource();
		connectionModel.deattachTarget();
	}
	
	
	
	
	
	
	
}
