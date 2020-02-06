package com.spdb.build.gef.command;

import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Node;

/**
 * 重命名命令
 * @author exphuhong
 *
 */
public class RenameCommand extends Command{

	private Node model;
	private String oldName;
	private String newName;
	
	@Override
	public void execute() {
		this.oldName = model.getName();
		this.model.setName(this.newName);
	}
	
	@Override
	public void undo() {
		this.model.setName(this.oldName);
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public Node getModel() {
		return model;
	}

	public void setModel(Object object) {
		this.model = (Node) object;
	}
	
	
	
}
