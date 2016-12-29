package com.chinamobile.workerspace.favorite;

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

public class FavoriteManager {

	private static  FavoriteManager  mInstance; 
	
	private SQLiteDatabase db;
	private CopyDbhelper  dbhelper; 
	
	


	 private Context  mContext ;
	
	public static  FavoriteManager GetInstance(Context context){
		
		if(mInstance == null){
			mInstance = new FavoriteManager(context);
		}
		
		
		return mInstance ;
	}
	
	public FavoriteManager(Context context){
		dbhelper = new CopyDbhelper(context);
		
		db = dbhelper.getReadableDatabase() ;
		
		mContext = context ;
	}

	public boolean CheckFavoriteUserExist(){
		boolean dbexist = false ;
		
		if(db!=null&&dbhelper.CheckMydatabase()){
			
			try{
				Cursor cursor = db.query(ConstProfile.TFavoriteuser.TABLE_NAME, null, null, null, null, null, null);
				
				dbexist = true ;
			}catch(Exception e){
				
			}	
			
		}
		return dbexist;
	}
	
	public boolean CheckFavoriteGrouprExist(){
		boolean dbexist = false ;
		
		if(db!=null&&dbhelper.CheckMydatabase()){
			
			try{
				Cursor cursor = db.query(ConstProfile.TFavoritegroup.TABLE_NAME, null, null, null, null, null, null);
				
				dbexist = true ;
			}catch(Exception e){
				
			}	
			
		}
		return dbexist;
	}
	
	
	
	public List<FavorateItem> GetAllFavorateUsers(){
		
		List<FavorateItem>  list = new ArrayList<FavorateItem>();
		
		try{
			Cursor cursor = db.query(ConstProfile.TFavoriteuser.TABLE_NAME, null, null, null, null, null, null);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				FavorateItem  item = new FavorateItem();
				
				item.setItem_id(cursor.getString(ConstProfile.TFavoriteuser.COLUMN_INDEX__USERID));

                item.setAdd_time(cursor.getString(ConstProfile.TFavoriteuser.COLUMN_INDEX__ADDTIME));
				
				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TFavoriteuser.TABLE_NAME+"not exist,please check");
		}
		
		return list ;
		
	}
	
		public List<FavorateItem> GetAllFavorateGroups(){
		
		List<FavorateItem>  list = new ArrayList<FavorateItem>();
		
		try{
			Cursor cursor = db.query(ConstProfile.TFavoritegroup.TABLE_NAME, null, null, null, null, null, null);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				FavorateItem  item = new FavorateItem();
				
				item.setItem_id(cursor.getString(ConstProfile.TFavoritegroup.COLUMN_INDEX__GROUPID));

                item.setAdd_time(cursor.getString(ConstProfile.TFavoritegroup.COLUMN_INDEX__ADDTIME));
				
				list.add(item);
				cursor.moveToNext();
			}
			cursor.close();
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TFavoritegroup.TABLE_NAME+"not exist,please check");
		}
		
		return list ;
		
	}
      
		
		
		
		public int CheckUserIfExist(String  sfz_no){
			int  id = -1 ;
			
			String  selection = ConstProfile.TFavoriteuser.COLUMN_USERID +"=?";
			
			String[] selectionArgs = new  String[]{ sfz_no}; 
			
			
			Cursor cursor = db.query(ConstProfile.TFavoriteuser.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
			cursor.moveToFirst();
			if(cursor.getCount() >0){
			   id = cursor.getInt(0);
			}
			cursor.close();
			return id ;
		}
		
		public int CheckGroupIfExist(String  group_id){
			int  id = -1 ;
			
			String  selection = ConstProfile.TFavoritegroup.COLUMN_GROUPID +"=?";
			
			String[] selectionArgs = new  String[]{ String.valueOf(group_id) }; 
			
			
			Cursor cursor = db.query(ConstProfile.TFavoritegroup.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
			cursor.moveToFirst();
			if(cursor.getCount() >0){
			   id = cursor.getInt(0);
			}
			cursor.close();
			return id ;
		}
		
		
		
		public int DeleteUserItem(String  user_id){
			int id = db.delete(ConstProfile.TFavoriteuser.TABLE_NAME, ConstProfile.TFavoriteuser.COLUMN_USERID+"=?", new String[]{user_id});
			
			return id;
		}
		
		
		public void RemoveallUsers(){
			db.delete(ConstProfile.TFavoriteuser.TABLE_NAME, null, null);
			
		}
		
		public int DeleteGroupItem(int group_id){
			int id = db.delete(ConstProfile.TFavoritegroup.TABLE_NAME, ConstProfile.TFavoritegroup.COLUMN_GROUPID+"=?", new String[]{String.valueOf(group_id)});
			
			return id;
		}
		
		
		public void RemoveallGroups(){
			db.delete(ConstProfile.TFavoritegroup.TABLE_NAME, null, null);
			
		}
	
	
		public void AddUserItem(String   user_id){
			ContentValues values = new ContentValues();
			
			
			Date date=new Date();
	    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	
	    	
			values.put(ConstProfile.TFavoriteuser.COLUMN_USERID, user_id);
			values.put(ConstProfile.TFavoriteuser.COLUMN_ADDTIME,df.format(date));
						
			
			db.insert(ConstProfile.TFavoriteuser.TABLE_NAME, null, values);
				
			
		}
		
		public void AddGroupItem(String   group_id){
			ContentValues values = new ContentValues();
			
			
			Date date=new Date();
	    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	
	    	
			values.put(ConstProfile.TFavoritegroup.COLUMN_GROUPID, group_id);
			values.put(ConstProfile.TFavoritegroup.COLUMN_ADDTIME,df.format(date));
						
			
			db.insert(ConstProfile.TFavoritegroup.TABLE_NAME, null, values);
				
			
		}
    

}
