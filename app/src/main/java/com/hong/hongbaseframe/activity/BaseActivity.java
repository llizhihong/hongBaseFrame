package com.hong.hongbaseframe.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hong.hongbaseframe.callback.OnResponseListener;

/**
 * 作者：李智宏 on 2016/5/27 17:21
 * 描述：
 */
public abstract class BaseActivity extends AppCompatActivity implements OnResponseListener{
    protected Context context;

    /**
     * 子类用来初始化操作
     */
    protected abstract void init();

    /**
     * 子类用来初始化监听
     */
    protected abstract void setListner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }


}
