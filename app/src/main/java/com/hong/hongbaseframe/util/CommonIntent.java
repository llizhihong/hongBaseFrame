package com.hong.hongbaseframe.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

/**
 * 作者：李智宏 on 2016/5/31 19:11
 * 描述：常用跳转到的Intent
 */
public class CommonIntent {
    /**
     * 拨打电话界面
     * */
    public static void call(Context context, String phone) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    /**
     * 设置界面
     * */
    public static void setNetwork(Context context) {
        Intent intent = null;
        if(Build.VERSION.SDK_INT > 10) {
            intent = new Intent("android.settings.WIFI_SETTINGS");
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }

        context.startActivity(intent);
    }
}
