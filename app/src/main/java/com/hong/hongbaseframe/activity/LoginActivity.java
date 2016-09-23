package com.hong.hongbaseframe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hong.hongbaseframe.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：李智宏 on 2016/5/30 16:41
 * 描述：
 */public class LoginActivity extends GestureActivity{
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        btnLogin.setText("hello");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
