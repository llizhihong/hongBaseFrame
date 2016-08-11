package com.hong.hongbaseframe.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.hong.hongbaseframe.Constant;
import com.hong.hongbaseframe.R;
import com.hong.hongbaseframe.util.CommonUtil;
import com.hong.hongbaseframe.util.SharePrefUtil;

/**
 * 作者：李智宏 on 2016/7/26 10:01
 * 描述：
 */
public class SplashActivity extends BaseActivity{

    @Override
    protected int setContentView() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        init();
    }

    @Override
    protected void init() {
        boolean b = CommonUtil.hasNetwork(this);
        if(SharePrefUtil.getBoolean(context, Constant.SHARE_ISFIRST, false)){
            if (b) {
                if(SharePrefUtil.getString(context, Constant.SHARE_USERID, "").equals("")){
                    startActivity(LoginActivity.class);
                    finish();
                } else{
                    sendLogin();
                }
                // 校验版本信息的
            } else {
                startActivity(LoginActivity.class);
                finish();
            }
        } else{
            SharePrefUtil.saveBoolean(context, Constant.SHARE_ISFIRST, true);
            startActivity(GuideActivity.class);
            finish();
        }
    }

    //自动登录
    private void sendLogin(){
        startActivity(LoginActivity.class);
        finish();
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
