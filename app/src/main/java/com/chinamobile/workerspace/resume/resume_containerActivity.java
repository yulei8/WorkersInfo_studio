package com.chinamobile.workerspace.resume;



import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.chinamobile.workerspace.HistoryType;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.contacts.ZhijiItem;
import com.chinamobile.workerspace.db.ConstProfile;
import com.chinamobile.workerspace.db.ZhijiQueryManager;
import com.chinamobile.workerspace.favorite.FavorateActivity;
import com.chinamobile.workerspace.favorite.FavoriteManager;
import com.chinamobile.workerspace.history.HistoryManager;
import com.chinamobile.workerspace.ui.CustomMainBodyView;
import com.chinamobile.workerspace.utils.T;

public class resume_containerActivity extends Activity {

	private static int mCurPage;
	private static int mCurPageOnTab[];
	
	/* page definition */
	
	public static final int mPageShort = 0;
	public static final int mPageDetail = 1;
	public static final int mPageFamily = 3;
	public static final int mPageResume = 2;
	
	
	/* tab number definition */
	public static final int mTabNumShort = 0;
	public static final int mTabNumDetail = 1;
	public static final int mTabNumFamily = 3;
	public static final int mTabNumResume = 2;
	
	
	private CustomMainBodyView mMainPager;
	private ViewPagerListener  mViewListener;
	private ImageView mTabShort;
	private ImageView mTabDetail;
	private ImageView mTabFamily;
	private ImageView mTabResume;
	
	
	private View  mShortPanel;
	private View  mDetailPanel;
	private View  mFamilyPanel;
	private View  mResumePanel;
	
	private ArrayList<View> listOfViews ;
	
	private ContactsManager   mContactsMgr ;
	
	private ZhijiQueryManager mZhijiQuery ;
	private HistoryManager  mHistoryMgr ;
	
	private  String  msfz_no ;
	private  UserItem  mCurrentItem ;
	private  List<String> mItems = new ArrayList<String>();
	
	private  ActionBar mAb = null ;
	
	//private List<String> mNumbers ;
	private int          mIndex   ;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_detail_container);
		
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
		
		mAb = ab ;
		mContactsMgr = ContactsManager.GetInstance(this);
		
		mZhijiQuery = ZhijiQueryManager.GetInstance(this);
		
		mHistoryMgr = HistoryManager.GetInstance(this);	
		
		msfz_no = getIntent().getStringExtra("user_id");
		
		//mNumbers = mContactsMgr.GetAllNumbers();
		
		mIndex = getIntent().getIntExtra("index", 0);
		
		mCurrentItem = mContactsMgr.GetUserById(msfz_no);
		
		List<ZhijiItem> zhijiitems = mZhijiQuery.GetZhijiInfomation(msfz_no);
		if(zhijiitems.size() > 0){
			for(int i = 0 ; i< zhijiitems.size() ;i++){
				ZhijiItem item = zhijiitems.get(i);
				switch(Integer.valueOf(item.getLdzj())){
				case 13:
				case 14: //zhengxian
					mCurrentItem.setM_zhengxiansj(mContactsMgr.TimeFormat(item.getRzjsj()));
					break;
				case 15:
				case 16:
					mCurrentItem.setM_fuxiansj(mContactsMgr.TimeFormat(item.getRzjsj()));
					 break;
				 default:
					 break;
				
				}
			}
		}
		
		mItems = (ArrayList<String>)getIntent().getSerializableExtra("items");
		
		if(mItems != null){
			mAb.setTitle(String.format("%s(%d/%d)", "个人信息查看",mIndex+1,mItems.size()));
		}else {
			mAb.setTitle(String.format("%s", "个人信息查看"));
		}
		
		
		
		String title = String.format("%s %s %s %s 出生", mCurrentItem.getM_xm(),mCurrentItem.getM_xb(),mCurrentItem.getM_mz(),mCurrentItem.getM_csrq());
		
		
		mHistoryMgr.InsertOrUpdateHistory(title, msfz_no, HistoryType.QUERY_USER);
		
		
		
		mCurPage = mPageShort;
		mCurPageOnTab = new int[4];
		mCurPageOnTab[mTabNumShort] = mPageShort;
		mCurPageOnTab[mTabNumDetail] = mPageDetail;
		mCurPageOnTab[mTabNumFamily] = mPageFamily;
		mCurPageOnTab[mTabNumResume] = mPageResume;
		
		
		mMainPager = (CustomMainBodyView)findViewById(R.id.resume_main_container_tabpager);
		
		mViewListener = new ViewPagerListener();
		mMainPager.setOnPageChangeListener(mViewListener);
		
	
		
		mTabShort = (ImageView)findViewById(R.id.resume_container_short);
		mTabDetail = (ImageView)findViewById(R.id.resume_container_detail);
		mTabFamily = (ImageView)findViewById(R.id.resume_container_family);
		mTabResume = (ImageView)findViewById(R.id.resume_container_resume);
		
		mShortPanel = (View)findViewById(R.id.resume_container_short_panel);
		mDetailPanel = (View)findViewById(R.id.resume_container_detail_panel);
		mFamilyPanel = (View)findViewById(R.id.resume_container_family_panel);
		mResumePanel = (View)findViewById(R.id.resume_container_resume_panel);
		
		mShortPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumShort));
		mDetailPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumDetail));
		mFamilyPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumFamily));
		mResumePanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumResume));
		
		
		mTabShort.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumShort));
		mTabDetail.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumDetail));
		mTabFamily.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumFamily));
		mTabResume.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumResume));
		
		
		LayoutInflater li = LayoutInflater.from(this);	
		View viewPersonresume = li.inflate(R.layout.person_resume, null);
		
		View viewDetail = li.inflate(R.layout.person_detail_list_view, null);
		
		View viewFamily = li.inflate(R.layout.person_family_listview, null);
		
		View viewExperience = li.inflate(R.layout.person_experience_view, null);
		
		
		listOfViews = new ArrayList<View>();
		listOfViews.add(viewPersonresume);
		listOfViews.add(viewDetail);
		listOfViews.add(viewExperience);
		listOfViews.add(viewFamily);
		
		
		
      
        
        PagerAdapter pagerAdapter0 = new PagerAdapter() {

    		@Override
    		public void destroyItem(View arg0, int arg1, Object arg2) {
    			// TODO Auto-generated method stub
    			((ViewPager)arg0).removeView(listOfViews.get(arg1));
    		}

    		@Override
    		public void finishUpdate(View arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public int getCount() {
    			// TODO Auto-generated method stub
    			return listOfViews.size();
    		}

    		@Override
    		public Object instantiateItem(View arg0, int arg1) {
    			// TODO Auto-generated method stub
    			((ViewPager)arg0).addView(listOfViews.get(arg1));
    			return listOfViews.get(arg1);
    		}

    		@Override
    		public boolean isViewFromObject(View arg0, Object arg1) {
    			// TODO Auto-generated method stub
    			return arg0 == arg1;
    		}

    		@Override
    		public void restoreState(Parcelable arg0, ClassLoader arg1) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public Parcelable saveState() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public void startUpdate(View arg0) {
    			// TODO Auto-generated method stub
    			
    		}
    		
    		
    	};
        mMainPager.setAdapter(pagerAdapter0);
        resume_shortPage.GetInstance().OnInit(viewPersonresume, this);
        resume_shortPage.GetInstance().SetItem(mCurrentItem);
        resume_shortPage.GetInstance().OnCreate();
       
        resume_DetailPage.GetInstance().OnInit(viewDetail, this);
        resume_DetailPage.GetInstance().SetItem(mCurrentItem);
        resume_DetailPage.GetInstance().OnCreate();
        
        resume_familyPage.GetInstance().OnInit(viewFamily,this);
        
        
        resume_familyPage.GetInstance().SetInitData(mContactsMgr.GetFamilyListByUserID(msfz_no));
        resume_familyPage.GetInstance().OnCreate();
        
        resume_experiencePage.GetInstance().OnInit(viewExperience, this);
        resume_experiencePage.GetInstance().SetInitData(mContactsMgr.GetResumeListByUserID(msfz_no));
        
        resume_experiencePage.GetInstance().SetViewData(mCurrentItem.getM_jlxx());
        resume_experiencePage.GetInstance().OnCreate();
	}


	/*
	 * 
	 * type = 1 ,next 
	 * type = 0 ,pre
	 */
	private void ShowNextorPrePersion(int type){
		
		if(mItems == null){
			T.showShortIntop(this, "只能查看当前人员信息");
			return ;
		}
		
		if(type == 1){
			if(mIndex >= mItems.size()-1){
				T.showLong(this, "已经是最后一个");
				return ;
			}
			mIndex ++ ;
		}else {
			if(mIndex == 0){
				T.showLong(this, "已经是第一个");
				return ;
			}
			mIndex -- ;
		}
		
		
		mAb.setTitle(String.format("%s(%d/%d)", "个人信息查看",mIndex+1,mItems.size()));
		
		//String current = mNumbers.get(mIndex);
		
		mCurrentItem =  mContactsMgr.GetUserById(mItems.get(mIndex));  //mContactsMgr.GetUserById(current);
		
		
		
		List<ZhijiItem> zhijiitems = mZhijiQuery.GetZhijiInfomation(mCurrentItem.getM_sfz_no());
		if(zhijiitems.size() > 0){
			for(int i = 0 ; i< zhijiitems.size() ;i++){
				ZhijiItem item = zhijiitems.get(i);
				switch(Integer.valueOf(item.getLdzj())){
				case 13:
				case 14: //zhengxian
					mCurrentItem.setM_zhengxiansj(mContactsMgr.TimeFormat(item.getRzjsj()));
					break;
				case 15:
				case 16:
					mCurrentItem.setM_fuxiansj(mContactsMgr.TimeFormat(item.getRzjsj()));
					 break;
				 default:
					 break;
				
				}
			}
		}
		
		resume_shortPage.GetInstance().SetItem(mCurrentItem);
	    resume_shortPage.GetInstance().OnUpdate();
	    
	    
	    resume_DetailPage.GetInstance().SetItem(mCurrentItem);
        resume_DetailPage.GetInstance().OnUpdate();
        
        resume_familyPage.GetInstance().SetInitData(mContactsMgr.GetFamilyListByUserID(mCurrentItem.getM_sfz_no()));
        resume_familyPage.GetInstance().OnUpdate();
        
        
        resume_experiencePage.GetInstance().SetViewData(mCurrentItem.getM_jlxx());
        resume_experiencePage.GetInstance().OnUpdate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int  action = event.getAction() ;
		
		float y = event.getY();
		float x = event.getX();
		
		Log.d("resume", "action:"+action+"x:"+x+"y:"+y);
		
		switch(action){
		 case MotionEvent.ACTION_DOWN: 
			 break;
		 case MotionEvent.ACTION_UP: 
			 break;
		 case MotionEvent.ACTION_MOVE:  
			 break;
		}
		
		return super.onTouchEvent(event);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater(); 
		
		inflater.inflate(R.menu.contactsview, menu);  
		return true;
	}

	private void GoFavorateActivity(){
		
		
		
		Intent  frqueui = new Intent();
		
		frqueui.putExtra("start_flag", 0);
		frqueui.setClass(this,FavorateActivity.class);
		frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		
		startActivity(frqueui);	
		
		overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case android.R.id.home:  
			finish();
			break;
		case R.id.menu_save:
			AddFavorateItem();
			T.showShortIntop(this, this.getString(R.string.save_success));
			break;
		case R.id.view_menu_save:
			GoFavorateActivity();
			break;
		case R.id.view_menu_last:
			ShowNextorPrePersion(0);
			break;
		case R.id.view_menu_next:
			ShowNextorPrePersion(1);
		    break;
		default:
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}

   private void AddFavorateItem(){
	   FavoriteManager   mgr = FavoriteManager.GetInstance(this);
	   
	   boolean  exist = mgr.CheckFavoriteUserExist() ;
	   if(true == exist){
		   if(mgr.CheckUserIfExist(mCurrentItem.getM_sfz_no()) < 0){
			   mgr.AddUserItem(mCurrentItem.getM_sfz_no());
		   }
	   }
   }
	
	public static int getCurPage() {
		return mCurPage;
	}
	
	public static void setCurPage(int page) {
		mCurPage = page;
	}
	
	public static int getCurPageForTab(int tab) {
		return mCurPageOnTab[tab];
	}
	
	public static void setCurPageForTab(int tab, int page) {
		mCurPageOnTab[tab] = page;
	}

	private class  ViewPagerListener implements OnPageChangeListener{

		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			int page = mCurPageOnTab[arg0];
			gotoPage(arg0,page);
		}
		
	}
	
	public void gotoPage(int tab, int page) {
		mMainPager.setCurrentItem(page,false);
		mCurPage = page;
		mCurPageOnTab[tab] = page;
		
		setBottomImageview(tab);
		
	}
	public void setBottomImageview(int tab) {
		switch(tab) {
		case mPageShort:
			mTabShort.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry_h));
			mTabDetail.setImageDrawable(getResources().getDrawable(R.drawable.info_xx));
			mTabFamily.setImageDrawable(getResources().getDrawable(R.drawable.info_home));
			mTabResume.setImageDrawable(getResources().getDrawable(R.drawable.info_jl));
			break;
		case mPageDetail:
			mTabShort.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry));
			mTabDetail.setImageDrawable(getResources().getDrawable(R.drawable.info_xx_h));
			mTabFamily.setImageDrawable(getResources().getDrawable(R.drawable.info_home));
			mTabResume.setImageDrawable(getResources().getDrawable(R.drawable.info_jl));
			break;
		case mPageFamily:
			mTabShort.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry));
			mTabDetail.setImageDrawable(getResources().getDrawable(R.drawable.info_xx));
			mTabFamily.setImageDrawable(getResources().getDrawable(R.drawable.info_home_h));
			mTabResume.setImageDrawable(getResources().getDrawable(R.drawable.info_jl));
			break;
		case mPageResume:
			mTabShort.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry));
			mTabDetail.setImageDrawable(getResources().getDrawable(R.drawable.info_xx));
			mTabFamily.setImageDrawable(getResources().getDrawable(R.drawable.info_home));
			mTabResume.setImageDrawable(getResources().getDrawable(R.drawable.info_jl_h));
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mContactsMgr.Close();
	}

	public class ViewpagerItemOnClickListener implements View.OnClickListener {
		private int index = 0;

		public ViewpagerItemOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			int page = mCurPageOnTab[index];
			gotoPage(index,page);
		}
		
	}

	
	
}
