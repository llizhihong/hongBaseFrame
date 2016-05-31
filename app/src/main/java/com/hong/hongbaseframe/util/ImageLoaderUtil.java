package com.hong.hongbaseframe.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hong.hongbaseframe.R;

/**
 * 作者：李智宏 on 2016/5/9 16:52
 * 描述：图片加载工具类
 */
public class ImageLoaderUtil {
    /**
     * 加载图片
     * @param context 上下文
     * @param url 请求地址
     * @param image 图片赋予对象
     * */
    public static void loadImage(Context context, String url, ImageView image){
        /**
         * diskCacheStrategy(DiskCacheStrategy.ALL)为缓存全部图片大小
         * placeholder 设置占位图
         * error       设置加载错误图
         * crossFade   设置图片淡入淡出动画
         */

        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .fitCenter().crossFade().into(image);
    }

    /**
     * 加载图片
     * @param context 上下文
     * @param url 请求地址
     * @param image 图片赋予对象
     * */
    public static void loadImage(FragmentActivity context, String url, ImageView image){
        /**
         * diskCacheStrategy(DiskCacheStrategy.ALL)为缓存全部图片大小
         * placeholder 设置占位图
         * error       设置加载错误图
         * crossFade   设置图片淡入淡出动画
         */

        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .fitCenter().crossFade().into(image);
    }

    /**
     * 加载图片
     * @param activity Activity
     * @param url 请求地址
     * @param image 图片赋予对象
     * */
    public static void loadImage(Activity activity, String url, ImageView image){
        /**
         * diskCacheStrategy(DiskCacheStrategy.ALL)为缓存全部图片大小
         * placeholder 设置占位图
         * error       设置加载错误图
         * crossFade   设置图片淡入淡出动画
         */

        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .fitCenter().crossFade().into(image);
    }

    /**
     * 加载图片
     * @param fragment fragment
     * @param url 请求地址
     * @param image 图片赋予对象
     * */
    public static void loadImage(Fragment fragment, String url, ImageView image){
        /**
         * diskCacheStrategy(DiskCacheStrategy.ALL)为缓存全部图片大小
         * placeholder 设置占位图
         * error       设置加载错误图
         * crossFade   设置图片淡入淡出动画
         */

        Glide.with(fragment).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .fitCenter().crossFade().into(image);
    }


    /**
     * 清除缓存
     * */
    public static void clearCache(Context context){
        Glide.get(context).clearDiskCache();
        Glide.get(context).clearMemory();
    }

}
