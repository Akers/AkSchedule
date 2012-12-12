package MyFiles;
//MyFiles.DataReader  日程数据操作类

import java.util.Vector;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class DataReader
{
	private Schedule schedule;
	private BufferedReader br;
	private Vector<String> datas_in;//输入数据
	public DataReader()
	{
		schedule = new Schedule();
		try
		{
			br = new BufferedReader(new FileReader("././schedule.txt"));
			datas_in = new Vector<String>();
			String temp;
			//取得文件内容
			temp = br.readLine();
			do
			{
				datas_in.add(temp);
				temp = br.readLine();
			}
			while(temp != null);
			
			Vector<String> all_lines = datas_in;
			//拆分数据
			if(all_lines.indexOf("[Monday]") != all_lines.indexOf("[Tuesday]"))
				schedule.mon.addAll(all_lines.subList(all_lines.indexOf("[Monday]")+1, all_lines.indexOf("[Tuesday]")));
			
			if(all_lines.indexOf("[Tuesday]")+1 != all_lines.indexOf("[Wednesday]"))
				schedule.tue.addAll(all_lines.subList(all_lines.indexOf("[Tuesday]")+1, all_lines.indexOf("[Wednesday]")));
			
			if(all_lines.indexOf("[Wednesday]")+1 != all_lines.indexOf("[Thursday]"))
				schedule.wed.addAll(all_lines.subList(all_lines.indexOf("[Wednesday]")+1, all_lines.indexOf("[Thursday]")));
			
			if(all_lines.indexOf("[Thursday]")+1 != all_lines.indexOf("[Friday]"))
				schedule.thu.addAll(all_lines.subList(all_lines.indexOf("[Thursday]")+1, all_lines.indexOf("[Friday]")));
			
			if(all_lines.indexOf("[Friday]")+1 != all_lines.indexOf("[Saturday]"))
				schedule.fri.addAll(all_lines.subList(all_lines.indexOf("[Friday]")+1, all_lines.indexOf("[Saturday]")));
			
			if(all_lines.indexOf("[Saturday]")+1 != all_lines.indexOf("[Sunday]"))
				schedule.sat.addAll(all_lines.subList(all_lines.indexOf("[Saturday]")+1, all_lines.indexOf("[Sunday]")));
			
			if(all_lines.indexOf("[Sunday]")+1 != all_lines.indexOf("[End]"))
				schedule.sun.addAll(all_lines.subList(all_lines.indexOf("[Sunday]")+1, all_lines.indexOf("[End]")));
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	//读取当日日程可以以中文或英文星期来作为索引参数
	public String getData(String week,int index)
	{	
		if(week.trim().equalsIgnoreCase("monday") || week.trim().equals("星期一"))
		{
			if(index <= this.schedule.mon.size())
				return this.schedule.mon.get(index-1);
			else
				return null;
		}
		if(week.trim().equalsIgnoreCase("tuesday") || week.trim().equals("星期二"))
		{
			if(index <= this.schedule.tue.size())
				return this.schedule.tue.get(index-1);
			else
				return null;
		}
		if(week.trim().equalsIgnoreCase("wednesday") || week.trim().equals("星期三"))
		{
			if(index <= this.schedule.wed.size())
				return this.schedule.wed.get(index-1);
			else
				return null;
		}
		if(week.trim().equalsIgnoreCase("thursday") || week.trim().equals("星期四"))
		{
			if(index <= this.schedule.thu.size())
				return this.schedule.thu.get(index-1);
			else
				return null;
		}
		if(week.trim().equalsIgnoreCase("friday") || week.trim().equals("星期五"))
		{
			if(index <= this.schedule.fri.size())
				return this.schedule.fri.get(index-1);
			return null;
		}
		if(week.trim().equalsIgnoreCase("saturday") || week.trim().equals("星期六"))
		{
			if(index <= this.schedule.sat.size())
				return this.schedule.sat.get(index-1);
			return null;
		}
		if(week.trim().equalsIgnoreCase("sunday") || week.trim().equals("星期日"))
		{
			if(index <= this.schedule.sun.size())
				return this.schedule.sun.get(index-1);
			return null;
		}
		return "传入参数错误-->传入参数必须是英文形式或者中文形式的星期标识！！";
	}
	
	//判断该日程是否已经完成
	public int IsFinished(String day,int index)
	{
		try
		{
			if(day.trim().equalsIgnoreCase("monday") || day.trim().equals("星期一"))
				if(this.schedule.mon.get(index-1).startsWith("（已完成）"))
					return 1;
			if(day.trim().equalsIgnoreCase("tuesday") || day.trim().equals("星期二"))
				if(this.schedule.tue.get(index-1).startsWith("（已完成）"))
					return 1;
			if(day.trim().equalsIgnoreCase("wednesday") || day.trim().equals("星期三"))
				if(this.schedule.wed.get(index-1).startsWith("（已完成）"))
					return 1;
			if(day.trim().equalsIgnoreCase("thursday") || day.trim().equals("星期四"))
				if(this.schedule.thu.get(index-1).startsWith("（已完成）"))
					return 1;
			if(day.trim().equalsIgnoreCase("friday") || day.trim().equals("星期五"))
				if(this.schedule.fri.get(index-1).startsWith("（已完成）"))
					return 1;
			if(day.trim().equalsIgnoreCase("saturday") || day.trim().equals("星期六"))
				if(this.schedule.sat.get(index-1).startsWith("（已完成）"))
					return 1;
			if(day.trim().equalsIgnoreCase("sunday") || day.trim().equals("星期日"))
				if(this.schedule.sun.get(index-1).startsWith("（已完成）"))
					return 1;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return -1;
		}
		return 0;
	}
	
//新建日程
	public void addSchedule(String day,String item)
	{
		if(day.trim().equalsIgnoreCase("monday") || day.trim().equals("星期一"))
				this.schedule.mon.add(item);
		if(day.trim().equalsIgnoreCase("tuesday") || day.trim().equals("星期二"))
			this.schedule.tue.add(item);
		if(day.trim().equalsIgnoreCase("wednesday") || day.trim().equals("星期三"))
			this.schedule.wed.add(item);
		if(day.trim().equalsIgnoreCase("thursday") || day.trim().equals("星期四"))
			this.schedule.thu.add(item);
		if(day.trim().equalsIgnoreCase("friday") || day.trim().equals("星期五"))
			this.schedule.fri.add(item);
		if(day.trim().equalsIgnoreCase("saturday") || day.trim().equals("星期六"))
			this.schedule.sat.add(item);
		if(day.trim().equalsIgnoreCase("sunday") || day.trim().equals("星期日"))
			this.schedule.sun.add(item);
	}
	
	//取得当日日程数量
	public int getSheduleNums(String day)
	{
		if(day.trim().equalsIgnoreCase("monday") || day.trim().equals("星期一"))
				return this.schedule.mon.size();
		if(day.trim().equalsIgnoreCase("tuesday") || day.trim().equals("星期二"))
			return this.schedule.tue.size();
		if(day.trim().equalsIgnoreCase("wednesday") || day.trim().equals("星期三"))
			return this.schedule.wed.size();
		if(day.trim().equalsIgnoreCase("thursday") || day.trim().equals("星期四"))
			return this.schedule.thu.size();
		if(day.trim().equalsIgnoreCase("friday") || day.trim().equals("星期五"))
			return this.schedule.fri.size();
		if(day.trim().equalsIgnoreCase("saturday") || day.trim().equals("星期六"))
			return this.schedule.sat.size();
		if(day.trim().equalsIgnoreCase("sunday") || day.trim().equals("星期日"))
			return this.schedule.sun.size();
		return -1;
	}
	
	//取得当日已完成事务数量
	public int getFinishedNum(String day)
	{
		int finished = 0;
		for(int i=1;i<=getSheduleNums(day);i++)
		{
			if(IsFinished(day, i) == 1)
			{
				finished  += 1;
			}
		}
		return finished;
	}
	
	//修改日程
	public boolean ModifySchedule(String day,int index,String s)
	{
		if(day.trim().equalsIgnoreCase("monday") || day.trim().equals("星期一"))
		{
			this.schedule.mon.set(index-1, s);
			return true;
		}
		if(day.trim().equalsIgnoreCase("tuesday") || day.trim().equals("星期二"))
		{
			this.schedule.tue.set(index-1, s);
			return true;
		}
		if(day.trim().equalsIgnoreCase("wednesday") || day.trim().equals("星期三"))
		{
			this.schedule.wed.set(index-1, s);
			return true;
		}
		if(day.trim().equalsIgnoreCase("thursday") || day.trim().equals("星期四"))
		{
			this.schedule.thu.set(index-1, s);
			return true;
		}
		if(day.trim().equalsIgnoreCase("friday") || day.trim().equals("星期五"))
		{
			this.schedule.fri.set(index-1, s);
			return true;
		}
		if(day.trim().equalsIgnoreCase("saturday") || day.trim().equals("星期六"))
		{
			this.schedule.sat.set(index-1, s);
			return true;
		}
		if(day.trim().equalsIgnoreCase("sunday") || day.trim().equals("星期日"))
		{
			this.schedule.sun.set(index-1, s);
			return true;
		}
		return false;
	}
	
	//删除日程
	public void deleSchedule(String day,int index)
	{
		if(day.trim().equalsIgnoreCase("monday") || day.trim().equals("星期一"))
			this.schedule.mon.remove(index-1);
		if(day.trim().equalsIgnoreCase("tuesday") || day.trim().equals("星期二"))
			this.schedule.tue.remove(index-1);
		if(day.trim().equalsIgnoreCase("wednesday") || day.trim().equals("星期三"))
			this.schedule.wed.remove(index-1);
		if(day.trim().equalsIgnoreCase("thursday") || day.trim().equals("星期四"))
			this.schedule.thu.remove(index-1);
		if(day.trim().equalsIgnoreCase("friday") || day.trim().equals("星期五"))
			this.schedule.fri.remove(index-1);
		if(day.trim().equalsIgnoreCase("saturday") || day.trim().equals("星期六"))
			this.schedule.sat.remove(index-1);
		if(day.trim().equalsIgnoreCase("sunday") || day.trim().equals("星期日"))
			this.schedule.sun.remove(index-1);
	}
	
	//将所有数据写入文件
	public boolean saveData()
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("././schedule.txt"));
			
			bw.write("[Monday]");
			bw.newLine();
			for(int i=0;i<this.schedule.mon.size();i++)
			{
				bw.write(this.schedule.mon.get(i));
				bw.newLine();
			}
			
			bw.write("[Tuesday]");
			bw.newLine();
			for(int i=0;i<this.schedule.tue.size();i++)
			{
				bw.write(this.schedule.tue.get(i));
				bw.newLine();
			}
			
			bw.write("[Wednesday]");
			bw.newLine();
			for(int i=0;i<this.schedule.wed.size();i++)
			{
				bw.write(this.schedule.wed.get(i));
				bw.newLine();
			}
			
			bw.write("[Thursday]");
			bw.newLine();
			for(int i=0;i<this.schedule.thu.size();i++)
			{
				bw.write(this.schedule.thu.get(i));
				bw.newLine();
			}
			
			bw.write("[Friday]");
			bw.newLine();
			for(int i=0;i<this.schedule.fri.size();i++)
			{
				bw.write(this.schedule.fri.get(i));
				bw.newLine();
			}
			
			bw.write("[Saturday]");
			bw.newLine();
			for(int i=0;i<this.schedule.sat.size();i++)
			{
				bw.write(this.schedule.sat.get(i));
				bw.newLine();
			}
			
			bw.write("[Sunday]");
			bw.newLine();
			for(int i=0;i<this.schedule.sun.size();i++)
			{
				bw.write(this.schedule.sun.get(i));
				bw.newLine();
			}
			
			bw.write("[End]");
			bw.newLine();
			bw.close();
		} 
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "文件操作出错，程序关闭！！"+"\n"+e.getMessage(),"程序错误",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		return true;
	}
	
	//日程数据暂存
	protected class Schedule
	{
		public Vector<String> mon;
		public Vector<String> tue;
		public Vector<String> wed;
		public Vector<String> thu;
		public Vector<String> fri;
		public Vector<String> sat;
		public Vector<String> sun;
		public Schedule()
		{
			mon = new Vector<String>();
			tue = new Vector<String>();
			wed = new Vector<String>();
			thu = new Vector<String>();
			fri = new Vector<String>();
			sat = new Vector<String>();
			sun = new Vector<String>();
		}
	}
	
}
