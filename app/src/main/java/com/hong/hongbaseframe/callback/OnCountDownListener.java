package com.hong.hongbaseframe.callback;

/**
 * 作者：李智宏 on 2016/7/29 17:23
 * 描述：倒计时回调类
 */
public interface OnCountDownListener {
    void countDownNow(String number);
    void CountDownStop();
}
