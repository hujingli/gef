package com.spdb.build.gef.editpart;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;

import com.spdb.build.gef.dialog.CustomConvertDialog;
import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.wizard.RenameWizard;

public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener{

	public void activate() {
		super.activate();
		Node model = (Node) getModel();
		model.addPropertyChangeListener(this);
	}
	
	public void deactivate() {
		super.deactivate();
		Node model = (Node) getModel();
		model.removePropertyChangeListener(this);
	}
	
	/**
	 * 处理图形元素双击  双击发送的request没有到policy而是直接被此方法进行处理
	 */
	@Override
	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
//			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//			try {
//				page.showView(IPageLayout.ID_PROP_SHEET);
//			} catch (PartInitException e) {
//				e.printStackTrace();
//			}
			List<String> list = new ArrayList<String>();
			list.add("hello");
			list.add("world");
			CustomConvertDialog dialog = new CustomConvertDialog(Display.getCurrent().getActiveShell(), list);
			
			if (dialog != null) {
				dialog.setBlockOnOpen(true);
				dialog.open();
			}
		}
	}

	@Override
	public List getChildren() {
		// TODO Auto-generated method stub
		return super.getChildren();
	}
}
