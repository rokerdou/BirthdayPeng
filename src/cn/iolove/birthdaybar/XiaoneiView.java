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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class XiaoneiView extends Activity {
	private ListView lv;
	private EditText msg;
	private Button title_left;
	private  Button title_right;
	private ViewContext  vc = ViewContext.getInstance();
	private Button send;
	private Entity en = Entity.getInstance();
	private String lastmsg = " ";
	private Handler hand = new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			if(msg.arg1==1)
			{
				String text= (String)msg.obj;
				Toast.makeText(XiaoneiView.this, text, Toast.LENGTH_LONG).show();
				
			}
			else if(msg.what==MessageDate.GETXIAONEI_SUCCESS)
			{
				if(msg.obj instanceof Message)
				{
					Message m = (Message)(msg.obj);
				
					en.getList().put("xiaoneiuser", m.getList().get("xiaoneiuser"));
					
					
					
					Toast.makeText(XiaoneiView.this,"加载完成" , Toast.LENGTH_SHORT).show();
					showlist(m.getXiaoneilist());
					
					m= null;
					
				}

			}
			else if(msg.what==MessageDate.POSTXIAONEI_SUCCESS)
			{
				Toast.makeText(XiaoneiView.this,"发布成功" , Toast.LENGTH_SHORT).show();
				XiaoneiView.this.msg.setText("");
				new Task(packMessage.getxiaoneimsg(),hand).start();
			}
			
		};
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); //
		setContentView(cn.iolove.birthdaybar.R.layout.xiaonei_view);  
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, cn.iolove.birthdaybar.R.layout.titile);
		msg =(EditText)findViewById(R.id.xiaonei_et_sendmessage);
		send=(Button)findViewById(R.id.xiaonei_btn_send);
		lv =(ListView)findViewById(R.id.xiaonei_lv_msg);
		new Task(packMessage.getxiaoneimsg(),hand).start();
		title_left=(Button)findViewById(R.id.title_btn_left);
		title_left.setVisibility(View.INVISIBLE);
		title_right=(Button)findViewById(R.id.title_option);
		title_right.setVisibility(View.INVISIBLE);

		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fabu();

			}


		});
	}
	protected void fabu() {
		if((vc.getMyself().getPermission()&32)!=0)
		{
			if(lastmsg.equals(msg.getText().toString()))
			{
			Toast.makeText(XiaoneiView.this, "严禁重复发布",Toast.LENGTH_LONG ).show();
			}
			else
			{	if(msg.getText().toString().isEmpty())
				{
				Toast.makeText(XiaoneiView.this, "说点什么吧",Toast.LENGTH_LONG ).show();
				return;
				}
				new Task(packMessage.xiaoneimsg((msg.getText().toString())),hand).start();
				lastmsg = msg.getText().toString();
			}
		}
		else
		{
			Toast.makeText(XiaoneiView.this, "因为某种原因今日被禁言 ,请联系管理员",Toast.LENGTH_LONG ).show();
		}
		
	}
	protected void showlist(ArrayList<Xiaoneimsg> xiaoneilist) {
		XiaoneiAdapter ad = new XiaoneiAdapter(XiaoneiView.this,parseMsg(xiaoneilist));
		ad.setType(LocalData.xiaoneilist);
		lv.setAdapter(ad);
		
		
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
