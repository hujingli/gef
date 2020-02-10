package com.spdb.build.gef;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	 /**
     * 1.注册菜单或者工具栏的Action。Action只有注册后才能添加到菜单中。
     * 
     */
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		makeAction(window, ActionFactory.UNDO);
		makeAction(window, ActionFactory.REDO);
		makeAction(window,ActionFactory.COPY);
		makeAction(window, ActionFactory.PASTE);
	}
	
	protected IWorkbenchAction makeAction(IWorkbenchWindow window, ActionFactory af) {
		IWorkbenchAction action = af.create(window);
		register(action);
		return action;
	}
	/**
     * 2.填充菜单栏。用Action来填充菜单
     * 
     *  (1) 菜单管理器负责管理菜单项、设置菜单行为、创建级联菜单或者对菜单项进行分组。
     *  (2) MenuManager构造函数：菜单项文本、菜单项ID
     *  (3) new Separator() 为一条分割线
     */
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		super.fillMenuBar(menuBar);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		super.fillCoolBar(coolBar);
	}

}

