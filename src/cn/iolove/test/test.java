package cn.iolove.test;


import cn.iolove.domain.SqliteManager;
import android.test.AndroidTestCase;

public class test extends AndroidTestCase {
	public void createDB() throws Exception
	{
		SqliteManager  sm = new SqliteManager(getContext());
		sm.getDbOpenHelper().getWritableDatabase();
		
	
		
		
		
	}
	

}
