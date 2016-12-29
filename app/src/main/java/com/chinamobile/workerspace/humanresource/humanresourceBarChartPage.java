package com.chinamobile.workerspace.humanresource;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.draw.BarGraphView;
import com.chinamobile.workerspace.draw.CakeSurfaceView;
import com.chinamobile.workerspace.draw.CakeSurfaceView.Gravity;
import com.chinamobile.workerspace.utils.MyColorTemplate;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import android.content.Context;
import android.graphics.Color;
import android.view.View;



public class humanresourceBarChartPage implements OnChartValueSelectedListener {

	    private static humanresourceBarChartPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		
		private BarChart mBarGraphView_sex;
		private BarChart mBarGraphView_age;
		private BarChart mBarGraphView_nation;
		private BarChart mBarGraphView_edu;
		private BarChart mBarGraphView_depth;
		
		
		private Map<String,List<UserItem>>  minfos_sex ;
		private Map<String,List<UserItem>>  minfos_nation ;
		private Map<String,List<UserItem>>  minfos_age ;
		private Map<String,List<UserItem>>  minfos_edu ;
		private Map<String,List<UserItem>>  minfos_depth ;
		
		
		 private int  statis_id ;
			
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
		public void MakeDataAge(Map<String,List<UserItem>>  maps){
			minfos_age = maps ;
		}
		public void MakeDataDepth(Map<String,List<UserItem>>  maps){
			minfos_depth = maps ;
		}
			
			
		
		public static humanresourceBarChartPage GetInstance(){
			if(mInstance ==null){
				mInstance = new humanresourceBarChartPage();
			}
			return mInstance ;
		}
		
		public humanresourceBarChartPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	
		 }
		
		 public void OnCreate(){
		    
			 SetupSex();
				
			 SetupAge();
				
			 SetupNation();
			 
			 SetupEdu();
			 
			 SetupDepth(); 
				
		 }
		 
		 
		 private void setData(BarChart mChart,Map<String,List<UserItem>> datas) {


		        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		        // IMPORTANT: In a PieChart, no values (Entry) should have the same
		        // xIndex (even if from different DataSets), since no values can be
		        // drawn above each other.

		        ArrayList<String> xVals = new ArrayList<String>();

		       
		        int i = 0 ;
		        int total = 0 ;
		        for(String key : datas.keySet()){
					
					List<UserItem>  l = datas.get(key);
					
					String  content =  String.format("%s (%d)", key,l.size());
					
					total += l.size() ;
					xVals.add(content);
					yVals1.add(new BarEntry((float) l.size(), i));
					i++ ;
				}
		        
		    
		        String totalstr = String.format("参与统计: %d人", total);

		        BarDataSet dataSet = new BarDataSet(yVals1, totalstr);
		        //dataSet.setBarSpacePercent(35f);
	            
		        dataSet.setValueTextSize(14f);
		        dataSet.setColors(MyColorTemplate.COLORFUL_COLORS);

		        BarData data = new BarData(xVals, dataSet);
		        
		       
		        data.setValueTextSize(20f);
		        data.setValueTextColor(Color.BLACK);
		        mChart.setData(data);

		        // undo all highlights
		        mChart.highlightValues(null);
		       
		        //mChart.invalidate();
		    }
			
			
			private void ConfigBarChartView(BarChart  barchart,Map<String,List<UserItem>> datas){
				
		
				barchart.setOnChartValueSelectedListener(this);

		        barchart.setDrawBarShadow(false);
		        barchart.setDrawValueAboveBar(true);

		        barchart.setDescription("");

		        // if more than 60 entries are displayed in the chart, no values will be
		        // drawn
		        barchart.setMaxVisibleValueCount(60);

		        barchart.setNoDataTextDescription("没有数据显示");
		        
		        // scaling can now only be done on x- and y-axis separately
		        barchart.setPinchZoom(false);

		        barchart.setDrawGridBackground(false);
		        
		        
		        // barchart.setDrawYLabels(false);

		        if(datas != null)
		          setData(barchart,datas);
		        
		        
		        XAxis xAxis = barchart.getXAxis();
		        xAxis.setPosition(XAxisPosition.BOTTOM);
		        xAxis.setDrawGridLines(false);
		        xAxis.setSpaceBetweenLabels(2);
		        xAxis.setTextSize(16f);
		        

		        YAxis leftAxis = barchart.getAxisLeft();
		        leftAxis.setLabelCount(8, false);

		        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
		        leftAxis.setSpaceTop(15f);
		        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
		        leftAxis.setTextSize(16f);
		        
		        YAxis rightAxis = barchart.getAxisRight();
		        rightAxis.setDrawGridLines(false);
		        rightAxis.setLabelCount(8, false);
		        rightAxis.setSpaceTop(15f);
		        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
	            rightAxis.setTextSize(16f);
	             
		        Legend l = barchart.getLegend();
		        l.setPosition(LegendPosition.BELOW_CHART_LEFT);
		        l.setForm(LegendForm.CIRCLE);
		        l.setFormSize(9f);
		        l.setTextSize(20f);
		        l.setXEntrySpace(4f);
		        
		        barchart.animateXY(1500, 1500);
		        
			}
			
		public void Repaint(){
			mBarGraphView_sex.animateXY(1500, 1500);
			mBarGraphView_age.animateXY(1500, 1500);
			mBarGraphView_nation.animateXY(1500, 1500);
			mBarGraphView_edu.animateXY(1500, 1500);
			mBarGraphView_depth.animateXY(1500, 1500);
		}
			
			
		 private void SetupSex(){
			 mBarGraphView_sex = (BarChart)mViewOfPage.findViewById(R.id.BarGraphView_human_sex_bar);
			 ConfigBarChartView(mBarGraphView_sex,minfos_sex);
			 
		 }
		 private void SetupAge(){
			 mBarGraphView_age = (BarChart)mViewOfPage.findViewById(R.id.BarGraphView_human_age_bar);
			 ConfigBarChartView(mBarGraphView_age,minfos_age);
			 
		 }
		 private void SetupNation(){
			 mBarGraphView_nation = (BarChart)mViewOfPage.findViewById(R.id.BarGraphView_human_nation_bar);
			 ConfigBarChartView(mBarGraphView_nation,minfos_nation);
		 }
		 private void SetupEdu(){
			 mBarGraphView_edu = (BarChart)mViewOfPage.findViewById(R.id.BarGraphView_human_edu_bar);
			 ConfigBarChartView(mBarGraphView_edu,minfos_edu);
		 }
		 private void SetupDepth(){
			 mBarGraphView_depth = (BarChart)mViewOfPage.findViewById(R.id.BarGraphView_human_depth_bar);
			 ConfigBarChartView(mBarGraphView_depth,minfos_depth);
			 
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
