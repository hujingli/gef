package com.spdb.build.gef.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class CustomConvertDialog extends TitleAreaDialog {
	

	List<String> list ;

	public CustomConvertDialog(Shell parentShell, List<String> list) {
		super(parentShell);
		this.list = list;
		
	}

	@Override
	protected Control createDialogArea(Composite parent) {
//
//		String paramName = "MAXKHBH";
//
//		setTitle("格式化");
//		setMessage("指定参数");
		Composite area = (Composite) super.createDialogArea(parent);
//		Composite container = new Composite(area, SWT.NONE);
//		container.setLayout(new GridLayout(1, false));

//		Group grpA = new Group(container, SWT.NONE);
//		GridLayout gLayout = new GridLayout(4, false);
//
//		grpA.setLayout(gLayout);
//		grpA.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
//		grpA.setText("转换线id");
//
//			Label label = new Label(grpA, SWT.NONE);
//			label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
//			label.setText("参数名:");
//
//			Label label2 = new Label(grpA, SWT.NONE);
//			label2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
//			label2.setText(paramName);
//			
//			Combo combo = new Combo(grpA, SWT.DROP_DOWN);
//			String[] items = {"补位", "去掉空格"};
//			combo.setItems(items);
//			
//
//		return area;
		
		
	      Composite container = new Composite(area, SWT.NONE);

	      

	      

	      // 假设现在有一个类叫做Hello  继承父类Object

	      // 此时就应该有两棵树

	      Map<String, List<String>> classMethod = new HashMap<String, List<String>>();

	     

	      List<String> helloMethod = new ArrayList<String>();

	      helloMethod.add("sayHello()");

	      helloMethod.add("getHello");

	      helloMethod.add("makeFriends()");

	      classMethod.put("Hello", helloMethod);

	     

	      List<String> objectMethod = new ArrayList<String>();

	      objectMethod.add("wait()");

	      objectMethod.add("notify()");

	      objectMethod.add("toString()");

	      classMethod.put("Object", objectMethod);

	     

	     

	      // 根据类中获取的数据进行树的创建

	      container.setSize(1000, 500);

	     

	      for(Map.Entry<String, List<String>> entry : classMethod.entrySet()) {

	         Tree tree = new Tree(container, SWT.CHECK);

	         tree.setSize(500, 400);

	         TreeItem root = new TreeItem(tree, SWT.NULL);

	         root.setText(entry.getKey());

	         List<String> value = entry.getValue();

	         for (String methodName : value) {

	            TreeItem child = new TreeItem(root, SWT.NULL);

	            child.setForeground(new Color(null, 144,238,144));

	            child.setText(methodName);

	            child.setData(methodName);

	         }

	        

	        

	      }

	 

	      container.setLayout(new GridLayout(1,false));

	      container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	      return area;



	}

}
