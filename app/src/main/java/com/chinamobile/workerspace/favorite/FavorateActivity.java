package com.chinamobile.workerspace.favorite;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.chinamobile.workerspace.HistoryType;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.adapter.FavorateGroupListAdapter;
import com.chinamobile.workerspace.adapter.FavorateUserListAdapter;
import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.history.HistoryItem;
import com.chinamobile.workerspace.humanresource.humanresource_containerActivity;
import com.chinamobile.workerspace.resume.resume_containerActivity;
import com.chinamobile.workerspace.statics.Statics_containerActivity;
import com.chinamobile.workerspace.utils.T;
import com.chinamobile.workspace.ui.swipelistview.BaseSwipeListViewListener;
import com.chinamobile.workspace.ui.swipelistview.SwipeListView;

public class FavorateActivity extends Activity implements MyDeleteCallback {

	
	
	private FavoriteManager   favorMgr ;
	
	private ContactsManager   mContactsMgr ;
	
	private List<FavorateItem>  mList ;
	private int  show_type = 0 ;
	
	private ArrayList<UserItem> mListUser = new ArrayList<UserItem>();
	
	private ArrayList<GroupItem> mListGroup = new ArrayList<GroupItem>();
	
	private FavorateUserListAdapter  mUserAdapter ;
	
	private FavorateGroupListAdapter  mGroupAdapter ;
	
	private SwipeListView  mSwipeListView = null;
	
	
	private int getDeviceWidth() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.favorate_listview);
		
		
        ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
	    
		
		favorMgr = FavoriteManager.GetInstance(this);
		
		mContactsMgr = ContactsManager.GetInstance(this);
		
		
		
		mSwipeListView = (SwipeListView)findViewById(R.id.favoriteuser_main_listview);
		int deviceWidth = getDeviceWidth();
    	mSwipeListView.setOffsetLeft(deviceWidth * 2/ 3);
    	mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);   
    	mSwipeListView.setSwipeListViewListener(mSwipeListViewListener);
		int flag =  this.getIntent().getIntExtra("start_flag", 0);
		show_type = flag ;
		
		if(flag == 0){
			
			mList = favorMgr.GetAllFavorateUsers() ;
			
			if(mList.size() == 0 ){
				T.showShort(this, R.string.no_favorate_items);
			}else {
				
				for(int i = 0 ; i < mList.size(); i++){
					mListUser.add(mContactsMgr.GetUserById(mList.get(i).getItem_id()));
				}
				
				mUserAdapter  = new FavorateUserListAdapter(this,mListUser);
				
				mUserAdapter.SetDeleteCallBack(this);
				mSwipeListView.setAdapter(mUserAdapter);	
				
			}
		}else{
			
			mList = favorMgr.GetAllFavorateGroups() ;
			
			if(mList.size() == 0 ){
				T.showShort(this, R.string.no_favorate_items);
			}else {
				
				for(int i = 0 ; i < mList.size(); i++){
										
					mListGroup.add(mContactsMgr.GetGroupByGroupID(mList.get(i).getItem_id()));
				}
				
				mGroupAdapter  = new FavorateGroupListAdapter(this,mListGroup);
				
				mGroupAdapter.SetDeleteCallBack(this);
				mSwipeListView.setAdapter(mGroupAdapter);	
				
			}
		}
		
		
	}	
	

	private void GotoActivity(FavorateItem item,int index){
	    Intent  mainui = new Intent();
	    
	    if(show_type == 0 ){
			
	    
	    	mainui.putExtra("user_id", item.getItem_id());
			mainui.setClass(this,resume_containerActivity.class);
	    	
			
		}else {
			mainui.putExtra("group_id", item.getItem_id());
	    	mainui.setClass(this,humanresource_containerActivity.class);
		}
		
		this.startActivity(mainui);		
   }
	
	BaseSwipeListViewListener mSwipeListViewListener = new BaseSwipeListViewListener() {
		@Override
		public void onClickFrontView(int position) {
			GotoActivity(mList.get(position),position);
		}

		@Override
		public void onClickBackView(int position) {
			
			mSwipeListView.closeOpenedItems();// 鍏抽棴鎵撳紑鐨勯」
		}
	};


	@Override
	public void DeleteItem(int pos,int type) {
		// TODO Auto-generated method stub
		if(type == 0){
			UserItem item = mListUser.get(pos);
			mListUser.remove(pos);
			favorMgr.DeleteUserItem(item.getM_sfz_no());
			mUserAdapter.notifyDataSetChanged();			
			mSwipeListView.closeOpenedItems();
		}else{
			GroupItem item = mListGroup.get(pos);
			mListGroup.remove(pos);
			//favorMgr.DeleteUserItem(item.getGroup_id());
			mGroupAdapter.notifyDataSetChanged();			
			mSwipeListView.closeOpenedItems();
		}
		
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
			
			
			
			if(show_type == 0){
				mListUser.clear();
				favorMgr.RemoveallUsers();
				mUserAdapter.notifyDataSetChanged();
			}else{
				mListGroup.clear();
				favorMgr.RemoveallGroups();
				mGroupAdapter.notifyDataSetChanged();
			}
			
			break;
		default:
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}
}
