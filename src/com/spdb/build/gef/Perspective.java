package com.spdb.build.gef;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * 透视图  
 * @author exphuhong
 *
 */
public class Perspective implements IPerspectiveFactory {

	private static final String ID_TABS_FOLODER = "PropertySheet";
	@Override	
	public void createInitialLayout(IPageLayout layout) {
		// 获取布局的编辑器
		String editorArea = layout.getEditorArea();
		// 设置显示编辑区
		layout.setEditorAreaVisible(true);
		
		// 添加各种视图
//		IFolderLayout tabs = layout.createFolder(ID_TABS_FOLODER, IPageLayout.LEFT, 0.3f, editorArea);
//		tabs.addView(IPageLayout.ID_OUTLINE);
//		tabs.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		
		// 添加大纲视图
		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, 0.2f, editorArea);
		// 添加属性视图
		layout.addView(IPageLayout.ID_PROP_SHEET, IPageLayout.BOTTOM, 0.8f, editorArea);
		
	}
}
