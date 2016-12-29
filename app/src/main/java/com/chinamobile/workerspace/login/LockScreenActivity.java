package com.chinamobile.workerspace.login;

import java.util.List;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.WorkersInfoActivity;
import com.chinamobile.workerspace.ui.GestureLockViewGroup;
import com.chinamobile.workerspace.ui.GestureLockViewGroup.OnGestureLockViewListener;
import com.chinamobile.workerspace.utils.SharedPrefs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreenActivity extends Activity implements OnClickListener
{

	private GestureLockViewGroup mGestureLockViewGroup;

	private Vibrator vibrator;
	
	private SharedPrefs  mPrefs ;
	
    private TextView mPasswordLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lockscreen);

		mPrefs = SharedPrefs.GetInstance(this);
		
		mPasswordLogin = (TextView)findViewById(R.id.use_password);
		mPasswordLogin.setOnClickListener(this);
		
		vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		
		mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
		mGestureLockViewGroup.setAnswer(new int[] { 1, 4, 7, 8,9 });
		
		
		mGestureLockViewGroup.setOnGestureLockViewListener(new OnGestureLockViewListener()
				{

					public void onUnmatchedExceedBoundary()
					{
						mGestureLockViewGroup.setUnMatchExceedBoundary(5);
					}

					public void onGestureEvent(boolean matched)
					{
						List<Integer>  choosed = mGestureLockViewGroup.getChoose();
						
						if(mPrefs.GetGesturePasswordPrefs().isEmpty() == true){
							if(matched == true){
								Intent  mainui = new Intent();
								mainui.setClass(LockScreenActivity.this, WorkersInfoActivity.class);
								startActivity(mainui);
								finish();
							}else{
								Toast.makeText(LockScreenActivity.this, R.string.password_wrong,
										Toast.LENGTH_SHORT).show();
							}
						}else if(mPrefs.GetGesturePasswordPrefs().equals(choosed.toString())){
							Intent  mainui = new Intent();
							mainui.setClass(LockScreenActivity.this, WorkersInfoActivity.class);
							startActivity(mainui);
							finish();
						}else {
							Toast.makeText(LockScreenActivity.this, R.string.password_wrong,
									Toast.LENGTH_SHORT).show();
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
						vibrator.vibrate(45);
					}
				});
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent  mainui = new Intent();
		mainui.setClass(LockScreenActivity.this, PasswordLoginActivity.class);
		startActivity(mainui);
		finish();
	}

}
