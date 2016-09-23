package com.hong.hongbaseframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hong.hongbaseframe.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：李智宏 on 2016/5/27 09:50
 * 描述：
 */
public class Fragment4 extends BasePage implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.lv_fragment4)
    ListView lvFragment4;
    List<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Bind(R.id.refresh_fragment4)
    SwipeRefreshLayout refreshFragment4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment4, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init() {
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        list.add("item5");
        list.add("item6");
        list.add("item7");
        if (lvFragment4.getAdapter() == null) {
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list);
            lvFragment4.setAdapter(adapter);
            refreshFragment4.setOnRefreshListener(this);
        } else {
            adapter.notifyDataSetChanged();
            refreshFragment4.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        init();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
