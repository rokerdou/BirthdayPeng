package cn.iolove.utils;

import cn.iolove.birthdaybar.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class DialogFactory {

	public static Dialog creatRequestDialog(final Context context, String tip) {

		final Dialog dialog = new Dialog(context,R.style.dialog);
		dialog.setContentView(R.layout.progress_dialog_layout);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		int width = Utils.getScreenWidth(context);
		lp.width = (int) (0.6 * width);

		TextView titleTxtv = (TextView) dialog.findViewById(R.id.tvLoad);
		{
			titleTxtv.setText(tip);
		}
		dialog.setCanceledOnTouchOutside(false);
		dialog.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				   switch (keyCode) {  
		            case KeyEvent.KEYCODE_BACK:  
		            	
		            return false;  
				   }
				   return false;
			}
		});
		
		
		return dialog;
	}

	public static void ToastDialog(Context context, String title, String msg) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
				.setPositiveButton("È·¶¨", null).create().show();
	}
}

