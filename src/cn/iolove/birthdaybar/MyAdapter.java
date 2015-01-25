package cn.iolove.birthdaybar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.View.OnClickListener;

import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import cn.iolove.domain.LocalData;

public class MyAdapter extends BaseAdapter {
	private List<HashMap<String,Object>> listitems;
	private Context context;
	private int type =LocalData.friendlist;//选择的是friend 还是搜索好友
	private LayoutInflater ListContainer;
	public final class listitemView{
		public TextView name;
		public TextView extr;
		public ImageView head;
		public RelativeLayout ly;
		

	}
	public MyAdapter(Context context,List list,int friendorhoney) {
		this.context=context;
		ListContainer = LayoutInflater.from(context);
		listitems = list;
		this.type=friendorhoney;
	}
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listitems.size();
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	public void showToast(int position)
	{
	
	}
	public void showInfoView(int postion)
	{
		Intent i = new Intent();
		i.setClass(context, MyInformation.class);
		i.putExtra("index", postion);
		i.putExtra("type", type);
		context.startActivity(i);
		
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int SelectID = position;
		listitemView  listitem =null;
		if(convertView==null)
		{
			listitem = new listitemView();
			convertView = ListContainer.inflate(R.layout.friendlist, null);
			listitem.extr=(TextView)(convertView.findViewById(R.id.extr));
			listitem.head=(ImageView)(convertView.findViewById(R.id.head));
			listitem.name=(TextView)(convertView.findViewById(R.id.name));
			listitem.ly =(RelativeLayout)(convertView.findViewById(R.id.ly));

			 convertView.setTag(listitem);
		}
		else{
			listitem=(listitemView) convertView.getTag();
		}
		listitem.extr.setText((String)(listitems.get(position).get("extrs")));
		listitem.head.setBackgroundResource((Integer)(listitems.get(position).get("head")));
		listitem.name.setText((String)(listitems.get(position).get("name")));
		listitem.ly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast(SelectID);
				showInfoView(SelectID);
	
				
				
			
			
				
			}
		});

		
		
		
		   return convertView;   
	}

}

