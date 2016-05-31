package com.hong.hongbaseframe.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 作者：李智宏 on 2016/5/27 15:50
 * 描述：
 */
public class AppUtils {
    //微信包名
    public static final String WECHAT_PACKAGE_NAME = "com.tencent.mm";

    /**
     * 判断手机上是否安装了某个APP
     * @param paramContext
     * @param paramString 应用包名
     * */
    public static boolean isAppInstalled(Context paramContext, String paramString) {
        try {
            paramContext.getPackageManager().getPackageInfo(paramString, 0);
            return true;
        } catch (Exception localException) {

        }
        return false;
    }

    /**
     * 启动某个APP
     * @param paramContext
     * @param paramString 应用包名
     * */
    public static void launchApp(Context paramContext, String paramString) {
        if (isAppInstalled(paramContext, paramString)) {
            paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
            return;
        }
        goToMarket(paramContext, paramString);
    }

    /**
     * 打开应用市场对应的APP下载页面,如没有应用市场则不启动
     * @param paramContext
     * @param paramString 应用包名
     * */
    public static void goToMarket(Context paramContext, String paramString) {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString));
        try {
            //如该APP在用户所点击的市场软件上线的话，则会跳转到下载详情页，如无上线的话则调到市场软件的提示页面
            paramContext.startActivity(localIntent);
            return;
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            //出现此异常则说明此手机上没有装应用市场软件
        }
    }

    /**
     * 获取当前APP版本号
     * */
    public static int getVersionCode(Context context) {
        try {
            PackageManager e = context.getPackageManager();
            PackageInfo packageInfo = e.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取设备ID
     * */
    public static String getDeviceId(Context context) {
        try {
            TelephonyManager e = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = e.getDeviceId();
            if(TextUtils.isEmpty(device_id)) {
                device_id = Settings.Secure.getString(context.getContentResolver(), "android_id");
            }

            return device_id;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * 获取手机Mac物理地址
     * */
    public static String getMacAddress(Context context) {
        String macAddress = "";

        try {
            WifiManager e = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            macAddress = e.getConnectionInfo().getMacAddress();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return macAddress;
    }
}
