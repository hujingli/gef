package com.spdb.build.gef.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Node;

/**
 * 树形大纲 editPart 由 { @link AppTreeEditPartFactory }此工厂进行创建
 * @author exphuhong
 *
 */
public class EnterpriseTreeEditPart extends AppAbstractTreeEditPart{

	protected List<Node> getModelChildren() {
		Enterprise model = (Enterprise) getModel();
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
		if (evt.getPropertyName().equals(Enterprise.PROPERTY_CAPITAL)) {
			refreshVisuals();
		}
		
	}
}
