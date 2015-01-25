package cn.iolove.birthdaybar;



import cn.iolove.Service.LocalTask;
import cn.iolove.Service.Service;
import cn.iolove.birthdaybar.R;
import cn.iolove.domain.Entity;
import cn.iolove.domain.LocalData;
import cn.iolove.domain.MessageDate;
import cn.iolove.utils.DialogFactory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginView extends Activity {
	private Button loginbt;
	private Button regbt;
	private EditText userid;
	private EditText pass;
	private TextView title;
	private Dialog mDialog = null;
	private Button option;
	private CheckBox cb_save;
	private RelativeLayout layout;
	private Service ser ;

	private ProgressDialog pd ;
	private Entity config = Entity.getInstance();
	
	private ViewContext context = ViewContext.getInstance();
	private Handler hander = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.arg1==1)
			{
			String text= (String)msg.obj;
			Toast.makeText(LoginView.this, text, Toast.LENGTH_SHORT).show();
			}
			else if(msg.arg1==MessageDate.SERVER_CONNECT_ERROR)
			{
				mDialog.dismiss();
				Toast.makeText(LoginView.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
				
			}
			else
			{
				cn.iolove.domain.Message ms = (cn.iolove.domain.Message)(msg.obj);
				
				
				String flag = ms.getToast();
				Toast.makeText(LoginView.this, flag ,Toast.LENGTH_LONG).show();
				mDialog.dismiss();
				if(flag.equals("登陆成功"))
				{
					
					
					config.setMyself(ms.getClientUser());
					new LocalTask(LoginView.this.getApplicationContext(), LocalData.writelastloginuserid).start();
					//将这次登录用户写入数据库
					if(!config.getLastLoginuserid().equals(config.getMyself().getUserid()))
					{
						new LocalTask(LoginView.this.getApplicationContext(), LocalData.removeallfriend).start();
						
						
					}
					String gonggao= ms.getExtra().get("gonggao");
					String usergonggao=ms.getExtra().get("usergonggao");
					
					
					Intent intent = new Intent();
					intent.putExtra("gonggao", gonggao);
					intent.putExtra("usergonggao", usergonggao);
					intent.setClass(LoginView.this,Main.class);
					startActivity(intent);
					
				}
				
			}
			

			 


		}
	};
	
	private void init()
	{
		new LocalTask(LoginView.this.getApplicationContext(),LocalData.loadconfig).start();
		
		userid = (EditText)findViewById(R.id.login_edit_account);
		pass = (EditText)findViewById(R.id.login_edit_pwd);
		regbt = (Button)findViewById(R.id.login_btn_reg);
		loginbt = (Button)findViewById(R.id.login_btn_login);
		layout =(RelativeLayout)findViewById(R.id.loginView);
		//layout.getBackground().setAlpha(100);
		ser= new Service(getApplicationContext());

		
	
		
		context.setLoginHanler(hander);
	}

	/**
	 * @author窦佳
	 * @param userid
	 * @param pwd
	 * @return
	 */
	public boolean check(String userid,String pwd)
	{
		if(userid.isEmpty())
		{
			Toast.makeText(LoginView.this, "学号不能为空", Toast.LENGTH_SHORT).show();
			return false;
		} else if(pwd.isEmpty())
		{
			Toast.makeText(LoginView.this, "密码不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		

		setContentView(R.layout.activity_main);  

		
		
		init();
		userid.setText(ser.getValue().get("userid"));
		pass.setText(ser.getValue().get("password"));
		cb_save=(CheckBox)findViewById(R.id.login_cb_savepwd);

		
		loginbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(check(userid.getText().toString(),pass.getText().toString()))
				{
					if(cb_save.isChecked())
					{
						ser.save(userid.getText().toString(), pass.getText().toString());
					}
					else
					{
						ser.save("", "");
					}
					mDialog = DialogFactory.creatRequestDialog(LoginView.this, "正在验证账号...");
	
					context.login(userid.getText().toString(), pass.getText().toString(),pd);
					mDialog.show();
				}
			
				
			}
		});
		
		regbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoginView.this,RegView.class);
				startActivity(intent);
				
			}
		});
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		//Toast.makeText(this.getApplicationContext(), "你好", 2000).show();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
