package com.hong.hongbaseframe.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * 作者：李智宏 on 2016/5/31 19:42
 * 描述：常用图片操作
 */
public class BitmapUtils {

    /**
     * 获取当前屏幕截图，不包含状态栏
     * */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = DensityUtil.ScreenWh(activity)[0];
        int height = DensityUtil.ScreenWh(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     * */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = DensityUtil.ScreenWh(activity)[0];
        int height = DensityUtil.ScreenWh(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * Bitmap转Base64字符串
     * */
    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, 0);
    }

    /**
     * 根据图片路径删除图片
     * */
    public static void deleteTempFile(String path) {
        if(!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if(file.exists()) {
                file.delete();
            }
        }

    }
}
