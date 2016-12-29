package com.chinamobile.workerspace.resume;




import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.adapter.FamilyListAdapter;
import com.chinamobile.workerspace.contacts.FamilyItem;



public class resume_familyPage {

	    private static resume_familyPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		
		private ListView  mFamilyList;
		

		private FamilyListAdapter   mAdapter ; 
        private ArrayList<FamilyItem> mList = new ArrayList<FamilyItem>();
        
        
        
		public static resume_familyPage GetInstance(){
			if(mInstance ==null){
				mInstance = new resume_familyPage();
			}
			return mInstance ;
		}
		
		public resume_familyPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
		    	
	
		 }
		
		
		 
		 public void OnCreate(){
		    
			 
			 mFamilyList = (ListView)mViewOfPage.findViewById(R.id.list_person_family);
			  
			 mAdapter  = new FamilyListAdapter(mContext0,mList);
			 
			 mFamilyList.setAdapter(mAdapter);
		 }
		 
		 public void OnUpdate(){
			 mAdapter.updateListView(mList);
		 }
		 
		 public void SetInitData(List<FamilyItem> list){
			 mList = (ArrayList<FamilyItem>) list;
		 }
		 
		 
}
