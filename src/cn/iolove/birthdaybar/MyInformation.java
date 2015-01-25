package cn.iolove.birthdaybar;

import java.util.ArrayList;

import cn.iolove.Service.LocalTask;
import cn.iolove.Service.Task;
import cn.iolove.domain.Entity;
import cn.iolove.domain.LocalData;
import cn.iolove.domain.LocalUser;
import cn.iolove.domain.Message;
import cn.iolove.domain.MessageDate;
import cn.iolove.domain.User;
import cn.iolove.domain.packMessage;
import cn.iolove.utils.Tool;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MyInformation extends Activity  {
	private RelativeLayout rlayout;
	private RelativeLayout rbackly;
	private RelativeLayout rnicknamely;
	private RelativeLayout rsexly;
	private RelativeLayout rdeptnoly;
	private RelativeLayout rbirthdayly;
	private RelativeLayout rphonely;
	private RelativeLayout ruseridly;
	private TextView tvname;
	private TextView tvnickname;
	private TextView tvphone;
	private TextView tvsex;
	private TextView tvdep;
	private TextView tvuserid;
	private TextView tvbirth;
	private int index;
	private ViewContext vc = ViewContext.getInstance();
	private Entity entity = Entity.getInstance();
	private LocalUser userinfo;
	private Button addfriend;
	private Button btn_send;
	private Button title_left;
	private  Button title_right;
	private EditText et_msg;
	private Button sendxj;
	private Button sendfz;
	private String lastmsg="";
	private Handler hand = new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			if(msg.obj instanceof Message)
			{
				Message o = (Message)msg.obj;
				if(msg.what==MessageDate.POSTMSG_SUCCESS)
				{
					Toast.makeText(MyInformation.this, "发送成功", Toast.LENGTH_SHORT).show();
					et_msg.setText("");
				}
				if(msg.what==MessageDate.SENDXIANGJIAO_SUCCESS)
				{
				}
				if(msg.what==MessageDate.SENDFEIZAO_SUCCESS)
				{
				//	Toast.makeText(MyInformation.this, "成功的扔给他一个肥皂",Toast.LENGTH_LONG ).show();
				}
			}
			
		};
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); //
		setContentView(cn.iolove.birthdaybar.R.layout.myinformation);  
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, cn.iolove.birthdaybar.R.layout.titile);
		rlayout =(RelativeLayout)findViewById(R.id.myinformation_ly);
		rlayout.getBackground().setAlpha(100);
		rbackly = (RelativeLayout)findViewById(R.id.myinformation_textly);
		rbackly.getBackground().setAlpha(100);
		et_msg= (EditText)findViewById(R.id.myinfo_et_sendmessage);
		btn_send = (Button)findViewById(R.id.myinfo_send);
		sendxj =(Button)findViewById(R.id.myinfo_givexiangjiao);
		sendfz=(Button)findViewById(R.id.myinfo_givefeizao);
		title_left=(Button)findViewById(cn.iolove.birthdaybar.R.id.title_btn_left);
		title_left.setVisibility(View.INVISIBLE);
		title_right=(Button)findViewById(cn.iolove.birthdaybar.R.id.title_option);
		title_right.setVisibility(View.INVISIBLE);
		sendxj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(entity.getXiangjiaonum()==0)
				{
					Toast.makeText(MyInformation.this, "你没有香蕉了,一小时后会有",Toast.LENGTH_LONG ).show();
					return;
				}
				Toast.makeText(MyInformation.this, "送去了你还剩"+(entity.getXiangjiaonum()-1)+"个香蕉可送",Toast.LENGTH_LONG ).show();
				
				entity.setXiangjiaonum((entity.getXiangjiaonum()-1));
				new LocalTask(getApplicationContext(), LocalData.updataxiangjiaonum).start();
			
				new Task(packMessage.Sendxiangjiao(userinfo.getTemp().getUserid(),userinfo.getTemp().getName()),hand).start();
			}
		});
		sendfz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(entity.getFeizaonum()==0)
				{
					Toast.makeText(MyInformation.this, "你没有肥皂了,一小时后会有",Toast.LENGTH_LONG ).show();
				
					return;
				}
				Toast.makeText(MyInformation.this, "送去了你还剩"+(entity.getFeizaonum()-1)+"个肥皂可送",Toast.LENGTH_LONG ).show();
				
				entity.setFeizaonum((entity.getFeizaonum()-1));
				new Task(packMessage.Sendfeizao(userinfo.getTemp().getUserid(),userinfo.getTemp().getName()),hand).start();
				new LocalTask(getApplicationContext(),LocalData.updatafeizaonum).start();
			
			}
		});
		
		
		addfriend=(Button)findViewById(R.id.myinfo_btn_addfriend);
		btn_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendMsg();
				
			}
		});
		
		init();
		Intent i = getIntent();
		index = i.getIntExtra("index", 0);
		int type = i.getIntExtra("type", LocalData.friendlist);
		User t;
		if(type==LocalData.friendlist)
		{
	
		addfriend.setVisibility(View.INVISIBLE);
		
		}
		else
		{
			
			 addfriend.setVisibility(View.VISIBLE);
		}
		t=vc.getUserinfoList(index, type);
		addfriend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addUserHoneyfriend();
				
			}


		});
		
		userinfo = new LocalUser(t);
		show();
		
		
	}
	protected void sendMsg() {
		if(et_msg.getText().toString().isEmpty())
		{
			Toast.makeText(MyInformation.this,"消息不能为空",Toast.LENGTH_SHORT).show();
			return ;
		}
		if(!lastmsg.equals(et_msg.getText().toString()))
		{
			if(((vc.getMyself().getPermission())&64)!=0)
			{
			Message m =packMessage.postmsg(userinfo.getTemp().getUserid(), et_msg.getText().toString());
			Toast.makeText(MyInformation.this,"消息已投递",Toast.LENGTH_SHORT).show();
			
			new Task(m,hand).start();
			}
			else
			{
				Toast.makeText(MyInformation.this,"被系统禁言,请联系管理员",Toast.LENGTH_SHORT).show();
				
			}
		}
		else
		{
			Toast.makeText(MyInformation.this,"严禁刷屏",Toast.LENGTH_SHORT).show();
			return ;
			
		}
		lastmsg=et_msg.getText().toString();
		
		
	}
	protected void addUserHoneyfriend() {
		ArrayList<User> temp = new ArrayList<User>();
		temp.add(userinfo.getTemp());
		entity.getList().put("tempUser", temp);
		new LocalTask(this.getApplicationContext(), LocalData.addhonny, hand).start();
		
		Toast.makeText(MyInformation.this, "添加成功", Toast.LENGTH_SHORT).show();
	   
		
	}
	public void  show()
	{
		tvbirth.setText(Tool.showBirth(userinfo.getTemp()));
		tvdep.setText("学院:"+userinfo.getTemp().getDep());
		tvname.setText(userinfo.getName());
		tvnickname.setText("昵称:"+userinfo.getTemp().getNickname());
		tvphone.setText(userinfo.getPhone());
		tvsex.setText("性别:"+userinfo.getTemp().getSex());
		tvuserid.setText(userinfo.getUserid());
	}
	public void init()
	{
		rdeptnoly = (RelativeLayout)findViewById(R.id.myinfo_deptnoly);
		rbirthdayly =(RelativeLayout)findViewById(R.id.myinfo_birthdayly);
		rnicknamely =(RelativeLayout)findViewById(R.id.myinfo_nicknamely);
		ruseridly=(RelativeLayout)findViewById(R.id.myinformation_useridly);
		rphonely=(RelativeLayout)findViewById(R.id.myinfo_phonely);
		rsexly=(RelativeLayout)findViewById(R.id.myinfo_sexly);
		tvbirth=(TextView)findViewById(R.id.myinfo_birthday);
		tvdep=(TextView)findViewById(R.id.myinfo_deptno);
		tvname=(TextView)findViewById(R.id.myinfo_name);
		tvnickname=(TextView)findViewById(R.id.myinfo_nickname);
		tvphone=(TextView)findViewById(R.id.myinfo_phone);
		tvsex=(TextView)findViewById(R.id.myinfo_sex);
		tvuserid=(TextView)findViewById(R.id.myinfo_tv_userid);
		rdeptnoly.getBackground().setAlpha(100);
		rbirthdayly.getBackground().setAlpha(100);
		ruseridly.getBackground().setAlpha(100);
		rsexly.getBackground().setAlpha(100);
		rphonely.getBackground().setAlpha(100);
		rnicknamely.getBackground().setAlpha(100);
		
		
		
		
//		tvbirth.setAlpha(100);
//		tvdep.setAlpha(100);
//		tvname.setAlpha(100);
//		tvnickname.setAlpha(100);
//		tvphone.setAlpha(100);
//		tvsex.setAlpha(100);
//		tvuserid.setAlpha(100);
		

	}

	

}
