package com.spdb.build.gef.command;

import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.AbstractConnectionModel;


public class ReconnectSoourceCommand extends Command{

	private AbstractConnectionModel connection;
	
	private Service src;
	private Service old;
	
	public ReconnectSoourceCommand(AbstractConnectionModel connection, Service newService) {
		super();
		this.connection = connection;
		this.src = newService;
		
	}
	
	@Override
	public void execute() {
		old = connection.getSrc();
		connection.deattachSource();
		connection.setSrc(src);
		connection.attachSource();
	}
	
	@Override
	public void undo() {
		src = connection.getSrc();
		connection.deattachSource();
		connection.setSrc(old);
		connection.attachSource();
	}
	
}
