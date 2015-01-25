package cn.iolove.birthdaybar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cn.iolove.Service.Task;
import cn.iolove.domain.Entity;
import cn.iolove.domain.LocalData;
import cn.iolove.domain.Message;
import cn.iolove.domain.MessageDate;
import cn.iolove.domain.Xiaoneimsg;
import cn.iolove.domain.packMessage;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MessageView extends Activity {
	private ListView lv_msg;
	private Button send;
	private Button title_btn_rigth;
	private Button title_btn_left;
	private LinearLayout ly;
	private Entity en = Entity.getInstance();
	private Handler hand = new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			if(msg.arg1==1)
			{
			String text= (String)msg.obj;
			Toast.makeText(MessageView.this, text, Toast.LENGTH_LONG).show();
			}
			else if(msg.arg1==MessageDate.SERVER_CONNECT_ERROR)
			{
				
				Toast.makeText(MessageView.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
				
			}
			else if (msg.what==MessageDate.GETMSG_SUCCESS)
			{
				if(msg.obj instanceof Message)
				{
					Message temp = (Message)msg.obj;
					ArrayList<Xiaoneimsg> msglist=temp.getXiaoneilist();
					if(msglist.isEmpty())
					{
						Toast.makeText(MessageView.this, "还没有人给发消息", Toast.LENGTH_LONG).show();
					}
					en.getList().put("msguser", temp.getList().get("msguserlist"));
					showlist(msglist);				
				}
			}
			
		};
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); //
		setContentView(cn.iolove.birthdaybar.R.layout.message);  
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, cn.iolove.birthdaybar.R.layout.titile);
		lv_msg =(ListView)findViewById(R.id.msg_lv_msg);
		ly=(LinearLayout)findViewById(R.id.msg_ly_listview);
		ly.getBackground().setAlpha(80);
		title_btn_left=(Button)findViewById(R.id.title_btn_left);
		title_btn_rigth=(Button)findViewById(R.id.title_option);
		title_btn_left.setVisibility(View.INVISIBLE);
		title_btn_rigth.setVisibility(View.INVISIBLE);
		
		new Task(packMessage.getMsg(),hand).start();
		
		
	}
	protected void showlist(ArrayList<Xiaoneimsg> msglist) {
		
		XiaoneiAdapter ad = new XiaoneiAdapter(MessageView.this,parseMsg(msglist));
		ad.setType(LocalData.msglist);
		lv_msg.setAdapter(ad);
		
	}
	private List parseMsg(ArrayList<Xiaoneimsg> xiaoneilist) 
	{
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		Iterator i = xiaoneilist.iterator();
		while(i.hasNext())
		{
			 Xiaoneimsg m = (Xiaoneimsg)i.next();
			 
			 HashMap<String,Object> mm = new HashMap<String,Object>();
			 mm.put("name", m.getNickname());
			 mm.put("msg", m.getMsg());
			 mm.put("time", m.getSendtime());
			 mm.put("userid",m.getUserid());
			 list.add(mm);
			 
			
			
		}
		
		
		
		return list;
	}
	

}
