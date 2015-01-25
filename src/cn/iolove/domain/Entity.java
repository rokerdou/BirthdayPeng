package cn.iolove.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entity {
	private Entity(){};
	private static Entity st = new Entity(){};
	private String [] deptnos;
	private User myself;
	private String loginuserid="";
	private HashMap<String,ArrayList<User>> list = new HashMap<String,ArrayList<User>>(); 
	//好友列表+同人列表
	private int feizaonum=0;
	private int xiangjiaonum=0;
	private String lastlogintime="";

	private int regnum=0;
	private String lastLoginuserid;
	private String Version="1.0";
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public String getVersion() {
		return Version;
	}
	public int getFeizaonum() {
		return feizaonum;
	}
	public void setFeizaonum(int feizaonum) {
		this.feizaonum = feizaonum;
	}
	public int getXiangjiaonum() {
		return xiangjiaonum;
	}
	public void setXiangjiaonum(int xiangjiaonum) {
		this.xiangjiaonum = xiangjiaonum;
	}
	
	public void setRegnum(int regnum) {
		this.regnum = regnum;
	}
	public int getRegnum() {
		return regnum;
	}
	

	
	public void setList(HashMap<String, ArrayList<User>> list) {
		this.list = list;
	}
	public String getLastLoginuserid() {
		return lastLoginuserid;
	}
	public void setLastLoginuserid(String lastLoginuserid) {
		this.lastLoginuserid = lastLoginuserid;
	}
	
	

   public HashMap<String, ArrayList<User>> getList() {
	return list;
   }

	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}
	public String getLoginuserid() {
		return loginuserid;
	}
	
	public void setMyself(User myself) {
		this.myself = myself;
	}
	public User getMyself() {
		return myself;
	}

	public static Entity getInstance()
	{
		return st;
	}
	

	public void setDeptnos(String[] deptnos) {
		this.deptnos = deptnos;
	}
	public String[] getDeptnos() {
		return deptnos;
	}
	public List<String> getDeptnoslist()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<15;i++)
		{
		list.add(deptnos[i]);
		}
		
		return list;
		
	}
	
	
	
	

}
