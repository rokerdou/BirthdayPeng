package cn.iolove.birthdaybar;

import java.util.ArrayList;

import cn.iolove.Service.Task;
import cn.iolove.domain.Entity;
import cn.iolove.domain.MessageDate;
import cn.iolove.domain.User;
import cn.iolove.domain.Xiaoneimsg;
import cn.iolove.domain.packMessage;
import cn.iolove.utils.Tool;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Myinfo extends Activity {
	private RelativeLayout rnicknamely;
	private RelativeLayout rsexly;
	private RelativeLayout rl ;
	private RelativeLayout rdeptnoly;
	private RelativeLayout rbirthdayly;
	private RelativeLayout rphonely;
	private RelativeLayout ruseridly;
	private RelativeLayout rlly;
	private RelativeLayout rbirthly;
	private RelativeLayout rnamely;
	private Entity en = Entity.getInstance();
	private RelativeLayout rbirtht;
	private TextView tvname;
	private TextView tvnickname;
	private TextView tvphone;
	private TextView tvsex;
	private TextView tvdep;
	private TextView tvuserid;
	private TextView tvbirth;
	private Button  ok;
	private EditText phone;
	private User u;
	private Button delete;
	private Handler hand = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.arg1==1)
			{
			String text= (String)msg.obj;
			Toast.makeText(Myinfo.this, text, Toast.LENGTH_LONG).show();
			}
			else if(msg.arg1==MessageDate.SERVER_CONNECT_ERROR)
			{
				
				Toast.makeText(Myinfo.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
				
			}
			else if (msg.what==MessageDate.UPADATPHOEN_SUCCESS)
			{
				
				Toast.makeText(Myinfo.this, "手机号增加成功", Toast.LENGTH_SHORT).show();
		
				
		
			}
			else if(msg.what==MessageDate.DELETEUSER_SUCCESS)
			{
				Toast.makeText(Myinfo.this, "删除账户成功", Toast.LENGTH_SHORT).show();
				Intent intend = new Intent();
				intend.setClass(Myinfo.this, LoginView.class);
				startActivity(intend);
				
				Myinfo.this.finish();
				
			}
			
		};
			
		
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo);
		rlly =(RelativeLayout)findViewById(R.id.info_ly);
		rdeptnoly = (RelativeLayout)findViewById(R.id.info_deptnoly);
		rbirthdayly =(RelativeLayout)findViewById(R.id.info_birthdayly);
		rnicknamely =(RelativeLayout)findViewById(R.id.info_nicknamely);
		ruseridly=(RelativeLayout)findViewById(R.id.info_useridly);
		rphonely=(RelativeLayout)findViewById(R.id.info_phonely);
		rbirtht =(RelativeLayout)findViewById(R.id.info_birth);
		phone =(EditText)findViewById(R.id.info_et_phone);
		
		
		rbirthly =(RelativeLayout)findViewById(R.id.info_birthdayly);
		ok=(Button)findViewById(R.id.info_ok);
		rbirtht.getBackground().setAlpha(100);
		delete=(Button)findViewById(R.id.info_delete);
		rl=(RelativeLayout)findViewById(R.id.info_rl);
		rdeptnoly.getBackground().setAlpha(100);
		rbirthdayly.getBackground().setAlpha(100);
		rbirthly.getBackground().setAlpha(100);
		ruseridly.getBackground().setAlpha(100);
		rnamely =(RelativeLayout)findViewById(R.id.info_textly);
		
		rphonely.getBackground().setAlpha(100);
		rnicknamely.getBackground().setAlpha(100);
		rnamely.getBackground().setAlpha(100);
		//rl.getBackground().setAlpha(100);
		rlly.getBackground().setAlpha(100);
		
		tvbirth=(TextView)findViewById(R.id.info_tv_birth);
		tvdep=(TextView)findViewById(R.id.info_tv_deptno);
		tvname=(TextView)findViewById(R.id.info_tv_name);
		tvnickname=(TextView)findViewById(R.id.info_tv_nickname);
		tvphone=(TextView)findViewById(R.id.info_tv_phone);
	
		tvuserid=(TextView)findViewById(R.id.info_tv_userid);
		u=en.getMyself();
		tvbirth.setText(Tool.showBirth(u));
		tvdep.setText("学院:"+u.getDep());
		tvname.setText(u.getName());
		tvnickname.setText("昵称:"+u.getNickname());
		
	
		tvuserid.setText(u.getUserid());
		phone.setText(u.getPhone());
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phones = phone.getText().toString();
				if(checknum(phones))
				{
					new Task(packMessage.updataphone(u.getUserid(),phones),hand).start();
				}
				
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(Myinfo.this);
				builder.setTitle("确定删除账户");
				builder.setMessage("你确定从服务器中删除账户吗？删除账户后可重新注册噢，");
				builder.setPositiveButton("确定删除", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						new Task(packMessage.deleteUser(), hand).start();
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					}
				});
				builder.show();
				
			
			}
		});
		
	}
	public boolean checknum(String num)
	{
		String reg="[0-9]{11}";
		if(num.matches(reg))
		{
			return true;
			
		}else
		{
			Toast.makeText(getApplicationContext(), "手机号有误", Toast.LENGTH_SHORT).show();
			return false;
		}
		
	}

}
