package com.spdb.build.gef.model.propertysource;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.model.Service;

/**
 * 属性源 {@link Node#getAdapter(Class)}方法中调用
 * @author exphuhong
 *
 */
public class NodePropertySource implements IPropertySource {
	
	private Node node;
	
	public NodePropertySource() {
		// TODO Auto-generated constructor stub
	}
	
	public NodePropertySource(Node node) {
		this.node = node;
	}

	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回IPropertyDescriptor 每个代表模型的一个属性   PropertyDescriptor只读
	 */
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ArrayList<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		if (node instanceof Employee) {
			properties.add(new PropertyDescriptor(Node.PROPERTY_RENAME, "Name"));
		}else {
			properties.add(new TextPropertyDescriptor(Node.PROPERTY_RENAME, "Name"));
		}
		
		if (node instanceof Service) {
			properties.add(new ColorPropertyDescriptor(Service.PROPERTY_COLOR, "Color"));
			properties.add(new TextPropertyDescriptor(Service.PROPERTY_FLOOR, "Etage"));
		}else if (node instanceof Enterprise) {
			properties.add(new TextPropertyDescriptor(Enterprise.PROPERTY_CAPITAL, "Capital"));
		}else if (node instanceof Employee) {
			properties.add(new PropertyDescriptor(Employee.PROPERTY_FIRSTNAME, "Prenom"));
		}
		
		return properties.toArray(new IPropertyDescriptor[0]);
	}

	/**
	 * 重获取属性值依赖于它的标识符
	 */
	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(Node.PROPERTY_RENAME)) {
			return node.getName();
		}
		if (id.equals(Service.PROPERTY_COLOR)) {
			return ((Service)node).getColor().getRGB();
		}
		if (id.equals(Service.PROPERTY_FLOOR)) {
			return Integer.toString(((Service)node).getEtage());
		}
		if (id.equals(Enterprise.PROPERTY_CAPITAL)) {
			return Integer.toString(((Enterprise)node).getCapital());
		}
		if (id.equals(Employee.PROPERTY_FIRSTNAME)) {
			return (((Employee)node).getPrenom());
		}
		
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 编辑后记录属性值
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {

		if (id.equals(Node.PROPERTY_RENAME)) {
			node.setName((String) value);
		}else if (id.equals(Service.PROPERTY_COLOR)) {
			Color newColor = new Color(null, (RGB) value);
			((Service)node).setColor(newColor);
		}else if (id.equals(Service.PROPERTY_FLOOR)) {
			Integer floor = Integer.parseInt((String) value);
			((Service)node).setEtage(floor);
		}else if (id.equals(Enterprise.PROPERTY_CAPITAL)) {
			Integer capital = Integer.parseInt((String) value);
			((Enterprise)node).setCapital(capital);
		}
	}

}
