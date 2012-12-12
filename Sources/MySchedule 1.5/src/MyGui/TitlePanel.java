package MyGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TitlePanel extends JPanel
{
	private static final long serialVersionUID = 2L;
	private ImageIcon icon_close;
	private ImageIcon icon_min;
	JButton close;
	JButton min;
	
	public TitlePanel()
	{
		icon_close = new ImageIcon("././images/close.gif");
		icon_min = new ImageIcon("././images/min.gif");
		close = new JButton();
		min = new JButton(); 
		add(close);
		add(min);
		
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(51,181,255));
		g2d.fillRect(0, 0, 435, 22);
		g.setFont(new Font("dialog",1,13));
		g.setColor(Color.BLACK);
		g.drawString("日程事务管理器 beta 1.5 by Akers", 0, 15);
		close.setSize(20, 20);
		close.setLocation(409, 1);
		close.setIcon(icon_close);
		min.setSize(20, 20);
		min.setLocation(385, 1);
		min.setIcon(icon_min);
	}
}

