package com.hong.hongbaseframe.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;


/**
 * 作者：李智宏 on 2016/5/30 19:14
 * 描述：通用类 常用的一些小工具，常用的dialog等一些常用的工具类
 */

public class CommonUtil {
    private static String NOT_LOGIN = "notlogin";

    /**
     * 系统Toast
     */
    public static void StartToast(Context context, String string) {
        if (context != null) {
            Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT); //200
            View view = toast.getView();
            toast.setView(view);
            toast.show();
        }
    }

    /**
     * 判定网络状态
     *
     * @param context 上下文对象
     */
    public static boolean hasNetwork(Context context) {
        // 检查网络状态
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        if (workinfo == null || !workinfo.isAvailable()) {
            Toast.makeText(context, "网络异常，请检查网络！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
