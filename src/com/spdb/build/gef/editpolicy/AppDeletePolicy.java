package com.spdb.build.gef.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.spdb.build.gef.command.DeleteCommand;
import com.spdb.build.gef.model.Node;

/**
 * 调用删除命令 {@link DeleteCommand}
 * 
 * policy在 {@link ServiceEditpart.class}
 * @author exphuhong
 *
 */
public class AppDeletePolicy extends ComponentEditPolicy{

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		
		DeleteCommand command = new DeleteCommand();
		Node model = (Node) getHost().getModel();
		command.setModel(model);
		command.setParentModel(model.getParent());
		return command;
	}
	
}

