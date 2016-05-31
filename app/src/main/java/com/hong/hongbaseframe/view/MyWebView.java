package com.hong.hongbaseframe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends WebView{

	public MyWebView(Context context) {
		super(context);
	}
	
	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//设置支持JS
		getSettings().setJavaScriptEnabled(true);
		setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		//取消Horizontal ScrollBar显示
		setHorizontalScrollBarEnabled(false);
		//设置此属性，可任意比例缩放
		getSettings().setUseWideViewPort(true);
		//缩放至屏幕的大小
		getSettings().setLoadWithOverviewMode(true);
		//支持缩放
		getSettings().setBuiltInZoomControls(false);
		getSettings().setSupportZoom(true);
		setInitialScale(70);
		//所有网页都在WebView中打开
		setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

	}

}
