package com.chinamobile.workerspace.contacts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.chinamobile.workerspace.AgeRange;
import com.chinamobile.workerspace.db.CopyDbhelper;
import com.chinamobile.workerspace.db.DictManager;
import com.chinamobile.workerspace.db.ConstProfile;
import com.chinamobile.workerspace.utils.T;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContactsManager {

	private static  ContactsManager  mInstance; 
	
	private SQLiteDatabase db;
	private CopyDbhelper  dbhelper; 
	
	 
    private DictManager  mDict ; 

	     public static final int COLUMN_INDEX_sfz_no              =     0; 
		 public static final int COLUMN_INDEX_xm            	    =     1; 
		 public static final int COLUMN_INDEX_xb                  =     2; 
		 public static final int COLUMN_INDEX_csrq                =     3; 
		 public static final int COLUMN_INDEX_mz                  =     4; 
		 public static final int COLUMN_INDEX_jg                  =     5; 
		 public static final int COLUMN_INDEX_csd                 =     6; 
		 public static final int COLUMN_INDEX_czd                 =     7; 
		 public static final int COLUMN_INDEX_zzmm                =     8; 
		 public static final int COLUMN_INDEX_sflb                =     9; 
		 public static final int COLUMN_INDEX_xrzw                =     10;
		 public static final int COLUMN_INDEX_rxzsj               =     11;
		 public static final int COLUMN_INDEX_xrzj                =     12;
		 public static final int COLUMN_INDEX_rxzjsj              =     13;
		 public static final int COLUMN_INDEX_fldzw               =     14;
		 public static final int COLUMN_INDEX_rfldzwsj            =     15;
		 public static final int COLUMN_INDEX_hbgbzj              =     16;
		 public static final int COLUMN_INDEX_hbgbsj              =     17;
		 public static final int COLUMN_INDEX_rdsj                =     18;
		 public static final int COLUMN_INDEX_cjgzsj              =     19;
		 public static final int COLUMN_INDEX_jkzk                =     20;
		 public static final int COLUMN_INDEX_zyjszw              =     21;
		 public static final int COLUMN_INDEX_zytc                =     22;
		 public static final int COLUMN_INDEX_q_xl                =     23;
		 public static final int COLUMN_INDEX_q_xw                =     24;
		 public static final int COLUMN_INDEX_q_yx                =     25;
		 public static final int COLUMN_INDEX_q_zy                =     26;
		 public static final int COLUMN_INDEX_z_xl                =     27;
		 public static final int COLUMN_INDEX_z_xw                =     28;
		 public static final int COLUMN_INDEX_z_yx                =     29;
		 public static final int COLUMN_INDEX_z_zy                =     30;
		 public static final int COLUMN_INDEX_zgxl                =     31;
		 public static final int COLUMN_INDEX_jtzz                =     32;
		 public static final int COLUMN_INDEX_zfmj                =     33;
		 public static final int COLUMN_INDEX_jtnsr               =     34;
		 public static final int COLUMN_INDEX_jjst                =     35;
		 public static final int COLUMN_INDEX_jjstmcdd            =     36;
		 public static final int COLUMN_INDEX_fcgsmj              =     37;
		 public static final int COLUMN_INDEX_zytdyj              =     38;  
		 public static final int COLUMN_INDEX_tc                  =     39;  
		 public static final int COLUMN_INDEX_xg                  =     40;  
		 public static final int COLUMN_INDEX_ah                  =     41;  
		 public static final int COLUMN_INDEX_bz                  =     42;  
		 public static final int COLUMN_INDEX_dh_b                =     43;  
		 public static final int COLUMN_INDEX_dh_z                =     44;  
		 public static final int COLUMN_INDEX_dh_s                =     45;  
		 public static final int COLUMN_INDEX_dh_q                =     46;  
		 public static final int COLUMN_INDEX_beizhu              =     47;  
		 public static final int COLUMN_INDEX_zp                  =     48;  
		 public static final int COLUMN_INDEX_py_1                =     49;  
		 public static final int COLUMN_INDEX_py_2                =     50;  
		 public static final int COLUMN_INDEX_zgzt                =     51;  
		 public static final int COLUMN_INDEX_rylb                =     52;  
		 public static final int COLUMN_INDEX_zc_1                =     53;  
		 public static final int COLUMN_INDEX_jlxx                =     54;  
		 public static final int COLUMN_INDEX_gwy                 =     55;  

		
  


	 private Context  mContext ;
	
	public static  ContactsManager GetInstance(Context context){
		
		mInstance = new ContactsManager(context);
		
		return mInstance ;
	}
	
	public ContactsManager(Context context){
		dbhelper = new CopyDbhelper(context);
		
		db = dbhelper.getReadableDatabase() ;
		
		
		mDict = DictManager.GetInstance(context);
		mContext = context ;
	}

	public boolean CheckDbExist(){
		boolean dbexist = false ;
		
		if(db!=null&&dbhelper.CheckMydatabase()){
			
			try{
				Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, null, null, null, null, null);
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
	
	public List<String>  GetAllNumbers(){
		List<String>  list = new ArrayList<String>();

		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, null, null, null, null, null);

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				String  number = new String();
				
				number =  cursor.getString(COLUMN_INDEX_sfz_no); 

				 list.add(number);
				 cursor.moveToNext();
			}
			
			cursor.close();
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+" not exist,please check");
		    Log.d("yulei exception", " GetAllNumbers :") ;
		}
  
		return list ;
	}
	
	public List<UserItem> GetAllUsers(){
		
		List<UserItem>  list = new ArrayList<UserItem>();
		
		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, null, null, null, null, ConstProfile.TUser.COLUMN_py_1);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				 UserItem  item = null;
				
				 item = GetUserItemByCursor(cursor) ;

				 list.add(item);
				 cursor.moveToNext();
			}
			
			cursor.close();
		
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+" not exist,please check");
		}
		
		
	    
		return list ;
		
	}
      
	/*
	 *  user_id  is sfzh
	 */
	public UserItem GetUserById(String user_id){
		UserItem  item = null;
		
        
		String  selection = ConstProfile.TUser.COLUMN_sfz_no + "=?";
		
		String[] selectionArgs = new  String[]{ user_id }; 
		
		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			item = GetUserItemByCursor(cursor);
			
			cursor.close();
		
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		    Log.d("yulei exception", " GetUserById :"+user_id) ;
		    
		    
		}
		
		return item ;
	}
	
	public List<UserItem> GetUserBySex(String sex){
		
		List<UserItem>  list = new ArrayList<UserItem>();
		
        
		String  selection = ConstProfile.TUser.COLUMN_xb + "=?";
		
		String sexbm = mDict.GetBmbylbandmc("xb", sex);
		
		String[] selectionArgs = new  String[]{ sexbm }; 
		
		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				UserItem item = GetUserItemByCursor(cursor);

				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		    Log.d("yulei exception", " GetUserBySex :"+sex) ;
		}
		
		return list ;
	}
	
	/*
	 *   gb_zwxx_table
	 * 
	 */
    public List<UserItem> GetUserBySexandGroupId(String sex , String dwbm){
		
    	
    	String sexbm = mDict.GetBmbylbandmc("xb", sex);
    	
    	
    	
		List<UserItem>  list = GetUserByGroupInfo(dwbm,ConstProfile.TUser.COLUMN_xb,sexbm);

		Log.d("ContactManager", "GetUserBySexandGroupId ,sexbm :"+sexbm+"dwbm:"+dwbm) ;
		
		return list ;
	}
    
    public int  GetAllContactsCount(){
		int count = 0 ;
		
		String  selection = "select count(*) from "+ConstProfile.TUser.TABLE_NAME;
		 
		try{
			Cursor cursor = db.rawQuery(selection, null);
			
			cursor.moveToFirst() ;
			
			count = cursor.getInt(0);
					
			cursor.close();
	
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");		
		    Log.d("yulei exception", " GetAllContactsCount :") ;
		}
		
		return count ;
	}
    
    public String TimeFormat(String date){
    	
    	String format = date;
    	
    	
    	if(date != null){
    		
    		int index = date.indexOf(" ");
    		if(index != -1){
    			format = date.substring(0, index);
    		}
    	}else{
    		format ="";
    	}
    		
    	if(format.length() >= 7){
    		format = format.substring(0,7);
    	}
    	return format ;
    }
    
    private UserItem  GetUserItemByCursor(Cursor cursor){
    	 UserItem  item = new UserItem();
    	 item.setM_sfz_no(cursor.getString(COLUMN_INDEX_sfz_no));     
		 item.setM_xm(cursor.getString(COLUMN_INDEX_xm));    
		 
		 
		 item.setM_xb(mDict.GetMcbylbandbm("xb", cursor.getString(COLUMN_INDEX_xb)));             
		 item.setM_csrq(TimeFormat(cursor.getString(COLUMN_INDEX_csrq)));         
		 item.setM_mz(mDict.GetMcbylbandbm("mz", cursor.getString(COLUMN_INDEX_mz)));             
		 item.setM_jg(cursor.getString(COLUMN_INDEX_jg));             
		 item.setM_csd( cursor.getString(COLUMN_INDEX_csd));    
		 item.setM_czd(cursor.getString(COLUMN_INDEX_czd));
		 item.setM_zzmm(mDict.GetMcbylbandbm("zzmm", cursor.getString(COLUMN_INDEX_zzmm)));         
		 item.setM_sflb(cursor.getString(COLUMN_INDEX_sflb));         
		 item.setM_xrzw(cursor.getString(COLUMN_INDEX_xrzw));         
		 item.setM_rxzsj( TimeFormat(cursor.getString(COLUMN_INDEX_rxzsj)));      
		 item.setM_xrzj(mDict.GetMcbylbandbm("ldzj", cursor.getString(COLUMN_INDEX_xrzj)));         
		 item.setM_rxzjsj(TimeFormat(cursor.getString(COLUMN_INDEX_rxzjsj)));     
		 item.setM_fldzw( cursor.getString(COLUMN_INDEX_fldzw));      
		 item.setM_rfldzwsj(cursor.getString(COLUMN_INDEX_rfldzwsj)); 
		 item.setM_hbgbzj(cursor.getString(COLUMN_INDEX_hbgbzj));     
		 item.setM_hbgbsj(cursor.getString(COLUMN_INDEX_hbgbsj));     
		 item.setM_rdsj(TimeFormat(cursor.getString(COLUMN_INDEX_rdsj)));         
		 item.setM_cjgzsj(TimeFormat(cursor.getString(COLUMN_INDEX_cjgzsj)));     
		 item.setM_jkzk(mDict.GetMcbylbandbm("jkzk", cursor.getString(COLUMN_INDEX_jkzk)));         
		 item.setM_zyjszw(cursor.getString(COLUMN_INDEX_zyjszw));     
		 item.setM_zytc(cursor.getString(COLUMN_INDEX_zytc));         
		 
		 item.setM_q_xl(mDict.GetMcbylbandbm("xl", cursor.getString(COLUMN_INDEX_q_xl)));         
		 item.setM_q_xw(mDict.GetMcbylbandbm("xw", cursor.getString(COLUMN_INDEX_q_xw)));         
		 item.setM_q_yx(cursor.getString(COLUMN_INDEX_q_yx));         
		 item.setM_q_zy(cursor.getString(COLUMN_INDEX_q_zy));         
		 item.setM_z_xl(mDict.GetMcbylbandbm("xl", cursor.getString(COLUMN_INDEX_z_xl)));         
		 item.setM_z_xw(mDict.GetMcbylbandbm("xw", cursor.getString(COLUMN_INDEX_z_xw)));         
		 item.setM_z_yx(cursor.getString(COLUMN_INDEX_z_yx));         
		 item.setM_z_zy(cursor.getString(COLUMN_INDEX_z_zy));         
		 item.setM_zgxl(cursor.getString(COLUMN_INDEX_zgxl));         
		 item.setM_jtzz(cursor.getString(COLUMN_INDEX_jtzz));         
		 item.setM_zfmj(cursor.getString(COLUMN_INDEX_zfmj));         
		 item.setM_jtnsr( cursor.getString(COLUMN_INDEX_jtnsr));      
		 item.setM_jjst(cursor.getString(COLUMN_INDEX_jjst));     
		 
		 item.setM_jjstmcdd(cursor.getString(COLUMN_INDEX_jjstmcdd)); 
		 item.setM_fcgsmj(cursor.getString(COLUMN_INDEX_fcgsmj));     
		 item.setM_zytdyj(cursor.getString(COLUMN_INDEX_zytdyj));  
		 item.setM_tc(cursor.getString(COLUMN_INDEX_tc));
		 item.setM_xg(cursor.getString(COLUMN_INDEX_xg));             
		 item.setM_ah(cursor.getString(COLUMN_INDEX_ah));             
		 item.setM_bz(cursor.getString(COLUMN_INDEX_bz));             
		 item.setM_dh_b(cursor.getString(COLUMN_INDEX_dh_b));         
		 item.setM_dh_z(cursor.getString(COLUMN_INDEX_dh_z));         
		 item.setM_dh_s(cursor.getString(COLUMN_INDEX_dh_s));         
		 item.setM_dh_q(cursor.getString(COLUMN_INDEX_dh_q));         
		 item.setM_beizhu(cursor.getString(COLUMN_INDEX_beizhu));     
		 
		 item.setM_zp(cursor.getString(COLUMN_INDEX_zp));             
		 
		 item.setM_py_1(cursor.getString(COLUMN_INDEX_py_1));        
		 item.setM_py_2(cursor.getString(COLUMN_INDEX_py_2));    
		 
		 item.setM_zgzt(mDict.GetMcbylbandbm("zgzt", cursor.getString(COLUMN_INDEX_zgzt)));  
		 
		 item.setM_rylb(cursor.getString(COLUMN_INDEX_rylb));
		 
		 item.setM_jlxx(cursor.getString(COLUMN_INDEX_jlxx));
		 
		 item.setM_gwy(cursor.getString(COLUMN_INDEX_gwy));
		 
		 item.setM_zc_1(cursor.getString(COLUMN_INDEX_zc_1));  
		 
		 return item ;
    }
    
	public List<UserItem> GetUserByNation(String nation){
		
		List<UserItem>  list = new ArrayList<UserItem>();
		
        
		String  selection = ConstProfile.TUser.COLUMN_mz + "=?";
		
		String nations = new String();
		
		if(nation.indexOf("汉族") != -1){
			nations = "01" ;
		}else if(nation.indexOf("壮族") != -1){
			nations = "02" ;
		}else if(nation.indexOf("回族") != -1){
			nations = "03" ;
		}else if(nation.indexOf("满族") != -1){
			nations = "04" ;
		}else if(nation.indexOf("朝鲜") != -1){
			nations = "14" ;
		}else if(nation.indexOf("其他") != -1){
			list = GetOtherNations();
			return list ;
		}else{
			nations = nation ;
		}
		
		
		
		String[] selectionArgs = new  String[]{ nations }; 
		
		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				 
				UserItem item = GetUserItemByCursor(cursor);
				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		    Log.d("yulei exception", " GetUserByNation :"+nation) ;
		}
		
		return list ;
	}
	
  public List<UserItem> GetUserByEdu(String edu){
		
		List<UserItem>  list = new ArrayList<UserItem>();
		
		
		String  selection =  null ;
		String[] selectionArgs = null ;
		
	  if(edu == null){
		  
		  
	  }else if(edu.equals("高中")){		
			edu = null ;
		}else if(edu.equals("中专")){	
			edu= "05" ;
		}else if(edu.equals("专科")){
			edu= "04" ;
		}else if(edu.equals("本科")){
			edu= "03" ;
		}else if(edu.equals("硕士")){
			edu= "02" ;
		}else if(edu.equals("博士")){
			edu= "01" ;
		}
		  
        if(edu != null){
        	selection = ConstProfile.TUser.COLUMN_q_xl + " like ?";
        	selectionArgs = new  String[]{edu +"%"}; 
        }else{
        	selection = ConstProfile.TUser.COLUMN_q_xl + " is null ";
        }
		
		
		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				UserItem item = GetUserItemByCursor(cursor);

				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		    Log.d("yulei debug", "GetUserByEdu:"+edu);
		    
		}
		
		return list ;
	}
  
  /*
   *   0: <35   1:35-40 ,2:40-50 ,3:50-55 ,4 >55
   */
  public List<UserItem> GetUserByAge(int  type){
		
		List<UserItem>  list = new ArrayList<UserItem>();
		
    
		Date date=new Date();
    	SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
    	
    	String currentday = df.format(date) ;
    	
    	
    	
    	int year = Integer.valueOf(currentday.substring(0, 4));
    	int month = Integer.valueOf(currentday.substring(4, 6));
    	int day = Integer.valueOf(currentday.substring(6));
    	
    	
    	String  age35birthdate = String.format("%d-%d-%d 00:00:00", (year-35),month,day);
    	
    	String  age40birthdate =  String.format("%d-%d-%d 00:00:00", (year-40),month,day);
    	
    	String  age50birthdate =  String.format("%d-%d-%d 00:00:00", (year-50),month,day);
    	
    	String  age55birthdate =  String.format("%d-%d-%d 00:00:00", (year-55),month,day);
    	
    	
    	String  selection  = null ;		
		String[] selectionArgs = null ; 

	
    	switch(type){
    	case AgeRange.BELOW_35:
    		selection = ConstProfile.TUser.COLUMN_csrq + ">=? or "+ConstProfile.TUser.COLUMN_csrq +" is null";
    		selectionArgs = new  String[]{ age35birthdate }; 
    		break;
    	case AgeRange.BELOW_40:
    		selection = ConstProfile.TUser.COLUMN_csrq + ">=? and " +ConstProfile.TUser.COLUMN_csrq + "<?";
    		selectionArgs = new  String[]{ age40birthdate ,age35birthdate }; 
    		break;
    	case AgeRange.BELOW_50:
    		selection = ConstProfile.TUser.COLUMN_csrq + ">=? and " +ConstProfile.TUser.COLUMN_csrq + "<?";
    		selectionArgs = new  String[]{ age50birthdate ,age40birthdate }; 
    		break;
    	case AgeRange.BELOW_55:
    		selection = ConstProfile.TUser.COLUMN_csrq + ">=? and " +ConstProfile.TUser.COLUMN_csrq + "<?";
    		selectionArgs = new  String[]{ age55birthdate ,age50birthdate }; 
    		break;
    	case AgeRange.ABOVE_55:
    		selection = ConstProfile.TUser.COLUMN_csrq + "<?";
    		selectionArgs = new  String[]{ age55birthdate }; 
    		break;
    		default:
    			break;
    	}

		
		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			//Log.d("GetUserByAge", selection+" count:"+cursor.getCount()+" 35:"+age35birthdate+" 50:"+age55birthdate) ;
			
			while(cursor.isAfterLast() == false){
				
				UserItem item = GetUserItemByCursor(cursor);
				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		    Log.d("yulei debug", "GetUserByAge:"+type);
		}
		
		return list ;
	}
  
  public  int  GetAgeType(UserItem  item){
	  int type = AgeRange.BELOW_35 ;
	  
	  String birthdate = item.getM_csrq();
	  if(birthdate.length() <= 6){
		  return type ;
	  }
	  
	  Date date=new Date();
	  SimpleDateFormat df=new SimpleDateFormat("yyyyMM");

	  
	  int index  = birthdate.indexOf(" ");
	  if(index != -1){
	  		birthdate = birthdate.substring(0, index);
	  		
	   }
	  
	  
	  birthdate =birthdate.replace("-", "");
	  
	  int age =  (Integer.valueOf(df.format(date)) -Integer.valueOf(birthdate));
		
	  if(age < 3500){
		  type = AgeRange.BELOW_35 ;
	  }else if(age < 4000){
		  type = AgeRange.BELOW_40;
	  }else if(age < 5000){
		  type = AgeRange.BELOW_50;
	  }else if(age < 5500){
		  type = AgeRange.BELOW_55;
	  }else {
		  type = AgeRange.ABOVE_55 ;
	  }
			  
	  
	  
	  return type ;
  }
  
  public List<UserItem> GetUserByAgeandGroupId(int  type ,String group_id){
		
		List<UserItem>  list = new ArrayList<UserItem>();
		
		
		List<String> sfzs =   GetSfzsByGroupID(group_id);
		int len = sfzs.size() ;
		
		Log.d("yulei count1","GetUserByAgeandGroupId:"+sfzs.size());
		
		//HashMap<String,UserItem>  map = new HashMap<String,UserItem>();
		
		if(len > 0){
			
			String  selectionsfz = ConstProfile.TUser.COLUMN_sfz_no + " in ("+makePlaceholders(sfzs.size())+")";
			
			String[] strselecttion2 = new String[len]; 
			
			int i = 0 ;
			for(i = 0 ; i < sfzs.size() ; i++){
				String  str = new String(sfzs.get(i));
				
				strselecttion2[i] = str ;
			}
			
            try{
            	
            	
            	
    			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selectionsfz, strselecttion2, null, null, null);
    			
    			cursor.moveToFirst() ;
    			
    			Log.d("yulei count1","type:"+type+"count:"+cursor.getCount());
    			while(cursor.isAfterLast() == false){
    				UserItem item = GetUserItemByCursor(cursor);	
    				
    				
    				if(type == GetAgeType(item)){
    					list.add(item);
    				}		
    				cursor.moveToNext();
    			}
    			
    			
    			
    			cursor.close();
    		
    		}catch(Exception e){
    		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
    		    Log.d("yulei exception", " GetUserByAgeandGroupId type:"+type+" group_id:"+group_id) ;
    		}
            
			
            
		}
            
       
		
		return list ;
	}
  
  //副地及以上
   public List<UserItem>  GetUserbyotherdepth(){
	   List<UserItem>  list = new ArrayList<UserItem>();
	   
	   try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, ConstProfile.TUser.COLUMN_xrzj+"<=12", null, null, null, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				UserItem item = GetUserItemByCursor(cursor);

				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		    
		}
		
	   
	   return list ;
   }
  
  /*
   * 
   * 
   * 现任职务
   */
    public List<UserItem> GetUserByDepth(String xrzw){
		
		List<UserItem>  list = new ArrayList<UserItem>();
		
      
		String  selection = null;
		
		String[] selectionArgs = null ;
		
		if(xrzw.indexOf("科员") != -1){
			selection = ConstProfile.TUser.COLUMN_xrzj + " like ?";
			xrzw = "21" ;
		    selectionArgs = new  String[]{"%"+xrzw+"%"}; 
    	}else if(xrzw.indexOf("副科") !=-1){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? or "+ConstProfile.TUser.COLUMN_xrzj +" like ?";
    		selectionArgs = new  String[]{"19%","20%"};
    	}else if(xrzw.indexOf("正科") !=-1){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? or "+ConstProfile.TUser.COLUMN_xrzj +" like ?";
    		selectionArgs = new  String[]{"17%","18%"};
    		
    	}/*else if(xrzw.indexOf("副县") !=-1){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? or "+ConstProfile.TUser.COLUMN_xrzj +" like ?";
    		selectionArgs = new  String[]{"15%","16%"};
    		
    	}else if(xrzw.indexOf("正县") !=-1){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? or "+ConstProfile.TUser.COLUMN_xrzj +" like ?";
    		selectionArgs = new  String[]{"13%","14%"};
    		
    	}*/else if(xrzw.equals("副县") == true){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? ";
    		selectionArgs = new  String[]{"15%"};
    		
    	}else if(xrzw.equals("副县级") == true){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? ";
    		selectionArgs = new  String[]{"16%"};
    		
    	}else if(xrzw.equals("正县") == true){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? ";
    		selectionArgs = new  String[]{"13%"};
    		
    	}else if(xrzw.equals("正县级") == true){
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ? ";
    		selectionArgs = new  String[]{"14%"};
    		
    	}else if(xrzw.equals("副地及以上") == true){
    		list = GetUserbyotherdepth();
    		return list ;
    	}else {
    		selection = ConstProfile.TUser.COLUMN_xrzj + " like ?";
		    selectionArgs = new  String[]{xrzw+"%"}; 
    	}
		
		 
		
		try{
			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				UserItem item = GetUserItemByCursor(cursor);

				list.add(item);
				cursor.moveToNext();
			}
			
			cursor.close();
		
		}catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		    Log.d("yulei exception", " GetUserByDepth xrzw:"+xrzw) ;
		}
		
		return list ;
	}
    
    /*
     * 
     * 
     * 现任职务
     */
      public List<UserItem> GetUserByStatus(String gwzt){
  		
  		List<UserItem>  list = new ArrayList<UserItem>();
  		String zgztarg = null ;
  		
  		if(gwzt != null){
  			zgztarg = mDict.GetBmbylbandmc("zgzt", gwzt) ;
  		}
  		
  		if(zgztarg == null){
  			zgztarg = gwzt ;
  		}else if(zgztarg.length() == 0){
  			zgztarg = gwzt ;
  		}
        
  		String  selection = ConstProfile.TUser.COLUMN_zgzt + " like ?";
  		
  		String[] selectionArgs = new  String[]{"%"+zgztarg+"%"}; 
  		
  		try{
  			Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, selectionArgs, null, null, null);
  			
  			cursor.moveToFirst() ;
  			
  			while(cursor.isAfterLast() == false){
  				UserItem item = GetUserItemByCursor(cursor);

  				list.add(item);
  				cursor.moveToNext();
  			}
  			
  			cursor.close();
  		
  		}catch(Exception e){
  		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
  		    Log.d("yulei exception", " GetUserByStatus gwzt:"+gwzt) ;
  		}
  		
  		return list ;
  	}
    
public List<UserItem> GetUserByNationandGroupId(String nation , String group_id){
		
	 
	
		List<UserItem>  list  = null ;
				
		if(nation.indexOf("汉族") != -1){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_mz,"01");
		}else if(nation.indexOf("壮族") != -1){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_mz,"02");
		}else if(nation.indexOf("回族") != -1){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_mz,"03");
		}else if(nation.indexOf("满族") != -1){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_mz,"04");
		}else if(nation.indexOf("朝鲜") != -1){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_mz,"14");
		}else if(nation.indexOf("其他") != -1){
			list = GetOtherNations();
		}else{
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_mz,nation);
		}
				
		
		return list ;
	}
	
  public List<UserItem>  GetOtherNations(){
	  List<UserItem>  list  = new ArrayList<UserItem>() ;
	  
	  String selection = "mz  is null  or mz not in ('01','02','03','04','14')" ;
	  try{
		  Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selection, null, null, null, null);
			cursor.moveToFirst() ;
			 
			while(cursor.isAfterLast() == false){
				
				UserItem item = GetUserItemByCursor(cursor);
				
				
				 cursor.moveToNext();
				 
				 list.add(item);
			 
			}

			cursor.close();
	  }catch(Exception e){
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+"not exist,please check");
		   
		}
	 
	  
	  return list; 
  }

  public List<UserItem> GetUserByEduandGroupId(String edu,String group_id){
		
	  List<UserItem>  list  = null ;
	  
	  if(edu == null){
		  list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,null);
		  
	  }else if(edu.equals("高中")){		
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,null);
		}else if(edu.equals("中专")){
			
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,"05");
		}else if(edu.equals("专科")){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,"04");
		}else if(edu.equals("本科")){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,"03");
		}else if(edu.equals("硕士")){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,"02");
		}else if(edu.equals("博士")){
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,"01");
		}else {
			list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_q_xl,edu);
		}
	  
		
       
		return list ;
	}
  
    public List<UserItem> GetUserByDepthandGroupId(String depth,String group_id){
		
    	List<UserItem>  list = null ; 
    	List<UserItem>  list2 = null ; 
    	if(depth.indexOf("科员") != -1){
    		list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"21");
    		
    	}else if(depth.indexOf("副科") !=-1){
    		list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"19");
    		
    		list2 = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"20");
    		
    		list.addAll(list2);
    		
    	}else if(depth.indexOf("正科") !=-1){
    		list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"17");
    		
    		list2 = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"18");
    		
    		list.addAll(list2);
    	}else if(depth.indexOf("副县") !=-1){
    		list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"15");
    		
    		list2 = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"16");
    		
    		list.addAll(list2);
    	}else if(depth.indexOf("正县") !=-1){
    		list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"13");
    		
    		list2 = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,"14");
    		
    		list.addAll(list2);
    	}else {
    		list = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_xrzj,depth);
    	}
    	

		
		return list ;
	}
    
    public List<UserItem> GetUserByStatusandGroupId(String status,String group_id){
		
		List<UserItem>  list  = GetUserByGroupInfo(group_id,ConstProfile.TUser.COLUMN_zgzt,status);
		
       
		
		return list ;
	}
    
    
	
	public void Close() {

		
        db.close();
        dbhelper.close();
    }
	
	
	
	public List<String> GetAllNationList(){
		
		List<String>  list = new ArrayList<String>();
		
		try{
			Cursor cursor = db.query(ConstProfile.TNation.TABLE_NAME, null, null, null, null, null, null);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				String text = new String();
				text = cursor.getString(1) ;
				list.add(text);
				cursor.moveToNext();
			}			
			cursor.close();

		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TNation.TABLE_NAME+"not exist,please check");
		}
    
		return list ;
	}
	
    public List<String> GetAllEduList(){
		
		List<String>  list = new ArrayList<String>();
		
		try{
			Cursor cursor = db.query(ConstProfile.TEdu.TABLE_NAME, null, null, null, null, null, null);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				String text = new String();
				text = cursor.getString(1) ;
				list.add(text);
				cursor.moveToNext();
			}			
			cursor.close();
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TEdu.TABLE_NAME+"not exist,please check");
		}
    
		return list ;
	}
    
    public List<String> GetAllDepthList(){
		
		List<String>  list = new ArrayList<String>();
		
		try{
			Cursor cursor = db.query(ConstProfile.TDepth.TABLE_NAME, null, null, null, null, null, null);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				String text = new String();
				text = cursor.getString(1) ;
				list.add(text);
				cursor.moveToNext();
			}			
			cursor.close();
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TDepth.TABLE_NAME+"not exist,please check");
		}
    
		return list ;
	}
    
    
    public List<GroupItem> GetAllGroupList(){
		
		List<GroupItem>  list = new ArrayList<GroupItem>();
		
		try{
			Cursor cursor = db.query(ConstProfile.TGroup.TABLE_NAME, null, null, null, null, null, null);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				GroupItem  item = new GroupItem();;
				item.setGroup_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__ID));
				item.setGroup_name(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__NAME));
				item.setParent_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__PARENTID));
				
				list.add(item);
				cursor.moveToNext();
			}			
			cursor.close();
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TGroup.TABLE_NAME+"not exist,please check");
		}
    
		return list ;
	}
    
    
    public GroupItem GetGroupByGroupID(String group_id){
		
		
		
		GroupItem  item = null;
		
		String  selection = ConstProfile.TGroup.COLUMN_ID + "=?";
		
		String[] selectionArgs = new  String[]{ group_id }; 
		
		
		try{
			
			
			Cursor cursor = db.query(ConstProfile.TGroup.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			

			if(cursor.moveToFirst() == true ){
				
				item = new GroupItem() ;
				item.setGroup_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__ID));
				item.setGroup_name(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__NAME));
				item.setParent_id(cursor.getString(ConstProfile.TGroup.COLUMN_INDEX__PARENTID));
				
				//Log.d("groupt_name", "name:"+item.getGroup_name()+"id:"+item.getGroup_id());
			}
					
			cursor.close();
		}
		catch(Exception e){
		    T.showShort(mContext, ConstProfile.TGroup.TABLE_NAME+"not exist,please check");
		}
    
		return item ;
	}
    
    
    public List<FamilyItem>  GetFamilyListByUserID(String  sfz_no){
    	List<FamilyItem> list = new ArrayList<FamilyItem>();
    	
    	String  selection = ConstProfile.TFamily.COLUMN_sfz_no + "=?";
		
		String[] selectionArgs = new  String[]{ sfz_no }; 
		
		
    	
    	try{
    		
    		Cursor cursor = db.query(ConstProfile.TFamily.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			

    		
    		
    		
    		
			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				FamilyItem  item = new FamilyItem();
				
				item.setM_sfz_no(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_sfz_no));   
				item.setM_xh(cursor.getInt(ConstProfile.TFamily.COLUMN_INDEX_xh));              
				item.setM_cw(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_cw));           
				item.setM_xm(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_xm));           
				item.setM_mz(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_mz));           
				item.setM_csrq(TimeFormat(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_csrq)));       
				item.setM_zzmm(mDict.GetMcbylbandbm("zzmm", cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_zzmm)));       
				item.setM_gzdw(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_gzdw));       
				item.setM_zw(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_zw));           
				item.setM_bz(cursor.getString(ConstProfile.TFamily.COLUMN_INDEX_bz));           
				
				list.add(item);
				cursor.moveToNext();
			}			
			cursor.close();
    		
    		
    	}catch(Exception e){
    		T.showShort(mContext, ConstProfile.TFamily.TABLE_NAME+"not exist,please check");
    	}
    	
    	return list ;
    }
    
    
    public List<ResumeItem>  GetResumeListByUserID(String xfz_no){
    	List<ResumeItem> list = new ArrayList<ResumeItem>();
    	
    	
    	String  selection = ConstProfile.TRESUME.COLUMN__sfz_no + "=?";
		
		String[] selectionArgs = new  String[]{ xfz_no }; 
    	
    	try{
    		
    		
    		
    		Cursor cursor = db.query(ConstProfile.TRESUME.TABLE_NAME, null, selection, selectionArgs, null, null, null);
			

			cursor.moveToFirst() ;
			
			while(cursor.isAfterLast() == false){
				ResumeItem  item = new ResumeItem();
				
				item.setSfz_no(cursor.getString(ConstProfile.TRESUME.COLUMN_INDEX__sfz_no));
				item.setXh(cursor.getInt(ConstProfile.TRESUME.COLUMN_INDEX__xh));
				item.setStart_time(cursor.getString(ConstProfile.TRESUME.COLUMN_INDEX__STARTTIME));
				item.setOver_time(cursor.getString(ConstProfile.TRESUME.COLUMN_INDEX__OVERTIME));
				item.setJob_desc(cursor.getString(ConstProfile.TRESUME.COLUMN_INDEX__JOBDESC));
				item.setZwsf(cursor.getString(ConstProfile.TRESUME.COLUMN_INDEX__zwsf));
				
				list.add(item);
				cursor.moveToNext();
			}			
			cursor.close();
    		
    		
    	}catch(Exception e){
    		T.showShort(mContext, ConstProfile.TRESUME.TABLE_NAME+"not exist,please check");
    	}
    	
    	return list ;
    }
    
    
    private List<String>  GetSfzsByGroupID(String group_id){
    	
    	List<String> sfzs = new ArrayList<String>();
    	
        String  selection = ConstProfile.TZwxx.COLUMN_dwbm + " like  ?";
		
		String[] selectionArgs = new  String[]{ String.valueOf(group_id)+"%" };
		
		//HashMap<String,UserItem>  map = new HashMap<String,UserItem>();
		
		
		try{
			Cursor cursor = db.query(ConstProfile.TZwxx.TABLE_NAME, null, selection, selectionArgs, ConstProfile.TZwxx.COLUMN_sfz_no, null, " dwbm,wzh");
			
			cursor.moveToFirst() ;
			
			
			int i = 0 ;
			
			while(cursor.isAfterLast() == false){
				
				String sfz_no = new String();
						
				sfz_no = cursor.getString(ConstProfile.TZwxx.COLUMN_INDEX_sfz_no) ;
					
				cursor.moveToNext();
				
				//Log.d("GetSfzsByGroupID", "sfz_no : "+sfz_no);
				
				sfzs.add(sfz_no)  ;
				i++ ;
			}
			
			cursor.close();
		
		}catch(Exception e){
		    T.showLong(mContext, ConstProfile.TZwxx.TABLE_NAME+"not exist,please check");
		    
		}
		
    	return sfzs ;
    	
    }
    
    
    String makePlaceholders(int len) {
        if (len < 1) {
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }
    
    public List<UserItem> GetUserByGroupID(String  group_id ){
    	
    	return GetUserByGroupInfo(group_id,null,null) ;
    }
    
    
    private  List<UserItem> GetUserByGroupInfo(String  group_id , String selection, String arg){
		List<UserItem>  list = new ArrayList<UserItem>();	
		
		 Log.d("Contactsmanager", "GetUserByGroupInfo selection: "+selection+" arg:"+arg);
		 
		 
		 HashMap<String , UserItem> map = new HashMap<String , UserItem>();
		 
		 
		try{
			
			List<String> sfzs =   GetSfzsByGroupID(group_id);
			int len = sfzs.size() ;
			
			if(len > 0){
				
				String  selectionsfz = ConstProfile.TUser.COLUMN_sfz_no + " in ("+makePlaceholders(sfzs.size())+")";
				
				if(arg != null){
					len = len +1 ;
				}
				
				String[] strselecttion2 = new String[len];  
				
				if(ConstProfile.TUser.COLUMN_q_xl.equals(selection) == false){
					if(selection != null){
						selectionsfz = selectionsfz +" and " + selection +" like ?";
						
					}
					int i = 0 ;
					for(i = 0 ; i < sfzs.size() ; i++){
						String  str = new String(sfzs.get(i));
						
						strselecttion2[i] = str ;
					}
					
					if(arg != null){
	                	String str2 = new String(arg);
	                	strselecttion2[i] = "%"+str2+"%" ;
	                }
					
				}else {
					
					
					if(arg == null){ 
						if(selection != null){
							selectionsfz = selectionsfz +" and " + selection +" is null ";
							
						}
						int i = 0 ;
						for(i = 0 ; i < sfzs.size() ; i++){
							String  str = new String(sfzs.get(i));
							
							strselecttion2[i] = str ;
						}
						
					}else{
						
						if(selection != null){
							selectionsfz = selectionsfz +" and " + selection +" like ?";
							
						}
						int i = 0 ;
						for(i = 0 ; i < sfzs.size() ; i++){
							String  str = new String(sfzs.get(i));
							
							strselecttion2[i] = str ;
						}
						
						if(arg != null){
		                	String str2 = new String(arg);
		                	strselecttion2[i] = str2+"%" ;
		                }
					}
					
				}
				

               
				Cursor cursor = db.query(ConstProfile.TUser.TABLE_NAME, null, selectionsfz, strselecttion2, null, null, null);
				cursor.moveToFirst() ;
				 
				while(cursor.isAfterLast() == false){
					
					UserItem item = GetUserItemByCursor(cursor);
					
					
					 cursor.moveToNext();
					 
					 //list.add(item);
					 map.put(item.getM_sfz_no(), item);
					 
				}
				
				
				cursor.close();
				
				for(int i = 0 ;  i< sfzs.size() ;i++){
					
					if(map.containsKey(sfzs.get(i)) == true){
						list.add(map.get(sfzs.get(i)));
					}
					
				}
				
				 Log.d("GetUserByGroupInfo", "list size : "+list.size());
			}

		}catch(Exception e){
			 Log.d("Contactsmanager", "GetUserByGroupInfo Exception: "+e.getMessage());
		    T.showShort(mContext, ConstProfile.TUser.TABLE_NAME+" Exception GetUserByGroupInfo:"+e.getMessage());
		    
		}
		
		return list ;
	}
}
