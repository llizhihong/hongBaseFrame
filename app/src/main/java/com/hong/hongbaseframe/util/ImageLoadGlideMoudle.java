package com.hong.hongbaseframe.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.hong.hongbaseframe.R;

/**
 * 作者：李智宏 on 2016/5/27 14:48
 * 描述：Glide全局设置ImageView的Tag
 */
public class ImageLoadGlideMoudle  implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.image_tag);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}