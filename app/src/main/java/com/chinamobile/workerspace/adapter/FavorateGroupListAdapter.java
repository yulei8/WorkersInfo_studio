package com.chinamobile.workerspace.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.favorite.MyDeleteCallback;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FavorateGroupListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext0;
	private List<GroupItem> mVector;
	
	private String  mImgpath;
	
	private Button  mDelBtn ;
	
	private MyDeleteCallback  mycallback;
	
	
	public void SetDeleteCallBack(MyDeleteCallback  callback){
		mycallback = callback ;
	}
	
	public FavorateGroupListAdapter(Context context,List<GroupItem> vector){		
		mContext0 = context;
		mInflater = LayoutInflater.from(context);
		mVector = vector ;
		mImgpath = SharedPrefs.GetInstance(context).GetImagePathPrefs();
	}
	
	public void updateListView(List<GroupItem> list)
	{
		this.mVector = list;
		notifyDataSetChanged();
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

	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		
		GroupItem item = mVector.get(position);
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.favorate_group_listview_item, null);
			
			viewHolder = new ViewHolder();
			

			
			viewHolder.tvIcon = (ImageView)convertView.findViewById(R.id.contact_item_icon_swipe);

			
			viewHolder.tvFirstLine = (TextView)convertView.findViewById(R.id.contact_item_text_name_swipe);
			viewHolder.tvSecondLine = (TextView)convertView.findViewById(R.id.contact_item_second_line_swipe);
			
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		mDelBtn = (Button)convertView.findViewById(R.id.recent_del_btn);
		mDelBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
     
				mycallback.DeleteItem(position,1);
			}});
		
		if(viewHolder != null){
			
	
			
			StringBuffer firstline = new StringBuffer();
			
			
			
			
			viewHolder.tvFirstLine.setText(item.getGroup_name());
			
			viewHolder.tvSecondLine.setText("test");
            

			
		}
		

		return convertView;
	}

	
	final static class ViewHolder 
	{
		ImageView tvIcon;
		

		TextView tvFirstLine;
		TextView tvSecondLine;
	}
	
	
}
