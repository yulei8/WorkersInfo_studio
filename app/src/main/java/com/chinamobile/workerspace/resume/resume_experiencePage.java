package com.chinamobile.workerspace.resume;




import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.adapter.ExperienceListAdapter;
import com.chinamobile.workerspace.adapter.FamilyListAdapter;
import com.chinamobile.workerspace.contacts.ResumeItem;



public class resume_experiencePage {

	    private static resume_experiencePage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		
		private ListView  mExperienceList;
		
        private TextView  mTextView ;
        private String    mText ;
		
		private ExperienceListAdapter   mAdapter ; 
        private ArrayList<ResumeItem> mList = new ArrayList<ResumeItem>();
  
        
		public static resume_experiencePage GetInstance(){
			if(mInstance ==null){
				mInstance = new resume_experiencePage();
			}
			return mInstance ;
		}
		
		public resume_experiencePage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
		 }
		
		
		 
		 public void OnCreate(){
		    
			/* 
			 mExperienceList = (ListView)mViewOfPage.findViewById(R.id.list_person_experience);
			  
			 mAdapter  = new ExperienceListAdapter(mContext0,mList);
			 
			 Log.d("resume_experiencePage", "experience count:"+mList.size());
			 mExperienceList.setAdapter(mAdapter);*/
			 mTextView = (TextView)mViewOfPage.findViewById(R.id.textview_person_experience);
			 
			 mTextView.setText(mText);
		 }
	
		 
		 public void OnUpdate(){
			 mTextView.setText(mText);
		 }
		 
		 public void SetInitData(List<ResumeItem> list){
			 mList = (ArrayList<ResumeItem>) list;
		 }
		 
		 public void SetViewData(String text){
			 mText = text ;
		 }
		 
}
