package com.chinamobile.workerspace.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.FamilyItem;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FamilyListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext0;
	private List<FamilyItem> mVector;
	
	
	
	public FamilyListAdapter(Context context,List<FamilyItem> vector){		
		mContext0 = context;
		mInflater = LayoutInflater.from(context);
		mVector = vector ;
		
	}
	
	public void updateListView(List<FamilyItem> list)
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
		
		FamilyItem item = mVector.get(position);
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.person_family_list_item, null);
			
			viewHolder = new ViewHolder();
			
			viewHolder.tvFirstLine = (TextView)convertView.findViewById(R.id.family_item_first_line);
			viewHolder.tvSecondLine = (TextView)convertView.findViewById(R.id.family_item_second_line);
			viewHolder.tvThirdLine = (TextView)convertView.findViewById(R.id.family_item_third_line);
			viewHolder.tvFourthLine = (TextView)convertView.findViewById(R.id.family_item_fourth_line);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		
		if(viewHolder != null){
			
			
			StringBuffer firstline = new StringBuffer();
			
			//firstline.append(String.format("%s %s %s %s 出生",item.getUserName(),item.getSex(),item.getNation(),item.getBirthdate()));
			
			firstline.append(item.getM_cw());
			
			viewHolder.tvFirstLine.setText(firstline);
			
			StringBuffer secondline = new StringBuffer();
			
			secondline.append(String.format("%s  %s  %s 出生   %s",item.getM_xm(),item.getM_mz(),item.getM_csrq(),item.getM_zzmm()));
			
			
			
			viewHolder.tvSecondLine.setText(secondline);
            
		
			
			
			viewHolder.tvThirdLine.setText(item.getM_gzdw());
			
			viewHolder.tvFourthLine.setText(item.getM_zw());
			

			
		}
		

		return convertView;
	}

	
	final static class ViewHolder 
	{
		
		TextView tvFirstLine;
		TextView tvSecondLine;
		TextView tvThirdLine;
		TextView tvFourthLine;
	}
	
	
}
