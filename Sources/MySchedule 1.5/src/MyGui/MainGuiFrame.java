package MyGui;
//GUI.MainGuiFrame 程序主界面边框类
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import MyFiles.Operation;

public class MainGuiFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private int xx, yy;
	private int left,top;
	private boolean isDraging = false;
	private Operation so;
	private TitlePanel tp = new TitlePanel();
	private DataPanel dp1;
	
	
	public MainGuiFrame()
	{
		super("日程管理");
		//setLayout(new GridLayout(3,0,0,0));
		dp1 = new DataPanel(this);
		so = new Operation();
		this.setBounds(so.getProsistion_x(), so.getProsistion_y(), so.getWidth(), so.getHigh());
		this.setLocationByPlatform(false);
		this.setUndecorated(true);// 去掉窗口的装饰 
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰 风格
		this.setBackground(new Color(197,234,255));
		//添加按钮的响应事件实现最小化以及关闭程序的操作
		tp.close.addActionListener(new ActionClose(this));
		tp.min.addActionListener(new ActionMin(this));
		//初始化titlePanel
		tp.setBounds(0, 0, 435, 20);
		//设置DataPanel
		dp1.setBackground(new Color(197,234,255));
		dp1.setBounds(0, 40, 435, 390);
		this.add(tp);
		//this.add(time_p);
		this.add(dp1);
	//为整个窗体建立事件，响应拖拽操作
		//添加鼠标事件监听，响应鼠标左键放开，设置当前坐标准备下一次拖拽
		this.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		     isDraging = true;
		     xx = e.getX();
		     yy = e.getY();
		    }

		    public void mouseReleased(MouseEvent e) {
		     isDraging = false;//重置拖拽标记
		    }
		   });
			//添加鼠标响应监听器，响应鼠标拖拽，重设当前边框坐标
		   this.addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		     if (isDraging) {
		      left = getLocation().x;
		      top = getLocation().y;
		      setLocation(left + e.getX() - xx, top + e.getY() - yy);
		     }
		    }
		   });
	}
	
	public void resetPanel()
	{
		this.dp1.resetPanel();
	}
	
//按钮点击事件监听器，实现关闭功能
	private class ActionClose implements ActionListener
	{
		private MainGuiFrame mgf;
		public ActionClose(MainGuiFrame mgf)
		{
			this.mgf = mgf;
		}
		public void actionPerformed(ActionEvent event)
		{
			Operation so = new Operation();
			so.setPosistion(mgf.getX(),mgf.getY());
			so.setWidth(mgf.getWidth(), mgf.getHeight());
			so.saveSettings();
			System.exit(0);
		}
	}
	
	//按钮点击事件监听器，实现最小化功能
	private class ActionMin implements ActionListener
	{
		private MainGuiFrame mgf;
		public ActionMin(MainGuiFrame mgf)
		{
			this.mgf = mgf;
		}
		public void actionPerformed(ActionEvent event)
		{
			mgf.setExtendedState(JFrame.ICONIFIED);
		}
	}
}

