package cn.iolove.domain;

import java.util.ArrayList;

import cn.iolove.birthdaybar.ViewContext;
import cn.iolove.utils.MD5utils;

public class packMessage {
	private static  Entity config = Entity.getInstance();

	public static Message RegUserMessage(User u){
		Message m = new Message();
		m.setClientUser(config.getMyself());
		ArrayList<User> al = new ArrayList<User>();
		al.add(u);
		m.getList().put("reguser", al);
		m.setState(MessageDate.REQUEST_REGUSER);
		return m;
		
	}
	public static Message Sendxiangjiao(String dstUserid,String name)
	{
		Message m= new Message();
		m.setClientUser(config.getMyself());
		m.setState(MessageDate.REQUEST_SENDXIANGJIAO);
		m.getExtra().put("xiangjiao", dstUserid);
		m.getExtra().put("name", name);
		return m;
	}
	public static Message getXiangjiaoList()
	{
		Message m= new Message();
		m.setState(MessageDate.REQUEST_GETXIANGJIAOLIST);
		return m ;
	}
	public static Message LoginMessage(String userid,String pwe){
		Message m = new Message();
		m.setLoginid(userid);
	
		User u = new User();
		u.setUserid(userid);
		u.setPassword(MD5utils.md5(pwe));
		ArrayList<User> al = new ArrayList<User>();
		al.add(u);
		m.getList().put("loginuser", al);
		m.setState(MessageDate.REQUEST_LOGIN);
		return m;
		
	}
	public static Message updataphone(String userid,String phone)
	{
		Message m= new Message();
		m.setState(MessageDate.REQUEST_XIUGAIUSER);
		m.getExtra().put("userid", userid);
		m.getExtra().put("phone", phone);
		m.getExtra().put("permission", String.valueOf(115));
		return m;
	}
	public static Message getMsg()
	{
		Message m= new Message();
		m.setState(MessageDate.REQUEST_GETMSG);
		m.setClientUser(config.getMyself());
		return m;
	}
	public static Message ChecklistNum()
	{
		Message m = new Message();
		m.setClientUser(config.getMyself());
		m.setState(MessageDate.REQUEST_CHECKFRIENDLIST);
		return m;
	}
	public static Message xiaoneimsg(String msg)
	{
		Message m = new Message();
		m.setClientUser(config.getMyself());
		m.setState(MessageDate.REQUEST_POSTXIAONEI);
		m.getExtra().put("postxiaoneimsg", msg);
	
		
		return m;
	}
	public static Message deleteUser( )
	{
		Message m = new Message();
		m.setClientUser(config.getMyself());
		return m;
	}
	public static Message UpdateFriendlist()
	{
		Message m = new Message();
		m.setClientUser(config.getMyself());
		m.setState(MessageDate.REQUEST_UPDATEFREINDLIST);
		m.setClientUser(config.getMyself());
		
		return m;
	}
	public static Message getxiaoneimsg() {
		Message m = new Message();
		m.setClientUser(config.getMyself());
		m.setState(MessageDate.REQUEST_GETXIAONEI);
		
		return m;
	}
	public static Message postmsg(String dstuserid,String msg)
	{
		Message m = new Message();
		m.setState(MessageDate.REQUEST_POSTMSG);
		m.setClientUser(config.getMyself());
		m.getExtra().put("srcuserid", config.getMyself().getUserid());
		m.getExtra().put("dstuserid", dstuserid);
		m.getExtra().put("msg", msg);
		return m;
		
	}
	public static cn.iolove.domain.Message searchFriend(String name) {
		Message m = new Message();
		m.setClientUser(config.getMyself());
		m.getExtra().put("searchfriend", name);
		m.setState(MessageDate.REQUEST_SEARCHFRIEND);
		
		return m;
	}
	public static Message Sendfeizao(String userid, String name) {
		Message m= new Message();
		m.setClientUser(config.getMyself());
		m.setState(MessageDate.REQUEST_SENDFEIZAO);
		m.getExtra().put("feizao", userid);
		m.getExtra().put("name", name);
		return m;
	}


}
