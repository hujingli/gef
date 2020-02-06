package com.spdb.build.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Service;

/**
 * 新建service命令
 * 
 * @author exphuhong
 *
 */
public class ServiceCreateCommand extends Command {

	private Enterprise enterprise;
	private Service srv;

	public ServiceCreateCommand() {
		super();
		enterprise = null;
		srv = null;
	}

	public void setService(Object s) {
		if (s instanceof Service) {
			this.srv = (Service) s;
		}
	}

	public void setEnterprise(Object e) {
		if (e instanceof Enterprise) {
			this.enterprise = (Enterprise) e;
		}
	}

	public void setLayout(Rectangle r) {
		if (srv != null) {
			srv.setLayout(r);
		}
	}

	@Override
	public boolean canExecute() {
		if (srv == null || enterprise == null) {
			return false;
		}
		return true;
	}

	@Override
	public void execute() {
		enterprise.addChild(srv);
	}

	@Override
	public boolean canUndo() {
		if (enterprise == null || srv == null) {
			return false;
		}
		return enterprise.contains(srv);
	}

	@Override
	public void undo() {
		enterprise.removeChild(srv);
	}
}
