package com.chinamobile.workerspace.filemanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import com.chinamobile.workerspace.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class FileManager {
	public static final String TAG = "FileManage";
	public static final String IS_DIR = "isDir";
	public static final String FILE = "FILE";
	public static final String IMAGE = "image";
	public static final String TITLE = "title";
	public static final String COUNT = "count";
	public static final String SIZE = "size";
	public static final String TIME = "time";
	public static final String IS_CHECKED = "isChecked";
	FileChooserActivity mMainActivity;

	public FileManager(FileChooserActivity mainActivity) {
		mMainActivity = mainActivity;
	}

	public void copy(ArrayList<HashMap<String, Object>> selectedItem,
			String targetFolder) {
		for (HashMap<String, Object> item : selectedItem) {
			File srcFile = (File) item.get(FileManager.FILE);
			File descFile = new File(targetFolder + File.separator
					+ srcFile.getName());
			copyFile(srcFile, descFile);
			Print("src:\t"+srcFile.getAbsolutePath()+"\tdesc:\t"+descFile.getAbsolutePath());
		}
	}
	public void cut(ArrayList<HashMap<String, Object>> selectedItem,
			String targetFolder){
		for (HashMap<String, Object> item : selectedItem) {
			File srcFile = (File) item.get(FileManager.FILE);
			File descFile = new File(targetFolder + File.separator
					+ srcFile.getName());
			srcFile.renameTo(descFile);
		}
	}

	public void copyFile(final File sourceFile, final File targetFile) {
		if (sourceFile == null || targetFile == null)
			return;
		if(sourceFile.equals(targetFile))
		    return;
		if(targetFile.exists()){
			
		    AlertDialog.Builder dialog = new AlertDialog.Builder(mMainActivity);
            dialog.setIcon(android.R.drawable.ic_dialog_alert);
            dialog.setMessage((mMainActivity).getResources().getString(R.string.copy_file_exist_info, targetFile.getName()));
            dialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            copy(sourceFile,targetFile);
                        }
                    });
            dialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            dialog.show();
		}
		
	}
	public void copy(File sourceFile, File targetFile){
	    if (sourceFile.isFile()) {
            FileInputStream in = null;
            FileOutputStream out = null;
            try {
                in = new FileInputStream(sourceFile);
                out = new FileOutputStream(targetFile);
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                out.flush();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                    if (out != null)
                        out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            targetFile.mkdir();
            File[] files = sourceFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                copyFile(files[i], new File(targetFile+File.separator+files[i].getName()));
            }
        }
	}

	public long fileSize(File file) {
		if (file == null) {
			Print("fileSize(File file),file=null");
			return 0;
		}
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				long size = 0;
				for (File f : files) {
					size += fileSize(f);
				}
				return size;
			} else {
				try {
					return new FileInputStream(file).available();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public void delete(ArrayList<HashMap<String, Object>> selectedItem) {
		for (HashMap<String, Object> item : selectedItem) {
			delete((File) item.get(FileManager.FILE));
		}
	}

	public void delete(String path) {
		if (path == null) {
			Print("delete(String FILE),FILE=null");
			return;
		}
		delete(new File(path));
	}

	public void delete(File f) {
		if (f == null) {
			Print("delete(File f),file=null");
			return;
		}
		if (f.exists()) {
			if (f.isDirectory()) {
				if (f.listFiles().length == 0) {
					if (!f.delete()) {
						AlertDialog.Builder warningDialog = new AlertDialog.Builder(
								mMainActivity);
						warningDialog
								.setIcon(android.R.drawable.ic_dialog_alert);
						warningDialog.setMessage(String.format(mMainActivity
								.getResources()
								.getString(R.string.delete_folder_err,
										f.getName())));
						warningDialog.setPositiveButton("OK", null);
						warningDialog.show();
					}
				} else {
					File delFile[] = f.listFiles();
					int i = f.listFiles().length;
					for (int j = 0; j < i; j++)
						delete(delFile[j]);
					delete(f);
				}
			} else {
				if (!f.delete()) {
					AlertDialog.Builder warningDialog = new AlertDialog.Builder(
							mMainActivity);
					warningDialog.setIcon(android.R.drawable.ic_dialog_alert);
					warningDialog.setMessage(String.format(mMainActivity
							.getResources().getString(R.string.delete_file_err,
									f.getName())));
					warningDialog.setPositiveButton("OK", null);
					warningDialog.show();
				}
			}
		}
	}

	public ArrayList<HashMap<String, Object>> getFilesList(String FILE) {
		if (FILE == null) {
			Print("The FILE (" + FILE + ") in getFilesList is null");
			return null;
		}
		return getFilesList(new File(FILE));
	}

	// 鑾峰彇璺緞path涓嬬殑鎵�鏈夋枃浠朵俊鎭�
	public ArrayList<HashMap<String, Object>> getFilesList(File file) {
		if (file == null) {
			Print("The file (" + file + ") is not exist!");
			return null;
		}
		File[] files = file.listFiles();
		if (files == null) {
			Print("The files under dir(" + file.getAbsolutePath()
					+ ") is not null!");
			return null;
		}
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < files.length; i++) {
			if (isDisplay(files[i])) {
				if (files[i].isDirectory()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put(IS_DIR, true);
					map.put(FILE, files[i]);
					map.put(IMAGE, R.drawable.folder);
					map.put(TITLE, files[i].getName());
					int count = 0 ;
					if (files[i].listFiles() == null)
						count = 0  ;
					else
						count =  getDirectoryCount(files[i]);
					
					map.put(COUNT, "(" + count + ")");
					map.put(TIME, new Date(files[i].lastModified()));
					map.put(IS_CHECKED, false);
					if(count !=0)
					  listItem.add(map);
				} else {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put(IS_DIR, false);
					map.put(FILE, files[i]);
					Bitmap bmp = getThumbnail(files[i].getAbsolutePath());
					if (bmp == null)
						map.put(IMAGE, R.drawable.file);
					else
						map.put(IMAGE, bmp);
					map.put(TITLE, files[i].getName());
					map.put(COUNT, "");
					map.put(TIME, new Date(files[i].lastModified()));
					try {
						long size = new FileInputStream(files[i]).available();
						map.put(SIZE, size);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					map.put(IS_CHECKED, false);
					listItem.add(map);
				}
			}
		}
		return listItem;
	}

	public int getDirectoryCount(File file) {
		int result = 0;
		if (file == null)
			return result;
		File[] files = file.listFiles();
		if (files == null)
			return result;
		for (int i = 0; i < files.length; i++) {
			if (isDisplay(files[i]))
				result++;
		}
		return result;
	}

	public int getFileCountIndir(File file){
		int filecount = 0 ;
		if(file.isDirectory() == false){
			return 1;
		}else{
			File[] files = file.listFiles();
			if (files == null)
				return 0;
			for(int i = 0 ; i<files.length; i++){
				filecount += getFileCountIndir(files[i]);
				
			}
		}
		return filecount;
	}
	
	
	
	public static void Print(Object obj) {
		FileChooserActivity.Print(TAG, obj);
	}

	public boolean isDisplay(File file) {
		if (!mMainActivity.isDisplayAll()) {
			if (file.getName().startsWith(".") || file.isHidden()){
				return false;
			}else {
				return true;
			}
	
		} else return true;
	}

	public boolean newFolder(String FILE) {
		File file = new File(FILE);
		return file.mkdir();
	}

	public boolean rename(String oldName, String newName) {
		File file1 = new File(oldName);
		File file2 = new File(newName);
		return file1.renameTo(file2);
	}

	public Bitmap getThumbnail(String filePath) {
		int width = 48;
		int height = 48;
		File file = new File(filePath);
		if (!file.exists())
			return null;
		String end = file
				.getName()
				.substring(file.getName().lastIndexOf(".") + 1,
						file.getName().length()).toLowerCase();
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			return null;
		} else if (end.equals("3gp") || end.equals("mp4")) {
			return getVideoThumbnail(filePath, width, height,
					MediaStore.Images.Thumbnails.MICRO_KIND);
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			return getImageThumbnail(filePath, width, height);
			// }else if(end.equals("apk")){
			// return getApkFileIntent(filePath);
			// }else if(end.equals("ppt")){
			// return getPptFileIntent(filePath);
			// }else if(end.equals("xls")){
			// return getExcelFileIntent(filePath);
			// }else if(end.equals("doc")){
			// return getWordFileIntent(filePath);
			// }else if(end.equals("pdf")){
			// return getPdfFileIntent(filePath);
			// }else if(end.equals("chm")){
			// return getChmFileIntent(filePath);
			// }else if(end.equals("txt")){
			// return getTextFileIntent(filePath,false);
			// }else{
			// return getAllIntent(filePath);
		}
		return null;
	}

	/**
	 * 鑾峰彇瑙嗛鐨勭缉鐣ュ浘 鍏堥�氳繃ThumbnailUtils鏉ュ垱寤轰竴涓棰戠殑缂╃暐鍥撅紝鐒跺悗鍐嶅埄鐢═humbnailUtils鏉ョ敓鎴愭寚瀹氬ぇ灏忕殑缂╃暐鍥俱��
	 * 濡傛灉鎯宠鐨勭缉鐣ュ浘鐨勫鍜岄珮閮藉皬浜嶮ICRO_KIND锛屽垯绫诲瀷瑕佷娇鐢∕ICRO_KIND浣滀负kind鐨勫�硷紝杩欐牱浼氳妭鐪佸唴瀛樸��
	 * 
	 * @param videoPath
	 *            瑙嗛鐨勮矾寰�
	 * @param width
	 *            鎸囧畾杈撳嚭瑙嗛缂╃暐鍥剧殑瀹藉害
	 * @param height
	 *            鎸囧畾杈撳嚭瑙嗛缂╃暐鍥剧殑楂樺害搴�
	 * @param kind
	 *            鍙傜収MediaStore.Images.Thumbnails绫讳腑鐨勫父閲廙INI_KIND鍜孧ICRO_KIND銆�
	 *            鍏朵腑锛孧INI_KIND: 512 x 384锛孧ICRO_KIND: 96 x 96
	 * @return 鎸囧畾澶у皬鐨勮棰戠缉鐣ュ浘
	 */
	private Bitmap getVideoThumbnail(String videoPath, int width, int height,
			int kind) {
		Bitmap bitmap = null;
		// 鑾峰彇瑙嗛鐨勭缉鐣ュ浘
		bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
		System.out.println("w" + bitmap.getWidth());
		System.out.println("h" + bitmap.getHeight());
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	/**
	 * 鏍规嵁鎸囧畾鐨勫浘鍍忚矾寰勫拰澶у皬鏉ヨ幏鍙栫缉鐣ュ浘 姝ゆ柟娉曟湁涓ょ偣濂藉锛� 1.
	 * 浣跨敤杈冨皬鐨勫唴瀛樼┖闂达紝绗竴娆¤幏鍙栫殑bitmap瀹為檯涓婁负null锛屽彧鏄负浜嗚鍙栧搴﹀拰楂樺害锛�
	 * 绗簩娆¤鍙栫殑bitmap鏄牴鎹瘮渚嬪帇缂╄繃鐨勫浘鍍忥紝绗笁娆¤鍙栫殑bitmap鏄墍瑕佺殑缂╃暐鍥俱�� 2.
	 * 缂╃暐鍥惧浜庡師鍥惧儚鏉ヨ娌℃湁鎷変几锛岃繖閲屼娇鐢ㄤ簡2.2鐗堟湰鐨勬柊宸ュ叿ThumbnailUtils锛屼娇 鐢ㄨ繖涓伐鍏风敓鎴愮殑鍥惧儚涓嶄細琚媺浼搞��
	 * 
	 * @param imagePath
	 *            鍥惧儚鐨勮矾寰�
	 * @param width
	 *            鎸囧畾杈撳嚭鍥惧儚鐨勫搴�
	 * @param height
	 *            鎸囧畾杈撳嚭鍥惧儚鐨勯珮搴�
	 * @return 鐢熸垚鐨勭缉鐣ュ浘
	 */
	private Bitmap getImageThumbnail(String imagePath, int width, int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 鑾峰彇杩欎釜鍥剧墖鐨勫鍜岄珮锛屾敞鎰忔澶勭殑bitmap涓簄ull
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		options.inJustDecodeBounds = false; // 璁句负 false
		// 璁＄畻缂╂斁姣�
		int h = options.outHeight;
		int w = options.outWidth;
		int beWidth = w / width;
		int beHeight = h / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		// 閲嶆柊璇诲叆鍥剧墖锛岃鍙栫缉鏀惧悗鐨刡itmap锛屾敞鎰忚繖娆¤鎶妎ptions.inJustDecodeBounds 璁句负 false
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		// 鍒╃敤ThumbnailUtils鏉ュ垱寤虹缉鐣ュ浘锛岃繖閲岃鎸囧畾瑕佺缉鏀惧摢涓狟itmap瀵硅薄
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	public Intent openFile(String filePath) {

		File file = new File(filePath);
		if (!file.exists())
			return null;
		String end = file
				.getName()
				.substring(file.getName().lastIndexOf(".") + 1,
						file.getName().length()).toLowerCase();
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			return getAudioFileIntent(filePath);
		} else if (end.equals("3gp") || end.equals("mp4")) {
			return getAudioFileIntent(filePath);
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			return getImageFileIntent(filePath);
		} else if (end.equals("apk")) {
			return getApkFileIntent(filePath);
		} else if (end.equals("ppt")) {
			return getPptFileIntent(filePath);
		} else if (end.equals("xls")) {
			return getExcelFileIntent(filePath);
		} else if (end.equals("doc")) {
			return getWordFileIntent(filePath);
		} else if (end.equals("pdf")) {
			return getPdfFileIntent(filePath);
		} else if (end.equals("chm")) {
			return getChmFileIntent(filePath);
		} else if (end.equals("txt")) {
			return getTextFileIntent(filePath, false);
		} else {
			return getAllIntent(filePath);
		}
	}

	public static Intent getAllIntent(String param) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "*/*");
		return intent;
	}

	public static Intent getApkFileIntent(String param) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		return intent;
	}

	public static Intent getVideoFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "video/*");
		return intent;
	}

	public static Intent getAudioFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "audio/*");
		return intent;
	}

	public static Intent getHtmlFileIntent(String param) {

		Uri uri = Uri.parse(param).buildUpon()
				.encodedAuthority("com.android.htmlfileprovider")
				.scheme("content").encodedPath(param).build();
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setDataAndType(uri, "text/html");
		return intent;
	}

	public static Intent getImageFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "image/*");
		return intent;
	}

	public static Intent getPptFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
		return intent;
	}

	public static Intent getExcelFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-excel");
		return intent;
	}

	public static Intent getWordFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	public static Intent getChmFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/x-chm");
		return intent;
	}

	public static Intent getTextFileIntent(String param, boolean paramBoolean) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (paramBoolean) {
			Uri uri1 = Uri.parse(param);
			intent.setDataAndType(uri1, "text/plain");
		} else {
			Uri uri2 = Uri.fromFile(new File(param));
			intent.setDataAndType(uri2, "text/plain");
		}
		return intent;
	}

	public static Intent getPdfFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/pdf");
		return intent;
	}

}
