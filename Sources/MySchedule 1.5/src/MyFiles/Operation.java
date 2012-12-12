package MyFiles;
//MyFiles.Operation 设置数据的读取存储类
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

public class Operation
{
	private String file = "././settings/settings.dat";
	private Setting setting;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	public Operation()
	{
		setting = new Setting();
		try
		{
			ois = new ObjectInputStream(new FileInputStream(file));
			setting = (Setting)ois.readObject();
			ois.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "文件操作出错，程序关闭！！"+"\n"+e.getMessage(),"程序错误",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
	public void setWidth(int width,int height)
	{
		setting.width = width;
		setting.height = height;
	}
	public void setPosistion(int x,int y)
	{
		setting.x = x;
		setting.y = y;
	}
	public int getWidth()
	{
 		return setting.width;
	}
	public int getHigh()
	{
 		return setting.height;
	}
	public int getProsistion_x()
	{
		return setting.x;
	}
	public int getProsistion_y()
	{
		return setting.y;
	}
	public void saveSettings()
	{
		try
		{
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(setting);
			oos.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "文件操作出错，程序关闭！！"+"\n"+e.getMessage(),"程序错误",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}
