package com.spdb.build.gef;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.spdb.build.gef.editor.MyEditorInput;
import com.spdb.build.gef.editor.MyGraphicalEditor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "com.spdb.build.gef.perspective"; //$NON-NLS-1$

	@Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }
    
	/**
	 * 指定工作台的初始透视图
	 */
    @Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
    
    /**
     * 第一个窗口打开之后但启动事件循环之前调用
     */
    @Override
    public void postStartup() {
    	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			page.openEditor(new MyEditorInput("企业"), MyGraphicalEditor.ID, false);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
    	super.postStartup();
    }
    
}
