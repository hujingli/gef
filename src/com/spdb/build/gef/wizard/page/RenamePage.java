package com.spdb.build.gef.wizard.page;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 重命名向导页
 * @author exphuhong
 *
 */
public class RenamePage extends WizardPage{

	private Text nameText;
	private String oldName;
	
	public RenamePage(String pageName, String oldName) {
		super(pageName);
		this.oldName = oldName;
		setTitle("重命名");
		setDescription("进行重命名");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("重命名为:");
		
		nameText = new Text(composite, SWT.NONE);
		nameText.setText(oldName);
		
		RowLayout I = new RowLayout();
		composite.setLayout(I);
		setControl(composite);
	}

	public Text getNameText() {
		return nameText;
	}

	public void setNameText(Text nameText) {
		this.nameText = nameText;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	
	

}
