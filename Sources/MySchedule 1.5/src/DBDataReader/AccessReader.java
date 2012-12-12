package DBDataReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AccessReader
{
	public AccessReader()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(dbURL);
			Statement sql;
			String str_com = "select * from schedule";//数据库操作字符串
			sql = con.createStatement();
			//String com_ins = "insert into schedule(s_week,schedule,user) values('星期一','继续日国枢菊花','akers')";
			//sql.execute(com_ins);
			ResultSet rs = sql.executeQuery(str_com);
			while(rs.next())
			{
				System.out.println(rs.getString("schedule"));
			}
		} 
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "数据库连接出错:"+e.getMessage(), "程序出错", JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "驱动加载失败："+e.getMessage(), "出错啦！！", JOptionPane.WARNING_MESSAGE);
		}
	}
	public static void main(String[] args)
	{
		new AccessReader();
	}
	
	private Connection con;
	private String dbURL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=Schedule.mdb";
}
