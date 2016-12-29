package com.chinamobile.workerspace.settings;



import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.chinamobile.workerspace.utils.T;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends Activity implements OnClickListener {
	private EditText mPasswordEt;
	private EditText mPassword_againEt;
	private EditText mOldPasswordEt;
	private Button   mConfitm ;
	
	private String   mOldPassword;
	private String   mPassword;
	private String   mPassword_again;
	
	private SharedPrefs  mPrefs ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_change);
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
	    
		mOldPasswordEt = (EditText)findViewById(R.id.edittext_old_password);
		
		mPasswordEt = (EditText)findViewById(R.id.edittext_password);
		mPassword_againEt = (EditText)findViewById(R.id.edittext_confirm_password);
		
		mConfitm = (Button)findViewById(R.id.btn_confirmchange);
		mConfitm.setOnClickListener(this);
		
		mPrefs = SharedPrefs.GetInstance(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mPassword = mPasswordEt.getText().toString();
		
		mPassword_again = mPassword_againEt.getText().toString();
		
		mOldPassword = mOldPasswordEt.getText().toString();
		
		if(TextUtils.isEmpty(mOldPassword)){
			T.showShort(this, R.string.mobileorpwd_null_prompt);
			return ;
		}
		
		String mPrefpassword = mPrefs.GetPasswordPrefs();
		if(mPrefpassword.equals(mOldPassword) == false){
			T.showShort(this, R.string.old_password_not_correct);
			mOldPasswordEt.setText("");
			return ;
		}
		
		
		if(TextUtils.isEmpty(mPassword) || TextUtils.isEmpty(mPassword_again)){
			T.showShort(this, R.string.mobileorpwd_null_prompt);
			return ;
		}
		
		
		if(!mPassword_again.equals(mPassword)){
			T.showShort(this, R.string.password_not_match);
			mPasswordEt.setText("");
			mPassword_againEt.setText("");
			return ;
		}
		
		
		
		T.showShort(this, R.string.password_updated);
		mPrefs.PutPassword(mPassword);
		finish();
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
