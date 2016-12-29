package com.chinamobile.workerspace.settings;



import java.util.List;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.WorkersInfoActivity;
import com.chinamobile.workerspace.login.LockScreenActivity;
import com.chinamobile.workerspace.ui.GestureLockViewGroup;
import com.chinamobile.workerspace.ui.GestureLockViewGroup.OnGestureLockViewListener;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.chinamobile.workerspace.utils.T;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeGestureActivity extends Activity implements OnClickListener {
	
	private TextView   mTitleText ;
	
	private TextView mPasswordLogin;
	private Vibrator vibrator;
	
	private String   mOldPassword;
	private String   mPassword;
	private String   mPassword_again;
	
	private SharedPrefs  mPrefs ;
	private GestureLockViewGroup mGestureLockViewGroup;
	
	
	
	private int  mstate = 1 ;  //1: first 2:confirm
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lockscreen);
		
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
	    
	    
		mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
		mGestureLockViewGroup.setAnswer(new int[] { 1, 2, 3, 4,5 });
		
		mTitleText = (TextView)findViewById(R.id.lockscreen_title);
		
		mTitleText.setTextColor(Color.RED);
		mTitleText.setText(R.string.new_gesture);
		vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
	
		mPasswordLogin = (TextView)findViewById(R.id.use_password);
		mPasswordLogin.setVisibility(View.INVISIBLE);
		
		mGestureLockViewGroup.setOnGestureLockViewListener(new OnGestureLockViewListener()
		{

			public void onUnmatchedExceedBoundary()
			{
				mGestureLockViewGroup.setUnMatchExceedBoundary(5);
			}

			public void onGestureEvent(boolean matched)
			{
				
				List<Integer>  choosed = mGestureLockViewGroup.getChoose();			
				
			    if(mstate == 1){
			    	
			    	if(choosed.size() < 4){
			    		mTitleText.setText(R.string.gesture_too_short);
			    		return ;
			    	}
			    	
			    	mPassword = choosed.toString() ;
			    	mstate = 2;
			    	mTitleText.setText(R.string.confirm_new_gesture);
			    	
			    }else{
			    	mstate = 1 ;
			    	if(mPassword.equals(choosed.toString()) == true){
			    		//save and prompt and exist
			    		T.showShort(ChangeGestureActivity.this, R.string.password_updated);
			    		String pass ;
			    		
			    		mTitleText.setText(R.string.password_updated);
			    		
			    		Log.d("ID", "updated:"+choosed.toString());
			    		
			    		
			    		mPrefs.PutGesturePassword(mPassword);
			    		finish();
			    		
			    	}else{
			    		mTitleText.setText(R.string.new_gesture);
			    		T.showShort(ChangeGestureActivity.this, R.string.gesture_different);
			    	}
			    	
			    }
				
			    
				/*
				Toast.makeText(LockScreenActivity.this, matched+"",
						Toast.LENGTH_SHORT).show();
				List<Integer>  choosed = mGestureLockViewGroup.getChoose();
				for(int i = 0 ; i<choosed.size();i++){
					Log.d("ID", "id:"+choosed.get(i));
				}*/
			}

			public void onBlockSelected(int cId)
			{
				mTitleText.setText(R.string.gesture_drawing);
				vibrator.vibrate(45);
			}
		});
		
		mPrefs = SharedPrefs.GetInstance(this);
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
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
