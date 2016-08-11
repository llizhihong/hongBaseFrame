package com.hong.hongbaseframe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.hong.hongbaseframe.okhttp.OkHttpClientManager;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okio.Buffer;

/**
 * 作者：李智宏 on 2016/4/22 14:10
 * 描述：重写Application
 */
public class MyApplication extends Application{
    private Context applicationContext;
    private static MyApplication mInstace;
    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        mInstace = this;
        applicationContext = this;
        init();

    }


    public static MyApplication getInstance(){
        return mInstace;
    }

    private void init(){
        //初始化推送
//        PushUtil.getInstance().startPush(applicationContext);
//        PushUtil.getInstance().setupCustomNotificationClick(applicationContext);
//        CrashHandler.getInstance().init(applicationContext);
        //初始化http请求
        OkHttpClientManager.getInstance().getOkHttpClient().setConnectTimeout(10000, TimeUnit.MILLISECONDS);
    }

    private void initHttps(){
        //https证书串
        String CER_12306 = "-----BEGIN CERTIFICATE-----\n" +
                "MIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAn\n" +
                "BgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4X\n" +
                "DTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNp\n" +
                "bm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3\n" +
                "DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb2\n" +
                "9bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6\n" +
                "D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHle\n" +
                "tne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDov\n" +
                "LzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Pt\n" +
                "x1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV\n" +
                "23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQ\n" +
                "og555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n" +
                "-----END CERTIFICATE-----";
        OkHttpClientManager.getInstance().setCertificates(new InputStream[]{
                new Buffer()
                        .writeUtf8(CER_12306)
                        .inputStream()});
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
