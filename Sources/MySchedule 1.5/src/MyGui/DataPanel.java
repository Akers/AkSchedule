package MyGui;
//MyGui.DataPanel 程序主要容器
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import MyFiles.DataReader;

public class DataPanel extends JPanel implements Runnable
{
	public DataPanel(MainGuiFrame mgf)
	{
		setLayout(null);
		this.mgf = mgf;
		//初始化一个label显示当前已经完成事务数量
		label_nums = new JLabel("今日已完成日程："+new DataReader().getFinishedNum(week)+"/"+new DataReader().getSheduleNums(week)+"    ");
		label_time = new JLabel();//初始化一个label显示当前系统时间
		
		//设置label_nums属性
		label_nums.setFont(new Font("黑体",1,13));
		label_nums.setBackground(new Color(197,234,255));
		label_nums.setHorizontalAlignment(JLabel.LEFT);
		label_nums.setBounds(250, 20, 185, 20);
		
		//设置label_time属性
		label_time.setFont(new Font("黑体",0,13));
		label_time.setBackground(new Color(197,234,255));
		label_time.setHorizontalAlignment(JLabel.LEFT);
		label_time.setBounds(0, 20, 250, 20);
		
		//翻页功能组
		lable_page = new JLabel();
		lable_page.setBounds(32, 300, 20, 20);
		lable_page.setAlignmentX(CENTER_ALIGNMENT);
		lable_page.setText(""+(pages+1));
		next = new JButton(new ImageIcon("././images/next.gif"));//下一页按钮
		next.setBounds(45, 300, 20, 20);
		pre = new JButton(new ImageIcon("././images/pre.gif"));//上一夜按钮
		pre.setBounds(5, 300, 20, 20);
		pre.setEnabled(false);
		//添加事件监听器
		next.addActionListener(new NextPagesAction());
		pre.addActionListener(new PreViewPagesAction());
		if(pages == 0 && new DataReader().getSheduleNums(week)<=5)
		{
			next.setEnabled(false);
			pre.setEnabled(false);
		}
		
	//新建日程按钮
		BTN_newschedule = new JButton("新建日程");
		BTN_newschedule.setBounds(340, 305, 87, 25);
		BTN_newschedule.addActionListener(new NewSchedule());
	
		//删除日程按钮
		BTN_delschedule = new JButton("删除日程");
		BTN_delschedule.setBounds(235, 305, 87, 25);
		BTN_delschedule.addActionListener(new DelSchedule());
		
		//修改日程按钮
		BTN_altschedule = new JButton("修改日程");
		BTN_altschedule.setBounds(125, 305, 87, 25);
		BTN_altschedule.addActionListener(new AltSchedule());
		
	//添加控件
		add(pre);
		add(next);
		add(lable_page);
		add(BTN_altschedule);
		add(BTN_newschedule);
		add(BTN_delschedule);
		add(label_time);
		add(label_nums);
		
	//初始化五个TextPane显示当日前五个事务
		for(int i=0;i<5;i++)
		{
			panes[i] = new JTextPane();
			panes[i].setBounds(5, 50+51*i, 320, 50);
			panes[i].setFont(new Font("仿宋体",0,14));
			panes[i].setEditable(false);
			panes[i].setBackground(new Color(197,234,255));
			add(panes[i]);
		}
		resetTextPane();
		
		//初始化五个按钮，单击按钮时标记该处事务为已完成
		for(int i=0;i<5;i++)
		{
			finish[i] = new JButton("完成该事务");
			finish[i].setFont(new Font("宋体",0,10));
			finish[i].setBounds(340, 60 + 48*i, 85, 25);
			finish[i].addActionListener(new FinishAction(i));
			add(finish[i]);
		}
		//重置按钮状态
		resetBtnFinish();
		start();	
	}
	
	//刷新已完成按钮状态
	public void resetBtnFinish()
	{
		for(int i=0;i<5;i++)
		{
			if(new DataReader().getData(week, pages*5+i+1) == null)
				finish[i].setVisible(false);
			else if(new DataReader().IsFinished(week, pages*5+i+1) == 1)
			{
				finish[i].setVisible(true);
				finish[i].setText("已完成");
				finish[i].setEnabled(false);
			}
			else if(new DataReader().IsFinished(week, pages*5+i+1) == 0)
			{
				finish[i].setVisible(true);
				finish[i].setText("完成该事务");
				finish[i].setEnabled(true);
			}
			else
				finish[i].setVisible(false);
		}
	}
	
	//时间设置
	public void setLabel_time_text(String s)
	{
		this.label_time.setText(s);
	}
	
	//重置全部控件
	public void resetPanel()
	{
		resetTextPane();
		updateNextPagesButton();
		updatePreViewButton();
		resetBtnFinish();
		label_nums.setText("今日已完成日程："+new DataReader().getFinishedNum(week)+"/"+new DataReader().getSheduleNums(week)+"    ");
	}
	
	//重置日程显示
	public void resetTextPane()
	{
		for(int i=1;i<=5;i++)
		{
			if(new DataReader().getData(week, pages*5+i) != null)
				panes[i-1].setText("事务"+(pages*5+i)+"："+new DataReader().getData(week, pages*5+i));
			else
				panes[i-1].setText("");
			updateNextPagesButton();
			updatePreViewButton();
		}
	}
	
	//重置下一页按钮状态
	public void updateNextPagesButton()
	{
		if(new DataReader().getData(week, 5*pages+6) == null)
			next.setEnabled(false);
		if(pages>=0)
			pre.setEnabled(true);
	}
	
	//重置上一页按钮状态
	public void updatePreViewButton()
	{
		if(pages <= 0)
			pre.setEnabled(false);
		if(new DataReader().getData(week, 5*pages+6) != null)
			next.setEnabled(true);
	}
	
	//下一页事件监听器
	private class NextPagesAction implements ActionListener
	{
		public NextPagesAction()
		{
			super();
		}
		public void actionPerformed(ActionEvent e)
		{
			pages += 1;
			resetTextPane();
			resetBtnFinish();
			lable_page.setText(""+(pages+1));
			updateNextPagesButton();
		}
	}
	
	//上一页事件监听器
	private class PreViewPagesAction implements ActionListener
	{
		public PreViewPagesAction()
		{
			super();
		}
		public void actionPerformed(ActionEvent e)
		{
			pages -= 1;
			resetTextPane();
			resetBtnFinish();
			lable_page.setText(""+(pages+1));
			updatePreViewButton();
		}
	}
	
	//事务完成按钮事件监听器
	private class FinishAction implements ActionListener
	{
		private int finished;
		public FinishAction(int finished)
		{
			super();
			this.finished = finished;
		}
		public void actionPerformed(ActionEvent e)
		{
			DataReader dr = new DataReader();
			panes[finished].setText("事务"+(finished+pages*5+1)+"："+"（已完成）"+dr.getData(week, finished+pages*5+1));
			dr.ModifySchedule(week, finished+pages*5+1, "（已完成）"+dr.getData(week, finished+pages*5+1));
			dr.saveData();
			finish[finished].setText("已完成");
			finish[finished].setEnabled(false);
			stop();
			label_nums.setText("今日已完成日程："+new DataReader().getFinishedNum(week)+"/"+new DataReader().getSheduleNums(week)+"    ");
			start();
		}
	}
	
	//修改日程按钮时间监听器
	private class AltSchedule implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new AltDilog(mgf).setVisible(true);
		}
	}
	
	
	//新建日程按钮事件监听器
	private class NewSchedule implements ActionListener
	{
		public NewSchedule()
		{
			super();
		}
		public void actionPerformed(ActionEvent e)
		{
			new InputDilog(mgf).setVisible(true);
		}	
	}
	
	//删除日程按钮事件监听器
	private class DelSchedule implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new DelDilog(mgf).setVisible(true);
		}
	}
	
	public void start()
	{
		if(clock == null)
		{
			clock = new Thread(this);
			clock.start();
		}
	}
	
	public void run()
	{
		while(clock != null)
		{
			this.setLabel_time_text(new SimpleDateFormat(" yyyy年 MM月 dd日 E   HH:mm:ss").format(new Date()));
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void stop()
	{
		clock = null;
	}
	
	//属性
	private static final long serialVersionUID = 4L;
	private JButton[] finish = new JButton[5];
	private JButton next;
	private JButton pre;
	private JButton BTN_newschedule;
	private JButton BTN_delschedule;
	private JButton BTN_altschedule;
	private JTextPane[] panes = new JTextPane[5];
	private String week = new SimpleDateFormat("E").format(new Date());
	private int pages = 0;//记录页数，0为第一页，以此类推
	private JLabel label_time;
	private JLabel label_nums;
	private Thread clock;
	private JLabel lable_page;
	private MainGuiFrame mgf;
}	

