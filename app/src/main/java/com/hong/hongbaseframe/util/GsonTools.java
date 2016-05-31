package com.hong.hongbaseframe.util;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * 作者：李智宏 on 2016/5/31 15:07
 * 描述：json数据转换类
 */
public class GsonTools {
    public GsonTools() {
    }

    /**
     * 创建一个json字符串
     * @param object
     * @return
     */
    public static String createGsonString(Object object) {
        Gson gson = newGson();
        String gsonString = gson.toJson(object);
        return gsonString;
    }


    /**list<T>转json字符串
     * @param lists
     * @return
     */
    public static <T> String list2GsonString(List<T> lists) {
        Gson gson = newGson();
        String gsonString = gson.toJson(lists);
        return gsonString;
    }

    /**
     * json字符串转T
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gson2Bean(String gsonString, Class<T> cls) {
        Gson gson = newGson();
        T t = null;
        try {
            t = gson.fromJson(gsonString, cls);
        }  catch (JsonSyntaxException e) {
            Log.e("gson解析", "解析失败");
        }
        return t;
    }

    /**
     * 初始化Gson对象
     * */
    private static Gson newGson(){
        Gson gson = null;
        final int sdk = Build.VERSION.SDK_INT;
        if (sdk >= 23) {
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.FINAL,
                            Modifier.TRANSIENT, Modifier.STATIC);
            gson = gsonBuilder.create();
        } else {
            gson = new Gson();
        }
        return gson;
    }

    /**json字符串转list<T>
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> gson2List(String gsonString, Class<T> cls) {
        Gson gson = newGson();
        List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    /**
     * json字符串转list<Map<String,T>>
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> gson2ListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = newGson();
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    /**json字符串转Map<String,T>
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> gson2Maps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = newGson();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }

}
