package com.chinamobile.workerspace.db;


import java.util.ArrayList;
import java.util.List;

import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.contacts.ZhijiItem;
import com.chinamobile.workerspace.utils.T;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ZhijiQueryManager {

	private static ZhijiQueryManager  mInstance ;
	
	private SQLiteDatabase db;
	private CopyDbhelper  dbhelper; 
	
	
	private Context  mContext ;
		
	public static  ZhijiQueryManager GetInstance(Context context){
		
		mInstance = new ZhijiQueryManager(context);
		
		return mInstance ;
	}
	
	public ZhijiQueryManager(Context context){
		dbhelper = new CopyDbhelper(context);
		
		db = dbhelper.getReadableDatabase() ;
		
		mContext = context ;
	}
	
	
	public List<ZhijiItem>  GetZhijiInfomation(String  sfz_no){
		
		
	   List<ZhijiItem>  items = new ArrayList<ZhijiItem>();
		
		String  selection = ConstProfile.TZjsj.COLUMN_sfz_no + " = " + "'" +sfz_no+"'";
		
		
		
		try{
			
			Cursor cursor = db.query(ConstProfile.TZjsj.TABLE_NAME, null, selection, null, null, null, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				
				ZhijiItem  item = new ZhijiItem();
				
				
		    	 item.setSfz_no(cursor.getString(ConstProfile.TZjsj.COLUMN_INDEX_sfz_no));     
				 item.setXh(cursor.getInt(ConstProfile.TZjsj.COLUMN_INDEX_xh));
				 item.setLdzj(cursor.getString(ConstProfile.TZjsj.COLUMN_INDEX_ldzj));
				 item.setRzjsj(cursor.getString(ConstProfile.TZjsj.COLUMN_INDEX_rzjsj));
				 item.setBz(cursor.getString(ConstProfile.TZjsj.COLUMN_INDEX_bz));
				
				 items.add(item);
				 cursor.moveToNext();
			}
			
			cursor.close();
		
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TZjsj.TABLE_NAME+"not exist,please check");
		    
		}

		return items ;
	}
	
	
	
}
