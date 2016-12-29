package com.chinamobile.workerspace.group;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.chinamobile.workerspace.contacts.GroupItem;

public class GroupListAdapter extends BaseExpandableListAdapter {

	
	
	
	private Context mContext0;
	
	private List<String>  mGroup;
	private List<List<GroupItem>>  mChilds;
	
	
	public GroupListAdapter(Context t, List<String> g, List<List<GroupItem>> c){
		mContext0 = t;
		mGroup = g;
		mChilds = c ;
		
	}
	
	
	public void UpdateListView(List<String> g, List<List<GroupItem>> c){
		mGroup = g;
		mChilds = c ;
		notifyDataSetChanged();
	}
	
	 public TextView getGenericView(String s) {  
         // Layout parameters for the ExpandableListView  
         AbsListView.LayoutParams lp = new AbsListView.LayoutParams(  
                 ViewGroup.LayoutParams.FILL_PARENT, 60);

         TextView text = new TextView(mContext0);  
         text.setLayoutParams(lp);  
         // Center the text vertically  
         text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
         // Set the text starting position  
         text.setPadding(36, 0, 0, 0);  
         text.setTextSize(20);
         text.setText(s);  
         return text;  
     }  

	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return mChilds.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String  displaytext = mChilds.get(groupPosition).get(childPosition).getGroup_name();
		
		return getGenericView(displaytext);
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return mChilds.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mChilds.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mGroup.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        
		String  displaytext = mGroup.get(groupPosition);
		
		return getGenericView(displaytext);
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
