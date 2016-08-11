package com.hong.hongbaseframe.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;

import com.hong.hongbaseframe.callback.OnResponseListener;
import com.hong.hongbaseframe.demain.MySerializable;
import com.hong.hongbaseframe.okhttp.OkHttpRequest;
import com.hong.hongbaseframe.okhttp.ResultCallback;
import com.squareup.okhttp.Request;

import java.io.File;
import java.util.Map;

/**
 * 作者：李智宏 on 2016/4/25 15:55
 * 描述：网络请求封装类
 */
public class NetUtil {
    private static NetUtil netUtil;
    //网络回调
    private OnResponseListener listener;
    //上下文
    private Context context;
    //请求地址
    private String url;

    public static NetUtil getInstance() {
        if (netUtil == null) {
            synchronized (NetUtil.class) {
                if (netUtil == null)
                    netUtil = new NetUtil();

            }
        }
        return netUtil;
    }

    /**
     * Get请求
     *
     * @param context  上下文对象
     * @param url      请求地址
     * @param listener 请求监听
     */
    public void requestGet(Context context, String url, OnResponseListener listener) {
        this.context = context;
        this.url = url;
        this.listener = listener;
        isNext();
        new OkHttpRequest.Builder()
                .tag(listener)
                .url(url)
                .get(resultCallback);
    }


    /**
     * Post请求
     *
     * @param context  上下文对象
     * @param url      请求地址
     * @param params   请求参数
     * @param listener 请求监听
     */
    public void requestPost(Context context, String url, Map<String, String> params, OnResponseListener listener) {
        this.context = context;
        this.url = url;
        this.listener = listener;
        isNext();
        new OkHttpRequest.Builder()
                .tag(listener)
                .url(url)
                .params(params)
                .post(resultCallback);
    }

    /**
     * 上传文件
     *
     * @param context  上下文对象
     * @param url      请求地址
     * @param params   上传参数
     * @param filePath 上传的文件地址
     * @param listener 请求监听
     */
    public void uploadFile(Context context, String url, Map<String, String> params, String filePath,
                           OnResponseListener listener) {
        this.context = context;
        this.url = url;
        this.listener = listener;
        isNext();
        File file = new File(filePath);
        if (file.exists()) {
            new OkHttpRequest.Builder()
                    .url(url)
                    .tag(listener)
                    .params(params)
                    .files(new Pair<String, File>("mFile", file))//
                    .upload(resultCallback);
        } else {
            throw new IllegalArgumentException("文件不存在，请修改文件路径");
        }

    }

    /**
     * 上传多个文件
     *
     * @param context  上下文对象
     * @param url      上传地址
     * @param filePath 上传的文件地址
     * @param params   上传参数
     * @param listener 请求监听
     */
    public void multiFileUpload(Context context, String url, Map<String, String> params, OnResponseListener listener,
                                String... filePath) {
        this.context = context;
        this.url = url;
        this.listener = listener;
        isNext();
        String []filePaths = filePath;
        for(String str:filePaths){
            File file = new File(str);
            if (!file.exists()) {
                throw new IllegalArgumentException("文件不存在，请修改文件路径");
            }
        }
        Pair<String, File>[] pairs = new Pair[filePaths.length];
        for(int i=0; i<filePaths.length; i++){
            pairs[i] = new Pair<String, File>("mFile", new File(filePaths[i]));
        }
        new OkHttpRequest.Builder()
                .url(url)
                .tag(listener)
                .params(params)
                .files(pairs)//
                .upload(resultCallback);
    }


    /**
     * 下载文件
     *
     * @param context  上下文对象
     * @param url      上传地址
     * @param fileName 文件名称
     * @param listener 请求监听
     */
    public void downLoadFile(Context context, String url, String fileName, OnResponseListener listener) {
        this.context = context;
        this.url = url;
        this.listener = listener;
        isNext();
        new OkHttpRequest.Builder()
                .url(url)
                .tag(listener)
                .destFileDir(
                        Environment.getExternalStorageDirectory()
                                .getAbsolutePath())
                .destFileName(fileName).download(resultCallback);
    }


    //判断初始条件是否OK，如果可以则请求接口
    private void isNext() {
        //判断url是否为空
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be Empty");
        }

        //判断上下文对象是否为空
        if (context == null) {
            throw new IllegalArgumentException("context can not be Empty");
        }

        //判断回调是否为空
        if (listener == null) {
            throw new IllegalArgumentException("listener can not be Empty");
        }

        //判断是否有网络
        if (!CommonUtil.hasNetwork(context)) {
            Logger.e("NetUtil", "无网络不可请求");
            return;
        }
    }

    /**
     * 封装的Okhttp接口回调
     */
    private ResultCallback<String> resultCallback = new ResultCallback<String>() {
        //请求之前
        @Override
        public void onBefore(Request request) {
//            Logger.e("TAG", "before , request = " + request.toString());
        }

        @Override
        public void onError(Request request, Exception e) {
            Logger.e("TAG", "onError , e = " + e.getMessage() + "????" + request.url().toString());
            ((OnResponseListener) request.tag()).onFailure(request.url().toString(), null, "");
        }

        @Override
        public void onResponse(Request request, String response) {
//            Logger.e("TAG", "response=" + response);
//            Logger.e("TAG", "request.url()=" + request.url().toString());
           /* MySerializable result = GsonTools.gson2Bean(response, MySerializable.class);
            if (result != null & response != null) {
                if (listener != null) {
                    listener.onSuccuss(request.url().toString(), result.code, result.message, response);
                }
            } else {
                if (listener != null) {
                    Logger.e("TAG", "解析失败" + request.url().toString());
                    listener.onFailure(request.url().toString(), null, response);
                }
            }*/
            MySerializable result = GsonTools.gson2Bean(response, MySerializable.class);
            OnResponseListener listener = (OnResponseListener) request.tag();
            if (result != null & response != null) {
                if (listener != null) {
                    listener.onSuccuss(request.url().toString(), result.code, result.message, response);
                }
            } else {
                if (listener != null) {
                    Logger.e("TAG", "解析失败" + request.url().toString());
                    listener.onFailure(request.url().toString(), null, response);
                }
            }
        }

        @Override
        public void inProgress(Request request, float progress) {
            ((OnResponseListener) request.tag()).onProgress(progress);
//            Logger.e("TAG", "inProgress , progress = " + progress);
        }
    };
}
