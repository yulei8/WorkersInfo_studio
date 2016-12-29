package com.chinamobile.workerspace.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chinamobile.workerspace.R;

import com.chinamobile.workerspace.contacts.ResumeItem;

public class ExperienceListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext0;
	private List<ResumeItem> mVector;
	
	
	
	public ExperienceListAdapter(Context context,List<ResumeItem> vector){		
		mContext0 = context;
		mInflater = LayoutInflater.from(context);
		mVector = vector ;
		
	}
	
	public void updateListView(List<ResumeItem> list)
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

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		
		ResumeItem item = mVector.get(position);
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.person_experience_list_item, null);
			
			viewHolder = new ViewHolder();
			
			viewHolder.tvFirstLine = (TextView)convertView.findViewById(R.id.experience_first_line);
			viewHolder.tvSecondLine = (TextView)convertView.findViewById(R.id.experience_second_line);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		
		if(viewHolder != null){
			
			
			StringBuffer firstline = new StringBuffer();
			
			//firstline.append(String.format("%s %s %s %s 出生",item.getUserName(),item.getSex(),item.getNation(),item.getBirthdate()));
			
			firstline.append(String.format("%s - %s",item.getStart_time(),item.getOver_time()));
			
			viewHolder.tvFirstLine.setText(firstline);
			
			StringBuffer secondline = new StringBuffer();
			
			secondline.append(String.format("%s(%s)",item.getJob_desc(),item.getZwsf()));
			
			
			viewHolder.tvSecondLine.setText(secondline);
            
			
		}
		

		return convertView;
	}

	
	final static class ViewHolder 
	{
		
		TextView tvFirstLine;
		TextView tvSecondLine;
	}
	
	
}
