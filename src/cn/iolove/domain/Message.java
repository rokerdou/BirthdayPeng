package cn.iolove.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message  implements Serializable{
	
	private static final long serialVersionUID = 818L;	
	private int state = 0;
	private HashMap<String,ArrayList<User>> list = new HashMap<String,ArrayList<User>>();
	private HashMap<String,String> extra = new HashMap<String,String>();
	/**

	**/
	private String version;
	private String message;
	private String loginid;
	private User clientUser;
	private String Toast;
	public ArrayList<String> paihangXnamelist;
	public ArrayList<String> paihangXnumlist;
	public ArrayList<String> paihangFnamelist;
	public ArrayList<String> paihangFnumlist;
	
	private ArrayList<Xiaoneimsg> xiaoneilist = new ArrayList<Xiaoneimsg>();
	public ArrayList<Xiaoneimsg> getXiaoneilist() {
		return xiaoneilist;
	}
	public void setXiaoneilist(ArrayList<Xiaoneimsg> xiaoneilist) {
		this.xiaoneilist = xiaoneilist;
	}
	public void setList(HashMap<String, ArrayList<User>> list) {
		this.list = list;
	}
	public HashMap<String, ArrayList<User>> getList() {
		return list;
	}

	public HashMap<String, String> getExtra() {
		return extra;
	}
	public void setExtra(HashMap<String, String> extra) {
		this.extra = extra;
	}
	public void setToast(String toast) {
		Toast = toast;
	}
	public String getToast() {
		return Toast;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public void setClientUser(User clientUser) {
		this.clientUser = clientUser;
	}
	public User getClientUser() {
		return clientUser;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersion() {
		return version;
	}

	
	public Message() {
		
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return state;
	}

	

}
