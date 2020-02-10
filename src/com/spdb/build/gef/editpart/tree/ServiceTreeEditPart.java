package com.spdb.build.gef.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.spdb.build.gef.editpolicy.AppDeletePolicy;
import com.spdb.build.gef.editpolicy.AppRenamePolicy;
import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.model.Service;

/**
 * 树形大纲 editPart 由 { @link AppTreeEditPartFactory }此工厂进行创建
 * @author exphuhong
 *
 */
public class ServiceTreeEditPart extends AppAbstractTreeEditPart{

	protected List<Node> getModelChildren() {
		Service model = (Service) getModel();
		return model.getChildrenArray();
	};
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) {
			refreshChildren();
		}
		if (evt.getPropertyName().contentEquals(Node.PROPERTY_REMOVE)) {
			refreshChildren();
		}
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) {
			refreshVisuals();
		}

		if (evt.getPropertyName().equals(Service.PROPERTY_COLOR)) {
			refreshVisuals();
		}
		if (evt.getPropertyName().equals(Service.PROPERTY_FLOOR)) {
			refreshVisuals();
		}
		
	}
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}
	
	@Override
	public void refreshVisuals() {
		Service model = (Service) getModel();
		setWidgetText(model.getName());
		
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
	}
}
