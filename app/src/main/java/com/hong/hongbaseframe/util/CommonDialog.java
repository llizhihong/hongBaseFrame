package com.hong.hongbaseframe.util;

import android.content.Context;

import com.hong.hongbaseframe.R;
import com.hong.hongbaseframe.view.dialog.CustomProgressDialog;

/**
 * 作者：李智宏 on 2016/5/30 19:14
 * 描述：常用的dialog
 */
public class CommonDialog {
    public static CustomProgressDialog progressDialog;
    private static boolean mShowingDialog = false;
    /**
     * 描述:启动加载数据中Dialog
     */
    public static void showProgressDialog(Context context) {
        try {
            if (!mShowingDialog) {
                progressDialog = CustomProgressDialog.createDialog(context);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setTitle(context.getString(R.string.loadTitle));
                progressDialog.setMessage(context.getString(R.string.LoadContent));
                if (!progressDialog.isShowing())
                    progressDialog.show();
                mShowingDialog = true;
            }
        } catch (Exception e) {

        }
    }

    /**
     * 关闭加载数据中dialog
     */
    public static void closeProgressDialog() {
        try {
            if (progressDialog != null || progressDialog.isShowing()) {
                progressDialog.dismiss();
                mShowingDialog = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
