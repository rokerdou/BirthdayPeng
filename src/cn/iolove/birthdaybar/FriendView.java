package cn.iolove.birthdaybar;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cn.iolove.Service.LocalTask;
import cn.iolove.Service.Task;
import cn.iolove.domain.Entity;
import cn.iolove.domain.LocalData;
import cn.iolove.domain.MessageDate;
import cn.iolove.domain.User;
import cn.iolove.domain.packMessage;
import cn.iolove.utils.DialogFactory;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class FriendView extends Activity {
	private Entity config = Entity.getInstance();
	private ViewContext vc = ViewContext.getInstance();
	private ListView lv;
	private Dialog progress;
	private TextView title ;
	private Button search;
	private EditText ed;
	private Dialog dialog;
	private Button title_right;
	private Button title_left;
	private LinearLayout searchbar;
	
	private LinearLayout ly;

	
	private Handler hand = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.arg1==LocalData.FinishReadlistformDB)
			{

					List userlist = (ArrayList<User>)(config.getList().get("friend").clone());
					List honeyList = config.getList().get("honey");
					new Task(packMessage.ChecklistNum(),hand).start();
					if(!userlist.isEmpty())
					{
						if(honeyList.isEmpty())
						{
							
						config.getList().put("total", (ArrayList<User>)(config.getList().get("friend").clone()));
						showList(config.getList().get("total"),LocalData.friendlist);
						}
						else
						{
							ArrayList<User> total = (ArrayList<User>)(config.getList().get("friend").clone());
							total.addAll(honeyList);
							config.getList().put("total", total);
							showList(config.getList().get("total"),LocalData.friendlist);
							
						}
						
						
				
					
					}
					else
					{
						UpdateFriendList();
					}
				
			}
			if(msg.arg1==1)
			{
			String text= (String)msg.obj;
			if(text.equals("正在核对好友列表")) return;				
				Toast.makeText(FriendView.this, text, Toast.LENGTH_LONG).show();
			}
			else if(msg.arg1==MessageDate.SERVER_CONNECT_ERROR)
			{
				
				Toast.makeText(FriendView.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
				
			}
			else 
			{
				if(msg.what==MessageDate.SEECK_FRIEND_SUCCESS)
				{
				cn.iolove.domain.Message ms = (cn.iolove.domain.Message)(msg.obj);
				ArrayList<User> honeyList = config.getList().get("honey");
				ArrayList<User> total = ms.getList().get("friend");
				config.getList().put("friend",(ArrayList<User>)(ms.getList().get("friend").clone()));
				total.addAll(honeyList);
				
				config.getList().put("total", total);
				showList(total,LocalData.friendlist);
				dialog.dismiss();
				
				
				new LocalTask(FriendView.this.getApplicationContext(),LocalData.writefriend,hand).start();
				}
				if(msg.what==MessageDate.SEARCH_FRIEND_SUCCESS)
				{
					cn.iolove.domain.Message ms = (cn.iolove.domain.Message)(msg.obj);
					config.getList().put("searchfriend", ms.getList().get("searchfriend"));
					showList(ms.getList().get("searchfriend"),LocalData.searchlist);
			
				}
				if(msg.what==MessageDate.CHECKFRIENDNUM)
				{
					
					cn.iolove.domain.Message ms = (cn.iolove.domain.Message)(msg.obj);
					String num = ms.getExtra().get("checkfriendnum");
					
					
					if(Integer.parseInt(num)>config.getList().get("friend").size() )
					{
						UpdateFriendList();
					}
					
				}
				
			}
		
		}
	



		private void showList(ArrayList<User> list,int t) 
		{
			MyAdapter ad = new MyAdapter(FriendView.this,ParseUserList(list),t);

			
			
			lv.setAdapter(ad);
			ad.notifyDataSetChanged();
			if(list.size()==1&&t==LocalData.friendlist)
			{

				Toast.makeText(FriendView.this, "没有找到和你同生日的好友,赶快邀请更多人加入吧", 6000).show();
			//lv.getBackground().setAlpha(100);
			
			
			}

		}
		
	};
	 void UpdateFriendList() 
	{
		new Task(packMessage.UpdateFriendlist(),hand).start();

		dialog.show();
	
		
	}
	public List ParseUserList(ArrayList<User> list )
	{ 	List<HashMap<String,Object>> lists = new ArrayList<HashMap<String,Object>>();
		Iterator<User> i = list.iterator();
		if(!list.isEmpty())
		{
			while(i.hasNext())
			{
				User u = (User)(i.next());
				HashMap<String,Object> hm = new HashMap<String, Object>();
				hm.put("extrs", u.toString());
				if(u.getSex().equals("男"))	
				hm.put("head", cn.iolove.birthdaybar.R.drawable.boy);
				else hm.put("head", cn.iolove.birthdaybar.R.drawable.girl);
				hm.put("name",u.getNickname() );
				lists.add(hm);				
			}
		}
		return lists;
			
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); //
		setContentView(cn.iolove.birthdaybar.R.layout.friendview);  
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, cn.iolove.birthdaybar.R.layout.titile);
		ed = (EditText)findViewById(cn.iolove.birthdaybar.R.id.friend_edit_search);
		dialog  = DialogFactory.creatRequestDialog(FriendView.this, "正在查找生日同一天的人..");
		search =(Button)findViewById(cn.iolove.birthdaybar.R.id.friend_btn_sear);
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				searchfriend();
			
			}
		});
		
		
		vc.setFriendHandler(hand);
		lv =(ListView)findViewById(cn.iolove.birthdaybar.R.id.listss);
		ly = (LinearLayout)findViewById(cn.iolove.birthdaybar.R.id.friend_lyout);
		ly.getBackground().setAlpha(100);
		searchbar =(LinearLayout)findViewById(cn.iolove.birthdaybar.R.id.friend_ly_search);
		searchbar.getBackground().setAlpha(100);
		title = (TextView)findViewById(cn.iolove.birthdaybar.R.id.titiletext);
		title.setText("生日对对碰-好友列表");
		title_left=(Button)findViewById(cn.iolove.birthdaybar.R.id.title_btn_left);
		title_left.setVisibility(View.INVISIBLE);
		title_right=(Button)findViewById(cn.iolove.birthdaybar.R.id.title_option);
		title_right.setVisibility(View.INVISIBLE);


	
		new LocalTask(FriendView.this.getApplicationContext(),LocalData.readfriend,hand).start();
		

	}
	protected void searchfriend() {
		String name = ed.getText().toString();
		new Task(packMessage.searchFriend(name),hand).start();
		
	}
	
	


}
