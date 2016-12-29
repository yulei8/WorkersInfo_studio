package com.chinamobile.workerspace.filemanagement;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;

public class UserOperate {
    public static final String TAG = "UserOperate";
    FileChooserActivity mMainActivity;

    public UserOperate(FileChooserActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void send(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        mMainActivity.startActivity(intent);
    }

    public void send(ArrayList<HashMap<String, Object>> selectedItem) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("*/*");
        ArrayList<Uri> uris = new ArrayList<Uri>();
        for (HashMap<String, Object> item : selectedItem) {
            Uri u=Uri.fromFile((File) item.get(FileManager.FILE));
            uris.add(u); 
        }
        Print(uris);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        mMainActivity.startActivity(intent);
    }

    public void selectAll() {
        for (HashMap<String, Object> item : mMainActivity.getListItem()) {
            item.put(FileManager.IS_CHECKED, true);
        }
        mMainActivity.refreshListView();
    }

    public void selectNone() {
        for (HashMap<String, Object> item : mMainActivity.getListItem()) {
            item.put(FileManager.IS_CHECKED, false);
        }
        mMainActivity.refreshListView();
    }

    public void select(int index) {
        HashMap<String, Object> item = mMainActivity.getListItem().get(index);
        Boolean checked = (Boolean) item.get(FileManager.IS_CHECKED);
        checked = !checked;
        if (checked)
            mMainActivity.addRadioChecked();
        else
            mMainActivity.delRadioChecked();
        item.put(FileManager.IS_CHECKED, checked);
        mMainActivity.refreshListView();
    }

    public void sortListItem(ArrayList<HashMap<String, Object>> listItem) {
        Collections.sort(listItem, getComparator());
    }

    Comparator<HashMap<String, Object>> comparatorByName = new Comparator<HashMap<String, Object>>() {
        
        public int compare(HashMap<String, Object> arg0,
                HashMap<String, Object> arg1) {
            File file0 = (File) arg0.get(FileManager.FILE);
            File file1 = (File) arg1.get(FileManager.FILE);
            if (file0.isDirectory() && file1.isFile()) {
                Print(file0.getAbsolutePath() + " " + file1.getAbsolutePath()
                        + " " + -1);
                return -1;
            } else if (file1.isDirectory() && file0.isFile()) {
                Print(file0.getAbsolutePath() + " " + file1.getAbsolutePath()
                        + " " + 1);
                return 1;
            } else {
                String title0 = (String) arg0.get(FileManager.TITLE);
                String title1 = (String) arg1.get(FileManager.TITLE);
                return title0.compareToIgnoreCase(title1);
            }
        }
    };
    Comparator<HashMap<String, Object>> comparatorBySize = new Comparator<HashMap<String, Object>>() {
        
        public int compare(HashMap<String, Object> arg0,
                HashMap<String, Object> arg1) {
            File file0 = (File) arg0.get(FileManager.FILE);
            File file1 = (File) arg1.get(FileManager.FILE);
            if (file0.isDirectory() && file1.isFile()) {
                return -1;
            } else if (file1.isDirectory() && file0.isFile()) {
                return 1;
            } else {
                if (file0.isDirectory() && file1.isDirectory()) {
                    String title0 = (String) arg0.get(FileManager.TITLE);
                    String title1 = (String) arg1.get(FileManager.TITLE);
                    return title0.compareToIgnoreCase(title1);
                } else
                    return (int) ((Long) arg0.get(FileManager.SIZE) - (Long) arg1
                            .get(FileManager.SIZE));
            }
        }
    };
    Comparator<HashMap<String, Object>> comparatorByTime = new Comparator<HashMap<String, Object>>() {
        
        public int compare(HashMap<String, Object> arg0,
                HashMap<String, Object> arg1) {
            File file0 = (File) arg0.get(FileManager.FILE);
            File file1 = (File) arg1.get(FileManager.FILE);
            if (file0.isDirectory() && file1.isFile()) {
                return -1;
            } else if (file1.isDirectory() && file0.isFile()) {
                return 1;
            } else {
                return ((Date) arg0.get(FileManager.TIME))
                        .compareTo((Date) arg1.get(FileManager.TIME));
            }
        }
    };
    Comparator<HashMap<String, Object>> comparatorByType = new Comparator<HashMap<String, Object>>() {
        
        public int compare(HashMap<String, Object> arg0,
                HashMap<String, Object> arg1) {
            File file0 = (File) arg0.get(FileManager.FILE);
            File file1 = (File) arg1.get(FileManager.FILE);
            if (file0.isDirectory() && file1.isFile()) {
                return -1;
            } else if (file1.isDirectory() && file0.isFile()) {
                return 1;
            } else {
                String title0 = (String) arg0.get(FileManager.TITLE);
                String title1 = (String) arg1.get(FileManager.TITLE);
                String end0 = title0.substring(title0.lastIndexOf(".") + 1,
                        title0.length()).toLowerCase();
                String end1 = title1.substring(title1.lastIndexOf(".") + 1,
                        title1.length()).toLowerCase();
                if (end0.equals(end1)) {
                    return title0.compareToIgnoreCase(title1);
                } else {
                    return end0.compareToIgnoreCase(end1);
                }
            }
        }
    };

    // 鎸夊悕绉版帓搴�    // 鎸夋枃浠跺悕浠ュ瓧姣嶉『搴忔帓鍒椼�
    //
    // 鎸夊ぇ灏忔帓搴�    // 鎸夋枃浠跺ぇ灏�鏂囦欢鍗犵敤鐨勭鐩樼┖闂�鎺掑簭銆傞粯璁ゆ儏鍐典笅浼氫粠鏈�皬鍒版渶澶ф帓鍒椼�
    //
    // 鎸夌被鍨嬫帓搴�    // 鎸夋枃浠剁被鍨嬩互瀛楁瘝椤哄簭鎺掑垪銆備細灏嗗悓绫绘枃浠跺綊骞跺埌涓�捣锛岀劧鍚庢寜鍚嶇О鎺掑簭銆�    //
    // 鎸変慨鏀规棩鏈熸帓搴�    // 鎸変笂娆℃洿鏀规枃浠剁殑鏃ユ湡鍜屾椂闂存帓搴忋�榛樿鎯呭喌涓嬩細浠庢渶鏃у埌鏈�柊鎺掑垪銆�   
    public Comparator<HashMap<String, Object>> getComparator() {
        Comparator comparator = null;
        switch (mMainActivity.getFileSort()) {
        case FileChooserActivity.MENU_SORT_NAME:
            comparator = comparatorByName;
            break;
        case FileChooserActivity.MENU_SORT_SIZE:
            comparator = comparatorBySize;
            break;
        case FileChooserActivity.MENU_SORT_TIME:
            comparator = comparatorByTime;
            break;
        case FileChooserActivity.MENU_SORT_TYPE:
            comparator = comparatorByType;
            break;
        }
        return comparator;
    }

    public static void Print(String TAG, Object obj) {
        Log.i(TAG, String.valueOf(obj));
    }

    public static void Print(Object obj) {
        Log.i(TAG, String.valueOf(obj));
    }

}
