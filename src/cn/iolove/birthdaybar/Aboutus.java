package cn.iolove.birthdaybar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Aboutus extends Activity {
	private LinearLayout ly;
	private TextView copy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);
		ly = (LinearLayout)findViewById(R.id.aboutus_ly);
		ly.getBackground().setAlpha(100);
		copy=(TextView)findViewById(R.id.aboutus_copyright);
		copy.setText("   首先感谢大家对我的支持,软件的开发测试经历了一个多月的时间,在这一个月里我学习到了很多,也非常感谢队友周晨,尚焱对这部作品的大力帮助支持,谨以此献给所有爱好开发的朋友。                                  --窦佳  邮箱:316766907@QQ.com");
		
	}

	

}
