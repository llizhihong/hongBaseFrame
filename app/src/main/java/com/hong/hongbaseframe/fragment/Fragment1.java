package com.hong.hongbaseframe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hong.hongbaseframe.R;
import com.hong.hongbaseframe.util.DensityUtil;
import com.hong.hongbaseframe.util.Logger;
import com.hong.hongbaseframe.util.NetUtil;
import com.hong.hongbaseframe.view.banner.CBViewHolderCreator;
import com.hong.hongbaseframe.view.banner.ConvenientBanner;
import com.hong.hongbaseframe.view.banner.NetworkImageHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：李智宏 on 2016/5/27 09:50
 * 描述：
 */
public class Fragment1 extends BasePage{
    @Bind(R.id.fragment1_banner)
    ConvenientBanner<String> convenientBanner;// 顶部广告栏控件
    List<String> banaerList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, null);
        ButterKnife.bind(this, view);
        initBanner();
        return view;
    }

    //初始化Banaer
    private void initBanner(){
        banaerList = new ArrayList<String>();
        banaerList.add("http://imgsrc.baidu.com/forum/pic/item/09be3f094b36acaf0ad6eb717cd98d1000e99cde.jpg");
        banaerList.add("http://attach2.scimg.cn/forum/201503/17/172006zr3762gfgdr5tmu9.jpg");
        banaerList.add("http://imgsrc.baidu.com/forum/pic/item/4fe0821349540923bc3560f39258d109b2de49b4.jpg");
        banaerList.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1307/10/c6/23169101_1373445265040.jpg");
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();

            }
        },banaerList).setPageIndicator(
                new int[]{R.mipmap.image_undian,
                        R.mipmap.image_selectdian});
        // 开始自动翻页
        convenientBanner.startTurning(3000);
        convenientBanner.setFocusable(true);
        convenientBanner.setFocusableInTouchMode(true);
        convenientBanner.requestFocus();
        convenientBanner.setLayoutParams(new LinearLayout.LayoutParams(DensityUtil.ScreenWh(context)[0],
                (int) (DensityUtil.ScreenWh(context)[0] / 6 * 3.5)));

        Map<String, String> map = new HashMap<String, String>();
        map.put("aa", "");
//        NetUtil.newInstance().requestPost(context, "http://home.baidu.com/", map, this);
        NetUtil.newInstance().requestGet(context, "http://home.baidu.com/", this);
    }

    @Override
    public void onSuccuss(String tag, String code, String msg, String resultData) {

    }

    @Override
    public void onFailure(String tag, String code, String msg) {
        if("http://www.baidu.com".equals(tag)){
            Logger.e("fragMent1 url测试", tag);
        } else if("http://home.baidu.com/".equals(tag)){
            Logger.e("fragMent1 url测试", tag);
        } else if("http://home.baidu.com/contact.html".equals(tag)){
            Logger.e("fragMent1 url测试", tag);
        }
    }

    @Override
    public void onProgress(float progress) {

    }

    @Override
    public void onClick(View v) {

    }
}