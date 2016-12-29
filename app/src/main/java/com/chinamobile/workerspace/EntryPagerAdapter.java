package com.chinamobile.workerspace;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinamobile.workerspace.contacts.ContactsListActivity;
import com.chinamobile.workerspace.favorite.FavorateActivity;
import com.chinamobile.workerspace.group.GroupListActivity;
import com.chinamobile.workerspace.group.GroupListActivity1;
import com.chinamobile.workerspace.history.HistoryActivity;
import com.chinamobile.workerspace.settings.SettingsActivity;
import com.chinamobile.workerspace.statics.StaticsListActivity;
import com.chinamobile.workerspace.statics.Statics_containerActivity;
import com.chinamobile.workerspace.utils.CustomDialog;

/**
 * 鑷畾涔夐�傞厤鍣�
 * @author wulianghuan
 *
 */
public class EntryPagerAdapter extends PagerAdapter {
	Vibrator vibrator;
	ArrayList<ImageInfo> data;
	private Context mContext0;
	LayoutParams params;

	final Class[] toClses = {GroupListActivity.class,ContactsListActivity.class,StaticsListActivity.class
			,HistoryActivity.class,FavorateActivity.class,FavorateActivity.class,
			SettingsActivity.class,Statics_containerActivity.class}; 
	
	final int[] flags = {0,0,0,0,0,1,0,0};
	
	public EntryPagerAdapter(Context activity, ArrayList<ImageInfo> data) {
		mContext0 = activity;
		this.data = data;
		vibrator = (Vibrator) activity
				.getSystemService(Context.VIBRATOR_SERVICE);
	}

	
	public int getCount() {
		// TODO Auto-generated method stub
		return (data.size()/9+1);
	}

	private void GoActivity(int index){
		
		if(index >= toClses.length){
			return ;
		}
		
		if(index == toClses.length -1){
			ShowPromptExitDlg();
			return ;
		}
		
		Intent  frqueui = new Intent();
		
		frqueui.putExtra("start_flag", flags[index]);
		frqueui.setClass(mContext0,toClses[index]);
		frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		mContext0.startActivity(frqueui);	
		
		((Activity)mContext0).overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
	}
	
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	
	/**
	 * 获取圆角位图的方法
	 * @param bitmap 需要转化成圆角的位图
	 * @param pixels 圆角的度数，数值越大，圆角越大
	 * @return 处理后的圆角位图
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	
	public Object instantiateItem(ViewGroup container, final int index) {
		//Log.v("test", index + "index");

		View view = ((Activity) mContext0).getLayoutInflater().inflate(R.layout.entry_grid, null);
		GridView gridView = (GridView) view.findViewById(R.id.gridView1);
		gridView.setNumColumns(2);
		
		gridView.setVerticalSpacing(50);
		gridView.setHorizontalSpacing(50);
		gridView.setAdapter(new BaseAdapter() {

			
			public int getCount() {
				return 8;
			}

			
			public Object getItem(int position) {
				return position;
			}

			
			public long getItemId(int position) {
				return position;
			}
			
			public View getView(int position, View convertView, ViewGroup parent) {
				
				Log.v("test", index + "index position:"+position);
				
				
				View item = LayoutInflater.from(mContext0).inflate(
						R.layout.entry_grid_item, null);
				ImageView iv = (ImageView) item.findViewById(R.id.imageView1);
				RelativeLayout relativeLayout = (RelativeLayout)item.findViewById(R.id.relativeLayout);
				iv.setImageResource((data.get(index * 8 + position)).imageId);
				
				Drawable drawable = mContext0.getResources().getDrawable((data.get(index * 8 + position)).bgId);
				BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
				Bitmap bitmap = bitmapDrawable.getBitmap();
				BitmapDrawable bbb = new BitmapDrawable(toRoundCorner(bitmap, 30));

				relativeLayout.setBackground(bbb);
				//relativeLayout.setBackgroundResource((data.get(index * 8 + position)).bgId);
				relativeLayout.getBackground().setAlpha(225);
				TextView tv = (TextView) item.findViewById(R.id.msg);
				tv.setText((data.get(index * 8 + position)).imageMsg);
			    
				
				
				if((parent.getWidth() > 200)&&(parent.getHeight()>450)){
					item.setLayoutParams(new AbsListView.LayoutParams((int) ((parent.getWidth()-80) / 2) , (int) ((parent.getHeight()-160) / 4)));
				}	
				return item;
			}
		});

		
		
		gridView.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				vibrator.vibrate(50);
				
				View view = arg1;
				
				GoActivity(arg2);
				
				
			}
			
		});
		
		// gridView.setOnTouchListener(new View.OnTouchListener() {
		//
		// 
		// public boolean onTouch(View v, MotionEvent event) {
		//
		// return true;
		// }
		// });
		((ViewPager) container).addView(view);

		return view;
	}
	
	private void ShowPromptExitDlg(){
		new CustomDialog.Builder((Activity) mContext0)
		.setTitle(R.string.important_tips_title)
		.setMessage(R.string.exist_tips_body)
		.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						
						 System.exit(0);

					}
				})
		.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int which) {
						
					}
				}).create().show();
	}
}
