package com.spdb.build.gef.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.model.Service;

public class CopyNodeCommand extends Command{

	private List<Node> list = new ArrayList<Node>();
	
	public boolean addElement(Node node) {
		if (!list.contains(node)) {
			return list.add(node);
		}
		return false;
	}
	
	@Override
	public boolean canExecute() {
		if (list == null || list.isEmpty()) {
			return false;
		}
		for (Node node : list) {
			if (!isCopyableNode(node)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void execute() {
		if (canExecute()) {
			Clipboard.getDefault().setContents(list);
		}
	}
	
	public boolean canUndo() {
		return false;
	}
	
	
	public boolean isCopyableNode(Node node) {
		if (node instanceof Service || node instanceof Employee) {
			return true;
		}
		return false;
	}
	
}
