package com.chinamobile.workerspace.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDbhelper extends SQLiteOpenHelper {

	public BaseDbhelper(Context context) {
		super(context, ConstProfile.DB_NAME, null, ConstProfile.DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		/*
		db.execSQL("CREATE TABLE IF NOT EXISTS " + ConstProfile.TUser.TABLE_NAME + " (" + ConstProfile.TUser.COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ ConstProfile.TUser.COLUMN_GROUPID        + " INTEGER,"
				+ ConstProfile.TUser.COLUMN_FULLNAME        + " TEXT," 
				+ ConstProfile.TUser.COLUMN_USERNAME	 + " TEXT,"
				+ ConstProfile.TUser.COLUMN_SEX            + " TEXT,"
				+ ConstProfile.TUser.COLUMN_BIRTHDATE        + " TEXT"
				
				+ ConstProfile.TUser.COLUMN_USERCODE         + " TEXT," 
				+ ConstProfile.TUser.COLUMN_NATIVEPLACE	     + " TEXT,"
				+ ConstProfile.TUser.COLUMN_BIRTHPLACE       + " TEXT,"
				+ ConstProfile.TUser.COLUMN_NATION        + " TEXT"
				+ ConstProfile.TUser.COLUMN_RDTIME        + " TEXT," 
				+ ConstProfile.TUser.COLUMN_POLITICSSTATUS	 + " TEXT,"
				+ ConstProfile.TUser.COLUMN_JOBTIME            + " TEXT,"
				+ ConstProfile.TUser.COLUMN_STATE        + " TEXT);" 				
				);
		
		*/
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        
	}

}
