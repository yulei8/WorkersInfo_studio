package com.chinamobile.workerspace.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class CopyDbhelper extends SQLiteOpenHelper {

	private static final int DB_VERSION    = 1;
	
	
	public static String DB_PATH_FORMAT = "/data/data/com.chinamobile.workerspace/databases/workers.db";
	
	private static String  DB_PATH    =  "/data/data/com.chinamobile.workerspace/databases/";
	
	private static String DB_NAME         = ConstProfile.DB_NAME;
 
	private static String ASSETS_NAME     = "messager.db";

	private SQLiteDatabase myDataBase    = null;
	private Context myContext;
	
	public CopyDbhelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
		myContext = context ;
		// TODO Auto-generated constructor stub
	}

	public CopyDbhelper(Context context, String name, int version){
	       this(context,name,null,version);
	   }

	
   
	public CopyDbhelper(Context context, String name){
       this(context,name,DB_VERSION);
   }

 
	public CopyDbhelper (Context context) {
	       this(context, DB_PATH_FORMAT);	          	       
	 }
	
	
	public boolean  CheckMydatabase(){
		SQLiteDatabase checkDB = null;
		String myPath = DB_PATH_FORMAT;

		try{
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			
		}
	    
		if(checkDB!=null){
			checkDB.close();
		}
		return (checkDB == null)?false:true;
	}
	
	private void CopyDatabase(File srcFile) throws IOException{
		   
		   
	       String outFileName = DB_PATH_FORMAT;
	       
	       
	       FileInputStream myInput = new FileInputStream(srcFile);
	       
	       OutputStream myOutput = new FileOutputStream(outFileName);
	       byte[] buffer = new byte[1024];
	       int length;
	       while ((length = myInput.read(buffer))>0){
	           myOutput.write(buffer, 0, length);
	       }
	       myOutput.flush();
	       myOutput.close();
	       myInput.close();
	       
	}
	
	
	public static boolean  DeleteDatabase(){
		File dbfile = new File(DB_PATH_FORMAT);
		
		 return dbfile.delete();
		 
	}
	
	
	public boolean  ImportDatabase(String outfilename){
		
 
			try {
				File dir = new File(DB_PATH);
				
				if(!dir.exists()){
					dir.mkdir();
				}
				
				File dbfile = new File(DB_PATH_FORMAT);
				if(dbfile.exists()){
					dbfile.delete();
				}
				
				File srcFile = new File(outfilename);
				
				if(srcFile.exists() == false){
					return false ;
				}
				
				//SQLiteDatabase.openOrCreateDatabase(dbfile, null);
				
				
				CopyDatabase(srcFile);
				
				return true ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false ;
			}
			
		
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
       
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
