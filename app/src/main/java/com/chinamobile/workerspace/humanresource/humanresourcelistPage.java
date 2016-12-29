package com.chinamobile.workerspace.humanresource;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.ContactsListActivity;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.draw.CakeSurfaceView;
import com.chinamobile.workerspace.draw.CakeSurfaceView.Gravity;
import com.chinamobile.workerspace.statics.StaticsListAdapter;
import com.chinamobile.workerspace.statics.StaticsListItem;
import com.chinamobile.workerspace.utils.T;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;



public class humanresourcelistPage implements OnItemClickListener {

	    private static humanresourcelistPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		
		
		
		private int  statis_id ;
		
		private StaticsListAdapter  mSexAdapter ;
		private List<StaticsListItem> mSexListU ;
		private ListView mSexListView;
		
		
		private StaticsListAdapter  mAgeAdapter ;
		private List<StaticsListItem> mAgeListU ;
		private ListView mAgeListView;
		
		private StaticsListAdapter  mNationAdapter ;
		private List<StaticsListItem> mNationListU ;
		private ListView mNationListView;
		
		
		private StaticsListAdapter  mEduAdapter ;
		private List<StaticsListItem> mEduListU ;
		private ListView mEduListView;
		
		private StaticsListAdapter  mDepthAdapter ;
		private List<StaticsListItem> mDepthListU ;
		
		private Map<String,List<UserItem>>  m_mapsage ;
		private Map<String,List<UserItem>>  m_mapssex ;
		private Map<String,List<UserItem>>  m_mapsnation ;
		private Map<String,List<UserItem>>  m_mapsedu ;
		private Map<String,List<UserItem>>  m_mapsdepth ;
		
		private List<String>  m_typeage ;
		private List<String>  m_typesex ;
		private List<String>  m_typenation ;
		private List<String>  m_typeedu ;
		private List<String>  m_typedepth ;
		
		
		
		private ListView mDepthListView;
		
		
		private  String  mselected_group_id   ;
		
		public static humanresourcelistPage GetInstance(){
			if(mInstance ==null){
				mInstance = new humanresourcelistPage();
			}
			return mInstance ;
		}
		
		public humanresourcelistPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	
		 }
		
		 public void SetGroupId(String group_id){
			 mselected_group_id = group_id ;
		 }
		 
		 public void MakeDataSexs(Map<String,List<UserItem>>  maps , List<String> keys){
				mSexListU = new ArrayList<StaticsListItem>();	
				
				m_mapssex = maps ;
				m_typesex = keys;
				for(int i = 0 ; i < keys.size() ; i++){
					String key = keys.get(i);
					List<UserItem>  l = maps.get(key);
					
					StaticsListItem  listitem = new StaticsListItem();
					listitem.setTitle(key);
					listitem.setnCount(l.size());
					
					mSexListU.add(listitem);
				}
				
				mSexAdapter = new StaticsListAdapter(mContext0,mSexListU);
	    }
		 
		 public void MakeDataAge(Map<String,List<UserItem>>  maps , List<String> keys){
				mAgeListU = new ArrayList<StaticsListItem>();	
				m_mapsage = maps ;
				m_typeage = keys;
				for(int i = 0 ; i < keys.size() ; i++){
					String key = keys.get(i);
					List<UserItem>  l = maps.get(key);
					
					StaticsListItem  listitem = new StaticsListItem();
					listitem.setTitle(key);
					listitem.setnCount(l.size());
					
					mAgeListU.add(listitem);
				}
				mAgeAdapter = new StaticsListAdapter(mContext0,mAgeListU);
	    }
		 
		 public void MakeDataNation(Map<String,List<UserItem>>  maps , List<String> keys){
				mNationListU = new ArrayList<StaticsListItem>();	
				
				m_mapsnation = maps ;
				m_typenation = keys;
				
				
				for(int i = 0 ; i < keys.size() ; i++){
					String key = keys.get(i);
					List<UserItem>  l = maps.get(key);
					
					StaticsListItem  listitem = new StaticsListItem();
					listitem.setTitle(key);
					listitem.setnCount(l.size());
					
					mNationListU.add(listitem);
				}
				
				mNationAdapter = new StaticsListAdapter(mContext0,mNationListU);
	    }
		 
		 public void MakeDataEdu(Map<String,List<UserItem>>  maps , List<String> keys){
				mEduListU = new ArrayList<StaticsListItem>();	
				
				m_mapsedu = maps ;
				m_typeedu = keys;
				
				for(int i = 0 ; i < keys.size() ; i++){
					String key = keys.get(i);
					List<UserItem>  l = maps.get(key);
					
					StaticsListItem  listitem = new StaticsListItem();
					listitem.setTitle(key);
					listitem.setnCount(l.size());
					
					mEduListU.add(listitem);
				}
				mEduAdapter = new StaticsListAdapter(mContext0,mEduListU);
	    }
		 
		 public void MakeDataDepth(Map<String,List<UserItem>>  maps , List<String> keys){
				mDepthListU = new ArrayList<StaticsListItem>();	
				m_mapsdepth = maps ;
				m_typedepth = keys;
				for(int i = 0 ; i < keys.size() ; i++){
					String key = keys.get(i);
					List<UserItem>  l = maps.get(key);
					
					StaticsListItem  listitem = new StaticsListItem();
					listitem.setTitle(key);
					listitem.setnCount(l.size());
					
					mDepthListU.add(listitem);
				}
				mDepthAdapter = new StaticsListAdapter(mContext0,mDepthListU);
	    }
		 
		 public void OnCreate(){
		    
			 mSexListView = (ListView)mViewOfPage.findViewById(R.id.list_view_sex);
			 mAgeListView = (ListView)mViewOfPage.findViewById(R.id.list_view_age);
			 mNationListView = (ListView)mViewOfPage.findViewById(R.id.list_view_nation);
			 mEduListView = (ListView)mViewOfPage.findViewById(R.id.list_view_edu);
			 mDepthListView = (ListView)mViewOfPage.findViewById(R.id.list_view_level);
			 

			 mAgeListView.setAdapter(mAgeAdapter);
			 mSexListView.setAdapter(mSexAdapter);
			 mNationListView.setAdapter(mNationAdapter);
			 mEduListView.setAdapter(mEduAdapter);
			 mDepthListView.setAdapter(mDepthAdapter);
			 
			 
			 setListViewHeightBasedOnChildren(mSexListView);
			 setListViewHeightBasedOnChildren(mAgeListView);
			 setListViewHeightBasedOnChildren(mNationListView);
			 setListViewHeightBasedOnChildren(mEduListView);
			 setListViewHeightBasedOnChildren(mDepthListView);
			 
			 mAgeListView.setOnItemClickListener(this);
			 mSexListView.setOnItemClickListener(this);
			 mNationListView.setOnItemClickListener(this);
			 mEduListView.setOnItemClickListener(this);
			 mDepthListView.setOnItemClickListener(this);
			 
		 }
		 private  void setListViewHeightBasedOnChildren(ListView listView) {
			    ListAdapter listAdapter = listView.getAdapter();
			    if (listAdapter == null) {
			     return;
			    }
			    int totalHeight = 0;
			    for (int i = 0; i < listAdapter.getCount(); i++) {
			     View listItem = listAdapter.getView(i, null, listView);
			     listItem.measure(0, 0);
			     totalHeight += listItem.getMeasuredHeight();
			    }
			   
			    ViewGroup.LayoutParams params = listView.getLayoutParams();
			    
			    params.height = totalHeight
			      + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
			    listView.setLayoutParams(params);
			 }

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
				
			
			switch(arg0.getId()){
			case R.id.list_view_sex:
				GoActivity(arg2,mSexListU.get(arg2).getTitle(),0,m_typesex,m_mapssex);
				break;
			case R.id.list_view_age:
				GoActivity(arg2,mAgeListU.get(arg2).getTitle(),1,m_typeage,m_mapsage);
			    break;
			case R.id.list_view_nation:
				GoActivity(arg2,mNationListU.get(arg2).getTitle(),2,m_typenation,m_mapsnation);
				break;
			case R.id.list_view_edu:
				GoActivity(arg2,mEduListU.get(arg2).getTitle(),3,m_typeedu,m_mapsedu);
				break;
			case R.id.list_view_level:
				GoActivity(arg2,mDepthListU.get(arg2).getTitle(),4,m_typedepth,m_mapsdepth);
				break;
				default:
					break;
			}
		}
       
        private void GoActivity(int arg2,String keys,int category,List<String> catalog, Map<String,List<UserItem>>  maps ){
			
			
			Intent  frqueui = new Intent();


			String key = catalog.get(arg2);
			List<UserItem>  l = maps.get(key);
			
			if((l != null)&&(l.size() > 0)){
			frqueui.putExtra("birthdate", l.get(0).getM_csrq());
			//frqueui.putExtra("org_id", l.get(0).getGroupId());
			frqueui.putExtra("group_id", mselected_group_id);
			frqueui.putExtra("category", category);
			frqueui.putExtra("category_keys", keys);
			frqueui.setClass(mContext0,ContactsListActivity.class);
			frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			mContext0.startActivity(frqueui);	

			
			Log.d("GoActivity", "birthdate:"+l.get(0).getM_csrq()+"arg2:"+arg2);
			
			((Activity)mContext0).overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
			}else{
				T.showShort(mContext0, "No infomation to show");
			}
		
		}
}
