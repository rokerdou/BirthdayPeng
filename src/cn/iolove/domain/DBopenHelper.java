package cn.iolove.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.iolove.domain.LocalData;


public class DBopenHelper extends SQLiteOpenHelper {
	private String [] deptnos = {"ͨ������Ϣ����ѧԺ",
		"�Զ���ѧԺ","�������ѧ�뼼��ѧԺ","���ù���ѧԺ","�����ѧԺ","��ý����ѧԺ",
		"��繤��ѧԺ","������ϢѧԺ","���ʰ뵼��ѧԺ","����ѧԺ","����ѧԺ","����ѧԺ",
		"���ѧԺ","��ѧԺ","���˼���������ѧԺ"};

	public DBopenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	public DBopenHelper(Context context) {
		super(context,"iolove.db",null,1);
		
		
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "Create TABLE user "+
				"("+
				"userid varchar(20) not null,"+
				"name varchar(16) ,"+
				"password varchar(32),"+
				"nickName varchar(20) not null,"+
				"sex varchar(6),"+
				"deptno Integer not null,"+
				"loginnum Integer,"+
				"birthday date,"+
				"chinabirth varchar(30),"+
				"lastlogin datetime,"+
				"regtime datetime,"+
				"permission Integer not null,"+
				"phone varchar(24),"+
				"yy tinyint(1) not null,"+
				"qq varchar(24),"+
				"mail varchar(40),"+
				"type Integer )";
		String sql2 = "Create TABLE dep "+
				"("+
				"deptno varchar(20) not null,"+
				"id Integer primary key )";
		String sql3 = "Create TABLE config "+
				"(type Integer, " +
				"zhi varchar(30) )";
		
		String sql4 = "Create TABLE honey "+
				"(userid varchar(20), " +
				"fatheruserid varchar(20))";
				
		
		db.execSQL(sql);
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);
		String inser ="insert into dep(DEPTNO,id)  values (?,?) ";
		for(int i =1;i<=15;i++)
		{
			               // new Object[]{person.getName(),person.getPhone()}
			db.execSQL(inser, new Object[]{deptnos[i-1],new Integer(i)});
			
		}
		 String t ="insert into config(type,zhi)  values (?,?) ";
		 String reg = "insert into config(type,zhi) values(?,?)";
		 String updat = "insert into config(type,zhi) values(?,?)";
		 try
		 {
			 db.execSQL(t,new Object[]{LocalData.lastloginuserid,"13443"});
			 db.execSQL(reg,new Object[]{0,LocalData.regnum});
			 db.execSQL(updat,new Object[]{LocalData.lastlogintime,""});
			 db.execSQL(updat,new Object[]{0,"xiangjiaonum"});
			 db.execSQL(updat,new Object[]{0,"feizaonum"});
		 }catch (Exception e)
		 {
			 e.printStackTrace();
			 
		 }
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
