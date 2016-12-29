package com.chinamobile.workerspace.settings;




import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.db.CopyDbhelper;
import com.chinamobile.workerspace.filemanagement.FileChooserActivity;
import com.chinamobile.workerspace.utils.CustomDialog;
import com.chinamobile.workerspace.utils.SharedPrefs;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingsActivity extends Activity implements OnClickListener {

	private View  mUpdatedataPanel;
	private View  mChangePasswordPanel;
	private View  mChangeGesturePanel;
	private View  mAboutPanel;
	
	private View  mResetPanel ;
	
	private SharedPrefs      mPrefs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_page);
		
		mUpdatedataPanel = (View)findViewById(R.id.update_data_panel);
		mChangePasswordPanel = (View)findViewById(R.id.password_change_panel);
		mChangeGesturePanel = (View)findViewById(R.id.gesturesetting_panel);
		mAboutPanel = (View)findViewById(R.id.aboutus_panel);
		
		mResetPanel = (View)findViewById(R.id.reset_panel);
		
		mPrefs = SharedPrefs.GetInstance(this);
		
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
		
		mUpdatedataPanel.setOnClickListener(this);
		mChangePasswordPanel.setOnClickListener(this);
		mChangeGesturePanel.setOnClickListener(this);
		mAboutPanel.setOnClickListener(this);
		mResetPanel.setOnClickListener(this);
		
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
	
	private void ShowPromptExitDlg(){
		new CustomDialog.Builder(this)
		.setTitle(R.string.important_tips_title)
		.setMessage(R.string.reset_tips)
		.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						
						CopyDbhelper.DeleteDatabase();
						mPrefs.ClearInformation(); 
					}
				})
		.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int which) {
						
					}
				}).create().show();
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.update_data_panel:
			GotoActivity(FileChooserActivity.class);
			break;
		case R.id.password_change_panel:
			GotoActivity(ChangePasswordActivity.class);
			break;
		case R.id.gesturesetting_panel:
			GotoActivity(ChangeGestureActivity.class);
			break;
		case R.id.aboutus_panel:
			GotoActivity(AboutActivity.class);
			break;
		case R.id.reset_panel:
			ShowPromptExitDlg();
		    break;
		 default:
				break;
		  
		}
	}
	
   private void GotoActivity(Class cls){
	   Intent  mainui = new Intent();
		mainui.setClass(this,cls);
		this.startActivity(mainui);		
   }
}
