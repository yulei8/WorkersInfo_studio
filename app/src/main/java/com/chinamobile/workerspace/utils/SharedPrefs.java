package com.chinamobile.workerspace.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefs {
     
	private static SharedPrefs instance  = null;
	
	
	private String  password;
	private String  gesturepassword;
	
	private static Context  ctx ;
	private static SharedPreferences Prefs;
	
	final private String SHARED_PASSWORD = "password";
	final private String SHARED_GESTURE_PASSWORD = "gesture_password";
	
	final private String SHARED_INIT_TIPS = "init_tips";
	final private String SHARED_IMAGE_PATH = "image_path";
	
	
	
	
	public static SharedPrefs GetInstance(Context context){
		if(instance == null){
			synchronized(SharedPrefs.class){
				instance = new SharedPrefs();
			}
			SetContext(context);
		}
		return instance ; 
	}
	
	public static void SetContext(Context context){
		instance.ctx = context ;
		Prefs = instance.ctx.getSharedPreferences("sharedpreference",
                Context.MODE_PRIVATE);
	}
	
	
	public String GetPasswordPrefs(){
		password = Prefs.getString(SHARED_PASSWORD, "");
		return password ;
	}
	
	public String GetGesturePasswordPrefs(){
		password = Prefs.getString(SHARED_GESTURE_PASSWORD, "");
		
	
		return password ;
	}
	
	
	public void PutPassword(String password){
		Editor editor = Prefs.edit();
	    
	    editor.putString(SHARED_PASSWORD,password);
	   
	    editor.commit();	
	}
	
	
	public void PutGesturePassword(String password){
		Editor editor = Prefs.edit();
	    
	    editor.putString(SHARED_GESTURE_PASSWORD,password);
	   
	    editor.commit();
		
	}
	
	public String GetInitTipsPrefs(){
		
		return Prefs.getString(SHARED_INIT_TIPS, "") ;
	}
	
	public String GetImagePathPrefs(){
		
	
		return Prefs.getString(SHARED_IMAGE_PATH, "");
	}
	
	
	public void PutInitTipsPrefs(String tips){
		Editor editor = Prefs.edit();
	    
		
	    editor.putString(SHARED_INIT_TIPS,tips);
	   
	    editor.commit();	
	}
	
	public void ClearInformation(){
		Editor editor = Prefs.edit();	
	    editor.clear();
	    editor.commit();
	}
	
	public void PutImagePath(String path){
		Editor editor = Prefs.edit();
	    
	    editor.putString(SHARED_IMAGE_PATH,path);
	   
	    editor.commit();
		
	}
	

	
}
