package com.hong.hongbaseframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.hong.hongbaseframe.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：李智宏 on 2016/5/27 09:50
 * 描述：
 */
public class Fragment2 extends BasePage {
    @Bind(R.id.lv_fragment2)
    ListView lvFragment2;
    List<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Bind(R.id.refresh_fragment2)
    MaterialRefreshLayout refreshFragment4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list);
        lvFragment2.setAdapter(adapter);
        refreshFragment4.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                onRefreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                onLoadMore();
            }
        });
        onRefreshData();
    }

    private void onRefreshData() {
        list.clear();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        adapter.notifyDataSetChanged();
        refreshFragment4.finishRefresh();
    }

    private void onLoadMore() {
        list.add("item5");
        list.add("item6");
        list.add("item7");
        list.add("item9");
        adapter.notifyDataSetChanged();
        refreshFragment4.finishRefreshLoadMore();
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
