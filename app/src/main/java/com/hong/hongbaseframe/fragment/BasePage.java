package com.hong.hongbaseframe.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * 作者：李智宏 on 2016/5/27 09:43
 * 描述：FragMent基准
 */
public class BasePage extends Fragment{
    protected Context context;
    protected View view;

    public BasePage(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    /*
             *
             * 启动新的Activity
             */
    @SuppressWarnings("rawtypes")
    public void startActivity( Class target) {
        Intent intent = new Intent(context, target);
        context.startActivity(intent);
    }

    /*
     * 启动新的Activity并传递一定的参数
     */
    public void startActivity(Class<?> target, Bundle bundle) {
        Intent intent = new Intent(context, target);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
