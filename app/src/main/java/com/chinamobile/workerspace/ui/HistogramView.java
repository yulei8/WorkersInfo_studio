/**
 * 宸ョ▼鍚�: histogram
 * 鏂囦欢鍚�: HistogramView.java
 * 鍖呭悕: com.style.histogram
 * 鏃ユ湡: 2014-4-21涓嬪崍8:23:38
 * Copyright (c) 2014
 *
*/

package com.chinamobile.workerspace.ui;
 

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 绫诲悕: HistogramView <br/>
 * 鍔熻兘: 鏌辩姸鍥�. <br/>
 * 鏃ユ湡: 2014-4-21 涓嬪崍8:23:38 <br/>
 *
 * @author   msl
 * @version  	 
 */
public class HistogramView extends View implements Runnable{
	private static final String TAG = HistogramView.class.getSimpleName();
	
	private int comWidth; //鎺т欢瀹藉害
	private int comHeight;//鎺т欢楂樺害
	
	private View rateView;//杩涘害鏉�
	
	private View rateTopView; //杩涘害鏉￠《閮╒iew
	
	private String rateBackgroundColor;//杩涘浘鏉¤儗鏅鑹�
	
	private int rateBackgroundId; //杩涘浘鏉¤儗鏅浘鐗噄d
	
	private Bitmap rataBackgroundBitmap;
	
	private boolean isHasRateTopView; //杩涘害鏉￠《閮╒iew
	
	private int rateHeight; //杩涘害鏉＄殑楂�
	
	private int rateWidth; //杩涘害鏉＄殑瀹�
	
	private int rateAnimValue;//杩涘害鏉″姩鐢婚珮搴�
	
	private int orientation; //璁剧疆鏌辩姸鍥炬柟鍚�
	
	private double progress;//璁剧疆杩涘害  1涓烘渶澶у��
	
	private boolean isAnim = true; //鏄惁鍔ㄧ敾鏄剧ず缁熻鏉�
	
    private Handler handler = new Handler();//鍔ㄧ敾handler
	
	private int animRate = 1; //鍔ㄧ敾閫熷害   浠ユ瘡1姣璁�
	
	private int animTime = 1;//鍔ㄧ敾寤惰繜鎵ц鏃堕棿
	
	private Canvas canvas;//鐢诲竷
	
	public HistogramView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle); 
	}

	public HistogramView(Context context, AttributeSet attrs) {
		super(context, attrs); 
	}

	public HistogramView(Context context) {
		super(context); 
	}
	

	 
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) { 
		super.onSizeChanged(w, h, oldw, oldh); 
		//鍒濆鍖栨帶浠跺拰杩涘害鐨勬潯鐩稿叧鍙傛暟
		comWidth = w;
		comHeight = h;
		if(orientation==LinearLayout.HORIZONTAL){
			rateWidth = (int) (w*progress); 
			rateHeight = h;
		}else{
			rateHeight = (int) (h*progress); 
			rateWidth = w;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) { 
		super.onDraw(canvas);
		this.canvas = canvas;
		Paint paint = new Paint();
		paint.setAntiAlias(true);		
		paint.setStyle(Paint.Style.FILL);
		Log.d(TAG, "onDraw  rateBackgroundColor===="+rateBackgroundColor);
		if(rateBackgroundColor!=null){
			drawViewWithColor(paint,isAnim);
		}else if(rateBackgroundId!=-1){
			drawViewWithBitmap(paint, isAnim);
		}
		
	}
	
	/**
	 * 
	 * drawViewWithColor:(缁樺埗棰滆壊杩涘害鏉�). <br/>
	 * @author msl
	 * @param paint
	 * @param isAnim
	 * @since 1.0
	 */
	private void drawViewWithColor(Paint paint,boolean isAnim){
		paint.setColor(Color.parseColor(rateBackgroundColor));
		Log.d(TAG, "rateBackgroundColor===="+rateBackgroundColor);
		if(isAnim){
			handler.postDelayed(this, animTime); 
			if(orientation==LinearLayout.HORIZONTAL){//姘村钩鏂瑰悜鏌辩姸鍥�
				canvas.drawRect(0, 0, rateAnimValue, comHeight, paint); 
			}else{//鍨傜洿鏂瑰悜鏌辩姸鍥�
				canvas.drawRect(0, comHeight-rateAnimValue, comWidth, comHeight, paint); 
			}
		}else {
			if(orientation==LinearLayout.HORIZONTAL){//姘村钩鏂瑰悜鏃犲姩鐢绘煴鐘跺浘
				canvas.drawRect(0, 0, rateWidth, comHeight, paint);
			}else{//鍨傜洿鏂瑰悜鏃犲姩鐢绘煴鐘跺浘
				canvas.drawRect(0, comHeight-rateHeight, comWidth, comHeight, paint); 
			}
		}
		
	}
	/**
	 * 
	 * drawViewWithBitmap:(缁樺埗鍥剧墖杩涘害鏉�). <br/>
	 * @author msl
	 * @param paint
	 * @param isAnim
	 * @since 1.0
	 */
	private void drawViewWithBitmap(Paint paint,boolean isAnim){
		Log.d(TAG, "bitmap===="+rataBackgroundBitmap);
		RectF dst = null;
		if(isAnim){
			handler.postDelayed(this, animTime); 
			if(orientation==LinearLayout.HORIZONTAL){//姘村钩鏂瑰悜鏌辩姸鍥�
			    dst = new RectF(0, 0, rateAnimValue, comHeight);			    
				canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
			}else{//鍨傜洿鏂瑰悜鏌辩姸鍥�
				dst = new RectF(0, comHeight-rateAnimValue, comWidth, comHeight);
				canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
			}
		}else {
			if(orientation==LinearLayout.HORIZONTAL){//姘村钩鏂瑰悜鏃犲姩鐢绘煴鐘跺浘
				dst = new RectF(0, 0, rateWidth, comHeight);
				canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
			}else{//鍨傜洿鏂瑰悜鏃犲姩鐢绘煴鐘跺浘 
				dst = new RectF(0, comHeight-rateHeight, comWidth, comHeight);
				canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
			}
		}
	}

  
  public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress; 
	}


	public View getRateView() {
		return rateView;
	}

	public void setRateView(View rateView) {
		this.rateView = rateView;
	}

	public int getRateHeight() {
		return rateHeight;
	}

	public void setRateHeight(int rateHeight) {
		this.rateHeight = rateHeight;
	}

	public int getRateWidth() {
		return rateWidth;
	}

	public void setRateWidth(int rateWidth) {
		this.rateWidth = rateWidth;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}


	public boolean isAnim() {
		return isAnim;
	}

	public void setAnim(boolean isAnim) {
		this.isAnim = isAnim;
	}

	public int getAnimRate() {
		return animRate;
	}

	public void setAnimRate(int animRate) {
		this.animRate = animRate;
	}

	public String getRateBackgroundColor() {
		return rateBackgroundColor;
	}

	public void setRateBackgroundColor(String rateBackgroundColor) {
		this.rateBackgroundColor = rateBackgroundColor;
		rateBackgroundId = -1;
		rataBackgroundBitmap = null;
	}
	
	

	public int getRateBackgroundId() {
		return rateBackgroundId;
	}

	public void setRateBackgroundId(int rateBackground) {
		this.rateBackgroundId = rateBackground;
		rataBackgroundBitmap = BitmapFactory.decodeResource(getResources(), rateBackgroundId);
		rateBackgroundColor = null;
	}

	/**
	 * 
	 *鍒锋柊鐣岄潰
	 * @see java.lang.Runnable#run()
	 */
	
	public void run() {
		if(orientation==LinearLayout.HORIZONTAL&&(rateAnimValue<=rateWidth)){
			rateAnimValue+=animRate;
			invalidate();
		}else if(orientation==LinearLayout.VERTICAL&&(rateAnimValue<=rateHeight)){
			rateAnimValue+=animRate;
			invalidate();
		}else{ 
			handler.removeCallbacks(this);
			rateAnimValue = 0;
		}
		
	}
	
	
	
}

