package cn.iolove.birthdaybar;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import cn.iolove.domain.Entity;
import cn.iolove.domain.LocalData;
import cn.iolove.domain.Message;
import cn.iolove.domain.MessageDate;
import cn.iolove.domain.User;
import cn.iolove.utils.DialogFactory;
import cn.iolove.utils.LunarCalendar;
import cn.iolove.utils.MD5utils;
import cn.iolove.Service.LocalTask;
import cn.iolove.birthdaybar.R;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RegView extends Activity {
	private Button cancel_btn;
	private Button reg_ok;
	private EditText rbirthday;
	private EditText rname;
	private EditText ruserid;
	private Spinner rdeptno;
	private ArrayAdapter<String> adapter; 
	private Entity configs = Entity.getInstance();
	private User u = new User();
	private CheckBox rsunday;
	private TextView rtitle;
	private Dialog waiting;
	private CheckBox rpermission_name;


	private CheckBox rmoonday;
	private CheckBox rmale;
	private CheckBox rfemale;
	private EditText rconfirmpwd;
	private EditText rnickname;
	private EditText rpwd;
	private RelativeLayout ly;
	private CheckBox rpermission_userid;
	
	private ViewContext vc = ViewContext.getInstance();
	private  Handler hander = new Handler()
	{
		@Override
		public void handleMessage(android.os.Message msg) {
			// TODO Auto-generated method stub
			if(msg.arg1==1)
			{
			String text= (String)msg.obj;
			Toast.makeText(RegView.this, text, Toast.LENGTH_SHORT).show();
			}
			else if(msg.arg1==MessageDate.SERVER_CONNECT_ERROR)
			{
				waiting.dismiss();
				Toast.makeText(RegView.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
				
			}
			else
			{
				waiting.dismiss();
				String str = ((Message)(msg.obj)).getToast();
				Toast.makeText(RegView.this, str ,Toast.LENGTH_LONG).show();
				if(str.equals("注册成功"))
				{
					
					new LocalTask(RegView.this.getApplicationContext(), LocalData.writeregnum).start();

					Intent intend = new Intent();
					intend.setClass(RegView.this, LoginView.class);
					startActivity(intend);
					RegView.this.finish();
					
				}
				
			}
			

		}
		
	};

	

	

	private Calendar c = new GregorianCalendar();
	
	
	private void updateDate(){
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		u.setBirthday(c.getTime());
		LunarCalendar lc =new LunarCalendar(c);
		//lc.toString().substring(lc.toString().indexOf("年")+1)
		rbirthday .setText( df .format( c .getTime())+"此日阴历:"+lc.toString().substring(lc.toString().indexOf("年")+1));
		}
	 

	private DatePickerDialog.OnDateSetListener mDateSetListener =  
            new DatePickerDialog.OnDateSetListener() {  
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {  

                	c .set(Calendar. YEAR , year);
                	c .set(Calendar. MONTH , monthOfYear);
                	c .set(Calendar. DAY_OF_MONTH , dayOfMonth);
                	updateDate();
                }             
            };  



     
    protected Dialog onCreateDialog(int id) {  
    	Dialog t = new DatePickerDialog(this,  
                mDateSetListener,  
               	c .get(Calendar. YEAR ),
            	c .get(Calendar. MONTH ),
            	c .get(Calendar. DAY_OF_MONTH ));
    	
    	
 
 
            return t;

    }  
      
    // 隐藏手机键盘  
    private void hideIM(View edt){  
        try {  
             InputMethodManager im = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);  
             IBinder  windowToken = edt.getWindowToken();  
             if(windowToken != null) {  
                 im.hideSoftInputFromWindow(windowToken, 0);  
             }  
         } catch (Exception e) {  
             
         }  
     }  

	public RegView() {
		
	}
	@SuppressLint("ResourceAsColor")
	public void init()
	{
		
		rconfirmpwd = (EditText)findViewById(R.id.reg_edit_confirmpwd);
		rsunday = (CheckBox)findViewById(R.id.reg_checkbox_sunday);
		rmoonday = (CheckBox)findViewById(R.id.reg_checkbox_moonday);
		rdeptno = (Spinner)findViewById(R.id.reg_spinner_dep);
		cancel_btn = (Button)findViewById(R.id.reg_btn_cancel);
		reg_ok = (Button)findViewById(R.id.reg_btn_ok);
		rbirthday = (EditText)findViewById(R.id.reg_edit_birthday);
		ruserid = (EditText)findViewById(R.id.reg_edit_userid);
		rname = (EditText)findViewById(R.id.reg_edit_name);
		rnickname = (EditText)findViewById(R.id.reg_edit_nickname);
		rpwd  = (EditText)findViewById(R.id.reg_edit_pwd);
		rmale = (CheckBox)findViewById(R.id.reg_checkbox_Sexmale);
		rfemale= (CheckBox)findViewById(R.id.reg_checkbox_sexfemale);
		ly =(RelativeLayout)findViewById(R.id.reg_relayout);
		ly.getBackground().setAlpha(130);
		rpermission_name = (CheckBox)findViewById(R.id.reg_permission_name);
		rpermission_userid =(CheckBox)findViewById(R.id.reg_permission_userid);
		rpermission_name.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) u.setPermission(u.getPermission()+1);
				else u.setPermission(u.getPermission()-1);
				
			}
		});
		rpermission_userid.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) u.setPermission(u.getPermission()+16);
				else u.setPermission(u.getPermission()-16);
				
			}
		});
		



		vc.setRegHandler(hander);
		adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_spriner, configs.getDeptnoslist());
		adapter.setDropDownViewResource(R.layout.simple_spriner);
		
		rdeptno.setAdapter(adapter);
		rdeptno.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
			u.setDeptno(position+1);
			 TextView v1 = (TextView)view;
			 v1.setTextColor(Color.WHITE) ;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		
		cancel_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intend = new Intent();
				intend.setClass(RegView.this, LoginView.class);
				startActivity(intend);
				
				RegView.this.finish();
				
				
			}
		});
		reg_ok.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {	
			   
				if(configs.getRegnum()>=2)
				{
					Toast.makeText(getApplicationContext(),"很抱歉一个手机最多仅能注册两个账号", Toast.LENGTH_SHORT).show();
					return ;
				}
				else
				{
					if(ruserid.getText().toString().matches("^[2][0][1]\\d{7}$"))
					{	
						if(rpwd.getText().toString().length()>4)
						{
							checkreg();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "密码不得小于四位", Toast.LENGTH_SHORT).show();
							
							
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "学号输入有误", Toast.LENGTH_SHORT).show();
					}
					
				}
		
				
				
			}
			public void checkreg()
			{
				if(rsunday.isChecked()||rmoonday.isChecked())
				{
					if(rmale.isChecked()||rfemale.isChecked())
					{
						if(rconfirmpwd.getText().toString().equals(rpwd.getText().toString()))
						{
							if(!rnickname.getText().toString().isEmpty()&&!rname.getText().toString().isEmpty())
							{
								Builder builder = new AlertDialog.Builder(RegView.this);
								builder.setTitle("确定生日");
								String str;
								if(u.getYy())
								{
									SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );								
									 str = "你生日为阳历"+df.format(c.getTime());
								}
								else
								{
									LunarCalendar lc =new LunarCalendar(c);
									 str = "你生日为阴历"+lc.toString().substring(lc.toString().indexOf("年")+1);
								}
								builder.setMessage(str);
								builder.setPositiveButton("确认注册",new  DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										RegUser();
										dialog.dismiss();
										
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
						}
						else
						{
							Toast.makeText(RegView.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
						}
					}
					else{
						Toast.makeText(RegView.this, "你的性别貌似有问题诶", Toast.LENGTH_SHORT).show();
						
					}
				}
				else
				{	
					Toast.makeText(RegView.this, "阴历阳历生日二选一", Toast.LENGTH_SHORT).show();					
				  
				}
				
			}

			public void RegUser() {
				

					waiting  = DialogFactory.creatRequestDialog(RegView.this, "正在注册...");
					waiting.show();
					
	
					u.setUserid(ruserid.getText().toString());
					u.setName(rname.getText().toString());
					u.setPassword(MD5utils.md5(rpwd.getText().toString()));
					u.setNickname(rnickname.getText().toString());
		
						vc.regUser(u);
					
			}
			
		});
		rbirthday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  hideIM(v); 
				  showDialog(1);
				
			}
		});
		rbirthday.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
				  hideIM(v); 
				  showDialog(1);
				}
				
			}
		});

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		setContentView(R.layout.reg_view);  		
		init();
		 updateDate();
		 u.setPermission(113);
		 new LocalTask(getApplicationContext(), LocalData.loadconfig).start();
		rmale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
				{
					rfemale.setChecked(false);
					u.setSex("男");
				}
				
			}
		});
		rfemale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
				{
					rmale.setChecked(false);
					u.setSex("女");
				}
				
			}
		});
		rmoonday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
				{
					u.setYy(false);
					rsunday.setChecked(false);
				}
				
			}
		});
		rsunday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
				{
					u.setYy(true);
					rmoonday.setChecked(false);
				}
				
			}
		});
	}
	

}
