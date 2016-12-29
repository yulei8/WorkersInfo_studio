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
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.favorite.MyDeleteCallback;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FavorateUserListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext0;
	private List<UserItem> mVector;
	
	private String  mImgpath;
	
	private Button  mDelBtn ;
	
	private MyDeleteCallback  mycallback;
	
	
	public void SetDeleteCallBack(MyDeleteCallback  callback){
		mycallback = callback ;
	}
	
	public FavorateUserListAdapter(Context context,List<UserItem> vector){		
		mContext0 = context;
		mInflater = LayoutInflater.from(context);
		mVector = vector ;
		mImgpath = SharedPrefs.GetInstance(context).GetImagePathPrefs();
	}
	
	public void updateListView(List<UserItem> list)
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
		
		UserItem item = mVector.get(position);
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.favorate_listview_item, null);
			
			viewHolder = new ViewHolder();
			

			
			viewHolder.tvIcon = (ImageView)convertView.findViewById(R.id.contact_item_icon_swipe);

			
			viewHolder.tvFirstLine = (TextView)convertView.findViewById(R.id.contact_item_text_name);
			viewHolder.tvSecondLine = (TextView)convertView.findViewById(R.id.contact_item_second_line);
			viewHolder.tvThirdLine = (TextView)convertView.findViewById(R.id.contact_item_third_line);
			viewHolder.tvFourthLine = (TextView)convertView.findViewById(R.id.contact_item_fourth_line);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		mDelBtn = (Button)convertView.findViewById(R.id.recent_del_btn);
		mDelBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
     
				mycallback.DeleteItem(position,0);
			}});
		
		if(viewHolder != null){
			
	
			
			StringBuffer firstline = new StringBuffer();
			
			firstline.append(String.format("%s %s %s %s 出生",item.getM_xm(),item.getM_xb(),item.getM_mz(),item.getM_csrq()));
			
			
			viewHolder.tvFirstLine.setText(firstline);
			
			StringBuffer secondline = new StringBuffer();
			
			secondline.append(String.format("%s 参加工作  %s 入党",item.getM_cjgzsj(),item.getM_rdsj()));
			
			
			
			viewHolder.tvSecondLine.setText(secondline);
            
		
			
			
			viewHolder.tvThirdLine.setText(item.getM_xrzj());
			
			viewHolder.tvFourthLine.setText(item.getM_xrzw()+" "+item.getM_xrzj());
			
			
			String s = "file://" + mImgpath+"/"+item.getM_sfz_no()+".jpg";
			
			//Log.d("getview", "image name:"+s);
			ImageLoader.getInstance().displayImage(s, viewHolder.tvIcon);
			
			
		}
		

		return convertView;
	}

	
	final static class ViewHolder 
	{
		ImageView tvIcon;
		

		TextView tvFirstLine;
		TextView tvSecondLine;
		TextView tvThirdLine;
		TextView tvFourthLine;
	}
	
	
}
