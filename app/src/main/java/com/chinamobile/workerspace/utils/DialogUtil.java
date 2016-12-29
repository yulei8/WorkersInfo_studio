package com.chinamobile.workerspace.utils;


import com.chinamobile.workerspace.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.DisplayMetrics;

public class DialogUtil {

	public static Dialog GetCustomProgressDialog(Activity context,int prompt){
		Dialog  dlg = new Dialog(context, R.style.DialogStyle);
		dlg.setCancelable(false);
		dlg.setContentView(R.layout.custom_progress_dialog);
		Window window = dlg.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();				
		int screenw = getScreenWidth(context);
		
		lp.width = (int) (0.6 * screenw);				
		
		TextView titleTxtv = (TextView) dlg.findViewById(R.id.dialogText);
		titleTxtv.setText(prompt);
		
		return dlg;
		
	}

	
	public static int getScreenWidth(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	public static int getScreenHeight(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}
