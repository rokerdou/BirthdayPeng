package cn.iolove.birthdaybar;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import cn.iolove.birthdaybar.MyAdapter.listitemView;
import cn.iolove.domain.LocalData;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class XiaoneiAdapter extends BaseAdapter {
	private Context con;
	private List<HashMap<String,Object>> listitems;
	private ViewContext vc= ViewContext.getInstance();
	private int type;
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	
	
	private LayoutInflater ListContainer;

	public final class listitemView{
		public TextView msg;
		public TextView name;
		public TextView time;
		
		public LinearLayout ly;


	}
	public XiaoneiAdapter(Context co,List list) {
		con = co;
		ListContainer = LayoutInflater.from(co);
		listitems=list;
	}
	public void  showinfo(String userid)
	{
		Intent i = new Intent();
		i.setClass(con, MyInformation.class);
		switch(type)
		{
			case LocalData.xiaoneilist:
				i.putExtra("index", vc.getindexfromlist(userid, LocalData.xiaoneilist));
				i.putExtra("type", LocalData.xiaoneilist);
				break;
			case LocalData.msglist:
				i.putExtra("index", vc.getindexfromlist(userid, LocalData.msglist));
				i.putExtra("type", LocalData.msglist);
				break;
				
		}
		

		con.startActivity(i);
		
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listitems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final int SelectID = position;
		final String Userid;
		listitemView  listitem =null;
		if(convertView==null)
		{
			listitem = new listitemView();
			convertView = ListContainer.inflate(R.layout.xiaoneilist, null);
			listitem.msg =(TextView)(convertView.findViewById(R.id.xiaonei_tv_msg));
			listitem.ly=(LinearLayout)(convertView.findViewById(R.id.xiaonei_lv));
			
			listitem.name=(TextView)(convertView.findViewById(R.id.xiaonei_tv_nicknmae));
			listitem.time =(TextView)(convertView.findViewById(R.id.xiaonei_tv_time));

			 convertView.setTag(listitem);
		}
		else{
			listitem=(listitemView) convertView.getTag();
		}
		listitem.msg.setText((String)(listitems.get(position).get("msg")));
		listitem.name.setText((String)(listitems.get(position).get("name")));
		
		Userid = (String)(listitems.get(position).get("userid"));
		SimpleDateFormat mft = new SimpleDateFormat("MM-dd HH:mm");
		listitem.ly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showinfo(Userid);
				
			}
		});
		
		listitem.time.setText(mft.format(listitems.get(position).get("time")));

		
		
		
		   return convertView;   
	}

}
