package cn.iolove.domain;

import java.io.Serializable;
import java.util.Date;

public class Xiaoneimsg implements Serializable{
	
	private static final long serialVersionUID = 822L;
	private String userid;
	private String nickname;
	private String msg;
	private Date sendtime;
	public String getMsg() {
		return msg;
	}
	public String getNickname() {
		return nickname;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public String getUserid() {
		return userid;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj ==null) return false;
		if(obj ==this) return true;
		if(obj instanceof Xiaoneimsg)
		{
			return (((Xiaoneimsg)(obj)).msg==this.msg)&&(((Xiaoneimsg)(obj)).nickname==this.nickname);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return msg.hashCode()+sendtime.hashCode()+nickname.hashCode();
	}
	
	
	
}
