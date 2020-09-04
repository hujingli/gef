package com.spdb.build.gef.editor;

import java.util.ArrayList;
import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ResourceLocator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.spdb.build.gef.Activator;
import com.spdb.build.gef.command.action.CopyNodeAction;
import com.spdb.build.gef.command.action.PasteNodeAction;
import com.spdb.build.gef.command.action.RenameAction;
import com.spdb.build.gef.command.creation.NodeCreationFactory;
import com.spdb.build.gef.editor.listener.MyTemplateTransferDropTargetListener;
import com.spdb.build.gef.factory.AppEditPartFactory;
import com.spdb.build.gef.factory.tree.AppTreeEditPartFactory;
import com.spdb.build.gef.menu.AppContextMenuProvider;
import com.spdb.build.gef.model.Employee;
import com.spdb.build.gef.model.Enterprise;
import com.spdb.build.gef.model.Service;
import com.spdb.build.gef.model.line.ArrowConnectioinModel;
import com.spdb.build.gef.model.line.PlainConnectionModel;
import com.spdb.build.gef.utils.GefCoreTool;
import com.thoughtworks.xstream.XStream;

/**
 * 图形编辑器 (需要在plugin.xml文件中进行配置添加扩展点editors
 * 然后在ApplicationWorkbenchAdvisor的postStartup方法中添加)
 * 
 * @author huh20
 *
 */
public class MyGraphicalEditor extends GraphicalEditorWithFlyoutPalette {

	private KeyHandler keyHandler;
	private Enterprise model;

	// 添加id 为后续作为扩展点使用
	public static final String ID = "WBHGEF.mygraphicaleditor";

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void initializeGraphicalViewer() {

		GraphicalViewer viewer = getGraphicalViewer();
		model = createEnterprise();
		viewer.setContents(model);

		// 添加拖放目标监听器
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(viewer));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
		// 创建文件
		XStream xStream = GefCoreTool.getXStream();
		xStream.setClassLoader(Activator.class.getClassLoader());
		String xml = xStream.toXML(model);
		System.out.println(xml + "------------ ");
		
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();

		// 设置自定义的editpart工厂对象
		viewer.setEditPartFactory(new AppEditPartFactory());

		// 设置图形缩放
		double[] zoomLevels = new double[] { 0.25, .05, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0, 20.0 };
		ArrayList<String> zoomContributions = new ArrayList<String>();

		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);

		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));

		manager.setZoomLevels(zoomLevels);
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		// 添加键盘事件
		keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));

		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));

		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), MouseWheelZoomHandler.SINGLETON);
		viewer.setKeyHandler(keyHandler);

		// 配置环境菜单
		ContextMenuProvider provider = new AppContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);

	}

	/**
	 * 该方法用于检验嵌套类的调用 如下面定义的大纲视图
	 */
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getZoomManager();
		}
		if (type == IContentOutlinePage.class) {
			return new OutlinePage();
		}
		return super.getAdapter(type);

	}

	/**
	 * 将自定义的action进行注册
	 */
	@Override
	protected void createActions() {
		super.createActions();

		ActionRegistry registry = getActionRegistry();
		IAction renameAction = new RenameAction(this);
		registry.registerAction(renameAction);
		getSelectionActions().add(renameAction.getId());

		IAction copyAction = new CopyNodeAction(this);
		registry.registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());

		IAction pasteAction = new PasteNodeAction(this);
		registry.registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());

	}

	/**
	 * 创建企业模型对象
	 * 
	 * @return
	 */
	public Enterprise createEnterprise() {

		Enterprise enterprise = new Enterprise();

		// 企业
		enterprise.setName("同福客栈");
		enterprise.setAddress("成都市成华区二环路东二段22号");
		enterprise.setCapital(800000);

		// 部门
		Service service_QianTang = new Service();
		service_QianTang.setName("前堂");
		service_QianTang.setEtage(2);
		service_QianTang.setLayout(new Rectangle(30, 50, 250, 150));

		// 雇员
		Employee employee_1 = new Employee();
		employee_1.setName("掌柜");
		employee_1.setPrenom("佟");
		employee_1.setLayout(new Rectangle(25, 40, 60, 40));
		service_QianTang.addChild(employee_1);

		// 雇员
		Employee employee_2 = new Employee();
		employee_2.setName("展堂");
		employee_2.setPrenom("白");
		employee_2.setLayout(new Rectangle(100, 60, 60, 40));
		service_QianTang.addChild(employee_2);

		// 雇员
		Employee employee_3 = new Employee();
		employee_3.setName("秀才");
		employee_3.setPrenom("吕");
		employee_3.setLayout(new Rectangle(180, 90, 60, 40));
		service_QianTang.addChild(employee_3);

		enterprise.addChild(service_QianTang);

		// 部门
		Service service_HouChu = new Service();
		service_HouChu.setName("后厨");
		service_HouChu.setEtage(1);
		service_HouChu.setLayout(new Rectangle(220, 230, 250, 150));

		// 雇员
		Employee employee_4 = new Employee();
		employee_4.setName("大嘴");
		employee_4.setPrenom("李");
		employee_4.setLayout(new Rectangle(40, 70, 60, 40));
		service_HouChu.addChild(employee_4);

		Employee employee_5 = new Employee();
		employee_5.setName("芙蓉");
		employee_5.setPrenom("郭");
		employee_5.setLayout(new Rectangle(170, 100, 60, 40));
		service_HouChu.addChild(employee_5);

		enterprise.addChild(service_HouChu);

		// 添加连接线
		PlainConnectionModel connection = new PlainConnectionModel();
		connection.setSrc(service_QianTang);
		connection.setTarget(service_HouChu);
		connection.attachSource();
		connection.attachTarget();

		return enterprise;
	}

	protected class OutlinePage extends ContentOutlinePage {

		private SashForm sash;

		// 实现缩小（鸟瞰图）
		private ScrollableThumbnail scrollableThumbnail;
		private DisposeListener disposeListener;

		public OutlinePage() {
			super(new TreeViewer());

		}

		public void createControl(Composite parent) {

			// 创建窗框对象 分割窗体
			sash = new SashForm(parent, SWT.VERTICAL);

			getViewer().createControl(sash);
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			getViewer().setContents(model);

			getSelectionSynchronizer().addViewer(getViewer());

			// 添加鸟瞰图
			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lwSystem = new LightweightSystem(canvas);
			scrollableThumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getFigure());

			scrollableThumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer().getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));

			lwSystem.setContents(scrollableThumbnail);
			disposeListener = new DisposeListener() {

				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (scrollableThumbnail != null) {
						scrollableThumbnail.deactivate();
						scrollableThumbnail = null;
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(disposeListener);

			IActionBars bars = getSite().getActionBars();
			ActionRegistry ar = getActionRegistry();

			bars.setGlobalActionHandler(ActionFactory.COPY.getId(), ar.getAction(ActionFactory.COPY.getId()));
			bars.setGlobalActionHandler(ActionFactory.PASTE.getId(), ar.getAction(ActionFactory.PASTE.getId()));
		}

		public void init(IPageSite pageSite) {
			super.init(pageSite);

			IActionBars bars = getSite().getActionBars();

			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
					getActionRegistry().getAction(ActionFactory.UNDO.getId()));

			bars.setGlobalActionHandler(ActionFactory.REDO.getId(),
					getActionRegistry().getAction(ActionFactory.REDO.getId()));

			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					getActionRegistry().getAction(ActionFactory.DELETE.getId()));

			bars.updateActionBars();
			getViewer().setKeyHandler(keyHandler);

			// 在大纲视图中设置环境菜单
			ContextMenuProvider provider = new AppContextMenuProvider(getViewer(), getActionRegistry());
			getViewer().setContextMenu(provider);
		}

		@Override
		public Control getControl() {
			return sash;
		}

		@Override
		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (getGraphicalViewer().getControl() != null && !getGraphicalViewer().getControl().isDisposed()) {
				getGraphicalViewer().getControl().removeDisposeListener(disposeListener);
			}
			super.dispose();
		}

	}

	@Override
	protected PaletteRoot getPaletteRoot() {

		PaletteRoot root = new PaletteRoot();
		PaletteGroup maniGroup = new PaletteGroup("编辑对象工具");
		root.add(maniGroup);

		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		maniGroup.add(selectionToolEntry);
		maniGroup.add(new MarqueeToolEntry());

		root.setDefaultEntry(selectionToolEntry);

		PaletteSeparator sep = new PaletteSeparator();
		root.add(sep);

		PaletteGroup instGroup = new PaletteGroup("创建元素工具");
		root.add(instGroup);

		instGroup.add(new CombinedTemplateCreationEntry("部门", "创建一个部门", new NodeCreationFactory(Service.class),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/部门.png").get(),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/部门.png").get()));

		instGroup.add(new CombinedTemplateCreationEntry("人员", "创建一个员工", new NodeCreationFactory(Employee.class),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/人员添加.png").get(),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/人员添加.png").get()));

		PaletteSeparator lineSep = new PaletteSeparator();
		root.add(lineSep);
		PaletteGroup connectionGroup = new PaletteGroup("创建连线工具");
		root.add(connectionGroup);

		ConnectionCreationToolEntry plainConnection = new ConnectionCreationToolEntry("直线", "普通的直连线",
				new SimpleFactory(PlainConnectionModel.class),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/line.png").get(),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/line.png").get());
		connectionGroup.add(plainConnection);

		// 带箭头的直线
		ConnectionCreationToolEntry arrowConnection = new ConnectionCreationToolEntry("箭头", "带箭头的直线",
				new SimpleFactory(ArrowConnectioinModel.class),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/jiantou.png").get(),
				ResourceLocator.imageDescriptorFromBundle(Activator.PLUGIN_ID, "icons/jiantou.png").get());
		connectionGroup.add(arrowConnection);

		return root;
	}

	/**
	 * 初始化画图板
	 */
//	@Override
//	protected void initializePaletteViewer() {
//		super.initializePaletteViewer();
//		
//		// 给画图板添加拖放源
//		getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getPaletteViewer()));
//	}

	@Override
	protected PaletteViewerProvider createPaletteViewerProvider() {

		return new PaletteViewerProvider(getEditDomain()) {
			@Override
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
			}
		};
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		super.createPartControl(parent);
	}

	/**
	 * 变更标记
	 */
	@Override
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		firePropertyChange(PROP_DIRTY);
	}
//	public static void main(String[] args) throws Exception { 
//	      String content = read("/Users/exphuhong/IdeaProjects/first/src/com/hujingli/Test.java"); //java源文件
//	      //创建解析器  
//	      ASTParser parsert = ASTParser.newParser(AST.JLS12); 
//	      //设定解析器的源代码字符  
//	      parsert.setSource(content.toCharArray()); 
//	      //使用解析器进行解析并返回AST上下文结果(CompilationUnit为根节点)  
//	      CompilationUnit result = (CompilationUnit) parsert.createAST(null); 
//	 
//	      //获取类型  
//	      List types = result.types(); 
//	      //取得类型声明  
//	      TypeDeclaration typeDec = (TypeDeclaration) types.get(0); 
//	 
//	      //##############获取源代码结构信息#################  
//	      //引用import  
//	      List importList = result.imports(); 
//	      //取得包名  
//	      PackageDeclaration packetDec = result.getPackage(); 
//	      //取得类名  
//	      String className = typeDec.getName().toString(); 
//	      //取得函数(Method)声明列表  
//	      MethodDeclaration methodDec[] = typeDec.getMethods(); 
//	      //取得函数(Field)声明列表  
//	      FieldDeclaration fieldDec[] = typeDec.getFields(); 
//	 
//	      
//	      
//	      //输出包名  
//	      System.out.println("包:"); 
//	      System.out.println(packetDec.getName()); 
//	      //输出引用import  
//	      System.out.println("引用import:"); 
//	      for (Object obj : importList) { 
//	          ImportDeclaration importDec = (ImportDeclaration) obj; 
//	          System.out.println(importDec.getName()); 
//	      } 
//	      //输出类名  
//	      System.out.println("类:"); 
//	      System.out.println(className);
//	      
//	      typeDec.getSuperclassType();
//	      
//	      //循环输出函数名称  
//	      System.out.println("========================"); 
//	      System.out.println("函数:"); 
//	      for (MethodDeclaration method : methodDec) { 
//	         /* System.out.println(method.getName()); 
//	          System.out.println("body:"); 
//	          System.out.println(method.getBody()); 
//	          System.out.println("Javadoc:" + method.getJavadoc()); 
//	 
//	          System.out.println("Body:" + method.getBody()); 
//	 
//	          System.out.println("ReturnType:" + method.getReturnType());*/ 
//	          System.out.println("============="); 
//	          System.out.println(method); 
//	      } 
//	 
//	      //循环输出变量  
//	      System.out.println("变量:"); 
//	      for (FieldDeclaration fieldDecEle : fieldDec) { 
//	          //public static  
//	          for (Object modifiObj : fieldDecEle.modifiers()) { 
//	              Modifier modify = (Modifier) modifiObj; 
//	              System.out.print(modify + "-"); 
//	          } 
//	          System.out.println(fieldDecEle.getType()); 
//	          for (Object obj : fieldDecEle.fragments()) { 
//	              VariableDeclarationFragment frag = (VariableDeclarationFragment) obj; 
//	              System.out.println("[FIELD_NAME:]" + frag.getName()); 
//	          } 
//	      } 
//	  } 
//	 
//	  private static String read(String filename) throws IOException { 
//	      File file = new File(filename); 
//	      byte[] b = new byte[(int) file.length()]; 
//	      FileInputStream fis = new FileInputStream(file); 
//	      fis.read(b); 
//	      return new String(b); 
//	 
//	  } 

}
