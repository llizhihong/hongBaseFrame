package com.hong.hongbaseframe.activity;

import android.os.Bundle;

import com.hong.hongbaseframe.R;
import com.hong.hongbaseframe.util.Logger;
import com.hong.hongbaseframe.view.MyWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：李智宏 on 2016/6/13 10:56
 * 描述：H5展示方面
 */
public class MyWebActivity extends BaseActivity {

    @Bind(R.id.myweb_webview)
    MyWebView mywebWebview;
    String title, url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myweb);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        Logger.e("MyWebView", "url="+url);
        mywebWebview.loadUrl(url);
    }

    @Override
    protected void init() {

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
}
