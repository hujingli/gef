package com.spdb.build.gef.wizard;

import org.eclipse.jface.wizard.Wizard;

import com.spdb.build.gef.wizard.page.RenamePage;

/**
 * 重命名向导  关联向导页{ @link RenamePage}
 * @author exphuhong
 *
 */
public class RenameWizard  extends Wizard{
	
	private static String PAGE_NAME = "重命名页面"; 

	private String newName;
	
	/**
	 * 点击向导的完成按钮触发
	 */
	@Override
	public boolean performFinish() {
		RenamePage page = (RenamePage) getPage(PAGE_NAME);
		if (page.getNameText().getText().isEmpty()) {
			page.setErrorMessage("名称不能为空");
			return false;
		}
		newName = page.getNameText().getText();
		return true;
	}
	
	
	public RenameWizard(String oldName) {
		
		// 添加向导页面
		addPage(new RenamePage(PAGE_NAME,oldName));
	}
	
	public String getRenameValue() {
		return this.newName;
	}

}
