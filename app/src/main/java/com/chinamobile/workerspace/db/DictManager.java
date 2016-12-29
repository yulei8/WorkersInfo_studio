package com.chinamobile.workerspace.db;

import com.chinamobile.workerspace.group.GroupManager;
import com.chinamobile.workerspace.utils.T;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DictManager {

	private static DictManager  mInstance ;
	
	private SQLiteDatabase db;
	private CopyDbhelper  dbhelper; 
	
	
	 private Context  mContext ;
		
	public static  DictManager GetInstance(Context context){
		
		mInstance = new DictManager(context);
		
		return mInstance ;
	}
	
	public DictManager(Context context){
		dbhelper = new CopyDbhelper(context);
		
		db = dbhelper.getReadableDatabase() ;
		
		mContext = context ;
	}
	
	
	public String  GetMcbylbandbm(String lb,String bm){
		
		
		if(bm == null){
			return "" ;
		}
		
		if(bm.isEmpty() == true){
			return "";
		}
		String name = null ;
		
		if("xb".endsWith(lb) == true){
			switch(Integer.valueOf(bm)){
			case 1:
				name = new String("男");
				break;
			case 2:
				name = new String("女");
				break;
				default:
					break;
			}
			return name ;
		}else if("mz".equals(lb) == true){
			switch(Integer.valueOf(bm)){
			case 1:
				name = new String("汉族");
				return name ;
			case 3:
				name = new String("满族");
				return name ;
			case 14:
				name = new String("朝鲜族");
				return name ;
			   default:
					break;
			}
		}else if("zzmm".equals(lb) == true){
			switch(Integer.valueOf(bm)){
			case 1:
				name = new String("中共党员");
				return name ;
			   default:
					break;
			}
		}else if("zgzt".equals(lb) == true){
			switch(Integer.valueOf(bm)){
			case 1:
				name = new String("在职");
				return name ;
			   default:
					break;
			}
		}else if("jkzk".equals(lb) == true){
			switch(Integer.valueOf(bm)){
			case 1:
				name = new String("健康");
				return name ;
			   default:
					break;
			}
		}
		
		String  selection = ConstProfile.TDict.COLUMN_lb + " =? and " + ConstProfile.TDict.COLUMN_bm+" =? ";
		
		String[] selectionArgs = new  String[]{ lb,bm }; 
		
		//String  selection = ConstProfile.TDict.COLUMN_lb + " = " +"'"+lb+"'" +" and "+ ConstProfile.TDict.COLUMN_bm+" = "+"'"+bm+",";
		
		
		
		
		
		try{
			Cursor cursor = db.query(ConstProfile.TDict.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			name = new String();
			
			name = cursor.getString(ConstProfile.TDict.COLUMN_INDEX_mc) ;
			
			cursor.close();
		
		}
		catch(Exception e){
			
			Log.d("GetMcbylbandbm", "exception:"+"lb:"+lb+"mb:"+bm);
		    T.showLong(mContext, ConstProfile.TDict.TABLE_NAME+" not exist yulei1 lb:"+lb+"mb:"+bm);
		    
		}

		return name ;
	}
	
	
	public String  GetBmbylbandmc(String lb,String mc){
		
		
		if(mc == null){
			return "" ;
		}
		
		if(mc.isEmpty() == true){
			return "";
		}
		
		
		String  selection = ConstProfile.TDict.COLUMN_lb + " =? and " + ConstProfile.TDict.COLUMN_mc+" =? ";
	
		String[] selectionArgs = new  String[]{ lb, mc}; 
		
		//String  selection = ConstProfile.TDict.COLUMN_lb + " = " +"'"+lb+"'"+" and " + ConstProfile.TDict.COLUMN_mc+" = " +"'"+mc+"'";
		
		String name = null ;
		
		try{
			Cursor cursor = db.query(ConstProfile.TDict.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			name = new String();
			
			name = cursor.getString(ConstProfile.TDict.COLUMN_INDEX_bm) ;
			
			cursor.close();
		
		}
		catch(Exception e){
			Log.d("GetBmbylbandmc", "exception:"+"lb:"+lb+"mc:"+mc);
		    T.showLong(mContext, ConstProfile.TDict.TABLE_NAME+"not exist, yulei2 lb:"+lb+"mc:"+mc);
		    
		}

		return name ;
	}
	
}
