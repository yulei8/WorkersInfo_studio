package com.chinamobile.workerspace.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinamobile.workerspace.db.CopyDbhelper;
import com.chinamobile.workerspace.db.ConstProfile;
import com.chinamobile.workerspace.utils.T;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class HistoryManager {

	private static  HistoryManager  mInstance; 
	
	private SQLiteDatabase db;
	private CopyDbhelper  dbhelper; 
	
	


	 private Context  mContext ;
	
	public static  HistoryManager GetInstance(Context context){
		
		if(mInstance == null){
			mInstance = new HistoryManager(context);
		}
		
		
		return mInstance ;
	}
	
	public HistoryManager(Context context){
		dbhelper = new CopyDbhelper(context);
		
		db = dbhelper.getReadableDatabase() ;
		
		mContext = context ;
	}

	public boolean CheckHistoryExist(){
		boolean dbexist = false ;
		
		if(db!=null&&dbhelper.CheckMydatabase()){
			
			try{
				Cursor cursor = db.query(ConstProfile.THistory.TABLE_NAME, null, null, null, null, null, null);
				
				dbexist = true ;
			}catch(Exception e){
				
			}	
			
		}
		return dbexist;
	}
	
	

	
	public List<HistoryItem> GetAllHistorys(){
		
		List<HistoryItem>  list = new ArrayList<HistoryItem>();
		
		try{
			Cursor cursor = db.query(ConstProfile.THistory.TABLE_NAME, null, null, null, null, null, ConstProfile.THistory.DEFAULT_SORT_ORDER);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				HistoryItem  item = new HistoryItem();
				
				item.setHistory_id(cursor.getInt(ConstProfile.THistory.COLUMN_INDEX__ID));
				item.setItem_title(cursor.getString(ConstProfile.THistory.COLUMN_INDEX__TITLE));
				item.setItem_id(cursor.getString(ConstProfile.THistory.COLUMN_INDEX__ITEMID));
				item.setItem_type(cursor.getInt(ConstProfile.THistory.COLUMN_INDEX__ITEMTYPE));
				item.setAdd_time(cursor.getString(ConstProfile.THistory.COLUMN_INDEX__ADDTIME));
				
				
				Log.d("HistoryManager", "group_name:"+item.getItem_title());
				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.THistory.TABLE_NAME+"not exist,please check");
		}
		
		return list ;
		
	}
	
		

		
		
		public int DeleteHistoryItem(int  history){
			int id = db.delete(ConstProfile.THistory.TABLE_NAME, ConstProfile.THistory.COLUMN_ID+"=?", new String[]{String.valueOf(history)});
			
			return id;
		}
		
		
		public void RemoveallHistorys(){
			db.delete(ConstProfile.THistory.TABLE_NAME, null, null);
			
		}
		
		
	
	
		private void AddHistoryItem(String title,String item_id,int item_type){
			ContentValues values = new ContentValues();
			
			
			Date date=new Date();
	    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	
	    	
			values.put(ConstProfile.THistory.COLUMN_ITEMTITLE, title);
			values.put(ConstProfile.THistory.COLUMN_ITEMID,String.valueOf(item_id));

			values.put(ConstProfile.THistory.COLUMN_ITEMTYPE,String.valueOf(item_type));
			values.put(ConstProfile.THistory.COLUMN_ADDTIME,df.format(date));
			
			
			db.insert(ConstProfile.THistory.TABLE_NAME, null, values);
				
			
		}
		
		private void UpdateHistoryItem(int id,String title ,String item_id,int item_type){
			
			
			String selection = ConstProfile.THistory.COLUMN_ID +"=?";

			String[] selectionArgs = new  String[]{ String.valueOf(id) }; 
			
			ContentValues values = new ContentValues();
			
			Date date=new Date();
	    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	values.put(ConstProfile.THistory.COLUMN_ADDTIME,df.format(date));
	    	
	    	db.update(ConstProfile.THistory.TABLE_NAME, values, selection, selectionArgs);
	    	
	    	
		}
		/*
		 * group:  item_type  = 1 
		 * user:   item_type  = 0;
		 * statics  item_type  > 1;
		 * 
		 */
    
		public void InsertOrUpdateHistory(String title,String item_id,int item_type){
			
			
			try{
				Cursor cursor = db.query(ConstProfile.THistory.TABLE_NAME, null, null, null, null, null, ConstProfile.THistory.DEFAULT_SORT_ORDER);
				
				if(cursor.moveToFirst() == true){
					HistoryItem  item = new HistoryItem();
					
					item.setHistory_id(cursor.getInt(ConstProfile.THistory.COLUMN_INDEX__ID));
					item.setItem_title(cursor.getString(ConstProfile.THistory.COLUMN_INDEX__TITLE));
					item.setItem_id(cursor.getString(ConstProfile.THistory.COLUMN_INDEX__ITEMID));
					item.setItem_type(cursor.getInt(ConstProfile.THistory.COLUMN_INDEX__ITEMTYPE));
					
					if((item_id.equals(item.getItem_id())&&(item_type == item.getItem_type()))){
						UpdateHistoryItem(item.getHistory_id(),item.getItem_title(),item.getItem_id(),item.getItem_type());
						
					}else{
						AddHistoryItem(title,item_id,item_type);
					}
					
					
				}else{
					AddHistoryItem(title,item_id,item_type);
				}
				cursor.close();
				
			}catch(Exception e){
				T.showShort(mContext, ConstProfile.THistory.TABLE_NAME+"InsertOrUpdateHistory exception");
			}
			
			
		}

}
