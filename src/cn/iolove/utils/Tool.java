package cn.iolove.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iolove.domain.User;

public class Tool {
	/**
	 * ��userת����sql���������
	 * @param user
	 * @return
	 */
	
	public static String intoSql(User user)
	{
		String sql = "insert into user "+
	"(userid,name,nickName,sex,deptno,"+
	"birthday,permission,phone,yy,mail,password) "+
	"values ("+
	user.getUserid()+","+user.getName()+","
	+user.getNickname()+","+user.getSex()+","
	+user.getDeptno()+","+dtoDate(user.getBirthday())+","
	+user.getPermission()+","+user.getPhone()+","
	+dbtobool(user.getYy())+","+user.getMail()+","
	+user.getPassword()+")";
		return sql;
	}
	public static int dbtobool(boolean b)
	{
		return b?1:0;
	}
	public static String toTable(User user)
	{
		SimpleDateFormat dates=new SimpleDateFormat("MM");
		return "month_"+dates.format(user.getBirthday());
		
	}
	public static String dtoDate(Date date)
	{
		SimpleDateFormat dates=new SimpleDateFormat("yyyy-MM-dd");
		return dates.format(date);
	}
	public static String showBirth(User u)
	{
		String t; 
		
		if(u.getYy())
		{
			SimpleDateFormat fmt=new SimpleDateFormat("MM-dd");
			t="����:"+fmt.format(u.getBirthday());
			return t;
			
			
		}
		else{
			LunarCalendar lc =new LunarCalendar(u.getBirthday());
			 t = "����:"+lc.toString().substring(lc.toString().indexOf("��")+1);

			return t;
		}

	}
	
	public static Object serial(byte[] buff) throws Exception
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(buff);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object o;
		try {
			o = ois.readObject();
		} 
		catch(IOException e)
		{
			throw new IOException("IO������");
		}
		catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("��û�з���");
		//	e.printStackTrace();
		}
		ois.close();
		return o;
		
		
		
	}


}
