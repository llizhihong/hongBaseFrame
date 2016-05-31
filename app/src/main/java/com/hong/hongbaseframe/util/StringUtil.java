package com.hong.hongbaseframe.util;

import android.content.ClipData;
import android.content.Context;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：李智宏 on 2016/5/4 09:54
 * 描述：对字符串的一些操作
 */
public class StringUtil {

    /**
     * 判定两个字符串的值是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEqual(String str1, String str2) {
        boolean b = false;
        if (!str1.equals("") && !str1.equals("")) {
            str1.equals(str2);
            b = true;
        }
        return b;
    }

    /**
     * 调用复制功能
     *
     * @param context 上下文
     * @param text    复制内容
     */
    public static void ClipText(Context context, String text) {
        //调用复制功能
        if (android.os.Build.VERSION.SDK_INT > 11) {
            android.content.ClipboardManager c = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            c.setPrimaryClip(ClipData.newPlainText("sinaweibo", "1211"));

        } else {
            android.text.ClipboardManager c = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            c.setText("1 21");
        }
    }

    /**
     * 使用MD5对原文进行加密
     *
     * @param value 需要被加密的原文
     * @return MD5加密后数据
     */
    public static String md5EncrypyStr(String value) {
        StringBuilder sb = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(value.getBytes());
            sb = new StringBuilder();
            for (byte b : result) {
                String hexString = Integer.toHexString(b & 0xFF);
                if (hexString.length() == 1) {
                    sb.append("0" + hexString);// 0~F
                } else {
                    sb.append(hexString);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}
