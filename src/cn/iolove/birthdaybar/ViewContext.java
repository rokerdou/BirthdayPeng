package cn.iolove.birthdaybar;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.iolove.Service.LocalTask;
import cn.iolove.Service.Task;
import cn.iolove.domain.Entity;
import cn.iolove.domain.LocalData;
import cn.iolove.domain.Message;
import cn.iolove.domain.MessageDate;
import cn.iolove.domain.User;
import cn.iolove.domain.packMessage;
import cn.iolove.utils.MD5utils;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import android.widget.Toast;

public class ViewContext {
	private Handler RegHandler ;
	private Handler MainHanler;
	private Handler LoginHanler;
	private Handler friendHandler;
	
	private Entity entity = Entity.getInstance();

	public void setLoginHanler(Handler loginHanler) {
		LoginHanler = loginHanler;
	}
	public User getMyself()
	{
		return entity.getMyself();
	}
	public String getLastLoginUserid()
	{
		return entity.getLastLoginuserid();
	}
	public void setMainHanler(Handler mainHanler)
	{
		MainHanler = mainHanler;
	}
	public void getDetnos()
	{
		entity.getDeptnos();
		
	}



	
	public void setRegHandler(Handler regHandler) {
		RegHandler = regHandler;
	}
	public void setFriendHandler(Handler friendHandler) {
		this.friendHandler = friendHandler;
	}
	public Handler getFriendHandler() {
		return friendHandler;
	}


	private ViewContext() {
	}
	private static ViewContext vc = new ViewContext();
	public static ViewContext getInstance()
	{
		return vc;
	}
	public void UpdateFriendlist()
	{
		
		new Task(packMessage.UpdateFriendlist(),friendHandler).start();

		
	}

	public void login(String userid,String pwe,ProgressDialog pd)
	{
		new Task(packMessage.LoginMessage(userid, pwe),LoginHanler).start();

	}
	public void regUser(User u)
	{		
			new Task(packMessage.RegUserMessage(u),RegHandler).start();
	}
	public User getFriendfromfriendList(int index) {
		
		return entity.getList().get("total").get(index);
	}
	public User getSearchFriendList(int index) {	
		return entity.getList().get("searchfriend").get(index);
	}
	public User getUserinfoList(int index,int type)
	{
		switch(type) {
		case LocalData.friendlist:
			return getFriendfromfriendList(index);	
		case LocalData.searchlist:
			return getSearchFriendList(index);
		case LocalData.xiaoneilist:
			return getxiaoneiUserlist(index);
		case LocalData.msglist:
			return getmsgUserlist(index);
			
		}
		return null;
	}
	private User getmsgUserlist(int index) {
		// TODO Auto-generated method stub
		return entity.getList().get("msguser").get(index);
	}
	private User getxiaoneiUserlist(int index) {
		return entity.getList().get("xiaoneiuser").get(index);
	}
	public int getindexfromlist(String userid,int type)
	{
		switch(type)
		{
		case LocalData.xiaoneilist:
			return getindexxiaoneilist(userid);
		case LocalData.msglist:
			return getindemsguserlist(userid);
		}
		
		return 0;
		
	}
	private int getindexxiaoneilist(String userid) {
		Iterator<User> i =entity.getList().get("xiaoneiuser").iterator();
		int index=0;
		while(i.hasNext())
		{
			User t = (User)i.next();
			if(t.getUserid().equals(userid)) 
			{
				break;
			}
			index++;
		}
		return index;
	}
	private int getindemsguserlist(String userid) {
		Iterator<User> i =entity.getList().get("msguser").iterator();
		int index=0;
		while(i.hasNext())
		{
			User t = (User)i.next();
			if(t.getUserid().equals(userid)) 
			{
				break;
			}
			index++;
		}
		return index;
	}
		

		
	
	
	

}
