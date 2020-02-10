package com.spdb.build.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * 布局命令 移动或修改模型大小
 * @author huh20
 *
 */
public abstract class AbstractLayoutCommand extends Command{

	/**
	 * 修改限制，矩形框Rectangle的大小或位置
	 * @param rect
	 */
	public abstract void setConstraint(Rectangle rect);
	
	public abstract void setModel(Object model);		
	
}
