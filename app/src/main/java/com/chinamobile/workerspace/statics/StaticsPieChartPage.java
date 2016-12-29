package com.chinamobile.workerspace.statics;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.draw.CakeSurfaceView;
import com.chinamobile.workerspace.draw.CakeSurfaceView.Gravity;
import com.chinamobile.workerspace.utils.MyColorTemplate;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;



public class StaticsPieChartPage implements OnChartValueSelectedListener {

	    private static StaticsPieChartPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		private PieChart cakeSurfaceView;
		
		private int  statis_id ;
		
		private int mtotalCount = 0 ;
		
		private int departmentcount = 0 ;
		
		static public final int[]  static_titles = {R.string.statis_sex,R.string.statis_age,R.string.statis_nation,
				R.string.statis_edu,R.string.statis_depth,R.string.statis_status,R.string.statis_group};
		
		
		
		private Map<String,List<UserItem>>  minfos ;//= new HashMap<String,List<UserItem>>();
		

		private List<String>  mkeys ;
		
		public static StaticsPieChartPage GetInstance(){
			if(mInstance ==null){
				mInstance = new StaticsPieChartPage();
			}
			return mInstance ;
		}
		
		public StaticsPieChartPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	
		 }
		
		public void MakeDatas(Map<String,List<UserItem>>  maps , List<String>  keys){
			minfos = maps ;
			
			mkeys = keys ;
		}
		
		public void SetTitle(int id,int count){
			
			
			TextView  title1 = (TextView)mViewOfPage.findViewById(R.id.pie_statis_title1);
			
			title1.setText(static_titles[id]);
			statis_id = id ;
			
			departmentcount = count ;
			
		}
		
		
		private void  ConfigPieChart(PieChart mChart){
			    mChart.setUsePercentValues(true);

		       
		        mChart.setDragDecelerationFrictionCoef(0.95f);
		
		        mChart.setHoleRadius(60f);

		        mChart.setDrawHoleEnabled(true);
		        mChart.setHoleColorTransparent(true);
		        mChart.setTransparentCircleColor(Color.WHITE);
		        mChart.setTransparentCircleAlpha(110);
		        mChart.setDescription("");
				mChart.setExtraOffsets(5, 10, 5, 5);

		        mChart.setDrawCenterText(true);


		        mChart.setRotationAngle(90);
		        // enable rotation of the chart by touch
		        mChart.setRotationEnabled(true);
		        mChart.setHighlightPerTapEnabled(true);
		        // mChart.setUnit(" 鈧�);
		        // mChart.setDrawUnitsInChart(true);

		        // add a selection listener
		        mChart.setOnChartValueSelectedListener(this);
		        // mChart.setTouchEnabled(false);

		        
		        mChart.setCenterText(mContext0.getString(static_titles[statis_id]));
		        mChart.setCenterTextSize(20f);
		        
		        setData(mChart,minfos.size(),100);

		        mChart.animateXY(1500, 1500);
		       
		        
		        // mChart.spin(2000, 0, 360);    
		        
		        Legend l = mChart.getLegend();
		        l.setPosition(LegendPosition.RIGHT_OF_CHART);
		        l.setXEntrySpace(0f);
		        l.setYEntrySpace(0f);
		        l.setTextSize(12f);
		        l.setYOffset(0f);
		 }
		 
		 private void setData(PieChart mChart,int count, float range) {

		        float mult = range;

		        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		        // IMPORTANT: In a PieChart, no values (Entry) should have the same
		        // xIndex (even if from different DataSets), since no values can be
		        // drawn above each other.

		        ArrayList<String> xVals = new ArrayList<String>();

		       
		        int i = 0 ;
		        mtotalCount = 0 ;
		        for(i = 0 ; i < mkeys.size() ; i++){
		        	
		        	String key = mkeys.get(i);
		        	List<UserItem>  l = minfos.get(key);
					
					String  content =  String.format("%s(%d) 人", key,l.size());
					
					mtotalCount += l.size() ;
					xVals.add(content);
					yVals1.add(new Entry((float) l.size(), i));
		        }
		        
		        /*for(String key : minfos.keySet()){
					
					List<UserItem>  l = minfos.get(key);
					
					String  content =  String.format("%s(%d) 人", key,l.size());
					
					mtotalCount += l.size() ;
					xVals.add(content);
					yVals1.add(new Entry((float) l.size(), i));
					i++ ;
				}*/
		        
		    
		        String totalstr = String.format("参与人数: %d人", mtotalCount);

		        PieDataSet dataSet = new PieDataSet(yVals1, totalstr);
		        dataSet.setSliceSpace(2f);
	            dataSet.setSelectionShift(5f);

	            

		        dataSet.setColors(MyColorTemplate.COLORFUL_COLORS);

		        PieData data = new PieData(xVals, dataSet);
		        data.setValueFormatter(new PercentFormatter());
		        data.setValueTextSize(14f);
		        data.setValueTextColor(Color.WHITE);
		        mChart.setData(data);

		        // undo all highlights
		        mChart.highlightValues(null);

		        //mChart.invalidate();
		    }
		
		 public void Repaint(){
			 cakeSurfaceView.animateXY(1500, 1500);
		 }
		 
		 
		 public void OnCreate(){
		    
			cakeSurfaceView = (PieChart)mViewOfPage.findViewById(R.id.cakeSurfaceView_statics);
			List<CakeSurfaceView.CakeValue> cakeValues2 = new ArrayList<CakeSurfaceView.CakeValue>();
			
			ConfigPieChart(cakeSurfaceView);

			
			TextView  title2 = (TextView)mViewOfPage.findViewById(R.id.pie_statis_title2);
			
			String totalstr = String.format("参与人数: %d人,参与部门:%d个", mtotalCount,departmentcount);
			
			title2.setText(totalstr);
				
			
		 }

		@Override
		public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNothingSelected() {
			// TODO Auto-generated method stub
			
		}
}
