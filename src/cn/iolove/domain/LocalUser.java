package cn.iolove.domain;

public class LocalUser  {
	private User temp;
	private boolean y;
	public LocalUser(User tt) {
		temp = tt;
		
	}
	
	public String getUserid() {
		if( (temp.getPermission()&16)!=0)
		return "ѧ��:"+temp.getUserid();
		else return "ѧ��:�Է�������";
	}
	public User getTemp() {
		return temp;
	}
	
	
	
	public String getPhone() {
		if( (temp.getPermission()&2)!=0)
		return "�ֻ�:"+temp.getPhone();
		else return "�ֻ�:����ϧ�˶Է���������";
	}

	
	public String getName() {
		if( (temp.getPermission()&1)!=0)
			return "����:"+temp.getName();
			else return "����:�Է�������";
	} 
}
