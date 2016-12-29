package com.chinamobile.workerspace.filemanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import com.chinamobile.workerspace.R;
import com.chinamobile.workerspace.db.CopyDbhelper;
import com.chinamobile.workerspace.utils.DialogUtil;
import com.chinamobile.workerspace.utils.SharedPrefs;
import com.chinamobile.workerspace.utils.T;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FileChooserActivity extends Activity implements OnClickListener,
		OnCreateContextMenuListener, OnItemClickListener {
	public static final int MENU_SELECT_ALL = 0;
	public static final int MENU_SORT = 1;
	public static final int MENU_SORT_NAME = 2;
	public static final int MENU_SORT_SIZE = 3;
	public static final int MENU_SORT_TIME = 4;
	public static final int MENU_SORT_TYPE = 5;
	public static final int MENU_NEW = 6;
	public static final int MENU_DISPLAY = 7;
	public static final int MENU_REFRESH = 8;
	public static final int MENU_COPY = 10;
	public static final int MENU_CUT = 11;
	public static final int MENU_SEND = 12;
	public static final int MENU_RENAME = 13;
	public static final int MENU_DELETE = 14;
	public static final int MENU_DETAIL = 15;

	public static final String TAG = "FileManagerment";
	ArrayList<HashMap<String, Object>> listItem;
	ArrayList<HashMap<String, Object>> selectedItem;
	ListView listView;
	ListViewLocationStack stack;
	String currentPath;
	TextView btnPath;
	Button btnBack;
	
	MyFileAdapter myAdapter;
	Utils utils = new Utils();

	FileManager fileManager;
	UserOperate userOperate;
	boolean menuSelectAllFlag = false;
	boolean displayAllFlag = false;
	int fileSortFlag = MENU_SORT_NAME;
	int longPressPosition = 0;
	boolean radioVisibleFlag = true;
	boolean copyFlag = true;
	int radioCheckedCount = 0;

	
	private Dialog   mProcessDlg;
	private ImportThread  mImportThread;
	
	public boolean isCopy() {
		return copyFlag;
	}

	public void addRadioChecked() {
		
	}

	public void delRadioChecked() {
		
	}

	public boolean isRadioVisible() {
		return false;
	}

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.filemgr_main);
		
		stack = new ListViewLocationStack(100);
		listView = (ListView) findViewById(R.id.file_listview);
		listView.setOnItemClickListener(this);
		
		btnPath = (TextView) findViewById(R.id.path);
		btnPath.setOnClickListener(this);
		
		btnBack = (Button) findViewById(R.id.file_btn_back);
		btnBack.setOnClickListener(this);
		
		mProcessDlg   = DialogUtil.GetCustomProgressDialog(this,R.string.importing);
        
		mImportThread = new ImportThread(this);
		
		fileManager = new FileManager(this);
		selectedItem = new ArrayList<HashMap<String, Object>>();


		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			
			
			
			File file =  Environment.getExternalStorageDirectory();
			currentPath = file.getPath();
			btnPath.setText(currentPath);
			//Log.d("yulei", "currentpath:"+currentPath);
			listItem = fileManager.getFilesList(currentPath);
			userOperate = new UserOperate(this);
			userOperate.sortListItem(listItem);
			myAdapter = new MyFileAdapter(this, userOperate);
			listView.setAdapter(myAdapter);
		} else {
			Print("No SdCard");
			finish();
		}
	}

	public void PrintList() {
		int i = 0;
		for (HashMap<String, Object> item : listItem) {
			Print(i++ + "\t" + item.get(FileManager.TITLE));
		}
	}

	public ArrayList<HashMap<String, Object>> getListItem() {
		return listItem;
	}

	

	
	public void onClick(View view) {
		if (view == btnPath) {
			refreshDirFromSDCard(currentPath);
		} else if (view == btnBack) {
			BackPressed();
		}
	}

	public void selectAll() {
		for (HashMap<String, Object> item : listItem) {
			item.put(FileManager.IS_CHECKED, true);
		}
		radioCheckedCount = 0;
	}
	public void selectNone() {
        for (HashMap<String, Object> item : listItem) {
            item.put(FileManager.IS_CHECKED, false);
        }
        radioCheckedCount = listItem.size();
    }

	
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		File file = (File) listItem.get(arg2).get(FileManager.FILE);
		if (file.isDirectory()) {
			stack.push(listView.getFirstVisiblePosition());
			currentPath = file.getAbsolutePath();
			btnPath.setText(currentPath);
			refreshDirFromSDCard(currentPath);
			radioCheckedCount = 0;
		} else {
			//startActivity(fileManager.openFile(file.getPath()));
			/*
			Intent mIntent = new Intent();  
			mIntent.putExtra("mediapath", file.getPath());  
			this.setResult(RESULT_OK, mIntent);   
			finish();*/
			
			if(file.getName().endsWith(".db")==true){
				mProcessDlg.show() ;
				mImportThread.SetDbPath(file.getPath());
				mImportThread.start();
			}else{
				T.showShort(this, R.string.is_not_database);
			}
				
			
			//do you want to update databases?
		}
	}
   
	public final int IMPORT_RESULT =  1 ;
	
	private Handler  mHandler = new Handler(){
		public void handleMessage(Message msg) { 
			
			switch (msg.what) {      
            case IMPORT_RESULT:  
            	mProcessDlg.dismiss();
            	if(msg.arg1 == 1){
            		
            		T.showShort(FileChooserActivity.this, R.string.import_sucess);
            		
            		
            		SharedPrefs.GetInstance(FileChooserActivity.this).PutImagePath(currentPath);
            		
            		finish();//back to setting 
            	}else{
            		T.showShort(FileChooserActivity.this, R.string.import_failed);
            	}
                break;  
             default:
            	 break;
			}
			
			
		}
		
	};
	
	
	public class ImportThread extends Thread{
		private Context  mContext ;
		private String   mDestdb ;
		
		private CopyDbhelper  dbcopy ;
		ImportThread(Context t){
			mContext = t ;
	
			dbcopy = new CopyDbhelper(t);
		}
		
		public void SetDbPath(String path){
			mDestdb = path;
		}
		public void run() {
			boolean result = dbcopy.ImportDatabase(mDestdb);
			dbcopy.close();
			Message msg = new Message();
			msg.what = IMPORT_RESULT ;
			if(result == true){
				msg.arg1 = 1 ;
			}else{
				msg.arg1 = 0 ;
			}
							
			//mHandler.sendEmptyMessage(SEARCH_SUCCESS);
			mHandler.sendMessage(msg);
		}
	}
	public void separateSelectedItemFromListItem() {
		selectedItem.clear();
		for (HashMap<String, Object> item : listItem) {
			if ((Boolean) item.get(FileManager.IS_CHECKED))
				selectedItem.add(item);
		}
		for (HashMap<String, Object> item : selectedItem) {
			listItem.remove(item);
		}
	}

	public void mergeSelectedItemToListItem() {
		listItem.addAll(selectedItem);
		selectedItem.clear();
		userOperate.sortListItem(listItem);
	}

	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (radioCheckedCount == 0) {
			menu.add(Menu.NONE, MENU_COPY, 0, R.string.copy);
			menu.add(Menu.NONE, MENU_CUT, 1, R.string.cut);
			menu.add(Menu.NONE, MENU_SEND, 2, R.string.send);
			menu.add(Menu.NONE, MENU_RENAME, 3, R.string.rename);
			menu.add(Menu.NONE, MENU_DELETE, 4, R.string.delete);
			menu.add(Menu.NONE, MENU_DETAIL, 5, R.string.detail);
			longPressPosition = ((AdapterContextMenuInfo) menuInfo).position;
		}
	}

	public void refreshListView() {
		myAdapter.notifyDataSetChanged();
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		/*
		 * 
		 * add()鏂规硶鐨勫洓涓弬鏁帮紝渚濇鏄細
		 * 
		 * 1銆佺粍鍒紝濡傛灉涓嶅垎缁勭殑璇濆氨鍐橫enu.NONE,
		 * 
		 * 2銆両d锛岃繖涓緢閲嶈锛孉ndroid鏍规嵁杩欎釜Id鏉ョ‘瀹氫笉鍚岀殑鑿滃崟
		 * 
		 * 3銆侀『搴忥紝閭ｄ釜鑿滃崟鐜板湪鍦ㄥ墠闈㈢敱杩欎釜鍙傛暟鐨勫ぇ灏忓喅瀹�
		 * 
		 * 4銆佹枃鏈紝鑿滃崟鐨勬樉绀烘枃鏈�
		 */
		// setIcon()鏂规硶涓鸿彍鍗曡缃浘鏍囷紝杩欓噷浣跨敤鐨勬槸绯荤粺鑷甫鐨勫浘鏍囷紝鍚屽浠暀鎰忎竴涓�,浠�
		// android.R寮�澶寸殑璧勬簮鏄郴缁熸彁渚涚殑锛屾垜浠嚜宸辨彁渚涚殑璧勬簮鏄互R寮�澶寸殑
		menu.add(Menu.NONE, MENU_SELECT_ALL, 1, R.string.menu_select_all);
		SubMenu sortSubMenu = menu.addSubMenu(Menu.NONE, MENU_SORT, 2,
				R.string.menu_sort);
		int sortSubMenuGroup = 1;
		sortSubMenu.add(sortSubMenuGroup, MENU_SORT_NAME, Menu.NONE,
				R.string.menu_sort_name).setChecked(true);
		sortSubMenu.add(sortSubMenuGroup, MENU_SORT_SIZE, Menu.NONE,
				R.string.menu_sort_size);
		sortSubMenu.add(sortSubMenuGroup, MENU_SORT_TIME, Menu.NONE,
				R.string.menu_sort_time);
		sortSubMenu.add(sortSubMenuGroup, MENU_SORT_TYPE, Menu.NONE,
				R.string.menu_sort_type);
		sortSubMenu.setGroupCheckable(sortSubMenuGroup, true, true);
		// menu.add(Menu.NONE, MENU_NEW, 3,
		// R.string.menu_new).setIcon(android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE, MENU_NEW, 3, R.string.menu_new);
		menu.add(Menu.NONE, MENU_DISPLAY, 4, R.string.menu_diaplay_hiden);
		menu.add(Menu.NONE, MENU_REFRESH, 5, R.string.menu_refresh);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_SELECT_ALL:
			menuSelectAllFlag = !menuSelectAllFlag;
			if (menuSelectAllFlag) {
				item.setTitle(R.string.menu_select_all);
				userOperate.selectAll();
			} else {
				item.setTitle(R.string.menu_cancel_select_all);
				userOperate.selectNone();
			}
			break;
		case MENU_SORT:
			break;
		case MENU_SORT_NAME:
			item.setChecked(true);
			fileSortFlag = MENU_SORT_NAME;
			userOperate.sortListItem(listItem);
			refreshListView();
			break;
		case MENU_SORT_SIZE:
			item.setChecked(true);
			fileSortFlag = MENU_SORT_SIZE;
			userOperate.sortListItem(listItem);
			refreshListView();
			break;
		case MENU_SORT_TIME:
			item.setChecked(true);
			fileSortFlag = MENU_SORT_TIME;
			userOperate.sortListItem(listItem);
			refreshListView();
			break;
		case MENU_SORT_TYPE:
			item.setChecked(true);
			fileSortFlag = MENU_SORT_TYPE;
			userOperate.sortListItem(listItem);
			refreshListView();
			break;
		case MENU_NEW:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setIcon(R.drawable.delete);
			dialog.setTitle(R.string.menu_new);
			dialog.setMessage(R.string.input_new_folder_info);
			final EditText input = new EditText(this);
			dialog.setView(input);
			dialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Print("NewFloder");
							String inputStr = input.getText().toString().trim();
							String errorStr = null;
							if (inputStr.equals(""))
								errorStr = getResources().getString(
										R.string.new_folder_null_err);
							else if (inputStr.contains(File.separator))
								errorStr = getResources().getString(
										R.string.new_folder_spec_char_err);
							else if (fileManager.newFolder(currentPath
									+ File.separator + inputStr) == false)
								errorStr = getResources().getString(
										R.string.new_folder_exist_err);
							else
								refreshDirFromSDCard(currentPath);
							if (errorStr != null) {
								AlertDialog.Builder warningDialog = new AlertDialog.Builder(
										FileChooserActivity.this);
								warningDialog
										.setIcon(android.R.drawable.ic_dialog_alert);
								warningDialog.setMessage(errorStr);
								warningDialog.setPositiveButton("OK", null);
								warningDialog.show();
							}
						}
					});
			dialog.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			dialog.show();
			break;
		case MENU_DISPLAY:
			displayAllFlag = !displayAllFlag;
			if (displayAllFlag) {
				item.setTitle(R.string.menu_diaplay_hiden);
			} else {
				item.setTitle(R.string.menu_not_diaplay_hiden);
			}
			refreshDirFromSDCard(currentPath);
			break;
		case MENU_REFRESH:
			refreshDirFromSDCard(currentPath);
			break;
		}
		return false;

	}

	public void refreshDirFromSDCard(String path) {
		int location = listView.getFirstVisiblePosition();
		listItem = fileManager.getFilesList(path);
		listItem.removeAll(selectedItem);
		userOperate.sortListItem(listItem);
		refreshListView();
		radioCheckedCount=0;
		listView.setSelection(location);
	}

	public int getFileSort() {
		return fileSortFlag;
	}

	public boolean isDisplayAll() {
		return displayAllFlag;
	}

	
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_COPY:
			listItem.get(longPressPosition).put(FileManager.IS_CHECKED, true);
			copy();
			break;
		case MENU_CUT:
			listItem.get(longPressPosition).put(FileManager.IS_CHECKED, true);
			cut();
			break;
		case MENU_SEND:
			userOperate.send((File) listItem.get(longPressPosition).get(
					FileManager.FILE));
			break;
		case MENU_RENAME: {
			final File file = (File) listItem.get(longPressPosition).get(
					FileManager.FILE);
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setIcon(android.R.drawable.ic_dialog_info);
			dialog.setTitle(R.string.rename);
			dialog.setMessage(R.string.input_rename_info);
			final EditText input = new EditText(this);
			dialog.setView(input);
			dialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Print("Rename");
							String inputStr = input.getText().toString().trim();
							String errorStr = null;
							if (inputStr.equals(""))
								errorStr = getResources().getString(
										R.string.rename_null_err);
							else if (inputStr.contains(File.separator))
								errorStr = getResources().getString(
										R.string.rename_spec_char_err);
							else if (fileManager.rename(file.getAbsolutePath(),
									currentPath + File.separator + inputStr) == false)
								errorStr = getResources().getString(
										R.string.rename_exist_err);
							else
								refreshDirFromSDCard(currentPath);
							if (errorStr != null) {
								AlertDialog.Builder warningDialog = new AlertDialog.Builder(
										FileChooserActivity.this);
								warningDialog
										.setIcon(android.R.drawable.ic_dialog_alert);
								warningDialog.setMessage(errorStr);
								warningDialog.setPositiveButton("OK", null);
								warningDialog.show();
							}
						}
					});
			dialog.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			dialog.show();
		}
			break;
		case MENU_DELETE: {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setIcon(android.R.drawable.ic_menu_delete);
			dialog.setMessage(R.string.delete_info);
			dialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							fileManager.delete((File) listItem.get(
									longPressPosition).get(FileManager.FILE));
							refreshDirFromSDCard(currentPath);
						}
					});
			dialog.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			dialog.show();
		}
			break;
		case MENU_DETAIL:
			File file = (File) listItem.get(longPressPosition).get(
					FileManager.FILE);
			AlertDialog.Builder detailDialog = new AlertDialog.Builder(
					FileChooserActivity.this);
			detailDialog.setIcon(android.R.drawable.ic_dialog_info);
			String path = getResources().getString(R.string.detail_path)
					+ file.getAbsolutePath();
			String size = getResources().getString(R.string.detail_size)
					+ utils.sizeAddUnit(fileManager.fileSize(file));
			String canRead = getResources().getString(R.string.detail_can_read)
					+ file.canRead();
			String canWrite = getResources().getString(
					R.string.detail_can_write)
					+ file.canWrite();
			String hidden = getResources().getString(R.string.detail_hidden)
					+ file.isHidden();
			detailDialog.setTitle(file.getName());
			detailDialog.setItems(new String[] { path, size, canRead, canWrite,
					hidden }, null);
			detailDialog.setPositiveButton("OK", null);
			detailDialog.show();
			break;
		}

		return super.onContextItemSelected(item);
	}

	private void commonInCopyAndCut() {
		separateSelectedItemFromListItem();
		radioVisibleFlag = false;
		int location = listView.getFirstVisiblePosition();
		refreshListView();
		listView.setSelection(location);
	}

	public void copy() {
		copyFlag = true;
		commonInCopyAndCut();
	}

	public void cut() {
		copyFlag = false;
		commonInCopyAndCut();
	}

	
	public void onBackPressed() {
		BackPressed();
	}

	public void BackPressed() {
		if (currentPath.lastIndexOf("/") >0) {
			currentPath = currentPath
					.substring(0, currentPath.lastIndexOf("/"));
			radioCheckedCount = 0;
			btnPath.setText(currentPath);
			refreshDirFromSDCard(currentPath);
			listView.setSelection(stack.pull());
		} else {
			super.onBackPressed();
		}
		Print(currentPath);
	}

	public static void Print(String TAG, Object obj) {
		Log.i(TAG, String.valueOf(obj));
	}

	public static void Print(Object obj) {
		Log.i(TAG, String.valueOf(obj));
	}

}
