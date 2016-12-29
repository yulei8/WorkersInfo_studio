package com.chinamobile.workerspace.resume;




import java.util.ArrayList;
import java.util.List;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.UserItem;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;



public class resume_DetailPage {

	    private static resume_DetailPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		
		
		private TextView  m_xmText;       
		private TextView  m_xbText;       
		private TextView  m_csrqText;     
		private TextView  m_csdText;      
		private TextView  m_mzText;       
		private TextView  m_jgText;       	
		private TextView  m_zzmmText;     
		private TextView  m_rdsjText;     
		private TextView  m_xrzwText;     
		private TextView  m_xrzsjText;    
		private TextView  m_xrzjText;     
		private TextView  m_rxrzsjText;    
		private TextView  m_fldzwText;    
		private TextView  m_rfldzwsjText; 
		private TextView  m_cjgzsjText;   
		private TextView  m_jkzkText;     
		private TextView  m_zyjszwText;   
		private TextView  m_zytcText;     
		private TextView  m_q_xlText;     
		private TextView  m_q_xwText;  
		private TextView  m_q_yxText;  
		private TextView  m_q_zyText;  
		private TextView  m_z_xlText;  
		private TextView  m_z_xwText;  
		private TextView  m_jtzzText;  
		private TextView  m_zfmjText;  
		private TextView  m_jtnsrText; 
		
		private TextView  m_jjstText; 
		private TextView  m_jjstmcddText; 
		
		private TextView  m_z_yxText; 
		private TextView  m_z_zyText; 
		
		
		private TextView  m_fcgsmjText; 
		
		private TextView  m_hbgbzjText; 
		private TextView  m_hbgbsjText; 
	
		private TextView  m_zytdyjText;
		private TextView  m_xgText;    
		private TextView  m_ahText;    
		private TextView  m_bzText;    
		private TextView  m_dh_bText;  
		private TextView  m_dh_zText;  
		private TextView  m_dh_sText;  
		private TextView  m_beizhuText;
		
		private TextView  m_zgztText;
		
		
		private TextView m_czdText;
		private TextView m_fuxiansjText;
		private TextView m_zhengxiansjText ;
		
		
		private  UserItem  mItem ;
		
		public static resume_DetailPage GetInstance(){
			if(mInstance ==null){
				mInstance = new resume_DetailPage();
			}
			return mInstance ;
		}
		
		public resume_DetailPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	
		 }
		
		 public void SetItem(UserItem item){
			 mItem = item;
		 }
		 
		 private void UpdateUi(){
			 m_xmText.setText(mItem.getM_xm());              
			 m_xbText.setText(mItem.getM_xb());   
			 m_zgztText.setText(mItem.getM_zgzt());
			 m_csrqText.setText(mItem.getM_csrq());          
			 m_csdText.setText(mItem.getM_csd());            
			 m_mzText.setText(mItem.getM_mz());              
			 m_jgText.setText(mItem.getM_jg());              
			 m_zzmmText.setText(mItem.getM_zzmm());          
			 m_rdsjText.setText(mItem.getM_rdsj());          
			 m_xrzwText.setText(mItem.getM_xrzw());          
			 m_xrzsjText.setText(mItem.getM_rxzsj());        
			 m_xrzjText.setText(mItem.getM_xrzj());          
			 m_rxrzsjText.setText(mItem.getM_rxzjsj());         
			 m_fldzwText.setText(mItem.getM_fldzw());        
			 m_rfldzwsjText.setText(mItem.getM_rfldzwsj());    
			 m_cjgzsjText.setText(mItem.getM_cjgzsj());      
			 m_jkzkText.setText(mItem.getM_jkzk());          
			 m_zyjszwText.setText(mItem.getM_zyjszw());      
			 m_zytcText.setText(mItem.getM_zytc());          
			 m_q_xlText.setText(mItem.getM_q_xl());          
			 m_q_xwText.setText(mItem.getM_q_xw());          
			 m_q_yxText.setText(mItem.getM_q_yx());          
			 m_q_zyText.setText(mItem.getM_q_zy());          
			 m_z_xlText.setText(mItem.getM_z_xl());          
			 m_z_xwText.setText(mItem.getM_z_xw());          
			 m_jtzzText.setText(mItem.getM_jtzz());          
			 m_zfmjText.setText(mItem.getM_zfmj());          
			 m_jtnsrText.setText(mItem.getM_jtnsr());        
			 m_fcgsmjText.setText(mItem.getM_fcgsmj());      
			 
			 m_hbgbzjText.setText(mItem.getM_hbgbzj());
			 m_hbgbsjText.setText(mItem.getM_hbgbsj());
			 
			 m_jjstText.setText(mItem.getM_jjst());
			 m_jjstmcddText.setText(mItem.getM_jjstmcdd());
			 m_z_yxText.setText(mItem.getM_z_yx());
			 m_z_zyText.setText(mItem.getM_z_zy());
			 
			 
			 m_zytdyjText.setText(mItem.getM_zytdyj());      
			 m_xgText.setText(mItem.getM_xg());              
			 m_ahText.setText(mItem.getM_ah());              
			 m_bzText.setText(mItem.getM_bz());              
			 m_dh_bText.setText(mItem.getM_dh_b());          
			 m_dh_zText.setText(mItem.getM_dh_z());          
			 m_dh_sText.setText(mItem.getM_dh_s());          
			 m_beizhuText.setText(mItem.getM_beizhu());  
			 
			 m_czdText.setText(mItem.getM_czd());
			 m_fuxiansjText.setText(mItem.getM_fuxiansj());
			 m_zhengxiansjText.setText(mItem.getM_zhengxiansj());
		 }
		 
		 public void OnUpdate(){
			 UpdateUi();
		 }
		 
		 public void OnCreate(){
		    
			 m_xmText    =         (TextView)mViewOfPage.findViewById(R.id.person_xm);     
			 m_xbText    =         (TextView)mViewOfPage.findViewById(R.id.person_xb);     
			 m_csrqText    =       (TextView)mViewOfPage.findViewById(R.id.person_csrq);   
			 m_csdText    =        (TextView)mViewOfPage.findViewById(R.id.person_csd);    
			 m_mzText    =         (TextView)mViewOfPage.findViewById(R.id.person_mz);     
			 m_jgText    =         (TextView)mViewOfPage.findViewById(R.id.person_jg);     
			 m_zzmmText    =       (TextView)mViewOfPage.findViewById(R.id.person_zzmm);   
			 m_rdsjText    =       (TextView)mViewOfPage.findViewById(R.id.person_rdsj);   
			 m_xrzwText    =       (TextView)mViewOfPage.findViewById(R.id.person_xrzw);   
			 m_xrzsjText    =      (TextView)mViewOfPage.findViewById(R.id.person_xrzsj);  
			 m_xrzjText    =       (TextView)mViewOfPage.findViewById(R.id.person_xrzj);   
			 m_rxrzsjText    =      (TextView)mViewOfPage.findViewById(R.id.person_rxrzsj);  
			 m_fldzwText    =      (TextView)mViewOfPage.findViewById(R.id.person_fldzw);  
			 m_rfldzwsjText  =     (TextView)mViewOfPage.findViewById(R.id.person_rfldzwsj);
			 m_cjgzsjText    =     (TextView)mViewOfPage.findViewById(R.id.person_cjgzsj); 
			 m_jkzkText    =       (TextView)mViewOfPage.findViewById(R.id.person_jkzk);   
			 m_zyjszwText    =     (TextView)mViewOfPage.findViewById(R.id.person_zyjszw); 
			 m_zytcText    =       (TextView)mViewOfPage.findViewById(R.id.person_zytc);   
			 m_q_xlText    =       (TextView)mViewOfPage.findViewById(R.id.person_q_xl);   
			 m_q_xwText    =       (TextView)mViewOfPage.findViewById(R.id.person_q_xw);   
			 m_q_yxText    =       (TextView)mViewOfPage.findViewById(R.id.person_q_yx);   
			 m_q_zyText    =       (TextView)mViewOfPage.findViewById(R.id.person_q_zy);   
			 m_z_xlText    =       (TextView)mViewOfPage.findViewById(R.id.person_z_xl);   
			 m_z_xwText    =       (TextView)mViewOfPage.findViewById(R.id.person_z_xw);   
			 m_jtzzText    =       (TextView)mViewOfPage.findViewById(R.id.person_jtzz);   
			 m_zfmjText    =       (TextView)mViewOfPage.findViewById(R.id.person_zfmj);   
			 m_jtnsrText    =      (TextView)mViewOfPage.findViewById(R.id.person_jtnsr); 
			 
			 m_fcgsmjText    =     (TextView)mViewOfPage.findViewById(R.id.person_fcgsmj); 
			 
			 m_hbgbzjText    =     (TextView)mViewOfPage.findViewById(R.id.person_hbgbzj); 
			 
			 
			 m_hbgbsjText   =  (TextView)mViewOfPage.findViewById(R.id.person_hbgbsj); 
			 
			 
			 m_jjstText    =     (TextView)mViewOfPage.findViewById(R.id.person_jjst);
			 m_jjstmcddText    =     (TextView)mViewOfPage.findViewById(R.id.person_jjstmcdd);
			 m_z_yxText    =     (TextView)mViewOfPage.findViewById(R.id.person_z_yx);
			 m_z_zyText    =     (TextView)mViewOfPage.findViewById(R.id.person_z_zy);
			 
		
			 
			 m_zytdyjText    =     (TextView)mViewOfPage.findViewById(R.id.person_zytdyj); 
			 m_xgText    =         (TextView)mViewOfPage.findViewById(R.id.person_xg);     
			 m_ahText    =         (TextView)mViewOfPage.findViewById(R.id.person_ah);     
			 m_bzText    =         (TextView)mViewOfPage.findViewById(R.id.person_bz);     
			 m_dh_bText    =       (TextView)mViewOfPage.findViewById(R.id.person_dh_b);   
			 m_dh_zText    =       (TextView)mViewOfPage.findViewById(R.id.person_dh_z);   
			 m_dh_sText    =       (TextView)mViewOfPage.findViewById(R.id.person_dh_s);   
			 m_beizhuText    =     (TextView)mViewOfPage.findViewById(R.id.person_beizhu); 

			 
			 
			 m_zgztText    =     (TextView)mViewOfPage.findViewById(R.id.person_zgzt); 

			 m_czdText    =     (TextView)mViewOfPage.findViewById(R.id.person_czd); 
			 m_fuxiansjText    =     (TextView)mViewOfPage.findViewById(R.id.person_fuxiansj); 
			 m_zhengxiansjText    =     (TextView)mViewOfPage.findViewById(R.id.person_zhengxiansj); 
				
			    
			 UpdateUi();
			
			
		 }
}
