package com.chinamobile.workerspace.humanresource;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;










import com.chinamobile.workerspace.HistoryType;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.contacts.ContactslistPage;
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

public class humanresource_containerActivity extends Activity {

	private static int mCurPage;
	private static int mCurPageOnTab[];
	
	/* page definition */
	
	public static final int mPagePerson = 0;
	public static final int mPagePie = 1;
	public static final int mPageBar = 2;
	public static final int mPageList = 3;
	
	
	/* tab number definition */
	public static final int mTabNumPerson = 0;
	public static final int mTabNumPie = 1;
	public static final int mTabNumBar = 2;
	public static final int mTabNumList = 3;
	
	
	private CustomMainBodyView mMainPager;
	private ViewPagerListener  mViewListener;
	private ImageView mTabPerson;
	private ImageView mTabPie;
	private ImageView mTabBar;
	private ImageView mTabList;
	
	
	private View  mPersonPanel;
	private View  mPiePanel;
	private View  mBarPanel;
	private View  mListPanel;
	
	private ArrayList<View> listOfViews ;
   private ContactsManager   mContactsMgr ;
	
   
   private List<UserItem>  mList = null;
   
   
    private Map<String,List<UserItem>>  minfos_sex = new HashMap<String,List<UserItem>>();
    
    private Map<String,List<UserItem>>  minfos_age = new HashMap<String,List<UserItem>>();
    
    private Map<String,List<UserItem>>  minfos_nation = new HashMap<String,List<UserItem>>();
    
    private Map<String,List<UserItem>>  minfos_edu = new HashMap<String,List<UserItem>>();
    
    private Map<String,List<UserItem>>  minfos_depth = new HashMap<String,List<UserItem>>();
	
    private HistoryManager  mHistoryMgr ;
    private GroupManager    mGroupmgr;
    private DictManager  mDict ;  
    
    private List<String> mkeys_age = new ArrayList<String>();
    
    private List<String> mkeys_sex = new ArrayList<String>();
    
     private List<String> mkeys_nation = new ArrayList<String>();
    
    private List<String> mkeys_edu = new ArrayList<String>();
    
   private List<String> mkeys_depth = new ArrayList<String>();
    

   private  String  mselected_group_id   ;
	
   
   private List<UserItem>  GetUsersBySex(String sex){
	   
	   List<UserItem>  list = new ArrayList<UserItem>();
	  
	  for(int i = 0 ; i < mList.size() ; i++){
		  
		  if(mList.get(i).getM_xb().indexOf(sex) != -1){
			  list.add(mList.get(i));
		  }
		  
	  }

	   return list ;
	   
   }
   
   private void GenerateMapDatasbySex(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			mkeys_sex.add(list.get(i));
			List<UserItem> t = GetUsersBySex(list.get(i));  //mContactsMgr.GetUserBySexandGroupId(list.get(i),mselected_group_id);
			minfos_sex.put(list.get(i), t);
		}
		
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
   
   private void GenerateMapDatasbyAge(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			mkeys_age.add(list.get(i));
			List<UserItem> t =  GetUsersByAge(i);   //mContactsMgr.GetUserByAgeandGroupId(i,mselected_group_id);
			minfos_age.put(list.get(i), t);
			//Log.d("yulei age", list.get(i)+ " size:"+t.size());
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
   
   private void GenerateMapDatasbyNation(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			
			String name = mDict.GetMcbylbandbm("mz", list.get(i)) ;
			mkeys_nation.add(name);
			List<UserItem> t = GetUsersByNation(name); //mContactsMgr.GetUserByNationandGroupId(list.get(i),mselected_group_id);
			minfos_nation.put(name, t);
		}
	}

	
   private void GenerateMapDatasbyEdu(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			mkeys_edu.add(list.get(i));
			
			List<UserItem> t = null ;
			if(list.get(i).equals("高中")){
				t = mContactsMgr.GetUserByEduandGroupId(null,mselected_group_id);
			}else if(list.get(i).equals("中专")){
				t = mContactsMgr.GetUserByEduandGroupId("05",mselected_group_id);
			}else if(list.get(i).equals("专科")){
				t = mContactsMgr.GetUserByEduandGroupId("04",mselected_group_id);
			}else if(list.get(i).equals("本科")){
				t = mContactsMgr.GetUserByEduandGroupId("03",mselected_group_id);
			}else if(list.get(i).equals("硕士")){
				t = mContactsMgr.GetUserByEduandGroupId("02",mselected_group_id);
			}else if(list.get(i).equals("博士")){
				t = mContactsMgr.GetUserByEduandGroupId("01",mselected_group_id);
			}
			
			
			minfos_edu.put(list.get(i), t);
		}
		
	}
   
  
   private void GenerateMapDatasbyDepth(List<String>  list){
		
		for(int i =0 ; i < list.size() ; i++){
			mkeys_depth.add(list.get(i));
			
			List<UserItem> t = new ArrayList<UserItem>() ;
			List<UserItem> t1 = null ;
			
			for(int j = 0 ; j < mList.size() ; j++){
				  
				  if(mList.get(j).getM_xrzj().indexOf(list.get(i)) != -1){
					  t.add(mList.get(j));
				  }
				  
			 }
			
			/*if(list.get(i).equals("科员")){
				t = mContactsMgr.GetUserByDepthandGroupId("21",mselected_group_id);
			}else if(list.get(i).equals("副科")){
				
				t = mContactsMgr.GetUserByDepthandGroupId("19",mselected_group_id);
				
				t1 = mContactsMgr.GetUserByDepthandGroupId("20",mselected_group_id);
				
				t.addAll(t1);
				
				
			}else if(list.get(i).equals("正科")){
				t = mContactsMgr.GetUserByDepthandGroupId("17",mselected_group_id);
				
				t1 = mContactsMgr.GetUserByDepthandGroupId("18",mselected_group_id);
				
				t.addAll(t1);
				
			}else if(list.get(i).equals("副县")){
				t = mContactsMgr.GetUserByDepthandGroupId("15",mselected_group_id);
				
				t1 = mContactsMgr.GetUserByDepthandGroupId("16",mselected_group_id);
				
				t.addAll(t1);
			}else if(list.get(i).equals("正县")){
				t = mContactsMgr.GetUserByDepthandGroupId("13",mselected_group_id);
				
				t1 = mContactsMgr.GetUserByDepthandGroupId("14",mselected_group_id);
				
				t.addAll(t1);
			}*/
			
			//Log.d("GenerateMapDatasbyDepth ", list.get(i)+" size:"+t.size());
			
			minfos_depth.put(list.get(i), t);
		}
	}
   
   

	
	private void  MakeProducts(int id){
		
		List<String> keys = new ArrayList<String>();
		
		
		switch(id){
		case 0:   //sex
			keys.add("男");
			keys.add("女");
			GenerateMapDatasbySex(keys);
			break;
		case 1:   //age
			keys.add("35岁以下");
			keys.add("35岁-40岁");
			keys.add("40岁-50岁");
			keys.add("50岁-55岁");
			keys.add("55岁以上");
			GenerateMapDatasbyAge(keys);
			break;
		case 2:  //nation
			keys.add("01");  //han
			keys.add("04");  //hui
			keys.add("03");  //man
			keys.add("02");  //zhuang
			keys.add("14");  //朝鲜
			//keys.add("99");  //朝鲜
			
			GenerateMapDatasbyNation(keys);
			break;
		case 3:  //edu
			
			keys.add("高中"); //06
			keys.add("中专"); //05
			keys.add("专科"); //04
			keys.add("本科"); //03
			keys.add("硕士"); //02
			keys.add("博士"); //01
			
			
			GenerateMapDatasbyEdu(keys);
			break;
		case 4:   //depth
			keys.add("科员");
			keys.add("副科");
			keys.add("正科");
			keys.add("副县");
			keys.add("正县");
			GenerateMapDatasbyDepth(keys);
			break;
		case 5:
			break;
		case 6:   //org
			break;
			default:
				break;
		}
	}
   
   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.humanresource__container);
		
		
		mGroupmgr = GroupManager.GetInstance(this);
		
		mDict = DictManager.GetInstance(this);
		mselected_group_id = getIntent().getStringExtra("group_id"); //
		
		mHistoryMgr = HistoryManager.GetInstance(this);
		
		
		GroupItem groupitem = mGroupmgr.GetGroupItemByID(mselected_group_id);
		
		String title = groupitem.getGroup_name();
		String  item_id = groupitem.getGroup_id() ;
		
		mHistoryMgr.InsertOrUpdateHistory(title, item_id, HistoryType.QUERY_GROUP);
		
		
		
		
		mCurPage = mPagePie;
		mCurPageOnTab = new int[4];
		mCurPageOnTab[mTabNumPerson] = mPagePerson;
		mCurPageOnTab[mTabNumPie] = mPagePie;
		mCurPageOnTab[mTabNumBar] = mPageBar;
		mCurPageOnTab[mTabNumList] = mPageList;
		
		
		mMainPager = (CustomMainBodyView)findViewById(R.id.humanresource_main_container_tabpager);
		
		mViewListener = new ViewPagerListener();
		mMainPager.setOnPageChangeListener(mViewListener);
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
		
	   
        ab.setHomeButtonEnabled(true);
		
		
		mTabPerson = (ImageView)findViewById(R.id.humanresource_container_person);
		mTabPie = (ImageView)findViewById(R.id.humanresource_container_pie);
		mTabBar = (ImageView)findViewById(R.id.humanresource_container_bar);
		mTabList = (ImageView)findViewById(R.id.humanresource_container_list);
		
		mPersonPanel = (View)findViewById(R.id.humanresource_person_panel);
		mPiePanel = (View)findViewById(R.id.humanresource_pie_panel);
		mBarPanel = (View)findViewById(R.id.humanresource_bar_panel);
		mListPanel = (View)findViewById(R.id.humanresource_list_panel);
		
		mPersonPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumPerson));
		mPiePanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumPie));
		mBarPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumBar));
		mListPanel.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumList));
		
		mTabPerson.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumPerson));
		mTabPie.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumPie));
		mTabBar.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumBar));
		mTabList.setOnClickListener(new ViewpagerItemOnClickListener(mTabNumList));
		
		
		LayoutInflater li = LayoutInflater.from(this);	
		View viewPersonlistchart = li.inflate(R.layout.contacts_list, null);
		View viewPiechart = li.inflate(R.layout.humanresource_piechart_view, null);
		View viewBarchart = li.inflate(R.layout.humanresource_barchart_view, null);
		View viewListchart = li.inflate(R.layout.word_list_view, null);
			
		
		
		
		listOfViews = new ArrayList<View>();
		listOfViews.add(viewPersonlistchart);
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
        
        
        mContactsMgr = ContactsManager.GetInstance(this);
		
        
        
		List<UserItem> list =   mContactsMgr.GetUserByGroupID(mselected_group_id);   // mContactsMgr.GetAllUsers();
		
		mList = list;
		//Log.d("humanresource", "UserItem size:"+list.size());
		
		String title1 = String.format("%s(人员共: %d 人)", title,list.size());
   
		ab.setTitle(title1);
		
		
		MakeProducts(0); //sex
		
		MakeProducts(1); //age
		
		MakeProducts(2); //nation
		
		MakeProducts(3); //edu
		
		MakeProducts(4); //depth
	
		
		
		
        ContactslistPage.GetInstance().OnInit(viewPersonlistchart, this);
        ContactslistPage.GetInstance().SetData(list);
        ContactslistPage.GetInstance().OnCreate(ab);
        ContactslistPage.GetInstance().OnlyShowList();
        
        humanresourcelistPage.GetInstance().OnInit(viewListchart, this);
        humanresourcelistPage.GetInstance().OnCreate();
        
        humanresourcePieChartPage.GetInstance().OnInit(viewPiechart, this);
        humanresourcePieChartPage.GetInstance().MakeDataDepth(minfos_depth);
        humanresourcePieChartPage.GetInstance().MakeDataAge(minfos_age);
        humanresourcePieChartPage.GetInstance().MakeDataSexs(minfos_sex);
        humanresourcePieChartPage.GetInstance().MakeDataNation(minfos_nation);
        humanresourcePieChartPage.GetInstance().MakeDataEdu(minfos_edu);
        humanresourcePieChartPage.GetInstance().OnCreate();
        
        
        
        humanresourceBarChartPage.GetInstance().OnInit(viewBarchart, this);
        humanresourceBarChartPage.GetInstance().MakeDataAge(minfos_age);
        humanresourceBarChartPage.GetInstance().MakeDataDepth(minfos_depth);
        humanresourceBarChartPage.GetInstance().MakeDataSexs(minfos_sex);
        humanresourceBarChartPage.GetInstance().MakeDataNation(minfos_nation);
        humanresourceBarChartPage.GetInstance().MakeDataEdu(minfos_edu);
        humanresourceBarChartPage.GetInstance().OnCreate();
        
        
        humanresourcelistPage.GetInstance().OnInit(viewListchart, this);
        humanresourcelistPage.GetInstance().MakeDataAge(minfos_age,mkeys_age);
         humanresourcelistPage.GetInstance().MakeDataDepth(minfos_depth,mkeys_depth);
        humanresourcelistPage.GetInstance().MakeDataSexs(minfos_sex,mkeys_sex);
        humanresourcelistPage.GetInstance().MakeDataNation(minfos_nation,mkeys_nation);
        humanresourcelistPage.GetInstance().MakeDataEdu(minfos_edu,mkeys_edu);
        humanresourcelistPage.GetInstance().SetGroupId(mselected_group_id);
        humanresourcelistPage.GetInstance().OnCreate();
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
		case mPagePerson:
			mTabPerson.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry_h));
			mTabPie.setImageDrawable(getResources().getDrawable(R.drawable.stat_bzt));
			mTabBar.setImageDrawable(getResources().getDrawable(R.drawable.stat_zzt));
			mTabList.setImageDrawable(getResources().getDrawable(R.drawable.stat_wzlb));
			break;
		case mPagePie:
			mTabPerson.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry));
			mTabPie.setImageDrawable(getResources().getDrawable(R.drawable.stat_bzt_h));
			mTabBar.setImageDrawable(getResources().getDrawable(R.drawable.stat_zzt));
			mTabList.setImageDrawable(getResources().getDrawable(R.drawable.stat_wzlb));
			humanresourcePieChartPage.GetInstance().Repaint();
			break;
		case mPageBar:
			mTabPerson.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry));
			mTabPie.setImageDrawable(getResources().getDrawable(R.drawable.stat_bzt));
			mTabBar.setImageDrawable(getResources().getDrawable(R.drawable.stat_zzt_h));
			mTabList.setImageDrawable(getResources().getDrawable(R.drawable.stat_wzlb));
			humanresourceBarChartPage.GetInstance().Repaint();
			break;
		case mPageList:
			mTabPerson.setImageDrawable(getResources().getDrawable(R.drawable.stat_ry));
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
		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater(); 
		
		inflater.inflate(R.menu.main, menu);  
		return true;
	}


	private void GoFavorateActivity(){
		Intent  frqueui = new Intent();
		
		frqueui.putExtra("start_flag", 1);
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
		default:
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	   private void AddFavorateItem(){
		   FavoriteManager   mgr = FavoriteManager.GetInstance(this);
		   
		   boolean  exist = mgr.CheckFavoriteGrouprExist() ;
		   if(true == exist){
			   if(mgr.CheckGroupIfExist(mselected_group_id) < 0){
				   mgr.AddGroupItem(mselected_group_id);
			   }
		   }
	   }
}
