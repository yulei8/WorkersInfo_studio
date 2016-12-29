package com.chinamobile.workerspace.contacts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnValueChangeListener;

import com.chinamobile.workerspace.PersonFilter;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.adapter.ContactsListAdapter;
import com.chinamobile.workerspace.group.GroupManager;
import com.chinamobile.workerspace.utils.T;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.EditText;
import com.rey.material.widget.RadioButton;
import com.rey.material.widget.Slider;
import com.rey.material.widget.Slider.OnPositionChangeListener;

public class ContactsListActivity extends Activity implements OnClickListener, Formatter, OnValueChangeListener, OnCheckedChangeListener {

	private ContactsManager   mContactsMgr ;
	
	private ContactslistPage  contactPage ;
	
	private ListView mListView = null;
	 
	private ContactsListAdapter mAdapter = null;
	
	private int  mShowCategory ;
	private String mkey;
	private String   org_id ;
	private String birthdate ;
	
	private GroupManager    mGroupmgr;
	private List<String> mgroup;
	private List<List<GroupItem>> mchild;
	
	
	
	private NumberPicker mfirstYearPk;
	
	private RadioButton  mSexall ;
	private RadioButton  mSexmale ;
	private RadioButton  mSexfemale ;
	
	private RadioButton  mzgztall ;
	private RadioButton  mzgzt_zg ;
	private RadioButton  mzgzt_lz ;
	private RadioButton  mzgzt_tx ;
	
	
	private EditText     mSmallege_et;
	private EditText     mlargeege_et;

	
	private RadioButton  mNationall ;
	private RadioButton  mNationHan ;
	private RadioButton  mNationNotHan ; 
	private RadioButton  mDangyuanall ;
	private RadioButton  mDangyuan ;
	private RadioButton  mNotdangyuan ;
	
	private  CheckBox   mZhong ;   //全日制
	private  CheckBox   mZhuanke;  //专科 
	private  CheckBox   mBenkeke;
	private  CheckBox   mShuoshi;
	private  CheckBox   mBoshi;
	
	
	private  CheckBox   m_zaizhi_Zhong ;
	private  CheckBox   m_zaizhi_Zhuanke;  //专科 
	private  CheckBox   m_zaizhi_Benkeke;
	private  CheckBox   m_zaizhi_Shuoshi;
	private  CheckBox   m_zaizhi_Boshi;
	
	
	private CheckBox    mkeyuanBox;
	private CheckBox    mfukeBox;
	private CheckBox    mzhengkeBox;
	private CheckBox    mfuchuBox;
	private CheckBox    mzhengchuBox;
	
	private CheckBox    mgaozhongBox;
	private CheckBox    mshuoshiyanBox;
	private CheckBox    m_zaizhi_shuoshiyanBox;
	
	
	private EditText     mEarlyYear_et;  //
	private EditText     mLastYear_et;

	private EditText     mEarlyParty_et;  //
	private EditText     mLastParty_et;

	
	private EditText    mFuke_early_et;
	private EditText    mzhengke_early_et;
	private EditText    mFuxian_early_et;
	private EditText    mZhengxian_early_et;
	private EditText    mRenzhi_early_et;
	
	private EditText    mFuke_to_et;
	private EditText    mzhengke_to_et;
	private EditText    mFuxian_to_et;
	private EditText    mZhengxian_to_et;
	private EditText    mRenzhi_to_et;
	
	private EditText    msuoxuezhuanye_et;
	private EditText    mcongshigangwei_et;
	
	private Button      mFilterBtn ;
	private Button      mResetBtn ;
	
	private PersonFilter  personfilter = new PersonFilter();
	
	
	
	private DrawerLayout mDrawerLayout;
    private View mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
   
    
    
    
    
   
    private List<UserItem>  MakeProductsAndGroupID(String keys,String group_id){
		
    	
		List<UserItem>  list  = null;
		
		switch(mShowCategory){
		case 0:   //sex
			list = mContactsMgr.GetUserBySexandGroupId(keys,group_id);
			break;
		case 1:   //age
			Date date=new Date();
	    	SimpleDateFormat df=new SimpleDateFormat("yyyyMM");
	    	
	    	int index  = birthdate.indexOf(" ");
	    	if(index != -1){
	    		birthdate = birthdate.substring(0, index);
	    		
	    	}
	    	birthdate =birthdate.replace("-", "");
	    	
	    	
	    	int   age =  0;
	    			
	        if(birthdate.length() >=6){
	        	age =	(Integer.valueOf(df.format(date)) -Integer.valueOf(birthdate))/100; 
	        }
	    	//Log.d("ContactsListActivity", "current:"+date+" age:"+birthdate+" result:"+age);
	
	    	int type = 0 ;
	    	
	    	if(age < 35){
	    		type = 0 ;
	    	}else if(age <= 40){
	    		type = 1;
	    	}else if(age <=50){
	    		type = 2 ;
	    	}else if(age <= 55){
	    		type = 3 ;
	    	}else {
	    		type = 4 ;
	    	}
	    	
	    	list = mContactsMgr.GetUserByAgeandGroupId(type,group_id);
			break;
		case 2:  //nation
			list = mContactsMgr.GetUserByNationandGroupId(keys,group_id);   
			break;
		case 3:  //edu
			list = mContactsMgr.GetUserByEduandGroupId(keys,group_id);
			break;
		case 4:   //depth
			list = mContactsMgr.GetUserByDepthandGroupId(keys,group_id);
			break;
		case 5:
			list = mContactsMgr.GetUserByStatusandGroupId(keys,group_id);
			break;
			default:
				break;
		}
		
		return list ;
	}
	
	private List<UserItem>  MakeProducts(String keys){
		
		List<UserItem>  list  = null;
		
		switch(mShowCategory){
		case 0:   //sex
			list = mContactsMgr.GetUserBySex(keys);
			break;
		case 1:   //age
			Date date=new Date();
	    	SimpleDateFormat df=new SimpleDateFormat("yyyyMM");
	    	
	    	int index  = birthdate.indexOf(" ");
	    	if(index != -1){
	    		birthdate = birthdate.substring(0, index);
	    		
	    	}
	    	
	    	birthdate =birthdate.replace("-", "");
	    	int   age =  0;
	    	if(birthdate.length() >= 6){
	    		age = (Integer.valueOf(df.format(date)) -Integer.valueOf(birthdate))/100; 	
	    	}
	    	//Log.d("ContactsListActivity", "current:"+date+" age:"+birthdate+" result:"+age);
	
	    	int type = 0 ;
	    	
	    	if(age < 35){
	    		type = 0 ;
	    	}else if(age <= 40){
	    		type = 1;
	    	}else if(age <=50){
	    		type = 2 ;
	    	}else if(age <= 55){
	    		type = 3 ;
	    	}else {
	    		type = 4 ;
	    	}
	    	
	    	list = mContactsMgr.GetUserByAge(type);
			break;
		case 2:  //nation
			list = mContactsMgr.GetUserByNation(keys);    
			break;
		case 3:  //edu
			list = mContactsMgr.GetUserByEdu(keys);
			break;
		case 4:   //depth
			list = mContactsMgr.GetUserByDepth(keys);
			break;
		case 5:
			list = mContactsMgr.GetUserByStatus(keys);
			break;
		case 6:   //org
			list = mContactsMgr.GetUserByGroupID(org_id);
			break;
			default:
				break;
		}
		
		return list ;
	}

	private void InitView(){
		
	/*	
		mfirstYearPk.setFormatter(this);
		mfirstYearPk.setOnValueChangedListener(this);
		
		mfirstYearPk.setMaxValue(2015);
		mfirstYearPk.setMinValue(1970);
		mfirstYearPk.setValue(2000);
	*/
		
		mSexall  	 =  (RadioButton)findViewById(R.id.sex_all);           
		mSexmale 	 =  (RadioButton)findViewById(R.id.sex_male);                  
		mSexfemale	 =  (RadioButton)findViewById(R.id.sex_female);    
		mSexall.setOnCheckedChangeListener(this);
		mSexmale.setOnCheckedChangeListener(this);
		mSexfemale.setOnCheckedChangeListener(this);
		
		
		
		
		mzgztall  	 =  (RadioButton)findViewById(R.id.zgzt_all);           
		mzgzt_zg 	 =  (RadioButton)findViewById(R.id.zgzt_zg);                  
		mzgzt_lz	 =  (RadioButton)findViewById(R.id.zgzt_lz);  
		mzgzt_tx	 =  (RadioButton)findViewById(R.id.zgzt_tx);
		mzgztall.setOnCheckedChangeListener(this);
		mzgzt_zg.setOnCheckedChangeListener(this);
		mzgzt_tx.setOnCheckedChangeListener(this);
		mzgzt_lz.setOnCheckedChangeListener(this);
		
		
		
		
		mSmallege_et =   (EditText)findViewById(R.id.small_age_edit);         
		mlargeege_et    =   (EditText)findViewById(R.id.small_age_edit_end);  
		
		
		
		mSmallege_et.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.toString().isEmpty() == false){
					int value = Integer.valueOf(s.toString()) ;
					personfilter.setNagemin(value);
					/*mSmallege_slider.setValue(value, true);*/
				}else{
					personfilter.setNagemin(0);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
		mlargeege_et.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.toString().isEmpty() == false){
					int value = Integer.valueOf(s.toString()) ;
					personfilter.setNagemax(value);
					/*mlargeege_slider.setValue(value, true);*/
				}else{
					personfilter.setNagemax(0);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		mNationall 			=   (RadioButton)findViewById(R.id.minzu_all);         
		mNationHan 			=   (RadioButton)findViewById(R.id.minzu_han);          
		mNationNotHan 		=   (RadioButton)findViewById(R.id.minzu_shao);       
		mDangyuanall 		=   (RadioButton)findViewById(R.id.dangyuan_all);        
		mDangyuan 			=   (RadioButton)findViewById(R.id.dangyuan_zhonggong);           
		mNotdangyuan 		=   (RadioButton)findViewById(R.id.dangyuan_fei);        
		mNationall.setOnCheckedChangeListener(this);
		mNationHan.setOnCheckedChangeListener(this);
		mNationNotHan.setOnCheckedChangeListener(this);
		
		mDangyuanall.setOnCheckedChangeListener(this);
		mDangyuan.setOnCheckedChangeListener(this);
		mNotdangyuan.setOnCheckedChangeListener(this);
		

		
		
		
		
		mZhong              =   (CheckBox)findViewById(R.id.zhongzhuan_degree_checkbox); 
		mZhuanke			=   (CheckBox)findViewById(R.id.zhuan_degree_checkbox);   
		mBenkeke			=   (CheckBox)findViewById(R.id.benke_degree_checkbox);               
		mShuoshi			=   (CheckBox)findViewById(R.id.master_degree_checkbox);                
		mBoshi				=   (CheckBox)findViewById(R.id.doctor_degree_checkbox); 
		mZhong.setOnCheckedChangeListener(this);
		mZhuanke.setOnCheckedChangeListener(this);
		mBenkeke.setOnCheckedChangeListener(this);
		mShuoshi.setOnCheckedChangeListener(this);
		mBoshi.setOnCheckedChangeListener(this);
		
		m_zaizhi_Zhong              =   (CheckBox)findViewById(R.id.zhongzhuan_degree_checkbox_1); 
		m_zaizhi_Zhuanke			=   (CheckBox)findViewById(R.id.zhuan_degree_checkbox_1);   
		m_zaizhi_Benkeke			=   (CheckBox)findViewById(R.id.benke_degree_checkbox_1);               
		m_zaizhi_Shuoshi			=   (CheckBox)findViewById(R.id.master_degree_checkbox_1);                
		m_zaizhi_Boshi				=   (CheckBox)findViewById(R.id.doctor_degree_checkbox_1); 
		m_zaizhi_Zhong.setOnCheckedChangeListener(this);
		m_zaizhi_Zhuanke.setOnCheckedChangeListener(this);
		m_zaizhi_Benkeke.setOnCheckedChangeListener(this);
		m_zaizhi_Shuoshi.setOnCheckedChangeListener(this);
		m_zaizhi_Boshi.setOnCheckedChangeListener(this);
		
		                      

		
		mgaozhongBox			=   (CheckBox)findViewById(R.id.gaozhong_degree_checkbox);               
		mshuoshiyanBox			=   (CheckBox)findViewById(R.id.master_shuo_degree_checkbox);                
		m_zaizhi_shuoshiyanBox				=   (CheckBox)findViewById(R.id.master_shuo_degree_checkbox_1); 
		mgaozhongBox.setOnCheckedChangeListener(this);
		mshuoshiyanBox.setOnCheckedChangeListener(this);
		m_zaizhi_shuoshiyanBox.setOnCheckedChangeListener(this);
		
		
		
		
		
		
		
		mkeyuanBox  = (CheckBox)findViewById(R.id.keyuan_checkbox);   
		mkeyuanBox.setOnCheckedChangeListener(this);
		mfukeBox  = (CheckBox)findViewById(R.id.fuke_checkbox);   
		mfukeBox.setOnCheckedChangeListener(this);
		mzhengkeBox  = (CheckBox)findViewById(R.id.zhengke_checkbox);   
		mzhengkeBox.setOnCheckedChangeListener(this);
		mfuchuBox  = (CheckBox)findViewById(R.id.fuchu_checkbox);   
		mfuchuBox.setOnCheckedChangeListener(this);
		mzhengchuBox  = (CheckBox)findViewById(R.id.zhengchu_checkbox);   
		mzhengchuBox.setOnCheckedChangeListener(this);
		
		
		
		
		mEarlyYear_et		=   (EditText)findViewById(R.id.work_age_edit);    
		mLastYear_et		=   (EditText)findViewById(R.id.work_age_edit_end);  
		mEarlyYear_et.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.toString().isEmpty() == false){
					int value = Integer.valueOf(s.toString()) ;
					personfilter.setNjobtimebein(value);
					/*mEarlyYear_slider.setValue(value, true);*/
				}else{
					personfilter.setNjobtimebein(0);
				}
			
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
		mLastYear_et.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.toString().isEmpty() == false){
					int value = Integer.valueOf(s.toString()) ;
					personfilter.setNjobtimeend(value);
					/*mLastYear_slider.setValue(value, true);*/
				}else{
					personfilter.setNjobtimeend(0);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Date dt=new Date();
	    SimpleDateFormat matter1year =new SimpleDateFormat("yyyy");
	    
	    int currentyear =  Integer.valueOf(matter1year.format(dt)) ;
	    
	    
		
		
		
		
		                      
		mEarlyParty_et		=   (EditText)findViewById(R.id.dang_age_edit);    
		mLastParty_et		=   (EditText)findViewById(R.id.dang_age_edit_end);  
		mEarlyParty_et.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(s.toString().isEmpty() == false){
					int value = Integer.valueOf(s.toString()) ;
					personfilter.setNrdtimebegin(value);
					/*mEarlyParty_slider.setValue(value, true);*/
				}else{
					personfilter.setNrdtimebegin(0);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
		mLastParty_et.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
				if(s.toString().isEmpty() == false){
				
					int value = Integer.valueOf(s.toString()) ;
					personfilter.setNrdtimeend(value);
					/*mLastParty_slider.setValue(value, true);*/
				}else{
					personfilter.setNrdtimeend(0);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if(s.toString().isEmpty() == true){
					
				}
			}
			
		});
		
		
		mFuke_early_et =   (EditText)findViewById(R.id.fuke_age_edit);    
		mFuke_early_et.addTextChangedListener(new MyTextWatcher(mFuke_early_et));
		
		mzhengke_early_et =   (EditText)findViewById(R.id.zhengke_age_edit);    
		mzhengke_early_et.addTextChangedListener(new MyTextWatcher(mzhengke_early_et));
		
		mFuxian_early_et =   (EditText)findViewById(R.id.fuxian_age_edit);    
		mFuxian_early_et.addTextChangedListener(new MyTextWatcher(mFuxian_early_et));
		
		mZhengxian_early_et =   (EditText)findViewById(R.id.zhengxian_age_edit);    
		mZhengxian_early_et.addTextChangedListener(new MyTextWatcher(mZhengxian_early_et));
		
		mRenzhi_early_et =   (EditText)findViewById(R.id.xianzhi_age_edit);    
		mRenzhi_early_et.addTextChangedListener(new MyTextWatcher(mRenzhi_early_et));
		
		
		mFuke_to_et =   (EditText)findViewById(R.id.fuke_age_edit_end);    
		mFuke_to_et.addTextChangedListener(new MyTextWatcher(mFuke_to_et));
		
		mzhengke_to_et =   (EditText)findViewById(R.id.zhengke_age_edit_end);    
		mzhengke_to_et.addTextChangedListener(new MyTextWatcher(mzhengke_to_et));
		
		mFuxian_to_et =   (EditText)findViewById(R.id.fuxian_age_edit_end);    
		mFuxian_to_et.addTextChangedListener(new MyTextWatcher(mFuxian_to_et));
		
		mZhengxian_to_et =   (EditText)findViewById(R.id.zhengxian_age_edit_end);    
		mZhengxian_to_et.addTextChangedListener(new MyTextWatcher(mZhengxian_to_et));
		
		mRenzhi_to_et =   (EditText)findViewById(R.id.xianzhi_age_edit_end);    
		mRenzhi_to_et.addTextChangedListener(new MyTextWatcher(mRenzhi_to_et));
		
		
		msuoxuezhuanye_et  =   (EditText)findViewById(R.id.xuekezhuanye_edit);   
		mcongshigangwei_et =   (EditText)findViewById(R.id.congshigangwei_edit);   
		msuoxuezhuanye_et.addTextChangedListener(new MyTextWatcher(msuoxuezhuanye_et));
		mcongshigangwei_et.addTextChangedListener(new MyTextWatcher(mcongshigangwei_et));
		
		
		mFilterBtn = (Button)findViewById(R.id.sure_filter); 
		
		mFilterBtn.setOnClickListener(this);
		

		mResetBtn = (Button)findViewById(R.id.reset); 
		
		mResetBtn.setOnClickListener(this);
	}
	
	
	private void RestFilter(){
		mSexall.setChecked(true);
		mSexmale.setChecked(false);
		mSexfemale.setChecked(false);
		mSmallege_et.setText("");
		mlargeege_et.setText("");
		mNationall.setChecked(true);
		mNationHan.setChecked(false);
		mNationNotHan.setChecked(false);
		mDangyuanall.setChecked(true);
		mDangyuan.setChecked(false);
		mNotdangyuan.setChecked(false);
		mZhong.setChecked(false);
		mZhuanke.setChecked(false);
		mBenkeke.setChecked(false);
		mShuoshi.setChecked(false);
		mBoshi.setChecked(false);
		m_zaizhi_Zhong.setChecked(false);
		m_zaizhi_Zhuanke.setChecked(false);
		m_zaizhi_Benkeke.setChecked(false);
		m_zaizhi_Shuoshi.setChecked(false);
		m_zaizhi_Boshi.setChecked(false);
		
		
		mgaozhongBox.setChecked(false);
		mshuoshiyanBox.setChecked(false);
		m_zaizhi_shuoshiyanBox.setChecked(false);
		
		
		
		mkeyuanBox.setChecked(false);
		mfukeBox.setChecked(false);
		mzhengkeBox.setChecked(false);
		mfuchuBox.setChecked(false);
		mzhengchuBox.setChecked(false);
		mEarlyYear_et.setText("");
		mLastYear_et.setText("");
		mEarlyParty_et.setText("");
		mLastParty_et.setText("");
		mFuke_early_et.setText("");
		mzhengke_early_et.setText("");
		mFuxian_early_et.setText("");
		mZhengxian_early_et.setText("");
		mRenzhi_early_et.setText("");
		
		mFuke_to_et.setText("");
		mzhengke_to_et.setText("");
		mFuxian_to_et.setText("");
		mZhengxian_to_et.setText("");
		mRenzhi_to_et.setText("");
		
		mcongshigangwei_et.setText("");
		msuoxuezhuanye_et.setText("");
		personfilter.Reset();
	}
	
	public class MyTextWatcher implements TextWatcher
	{
       
		private EditText EditID = null ;
		
		public MyTextWatcher(EditText id ){
			EditID = id ;
			
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			int value = 0 ;
			
			if(EditID == msuoxuezhuanye_et){
				personfilter.setM_suoxuezhuanye(arg0.toString());
				return ;
			}else if(EditID == mcongshigangwei_et){
				personfilter.setM_congshigangwei(arg0.toString());
				return ;
			}
			
			
			if(arg0.toString().isEmpty() == true){
				
				value = 0;
			}else{
				value = Integer.valueOf(arg0.toString()) ;
			}
			

			//Log.d("afterTextChanged", "editid："+EditID+"mFuxian_early_et:"+mFuxian_early_et+"value:"+value);
			
		    if(EditID == mFuke_early_et){
		    	personfilter.setNzj_fuke_min(value);
			}else if(EditID == mzhengke_early_et){
				personfilter.setNzj_zhengke_min(value);
			}else if(EditID == mFuxian_early_et){
				personfilter.setNzj_fuxian_min(value);
				
			}else if(EditID == mZhengxian_early_et){
				personfilter.setNzj_zhengxian_min(value);
			}else if(EditID == mRenzhi_early_et){
				personfilter.setNzj_xianzhi_min(value);
			}else if(EditID == mFuke_to_et){
				personfilter.setNzj_fuke_max(value);
			}else if(EditID == mzhengke_to_et){
				personfilter.setNzj_zhengke_max(value);
			}else if(EditID == mFuxian_to_et){
				personfilter.setNzj_fuxian_max(value);
			}else if(EditID == mZhengxian_to_et){
				personfilter.setNzj_zhengxian_max(value);
			}else if(EditID == mRenzhi_to_et){
				personfilter.setNzj_zhengxian_max(value);
			}
		    
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater(); 
		
		inflater.inflate(R.menu.back, menu);  
		return true;
	}
	
	private void InitGroupData(){
		
		List<GroupItem> groups =  mGroupmgr.GetTopLevelGroup();
		
		mgroup = new ArrayList<String>();
		
		mchild = new ArrayList<List<GroupItem>>();
		for(int i = 0 ; i < groups.size() ; i++){
			mgroup.add(groups.get(i).getGroup_name());
			List<GroupItem> childs = mGroupmgr.GetChildLevelGroup(groups.get(i).getGroup_id());
			
			if(childs.size() >0){
				mchild.add(childs);
			}		
			
			
		}
		
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/*
		setContentView(R.layout.contacts_list);
		
		
		mListView = (ListView) findViewById(R.id.list_view_user_list);
		
        mContactsMgr = ContactsManager.GetInstance(this);
		
		List<UserItem> list = mContactsMgr.GetAllUsers();
		
		
		
		mAdapter = new ContactsListAdapter(this,list);
		
		mListView.setAdapter(mAdapter);*/
		
		
		mGroupmgr = GroupManager.GetInstance(this);
		InitGroupData();
		LayoutInflater li = LayoutInflater.from(this);	
		View viewPersonlistchart = li.inflate(R.layout.contacts_list, null);
		
		setContentView(viewPersonlistchart);
		
		
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);

		
        
		Log.d("getActionBar", "getActionBar:"+ab);
		
		
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       
        
     /*   mfirstYearPk = (NumberPicker)findViewById(R.id.first_party_year_picker);
     */   

        InitView();
        
        mDrawerList =  findViewById(R.id.main2);

        mDrawerList.setOnClickListener(this);
        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            drawerArrow, R.string.drawer_open,
            R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
       
		
        
		
		
		mShowCategory = getIntent().getIntExtra("category", -1);
		
		mkey = getIntent().getStringExtra("category_keys");

		birthdate = getIntent().getStringExtra("birthdate");
		org_id = getIntent().getStringExtra("org_id") ;
		
		contactPage = ContactslistPage.GetInstance();
		
		mContactsMgr = ContactsManager.GetInstance(this);
		
		List<UserItem> list = new ArrayList<UserItem>() ;
		
		if(mShowCategory != -1){
			
			String group_id = getIntent().getStringExtra("group_id")  ; 
			if(group_id!= null){
				list = MakeProductsAndGroupID(mkey,group_id);//从组织机构查询进入
			}else{
				list = MakeProducts(mkey) ;   //从统计入口进入
			}
				
			
			
			
		}else{
			list = mContactsMgr.GetAllUsers();
			
			Log.d("Contacts:", "contacts size :"+list.size());
			/*for(int i =0 ; i < mchild.size() ; i++){
				List<GroupItem>  child = mchild.get(i);
				for(int j = 0 ; j < child.size(); j++){
					List<UserItem> users =  mContactsMgr.GetUserByGroupID(child.get(j).getGroup_id());
					if(users.size() >0){
						list.addAll(users);
					}
				}
			}*/
		
		}
		
	    String title = String.format("人员信息查询(单位共:%d ,人员共: %d)", mGroupmgr.GetAllGroupsCount(),list.size());
	    ab.setTitle(title);
		
		
		
		contactPage.OnInit(viewPersonlistchart, this);
		if(list != null){
			contactPage.SetData(list);
			contactPage.OnCreate(ab);
			
			if(mShowCategory != -1){
				contactPage.OnlyShowList();
			}
			
		}
		
		//Log.d("Test", "count:"+list.size());
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		mContactsMgr.Close();
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
		Log.d("onOptionsItemSelected", "item:"+item.getItemId());
        
		switch(item.getItemId()){
		case R.id.menu_back:
			finish();
			break;
		default:
	        if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
	        	Log.d("onOptionsItemSelected close", "item:"+item.getItemId());
	            mDrawerLayout.closeDrawer(mDrawerList);
	        } else {
	        	Log.d("onOptionsItemSelected open", "item:"+item.getItemId());
	            mDrawerLayout.openDrawer(mDrawerList);
	        }
			break;
			
		}
		
        
        return super.onOptionsItemSelected(item);
    }
	
	 @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Log.d("onClick", "test");
		
		
		if(arg0.getId() == R.id.sure_filter){
			
			mDrawerLayout.closeDrawer(mDrawerList);
			contactPage.SetFilterAndUpdate(personfilter);
		}else if(arg0.getId() == R.id.reset){
		
			RestFilter();
		}
	}

	@Override
	public String format(int value) {
		// TODO Auto-generated method stub
		
		return String.valueOf(value);
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.zgzt_all:
			if(isChecked == true){
				mzgzt_zg.setChecked(false);
				mzgzt_lz.setChecked(false);
				mzgzt_tx.setChecked(false);
				personfilter.setNzgzt(0);
			}
			break;
		case R.id.zgzt_zg:
			if(isChecked == true){
				mzgzt_tx.setChecked(false);
				mzgzt_lz.setChecked(false);
				mzgztall.setChecked(false);
				personfilter.setNzgzt(1); //zaigang
			}
			
			break;
		case R.id.zgzt_lz:
			if(isChecked == true){
				mzgzt_tx.setChecked(false);
				mzgzt_zg.setChecked(false);
				mzgztall.setChecked(false);
				personfilter.setNzgzt(2); //lizhi
			}
			
			break;
		case R.id.zgzt_tx:
			if(isChecked == true){
				mzgzt_zg.setChecked(false);
				mzgzt_lz.setChecked(false);
				mzgztall.setChecked(false);
				personfilter.setNzgzt(3); //tuixiu
			}
			
			break;
		case R.id.sex_all:
			if(isChecked == true){
				mSexfemale.setChecked(false);
				mSexmale.setChecked(false);
				personfilter.setNxb(0);
			}
			break;
		case R.id.sex_male:
			if(isChecked == true){
				mSexall.setChecked(false);
				mSexfemale.setChecked(false);
				personfilter.setNxb(1);
			}
			
			break;
		case R.id.sex_female:
			if(isChecked == true){
				mSexall.setChecked(false);
				mSexmale.setChecked(false);
				personfilter.setNxb(2);
			}
			break;
		case R.id.minzu_all:
			if(isChecked == true){
				mNationHan.setChecked(false);
				mNationNotHan.setChecked(false);
				personfilter.setNmz(0);
			}
			break;
		case R.id.minzu_han:
			if(isChecked == true){
				mNationall.setChecked(false);
				mNationNotHan.setChecked(false);
				personfilter.setNmz(1);
			}
			
			break;
		case R.id.minzu_shao:
			if(isChecked == true){
				mNationall.setChecked(false);
				mNationHan.setChecked(false);
				personfilter.setNmz(2);
			}
			break;
		case R.id.dangyuan_all:
			if(isChecked == true){
				mDangyuan.setChecked(false);
				mNotdangyuan.setChecked(false);
				personfilter.setNzzmm(0);
			}
			break;
		case R.id.dangyuan_zhonggong:
			if(isChecked == true){
				mDangyuanall.setChecked(false);
				mNotdangyuan.setChecked(false);
				personfilter.setNzzmm(1);
			}
			
			break;
		case R.id.dangyuan_fei:
			if(isChecked == true){
				mDangyuanall.setChecked(false);
				mDangyuan.setChecked(false);
				personfilter.setNzzmm(2);
			}
			break;
		case R.id.zhongzhuan_degree_checkbox:
			if(isChecked == true){
				personfilter.setQxl_zhong(1);
			}else{
				personfilter.setQxl_zhong(0);
			}
			break;
		case R.id.zhuan_degree_checkbox:
			if(isChecked == true){
				personfilter.setQxl_zhuan(1);
			}else{
				personfilter.setQxl_zhuan(0);
			}
			break;
		case R.id.benke_degree_checkbox:
			if(isChecked == true){
				personfilter.setQxl_ben(1);
			}else{
				personfilter.setQxl_ben(0);
			}
				
			break;
		case R.id.master_degree_checkbox:
			if(isChecked == true){
				personfilter.setQxl_shuo(1);
			}else{
				personfilter.setQxl_shuo(0);
			}
			break;
		case R.id.doctor_degree_checkbox:
			if(isChecked == true){
				personfilter.setQxl_bo(1);
			}else{
				personfilter.setQxl_bo(0);
			}
			break;
		case R.id.zhongzhuan_degree_checkbox_1:
			if(isChecked == true){
				personfilter.setZxl_zhong(1);
			}else{
				personfilter.setZxl_zhong(0);
			}
			break;
		case R.id.zhuan_degree_checkbox_1:
			if(isChecked == true){
				personfilter.setZxl_zhuan(1);
			}else{
				personfilter.setZxl_zhuan(0);
			}
			break;
		case R.id.benke_degree_checkbox_1:
			if(isChecked == true){
				personfilter.setZxl_ben(1);
			}else{
				personfilter.setZxl_ben(0);
			}
				
			break;
		case R.id.master_degree_checkbox_1:
			if(isChecked == true){
				personfilter.setZxl_shuo(1);
			}else{
				personfilter.setZxl_shuo(0);
			}
			break;
		case R.id.doctor_degree_checkbox_1:
			if(isChecked == true){
				personfilter.setZxl_bo(1);
			}else{
				personfilter.setZxl_bo(0);
			}
			break;
		
		case R.id.gaozhong_degree_checkbox:
			if(isChecked == true){
				personfilter.setQxl_gaozhong(1);
			}else{
				personfilter.setQxl_gaozhong(0);
			}
				
			break;
		case R.id.master_shuo_degree_checkbox:
			if(isChecked == true){
				personfilter.setQxl_shuo_yan(1);
			}else{
				personfilter.setQxl_shuo_yan(0);
			}
			break;
		case R.id.master_shuo_degree_checkbox_1:
			if(isChecked == true){
				personfilter.setZxl_shuo_yan(1);
			}else{
				personfilter.setZxl_shuo_yan(0);
			}
			break;
		
		case R.id.keyuan_checkbox:
			if(isChecked == true){
				personfilter.setXrzj_keyuan(1);
			}else{
				personfilter.setXrzj_keyuan(0);
			}
			break;
		case R.id.fuke_checkbox:
			if(isChecked == true){
				personfilter.setXrzj_fuke(1);
			}else{
				personfilter.setXrzj_fuke(0);
			}
			break;
		case R.id.zhengke_checkbox:
			if(isChecked == true){
				personfilter.setXrzj_zhengke(1);
			}else{
				personfilter.setXrzj_zhengke(0);
			}
			break;
		case R.id.fuchu_checkbox:
			if(isChecked == true){
				personfilter.setXrzj_fuxian(1);
			}else{
				personfilter.setXrzj_fuxian(0);
			}
			break;
		case R.id.zhengchu_checkbox:
			if(isChecked == true){
				personfilter.setXrzj_zhengxian(1);
			}else{
				personfilter.setXrzj_zhengxian(0);
			}
			break;
		default:
			break;
		}
	}

	

	
}
