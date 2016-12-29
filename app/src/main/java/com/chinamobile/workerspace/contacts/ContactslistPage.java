package com.chinamobile.workerspace.contacts;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.chinamobile.workerspace.PersonFilter;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.adapter.ContactsListAdapter;
import com.chinamobile.workerspace.db.DictManager;
import com.chinamobile.workerspace.resume.resume_containerActivity;
import com.chinamobile.workerspace.ui.SearchEditText;
import com.chinamobile.workerspace.ui.SideBar;
import com.chinamobile.workerspace.ui.SideBar.OnTouchingLetterChangedListener;
import com.chinamobile.workerspace.utils.PinYinKit;
public class ContactslistPage implements OnTouchingLetterChangedListener, TextWatcher, OnItemClickListener {

	    private static ContactslistPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		
		
		private Button mBtnBack;
	    private ListView mListView = null;
	    
	    private SideBar  msideBar;	
	    
	    private ActionBar  mAb ;
		private TextView mdialogTxt;
		private TextView mHeader;
		private SearchEditText mSearchEditText;
		
	    private ContactsListAdapter mAdapter = null;
		
	    private DictManager  mDict ; 

	    private  PinyinComparator  cmp = new PinyinComparator();
	    
	    
		private ArrayList<UserItem> mListU = new ArrayList<UserItem>();
		
		private ArrayList<String> mTransfor = new ArrayList<String>();
		private ArrayList<UserItem> mListU_show = new ArrayList<UserItem>();
		
		private List<List<GroupItem>> mchild;
		
		
		public static ContactslistPage GetInstance(){
			if(mInstance ==null){
				mInstance = new ContactslistPage();
			}
			return mInstance ;
		}
		
		public ContactslistPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	            
		    	mDict = DictManager.GetInstance(t);
		 }
		
		 
		 public void SetData(List<UserItem> list){
			
			 mListU = (ArrayList<UserItem>) list; 
			 
			 
		 }
		 
		
		 
		 public void SetFilterAndUpdate(PersonFilter  filter){
			 //
			 ArrayList<UserItem> list = new ArrayList<UserItem>();
			 
			 for(int i = 0 ; i< mListU.size() ; i++){
				 boolean  shouldadd = true ;
				 
				 UserItem item = mListU.get(i);
				 
				 //Log.d("SetFilterAndUpdate", "zgzt:"+filter.getNzgzt()+" minage:"+filter.getNagemin()+" maxage:"+filter.getNagemax());
				 //zgzt
				 switch(filter.getNzgzt()){
				 case 0:	 
					 break;
				 case 1:  
					 if(item.getM_zgzt().indexOf("在") == -1 ){
						 shouldadd = false ;
					 }
					 break;
				 case 2:
					 if(item.getM_zgzt().indexOf("离") == -1 ){
						 shouldadd = false ;
					 }
					 break;
				 case 3:
					 if(item.getM_zgzt().indexOf("退") == -1 ){
						 shouldadd = false ;
					 }
					 break;
				   default:
					   shouldadd = false ;
					   break;
				 }
				 if(shouldadd == false){
					 continue ;
				 }
				  
				 
				//性别
		
				 switch(filter.getNxb()){
				 case 0:
					 break;
				 case 1:  
					 if(item.getM_xb().indexOf("男") == -1 ){
						 shouldadd = false ;
					 }
					 break;
				 case 2:
					 if(item.getM_xb().indexOf("女") == -1 ){
						 shouldadd = false ;
					 }
					 break;
				   default:
					   shouldadd = false ;
					   break;
				 }
				 if(shouldadd == false){
					 continue ;
				 }
				 
				 
				
				 
				 //age
				 Date dt=new Date();
				 SimpleDateFormat matter1year =new SimpleDateFormat("yyyy");
				 
				if(item.getM_csrq().length() >= 4) { 
					 if(Integer.valueOf(item.getM_csrq().substring(0, 4))  <= (Integer.valueOf(matter1year.format(dt)) - filter.getNagemin())){
						 shouldadd = true ;
					 }else{
						 continue ;
					 }
					 
					if((filter.getNagemax() != 0)){	 
						 if(Integer.valueOf(item.getM_csrq().substring(0, 4))  >= (Integer.valueOf(matter1year.format(dt)) - filter.getNagemax())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
					 }
				 }else if((filter.getNagemin() > 0) || (filter.getNagemax() >0) ){
					 continue ;
				 }
				
				 //民族
				
				 switch(filter.getNmz()){
				 case 0:
					 break;
				 case 1:  
					 if(item.getM_mz().indexOf("朝鲜") == -1 ){
						 shouldadd = false ;
					 }
					 break;
				 case 2:
					 if(item.getM_mz().indexOf("朝鲜") != -1 ){
						 shouldadd = false ;
					 }
					 break;
				   default:
					   shouldadd = false ;
					   break;
				 }
				 if(shouldadd == false){
					 continue ;
				 }
				 //党员
				 switch(filter.getNzzmm()){
				 case 0:
					 break;
				 case 1:  
					 if((item.getM_zzmm().indexOf("群众") != -1 )||(item.getM_zzmm().indexOf("非") != -1 )){
						 shouldadd = false ;
					 }
					 break;
				 case 2:
					 if((item.getM_zzmm().indexOf("群众") != -1 )||(item.getM_zzmm().indexOf("非") != -1 ) ){
						 shouldadd = true ;
					 }else{
						 shouldadd = false ;
					 }
					 break;
				   default:
					   shouldadd = false ;
					   break;
				 }
				 Log.d("SetFilterAndUpdate", "filter zzmm:"+filter.getNzzmm()+"item zzmm:"+item.getM_zzmm()+"qxl:"+item.getM_q_xl());
				 
				 if(shouldadd == false){
					 continue ;
				 }
				 
				 
				 //全日制学历
				 int qxl = filter.getQxl_gaozhong()+filter.getQxl_shuo_yan()+filter.getQxl_zhuan() + filter.getQxl_zhong()+filter.getQxl_ben()+filter.getQxl_shuo()+filter.getQxl_bo();
				 
				 boolean xueli_shouldadd = false  ;
				 
				 
				 if((qxl >= 1) && (qxl <=6)){
					 
					 
					 String bianma =  new String();
					 if((item.getM_q_xl() == null)||(item.getM_q_xl().isEmpty() == true)){
						 bianma = "07";
					 }else {
						 bianma = mDict.GetBmbylbandmc("xl", item.getM_q_xl()) ;
						 if(bianma == null){
							 bianma = "07";
						 }else if(bianma.isEmpty() == true){
							 bianma = "07";
						 }
					 }
							 
					 
					 
					 switch(Integer.valueOf(bianma.substring(0, 2))){
					 case 1:  //bo
						 if(filter.getQxl_bo() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 2: //shuo
						 
						 if(bianma.length() >= 4){
							 int code = Integer.valueOf(bianma.substring(0, 4));
							 if(code == 201){
								 if(filter.getQxl_shuo() == 1){
									 shouldadd = true ;
								 }else{
									 shouldadd = false ;
								 }
							 }else{
								 if(filter.getQxl_shuo_yan() == 1){
									 shouldadd = true ;
								 }else{
									 shouldadd = false ;
								 }
							 }
							 
						 }else  if(filter.getQxl_shuo() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 3:  //ben
						 if(filter.getQxl_ben() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 4:   //zhuan
						 if(filter.getQxl_zhuan() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 5:   //zhong
					 case 6:   //zhong
						 if(filter.getQxl_zhong() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					  default:
						  if(filter.getQxl_gaozhong() == 1){
								 shouldadd = true ;
							 }else{
								 shouldadd = false ;
							 }
						  break;
					 }
                     
					 xueli_shouldadd = shouldadd ;
				 }
				/* 
				 if(shouldadd == false){
					 continue ;
				 }*/
				
				 
				 //在职学历
				 int zxl = filter.getZxl_zhuan()+filter.getZxl_shuo_yan() + filter.getZxl_zhong()+filter.getZxl_ben()+filter.getZxl_shuo()+filter.getZxl_bo();
				 
				 if((zxl >= 1) && (zxl <=5)){
					 
					 
					 String bianma =  new String();
					 if((item.getM_z_xl() == null)||(item.getM_z_xl().isEmpty() == true)){
						 bianma = "06";
					 }else {
						 bianma = mDict.GetBmbylbandmc("xl", item.getM_z_xl()) ;
						 if(bianma == null){
							 bianma = "06";
						 }else if(bianma.isEmpty() == true){
							 bianma = "06";
						 }
					 }
							 
					 
					 
					 switch(Integer.valueOf(bianma.substring(0, 2))){
					 case 1:  //bo
						 if(filter.getZxl_bo() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 2: //shuo
						 if(bianma.length() >= 4){
							 int code = Integer.valueOf(bianma.substring(0, 4));
							 if(code == 201){
								 if(filter.getZxl_shuo() == 1){
									 shouldadd = true ;
								 }else{
									 shouldadd = false ;
								 }
							 }else{
								 if(filter.getZxl_shuo_yan() == 1){
									 shouldadd = true ;
								 }else{
									 shouldadd = false ;
								 }
							 } 
						 }else  if(filter.getZxl_shuo() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 3:  //ben
						 if(filter.getZxl_ben() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 4:   //zhuan
						 if(filter.getZxl_zhuan() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					 case 5:   //zhong
						 if(filter.getZxl_zhong() == 1){
							 shouldadd = true ;
						 }else{
							 shouldadd = false ;
						 }
						 break;
					  default:
						  if(filter.getZxl_zhong() == 1){
								 shouldadd = true ;
							 }else{
								 shouldadd = false ;
							 }
						  break;
					 }

				 }
				 
				 if((shouldadd == false)&&(xueli_shouldadd == false)){
					 continue ;
				 }
				 
				 
				 
				 
				 //job time
				 if(item.getM_cjgzsj().length() >= 4){
				 
					 if(Integer.valueOf(item.getM_cjgzsj().substring(0, 4))  >= (filter.getNjobtimebein())){
						 shouldadd = true ;
					 }else{
						 continue ;
					 }
						 
					 if((filter.getNjobtimeend() != 0) ){
						 if(Integer.valueOf(item.getM_cjgzsj().substring(0, 4))  <= (filter.getNjobtimeend())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
					 }
				 }else if((filter.getNjobtimebein() >0)|| (filter.getNjobtimeend() >0)){
					 continue ;
				 }
				 
				 //dangyuan time
				 //Log.d("yulei", "zzmm:"+filter.getNzzmm()+"rdsj begin:"+filter.getNrdtimebegin()+"end:"+filter.getNrdtimeend());
				 
				
				 
				 if((filter.getNzzmm() == 0 )||(filter.getNzzmm() == 1)){
					 
					 if(item.getM_rdsj().length() >=4){
						 if(Integer.valueOf(item.getM_rdsj().substring(0, 4))  >= ( filter.getNrdtimebegin())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
						 
						 if(filter.getNrdtimeend() != 0){
							 if(Integer.valueOf(item.getM_rdsj().substring(0, 4))  <= (filter.getNrdtimeend())){
								 shouldadd = true ;
							 }else{
								 continue ;
							 }
						 }
					 }else if((filter.getNrdtimebegin() >0) || (filter.getNrdtimeend() >0)){
						 continue ;
					 }
					 
					 
				 }
				 
				 //副科时间
				 int xrzj = filter.getXrzj_fuke()+filter.getXrzj_zhengke()+filter.getXrzj_keyuan()+filter.getXrzj_fuxian()+filter.getXrzj_zhengxian();
				 if((xrzj) >=1 &&(xrzj <= 4)){
					 
					 String ldzj = mDict.GetBmbylbandmc("ldzj", item.getM_xrzj()) ;
					 
					 if(ldzj == null){
						 continue ;
					 }
					 if(ldzj.isEmpty() == true){
						 continue ;
					 }
					 
					 switch(Integer.valueOf(ldzj)){
						 case 21:  //科员
							 if(filter.getXrzj_keyuan() == 1){
								 shouldadd = true ;
							 }else {
								 shouldadd = false ;
							 }
							 break;
						 case 20:  //副科
						 case 19:
							 if(filter.getXrzj_fuke() == 1){
								 shouldadd = true ;
								 
								 
							 }else {
								 shouldadd = false ;
							 }
							 break;
						 case 17:  //正科
						 case 18:
							 if(filter.getXrzj_zhengke() == 1){
								 shouldadd = true ;
							 }else {
								 shouldadd = false ;
							 }
							  break;
						 case 16:  //副县
						 case 15:
							 if(filter.getXrzj_fuxian() == 1){
								 shouldadd = true ;
							 }else {
								 shouldadd = false ;
							 }
							 break;
						 case 14:  //正县
						 case 13:
							 if(filter.getXrzj_zhengxian() == 1){
								 shouldadd = true ;
							 }else {
								 shouldadd = false ;
							 }
							  break;					
						 case 12:  //副地
						 case 11:
							 if(filter.getXrzj_zhengxian() == 1){
								 shouldadd = true ;
							 }else {
								 shouldadd = false ;
							 }
							 break;
						 case 10:  //正地
						 case 9:
							 if(filter.getXrzj_zhengxian() == 1){
								 shouldadd = true ;
							 }else {
								 shouldadd = false ;
							 }
							  break;
						  default:
							  if(filter.getXrzj_zhengxian() == 1){
									 shouldadd = true ;
								 }else {
									 shouldadd = false ;
								 }
							  break;
							 
					 }
					 
					 if(shouldadd == false){
						 continue ;
					 }
					 
				 }
				 
				 int ldzj = 0 ;
				 
				 String  ldzjfilter = null ;
				 
				 ldzjfilter = mDict.GetBmbylbandmc("ldzj", item.getM_xrzj());
				 
				 
				 //副科时间
				 if((filter.getNzj_fuke_min() != 0 )&&(filter.getNzj_fuke_max() != 0)){
					 
					 
					 
					 if(ldzjfilter == null){
						 continue ;
					 }
					 if(ldzjfilter.isEmpty() == true){
						 continue ;
					 }
					 
					 ldzj = Integer.valueOf(ldzjfilter) ;
					 if((ldzj != 19)&&(ldzj != 20)){
						 continue ;
					 }else if(item.getM_rxzjsj().length() >= 4){
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  >= ( filter.getNzj_fuke_min())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
						 
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  <= ( filter.getNzj_fuke_max())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
					 }else{
						 continue ;
					 }
				 }
				 
				//正科时间
				 if((filter.getNzj_zhengke_min() != 0 )&&(filter.getNzj_zhengke_max() != 0)){
					 
					
					 
					 if(ldzjfilter == null){
						 continue ;
					 }
					 if(ldzjfilter.isEmpty() == true){
						 continue ;
					 }
					 
					 ldzj = Integer.valueOf(ldzjfilter) ;
					 
					 
					 if((ldzj != 17)&&(ldzj != 18)){
						 continue ;
					 }else if(item.getM_rxzjsj().length() >= 4){
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  >= ( filter.getNzj_zhengke_min())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
						 
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  <= ( filter.getNzj_zhengke_max())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
					 }else{
						 continue ;
					 }
				 }
				 
				 //Log.d("Filter:", "fuxian:"+filter.getNzj_fuxian_min()+"max:"+filter.getNzj_fuxian_max());
				 
				 
				 
				//副县时间
				 if((filter.getNzj_fuxian_min() != 0 )&&(filter.getNzj_fuxian_max() != 0)){
					 
					
					 
					 if(ldzjfilter == null){
						 continue ;
					 }
					 if(ldzjfilter.isEmpty() == true){
						 continue ;
					 }
					 
					 ldzj = Integer.valueOf(ldzjfilter) ;
					 
					
					 //Log.d("Filter:", "ldzj:"+ldzj+"rxzjsj:"+item.getM_rxzjsj());
					 
					 if((ldzj != 15)&&(ldzj != 16)){
						 continue ;
					 }else if(item.getM_rxzjsj().length() >= 4){
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  >= ( filter.getNzj_fuxian_min())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
						 
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  <= ( filter.getNzj_fuxian_max())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
					 } else{
						 continue ;
					 }
				 }
				 
				//正县时间
				 if((filter.getNzj_zhengxian_min() != 0 )&&(filter.getNzj_zhengxian_max() != 0)){
					 
					 
					 if(ldzjfilter == null){
						 continue ;
					 }
					 if(ldzjfilter.isEmpty() == true){
						 continue ;
					 }
					 
					 ldzj = Integer.valueOf(ldzjfilter) ;
					 if((ldzj != 13)&&(ldzj != 14)){
						 continue ;
					 }else if(item.getM_rxzjsj().length() >= 4){
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  >= ( filter.getNzj_zhengxian_min())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
						 
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  <= ( filter.getNzj_zhengxian_max())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
					 }else{
						 continue ;
					 }
				 }
				 
				//现任职级时间
				 if((filter.getNzj_xianzhi_min() != 0 )&&(filter.getNzj_xianzhi_max() != 0)){
					 
					 if(item.getM_rxzjsj().length() >= 4){
					
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  >= ( filter.getNzj_xianzhi_min())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
						 
						 if(Integer.valueOf(item.getM_rxzjsj().substring(0, 4))  <= ( filter.getNzj_xianzhi_max())){
							 shouldadd = true ;
						 }else{
							 continue ;
						 }
					 }else{
						 continue ;
					 }
					 
				 }
				 
				 //suoxuezhuanye
				 
				 if(filter.getM_suoxuezhuanye().isEmpty() == false){
					 
					 if(item.getM_q_zy() == null){
						 shouldadd = false ;
					 }else if(item.getM_q_zy().indexOf(filter.getM_suoxuezhuanye()) != -1){
						 shouldadd = true  ;
					 }else {
						 shouldadd = false ;
					 }
				 }
				 
				 if(shouldadd == false){
					 continue ;
				 }
				 
				 //congshigangwei
				 if(filter.getM_congshigangwei().isEmpty() == false){
					 
					 if(item.getM_jlxx() == null){
						 shouldadd = false ;
					 }else if(item.getM_jlxx().indexOf(filter.getM_congshigangwei()) != -1){
						 shouldadd = true  ;
					 }else{
						 shouldadd = false  ;
					 }
				 }
				 if(shouldadd == false){
					 continue ;
				 }
				 
				 
				 
				 
				 if(shouldadd == true){ 
					 list.add(mListU.get(i));
				 }
			 }
			 
			 
			 OnContactsListUpdate(list);
			 
			String title = String.format("人员信息查询(查询到人员共: %d)", list.size());
			mAb.setTitle(title);
				
		 }
		 
		 public void OnlyShowList(){
			/* msideBar.setVisibility(View.GONE);
			 mSearchEditText.setVisibility(View.GONE);*/
			 
			 mAdapter.SetLetterDisabled();
		 }
		 
		 
		 public void OnCreate(ActionBar ab ){
		    
			mAb = ab ;
			 
			mListView = (ListView)mViewOfPage.findViewById(R.id.list_view_user_list);
		    	
	    	msideBar = (SideBar)mViewOfPage.findViewById(R.id.sild_bar);
	    	mdialogTxt = (TextView) mViewOfPage.findViewById(R.id.txt_dialog);
	    	//mHeader = (TextView) mViewOfPage.findViewById(R.id.contacts_list_textview_header);
		    
		    msideBar.setmTextDialog(mdialogTxt);
	    	msideBar.setOnTouchingLetterChangedListener(this);
	    	mSearchEditText = (SearchEditText)mViewOfPage.findViewById(R.id.txt_filter_edit);
	    	mSearchEditText.addTextChangedListener(this);
	    	
	    	OnContactsListUpdate(mListU);
				
		 }

		 
		 public void OnContactsListUpdate(ArrayList<UserItem> list){
				
				//mListU = (ArrayList<UserInfo>) contactmgr.GetAllContacts();
				
			 /*
				Collections.sort(list, cmp);
				*/
				mAdapter = new ContactsListAdapter(mContext0,mchild,list);
				mListU_show = list ;
				mListView.setAdapter(mAdapter);
				mListView.setOnItemClickListener(this);
				
		}
		 
		public void onTouchingLetterChanged(String str) {
			// TODO Auto-generated method stub
			int pos = mAdapter.getPositionForSection(str.charAt(0));
			
			if(pos != -1)
			    mListView.setSelection(pos);
		}

		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
			try {
				UpdateFilterResult(s.toString());
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			mTransfor.clear(); 
			for(int i = 0 ; i < mListU_show.size() ;i++){
				 mTransfor.add(new String(mListU_show.get(i).getM_sfz_no()));
			 }
			GoActivity(mListU_show.get(arg2).getM_sfz_no(),arg2);
		}
		
		
		
		private void GoActivity(String userid, int index ){
			
			
			Intent  frqueui = new Intent();
			
			
			frqueui.putExtra("items", mTransfor);
			frqueui.putExtra("index", index);
			frqueui.putExtra("user_id", userid);
			frqueui.setClass(mContext0,resume_containerActivity.class);
			frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			mContext0.startActivity(frqueui);	
			
			((Activity)mContext0).overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
		}
		
		private void UpdateFilterResult(String str) throws BadHanyuPinyinOutputFormatCombination{
			ArrayList<UserItem> resultlist = new ArrayList<UserItem>();
			
			if(str.isEmpty()){
				resultlist = mListU;
			}else{
				resultlist.clear();
				for(UserItem info:mListU){
					String name = info.getM_xm();
					
					String yinpin1 = info.getM_py_1();
					
					if(name.indexOf(str)!=-1 || PinYinKit.getPingYin(name).startsWith(str)|| PinYinKit.getPingYin(name).startsWith(str.toUpperCase().toString())
							|| yinpin1.indexOf(str)!=-1 )
						{
						resultlist.add(info);
					}
				}
				
			}
			
			mListU_show= resultlist ;
			//Collections.sort(resultlist, cmp);
			mAdapter.updateListView(resultlist);
		}
		
}
