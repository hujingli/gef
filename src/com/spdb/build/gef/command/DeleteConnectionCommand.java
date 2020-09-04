package com.spdb.build.gef.command;

import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.AbstractConnectionModel;

/**
 * 删除连接线
 * 
 * @author exphuhong
 *
 */
public class DeleteConnectionCommand extends Command {

	private AbstractConnectionModel connection;
	
	private Service src;
	private Service target;

	public DeleteConnectionCommand(AbstractConnectionModel connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void execute() {
		this.src = connection.getSrc();
		this.target = connection.getTarget();
		connection.deattachSource();
		connection.deattachTarget();
	}

	@Override
	public void undo() {
		connection.setSrc(src);
		connection.setTarget(target);
		connection.attachSource();
		connection.attachTarget();
	}
}
