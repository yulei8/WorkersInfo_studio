package com.chinamobile.workerspace.statics;

import java.util.List;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.UserItem;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StaticsListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext0;
	private List<StaticsListItem> mVector;
	
	
	public StaticsListAdapter(Context context,List<StaticsListItem> vector){		
		mContext0 = context;
		mInflater = LayoutInflater.from(context);
		mVector = vector ;
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
		
		StaticsListItem item = mVector.get(position);
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.statics_list_view_item, null);
			
			viewHolder = new ViewHolder();
			

			viewHolder.tvLeft = (TextView)convertView.findViewById(R.id.statics_item_textView1);
			
			
			viewHolder.tvRight = (TextView)convertView.findViewById(R.id.statics_item_textView2);
			
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		
		if(viewHolder != null){
			
			viewHolder.tvLeft.setText(item.getTitle());
			viewHolder.tvRight.setText(String.valueOf(item.getnCount()));
			
		}
		

		return convertView;
	}

	
	final static class ViewHolder 
	{
	
		TextView tvLeft;			
		TextView tvRight;
		
	}
	
	
}
