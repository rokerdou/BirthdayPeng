package cn.iolove.domain;

public class LocalData {
	/**
	 * 动作
	 * 字段
	 * 数据库
	 * APP
	 */

	public  static final int   myself = 0x0013;
	public  static final int  friend = 0x0011;//同人
	public  static final int  birth = 0x0012;
	
	public  static final int  today = 0x0010;
	public static final int honny=0x0014; //好友
	public static final int lastloginuserid = 0x0015;//上次登录的账号
	public static final int removeallfriend =0x0016;
	public static final int friendlist =0x0017;
	public static final int searchlist = 0x0018;
	public static final int xiaoneilist = 0x0019;
	public static final int msglist = 0x001a;
	public static final int xiangjiao = 0x001b;
	public static final int feizao=0x001c;
	public static final int lastlogintime=0x001d;
	public  static final String  regnum = "regnum";//今天注册了多少次
	public  static final int  readmyself = 0x0003;
	
	
	public  static final int  writetoday = 0x0004;
	
	public static final int  loadconfig= 0x0005;
	public  static final int  writemyself = 0x0006;
	public  static final int  readtoday = 0x0007;
	public  static final int  readfriend = 0x0008;
	
	public  static final int  writefriend = 0x0009;
	public static final int addhonny = 0x000A;
	public static final int readhony = 0x000B;
	public static final int writelastloginuserid = 0x000C;
	public static final int readregnum =0x000d;
	public static final int writeregnum=0x000e;
	public static final int updataxiangjiaonum=0x000f;
	public static final int updatafeizaonum=0x0010;
	//flag标志位
	public static final int  FinishReadlistformDB=0x1000;
	
	

}
