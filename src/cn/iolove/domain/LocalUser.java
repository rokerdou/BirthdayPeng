package cn.iolove.domain;

public class LocalUser  {
	private User temp;
	private boolean y;
	public LocalUser(User tt) {
		temp = tt;
		
	}
	
	public String getUserid() {
		if( (temp.getPermission()&16)!=0)
		return "学号:"+temp.getUserid();
		else return "学号:对方不给见";
	}
	public User getTemp() {
		return temp;
	}
	
	
	
	public String getPhone() {
		if( (temp.getPermission()&2)!=0)
		return "手机:"+temp.getPhone();
		else return "手机:诶可惜了对方不给看啊";
	}

	
	public String getName() {
		if( (temp.getPermission()&1)!=0)
			return "姓名:"+temp.getName();
			else return "姓名:对方保密了";
	} 
}
