package com.spdb.build.gef.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.spdb.build.gef.command.DeleteConnectionCommand;
import com.spdb.build.gef.model.line.AbstractConnectionModel;

/**
 * 删除连接线的策略
 * @author exphuhong
 *
 */
public class AppDeleteConnectionPolicy extends ConnectionEditPolicy{

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		Command cmd = new DeleteConnectionCommand((AbstractConnectionModel) getHost().getModel());
		return cmd;
	}

}
