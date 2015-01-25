package cn.iolove.birthdaybar;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import cn.iolove.birthdaybar.XiaoneiAdapter.listitemView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class paihangAdapter extends BaseAdapter {
	private Context con;
	private List<HashMap<String,Object>> listitems;
	private ViewContext vc= ViewContext.getInstance();

	private LayoutInflater ListContainer;

	public final class listitemView{
		public TextView num;
		public TextView name;



	}
	public paihangAdapter(Context co,List list) {
		con = co;
		ListContainer = LayoutInflater.from(co);
		listitems=list;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int SelectID = position;
		final String Userid;
		listitemView  listitem =null;
		if(convertView==null)
		{
			listitem = new listitemView();
			convertView = ListContainer.inflate(R.layout.paihanglist, null);
			listitem.num =(TextView)(convertView.findViewById(R.id.paihang_tv_xjnum));
			listitem.name=(TextView)(convertView.findViewById(R.id.paihang_tv_name));
			
		
			 convertView.setTag(listitem);
		}
		else{
			listitem=(listitemView) convertView.getTag();
		}
		listitem.num.setText((String)(listitems.get(position).get("num")));
		listitem.name.setText((String)(listitems.get(position).get("name")));
		


		
		
		
		   return convertView;   
	}

}
