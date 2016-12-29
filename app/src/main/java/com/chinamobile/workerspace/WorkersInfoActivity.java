package com.chinamobile.workerspace;

import java.util.ArrayList;

import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.filemanagement.FileChooserActivity;
import com.chinamobile.workerspace.utils.CustomDialog;
import com.chinamobile.workerspace.utils.ImageUtil;
import com.chinamobile.workerspace.utils.SharedPrefs;









import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

public class WorkersInfoActivity extends Activity {
    /** Called when the activity is first created. */
	ArrayList<ImageInfo> uidata; // 鑿滃崟鏁版嵁
	private static TextView mynum; // 椤电爜
	
	private ContactsManager  mMgr ;
	private SharedPrefs      mPrefs;
	
	
	private Context   mContext0 ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_entry);
        
        mMgr = ContactsManager.GetInstance(this);
        
        mPrefs = SharedPrefs.GetInstance(this);
        
        ImageUtil.initImageLoader(this);
        
        InitData();
        
        SetUpLayout();
        mContext0 = this ;
        
        if((mMgr.CheckDbExist() == false)&&(mPrefs.GetInitTipsPrefs().isEmpty())){
        	ShowPromptDlg();
        }
        mMgr.Close();
      
    }
    
    private void SetUpLayout(){
    	
    	mynum = (TextView) findViewById(R.id.mynum);
    	    	
    	ViewPager vpager = (ViewPager) findViewById(R.id.vPager);
		vpager.setAdapter(new EntryPagerAdapter(this, uidata));
		vpager.setPageMargin(100);
		vpager.setOnPageChangeListener(new OnPageChangeListener() {

			
			public void onPageSelected(int arg0) {
				
			}

			
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			
			public void onPageScrollStateChanged(int arg0) {

			}
		});
    }
    
    private void InitData(){
    	uidata = new ArrayList<ImageInfo>();
		
		uidata.add(new ImageInfo("组织查询", R.drawable.icon5, R.drawable.icon_bg01));      
		uidata.add(new ImageInfo("人员查询", R.drawable.icon10, R.drawable.icon_bg01));      
		uidata.add(new ImageInfo("总体分析", R.drawable.icon7, R.drawable.icon_bg01));      
		uidata.add(new ImageInfo("历史记录", R.drawable.icon9, R.drawable.icon_bg02));      
		uidata.add(new ImageInfo("人员收藏", R.drawable.icon3, R.drawable.icon_bg02));      
		uidata.add(new ImageInfo("组织收藏", R.drawable.icon1, R.drawable.icon_bg02));      
		uidata.add(new ImageInfo("系统设置", R.drawable.icon11, R.drawable.icon_bg02));      
		uidata.add(new ImageInfo("退出系统", R.drawable.icon14, R.drawable.icon_bg02));                                                        
		    
    }
    
    private void ShowPromptDlg(){
		new CustomDialog.Builder(this)
		.setTitle(R.string.important_tips_title)
		.setMessage(R.string.important_tips_body)
		.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						mPrefs.PutInitTipsPrefs("ok");
						Intent  frqueui = new Intent();
						
						
						
						frqueui.setClass(mContext0,FileChooserActivity.class);
						frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
						mContext0.startActivity(frqueui);	
						
						//overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
					}
				})
		.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int which) {
						
					}
				}).create().show();
	}
}