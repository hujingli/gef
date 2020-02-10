package com.spdb.build.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import com.spdb.build.gef.editpolicy.AppDeletePolicy;
import com.spdb.build.gef.editpolicy.AppEditLayoutPolicy;
import com.spdb.build.gef.editpolicy.AppRenamePolicy;
import com.spdb.build.gef.editpolicy.ServiceGraphicalNodeEditPolicy;
import com.spdb.build.gef.figure.ServiceFigure;
import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.model.Service;

public class ServiceEditpart extends AppAbstractEditPart implements NodeEditPart{

	/**
	 * 创建模型对应的图像
	 */
	@Override
	protected IFigure createFigure() {
		return new ServiceFigure();
	}

	/**
	 * 注册命令
	 */
	@Override
	protected void createEditPolicies() {
		// 修改布局
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		
		// 删除
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new ServiceGraphicalNodeEditPolicy());
	}

	/**
	 * 刷新视图
	 */
	protected void refreshVisuals() {
		ServiceFigure  figure = (ServiceFigure)getFigure();
		Service model= (Service) getModel();
		
		figure.setName(model.getName());
		figure.setEtage(model.getEtage());
		figure.setLayout(model.getLayout());
		
	}
	
	public List<Node> getModelChildren(){
		
		return ((Service)getModel()).getChildrenArray();
		
	}
	
	/**
	 * 属性改变监听操作。 处理fire过来的监听事件
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) {
			refreshVisuals();
		}
		if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) {
			refreshChildren();
		}
		if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) {
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
		if (evt.getPropertyName().equals(Service.P_SOURCE)) {
			refreshSourceConnections();
		}
		if (evt.getPropertyName().equals(Service.P_TARGET)) {
			refreshTargetConnections();
		}
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request arg0) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
		
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request arg0) {
		return new ChopboxAnchor(getFigure());
	}
	
	
	@Override
	protected List getModelSourceConnections() {
		return ((Service)getModel()).getOutputs();
	}
	
	@Override
	protected List getModelTargetConnections() {
		return ((Service)getModel()).getInputs();
	}
	
	
	
	
}
