package com.chinamobile.workerspace.group;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.chinamobile.workerspace.HistoryType;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.history.HistoryManager;
import com.chinamobile.workerspace.humanresource.humanresource_containerActivity;
import com.chinamobile.workerspace.ui.SearchEditText;
import com.chinamobile.workerspace.utils.PinYinKit;

public class GroupListActivity1 extends ExpandableListActivity implements TextWatcher {

	private List<String> mgroup;
	private List<List<GroupItem>> mchild;
	
	private List<List<GroupItem>> mcurrentchilds;
	
	private List<GroupItem> mgroupitems;
	
	private GroupListAdapter  mAdapter ;
	
	private GroupManager    mGroupmgr;
	
	private SearchEditText mSearchEditText;
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		
		GoHumanResourceActivity(mcurrentchilds.get(groupPosition).get(childPosition).getGroup_id());
		
		
		
		
		return super.onChildClick(parent, v, groupPosition, childPosition, id);
	}

	private void GoHumanResourceActivity(String groupid){
		
		
		Intent  frqueui = new Intent();
	
		frqueui.putExtra("group_id", groupid);
		frqueui.setClass(this,humanresource_containerActivity.class);
		frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(frqueui);	
		
		((Activity)this).overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_main_list);  
		
		
		
		mgroup = new ArrayList<String>();
		
		mchild = new ArrayList<List<GroupItem>>();
		
		mGroupmgr = GroupManager.GetInstance(this);
		
		
		ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
		
		InitGroupData();
		
		mAdapter = new GroupListAdapter(this,mgroup,mchild);
		
		getExpandableListView().setAdapter(mAdapter);
		
		getExpandableListView().setCacheColorHint(0); //设置拖动列表的时候防止出现黑色背景 
		
		getExpandableListView().setOnChildClickListener(this);
		//getExpandableListView().setOnGroupClickListener(this);
		
		mSearchEditText = (SearchEditText)findViewById(R.id.txt_filter_edit);
    	mSearchEditText.addTextChangedListener(this);
    	
    	
	}

	private void InitGroupData(){
		
		List<GroupItem> groups =  mGroupmgr.GetTopLevelGroup();
		
		mgroupitems = groups ;
		for(int i = 0 ; i < groups.size() ; i++){
			
			
			
			mgroup.add(groups.get(i).getGroup_name());
			List<GroupItem> childs = mGroupmgr.GetChildLevelGroup(groups.get(i).getGroup_id());
			
			if(childs.size() >0){
				mchild.add(childs);
			}		
			
			
		}
		
		mcurrentchilds= mchild ;
	
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

	private void UpdateFilterResult(String str) throws BadHanyuPinyinOutputFormatCombination{
		List<String> leve1list = new ArrayList<String>();
		List<List<GroupItem>> childlist = new ArrayList<List<GroupItem>>();
		
		List<GroupItem> leve2list = new ArrayList<GroupItem>();
		
		
		if(str.isEmpty()){
			leve1list = mgroup;
			childlist = mchild ;
		}else{
			leve1list.clear();
			childlist.clear();
			leve2list.clear();
			for(GroupItem info:mgroupitems){
				String name = info.getGroup_name();
				List<GroupItem> childs ;
				if(name.indexOf(str)!=-1 || PinYinKit.getPingYin(name).startsWith(str)|| PinYinKit.getPingYin(name).startsWith(str.toUpperCase().toString())
						||PinYinKit.getPingYin(name).indexOf(str)!=-1||PinYinKit.getPingYin(name).indexOf(str.toUpperCase())!=-1)
				{
					leve1list.add(name);
					childs = mGroupmgr.GetChildLevelGroup(info.getGroup_id());
					
					if(childs.size() >0){
						childlist.add(childs);
					}
				}else{
					childs = mGroupmgr.GetChildLevelGroup(info.getGroup_id());
					
					leve2list.clear();
					//Log.d("childs", "count:"+childs.size());
					for(GroupItem child:childs){
						name = child.getGroup_name();
						//Log.d("child", "name:"+name+"pinyin:"+PinYinKit.getPingYin(name)+"str:"+str);
						if(name.indexOf(str)!=-1 || PinYinKit.getPingYin(name).startsWith(str)|| PinYinKit.getPingYin(name).startsWith(str.toUpperCase().toString())
								||PinYinKit.getPingYin(name).indexOf(str)!=-1||PinYinKit.getPingYin(name).indexOf(str.toUpperCase())!=-1){
							if(leve1list.contains(info.getGroup_name()) == false){
								leve1list.add(info.getGroup_name());
							}
							
							leve2list.add(child);
						}
					}
					
					if(leve2list.size() >0){
						childlist.add(leve2list);
					}
					
				}
			}
	
		}
		
		mcurrentchilds = childlist ;
		//Collections.sort(resultlist, cmp);
		mAdapter.UpdateListView(leve1list,childlist);
	}
	
	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		try {
			UpdateFilterResult(arg0.toString());
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
