package com.chinamobile.workerspace.login;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.WorkersInfoActivity;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.chinamobile.workerspace.utils.T;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PasswordLoginActivity extends Activity implements OnClickListener {

	private EditText mPasswordEt;
	
	private Button   mLogin ;
	
	private String   mOldPassword;
	
	private SharedPrefs  mPrefs ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.passwordlogin);
		//setContentView(R.layout.person_info_detail);

		
		mPasswordEt = (EditText)findViewById(R.id.password_edit);
		
		
		mLogin = (Button)findViewById(R.id.loginBtn);
		mLogin.setOnClickListener(this);
		
		mPrefs = SharedPrefs.GetInstance(this);
		
		
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		mOldPassword = mPasswordEt.getText().toString();
		if(TextUtils.isEmpty(mOldPassword)){
			T.showShort(this, R.string.password_wrong);
			return ;
		}
		
		if(mPrefs.GetPasswordPrefs().isEmpty()&&mOldPassword.equals("123456")){
			
			mPrefs.PutPassword("123456");
			Intent  mainui = new Intent();
			mainui.setClass(this, WorkersInfoActivity.class);
			startActivity(mainui);
			finish();
		}else if((mPrefs.GetPasswordPrefs().isEmpty() == false)&&mPrefs.GetPasswordPrefs().equals(mOldPassword)){
			Intent  mainui = new Intent();
			mainui.setClass(this, WorkersInfoActivity.class);
			startActivity(mainui);
			finish();
		}else{
			T.showShort(this, R.string.password_wrong);
		}
		
		
	}

}
