package com.chinamobile.workerspace.db;

import java.util.ArrayList;

import android.net.Uri;

public class ConstProfile {
	    
	    public static final String DB_NAME           = "workers.db";
	    public static final String AUTOHORITY        = "com.chinamobile.workerspace.provider";
	    
	    public static final  int   DATABASE_VERSION  = 1;  
	    
	    
	    
	    public static class TUser{
	    	public static final String TABLE_NAME        				= "gb_jbxx_table";        
		    public static final String COLUMN_ID         				= "_id"; 
		    public static final String DEFAULT_SORT_ORDER 				= "_id ASC"; 
		    
		    public static final String COLUMN_sfz_no              =  "sfz_no" ;    
		    public static final String COLUMN_xm            	   =  "xm" ;       
		    public static final String COLUMN_xb                  =  "xb" ;        
		    public static final String COLUMN_csrq                =  "csrq" ;      
		    public static final String COLUMN_mz                  =  "mz" ;        
		    public static final String COLUMN_jg                  =  "jg" ;        
		    public static final String COLUMN_csd                 =  "csd" ; 
		    public static final String COLUMN_czd                 =  "czd" ; 
		    public static final String COLUMN_zzmm                =  "zzmm" ;      
		    public static final String COLUMN_sflb                =  "sflb" ;      
		    public static final String COLUMN_xrzw                =  "xrzw" ;      
		    public static final String COLUMN_rxzsj               =  "rxzsj" ;     
		    public static final String COLUMN_xrzj                =  "xrzj" ;      
		    public static final String COLUMN_rxzjsj              =  "rxzjsj" ;    
		    public static final String COLUMN_fldzw               =  "fldzw" ;     
		    public static final String COLUMN_rfldzwsj            =  "rfldzwsj" ;  
		    public static final String COLUMN_hbgbzj              =  "hbgbzj" ;    
		    public static final String COLUMN_hbgbsj              =  "hbgbsj" ;    
		    public static final String COLUMN_rdsj                =  "rdsj" ;      
		    public static final String COLUMN_cjgzsj              =  "cjgzsj" ;    
		    public static final String COLUMN_jkzk                =  "jkzk" ;      
		    public static final String COLUMN_zyjszw              =  "zyjszw" ;    
		    public static final String COLUMN_zytc                =  "zytc" ;      
		    public static final String COLUMN_q_xl                =  "q_xl" ;      
		    public static final String COLUMN_q_xw                =  "q_xw" ;      
		    public static final String COLUMN_q_yx                =  "q_yx" ;      
		    public static final String COLUMN_q_zy                =  "q_zy" ;      
		    public static final String COLUMN_z_xl                =  "z_xl" ;      
		    public static final String COLUMN_z_xw                =  "z_xw" ;      
		    public static final String COLUMN_z_yx                =  "z_yx" ;      
		    public static final String COLUMN_z_zy                =  "z_zy" ;      
		    public static final String COLUMN_zgxl                =  "zgxl" ;      
		    public static final String COLUMN_jtzz                =  "jtzz" ;      
		    public static final String COLUMN_zfmj                =  "zfmj" ;      
		    public static final String COLUMN_jtnsr               =  "jtnsr" ;     
		    public static final String COLUMN_jjst                =  "jjst" ;      
		    public static final String COLUMN_jjstmcdd            =  "jjstmcdd" ;  
		    public static final String COLUMN_fcgsmj              =  "fcgsmj" ;    
		    public static final String COLUMN_zytdyj              =  "zytdyj" ;   
		    public static final String COLUMN_tc                  =  "tc" ;   
		    public static final String COLUMN_xg                  =  "xg" ;        
		    public static final String COLUMN_ah                  =  "ah" ;        
		    public static final String COLUMN_bz                  =  "bz" ;        
		    public static final String COLUMN_dh_b                =  "dh_b" ;      
		    public static final String COLUMN_dh_z                =  "dh_z" ;      
		    public static final String COLUMN_dh_s                =  "dh_s" ;      
		    public static final String COLUMN_dh_q                =  "dh_q" ;      
		    public static final String COLUMN_beizhu              =  "beizhu" ;    
		    public static final String COLUMN_zp                  =  "zp" ;        
		    public static final String COLUMN_py_1                =  "py_1" ;      
		    public static final String COLUMN_py_2                =  "py_2" ;      
		    public static final String COLUMN_zgzt                =  "zgzt" ;      
		    public static final String COLUMN_zc_1                =  "zc_1" ;      
 

		    
		    
	    }
	    
	    public static class TStatus{
	    	public static final String TABLE_NAME        = "t_status";        

		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 
		    
		    public static final String COLUMN_TITLE       = "status_text"; 		   
	    }
	    /*
	    public static class TZwxx{
	    	
	    	public static final String COLUMN_sfz_no  =   "sfz_no";    
	    	public static final String COLUMN_xh      =   "xh";        
	    	public static final String COLUMN_dwbm    =   "dwbm";      
	    	public static final String COLUMN_zwmc    =   "zwmc";      
	    	public static final String COLUMN_rzsj    =   "rzsj";      
	    	public static final String COLUMN_xs      =   "xs";        
	    	public static final String COLUMN_js      =   "js";        
	    	public static final String COLUMN_sc      =   "sc";        
	    	public static final String COLUMN_bz      =   "bz";        

	    	
	    	
	    	public static final int COLUMN_INDEX_sfz_no  =  0;
	    	public static final int COLUMN_INDEX_xh      =  1;
	    	public static final int COLUMN_INDEX_dwbm    =  2;
	    	public static final int COLUMN_INDEX_zwmc    =  3;
	    	public static final int COLUMN_INDEX_rzsj    =  4;
	    	public static final int COLUMN_INDEX_xs      =  5;
	    	public static final int COLUMN_INDEX_js      =  6;
	    	public static final int COLUMN_INDEX_sc      =  7;
	    	public static final int COLUMN_INDEX_bz      =  8;

	    }*/
	    
	    public static class TRESUME{
	    	public static final String TABLE_NAME        = "gb_jlxx_table";        

		    /*public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; */
		    
		    public static final String COLUMN__sfz_no       = "sfz_no"; 
		    public static final String COLUMN_xh              = "xh"; 
		    
		    public static final String COLUMN_STARTTIME       = "start_time";  
		    public static final String COLUMN_OVERTIME         = "over_time";  		    
		    public static final String COLUMN_JOBDESC         = "job_desc";  // request on road , approved?		   
		    
		    public static final String COLUMN_zwsf           = "zwsf"; 
		    
		    
		    public static final int COLUMN_INDEX__sfz_no        = 0 ;   
		    public static final int COLUMN_INDEX__xh    =1 ;
		    public static final int COLUMN_INDEX__STARTTIME    =2; 
		    public static final int COLUMN_INDEX__OVERTIME  =3; 
		    public static final int COLUMN_INDEX__JOBDESC    =4; 
		    public static final int COLUMN_INDEX__zwsf    =5; 
		    
		    
		    public static final int    CONTACTSREQUEST              = 1;
		    public static final int    CONTACTREQUEST_ID           = 2;

		    
		    public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/contactsrequest";
		    
		    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contactsrequest";

		    public static final Uri    CONTENT_URI       = Uri.parse("content://" + AUTOHORITY + "/"+TABLE_NAME);
	    }
	    
	    
	    
	    public static class TPosition {
	    	public static final String TABLE_NAME        = "t_position";        

		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 
		    
		    public static final String COLUMN_POSITIONTEXT       = "position_text"; 		    
		    
		  
	    }
	    
	    public static class TPOLITICSSTATUS{
	    	public static final String TABLE_NAME        = "t_politics_status";        

		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 		    
		    public static final String COLUMN_POLITICS       = "politics_status_text"; 		 		    
	    }
	    
	    
	    public static class TNation{
	    	public static final String TABLE_NAME        = "t_nation";        
		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 		   
		    public static final String COLUMN_NATIONTEXT       = "nation_text"; 
		  
		    
	    }
	    

	    public static class TJobtitle{
	    	public static final String TABLE_NAME        = "t_job_title";        
		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 		    
		    public static final String COLUMN_JOBTEXT       = "job_text"; 		   		    		    
	    }
	    
	    
	    public static class THistory{
	    	public static final String TABLE_NAME        = "t_history";        
		    public static final String COLUMN_ID         = "history_id"; 
		    public static final String DEFAULT_SORT_ORDER = "history_id desc"; 		   
		    public static final String COLUMN_ITEMTITLE       = "item_title"; 
		    public static final String COLUMN_ITEMID       = "item_id"; 
		    public static final String COLUMN_ITEMTYPE       = "item_type"; 
		    public static final String COLUMN_ADDTIME       = "add_time"; 	
		    
		    public static final int COLUMN_INDEX__ID        	= 0 ;   
		    public static final int COLUMN_INDEX__TITLE  		=1 ;
		    public static final int COLUMN_INDEX__ITEMID    	=2; 
		    public static final int COLUMN_INDEX__ITEMTYPE  	=3; 
		    public static final int COLUMN_INDEX__ADDTIME   	=4; 
		    
	    }
	    

	    public static class TGroup{
	    	public static final String TABLE_NAME        = "gb_dwxx_table";        
		    public static final String COLUMN_ID         = "dwbm";    //0
		    public static final String DEFAULT_SORT_ORDER = "dwbm ASC"; 		   
		    public static final String COLUMN_NAME       = "dwmc"; 	  //1
		    public static final String COLUMN_wzmc       = "wzmc"; 	  //2
		    public static final String COLUMN_wzh       = "wzh"; 	
		    public static final String COLUMN_PARENTID      = "sjbm"; 	
		    public static final String COLUMN_dwlb      = "dwlb"; 
		    public static final String COLUMN_dy      = "dy"; 	
		    public static final String COLUMN_bz      = "bz"; 
		    
		    
		    public static final int COLUMN_INDEX__ID          =0 ;   
		    public static final int COLUMN_INDEX__NAME        =1 ;
		    public static final int COLUMN_INDEX__wzmc        =2 ;
		    public static final int COLUMN_INDEX__PARENTID    =3; 
		    public static final int COLUMN_INDEX__wzh         =4 ;
		    public static final int COLUMN_INDEX__dwlb        =5; 
		    public static final int COLUMN_INDEX__dy          =6; 
		    public static final int COLUMN_INDEX__bz          =7; 

	    }
	    
	    //职务信息
	    public static class TZwxx{
	    	
	    	public static final String TABLE_NAME        = "gb_zwxx_table"; 
	    	
	    	 public static final String COLUMN_sfz_no =       "sfz_no";
	    	 public static final String COLUMN_xh =           "xh";    
	    	 public static final String COLUMN_dwbm =         "dwbm";  
	    	 public static final String COLUMN_zwxh =         "zwxh";  
	    	 public static final String COLUMN_zwmc =         "zwmc";  
	    	 
	    	 public static final String COLUMN_rzsj =         "rzsj";  
	    	 public static final String COLUMN_wzh =          "wzh";  
	    	 public static final String COLUMN_xs =           "xs";    
	    	 public static final String COLUMN_js =           "js";    
	    	 public static final String COLUMN_sc =           "sc";    
	    	 public static final String COLUMN_bz =           "bz";    

		    
		    
	    	 public static final int COLUMN_INDEX_sfz_no =    0 ;
	    	 public static final int COLUMN_INDEX_xh =        1 ;
	    	 public static final int COLUMN_INDEX_dwbm =      2; 
	    	 public static final int COLUMN_INDEX_zwxh =      3; 
	    	 public static final int COLUMN_INDEX_zwmc =      4; 
	    	 public static final int COLUMN_INDEX_rzsj =      5; 
	    	 public static final int COLUMN_INDEX_wzxh =      6; 
	    	 public static final int COLUMN_INDEX_xs =        7; 
	    	 public static final int COLUMN_INDEX_js =        8; 
	    	 public static final int COLUMN_INDEX_sc =        9; 
	    	 public static final int COLUMN_INDEX_bz =        10; 


	    }
	    
	    
	    
	    public static class TFavoriteuser{
	    	public static final String TABLE_NAME        = "t_favorite_user";        
		    public static final String COLUMN_ID         = "favorite_id"; 
		    public static final String DEFAULT_SORT_ORDER = "favorite_id ASC"; 		   
		    public static final String COLUMN_USERID       = "user_id"; 
		    public static final String COLUMN_ADDTIME       = "add_time"; 
		    
		    public static final int COLUMN_INDEX__ID        = 0 ;   
		    public static final int COLUMN_INDEX__USERID       =1 ;
		    public static final int COLUMN_INDEX__ADDTIME       =2; 
		    
		    
		    
		   	    
	    }
	    
	    public static class TFavoritegroup{
	    	public static final String TABLE_NAME        = "t_favorite_group";        
		    public static final String COLUMN_ID         = "favorite_id"; 
		    public static final String DEFAULT_SORT_ORDER = "favorite_id ASC"; 		   
		    public static final String COLUMN_GROUPID       = "group_id"; 
		    public static final String COLUMN_ADDTIME       = "add_time"; 
		    
		    
		    
		    public static final int COLUMN_INDEX__ID        = 0 ;   
		    public static final int COLUMN_INDEX__GROUPID       =1 ;
		    public static final int COLUMN_INDEX__ADDTIME       =2; 
		    
		    
		   	    
	    }
	    
	    public static class TFamily{
	    	public static final String TABLE_NAME        = "gb_jtxx_table";        
		    
	    	public static final String COLUMN_sfz_no  =  "sfz_no"; 
	    	public static final String COLUMN_xh  =      "xh";     
	    	public static final String COLUMN_cw  =      "cw";     
	    	public static final String COLUMN_xm  =      "xm";     
	    	public static final String COLUMN_mz  =      "mz";     
	    	public static final String COLUMN_csrq  =    "csrq";   
	    	public static final String COLUMN_zzmm  =    "zzmm";   
	    	public static final String COLUMN_gzdw  =    "gzdw";   
	    	public static final String COLUMN_zw  =      "zw";     
	    	public static final String COLUMN_bz  =      "bz";     

	    	
	    	public static final int COLUMN_INDEX_sfz_no  =    0;   
	    	public static final int COLUMN_INDEX_xh  =        1;   
	    	public static final int COLUMN_INDEX_cw  =        2;   
	    	public static final int COLUMN_INDEX_xm  =        3;   
	    	public static final int COLUMN_INDEX_mz  =        4;   
	    	public static final int COLUMN_INDEX_csrq  =      5;   
	    	public static final int COLUMN_INDEX_zzmm  =      6;   
	    	public static final int COLUMN_INDEX_gzdw  =      7;   
	    	public static final int COLUMN_INDEX_zw  =        8;   
	    	public static final int COLUMN_INDEX_bz  =        9;   


		   	    
	    }
	    
	    public static class TDict{
	    	public static final String TABLE_NAME        = "gb_dict_table";        
		    
	    	public static final String COLUMN_lb	    =   "lb"	;
	    	public static final String COLUMN_bm      =   "bm"  ;
	    	public static final String COLUMN_mc	    =   "mc"	;
	    	public static final String COLUMN_sjbm    =   "sjbm";
	    	public static final String COLUMN_py	    =   "py"	;

	    	
	    	public static final int COLUMN_INDEX_lb  =    0;   
	    	public static final int COLUMN_INDEX_bm  =        1;   
	    	public static final int COLUMN_INDEX_mc  =        2;   
	    	public static final int COLUMN_INDEX_sjbm  =        3;   
	    	public static final int COLUMN_INDEX_py  =        4;   
    
	    }
	    
	    public static class TZjsj{
	    	public static final String TABLE_NAME        = "gb_zjsj_table";        
		    
	    	public static final String COLUMN_sfz_no	    =   "sfz_no"	;
	    	public static final String COLUMN_xh      =   "xh"  ;
	    	public static final String COLUMN_ldzj	    =   "ldzj"	;
	    	public static final String COLUMN_rzjsj    =   "rzjsj";
	    	public static final String COLUMN_bz	    =   "bz"	;

	    	
	    	public static final int COLUMN_INDEX_sfz_no	=    0;   
	    	public static final int COLUMN_INDEX_xh     =        1;   
	    	public static final int COLUMN_INDEX_ldzj	  =        2;   
	    	public static final int COLUMN_INDEX_rzjsj    =        3;   
	    	public static final int COLUMN_INDEX_bz	    =        4;   
    
	    }
	    
	    
	    
	    public static class TExamine{
	    	public static final String TABLE_NAME        = "t_examine";        
		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 		   
		    public static final String COLUMN_USERID       = "user_id"; 
		    public static final String COLUMN_EXAMINENAME       = "examine_name"; 
		    public static final String COLUMN_EXAMINETIME       = "examine_time"; 
		   		   	    
	    }
	    
	    public static class TEdu{
	    	public static final String TABLE_NAME        = "t_edu";        
		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 		   
		    public static final String COLUMN_EDUNAME       = "edu_name"; 
		  		   		   	    
	    }
	    
	    public static class TDepth{
	    	public static final String TABLE_NAME        = "t_depth";        
		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 		   
		    public static final String COLUMN_EDUNAME       = "depth_text"; 
		  		   		   	    
	    }
	    public static class TAssess{
	    	public static final String TABLE_NAME        = "t_assess";        
		    public static final String COLUMN_ID         = "_id"; 
		    public static final String DEFAULT_SORT_ORDER = "_id ASC"; 		   
		    public static final String COLUMN_YEAR       = "year"; 
		    public static final String COLUMN_RESULT       = "result"; 
		  		   		   	    
	    }
	    
	    
	    
	    
	    
}
