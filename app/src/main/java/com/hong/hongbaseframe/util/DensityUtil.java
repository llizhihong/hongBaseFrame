package com.hong.hongbaseframe.util;

import android.content.Context;
import android.view.WindowManager;

public class DensityUtil {
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (dpValue * scale + 0.5f);  
	}  

	/** 
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
	 */  
	public static int px2dip(Context context, float pxValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (pxValue / scale + 0.5f);  
	}  
	
	/**
	 * 获取手机的宽高
	 * 0  宽
	 * 1 高
	 * */
	public static int [] ScreenWh(Context ct){
		int screen [] = new int [2];
		WindowManager wm = (WindowManager) ct
				.getSystemService(Context.WINDOW_SERVICE);

		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		
		screen[0] = width;
		screen[1] = height;
		return screen;
	}
	
}
