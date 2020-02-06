package com.spdb.build.gef.command.creation;

import org.eclipse.gef.requests.CreationFactory;

import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Service;

/**
 * 用于创建依赖于环境新的对象实体
 * @author exphuhong
 *
 */
public class NodeCreationFactory implements CreationFactory{

	private Class<?> template;
	
	public NodeCreationFactory(Class<?> t) {
		this.template = t;
	}
	
	@Override
	public Object getNewObject() {
		if (template == null) {
			return null;
		}
		if (template == Service.class) {
			Service srv = new Service();
			srv.setEtage(42);
			srv.setName("客房");
			return srv;
		}else if (template == Employee.class) {
			Employee employee = new Employee();
			employee.setName("小二");
			employee.setPrenom("店");
			return employee;
		}
		return null;
	}

	@Override
	public Object getObjectType() {
		return template;
	}

	
}
