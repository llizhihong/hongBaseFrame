package com.hong.hongbaseframe.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.hong.hongbaseframe.callback.OnResponseListener;
import java.util.Map;

/**
 * 作者：李智宏 on 2016/4/25 15:55
 * 描述：网络请求封装类
 */
public class NetUtil {
    private  static NetUtil netUtil;
    //网络回调
    public OnResponseListener listener;
    //上下文
    private Context context;
    //请求地址
    private String url;
    public static NetUtil newInstance(){
        if(netUtil == null){
            netUtil = new NetUtil();
        }
        return netUtil;
    }

    /**
     * Get请求
     * @param context 上下文对象
     * @param url    请求地址
     * @param listener  请求监听
     */
    public void requestGet(Context context, String url, OnResponseListener listener){
        this.context = context;
        this.url = url;
        this.listener = listener;
        isNext();

    }


    /**
     * Post请求
     * @param context 上下文对象
     * @param url     请求地址
     * @param map     请求参数
     * @param listener  请求监听
     * */
    public void requestPost(Context context, String url, Map<String, String> map, OnResponseListener listener){
        this.context = context;
        this.url = url;
        this.listener = listener;
        isNext();

    }

    /**
     * 上传文件
     * @param context 上下文对象
     * @param url    请求地址
     * @param uploadFilePath 上传的文件地址
     * @param listener  请求监听
     * */
    public void uploadFile(Context context, String url, String uploadFilePath, OnResponseListener listener){

    }

    //判断初始条件是否OK，如果可以则请求接口
    private void isNext(){

        //判断url是否为空
        if(TextUtils.isEmpty(url)){
            throw new IllegalArgumentException("url can not be Empty");
        }

        //判断上下文对象是否为空
        if(context == null){
            throw new IllegalArgumentException("context can not be Empty");
        }

        //判断回调是否为空
        if(listener == null){
            throw new IllegalArgumentException("listener can not be Empty");
        }

        //判断是否有网络
        if(!CommonUtil.hasNetwork(context)){
            Log.e("NetUtil", "无网络不可请求");
        }
    }

}
