package com.spdb.build.gef.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import com.spdb.build.gef.model.line.AbstractConnectionModel;

/**
 * 部门模型 继承node
 * 
 * @author huh20
 *
 */
public class Service extends Node {

	public static final String PROPERTY_COLOR = "ServiceColor";
	public static final String PROPERTY_FLOOR = "ServiceFloor";
	
	public static final String P_SOURCE = "p_source";
	public static final String P_TARGET = "p_target";

	private int etage;
	private Color color;
	
	// 保存和操作连线
	private List<AbstractConnectionModel> inputs = new ArrayList<AbstractConnectionModel>();
	private List<AbstractConnectionModel> outputs = new ArrayList<AbstractConnectionModel>();

	private Color createRandomColor() {
		return new Color(null, new Double(Math.random() * 128).intValue() + 128,
				new Double(Math.random() * 128).intValue() + 128, new Double(Math.random() * 128).intValue() + 128);
	}

	public Service() {
		this.color = createRandomColor();
	}

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, color);
	}

	public Color getColor() {
		return color;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Service service = new Service();
		service.setColor(this.color);
		service.setEtage(this.etage);
		service.setName(this.getName());
		service.setParent(this.getParent());
		service.setLayout(new Rectangle(getLayout().x + 10, getLayout().y+10, getLayout().width, getLayout().height));
		
		Iterator<Node> iterator = this.getChildrenArray().iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (node instanceof Employee) {
				Employee child = (Employee) node;
				Node clone = (Node) child.clone();
				service.addChild(clone);
				clone.setLayout(child.getLayout());
			}
			
		}
		return service;
		
	}

	public List getInputs() {
		return inputs;
	}

	public List getOutputs() {
		return outputs;
	}
	
	public void addIn(AbstractConnectionModel model) {
			if (!inputs.contains(model)) {
				inputs.add(model);
				getListeners().firePropertyChange(P_TARGET, null, model);
			}
	}
	
	public void addOut(AbstractConnectionModel model) {
		if (!outputs.contains(model)) {
			outputs.add(model);
			getListeners().firePropertyChange(P_SOURCE, null, model);
		}
	}
	
	public void removeInput(AbstractConnectionModel model) {
		if (inputs.contains(model)) {
			inputs.remove(model);
			getListeners().firePropertyChange(P_TARGET, model, null);
		}
	}
	
	public void removeOut(AbstractConnectionModel model) {
		if (outputs.contains(model)) {
			outputs.remove(model);
			getListeners().firePropertyChange(P_SOURCE, model, null);
		}
	}

}
