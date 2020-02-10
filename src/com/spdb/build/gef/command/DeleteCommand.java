package com.spdb.build.gef.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.spdb.build.gef.model.Node;
import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.AbstractConnectionModel;

/**
 * 删除命令 由 {@link AppDeletePolicy.class}创建调用
 * @author exphuhong
 *
 */
public class DeleteCommand extends Command{

	private Node model;
	private Node parentModel;
	
	private List<AbstractConnectionModel> inputs = new ArrayList<AbstractConnectionModel>();
	private List<AbstractConnectionModel> outs = new ArrayList<AbstractConnectionModel>();
	
	@Override
	public void execute() {
		this.parentModel.removeChild(model);
		
		// 删除节点时删除连接线
		if (model instanceof Service) {
			inputs.addAll(((Service)model).getInputs());
			outs.addAll(((Service)model).getOutputs());
			for (AbstractConnectionModel connection : inputs) {
				connection.deattachSource();
				connection.deattachTarget();
			}
			for (AbstractConnectionModel connection : outs) {
				connection.deattachSource();
				connection.deattachTarget();
			}
		}
	}
	
	public void setModel(Object model) {
		this.model = (Node) model;
	}
	
	
	public void setParentModel(Object parentModel) {
		this.parentModel = (Node) parentModel;
	}
	
	@Override
	public void undo() {
		this.parentModel.addChild(model);
		
		// undo恢复节点时恢复连线
		if (model instanceof Service) {
			inputs.addAll(((Service)model).getInputs());
			outs.addAll(((Service)model).getOutputs());
			for (AbstractConnectionModel connection : inputs) {
				connection.attachSource();
				connection.attachTarget();
			}
			for (AbstractConnectionModel connection : outs) {
				connection.attachSource();
				connection.attachTarget();
			}
		}
	}
	
}
