package com.chinamobile.workerspace.statics;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;







import com.chinamobile.workerspace.HistoryType;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.db.DictManager;
import com.chinamobile.workerspace.favorite.FavorateActivity;
import com.chinamobile.workerspace.favorite.FavoriteManager;
import com.chinamobile.workerspace.group.GroupManager;
import com.chinamobile.workerspace.history.HistoryManager;
import com.chinamobile.workerspace.ui.CustomMainBodyView;
import com.chinamobile.workerspace.utils.T;






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
import android.view.View;
import android.widget.ImageView;

public class Statics_containerActivity extends Activity {

	private static int mCurPage;
	private static int mCurPageOnTab[];
	
	/* page definition */
	public static final int mPagePie = 0;
	public static final int mPageBar = 1;
	public static final int mPageList = 2;
	
	
	/* tab number definition */
	public static final int mTabNumPie = 0;
	public static final int mTabNumBar = 1;
	public static final int mTabNumList = 2;
	
	
	
	
	private CustomMainBodyView mMainPager;
	private ViewPagerListener  mViewListener;
	private ImageView mTabPie;
	private ImageView mTabBar;
	private ImageView mTabList;
	
	
	private View  mPiePanel;
	private View  mBarPanel;
	private View  mListPanel;
	
	private ArrayList<View> listOfViews ;
	
	
	private ContactsManager   mContactsMgr ;
	private GroupManager   mgroupMgr ;
	private HistoryManager  mHistoryMgr ;
	private DictManager  mDict ;  
	private int  mShowCategory ;
	
	private Map<String,List<UserItem>>  minfos = new HashMap<String,List<UserItem>>();
	
	private List<String>  mkeys ;
	
	List<UserItem> mList = new ArrayList<UserItem>() ;
	
	private List<String>  GenerateKeys(int id){
		
		List<String> keys = new ArrayList<String>();
		
		switch(id){
		case 0:
			keys.add("男");
			keys.add("女");
			break;
		case 1:
			break;
			
			default:
				break;
		}
		
		return keys;
	}
	
	
	private void GenerateMapDatasbySex(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			List<UserItem> t =  GetUsersBySex(list.get(i));  //mContactsMgr.GetUserBySex(list.get(i));
			minfos.put(list.get(i), t);
		}
	}
	
	private void GenerateMapDatasbyAge(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			List<UserItem> t =   GetUsersByAge(i); //mContactsMgr.GetUserByAge(i);
			minfos.put(list.get(i), t);
			
		}
	}

	private List<UserItem>  GetUsersByNation(String nation){
		   
		   List<UserItem>  list = new ArrayList<UserItem>();
		  
		  for(int i = 0 ; i < mList.size() ; i++){
			  
			  if(mList.get(i).getM_mz().indexOf(nation) != -1){
				  list.add(mList.get(i));
			  }
			  
		  }

		   return list ;
		   
	   }
	
	  private List<UserItem>  GetUsersByLevel(String level){
		   
		   List<UserItem>  list = new ArrayList<UserItem>();
		  
		  for(int i = 0 ; i < mList.size() ; i++){
			  
			  if(mList.get(i).getM_xrzj().equals(level) == true){
				  list.add(mList.get(i));
			  }
			  
		  }

		   return list ;
		   
	   }
	
    private void GenerateMapDatasbyNation(List<String>  list){
		
		for(int i =0 ; i < list.size()-1 ; i++){
			
			String name = mDict.GetMcbylbandbm("mz", list.get(i)) ;		
			mkeys.add(name);
			List<UserItem> t = GetUsersByNation(name);
			minfos.put(name, t);
			
		}
		
		mkeys.add("其他");
		minfos.put("其他", mContactsMgr.GetOtherNations());
	}

	
    private void GenerateMapDatasbyEdu(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			
			List<UserItem> t = null ;
			if(list.get(i).equals("高中")){
				t = mContactsMgr.GetUserByEdu(null);
			}else if(list.get(i).equals("中专")){
				t = mContactsMgr.GetUserByEdu("05");
			}else if(list.get(i).equals("专科")){
				t = mContactsMgr.GetUserByEdu("04");
			}else if(list.get(i).equals("本科")){
				t = mContactsMgr.GetUserByEdu("03");
			}else if(list.get(i).equals("硕士")){
				t = mContactsMgr.GetUserByEdu("02");
			}else if(list.get(i).equals("博士")){
				t = mContactsMgr.GetUserByEdu("01");
			}
			
			
			minfos.put(list.get(i), t);
		}
	}
    
   
    private void GenerateMapDatasbyDepth(List<String>  list){
    	int i =0 ;
		for(i =0 ; i < list.size()-1 ; i++){
			
			
			List<UserItem> t = GetUsersByLevel(list.get(i));
			
			/*
			if(list.get(i).equals("科员")){
				t = mContactsMgr.GetUserByDepth("21");
			}else if(list.get(i).equals("副科")){		
				t = mContactsMgr.GetUserByDepth("19");			
				t1 = mContactsMgr.GetUserByDepth("20");				
				t.addAll(t1);
			
			}else if(list.get(i).equals("正科")){
				t = mContactsMgr.GetUserByDepth("17");
				
				t1 = mContactsMgr.GetUserByDepth("18");			
				t.addAll(t1);
				
			}else if(list.get(i).equals("副县")){
				t = mContactsMgr.GetUserByDepth("15");
				
				t1 = mContactsMgr.GetUserByDepth("16");
				
				t.addAll(t1);
			}else if(list.get(i).equals("正县")){
				t = mContactsMgr.GetUserByDepth("13");
				
				t1 = mContactsMgr.GetUserByDepth("14");
				
				t.addAll(t1);
			}*/
			
			minfos.put(list.get(i), t);
		}
		
		minfos.put(list.get(i), mContactsMgr.GetUserbyotherdepth());
	}
    
    private void GenerateMapDatasbyStatus(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			List<UserItem> t = mContactsMgr.GetUserByStatus(list.get(i));
			minfos.put(list.get(i), t);
		}
	}
    
     private void GenerateMapDatasbyorg(List<GroupItem> list){
		
    	 
    	 
    	// Log.d("container_statics", "count:"+list.size());
		for(int i =0 ; i < list.size() ; i++){
			List<UserItem> t = mContactsMgr.GetUserByGroupID(list.get(i).getGroup_id());
			minfos.put(list.get(i).getGroup_name(), t);
			mkeys.add(list.get(i).getGroup_name());
		}
	}
 

	
	private void  MakeProducts(int id){
		
		mkeys = new ArrayList<String>();
		
		switch(id){
		case 0:   //sex
			mkeys.add("男");
			mkeys.add("女");
			GenerateMapDatasbySex(mkeys);
			break;
		case 1:   //age
			mkeys.add("35岁以下");
			mkeys.add("35岁-40岁");
			mkeys.add("40岁-50岁");
			mkeys.add("50岁-55岁");
			mkeys.add("55岁以上");
			GenerateMapDatasbyAge(mkeys);
			break;
		case 2:  //nation
			
			
			
			List<String> keys = new ArrayList<String>();
			keys.add("01");  //han
			keys.add("04");  //hui
			keys.add("03");  //man
			keys.add("02");  //zhuang
			keys.add("14");  //朝鲜
			keys.add("00"); 
			
			GenerateMapDatasbyNation(keys);
			break;
		case 3:  //edu
			//mkeys = mContactsMgr.GetAllEduList();
			
			mkeys.add("高中");
			mkeys.add("中专");
			mkeys.add("专科");
			mkeys.add("本科");
			mkeys.add("硕士");
			mkeys.add("博士");
			GenerateMapDatasbyEdu(mkeys);
			break;
		case 4:   //depth
			//mkeys = mContactsMgr.GetAllEduList();
			/*mkeys.add("科员");
			mkeys.add("副科");
			mkeys.add("正科");*/
			mkeys.add("副县");
			mkeys.add("正县");
			mkeys.add("副县级");
			mkeys.add("正县级");
			mkeys.add("副地及以上");
			GenerateMapDatasbyDepth(mkeys);
			break;
		case 5:
			mkeys.add("在职");
			mkeys.add("离岗");
			mkeys.add("退（离）休");
			mkeys.add("其他");
			GenerateMapDatasbyStatus(mkeys);
			break;
		case 6:   //org
			
			GenerateMapDatasbyorg(mContactsMgr.GetAllGroupList());
			break;
			default:
				break;
		}
	}
	
	 private List<UserItem>  GetUsersBySex(String sex){
		   
		   List<UserItem>  list = new ArrayList<UserItem>();
		  
		  for(int i = 0 ; i < mList.size() ; i++){
			  
			  if(mList.get(i).getM_xb().indexOf(sex) != -1){
				  list.add(mList.get(i));
			  }
			  
		  }

		   return list ;
		   
	   }
	 
	 private List<UserItem>  GetUsersByAge(int type){
		   
		   List<UserItem>  list = new ArrayList<UserItem>();
		  
		  for(int i = 0 ; i < mList.size() ; i++){
			  
			  if(type == mContactsMgr.GetAgeType(mList.get(i))){
				  list.add(mList.get(i));
			  }
			  
		  }

		   return list ;
		   
	   }
	 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total_statics_viewpager_container);
		
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
		
		mContactsMgr = ContactsManager.GetInstance(this);
		
		mgroupMgr = GroupManager.GetInstance(this) ;
		mDict = DictManager.GetInstance(this);
		mShowCategory = Integer.valueOf(getIntent().getStringExtra("category"))  ;
		
		//mkeys = GenerateKeys(mShowCategory);
		
		mList = mContactsMgr.GetAllUsers();
		
		MakeProducts(mShowCategory) ;
		String title = String.format("%s", this.getString(StaticsPieChartPage.static_titles[mShowCategory]));
		
		mHistoryMgr = HistoryManager.GetInstance(this);
		mHistoryMgr.InsertOrUpdateHistory(title, String.valueOf(mShowCategory), HistoryType.QUERY_STATIC);
		
		
		int departmentcount = mgroupMgr.GetAllGroupsCount() ;
		ab.setTitle(String.format("%s(参与统计单位:%d)", title,departmentcount));
		
		mCurPage = mPagePie;
		mCurPageOnTab = new int[3];
		mCurPageOnTab[mTabNumPie] = mPagePie;
		mCurPageOnTab[mTabNumBar] = mPageBar;
		mCurPageOnTab[mTabNumList] = mPageList;
		
		
		mMainPager = (CustomMainBodyView)findViewById(R.id.total_statics_container_tabpager);
		
		mViewListener = new ViewPagerListener();
		mMainPager.setOnPageChangeListener(mViewListener);
		
		mTabPie = (ImageView)findViewById(R.id.total_statics_container_pie);
		mTabBar = (ImageView)findViewById(R.id.total_statics_container_bar);
		mTabList = (ImageView)findViewById(R.id.total_statics_container_list);
		
		
		mPiePanel = (View)findViewById(R.id.pie_container);
		mBarPanel = (View)findViewById(R.id.bar_container);
		mListPanel = (View)findViewById(R.id.list_container);
		
		
		mPiePanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumPie));
		mBarPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumBar));
		mListPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumList));
		
		
		mTabPie.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumPie));
		mTabBar.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumBar));
		mTabList.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumList));
		
		
		LayoutInflater li = LayoutInflater.from(this);
		View viewPiechart = li.inflate(R.layout.statics_piechart_view, null);
		
		View viewBarchart = li.inflate(R.layout.statics_barchart_view, null);
		
		View viewListchart = li.inflate(R.layout.statics_list_view, null);
		
		listOfViews = new ArrayList<View>();
        listOfViews.add(viewPiechart);
        listOfViews.add(viewBarchart);
        listOfViews.add(viewListchart);
        
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
        
        if(minfos.size() == 0){
        	T.showShort(this, "no data to show");
        	return ;
        }
        
        StaticsPieChartPage.GetInstance().OnInit(viewPiechart, this);
        
        StaticsPieChartPage.GetInstance().MakeDatas(minfos,mkeys);
        
        StaticsPieChartPage.GetInstance().SetTitle(mShowCategory,departmentcount);
        StaticsPieChartPage.GetInstance().OnCreate();
        
        StaticsBarChartPage.GetInstance().OnInit(viewBarchart, this);
        StaticsBarChartPage.GetInstance().MakeDatas(minfos,mkeys);
        StaticsBarChartPage.GetInstance().SetTitle(mShowCategory,departmentcount);
        StaticsBarChartPage.GetInstance().OnCreate();
        
        StaticsListPage.GetInstance().OnInit(viewListchart, this);
        StaticsListPage.GetInstance().MakeDatas(minfos,mkeys);
        StaticsListPage.GetInstance().SetTitle(mShowCategory);
        StaticsListPage.GetInstance().OnCreate();
        
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
		case mPagePie:
			mTabPie.setImageDrawable(getResources().getDrawable(R.drawable.stat_bzt_h));
			mTabBar.setImageDrawable(getResources().getDrawable(R.drawable.stat_zzt));
			mTabList.setImageDrawable(getResources().getDrawable(R.drawable.stat_wzlb));
			StaticsPieChartPage.GetInstance().Repaint();
			break;
		case mPageBar:
			mTabPie.setImageDrawable(getResources().getDrawable(R.drawable.stat_bzt));
			mTabBar.setImageDrawable(getResources().getDrawable(R.drawable.stat_zzt_h));
			mTabList.setImageDrawable(getResources().getDrawable(R.drawable.stat_wzlb));
			StaticsBarChartPage.GetInstance().Repaint();
			break;
		case mPageList:
			mTabPie.setImageDrawable(getResources().getDrawable(R.drawable.stat_bzt));
			mTabBar.setImageDrawable(getResources().getDrawable(R.drawable.stat_zzt));
			mTabList.setImageDrawable(getResources().getDrawable(R.drawable.stat_wzlb_h));
			break;
		default:
			break;
		}
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mContactsMgr.Close();
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater(); 
		
		//inflater.inflate(R.menu.onlymain, menu);  
		return true;
	}


	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case android.R.id.home:  
			finish();
			break;
		default:
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
