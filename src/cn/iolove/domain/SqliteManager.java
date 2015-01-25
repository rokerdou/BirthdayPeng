package cn.iolove.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cn.iolove.utils.LunarCalendar;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;




public class SqliteManager implements DBmanager {
	private DBopenHelper dbOpenHelper;
	
	public SqliteManager(Context context) {
		dbOpenHelper = new DBopenHelper(context);
		
	}
	@Override
	public String getVersion() {
		return null;
	}
	public DBopenHelper getDbOpenHelper() {
		return dbOpenHelper;
	}
	public String[] getDeptnos()
	{
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		String sql = "select * from dep ";
		Cursor cu = db.rawQuery(sql,null);
		String [] dep = new String[15];
		int i=0;
		
		while(cu.moveToNext())
		{
			dep[i++]=cu.getString(cu.getColumnIndex("deptno"));
			
		}
		db.close();
	
		
		return dep;
	}
//	String sql4 = "Create TABLE honey "+
//			"(userid varchar(20), " +
//			"fatheruserid varchar(20)";
	
	public void addMapHoney(String localuser,String father)
	{
		SQLiteDatabase  db = dbOpenHelper.getWritableDatabase();
		String sqls ="insert into honey(userid,fatheruserid) values (?,?)";
		db.execSQL(sqls, new Object[]{localuser,father});
		db.close();	
	}
	public int getRegNum()
	{
		String sql ="select type from config where zhi =?";
		SQLiteDatabase  db = dbOpenHelper.getWritableDatabase();
		Cursor cu = db.rawQuery(sql, new String[]{"regnum"});
		int i =0;
		if(cu.moveToNext())
		{
			
	 	i= 	cu.getInt(cu.getColumnIndex("type"));
		}
		db.close();
		return i;
	
	}
	public void addRegnum(int old)
	{
		String sql = "Update  config set type =? where zhi= ?";
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(sql,new Object[]{(old+1),"regnum"});
		
		db.close();
		
	}
	public int getxiangjiaonum()
	{
		String sql="select type from config where zhi=?";
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cu=db.rawQuery(sql, new String[]{"xiangjiaonum"});
		int i =0;
		if(cu.moveToNext())
		{
			
	 	i= 	cu.getInt(cu.getColumnIndex("type"));
		}
		db.close();
		return i;
		
		
	}
	public int getfeizaonum()
	{
		String sql="select type from config where zhi=?";
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cu=db.rawQuery(sql, new String[]{"feizaonum"});
		int i =0;
		if(cu.moveToNext())
		{
			
	 	i= 	cu.getInt(cu.getColumnIndex("type"));
		}
		db.close();
		return i;
		
	}
	public ArrayList<User> readHoney(String myuserid)
	{
		String sql ="select * from user where userid in (select userid from honey where fatheruserid = ?) and type = ?";
		SQLiteDatabase  db = dbOpenHelper.getWritableDatabase();
		Cursor cu = db.rawQuery(sql, new String[]{myuserid,String.valueOf(LocalData.honny)});
		ArrayList<User> temp = new ArrayList<User>();
		while(cu.moveToNext())
		{
			User u = new User();
			u.setUserid(cu.getString(cu.getColumnIndex("userid")));
			String myDate =cu.getString(cu.getColumnIndex("birthday"));  
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			
			u.setDeptno(cu.getInt(cu.getColumnIndex("deptno")));
			u.setName(cu.getString(cu.getColumnIndex("name")));
			u.setNickname(cu.getString(cu.getColumnIndex("nickName")));
			u.setPermission(cu.getInt(cu.getColumnIndex("permission")));
			u.setSex(cu.getString(cu.getColumnIndex("sex")));
			u.setPhone(cu.getString(cu.getColumnIndex("phone")));
			u.setQQ(cu.getString(cu.getColumnIndex("qq")));
			u.setMail(cu.getString(cu.getColumnIndex("mail")));
			u.setYy(setYy(cu.getInt(cu.getColumnIndex("yy"))));
			try {
				Date date = format.parse(myDate);
				u.setBirthday(date);
		
			} catch (ParseException e) {
				System.out.println("时间解析失败");
				e.printStackTrace();
			}
			temp.add(u);

			
			
		}
		db.close();
		return temp;
	}

	@Override
	public boolean addUser(User u,int type) {
		SQLiteDatabase  db = dbOpenHelper.getWritableDatabase();
		 LunarCalendar lc = new LunarCalendar(u.getBirthday());
		 String sqls = "select * from user where userid =? and type =?";
		 Cursor cu = db.rawQuery(sqls, new String[]{u.getUserid(),String.valueOf(type)});
		 if(cu.moveToNext())
		 {
			 sqls ="update user set name = ?,nickName=?,sex=?,deptno=?,birthday=? "
					 +", permission=?,phone=?,yy=?,mail=?,regtime=?,chinabirth=? where userid =? and type =?";
			 db.execSQL(sqls,new Object[]{u.getName(),u.getNickname(),u.getSex(),u.getDeptno(),u.getBirthday(),u.getPermission(),u.getPhone()
					 ,u.getYy(),u.getMail(),u.getRegtime(),(lc.toString().substring(lc.toString().indexOf("年")+1)),u.getUserid(),type});
			
			 
			 
		 }
		 else
		 {
		String sql = "insert into user "+
					"(userid,name,nickName,sex,deptno,"+
					"birthday,permission,phone,yy,mail,password,regtime,chinabirth,type) "+
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		db.execSQL(sql,new Object[]{u.getUserid(),u.getName(),u.getNickname(),
				u.getSex(),u.getDeptno(),u.getBirthday(),u.getPermission(),u.getPhone()
				,u.getYy(),u.getMail(),u.getPassword(),u.getRegtime(),(lc.toString().substring(lc.toString().indexOf("年")+1))
				,type});
		 }
		db.close();
		

		return true;
	}

	@Override
	public String getUserid() {
		return null;
	}

	@Override
	public String getpwd() {
		return null;
	}

	@Override
	public boolean putSMSMaxnum(int num) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSMSMaxnum() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean addUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}
	public User getMyself(String userid)
	{
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		String sql = "Select * from user where userid = ?";
		Cursor cu = db.rawQuery(sql, new String[]{userid});
		User u = new User();
		while(cu.moveToNext())
		{
			
			u.setUserid(cu.getString(cu.getColumnIndex("userid")));
			String myDate =cu.getString(cu.getColumnIndex("birthday"));  
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			
			u.setDeptno(cu.getInt(cu.getColumnIndex("deptno")));
			u.setName(cu.getString(cu.getColumnIndex("name")));
			u.setNickname(cu.getString(cu.getColumnIndex("nickName")));
			u.setPermission(cu.getInt(cu.getColumnIndex("permission")));
			u.setSex(cu.getString(cu.getColumnIndex("sex")));
			u.setPhone(cu.getString(cu.getColumnIndex("phone")));
			u.setQQ(cu.getString(cu.getColumnIndex("qq")));
			u.setMail(cu.getString(cu.getColumnIndex("mail")));
			u.setYy(setYy(cu.getInt(cu.getColumnIndex("yy"))));
			
	
			try {
				Date date = format.parse(myDate);
				u.setBirthday(date);
		
			} catch (ParseException e) {
				System.out.println("时间解析失败");
				e.printStackTrace();
			}
			finally
			{
				db.close();
				
				
			}
			
			
			
		}
		return u;
	}
	public void removeUser(int type)
	{
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String sql = "delete from user where type = ?";
		db.execSQL(sql, new Object[]{type});
		db.close();
		
	}
	@Override
	public HashMap<Integer, ArrayList<User>> getUser(int type) 
	{
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		String sql = "Select * from user where type = ?";
		//db.execSQL(sql, new Object[]{type});
		Cursor cu = db.rawQuery(sql, new String[]{String.valueOf(type)});
		HashMap<Integer, ArrayList<User>> temp = new HashMap<Integer, ArrayList<User>>();
		ArrayList<User> lists = new ArrayList<User>();
		while(cu.moveToNext())
		{
			User u = new User();
			u.setUserid(cu.getString(cu.getColumnIndex("userid")));
			String myDate =cu.getString(cu.getColumnIndex("birthday"));  
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			
			u.setDeptno(cu.getInt(cu.getColumnIndex("deptno")));
			u.setName(cu.getString(cu.getColumnIndex("name")));
			u.setNickname(cu.getString(cu.getColumnIndex("nickName")));
			u.setPermission(cu.getInt(cu.getColumnIndex("permission")));
			u.setSex(cu.getString(cu.getColumnIndex("sex")));
			u.setPhone(cu.getString(cu.getColumnIndex("phone")));
			u.setQQ(cu.getString(cu.getColumnIndex("qq")));
			u.setMail(cu.getString(cu.getColumnIndex("mail")));
			u.setYy(setYy(cu.getInt(cu.getColumnIndex("yy"))));
			
	
			try {
				Date date = format.parse(myDate);
				u.setBirthday(date);
		
			} catch (ParseException e) {
				System.out.println("时间解析失败");
				e.printStackTrace();
			}
			finally
			{
				lists.add(u);
				
			}
			
			
			
		}
		temp.put(type, lists);
		db.close();
		
		
		return temp;
	}
	private boolean setYy(int columnIndex) {
		if(columnIndex==0)
			return false;
		else return true;			
	}
	@Override
	public void writeLastLoginUserid(String userid) {
	  SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
	  String sql = "Update config set zhi = ? where type = ?" ;
	  try{
		  db.execSQL(sql, new Object[]{userid,LocalData.lastloginuserid});
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  db.close();
	}
	@Override
	public String getLastLoginUserid() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		String sql = "select zhi from config where type = ?";
		String re = "";
		Cursor cu = db.rawQuery(sql, new String[]{String.valueOf(LocalData.lastloginuserid)});
		while(cu.moveToNext())
		{
			re= cu.getString(cu.getColumnIndex("zhi"));
		}
		db.close();
		
		return re;
	}
	public String getLastLogintime() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		String sql = "select zhi from config where type = ?";
		String re = "";
		Cursor cu = db.rawQuery(sql, new String[]{String.valueOf(LocalData.lastlogintime)});
		while(cu.moveToNext())
		{
			re= cu.getString(cu.getColumnIndex("zhi"));
		}
		db.close();
		
		return re;
	}
	public void updatelastlogintime() {
		 Time time = new Time("GMT+8");    
	        time.setToNow();   
	        int year = time.year;   
	        int month = time.month;   
	        int day = time.monthDay; 
	        int hour = time.hour;  
	        String times = year+"-"+month+"-"+day+"-"+hour;
	  	  SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		  String sql = "Update config set zhi = ? where type = ?" ;
		  try{
			  db.execSQL(sql, new Object[]{times,LocalData.lastlogintime});
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  db.close();
		
		
	}
	

	public void updatexiangjiaonum(int num) {
        
  	  SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
	  String sql = "Update config set type = ? where zhi = ?" ;
	  try{
		  db.execSQL(sql, new Object[]{num,"xiangjiaonum"});
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  db.close();
		
		
	}
	public void updatefeizaonum(int num) {
        
  	  SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
	  String sql = "Update config set type = ? where zhi = ?" ;
	  try{
		  db.execSQL(sql, new Object[]{num,"feizaonum"});
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  db.close();
		
	}


}
