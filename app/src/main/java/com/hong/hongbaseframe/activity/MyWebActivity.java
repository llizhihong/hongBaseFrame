package com.hong.hongbaseframe.activity;

import android.view.View;

import com.hong.hongbaseframe.R;
import com.hong.hongbaseframe.util.Logger;
import com.hong.hongbaseframe.view.MyWebView;

import butterknife.Bind;

/**
 * 作者：李智宏 on 2016/6/13 10:56
 * 描述：H5展示方面
 */
public class MyWebActivity extends BaseActivity {

    @Bind(R.id.myweb_webview)
    MyWebView mywebWebview;
    String title="", url="";

    @Override
    protected int setContentView() {
        return R.layout.activity_myweb;
    }

    @Override
    protected void init() {
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        Logger.e("MyWebView", "url="+url);
        mywebWebview.loadUrl(url);
    }

    @Override
    protected void setListner() {

    }

    @Override
    public void onSuccuss(String tag, String code, String msg, String resultData) {

    }

    @Override
    public void onFailure(String tag, String code, String msg) {

    }

    @Override
    public void onProgress(float progress) {

    }

    @Override
    public void onClick(View v) {

    }
}
