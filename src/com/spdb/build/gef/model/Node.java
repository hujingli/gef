package com.spdb.build.gef.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertySource;

import com.spdb.build.gef.model.propertysource.NodePropertySource;

/**
 * 插件中所有模型的父类
 * @author huh20
 *
 */
public class Node implements IAdaptable{

	private String name;
	private Rectangle layout;
	private List<Node> children;
	private Node parent;
	
	// 定义属性源
	private IPropertySource propertySource = null;
	
	// 添加监听器(属性改变)
	private PropertyChangeSupport listeners;
	public static final String PROPERTY_LAYOUT = "NodeLayout";
	
	// 添加和删除
	public static final String PROPERTY_ADD = "NodeAddChild";
	public static final String PROPERTY_REMOVE = "NodeRemoveChild";
	
	// 重命名
	public static final String PROPERTY_RENAME = "NodeRename";
	
	public Node() {
		this.name = "Unknown";
		this.layout = new Rectangle(10,10, 100, 100);
		this.children = new ArrayList<Node>();
		this.parent = null;
		this.listeners = new PropertyChangeSupport(this);
	}
	
	public boolean contains(Node node) {
		return children.contains(node);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	public PropertyChangeSupport getListeners() {
		return listeners;
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String  oldName = this.name;
		this.name = name;
		getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}

	public Rectangle getLayout() {
		return layout;
	}

	public void setLayout(Rectangle newLayout) {
		
		Rectangle oldLayout = this.layout;
		this.layout = newLayout;
		
		getListeners().firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);
	}
	
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean addChild(Node child) {
		
		boolean b = this.children.add(child);
		if (b) {
			child.setParent(this);
			getListeners().firePropertyChange(PROPERTY_ADD, null, child);
		}
		return b;
	}
	
	public boolean removeChild(Node child) {
		boolean b = this.children.remove(child);
		if (b) {
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		}
		return b;
	}
	
	public List<Node> getChildrenArray(){
		return this.children;
	}

	/**
	 * 定义属性源
	 */
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		
		if (adapter == IPropertySource.class) {
			if (propertySource == null) {
				propertySource = new NodePropertySource(this);
			}
			return (T) propertySource;
		}
		return null;
	}
}
