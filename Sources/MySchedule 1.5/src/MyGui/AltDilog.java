package MyGui;
//修改日程对话框
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import MyFiles.Operation;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import MyFiles.DataReader;

public class AltDilog extends JFrame
{
	public AltDilog(MainGuiFrame mgf)
	{
		super();
		this.dp = mgf;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(new Operation().getProsistion_x(), new Operation().getProsistion_y(), 300, 250);
		this.setTitle("修改日程");
		this.setResizable(false);
		dr = new DataReader();
		ComBox_week = new JComboBox();
		ComBox_index = new JComboBox();
		panel_comboxs = new JPanel();
		panel_buttons = new JPanel();
		panel_input = new JPanel();
		label = new JLabel("日程：");
		BTN_del = new JButton("确认修改");
		BTN_clr = new JButton("取消");
		TXT_sch = new JTextPane();
		
		
		//初始化日程序号组合框
		ComBox_index.setEditable(false);
		ComBox_index.addItem(0);
		ComBox_index.addItemListener(new ComBoxIndex());
		
		//初始化星期组合框
		ComBox_week.addItem("null");
		ComBox_week.addItem("星期一");
		ComBox_week.addItem("星期二");
		ComBox_week.addItem("星期三");
		ComBox_week.addItem("星期四");
		ComBox_week.addItem("星期五");
		ComBox_week.addItem("星期六");
		ComBox_week.addItem("星期日");
		ComBox_week.addActionListener(new ComBoxWeek());
		
		//修改日程按钮
		BTN_del.setBounds(10, 5, 100, 25);
		BTN_del.addActionListener(new AltAction());
		//取消按钮
		BTN_clr.setBounds(180, 5, 100, 25);
		BTN_clr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		
		//添加控件
		panel_comboxs.setLayout(null);
		ComBox_week.setBounds(20, 10, 100, 25);
		ComBox_index.setBounds(200, 10, 50, 25);
		label.setBounds(150, 10, 100, 25);
		TXT_sch.setBounds(10, 20, 260, 100);
		TXT_sch.setEditable(true);
		panel_comboxs.add(ComBox_week);
		panel_comboxs.add(ComBox_index);
		panel_comboxs.add(label);
		panel_input.add(TXT_sch);
		
		//组合框所在面板
		panel_comboxs.setBounds(0, 0, 300, 50);
		
		//输入框所在面板
		Border input = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "即将修改的日程");
		TXT_sch.registerKeyboardAction(new AltAction(),KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),JComponent.WHEN_FOCUSED);
		panel_input.add(TXT_sch);
		panel_input.setBorder(input);
		panel_input.setLayout(null);
		panel_input.setBounds(5, 50, 280, 130);
		
		
		//按钮所在面板
		panel_buttons.setLayout(null);
		panel_buttons.setBounds(0, 180, 300, 70);
		panel_buttons.add(this.BTN_del);
		panel_buttons.add(this.BTN_clr);
		
		//添加面板
		this.add(panel_comboxs);
		this.add(panel_input);
		this.add(panel_buttons);
	}
	
	//修改所选日程为当前文本区域内的内容
	public void altit(String week, int index, String new_sch)
	{
		new_sch = new_sch.replaceAll("[\n\r]", "");
		dr.ModifySchedule(week, index, new_sch);
		dr.saveData();
		TXT_sch.setText("");
		dp.resetPanel();
		dp.repaint();
	}
	
	
	//星期选择组合框事件监听器
	private class ComBoxWeek implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String week = (String)ComBox_week.getSelectedItem();
			if(!week.equalsIgnoreCase("null"))
			{
				ComBox_index.removeAllItems();
				for(int i=0;i<=dr.getSheduleNums( week);i++)
				{
					ComBox_index.addItem(i);
				}
			}
		}
	}
	
	//日程索引选择组合框事件监听器
	private class ComBoxIndex implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			int index;
			try
			{
				index = (Integer)ComBox_index.getSelectedItem();
			}
			catch(NullPointerException exception)
			{
				index = 0;
			}
			if(index != 0)
			{
				TXT_sch.setText( dr.getData((String)ComBox_week.getSelectedItem(), index));
			}
		}
	}
	
	//修改日程按钮事件监听器
	private class AltAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String week = (String)ComBox_week.getSelectedItem();
			int index = (Integer)ComBox_index.getSelectedItem();
			if(!week.equalsIgnoreCase("null") && index != 0)
			{
				int res = JOptionPane.showConfirmDialog(null,"是否确认要将日程“"+dr.getData(week, index)+"”替换成“"+TXT_sch.getText()+"”？","修改日程",JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION)
				{
					altit( week, index, TXT_sch.getText());
				}
			}
		}
	}
	
	//属性
	private JComboBox ComBox_week;
	private JComboBox ComBox_index;
	private JButton BTN_del;
	private JButton BTN_clr;
	private JLabel label;
	private JTextPane TXT_sch;
	private DataReader dr;
	private JPanel panel_comboxs;
	private JPanel panel_input;
	private JPanel panel_buttons;
	private MainGuiFrame dp;
	private static final long serialVersionUID = 6205739335490614358L;
}
