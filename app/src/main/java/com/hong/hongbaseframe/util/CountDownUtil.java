package com.hong.hongbaseframe.util;

import android.os.Handler;
import android.os.Message;

import com.hong.hongbaseframe.callback.OnCountDownListener;

/**
 * 作者：李智宏 on 2016/7/29 17:10
 * 描述：倒计时
 */
public class CountDownUtil {
    int recLen;
    OnCountDownListener listener;
    public static CountDownUtil countDownUtil;
    public static CountDownUtil getInstance(){
        if(countDownUtil == null){
            countDownUtil = new CountDownUtil();
        }
        return countDownUtil;
    }

    public void startCountDown(int recLen, OnCountDownListener listener){
        this.recLen = recLen;
        this.listener = listener;
        handler.removeCallbacks(runnable);
        // 开始倒计时
        handler.postDelayed(runnable, 1000);
    }

    /**
     * 以下是3个函数是自定义timer用于倒计时
     */
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    listener.countDownNow(recLen+"");
                    break;
                case 0:
                    reset();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Runnable runnable = new Runnable() {
        public void run() {
            Message message = new Message();
            message.what = 1;
            recLen--;
            if (recLen == 0) {
                message.what = 0;
            }
            handler.sendMessage(message);
            handler.postDelayed(this, 1000); // postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
        }

    };

    public void reset() {
        handler.removeCallbacks(runnable); // 停止Timer
        listener.CountDownStop();
    }

    public void stopCountDown(){
        handler.removeCallbacks(runnable);
    }
}
