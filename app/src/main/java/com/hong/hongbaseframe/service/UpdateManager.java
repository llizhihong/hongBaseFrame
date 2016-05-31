package com.hong.hongbaseframe.service;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.hong.hongbaseframe.util.CommonUtil;
import com.hong.hongbaseframe.view.dialog.AlertDialog;

/**
 * 
 * @作者: lzh
 * 
 * @创建时间: 2016年1月14日 下午2:39:10
 * 
 * @描述: 版本更新管理类
 * 
 * @修改时间: 
 * 
 */

public class UpdateManager{
	private final String TAG = "UpdateManager";
	private Context context;
	private String title;
	private String content;
	public static int UPDATE_NO = 0;// 不更新
	public static int UPDATE_YES = 0;// 更新
	private String version;
	private UpdateListener updateListener;
	private static UpdateManager mInstance;
	public void setUpdateListener(UpdateListener updateListener){
		this.updateListener  = updateListener;
	}

	public interface UpdateListener {
		public void update(boolean isUpdate);
	}

	public UpdateManager(Context context) {
		this.context = context;
	}

	public static UpdateManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new UpdateManager(context);
		}
		return mInstance;
	}

	/**
	 * @Title: checkUpdateByVersonName
	 * @Description: 检查更新,根据versionName来判断
	 * @param @param newVersionName
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkUpdateByVersonName(String newVersionName) {
		boolean checkUpdate = false;
		if (!newVersionName.equals(getCurrentVersionName())) {// 有新版本
			checkUpdate = true;
		} else {
			checkUpdate = false;
		}
		return checkUpdate;
	}

	/**
	 * 版本更新提示框
	 * content 提示内容
	 * downloadUrl 下载地址
	 * */
	private void showUpdateDialog(String content,final String downloadUrl) {
		title = "更新提醒";
		try {
			AlertDialog builder = new AlertDialog(context).builder();
			builder.setTitle(title);
			builder.setMsg(content);
			builder.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent updateIntent = new Intent(context,
							UpdateService.class);
					updateIntent.putExtra("downloadUrl", downloadUrl);
					Log.d(TAG, downloadUrl);
					context.startService(updateIntent);
					CommonUtil.StartToast(context, "正在后台下载，详见状态栏");
					if(updateListener!=null)
						updateListener.update(true);
				}
			}).setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(updateListener!=null)
						updateListener.update(false);
				}
			}).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @描述:启动更新(本地判断版本号)
	 * update_version 网络版本
	 * downloadUrl  下载地址
	 */
	public void startAppUpdate(String  update_version, String downloadUrl){
		Log.d(TAG, "本地版本="+getCurrentVersionCode(context)+"");
		Log.d(TAG, "网络版本="+update_version);
		if (update_version.equals((getCurrentVersionCode(context)+""))) {// 不需要升级
			if (updateListener != null)
				updateListener.update(false);

			CommonUtil.StartToast(context, "您是最新版本，不需要更新");
			Log.d("版本更新", "您是最新版本，不需要更新");
		} else {
			content = "亲,有新的版本啦，快来下载吧";
			showUpdateDialog(content, downloadUrl);
		}
	}

	/**
	 * @描述:启动更新(本地无需判断版本号)
	 * downloadUrl  下载地址
	 */
	public void startAppUpdate(String downloadUrl){
		content = "亲,有新的版本啦，快来下载吧";
		showUpdateDialog(content, downloadUrl);
	}


	/**
	 * @描述:直接下载（0
	 * downloadUrl  下载地址
	 */
	public void startDownLoad(String downloadUrl){
		Intent updateIntent = new Intent(context,
				UpdateService.class);
		updateIntent.putExtra("downloadUrl", downloadUrl);
		Log.d(TAG, downloadUrl);
		context.startService(updateIntent);
		CommonUtil.StartToast(context, "正在后台下载，详见状态栏");
		if(updateListener!=null)
			updateListener.update(true);
	}


	OnKeyListener keylistener = new OnKeyListener() {
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				return true;
			} else {
				return false;
			}
		}
	};

	/**
	 * @Title: getCurrentVersionCode
	 * @Description: 获取当前的versionCode
	 * @param @return
	 * @return int 返回类型
	 * @throws
	 */
	public static int getCurrentVersionCode(Context context) {
		int curVersionCode = 0;
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			// curVersionName = info.versionName;
			curVersionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		return curVersionCode;
	}

	/**
	 * @Title: getCurrentVersionName
	 * @Description: 获取当前的versionName
	 * @param @return
	 * @return String 返回类型
	 * @throws
	 */
	private String getCurrentVersionName() {
		String curVersionName = null;
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			curVersionName = info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		return curVersionName;
	}

	/**
	 * @Title: getCurrentVersionName
	 * @Description: 获取当前的versionName
	 * @param @return
	 * @return String 返回类型
	 * @throws
	 */
	public static String getCurrentVersionName(Context context) {
		String curVersionName = null;
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			curVersionName = info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		return curVersionName;
	}

	public void updateAppVersion(){
		version = getCurrentVersionName(context);
	}

	/**
	 * 比较版本号
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compare(String s1, String s2) {
		if (s1 == null && s2 == null)
			return 0;
		else if (s1 == null)
			return -1;
		else if (s2 == null)
			return 1;

		String[] arr1 = s1.split("[^a-zA-Z0-9]+"), arr2 = s2.split("[^a-zA-Z0-9]+");

		int i1, i2, i3;

		for (int ii = 0, max = Math.min(arr1.length, arr2.length); ii <= max; ii++) {
			if (ii == arr1.length)
				return ii == arr2.length ? 0 : -1;
			else if (ii == arr2.length)
				return 1;

			try {
				i1 = Integer.parseInt(arr1[ii]);
			} catch (Exception x) {
				i1 = Integer.MAX_VALUE;
			}

			try {
				i2 = Integer.parseInt(arr2[ii]);
			} catch (Exception x) {
				i2 = Integer.MAX_VALUE;
			}

			if (i1 != i2) {
				return i1 - i2;
			}

			i3 = arr1[ii].compareTo(arr2[ii]);

			if (i3 != 0)
				return i3;
		}

		return 0;
	}

}
