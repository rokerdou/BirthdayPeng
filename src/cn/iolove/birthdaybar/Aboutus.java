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
		copy.setText("   ���ȸ�л��Ҷ��ҵ�֧��,����Ŀ������Ծ�����һ�����µ�ʱ��,����һ��������ѧϰ���˺ܶ�,Ҳ�ǳ���л�����ܳ�,���Ͷ��ⲿ��Ʒ�Ĵ�������֧��,���Դ��׸����а��ÿ��������ѡ�                                  --��  ����:316766907@QQ.com");
		
	}

	

}
