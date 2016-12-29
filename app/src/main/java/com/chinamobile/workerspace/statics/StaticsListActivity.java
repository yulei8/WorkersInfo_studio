package com.chinamobile.workerspace.statics;




import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.filemanagement.FileChooserActivity;
import com.chinamobile.workerspace.utils.T;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class StaticsListActivity extends Activity implements OnClickListener {

	private View  mSexPanel;
	private View  mAgePanel;
	private View  mNationPanel;
	private View  mEduPanel;
	private View  mLevelPanel;
	private View  mJobPanel;
	private View  mOrgPanel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total_statistics_page);
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
		
		mSexPanel = (View)findViewById(R.id.person_sex_count);
		mAgePanel = (View)findViewById(R.id.person_age_count);
		mNationPanel = (View)findViewById(R.id.person_nation_count);
		mEduPanel = (View)findViewById(R.id.person_edu_count);
		mLevelPanel = (View)findViewById(R.id.person_level_count);
		mJobPanel = (View)findViewById(R.id.person_job_count);
		mOrgPanel = (View)findViewById(R.id.person_org_count);
		
		
		
		mSexPanel.setOnClickListener(this);
		mAgePanel.setOnClickListener(this);
		mNationPanel.setOnClickListener(this);
		mEduPanel.setOnClickListener(this);
		mLevelPanel.setOnClickListener(this);
		mJobPanel.setOnClickListener(this);
		mOrgPanel.setOnClickListener(this);
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.person_sex_count:
			GotoActivity(Statics_containerActivity.class,0);
			break;
		case R.id.person_age_count:
			GotoActivity(Statics_containerActivity.class,1);
			break;
		case R.id.person_nation_count:
			GotoActivity(Statics_containerActivity.class,2);
			break;
		case R.id.person_edu_count:
			GotoActivity(Statics_containerActivity.class,3);
			break;
		case R.id.person_level_count:
			GotoActivity(Statics_containerActivity.class,4);
			break;
		case R.id.person_job_count:
			GotoActivity(Statics_containerActivity.class,5);
			break;
		case R.id.person_org_count:
			GotoActivity(Statics_containerActivity.class,6);
			break;
		 default:
				break;
		  
		}
	}
	
   private void GotoActivity(Class cls,int index){
	    Intent  mainui = new Intent();
		mainui.setClass(this,cls);
		mainui.putExtra("category", String.valueOf(index));
		this.startActivity(mainui);		
   }
   @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater(); 
		
		//inflater.inflate(R.menu.main, menu);  
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
