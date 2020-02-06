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
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		
//		IFolderLayout tabs = layout.createFolder(ID_TABS_FOLODER, IPageLayout.LEFT, 0.3f, editorArea);
//		tabs.addView(IPageLayout.ID_OUTLINE);
//		tabs.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		
		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, 0.2f, editorArea);
		layout.addView(IPageLayout.ID_PROP_SHEET, IPageLayout.BOTTOM, 0.8f, editorArea);
		
	}
}
