package com.chinamobile.workerspace.statics;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.ContactsListActivity;
import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.utils.T;



public class StaticsListPage implements OnItemClickListener, TextWatcher {

	    private static StaticsListPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		private ListView mListView;
		
		private int  statis_id ;
		
		private StaticsListAdapter  mAdapter ;
		
		private List<StaticsListItem> mListU ;
		
		
		private ContactsManager   mContactsMgr ;
		
		private List<GroupItem>  grouplist  ;
		
		private final int[]  static_titles = {R.string.statis_sex,R.string.statis_age,R.string.statis_nation,
				R.string.statis_edu,R.string.statis_depth,R.string.statis_status,R.string.statis_group};
		
		
		
		private Map<String,List<UserItem>>  minfos ;//= new HashMap<String,List<UserItem>>();
		private List<String> mkeys ;

		
		public static StaticsListPage GetInstance(){
			if(mInstance ==null){
				mInstance = new StaticsListPage();
			}
			return mInstance ;
		}
		
		public StaticsListPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	
		    	mContactsMgr = ContactsManager.GetInstance(t);
		 }
		
		public void MakeDatas(Map<String,List<UserItem>>  maps , List<String> keys){
			minfos = maps ;
			mkeys = keys ;
		}
		
		public void SetTitle(int id){
			
			
			TextView  title1 = (TextView)mViewOfPage.findViewById(R.id.statics_list_title);
			
			title1.setText(static_titles[id]);
			
			statis_id = id ;
			
			if(id == 6){ //by group
				grouplist = mContactsMgr.GetAllGroupList();
			}
			
		}
		 public void OnCreate(){
		    
			ListView mListView = (ListView)mViewOfPage.findViewById(R.id.list_view_statics_list);
			
		 	
			
			mListU = new ArrayList<StaticsListItem>();
			
			for(int i = 0 ; i < mkeys.size() ; i++){
				String key = mkeys.get(i);
				List<UserItem>  l = minfos.get(key);
				
				StaticsListItem  listitem = new StaticsListItem();
				listitem.setTitle(key);
				listitem.setnCount(l.size());
				
				mListU.add(listitem);
			}
			/*for(String key : minfos.keySet()){
				
				List<UserItem>  l = minfos.get(key);
				
				StaticsListItem  listitem = new StaticsListItem();
				listitem.setTitle(key);
				listitem.setnCount(l.size());
				
				mListU.add(listitem);
			}*/

			mAdapter = new StaticsListAdapter(mContext0,mListU);
			
			mListView.setAdapter(mAdapter);	
			mListView.setOnItemClickListener(this);
		 }

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.d("onItemClick", "onItemClick is clicked");
			GoActivity(arg2,mListU.get(arg2).getTitle());
		}
		

		
		private void GoActivity(int arg2,String keys){
			
			
			Intent  frqueui = new Intent();


			String key = mkeys.get(arg2);
			List<UserItem>  l = minfos.get(key);
			
			if((l != null)&&(l.size() > 0)){
			frqueui.putExtra("birthdate", l.get(0).getM_csrq());
			
			if(statis_id == 6){
				frqueui.putExtra("org_id", grouplist.get(arg2).getGroup_id());
			}
			
			frqueui.putExtra("category", statis_id);
			frqueui.putExtra("category_keys", keys);
			frqueui.setClass(mContext0,ContactsListActivity.class);
			frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			mContext0.startActivity(frqueui);	

			((Activity)mContext0).overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
			}else{
				T.showShort(mContext0, "No infomation to show");
			}
		
		}

		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
		}
		
		

}
