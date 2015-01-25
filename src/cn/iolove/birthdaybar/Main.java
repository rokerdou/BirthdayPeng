package cn.iolove.birthdaybar;


import cn.iolove.Service.LocalTask;
import cn.iolove.birthdaybar.R;
import cn.iolove.domain.LocalData;
import android.app.Activity;
import android.app.Fragment;
import android.app.TabActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class Main extends Activity {

	private TextView title;
	private RelativeLayout ly;

	private Button title_btn_option;

	private LinearLayout titlely;
	private Button title_btn_sendxiaonei;

	private RelativeLayout rlpeng;
	private RelativeLayout rlxiaonei;
	private RelativeLayout rlgetmsg;
	private TextView tv_gonggao;
	private TextView tv_usergonggao;
	private RelativeLayout rlpaihang;
	private RelativeLayout rlmyinfo;

	private ViewContext context = ViewContext.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); //
		setContentView(R.layout.main);  
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titile);//
		title = (TextView)findViewById(R.id.titiletext);
		title.setText("生日对对碰");
		ly = (RelativeLayout)findViewById(R.id.main_ly);
		ly.getBackground().setAlpha(120);

		title_btn_option=(Button)findViewById(R.id.title_option);
		titlely =(LinearLayout)findViewById(R.id.main_ggly);
		titlely.getBackground().setAlpha(100);
		title_btn_option.setText("关于");
		rlpeng =(RelativeLayout)findViewById(R.id.main_rl_peng);
		rlpeng.getBackground().setAlpha(100);
		title_btn_option.getBackground().setAlpha(100);
		title_btn_sendxiaonei = (Button)findViewById(R.id.title_btn_left);
		title_btn_sendxiaonei.getBackground().setAlpha(100);
		title_btn_sendxiaonei.setText("校内事");
		rlmyinfo=(RelativeLayout)findViewById(R.id.main_rl_info);
		rlpaihang=(RelativeLayout)findViewById(R.id.main_rl_paihang);
		rlpaihang.getBackground().setAlpha(100);
		rlpaihang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent();
				 i.setClass(Main.this, paihangView.class);
				 startActivity(i);
				
			}
		});
		
		rlmyinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent();
				 i.setClass(Main.this, Myinfo.class);
				 startActivity(i);
				
				
			}
		});
		rlmyinfo.getBackground().setAlpha(100);
		tv_gonggao=(TextView)findViewById(R.id.main_title_gonggao);
		tv_usergonggao=(TextView)findViewById(R.id.main_title_usergonggao);
		String gonggao = getIntent().getExtras().getString("gonggao");
		String usergonggao=getIntent().getExtras().getString("usergonggao");
		tv_gonggao.setText(gonggao);
		tv_usergonggao.setText(usergonggao);

		title_btn_sendxiaonei.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent();
				 i.setClass(Main.this, XiaoneiView.class);
				 startActivity(i);
			
				
			}
		});
		title_btn_option.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent();
				 i.setClass(Main.this, Aboutus.class);
				 startActivity(i);
				
				
			}
		});
		rlxiaonei =(RelativeLayout)findViewById(R.id.main_rl_xiaonei);
		rlgetmsg= (RelativeLayout)findViewById(R.id.main_rl_getmsg);
		rlxiaonei.getBackground().setAlpha(100);
		rlgetmsg.getBackground().setAlpha(100);
		rlxiaonei.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent();
				 i.setClass(Main.this, XiaoneiView.class);
				 startActivity(i);
				
			}
		});

		
		rlpeng.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(Main.this,FriendView.class);
				startActivity(intent);
			}
		});
		rlgetmsg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(Main.this,MessageView.class);
				startActivity(intent);
			}
			
			
			
		});
	
		
	
		
		new LocalTask(Main.this,LocalData.writemyself).start();

	}

	
}

      
   


