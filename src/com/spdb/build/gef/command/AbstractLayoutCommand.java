package com.spdb.build.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * 布局命令
 * @author huh20
 *
 */
public abstract class AbstractLayoutCommand extends Command{

	public abstract void setConstraint(Rectangle rect);
	
	public abstract void setModel(Object model);		
	
}
