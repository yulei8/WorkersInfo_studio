package com.chinamobile.workerspace.group;

 

import  java.util.ArrayList;   
import  java.util.List;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.contacts.ContactsManager;
import com.chinamobile.workerspace.contacts.GroupItem;
import com.chinamobile.workerspace.humanresource.humanresource_containerActivity;
import com.chinamobile.workerspace.ui.SearchEditText;
import com.chinamobile.workerspace.utils.PinYinKit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ExpandableListActivity;
import  android.app.ListActivity;   
import  android.content.Context;
import android.content.Intent;
import  android.graphics.Bitmap;   
import  android.graphics.BitmapFactory;   
import  android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import  android.util.Log;   
import  android.view.LayoutInflater;
import android.view.MenuItem;
import  android.view.View;   
import  android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import  android.widget.ArrayAdapter;   
import  android.widget.ImageView;   
import  android.widget.ListView;   
import  android.widget.TextView;   
import  android.widget.Toast;   
   
/**   
 *    
 * <p>   
 * Title: TreeView.java   
 * </p>   
 * <p>   
 * E-Mail: 176291935@qq.com   
 * </p>   
 * <p>   
 * QQ: 176291935   
 * </p>   
 * <p>   
 * Http: iaiai.iteye.com   
 * </p>   
 * <p>   
 * Create time: 2011-8-20   
 * </p>   
 *    
 * @author ����   
 * @version 0.0.1   
 */    
public   class  GroupListActivity  extends  Activity implements OnItemClickListener, TextWatcher {   
    private  ArrayList<TreeElement> mPdfOutlinesCount =  new  ArrayList<TreeElement>();   
    private  TreeViewAdapter treeViewAdapter =  null ;   
   
    private List<TreeElement> mTopitems =  new  ArrayList<TreeElement>(); 
    

    private List<GroupItem> mAllitems =  null ; 
    
    private GroupManager    mGroupmgr;
    private ContactsManager   mContactsMgr ;
    
    private ListView mListView = null;
    private SearchEditText mSearchEditText;
    
    
    /** Called when the activity is first created. */    
    @Override    
    public   void  onCreate(Bundle savedInstanceState) {   
        super .onCreate(savedInstanceState);   
   
        setContentView(R.layout.group_list);
        
        mListView = (ListView) findViewById(R.id.group_list);
        
        
        mGroupmgr = GroupManager.GetInstance(this);
        
        mContactsMgr = ContactsManager.GetInstance(this);
        ActionBar ab = getActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
        
        initialData();   
        treeViewAdapter = new  TreeViewAdapter( this , R.layout.group_main,   mPdfOutlinesCount);   
        //setListAdapter(treeViewAdapter);   
        //registerForContextMenu(getListView());   
        
        String title = String.format("组织机构查询(单位共:%d ,人员共: %d)", mGroupmgr.GetAllGroupsCount(),mContactsMgr.GetAllContactsCount());
        ab.setTitle(title);
        
        mListView.setAdapter(treeViewAdapter);
		
        mListView.setOnItemClickListener(this);
        
        mSearchEditText = (SearchEditText)findViewById(R.id.txt_filter_edit);
    	mSearchEditText.addTextChangedListener(this);
    	
    	
    }   
   
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case android.R.id.home:  
			finish();
			break;	
		default:
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}
    
    private   void  initialData() {   
    	
    	List<GroupItem> groups =  mGroupmgr.GetTopLevelGroup();
    	
    	mAllitems = mGroupmgr.GetAllGroups();
    	
    	Log.d("grouplistactivity", "toplevel size is :"+groups.size());
    	for(int i = 0 ; i < groups.size() ; i++){
    		
    		if( Integer.valueOf(groups.get(i).getGroup_id()) == 0){
				continue ;
			}
    		
    		TreeElement element1 = new  TreeElement( groups.get(i).getGroup_id() , groups.get(i).getGroup_name());  
    		
    		mTopitems.add(element1);
    		mPdfOutlinesCount.add(element1);
    		
    		
			List<GroupItem> childs = mGroupmgr.GetChildLevelGroup(groups.get(i).getGroup_id());
			
			
			
			for(int j = childs.size()-1 ; j >=0  ; j-- ){
				TreeElement element2 = new  TreeElement( childs.get(j).getGroup_id() , childs.get(j).getGroup_name()); 
				
			
				element1.addChild(element2);
				
				List<GroupItem> thirds = mGroupmgr.GetChildLevelGroup(childs.get(j).getGroup_id());
				
				
				for(int k = thirds.size()-1 ; k >= 0 ; k --){
					TreeElement element3 = new  TreeElement( thirds.get(k).getGroup_id() , thirds.get(k).getGroup_name()); 
					
					element2.addChild(element3);
				}
				 		
			}
					
		}
    	   
   
    }   
    
    private void GoHumanResourceActivity(String groupid){
	
		Intent  frqueui = new Intent();
	
		frqueui.putExtra("group_id", groupid);
		frqueui.setClass(this,humanresource_containerActivity.class);
		frqueui.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(frqueui);	
		
		((Activity)this).overridePendingTransition(R.anim.my_slide_right_in,R.anim.my_slide_left_out);
	}
    
   
    @SuppressWarnings ( "unchecked" )   
    private   class  TreeViewAdapter  extends  ArrayAdapter {   
        public  TreeViewAdapter(Context context,  int  textViewResourceId,   
                List objects) {   
            super (context, textViewResourceId, objects);   
            mInflater = LayoutInflater.from(context);   
            mfilelist = objects;   
            mIconCollapse = BitmapFactory.decodeResource(   
                    context.getResources(), R.drawable.outline_list_collapse);   
            mIconExpand = BitmapFactory.decodeResource(context.getResources(),   
                    R.drawable.outline_list_expand);   
        }   
   
        private  LayoutInflater mInflater;   
        private  List<TreeElement> mfilelist;   
        private  Bitmap mIconCollapse;   
        private  Bitmap mIconExpand;   
   
        public   int  getCount() {   
            return  mfilelist.size();   
        }   
   
        public  Object getItem( int  position) {   
            return  position;   
        }   
   
        public   long  getItemId( int  position) {   
            return  position;   
        }   
   
        public  View getView( int  position, View convertView, ViewGroup parent) {   
            ViewHolder holder;   
            /* if (convertView == null) { */    
            convertView = mInflater.inflate(R.layout.group_main, null );   
            holder = new  ViewHolder();   
            holder.text = (TextView) convertView.findViewById(R.id.text);   
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);   
            convertView.setTag(holder);   
            /*   
             * } else { holder = (ViewHolder) convertView.getTag(); }   
             */    
   
            final  TreeElement obj = mfilelist.get(position);   
   
            holder.text.setOnClickListener(new  View.OnClickListener() {   
   
                @Override    
                public   void  onClick(View v) {   
                    Log.i("TreeView" ,  "obj.id:"  + obj.getId());   
                    GoHumanResourceActivity(obj.getId());
                }   
            });   
   
            int  level = obj.getLevel();   
            holder.icon.setPadding(25  * (level +  1 ),   
                    holder.icon.getPaddingTop(), 0 ,   
                    holder.icon.getPaddingBottom());   
            holder.text.setText(obj.getOutlineTitle());   
            if  (obj.isMhasChild() && (obj.isExpanded() ==  false )) {   
                holder.icon.setImageBitmap(mIconCollapse);   
            } else   if  (obj.isMhasChild() && (obj.isExpanded() ==  true )) {   
                holder.icon.setImageBitmap(mIconExpand);   
            } else   if  (!obj.isMhasChild()) {   
                holder.icon.setImageBitmap(mIconCollapse);   
                holder.icon.setVisibility(View.INVISIBLE);   
            }   
            return  convertView;   
        }   
   
        class  ViewHolder {   
            TextView text;   
            ImageView icon;   
   
        }   
    }   
   
   /* @Override    
    protected   void  onListItemClick(ListView l, View v,  int  position,  long  id) {   
        super .onListItemClick(l, v, position, id);   
        
    }*/

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		// TODO Auto-generated method stub
		Log.i("TreeView" ,  "position:"  + position);   
        if  (!mPdfOutlinesCount.get(position).isMhasChild()) {   
            Toast.makeText(this , mPdfOutlinesCount.get(position)   
                    .getOutlineTitle(), 2000 );   
            /*   
             * int pageNumber; Intent i = getIntent(); element element =   
             * mPdfOutlinesCount .get(position); pageNumber =   
             * element.getOutlineElement().pageNumber; if (pageNumber <= 0) {   
             * String name = element.getOutlineElement().destName; pageNumber =   
             * idocviewer.getPageNumberForNameForOutline(name);   
             * element.getOutlineElement().pageNumber = pageNumber;   
             * element.getOutlineElement().destName = null; }   
             * i.putExtra("PageNumber", pageNumber); setResult(RESULT_OK, i);   
             * finish();   
             */    
   
            return ;   
        }   
   
        if  (mPdfOutlinesCount.get(position).isExpanded()) {   
            mPdfOutlinesCount.get(position).setExpanded(false );   
            TreeElement element = mPdfOutlinesCount.get(position);   
            ArrayList<TreeElement> temp = new  ArrayList<TreeElement>();   
   
            for  ( int  i = position +  1 ; i < mPdfOutlinesCount.size(); i++) {   
                if  (element.getLevel() >= mPdfOutlinesCount.get(i).getLevel()) {   
                    break ;   
                }   
                temp.add(mPdfOutlinesCount.get(i));   
            }   
   
            mPdfOutlinesCount.removeAll(temp);   
   
            treeViewAdapter.notifyDataSetChanged();   
            /*   
             * fileExploreAdapter = new TreeViewAdapter(this, R.layout.outline,   
             * mPdfOutlinesCount);   
             */    
   
            // setListAdapter(fileExploreAdapter);    
   
        } else  {   
            TreeElement obj = mPdfOutlinesCount.get(position);   
            obj.setExpanded(true );   
            int  level = obj.getLevel();   
            int  nextLevel = level +  1 ;   
   
            for  (TreeElement element : obj.getChildList()) {   
                element.setLevel(nextLevel);   
                element.setExpanded(false );   
                mPdfOutlinesCount.add(position + 1 , element);   
   
            }   
            treeViewAdapter.notifyDataSetChanged();   
            /*   
             * fileExploreAdapter = new TreeViewAdapter(this, R.layout.outline,   
             * mPdfOutlinesCount);   
             */    
   
            // setListAdapter(fileExploreAdapter);    
        }   
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	private void UpdateFilterResult(String str) throws BadHanyuPinyinOutputFormatCombination{
		
		
		mPdfOutlinesCount.clear();
		
		
		if(str.isEmpty()){
			
			mPdfOutlinesCount.addAll(mTopitems);
		}else{
			
			
			for(GroupItem info:mAllitems){
				String name = info.getGroup_name();
				

				if(name == null){
					continue ;
				}
				
				List<GroupItem> childs ;
				if(name.indexOf(str)!=-1 || PinYinKit.getPingYin(name).startsWith(str)|| PinYinKit.getPingYin(name).startsWith(str.toUpperCase().toString())
						||PinYinKit.getPingYin(name).indexOf(str)!=-1||PinYinKit.getPingYin(name).indexOf(str.toUpperCase())!=-1)
				{
					TreeElement element1 = new  TreeElement( info.getGroup_id() , info.getGroup_name());  
		    		
		    		mPdfOutlinesCount.add(element1);
					
				}
			}
			
			
		}
		
		
		treeViewAdapter.notifyDataSetChanged();  
	}
	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		try {
			UpdateFilterResult(arg0.toString());
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   
   
}