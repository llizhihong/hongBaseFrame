package com.hong.hongbaseframe.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
/*
*  位移动画工具类
* */
public class ViewTranslateAnimation {
    private int offset = 0;  //每次移动的距离
    private int currIndex = 0; //上次的索引
    private int lineHeight = 4; //线高度
    private View view;
    private static ViewTranslateAnimation mInstance;

    public static ViewTranslateAnimation getInstance() {
        if (mInstance == null)
            mInstance = new ViewTranslateAnimation();
        return mInstance;
    }

    /**
     * @描述:activity 上下文对象
     * view 位移动画对象
     * maxNum 分成几份来位移
     */
    public void initMoveLine(Activity activity, View view, int maxNum) {
        if(maxNum != 0) {
            this.view = view;
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenW = dm.widthPixels;
            offset = screenW / maxNum;
            LayoutParams lp = new LayoutParams(offset, lineHeight);
            view.setLayoutParams(lp);
            setMoveAnimation(0);
        }
    }

    /**
     * @描述:index 位移的坐标
     */
    public void setMoveAnimation(int index) {
        if (view != null) {
            Animation animation = new TranslateAnimation(offset * currIndex, offset * index, 0, 0);
            currIndex = index;
            animation.setFillAfter(true);
            animation.setDuration(200);
            LayoutParams lp = new LayoutParams(offset, lineHeight);
            view.setLayoutParams(lp);
            view.startAnimation(animation);
        }
    }

}
