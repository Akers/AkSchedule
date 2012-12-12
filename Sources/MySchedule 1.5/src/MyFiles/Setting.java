package MyFiles;
//MyFile.Setting 可序列化类，用于保存设置
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;

public class Setting implements Serializable
{
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int width;
	int height;
	public Setting()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		x = (int)(screenSize.width *0.6);
		y = (int)(screenSize.height * 0.1);
		width = 435;
		height = 350;
	}
	public Setting(int x,int y,int width,int hight)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = hight;
	}
}
