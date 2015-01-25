package cn.iolove.birthdaybar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cn.iolove.Service.Task;
import cn.iolove.domain.Message;
import cn.iolove.domain.MessageDate;
import cn.iolove.domain.packMessage;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class paihangView extends Activity {
	private Button title_right;
	private Button title_left;
	private ListView lv;
	private ListView feizaolv;

	private Handler hand = new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			if(msg.what==MessageDate.GETXIANGJIAOLIST_SUCCESS)
			if(msg.obj instanceof Message)
			{
				Message ms = (Message)msg.obj;
				lv.setAdapter(new paihangAdapter(paihangView.this,parseXiangjiaoList(ms)));
				feizaolv.setAdapter(new paihangAdapter(paihangView.this,parseFeizaoList(ms)));
				
				
				
				
				
				
			}
			
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); //
		setContentView(cn.iolove.birthdaybar.R.layout.paihang);  
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, cn.iolove.birthdaybar.R.layout.titile);
		title_left=(Button)findViewById(R.id.title_btn_left);
		feizaolv =(ListView)findViewById(R.id.paihang_lv_fzao);
		title_right=(Button)findViewById(R.id.title_option);
		lv = (ListView)findViewById(R.id.paihang_lv_xiangjiao);
		Toast.makeText(paihangView.this, "正在加载排行榜。。", Toast.LENGTH_SHORT).show();
		new Task(packMessage.getXiangjiaoList(),hand).start();
		title_left=(Button)findViewById(cn.iolove.birthdaybar.R.id.title_btn_left);
		title_left.setVisibility(View.INVISIBLE);
		title_right=(Button)findViewById(cn.iolove.birthdaybar.R.id.title_option);
		title_right.setVisibility(View.INVISIBLE);
		
		
	}
	protected List parseFeizaoList(Message ms) {
		ArrayList<HashMap<String,Object>> list =new ArrayList<HashMap<String,Object>>();
		ArrayList<String> l = ms.paihangFnamelist;
		Iterator<String> i = l.iterator();
		int s =0;
		while(i.hasNext())
		{
			HashMap<String,Object> hm = new HashMap<String,Object>();
			hm.put("name", (String)i.next());
			hm.put("num", ms.paihangFnumlist.get(s));
			s++;
			list.add(hm);
		
		}
		
		return list;
	

	}
	protected List parseXiangjiaoList(Message ms) {
		
		ArrayList<HashMap<String,Object>> list =new ArrayList<HashMap<String,Object>>();
		ArrayList<String> l = ms.paihangXnamelist;
		Iterator<String> i = l.iterator();
		int s =0;
		while(i.hasNext())
		{
			HashMap<String,Object> hm = new HashMap<String,Object>();
			hm.put("name", (String)i.next());
			hm.put("num", ms.paihangXnumlist.get(s));
			s++;
			list.add(hm);
		
		}
		
		return list;
	}

}
