package com.chinamobile.workerspace.humanresource;




import java.util.ArrayList;
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
import com.github.mikephil.charting.utils.ColorTemplate;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;



public class humanresourcePieChartPage implements OnChartValueSelectedListener {

	    private static humanresourcePieChartPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		private PieChart cakeeView_sex;
		private PieChart cakeeView_age;
		private PieChart cakeeView_nation;
		private PieChart cakeeView_edu;
		private PieChart cakeeView_depth;
		
		
		private Map<String,List<UserItem>>  minfos_sex ;
		private Map<String,List<UserItem>>  minfos_age ;
		private Map<String,List<UserItem>>  minfos_nation ;
		private Map<String,List<UserItem>>  minfos_edu ;
		private Map<String,List<UserItem>>  minfos_depth ;
		
		
		private final int[]  static_titles = {R.string.statis_sex,R.string.statis_age,R.string.statis_nation,
				R.string.statis_edu,R.string.statis_depth,R.string.statis_positon,R.string.statis_group};
		
		public void MakeDataSexs(Map<String,List<UserItem>>  maps){
			minfos_sex = maps ;
		}
		public void MakeDataNation(Map<String,List<UserItem>>  maps){
			minfos_nation = maps ;
		}
		public void MakeDataEdu(Map<String,List<UserItem>>  maps){
			minfos_edu = maps ;
		}
		public void MakeDataDepth(Map<String,List<UserItem>>  maps){
			minfos_depth = maps ;
		}
		public void MakeDataAge(Map<String,List<UserItem>>  maps){
			minfos_age = maps ;
		}
		
		
		public static humanresourcePieChartPage GetInstance(){
			if(mInstance ==null){
				mInstance = new humanresourcePieChartPage();
			}
			return mInstance ;
		}
		
		public humanresourcePieChartPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	
		 }
		
		 public void OnCreate(){
		    
			 SetupSex();
			
			 SetupAge();
				
			 SetupDepth();
			 SetupNation();
			 
			 SetupEdu();
		 }
		 
		 
		 public void Repaint(){
			 cakeeView_sex.animateXY(1500, 1500);
			 cakeeView_age.animateXY(1500, 1500);
			 cakeeView_nation.animateXY(1500, 1500);
			 cakeeView_edu.animateXY(1500, 1500);
			 cakeeView_depth.animateXY(1500, 1500);
		 }
		 
		 
		 private void  ConfigPieChart(PieChart mChart,Map<String,List<UserItem>>  datas,int type){
			    mChart.setUsePercentValues(true);

		       
		        mChart.setDragDecelerationFrictionCoef(0.95f);
		
		        mChart.setHoleRadius(55f);

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

		        
		        mChart.setCenterText(mContext0.getString(static_titles[type]));
		        mChart.setCenterTextSize(20f);
		        
		        if(datas != null){
		        	setData(mChart,datas,100);
		        }
		        

		        mChart.animateXY(1500, 1500);
		        
		        
		        // mChart.spin(2000, 0, 360);

		        
		        
		        
		        Legend l = mChart.getLegend();
		        l.setPosition(LegendPosition.RIGHT_OF_CHART);
		        l.setXEntrySpace(7f);
		        l.setYEntrySpace(0f);
		        l.setYOffset(0f);
		        l.setTextSize(12f);
		 }
		 
		 private void setData(PieChart mChart,Map<String,List<UserItem>> datas, float range) {

		        float mult = range;

		        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		        // IMPORTANT: In a PieChart, no values (Entry) should have the same
		        // xIndex (even if from different DataSets), since no values can be
		        // drawn above each other.

		        ArrayList<String> xVals = new ArrayList<String>();

		       
		        int i = 0 ;
		        int total = 0 ;
		        for(String key : datas.keySet()){
					
					List<UserItem>  l = datas.get(key);
					
					String  content =  String.format("%s(%d) 人", key,l.size());
					
					total += l.size() ;
					xVals.add(content);
					yVals1.add(new Entry((float) l.size(), i));
					i++ ;
				}
		        
		    
		        String totalstr = String.format("参与统计: %d人", total);

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
		 
		 private void SetupSex(){
			    cakeeView_sex = (PieChart)mViewOfPage.findViewById(R.id.PieGraphView_human_sex_pie);
			    ConfigPieChart(cakeeView_sex,minfos_sex,0);
		 }
		 
		 private void SetupAge(){
			    cakeeView_age = (PieChart)mViewOfPage.findViewById(R.id.PieGraphView_human_age_pie);
			    ConfigPieChart(cakeeView_age,minfos_age,1);
		 }
		 
		 private void SetupDepth(){
			    cakeeView_depth = (PieChart)mViewOfPage.findViewById(R.id.PieGraphView_human_depth_pie);
			    ConfigPieChart(cakeeView_depth,minfos_depth,4);
		 }
		 
		 private void SetupNation(){
			    cakeeView_nation = (PieChart)mViewOfPage.findViewById(R.id.PieGraphView_human_nation_pie);
			    ConfigPieChart(cakeeView_nation,minfos_nation,2);
		 }
		 
		 private void SetupEdu(){
			    cakeeView_edu = (PieChart)mViewOfPage.findViewById(R.id.PieGraphView_human_edu_pie);
			    ConfigPieChart(cakeeView_edu,minfos_edu,3);
			    //cakeeView_edu.animateXY(1500, 1500);
		 }

		@Override
		public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
			// TODO Auto-generated method stub
			
			Log.d("onValueSelected", "dataSetIndex:"+dataSetIndex);
		}

		@Override
		public void onNothingSelected() {
			// TODO Auto-generated method stub
			
		}
		 
}
