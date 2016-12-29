package com.chinamobile.workerspace.statics;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.UserItem;
import com.chinamobile.workerspace.draw.BarGraphView;
import com.chinamobile.workerspace.draw.CakeSurfaceView;
import com.chinamobile.workerspace.draw.CakeSurfaceView.Gravity;
import com.chinamobile.workerspace.utils.MyColorTemplate;
import com.chinamobile.workerspace.utils.MyYAxisValueFormatter;
import com.chinamobile.workerspace.utils.T;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;


import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;



public class StaticsBarChartPage implements OnChartValueSelectedListener {

	    private static StaticsBarChartPage mInstance;
		
		private View mViewOfPage;
		private Context mContext0;
		private BarChart mBarGraphView;
		
		
		private int  statis_id ;
		
		private int  m_totalcount = 0 ;
		private int  departmentcount = 0 ;
		
		private final int[]  static_titles = {R.string.statis_sex,R.string.statis_age,R.string.statis_nation,
				R.string.statis_edu,R.string.statis_depth,R.string.statis_status,R.string.statis_group};
		
		private Map<String,List<UserItem>>  minfos ;
		
		private List<String>  mkeys ;
		public static StaticsBarChartPage GetInstance(){
			if(mInstance ==null){
				mInstance = new StaticsBarChartPage();
			}
			return mInstance ;
		}
		
		public StaticsBarChartPage(){
			
		}
		 public void OnInit(View v , Context t){
		    	mViewOfPage = v;
		    	mContext0 = t;
	
		 }
		 
		 public void MakeDatas(Map<String,List<UserItem>>  maps,List<String>  keys ){
				minfos = maps ;
				mkeys = keys;
		}
			
		 
		public void SetTitle(int id,int count){
			
			
			TextView  title1 = (TextView)mViewOfPage.findViewById(R.id.bar_statis_title1);
			
			title1.setText(static_titles[id]);
			
			departmentcount = count ;
			
		}
		
		private void setData(BarChart mChart) {


	        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

	        // IMPORTANT: In a PieChart, no values (Entry) should have the same
	        // xIndex (even if from different DataSets), since no values can be
	        // drawn above each other.

	        ArrayList<String> xVals = new ArrayList<String>();

	       
	        int i = 0 ;
	        int total = 0 ;
	        
	        for(i = 0 ; i < mkeys.size() ; i++){
	        	
	        	String key = mkeys.get(i);
	        	List<UserItem>  l = minfos.get(key);
				
				String  content =  String.format("%s (%d)", key,l.size());
				
				total += l.size() ;
				xVals.add(content);
				yVals1.add(new BarEntry((float) l.size(), i));
	        }
	        
	        /*for(String key : minfos.keySet()){
				
				List<UserItem>  l = minfos.get(key);
				
				String  content =  String.format("%s 人(%d)", key,l.size());
				
				total += l.size() ;
				xVals.add(content);
				yVals1.add(new BarEntry((float) l.size(), i));
				i++ ;
			}*/
	        
	    
	        String totalstr = String.format("参与人数: %d人", total);

	        m_totalcount = total ;
	        
	        BarDataSet dataSet = new BarDataSet(yVals1, totalstr);
	        //dataSet.setBarSpacePercent(35f);
            
	        dataSet.setValueTextSize(20f);
	        dataSet.setColors(MyColorTemplate.COLORFUL_COLORS);

	        BarData data = new BarData(xVals, dataSet);
	        
	       
	        data.setValueTextSize(20f);
	        data.setValueTextColor(Color.BLACK);
	        mChart.setData(data);

	        // undo all highlights
	        mChart.highlightValues(null);

	        //mChart.invalidate();
	    }
		
		
		private void ConfigBarChartView(BarChart  barchart){
			
	
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

	        setData(barchart);
	        
	        
	        XAxis xAxis = barchart.getXAxis();
	        xAxis.setPosition(XAxisPosition.BOTTOM);
	        xAxis.setDrawGridLines(false);
	        xAxis.setSpaceBetweenLabels(2);

	        

	        YAxis leftAxis = barchart.getAxisLeft();
	        leftAxis.setLabelCount(8, false);

	        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
	        leftAxis.setSpaceTop(15f);
	        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

	        YAxis rightAxis = barchart.getAxisRight();
	        rightAxis.setDrawGridLines(false);
	        rightAxis.setLabelCount(8, false);
	        rightAxis.setSpaceTop(15f);
	        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
             
             
	        Legend l = barchart.getLegend();
	        l.setPosition(LegendPosition.BELOW_CHART_LEFT);
	        l.setForm(LegendForm.CIRCLE);
	        l.setFormSize(9f);
	        l.setTextSize(20f);
	        l.setXEntrySpace(4f);
	        
	       
	        
	        
		}
		
		public void Repaint(){
			
			mBarGraphView.animateXY(1500, 1500);
		}
		
		 public void OnCreate(){
		    
			 mBarGraphView = (BarChart)mViewOfPage.findViewById(R.id.custom_barview);
			 
			 ConfigBarChartView(mBarGraphView);
			
	        
	        TextView  title2 = (TextView)mViewOfPage.findViewById(R.id.bar_statis_title2);
			
	        String totalstr = String.format("参与人数: %d人,参与部门:%d个", m_totalcount,departmentcount);
	        
			
			
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
