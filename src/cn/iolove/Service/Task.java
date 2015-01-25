package cn.iolove.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.UnknownHostException;


import android.app.ProgressDialog;
import android.os.Handler;


import cn.iolove.domain.Entity;
import cn.iolove.domain.Message;
import cn.iolove.domain.MessageDate;

public class Task extends Thread{
	private Socket ss;
	private Message msg;
	private Handler handler;



	public Task(Message ms,Handler hand ) {
		handler = hand;

		
		msg = ms;
		}
	@Override
	public void run() {
		TaskTwo tt=null;

			try {
				
				android.os.Message msgg = handler.obtainMessage();
				msgg.arg1=1;
				
				msgg.obj=toStringState(msg.getState());
				
				handler.sendMessage(msgg);
				tt=new TaskTwo(msg,handler);
				tt.start();
				Request("agoboy.vicp.net",null);


			}
			catch (Exception e) {
				
		
						if(tt.flag==1)
						{
						android.os.Message msg = handler.obtainMessage();
						msg.arg1=MessageDate.SERVER_CONNECT_ERROR;
					
						
						handler.sendMessage(msg);
						
						}
				
			}


			
	
			
			
		
	
	}
	public void Request(String domain,String ip) throws Exception
	{
		if(ip==null)
		{
			java.net.InetAddress address=java.net.InetAddress.getByName(domain);
			ss = new Socket(address,18520);		
			ss.setSoTimeout(5000);
			ObjectOutputStream oos = new ObjectOutputStream((ss.getOutputStream()));
			ObjectInputStream ois =new ObjectInputStream((ss.getInputStream()));
			
			oos.writeObject(msg);
			oos.flush();
			Message mm = (Message)ois.readObject();
			android.os.Message msg = handler.obtainMessage();
			msg.what=mm.getState();
			msg.obj=mm;
			handler.sendMessage(msg);
			
		}
		else
		{
			
			ss = new Socket("172.18.102.217",18520);		
			ss.setSoTimeout(5000);
			ObjectOutputStream oos = new ObjectOutputStream((ss.getOutputStream()));
			ObjectInputStream ois =new ObjectInputStream((ss.getInputStream()));
			
			oos.writeObject(msg);
			oos.flush();
			Message mm = (Message)ois.readObject();
			android.os.Message msg = handler.obtainMessage();
			msg.what=mm.getState();
			msg.obj=mm;
			handler.sendMessage(msg);
			
		}
		
	}
	private String toStringState(int state) {
		switch (state)
		{
			case MessageDate.REQUEST_LOGIN :
				return "�������������ĵȴ�.";
				
			case MessageDate.REQUEST_GETXIAONEI:
				return "���ڻ�ȡУ����";
			case MessageDate.REQUEST_POSTXIAONEI:
				return "�����ύУ����";
			case MessageDate.REQUEST_REGUSER:
				return "����ע��..";
			case MessageDate.REQUEST_SEARCHFRIEND:
				return "������������..";
			case MessageDate.REQUEST_UPDATEFREINDLIST:
				return "���ڸ��º����б�";
			case MessageDate.REQUEST_CHECKFRIENDLIST:
				return "���ں˶Ժ����б�";
			case MessageDate.REQUEST_GETMSG:
				return "����ˢ����Ϣ";
			case MessageDate.REQUEST_DELETEUSER:
				return "����ɾ�����Ժ�";
			case MessageDate.REQUEST_XIUGAIUSER:
				return "���Ժ����������ֻ���";
			
		}
		return null;
	}

}