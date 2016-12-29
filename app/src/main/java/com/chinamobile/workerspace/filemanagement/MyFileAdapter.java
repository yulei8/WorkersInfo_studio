package com.chinamobile.workerspace.filemanagement;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinamobile.workerspace.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFileAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private FileChooserActivity mMainActivity;
	UserOperate mUserOperate;
	Utils utils;

	public MyFileAdapter(FileChooserActivity mainActivity, UserOperate userOperate) {
		mMainActivity = mainActivity;
		mUserOperate = userOperate;
		mInflater = LayoutInflater.from(mainActivity);
		utils=new Utils();
	}

	
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listview_item, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.itemImage);
			holder.title = (TextView) convertView.findViewById(R.id.itemTitle);
			holder.count = (TextView) convertView.findViewById(R.id.itemCount);
			holder.time = (TextView) convertView.findViewById(R.id.itemTime);
			holder.size = (TextView) convertView.findViewById(R.id.itemSize);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (mMainActivity.getListItem().get(position).get(FileManager.IMAGE) instanceof Bitmap)
			holder.image.setImageBitmap((Bitmap) mMainActivity.getListItem()
					.get(position).get(FileManager.IMAGE));
		else
			holder.image.setImageResource((Integer) (mMainActivity
					.getListItem().get(position).get(FileManager.IMAGE)));
		holder.title.setText((String) mMainActivity.getListItem().get(position)
				.get(FileManager.TITLE));
		holder.count.setText((String) mMainActivity.getListItem().get(position)
				.get(FileManager.COUNT));
		holder.time.setText(utils.timeFormat((Date) mMainActivity.getListItem()
				.get(position).get(FileManager.TIME)));
		if ((Boolean) mMainActivity.getListItem().get(position)
				.get(FileManager.IS_DIR))
			holder.size.setText("");
		else
			holder.size.setText(utils.sizeAddUnit((Long) mMainActivity.getListItem()
					.get(position).get(FileManager.SIZE)));
		
		return convertView;
	}

	static class ViewHolder {
		private ImageView image;
		private TextView title;
		private TextView count;
		private TextView time;
		private TextView size;
	}

	
	public int getCount() {
		return mMainActivity.getListItem().size();
	}

	
	public Object getItem(int position) {
		return mMainActivity.getListItem().get(position);
	}

	
	public long getItemId(int position) {
		return position;
	}
}
