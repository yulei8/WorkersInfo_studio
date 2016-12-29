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

import com.chinamobile.workerspace.HistoryType;
import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.favorite.MyDeleteCallback;
import com.chinamobile.workerspace.history.HistoryItem;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HistoryListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext0;
	private List<HistoryItem> mVector;
	
	private String  mImgpath;
	
	private Button  mDelBtn ;
	
	private MyDeleteCallback  mycallback;
	
	
	public void SetDeleteCallBack(MyDeleteCallback  callback){
		mycallback = callback ;
	}
	
	public HistoryListAdapter(Context context,List<HistoryItem> vector){		
		mContext0 = context;
		mInflater = LayoutInflater.from(context);
		mVector = vector ;
		mImgpath = SharedPrefs.GetInstance(context).GetImagePathPrefs();
	}
	
	public void updateListView(List<HistoryItem> list)
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
		
		HistoryItem item = mVector.get(position);
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.history_listview_item, null);
			
			viewHolder = new ViewHolder();
			

		

			
			viewHolder.tvFirstLine = (TextView)convertView.findViewById(R.id.contact_item_text_name_swipe);
			viewHolder.tvSecondLine = (TextView)convertView.findViewById(R.id.contact_item_second_line_swipe);
			viewHolder.tvRightLine = (TextView)convertView.findViewById(R.id.contact_item_right_line_swipe);
			
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
			
			
			
			
			viewHolder.tvFirstLine.setText(item.getItem_title());
			
			
			
			if(item.getItem_type() == HistoryType.QUERY_GROUP ){
				
				viewHolder.tvSecondLine.setText(mContext0.getString(R.string.group_query));
				
			}else if(item.getItem_type() == HistoryType.QUERY_USER){
				viewHolder.tvSecondLine.setText(mContext0.getString(R.string.user_query));
			}else if(item.getItem_type() == HistoryType.QUERY_STATIC){
				viewHolder.tvSecondLine.setText(mContext0.getString(R.string.static_query));
			}
			
			viewHolder.tvRightLine.setText(item.getAdd_time());

			
		}
		

		return convertView;
	}

	
	final static class ViewHolder 
	{
		

		TextView tvFirstLine;
		TextView tvSecondLine;
		TextView tvRightLine;
	}
	
	
}
