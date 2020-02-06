package com.spdb.build.gef.editor.listener;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

public class MyTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener{

	public MyTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	@Override
	protected CreationFactory getFactory(Object template) {
		return (CreationFactory) template;
	}
	
	
}
