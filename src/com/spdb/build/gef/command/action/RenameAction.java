package com.spdb.build.gef.command.action;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ResourceLocator;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.wizard.RenameWizard;

public class RenameAction extends SelectionAction{

	public RenameAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createRenameCommand("");
		if (cmd == null) {
			return false;
		}
		return true;
	}

	@Override
	protected void init() {
		setText("重命名...");
		setToolTipText("重命名");
		
		setId(ActionFactory.RENAME.getId());
		
		Optional<ImageDescriptor> icon = ResourceLocator.imageDescriptorFromBundle("com.spdb.build.gef", "icons/rename.png");
		if (icon.isPresent()) {
			setImageDescriptor(icon.get());
		}
		
		setEnabled(false);
	}
	
	public Command createRenameCommand(String name) {
		Request renameReq = new Request("rename");
		HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newName", name);
		renameReq.setExtendedData(reqData);
		
		EditPart object = (EditPart) getSelectedObjects().get(0);
		Command cmd = object.getCommand(renameReq);
		
		return cmd;
	}
	
	public void run() {
		Node node = getSelectNode();
		RenameWizard wizard = new RenameWizard(node.getName());
		WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(), wizard);
		
		dialog.create();
		dialog.getShell().setSize(400, 320);
		
		dialog.setTitle("重命名 wizard");
		dialog.setMessage("重命名");
		if (dialog.open() == WizardDialog.OK) {
			String name = wizard.getRenameValue();
			execute(createRenameCommand(name));
		}
	}
	
	private Node getSelectNode() {
		List objects = getSelectedObjects();
		if (objects.isEmpty()) {
			return null;
		}
		if (!(objects.get(0) instanceof EditPart)) {
			return null;
		}
		
		EditPart part = (EditPart) objects.get(0);
		return (Node) part.getModel();
	}
	
}
