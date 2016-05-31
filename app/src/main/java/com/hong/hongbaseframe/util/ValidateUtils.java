package com.hong.hongbaseframe.util;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：李智宏 on 2016/5/30 17:06
 * 描述：常用数据格式校验
 */
public class ValidateUtils {

    private final static String TAG = "ValidateUtils";

    /**
     * 检查是否是邮箱
     *
     * @param paramString
     */
    public static boolean isValidEmail(String paramString) {

        String regex = "[a-zA-Z0-9_\\.]{1,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        if (paramString.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手机号验证
     *
     * @param str 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * @param str
     * @Description: 校验身份证 true为验证通过
     */
    public static boolean isIdCard(String str) {
        CheckIdCard checIdCard = new CheckIdCard(str);
        Log.d(TAG, "result=" + checIdCard.validate());
        return checIdCard.validate();
    }

    /**
     * 密码验证
     *
     * @param str
     */
    public static boolean isPassword(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        try {
            p = Pattern.compile("^//d{6,16}$"); //
            m = p.matcher(str);
            b = m.matches();
        } catch (Exception e) {
        }
        return b;
    }

    /**
     * @param str 真实姓名校验
     */
    public static boolean realNameValidate(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        try {
            p = Pattern.compile("^[\u4E00-\u9FA5A-Za-z0-9]+$"); //
            m = p.matcher(str);
            b = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * @param content
     * @Description: 非空判断
     */
    public static boolean isEmpty(String content) {
        if (null != content && !"".equals(content)) {
            return false;
        }
        return true;
    }

}
