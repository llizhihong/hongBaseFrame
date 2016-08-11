package com.hong.hongbaseframe.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hong.hongbaseframe.callback.OnResponseListener;
import com.hong.hongbaseframe.util.AppManager;

import butterknife.ButterKnife;

/**
 * 作者：李智宏 on 2016/5/27 17:21
 * 描述：
 */
public abstract class BaseActivity extends AppCompatActivity implements OnResponseListener, View.OnClickListener{
    protected Context context;

    /**
     * 子类用来初始化操作
     */
    protected abstract void init();

    /**
     * 子类用来初始化监听
     */
    protected abstract void setListner();

    /**
     * 设置ContentView
     * */
    protected abstract int setContentView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(setContentView() != 0) {
            setContentView(setContentView());
            ButterKnife.bind(this);
            init();
            setListner();
        }
        context = this;
    }



    /**
     *
     * 启动新的Activity
     */
    @SuppressWarnings("rawtypes")
    public void startActivity(Class target) {
        Intent intent = new Intent(this, target);
        startActivity(intent);
    }

    /**
     * 启动新的Activity并传递一定的参数
     */
    public void startActivity(Class<?> target, Bundle bundle) {
        Intent intent = new Intent(this, target);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().killActivity(this);
    }
}
