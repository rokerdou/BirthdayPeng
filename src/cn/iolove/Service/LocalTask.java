package cn.iolove.Service;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.iolove.domain.Entity;
import cn.iolove.domain.LocalData;
import cn.iolove.domain.SqliteManager;
import cn.iolove.domain.User;

public class LocalTask extends Thread {
	private  SqliteManager sm ;
	
	private Context con;
	private Entity config = Entity.getInstance();
	private int state = 0;
	private Handler hand;
	
	public LocalTask(Context c,int stat) {
		con=c;
		
		state=stat;	

	}
	public LocalTask(Context c,int stat,Handler h) {
		con=c;
		hand =h;
		
		state=stat;	

	}
	public LocalTask(int stat,Handler hh) {
		hand = hh;
		state=stat;	

	}
	public LocalTask(int stat) {
		state=stat;	

	}

	@Override
	public void run() {
		sm =new SqliteManager(con);
		 dispatchTask(state);
		


	}
	private Object dispatchTask(int state) {
		switch(state)
		{
		case LocalData.loadconfig:
			loadconfig();
			break;
		case LocalData.writemyself:
			writemyself();
			break;	
		case LocalData.readmyself:
			readmyself();
			break;
		case LocalData.readfriend:
			readfriend();
			break;
		case LocalData.writefriend:
			writefriend();
			break;
		case LocalData.writelastloginuserid:
			wrietLastLoginUserid();
			break;
		case LocalData.removeallfriend:
			removeallfriend();
			break;
		case LocalData.addhonny:
			addhonny();
			break;
		case LocalData.readhony:
			readhoney();
			break;
		case LocalData.writeregnum:
			sm.addRegnum(config.getRegnum());
			break;
		case LocalData.updatafeizaonum:
			sm.updatefeizaonum(config.getFeizaonum());
			break;
		case LocalData.updataxiangjiaonum:
			sm.updatexiangjiaonum(config.getXiangjiaonum());
			break;
		
		}
		
		return null;
	}
	private void readhoney() {
		ArrayList<User> honeylist= sm.readHoney(config.getMyself().getUserid());
		 config.getList().put("honey",honeylist );
			
	    

		
	}
	private void addhonny() {
		sm.addUser(config.getList().get("tempUser").get(0),LocalData.honny);
		sm.addMapHoney(config.getList().get("tempUser").get(0).getUserid(), config.getMyself().getUserid());
		
		
	}
	private void removeallfriend() 
	{
		sm.removeUser(LocalData.friend);
	
		
	}
	private void wrietLastLoginUserid() {
		sm.writeLastLoginUserid(config.getMyself().getUserid());
		config.setLastlogintime(sm.getLastLogintime());
		sm.updatelastlogintime();
		config.setFeizaonum(sm.getfeizaonum());
		config.setXiangjiaonum(sm.getxiangjiaonum());
		
		if(config.getLastlogintime().equals(sm.getLastLogintime()))
		{
			return;
		}
		else
		{
			sm.updatexiangjiaonum(10);
			sm.updatefeizaonum(10);
			sm.updatelastlogintime();
		}

		
	}
	private void writefriend() {
		ArrayList<User> list = config.getList().get("friend");
		Iterator l = list.iterator();
		sm.removeUser(LocalData.friend);
		while(l.hasNext())
		{
		
		sm.addUser((User)(l.next()), LocalData.friend);
		}
		
	}
	private void readfriend() {
		
	ArrayList<User> friendlist= sm.getUser(LocalData.friend).get(LocalData.friend);

	 config.getList().put("friend",friendlist );
	 readhoney();
		
    
     Message tt= new Message();
     tt.arg1=LocalData.FinishReadlistformDB;
     hand.sendMessage(tt);
     
	}
	private void readmyself() {

	}
	private void writemyself() {
		sm.addUser(config.getMyself(), LocalData.myself);
		wrietLastLoginUserid();
		
	}
	
	private void loadconfig() {
		config.setDeptnos(sm.getDeptnos());
		config.setLastLoginuserid(sm.getLastLoginUserid());
		config.setRegnum(sm.getRegNum());
		
		
		
	}
	

}
