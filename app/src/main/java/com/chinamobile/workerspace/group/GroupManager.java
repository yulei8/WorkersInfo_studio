package com.chinamobile.workerspace.group;

import java.util.ArrayList;
import java.util.List;

import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.db.CopyDbhelper;
import com.chinamobile.workerspace.db.ConstProfile;
import com.chinamobile.workerspace.utils.T;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GroupManager {

	private static  GroupManager  mInstance; 
	
	private SQLiteDatabase db;
	private CopyDbhelper  dbhelper; 
	
	
	 private final  int COLUMN_USERID       =0 ;		
     
	 private final  int COLUMN_GROUPID      =1 ;  	
	 private final  int COLUMN_FULLNAME     =2 ;  	
	 private final  int COLUMN_USERNAME     =3 ;   	
	 private final  int COLUMN_SEX          =4 ;  	
	 private final  int COLUMN_BIRTHDATE    =5;   
	                                           
	                                           
	                                           
	

	 private Context  mContext ;
	
	public static  GroupManager GetInstance(Context context){
		
		mInstance = new GroupManager(context);
		
		return mInstance ;
	}
	
	public GroupManager(Context context){
		dbhelper = new CopyDbhelper(context);
		
		db = dbhelper.getReadableDatabase() ;
		
		mContext = context ;
	}

	public boolean CheckDbExist(){
		boolean dbexist = false ;
		
		if(db!=null&&dbhelper.CheckMydatabase()){
			
			try{
				Cursor cursor = db.query(ConstProfile.TGroup.TABLE_NAME, null, null, null, null, null, null);
				if(cursor.getCount() >0)
				     dbexist = true ;
			}catch(Exception e){
				
			}
			
			
		}
		return dbexist;
	}
	
	public boolean CheckImageExist(){
		boolean bexist = false ;
		
		
		return bexist ;
	}
	
	public List<GroupItem>  GetAllGroups(){
		
		List<GroupItem> toplevel = new ArrayList<GroupItem>();
		
        
		
		
		try{
			Cursor cursor = db.query(ConstProfile.TGroup.TABLE_NAME, null, null, null, null, null, null);
			
			cursor.moveToFirst() ;
			
			
			while(cursor.isAfterLast() == false){
				GroupItem  item = new GroupItem();;
				item.setGroup_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__ID));
				item.setGroup_name(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__NAME));
				item.setParent_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__PARENTID));
				
				toplevel.add(item);
				cursor.moveToNext();
			}			
			cursor.close();
			
			
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TGroup.TABLE_NAME+"not exist,please check");		    
		}
		
		return toplevel ;
	}
	
	
	public int  GetAllGroupsCount(){
		int count = 1 ;
		
		List<String> list = new ArrayList<String>();
		String  selection = "select * from gb_dwxx_table where length(dwbm) >=4 ";
		 
		try{
			Cursor cursor = db.rawQuery(selection, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				
				String bm = cursor.getString(0);
				list.add(bm);
				cursor.moveToNext();
			}	
					
			cursor.close();
	
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TGroup.TABLE_NAME+"not exist,please check");		    
		}
		
		for(int i = 0 ; i< list.size()-1 ;i++){
			if(list.get(i+1).indexOf(list.get(i))!=-1){
				continue ;
			}
			count++ ;
		}
		
		return count ;
	}
	
	public List<GroupItem>  GetTopLevelGroup(){
		List<GroupItem> toplevel = new ArrayList<GroupItem>();
		
        String  selection = "length("+ConstProfile.TGroup.COLUMN_ID+")" + "=2";
       
		
		String[] selectionArgs = new  String[]{ String.valueOf(2) }; 
		
		
		try{
			Cursor cursor = db.query(ConstProfile.TGroup.TABLE_NAME, null, selection, null, null, null, null);
			
			cursor.moveToFirst() ;
			
			
			while(cursor.isAfterLast() == false){
				GroupItem  item = new GroupItem();;
				item.setGroup_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__ID));
				item.setGroup_name(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__NAME));
				item.setParent_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__PARENTID));
				
				toplevel.add(item);
				cursor.moveToNext();
			}			
			cursor.close();
			
			
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TGroup.TABLE_NAME+"not exist,please check");		    
		}
		
		return toplevel ;
	}
	
	
	public boolean HasNextLevel(String currentid){
		
		boolean hasnext = true ;
		
		if(currentid.length() > 4){
			hasnext = false ;
		}
		
		return hasnext ;
	}
	
	public List<GroupItem>  GetChildLevelGroup(String parent_id){
		List<GroupItem> toplevel = new ArrayList<GroupItem>();
		
       String  selection = ConstProfile.TGroup.COLUMN_PARENTID + "=?";
		
		String[] selectionArgs = new  String[]{ parent_id }; 
		
		
		try{
			Cursor cursor = db.query(ConstProfile.TGroup.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
			
				
				GroupItem  item = new GroupItem();;
				item.setGroup_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__ID));
				item.setGroup_name(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__NAME));
				item.setParent_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__PARENTID));
				
				toplevel.add(item);
				
				cursor.moveToNext();
				
			}
			
			
			cursor.close();
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TGroup.TABLE_NAME+"not exist,please check");		    
		}
		
		return toplevel ;
	}

	public  GroupItem  GetGroupItemByID(String group_id){
		
		GroupItem  item = null ;
		
		String  selection = ConstProfile.TGroup.COLUMN_ID + "= ? ";
			
		
		String[] selectionArgs = new  String[]{ String.valueOf(group_id) }; 
		
		
		try{
			Cursor cursor = db.query(ConstProfile.TGroup.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			if(cursor.moveToFirst() == true){
				
				item = new GroupItem();;
				item.setGroup_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__ID));
				item.setGroup_name(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__NAME));
				item.setParent_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__PARENTID));
				 
			}
			
			
			cursor.close();
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TGroup.TABLE_NAME+"not exist,please check");		    
		}
			
		
		return item ;
		
	}
	
    
	
}
