package com.hong.hongbaseframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hong.hongbaseframe.util.Logger;
import com.hong.hongbaseframe.util.NetUtil;

/**
 * 作者：李智宏 on 2016/5/27 09:50
 * 描述：
 */
public class Fragment2 extends BasePage{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void init(){
        NetUtil.newInstance().requestGet(context, "http://www.baidu.com/", this);
    }

    @Override
    public void onSuccuss(String tag, String code, String msg, String resultData) {

    }

    @Override
    public void onFailure(String tag, String code, String msg) {
        if("http://www.baidu.com/".equals(tag)){
            Logger.e("fragMent2 url测试", tag);
        } else if("http://home.baidu.com/".equals(tag)){
            Logger.e("fragMent2 url测试", tag);
        }
    }

    @Override
    public void onProgress(float progress) {

    }

    @Override
    public void onClick(View v) {

    }
}
