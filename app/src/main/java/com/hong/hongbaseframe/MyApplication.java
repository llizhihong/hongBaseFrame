package com.hong.hongbaseframe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * 作者：李智宏 on 2016/4/22 14:10
 * 描述：重写Application
 */
public class MyApplication extends Application{
    private Context applicationContext;
    private static MyApplication mInstace;
    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        mInstace = this;
        applicationContext = this;
//        CrashHandler.getInstance().init(applicationContext);
    }

    public static MyApplication getInstance(){
        return mInstace;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
