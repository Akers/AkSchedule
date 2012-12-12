package MyGui;
//新建日程对话框
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import MyFiles.DataReader;
import MyFiles.Operation;

public class InputDilog extends JFrame
{
	public InputDilog(MainGuiFrame dp)
	{
		super();
		this.dp = dp;
		setSize(300,250);
		panel = new JPanel();
		pane_input = new JPanel();
		buttons = new JPanel();
		label = new JLabel("增加事务至：");
		TXT_input = new JTextPane();
		cb = new JComboBox();
		newSchedule = new JButton("新建日程");
		clean = new JButton("取消");
		this.setResizable(false);
		this.setLayout(null);
		this.setLocation(new Operation().getProsistion_x(), new Operation().getProsistion_y());
		this.setTitle("日程创建");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.requestFocus();
		cb.addItem("星期一");
		cb.addItem("星期二");
		cb.addItem("星期三");
		cb.addItem("星期四");
		cb.addItem("星期五");
		cb.addItem("星期六");
		cb.addItem("星期日");
		cb.setEditable(false);
		cb.setVisible(true);
		panel.setLayout(null);
		label.setBounds(5, 5, 100, 20);
		panel.add(label);
		cb.setBounds(110, 5, 100, 20);
		panel.add(cb);
		panel.setBounds(0, 0, 300, 32);
		TXT_input.setBounds(6, 26, 275, 90);
		TXT_input.addKeyListener(new KeyListener()
		{

			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					newschedule();
			}

			public void keyReleased(KeyEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
		//设置输入框所在面板
		Border input = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "新建日程");
		pane_input.add(TXT_input);
		pane_input.setBorder(input);
		pane_input.setLayout(null);
		pane_input.setBounds(1, 40, 290, 130);
		//新建日程按钮
		newSchedule.addActionListener(new AddSch());
		newSchedule.setBounds(30, 0, 90, 25);
		newSchedule.setVisible(true);
		//取消输入按钮
		clean.addActionListener(new Close());
		clean.setVisible(true);
		clean.setBounds(160, 0, 90, 25);
		//设置按钮所在面板
		buttons.setLayout(null);
		buttons.setBounds(0, 170, 300, 50);
		//添加按钮
		buttons.add(newSchedule);
		buttons.add(clean);
		add(panel);
		add(pane_input);
		add(buttons);
	}

	//新建事务
	public void newschedule()
	{
		String s = TXT_input.getText().replaceAll("[\n\r]", "");
		if(s.equals(""))
			JOptionPane.showMessageDialog(null, "输入框为空，请输入日程内容，然后再次点击此按钮", "输入错误", JOptionPane.INFORMATION_MESSAGE);
		else
		{
			dr = new DataReader();
			dr.addSchedule(new SimpleDateFormat("E").format(new Date()), s);
			dr.saveData();
			TXT_input.setText("");
			JOptionPane.showMessageDialog(null, "日程添加成功", "输入错误", JOptionPane.INFORMATION_MESSAGE);
			dp.resetPanel();
			dp.repaint();
		}
	}
	
	//取消按钮事件监听器
	private class Close implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
	
	//增加日程按钮事件监听器
	private class AddSch implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			newschedule();
		}
		
	}
	
	//属性
	private static final long serialVersionUID = 8342212293757871904L;
	private JComboBox cb;
	private JLabel label;
	private JPanel panel;
	private JPanel pane_input;
	private JPanel buttons;
	private JTextPane TXT_input;
	private JButton newSchedule;
	private JButton clean;
	private DataReader dr;
	private MainGuiFrame dp;
}
