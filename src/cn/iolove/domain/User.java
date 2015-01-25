package cn.iolove.domain;

import java.io.Serializable;

import java.util.Date;

import cn.iolove.utils.Tool;

public class User  implements Serializable{
	private static final long serialVersionUID = 1029L;	
	private int id;  //不是学号 //数据库主键
	private String userid="";//学号
	private String nickname="";//昵称
	private String name="";//姓名
	private String password="";
	private Date birthday;
	private Date lastlogin;
	private Date regtime;
	private  String qq="";
	private String phone="";
	private String mail="";
	private String sex="";//性别 
	private int deptno;//学院
	private int permission=0;//权限
	private int sessionid;//会话编号
	private int xnum=0;
	private int fnum=0;
	public int getXnum() {
		return xnum;
	}
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public void setXnum(int bnum) {
		this.xnum = bnum;
	}
	
	private boolean yy= true;
	String[] deptnos="通信与信息工程学院,自动化学院,计算机科学与技术学院,经济管理学院,外国语学院,传媒艺术学院,光电工程学院,生物信息学院,国际半导体学院,国际学院,数理学院,体育学院,软件学院,法学院,成人及继续教育学院".split(",");
	
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getDep()
	{
		return deptnos[deptno-1];
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public Date getRegtime() {
		return regtime;
	}
	public String getUserid() {
	
		return userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setYy(boolean yy) {
		this.yy = yy;
	}
	public boolean getYy()
	{
		return yy;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public User() {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Tool.showBirth(this)+" "+userid.substring(2, 4)+"级"+deptnos[deptno-1];
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getQQ() {
		return qq;
	}

	public void setQQ(String qQ) {
		qq = qQ;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getSessionid() {
		return sessionid;
	}

	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj==this) return true;
		if(obj instanceof User)
		{
			User temp = (User)obj;
			return temp.id==this.id;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return id;
	}

}
