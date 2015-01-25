package cn.iolove.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;

import cn.iolove.domain.Message;

public class Service {
	private String ip="192.168.34.1";
	private int port=3481;
	private Socket so ;
	private Context context;
	private Handler handler;
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public Service(Context co) {
		context =co;
	
	}
	public void save(String userid,String password)
	{
		SharedPreferences sp = context.getSharedPreferences("iolove",Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString("userid", userid);
		edit.putString("password", password);
		edit.commit();
		
		
	}
	public HashMap<String,String> getValue()
	{
		HashMap<String, String> hm = new HashMap<String,String>();
		SharedPreferences sp = context.getSharedPreferences("iolove",Context.MODE_PRIVATE);
		hm.put("userid", sp.getString("userid", ""));
		hm.put("password", sp.getString("password", ""));
		return hm;
		
	}


		
	
		
	
	
	

}
