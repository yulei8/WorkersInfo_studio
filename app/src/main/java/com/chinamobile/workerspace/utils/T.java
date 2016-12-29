package com.chinamobile.workerspace.utils;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast缁熶竴绠＄悊绫�
 * 
 * @author way
 * 
 */
public class T {
	// Toast
	private static Toast toast;

	/**
	 * 
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (null == toast) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	public static void showShortIntop(Context context, CharSequence message) {
		if (null == toast) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		
		toast.setGravity(Gravity.TOP, 150, 50);
		toast.show();
	}
	
	/**
	 * 
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, int message) {
		if (null == toast) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (null == toast) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
		if (null == toast) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		if (null == toast) {
			toast = Toast.makeText(context, message, duration);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
		if (null == toast) {
			toast = Toast.makeText(context, message, duration);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/** Hide the toast, if any. */
	public static void hideToast() {
		if (null != toast) {
			toast.cancel();
		}
	}
	public static boolean IsMobileNum(String num){
		String nummatch = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		
		if(num.matches(nummatch)){
			return true;
		}else{
			return false;
		}
		
	}
	//0:mobile  ,1:username  , other -1
	public static int  GetSearchType(String str){
		int type = -1 ;
		
		String nummatch="^[0-9]{5,11}$";
		if(str.matches(nummatch)){
			type = 0 ;
		}else if(str.length() >1 && str.length() < 15){
			type = 1 ;
		}
		
		return type ;
	}
	
	public static boolean IsvalidUsername(String name){
		String namematch = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
		
		if(namematch.matches(name)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean IsEmail(String email){
		String emailmatch = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		
		if(email.matches(emailmatch)){
			return true ;
		}else {
			return false;
		}
	}
}
