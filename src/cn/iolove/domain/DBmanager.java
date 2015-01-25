package cn.iolove.domain;

import java.util.ArrayList;
import java.util.HashMap;

public interface DBmanager {
	public boolean addUser(User u );
	public String getUserid();
	public String getpwd();
	public boolean putSMSMaxnum(int num);
	public int getSMSMaxnum();
	public String getVersion();
	boolean addUser(User u, int type);
	public HashMap<Integer, ArrayList<User>> getUser(int type);
	public void removeUser(int type);
	public void writeLastLoginUserid(String userid);
	public String getLastLoginUserid();
	
	
	

}
