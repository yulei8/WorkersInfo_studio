package com.chinamobile.workerspace.draw;

import java.util.List;

import com.chinamobile.workerspace.R;

import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;

public class BarGraphView extends View implements Runnable{
	private final String TAG = BarGraphView.class.getName();
    //����
    private Paint mPaint;
    //����
    private String title;
    //������ɫ
    private int titleColor;
    //�����С
    private float titleSize;
    //X��������ֵ
    private float maxAxisValueX = 900;
    //X�����̶�������
    private int axisDivideSizeX = 9;
    //Y��������ֵ
    private float maxAxisValueY = 700;
    //Y�����̶�������
    private int axisDivideSizeY = 7;
    //��ͼ���
    private int width;
    //��ͼ�߶�
    private int height;
    //���ԭ��λ��
    private int originX = 20;
    private int originY = 20;
    
    private int fontsize = 10 ;
    
    private int animTime = 10;
    
    private int animRate = 1;
    
    private int offestX = 0 ;
    private int lastcoorX = 0 ;
    private int CurrcoorX = 0 ;
    private int xspace = 10 ; //x jian ge
    
    
    private int gap = 20 ;
    
    private float minTopy ;
    
    
    private boolean  animShow = false ;
    private int rateAnimValue;
    
    private Handler handler = new Handler();
    
    private int  fontwidth = 0 ;
    private int  fontheight = 0 ;
    
    //��״ͼ���
    private int columnInfo[][];
 
    private List<String> mItems;
    public BarGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //��������
        mPaint = new Paint();
        //��ȡ���õ�����ֵ
        TypedArray mArray = context.obtainStyledAttributes(attrs, R.styleable.BarGraphView);
        
        
        titleColor = mArray.getColor(R.styleable.BarGraphView_barGraph_titleColor, Color.BLACK);
        titleSize = mArray.getDimension(R.styleable.BarGraphView_barGraph_titleSize, 36);
      
        Rect rect = new Rect();
        mPaint.getTextBounds("100", 0, 2, rect);
        
        fontwidth = rect.width();
        fontheight = rect.height();
        
        originX = fontwidth*2+10;
        
    }
 
    /**
     * ����X������ֵ���̶�������������0���̶ȣ�
     *
     * @param maxValue   X������ֵ
     * @param divideSize �̶�������
     */
    public void setAxisX(float maxValue, int divideSize) {
        maxAxisValueX = maxValue;
        axisDivideSizeX = divideSize;
    }
 
    /**
     * ����Y������ֵ���̶�������������0���̶ȣ�
     *
     * @param maxValue   Y������ֵ
     * @param divideSize �̶�������
     */
    public void setAxisY(float maxValue, int divideSize) {
        maxAxisValueY = maxValue;
        axisDivideSizeY = divideSize;
        
        float cellValue = maxAxisValueY / axisDivideSizeY;
        
        /*
        String text = String.valueOf(2);
        float textWidth = mPaint.measureText(text); 
        
        
        originX = (int)(textWidth*6)+10 ;
        */
        
    }
 
    /**
     * ������״ͼ���
     *
     * @param columnInfo
     */
    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }
 
    public void SetItems(List<String> items){
    	mItems = items;
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        
        
        width = MeasureSpec.getSize(widthMeasureSpec) -originX ;
        
        
        height = MeasureSpec.getSize(heightMeasureSpec) - 20;
        
        
        originY = height- fontheight*4;
        
        height = height - fontheight*4 - 10  ;
        
        minTopy = originY ;
        
        
        fontsize = width/24;
        
        Log.d("onMeasure", "originX:"+originX+"originY:"+originY+"width:"+width+"fontwidth:"+fontwidth+"fontheight:"+fontheight);
        
        
    }
 
    @Override
    public void onDraw(Canvas canvas) {
        drawAxisX(canvas, mPaint);
        drawAxisY(canvas, mPaint);
        //drawAxisScaleMarkX(canvas, mPaint);
        //drawAxisScaleMarkY(canvas, mPaint);
        //drawAxisArrowsX(canvas, mPaint);
        //drawAxisArrowsY(canvas, mPaint);
        //drawAxisScaleMarkValueX(canvas, mPaint);
        
        
        
        drawColumn(canvas, mPaint);
        drawAxisScaleMarkValueY(canvas, mPaint);
        
        drawTitle(canvas, mPaint);
        
    }
 
    /**
     * ���ƺ�����ᣨX�ᣩ
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisX(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        //���û��ʿ��
        paint.setStrokeWidth(2);
        //���û��ʿ����
        paint.setAntiAlias(true);
        //������(X)
        canvas.drawLine(originX, originY, originX + width, originY, paint);
        
        //canvas.drawLine(20, 20, 300 , 300, paint);
        
        //Log.d("BarView drawAxisX", "originX:"+originX+"originY:"+originY+"width:"+width);
        
    }
 
    /**
     * �����������(Y��)
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisY(Canvas canvas, Paint paint) {
        //������(Y)
        canvas.drawLine(originX, originY, originX, originY - height, paint);//����˵������ʼ�����x,y���յ����x,y������
    }
 
 
    /**
     * ���ƺ������̶���(X��)
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisScaleMarkX(Canvas canvas, Paint paint) {
        float cellWidth = width / axisDivideSizeX;
        for (int i = 0; i < axisDivideSizeX - 1; i++) {
        	
        	float currentLeft = cellWidth * (i + 1) + originX+offestX ;
        	
        	 if(currentLeft >=  originX){
        		 canvas.drawLine(currentLeft, originY, currentLeft, originY - 6, paint);
        	 }
            
        }
    }
 
    /**
     * �����������̶���(Y��)
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisScaleMarkY(Canvas canvas, Paint paint) {
        float cellHeight = height / axisDivideSizeY;
        for (int i = 0; i < axisDivideSizeY - 1; i++) {
            canvas.drawLine(originX, (originY - cellHeight * (i + 1)), originX + 6, (originY - cellHeight * (i + 1)), paint);
        }
    }
 
    /**
     * ���ƺ������̶�ֵ(X��)
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisScaleMarkValueX(Canvas canvas, Paint paint) {
        //���û��ʻ������ֵ�����
        paint.setColor(Color.GRAY);
        paint.setTextSize(fontsize);
        paint.setFakeBoldText(false);
 
        float cellWidth = width / axisDivideSizeX;
        float cellValue = maxAxisValueX / axisDivideSizeX;
        for (int i = 1; i < axisDivideSizeX; i++) {
        	
        	 float currentLeft = cellWidth * i + originX - 5+offestX ;
        	 
        	 
             currentLeft = currentLeft +xspace*i ;
             
        	 
             if(currentLeft >=  originX){
            	 canvas.drawText(String.valueOf(cellValue * i), currentLeft, originY + 5+fontsize, paint);
             }
             
            
            
        }
    }
 
    /**
     * �����������̶�ֵ(Y��)
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisScaleMarkValueY(Canvas canvas, Paint paint) {
       
    	float h =  (originY - minTopy) ;
        if((int)maxAxisValueY >=20){
        	axisDivideSizeY = 8;
        	
        }else if((int)maxAxisValueY >=10){
        	axisDivideSizeY = 6;
        }else if((int)maxAxisValueY >=5){
        	axisDivideSizeY = 5;
        }else {
        	
        }
        float cellHeight = h / axisDivideSizeY;
        float cellValue = maxAxisValueY / axisDivideSizeY;
        
        String text = String.valueOf(cellValue);
        float textWidth = paint.measureText(text);  
        paint.setTextSize(fontsize);
        
        for (int i = 1; i <= axisDivideSizeY; i++) {
            canvas.drawText(String.valueOf(cellValue * i), originX - textWidth-5, originY - cellHeight * i , paint);
        }
        
        
    }
 
    /**
     * ���ƺ�������ͷ(X��)
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisArrowsX(Canvas canvas, Paint paint) {
        //������Σ�X���ͷ��
        Path mPathX = new Path();
        mPathX.moveTo(originX + width , originY);//��ʼ��
        mPathX.lineTo(originX + width -5, originY - 5);//��һ��
        mPathX.lineTo(originX + width -5, originY + 5);//��һ��
        mPathX.close();
        canvas.drawPath(mPathX, paint);
    }
 
    /**
     * ������������ͷ(Y��)
     *
     * @param canvas
     * @param paint
     */
    private void drawAxisArrowsY(Canvas canvas, Paint paint) {
        //������Σ�Y���ͷ��
        Path mPathX = new Path();
        mPathX.moveTo(originX, originY- height+20 );//��ʼ��
        mPathX.lineTo(originX-5, originY- height+30);//��һ��
        mPathX.lineTo(originX+5, originY- height+30);//��һ��
        mPathX.close();
        canvas.drawPath(mPathX, paint);
        
    }
 
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
    	int action = event.getAction();
        /*
         * (x,y)��Ϊ�����¼�ʱ�ĵ㣬������ֵΪ����ڸÿؼ����Ͻǵľ���
         */
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {

        case MotionEvent.ACTION_DOWN: // ����
        	lastcoorX = x ;
            break;
        case MotionEvent.ACTION_MOVE: // �϶�
            /*
             * �϶�ʱԲ���������˶� (x0,y0)������ǰһ���¼���������
             * �϶���ʱ��ֻҪ������������deltaֵ��Ȼ��ӵ�Բ���У����ƶ���Բ�ġ�
             * Ȼ�����invalidate()��ϵͳ����onDraw()ˢ��������Ļ����ʵ��������ƶ���
             */
        	offestX = x - lastcoorX ;
        	
            invalidate();
            break;
        case MotionEvent.ACTION_UP: // ����
            break;
        }

        /*
         * ע�⣺����һ��Ҫ����true
         * ����false��super.onTouchEvent(event)���᱾����ֻ�ܼ�⵽������Ϣ
         * ������Ϊfalse��super.onTouchEvent(event)�Ĵ��?�Ǹ���ϵͳ�ÿؼ����ܴ����������Ϣ��
         * ����ϵͳ�Ὣ��Щ�¼�������ĸ��������?
         */
        return true;
        
    }
		


	/**
     * ������״ͼ
     *
     * @param canvas
     * @param paint
     */
    private void drawColumn(Canvas canvas, Paint paint) {
        if(columnInfo == null)
            return;
        float cellWidth = (width-(axisDivideSizeX+1)*gap) / axisDivideSizeX;
        
        
        paint.setTextSize(fontsize);
        
        if(animShow == false){
        	handler.postDelayed(this, animTime); 
        }
        
        
        for (int i = 0; i < columnInfo.length; i++) {
            paint.setColor(columnInfo[i][1]);
            float leftTopY = originY - height * columnInfo[i][0] / maxAxisValueY;
            
            if(minTopy > leftTopY){
            	minTopy = leftTopY ;
            }
            
            
            float currentY = originY -rateAnimValue  ;
            if(animShow == true){
            	currentY = leftTopY ;
            }
            
            float currentLeft = originX + (cellWidth+gap) * (i )+gap +offestX;
            if(currentLeft <= originX){
            	currentLeft = originX ;
            }
            
            float currentRight = currentLeft+ cellWidth ; //originX + (cellWidth+gap) * (i + 1)+gap;
            if(currentRight <= originX){
            	currentRight = originX ;
            }
           
           
            //Log.d("drawColumn:", "width:"+(currentRight-currentLeft)+"left:"+currentLeft+"right:"+currentRight);
            
            
            if((mItems != null) &&(i < mItems.size())){
            	
            	float textWidth = mPaint.measureText(mItems.get(i)); 
            	canvas.drawText(mItems.get(i), currentLeft+(cellWidth-textWidth)/2, originY + 5+fontsize, paint);
            	
            	
            }
            
            String  columntitle ; 
            columntitle = String.format("%d 人", columnInfo[i][0]);
            float titleWidth = mPaint.measureText(columntitle); 
            
            if(currentY >= leftTopY){
            	
            	canvas.drawRect(currentLeft, currentY, currentRight, originY, mPaint);//���Ͻ�x,y���½�x,y������
            	
            	canvas.drawText(columntitle, currentLeft+(cellWidth-titleWidth)/2, currentY - 5, paint);
            	
            }    
            else{
            	
            	canvas.drawRect(currentLeft, leftTopY, currentRight, originY, mPaint);
            	canvas.drawText(columntitle, currentLeft+(cellWidth-titleWidth)/2, leftTopY - 5, paint);
            }
            
            
        }
    }
 
    /**
     * ���Ʊ���
     *
     * @param canvas
     * @param paint
     */
    private void drawTitle(Canvas canvas, Paint paint) {
 
        //������
        if (title != null) {
            //���û��ʻ������ֵ�����
            mPaint.setColor(titleColor);
            mPaint.setTextSize(titleSize);
            mPaint.setFakeBoldText(true);
            canvas.drawText(title, 300, originY + 150, paint);
        }
    }

	public void run() {
		// TODO Auto-generated method stub
		
		
		if(rateAnimValue <=originY){
			animRate+=1;
			rateAnimValue+=animRate;
			invalidate();
		}else if((originY -rateAnimValue) <= minTopy){
			handler.removeCallbacks(this);
			rateAnimValue = 0;
			animRate = 1 ;
			animShow = true ;
		}
	}

}
