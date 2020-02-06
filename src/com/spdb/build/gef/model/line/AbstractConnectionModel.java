package com.spdb.build.gef.model.line;

import com.spdb.build.gef.model.Service;

/**
 * 连接线模型
 * @author exphuhong
 *
 */
public abstract class AbstractConnectionModel {

	private Service src;
	private Service target;
	public Service getSrc() {
		return src;
	}
	public void setSrc(Service src) {
		this.src = src;
	}
	public Service getTarget() {
		return target;
	}
	public void setTarget(Service target) {
		this.target = target;
	}
	
	
	public void attachSource() {
		if (!src.getOutputs().contains(this)) {
			src.addOut(this);
		}
	}
	
	public void attachTarget() {
		if (!target.getInputs().contains(this)) {
			target.addIn(this);
		}
	}
	
	public void deattachSource() {
		if (src.getOutputs().contains(this)) {
			src.removeOut(this);
		}
	}
	
	public void deattachTarget() {
		if (target.getInputs().contains(this)) {
			target.removeInput(this);
		}
	}
	
}
