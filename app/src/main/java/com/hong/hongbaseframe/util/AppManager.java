package com.hong.hongbaseframe.util;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.hong.hongbaseframe.MainActivity;
import com.hong.hongbaseframe.activity.login.LoginActivity;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Activity管理类
 *
 * @author Robin
 */
public class AppManager {

    private CopyOnWriteArrayList<Activity> mActivityStack;
    private static AppManager mAppManager;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (mAppManager == null) {
            mAppManager = new AppManager();
        }
        return mAppManager;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new CopyOnWriteArrayList<Activity>();
        }
        mActivityStack.add(activity);
    }


    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        try {
            if (mActivityStack != null) {
                if (activity != null) {
                    mActivityStack.remove(activity);
                    activity.finish();
                    activity = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 获取当前activity数
     */
    public int getCurrentActivitySize() {
        return mActivityStack.size();
    }

    /**
     * 结束所有Activity除了Login
     */
    public void killAllActivityNoLogin() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                if (mActivityStack.get(i).getClass() != LoginActivity.class) {
                    mActivityStack.get(i).finish();
                }
            }
        }
        mActivityStack.clear();
    }

    /**
     * 结束所有Activity除了Main
     */
    public void killAllActivityNoMain() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                if (mActivityStack.get(i).getClass() != MainActivity.class) {
                    mActivityStack.get(i).finish();
                }
            }
        }
        mActivityStack.clear();
    }

    /**
     * 结束所有Activity除了Main和指定的Activity
     */
    public void killAllActivityNoOther(Class<?> cls) {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                if (mActivityStack.get(i).getClass() != MainActivity.class && mActivityStack.get(i).getClass() != cls) {
                    mActivityStack.get(i).finish();
                }
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            // 杀死进程
            android.os.Process.killProcess(android.os.Process.myPid());
            // 抛异常强制退出
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
