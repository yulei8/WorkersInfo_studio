package com.chinamobile.workerspace.resume;




import java.util.ArrayList;
import java.util.List;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class resume_shortPage {

	    private static resume_shortPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		
		private TextView  mFirstText;
		private TextView  mSecText;
		private TextView  mThirdText;
		
		private TextView  mjiguanText;
		private TextView  mchushengdiText;
		private TextView  mchengzhangdiText;
		
		private TextView  mFourthText;
		private TextView  mFifthText;
		private TextView  mSixText;
		
		private TextView  m7Text;
		private TextView  m8Text;
		private TextView  m9Text;
		private TextView  m10Text;
		private TextView  m11Text;
		
		
		private  UserItem  mItem ;
		private String  mImgpath;
		private ImageView mImage ;
		public static resume_shortPage GetInstance(){
			if(mInstance ==null){
				mInstance = new resume_shortPage();
			}
			return mInstance ;
		}
		
		public resume_shortPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
		    	mImgpath = SharedPrefs.GetInstance(t).GetImagePathPrefs();
	
		 }
		
		 public void SetItem(UserItem item){
			 mItem = item;
		 }
		 
		 
		 private void UpdateUi(){
			    String s = "file://" + mImgpath+"/"+mItem.getM_sfz_no()+".jpg";
				
			 
			 
				ImageLoader.getInstance().displayImage(s, mImage);
				
				

				StringBuffer firstline = new StringBuffer();
				
				//firstline.append(String.format("%s %s %s %s 出生",mItem.getM_xm(),mItem.getM_xb(),mItem.getM_mz(),mItem.getM_csrq()));
				
				firstline.append(String.format("%s",mItem.getM_xm()));
				
				
				mFirstText.setText(firstline);
				
				
				StringBuffer secline = new StringBuffer();
					
				secline.append(String.format("%s", mItem.getM_xb()));
				
				mSecText.setText(secline);
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_mz()));
				mThirdText.setText(secline);
				
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_jg()));
				mjiguanText.setText(secline);
				
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_csd()));
				mchushengdiText.setText(secline);
				
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_czd()));
				mchengzhangdiText.setText(secline);
				
				
				
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_csrq()));
				mFourthText.setText(secline);
			
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_cjgzsj()));
				
				mFifthText.setText(secline);
				
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_rdsj()));
				mSixText.setText(secline);
				
				
				secline.setLength(0);
				secline.append(String.format("%s %s %s", mItem.getM_q_xl(),mItem.getM_q_yx(),mItem.getM_q_zy()));
				m7Text.setText(secline);
				
				
				secline.setLength(0);
				secline.append(String.format("%s %s %s", mItem.getM_z_xl(),mItem.getM_z_yx(),mItem.getM_z_zy()));
				m8Text.setText(secline);
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_xrzj()));
				m9Text.setText(secline);
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_rxzsj()));
				m10Text.setText(secline);
				
				secline.setLength(0);
				secline.append(String.format("%s", mItem.getM_xrzw()));
				m11Text.setText(secline);
		 }
		 
		 
		 public void OnUpdate(){
			 UpdateUi();
		 }
		 
		 public void OnCreate(){
		    
			 mFirstText = (TextView)mViewOfPage.findViewById(R.id.personresume_first_line);
			 mSecText = (TextView)mViewOfPage.findViewById(R.id.personresume_second_line);
			 mThirdText = (TextView)mViewOfPage.findViewById(R.id.personresume_third_line);
			 mFourthText = (TextView)mViewOfPage.findViewById(R.id.personresume_fourth_line);
			 mFifthText = (TextView)mViewOfPage.findViewById(R.id.personresume_fifth_line);
			 mSixText = (TextView)mViewOfPage.findViewById(R.id.personresume_six_line);
			 
			 
			 mjiguanText = (TextView)mViewOfPage.findViewById(R.id.personresume_jiguan_line);
			 
			 mchushengdiText = (TextView)mViewOfPage.findViewById(R.id.personresume_chushengdi_line);
			 mchengzhangdiText = (TextView)mViewOfPage.findViewById(R.id.personresume_chengzhangdi_line);
			 
			 
			 
			 m7Text = (TextView)mViewOfPage.findViewById(R.id.personresume_7_line);
			 m8Text = (TextView)mViewOfPage.findViewById(R.id.personresume_8_line);
			 m9Text = (TextView)mViewOfPage.findViewById(R.id.personresume_9_line);
			 m10Text = (TextView)mViewOfPage.findViewById(R.id.personresume_10_line);
			 m11Text = (TextView)mViewOfPage.findViewById(R.id.personresume_11_line);
			 
			 
			 
			 mImage = (ImageView)mViewOfPage.findViewById(R.id.personresume_avator);
			 
			 UpdateUi();
			
		 }
}
