package com.spdb.build.gef.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.model.Service;

public class PasteNodeCommand extends Command {

	private HashMap<Node, Node> list = new HashMap<Node, Node>();

	@Override
	public boolean canExecute() {
		// 判断剪切板上是否有内容可粘贴
		ArrayList<Node> bList = (ArrayList<Node>) Clipboard.getDefault().getContents();
		if (bList == null || bList.isEmpty()) {
			return false;
		}
		for (Node node : bList) {
			if (isPastableNode(node)) {
				list.put(node, null);
			}
		}
		return true;
	}

	public boolean isPastableNode(Node node) {
		if (node instanceof Service || node instanceof Employee) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (!canExecute()) {
			return;
		}

		for (Map.Entry<Node, Node> entry : list.entrySet()) {
			try {
				if (entry.getKey() instanceof Service) {
					Service srv = (Service) entry.getKey();

					Service clone = (Service) srv.clone();
					list.put(entry.getKey(), clone);

				} else if (entry.getKey() instanceof Employee) {
					Employee emp = (Employee) entry.getKey();
					Employee clone = (Employee) emp.clone();
					list.put(entry.getKey(), clone);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		redo();
	}

	@Override
	public void redo() {
		Iterator<Node> iterator = list.values().iterator();

		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (isPastableNode(node)) {
				node.getParent().addChild(node);
			}

		}
	}

	@Override
	public boolean canUndo() {
		return !(list.isEmpty());
	}

	@Override
	public void undo() {
		Iterator<Node> iterator = list.values().iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (isPastableNode(node)) {
				node.getParent().removeChild(node);
			}
		}
	}

}
