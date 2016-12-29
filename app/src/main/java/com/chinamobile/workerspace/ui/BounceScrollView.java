package com.chinamobile.workerspace.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * ScrollVie
 */
public class BounceScrollView extends ScrollView {
	private View inner;// 瀛╁瓙View

	private float y;// 鐐瑰嚮鏃秠鍧愭爣

	private Rect normal = new Rect();// 鐭╁舰(杩欓噷鍙槸涓舰寮忥紝鍙槸鐢ㄤ簬鍒ゆ柇鏄惁闇�瑕佸姩鐢�.)

	private boolean isCount = false;// 鏄惁寮�濮嬭绠�

	public BounceScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/***
	 * 
	 */
	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			inner = getChildAt(0);
		}
	}

	/***
	 * 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (inner != null) {
			commOnTouchEvent(ev);
		}

		return super.onTouchEvent(ev);
	}

	/***
	 * 
	 * 
	 * @param e
	 */
	public void commOnTouchEvent(MotionEvent e) {
		int action = e.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			// 鎵嬫寚鏉惧紑.
			if (isNeedAnimation()) {
				animation();
				isCount = false;
			}
			break;
		
		case MotionEvent.ACTION_MOVE:
			final float preY = y;// 鎸変笅鏃剁殑y鍧愭爣
			float nowY = e.getY();// 鏃舵椂y鍧愭爣
			int deltaY = (int) (preY - nowY);// 婊戝姩璺濈
			if (!isCount) {
				deltaY = 0; // 鍦ㄨ繖閲岃褰�0.
			}

			y = nowY;
			// 褰撴粴鍔ㄥ埌鏈�涓婃垨鑰呮渶涓嬫椂灏变笉浼氬啀婊氬姩锛岃繖鏃剁Щ鍔ㄥ竷灞�
			if (isNeedMove()) {
				// 鍒濆鍖栧ご閮ㄧ煩褰�
				if (normal.isEmpty()) {
					// 淇濆瓨姝ｅ父鐨勫竷灞�浣嶇疆
					normal.set(inner.getLeft(), inner.getTop(),
							inner.getRight(), inner.getBottom());
				}
//				Log.e("jj", "鐭╁舰锛�" + inner.getLeft() + "," + inner.getTop()
//						+ "," + inner.getRight() + "," + inner.getBottom());
				// 绉诲姩甯冨眬
				inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
						inner.getRight(), inner.getBottom() - deltaY / 2);
			}
			isCount = true;
			break;

		default:
			break;
		}
	}

	/***
	 * 鍥炵缉鍔ㄧ敾
	 */
	public void animation() {
		// 寮�鍚Щ鍔ㄥ姩鐢�
		TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
				normal.top);
		ta.setDuration(200);
		inner.startAnimation(ta);
		// 璁剧疆鍥炲埌姝ｅ父鐨勫竷灞�浣嶇疆
		inner.layout(normal.left, normal.top, normal.right, normal.bottom);

//		Log.e("jj", "鍥炲綊锛�" + normal.left + "," + normal.top + "," + normal.right
//				+ "," + normal.bottom);

		normal.setEmpty();

	}

	// 鏄惁闇�瑕佸紑鍚姩鐢�
	public boolean isNeedAnimation() {
		return !normal.isEmpty();
	}

	/***
	 * 鏄惁闇�瑕佺Щ鍔ㄥ竷灞� inner.getMeasuredHeight():鑾峰彇鐨勬槸鎺т欢鐨勬�婚珮搴�
	 * 
	 * getHeight()锛氳幏鍙栫殑鏄睆骞曠殑楂樺害
	 * 
	 * @return
	 */
	public boolean isNeedMove() {
		int offset = inner.getMeasuredHeight() - getHeight();
		int scrollY = getScrollY();
//		Log.e("jj", "scrolly=" + scrollY);
		// 0鏄《閮紝鍚庨潰閭ｄ釜鏄簳閮�
		if (scrollY == 0 || scrollY == offset) {
			return true;
		}
		return false;
	}

}
