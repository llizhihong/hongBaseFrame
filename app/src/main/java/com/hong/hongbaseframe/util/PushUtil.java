package com.hong.hongbaseframe.util;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.hong.hongbaseframe.activity.MyWebActivity;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * 作者：李智宏 on 2016/6/13 11:07
 * 描述：推送工具类
 */
public class PushUtil {
    private static PushUtil mInstance;
    private String device_token;

    public static PushUtil getInstance() {
        if (mInstance == null) {
            synchronized (PushUtil.class) {
                if (mInstance == null) {
                    mInstance = new PushUtil();
                }
            }
        }
        return mInstance;
    }


    /**
     * 开启推送并返回设备唯一标示，设备唯一标示为推送个别用户所需，如不需要，不用即可
     * 可在Activity或者Application中调用
     *
     * @param context 上下文
     * @return device_token 友盟生成的用于标识设备的唯一id
     */
    public void startPush(Context context) {
//        if (!PushAgent.getInstance(context).isEnabled()) {
        PushAgent mPushAgent = PushAgent.getInstance(context);
        //开启友盟推送日志
        mPushAgent.setDebugMode(true);
        Logger.e("PushUtil", "推送正在开启");
//            mPushAgent.enable();
//            device_token = UmengRegistrar.getRegistrationId(context);
        //开启推送并设置注册的回调处理
        mPushAgent.enable(new IUmengRegisterCallback() {
            @Override
            public void onRegistered(final String registrationId) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        //onRegistered方法的参数registrationId即是device_token
                        Logger.e("PushUtil", "推送开启,device_token=" + registrationId);
                        device_token = registrationId;
                    }
                });
            }
        });
//        } else {
//            Logger.e("PushUtil", "推送已经开启");
//        }
    }

    /**
     * 关闭推送
     */
    public void stopPush(Context context) {
        if (PushAgent.getInstance(context).isEnabled()) {
            Logger.e("pushUtil", "推送关闭");
            PushAgent.getInstance(context).disable();
        }
    }


    /**
     * 设置通知栏点击事件
     * */
    public void setupCustomNotificationClick(Context context) {
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void openUrl(Context context, UMessage uMessage) {
//                super.openUrl(context, uMessage);
                if (uMessage.url != null && !TextUtils.isEmpty(uMessage.url.trim())) {
                    Intent intent = new Intent(context, MyWebActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("url", uMessage.url);
                    context.startActivity(intent);
                }
            }

            @Override
            public void openActivity(Context context, UMessage uMessage) {
                super.openActivity(context, uMessage);
            }

            @Override
            public void launchApp(Context context, UMessage uMessage) {
                super.launchApp(context, uMessage);
            }

            @Override
            public void dealWithCustomAction(Context context, UMessage uMessage) {
                super.dealWithCustomAction(context, uMessage);
            }
        };
        PushAgent.getInstance(context).setNotificationClickHandler(notificationClickHandler);

        UmengMessageHandler umengMessageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                switch (uMessage.builder_id) {
                    case 1:
                        break;
                    default:
                        super.getNotification(context, uMessage);
                        break;
                }

                return super.getNotification(context, uMessage);
            }
        };
    }
}
