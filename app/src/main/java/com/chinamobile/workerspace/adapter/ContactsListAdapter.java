package com.chinamobile.workerspace.adapter;

import java.util.List;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.nostra13.universalimageloader.core.ImageLoader;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactsListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext0;
	private List<UserItem> mVector;
	
	private List<List<GroupItem>> mGroup ;
	private String  mImgpath;
	
	private int    lettershow = 1 ;
	
	public ContactsListAdapter(Context context,List<List<GroupItem>> group,List<UserItem> vector){		
		mContext0 = context;
		mInflater = LayoutInflater.from(context);
		mVector = vector ;
		mGroup= group ;
		mImgpath = SharedPrefs.GetInstance(context).GetImagePathPrefs();
	}
	
	public void updateListView(List<UserItem> list)
	{
		this.mVector = list;
		notifyDataSetChanged();
	}
	
	public void SetLetterDisabled(){
		lettershow = 0 ;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return mVector.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mVector.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		
		UserItem item = mVector.get(position);
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.contacts_list_item, null);
			
			viewHolder = new ViewHolder();
			
			
			
			viewHolder.tvIcon = (ImageView)convertView.findViewById(R.id.contact_item_icon);
			
			viewHolder.tvLetter = (TextView)convertView.findViewById(R.id.txt_catalog);
			
			
			viewHolder.tvFirstLine = (TextView)convertView.findViewById(R.id.contact_item_text_name);
			viewHolder.tvSecondLine = (TextView)convertView.findViewById(R.id.contact_item_second_line);
			viewHolder.tvThirdLine = (TextView)convertView.findViewById(R.id.contact_item_third_line);
			viewHolder.tvFourthLine = (TextView)convertView.findViewById(R.id.contact_item_fourth_line);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		
		if(viewHolder != null){
			
			if(lettershow == 1){
			int section = getSectionForPosition(position);
			if(position == getPositionForSection(section)){
				viewHolder.tvLetter.setVisibility(View.VISIBLE);
				viewHolder.tvLetter.setText(item.getM_py_1().substring(0, 1).toUpperCase());
			}else{
				viewHolder.tvLetter.setVisibility(View.GONE);
			}
			}else{
				viewHolder.tvLetter.setVisibility(View.GONE);
			}
	
			
			StringBuffer firstline = new StringBuffer();
			
			firstline.append(String.format("%s %s %s %s 出生",item.getM_xm(),item.getM_xb(),item.getM_mz(),item.getM_csrq()));
			
			
			viewHolder.tvFirstLine.setText(firstline);
			
			StringBuffer secondline = new StringBuffer();
			
			secondline.append(String.format("%s 参加工作  %s 入党",item.getM_cjgzsj(),item.getM_rdsj()));
			
			
			
			viewHolder.tvSecondLine.setText(secondline);
            
		
			
			
			
			viewHolder.tvThirdLine.setText(item.getM_xrzw()+" "+item.getM_xrzj());
			
			viewHolder.tvFourthLine.setText(item.getM_xrzj());
			
			
			String s = "file://" + mImgpath+"/"+item.getM_sfz_no()+".jpg";
			
			Log.d("getview", "image name:"+s);
			ImageLoader.getInstance().displayImage(s, viewHolder.tvIcon);
			
			
		}
		

		return convertView;
	}

	
	final static class ViewHolder 
	{
		ImageView tvIcon;
		
		TextView tvLetter;
		
		
		TextView tvFirstLine;
		TextView tvSecondLine;
		TextView tvThirdLine;
		TextView tvFourthLine;
	}
	
	public int getPositionForSection(int section) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getCount(); i++)
		{
			String sortStr = mVector.get(i).getM_py_1();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section)
				return i;
		}
		return -1;
	}

	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		
		
		if(mVector.get(position).getM_py_1()!= null){
			return mVector.get(position).getM_py_1().toUpperCase().charAt(0);
		}else{
			return 0 ;
		}
	/*	
		int sectionindex = 0 ;
		int baseindex  = 0 ;
		
		int total = 0 ;
		for(int i = 0 ; i < mGroup.size(); i++){
			if(i >0){
				baseindex += mGroup.get(i-1).size() ; 
			}
			
			for(int j =0 ; j < mGroup.get(i).size();j++){
                
				sectionindex = baseindex+j ;
				
				
			}
		}
		
		
		return sectionindex ;*/
		
	}

	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}
}
