package com.chinamobile.workerspace.history;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.chinamobile.workerspace.HistoryType;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.adapter.FavorateUserListAdapter;
import com.chinamobile.workerspace.adapter.HistoryListAdapter;
import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.favorite.MyDeleteCallback;
import com.chinamobile.workerspace.humanresource.humanresource_containerActivity;
import com.chinamobile.workerspace.resume.resume_containerActivity;
import com.chinamobile.workerspace.statics.Statics_containerActivity;
import com.chinamobile.workerspace.utils.T;
import com.chinamobile.workspace.ui.swipelistview.BaseSwipeListViewListener;
import com.chinamobile.workspace.ui.swipelistview.SwipeListView;

public class HistoryActivity extends Activity implements MyDeleteCallback {

	
	

	
	private ContactsManager   mContactsMgr ;
	

	
	private ArrayList<UserItem> mListUser = new ArrayList<UserItem>();
	
	private ArrayList<GroupItem> mListGroup = new ArrayList<GroupItem>();
	

	
	private SwipeListView  mSwipeListView = null;
	
	private HistoryManager  mHisMgr ; 
	
	private ArrayList<HistoryItem>  mList = new ArrayList<HistoryItem>();
	
	private HistoryListAdapter   mAdapter ; 
	
	private int getDeviceWidth() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.history_listview);
		
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	       ab.setHomeButtonEnabled(true);
		
		mContactsMgr = ContactsManager.GetInstance(this);
		
		mHisMgr = HistoryManager.GetInstance(this);
		
		mSwipeListView = (SwipeListView)findViewById(R.id.history_main_listview);
		int deviceWidth = getDeviceWidth();
    	mSwipeListView.setOffsetLeft(deviceWidth * 2/ 3);
    	mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);   
    	
    	mList = (ArrayList<HistoryItem>) mHisMgr.GetAllHistorys();
    	
    	
		if((mList == null)||(mList.size() == 0) ){
			T.showShort(this, R.string.no_query_record);
			
		}else{
			mAdapter = new HistoryListAdapter(this,mList);
			mAdapter.SetDeleteCallBack(this);
			
			mSwipeListView.setAdapter(mAdapter);
			mSwipeListView.setSwipeListViewListener(mSwipeListViewListener);
		}
		
	}	
	

	private void GotoActivity(HistoryItem item){
	    Intent  mainui = new Intent();
	    
	    if(item.getItem_type() == HistoryType.QUERY_GROUP ){
			
	    	
	    	mainui.putExtra("group_id", item.getItem_id());
	    	mainui.setClass(this,humanresource_containerActivity.class);
			
		}else if(item.getItem_type() == HistoryType.QUERY_USER){
			mainui.putExtra("user_id", item.getItem_id());
			mainui.setClass(this,resume_containerActivity.class);
		}else if(item.getItem_type() == HistoryType.QUERY_STATIC){
		
	    	mainui.setClass(this,Statics_containerActivity.class);
			mainui.putExtra("category", item.getItem_id());
			
		}
		
		this.startActivity(mainui);		
   }
	
	BaseSwipeListViewListener mSwipeListViewListener = new BaseSwipeListViewListener() {
		@Override
		public void onClickFrontView(int position) {
			HistoryItem item = mList.get(position);
			GotoActivity(item);
		}

		@Override
		public void onClickBackView(int position) {
			
			mSwipeListView.closeOpenedItems();// 鍏抽棴鎵撳紑鐨勯」
		}
	};


	@Override
	public void DeleteItem(int pos,int type) {
		// TODO Auto-generated method stub
		HistoryItem item = mList.get(pos);
		
		mList.remove(pos);
		
		mHisMgr.DeleteHistoryItem(item.getHistory_id());
		
		mAdapter.notifyDataSetChanged();			
		mSwipeListView.closeOpenedItems();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater(); 
		
		inflater.inflate(R.menu.withclear, menu);  
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case android.R.id.home:  
			finish();
			break;
		case R.id.menu_clear:
			mList.clear();
			
			mHisMgr.RemoveallHistorys();
			
			mAdapter.notifyDataSetChanged();	
		default:
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}
}
