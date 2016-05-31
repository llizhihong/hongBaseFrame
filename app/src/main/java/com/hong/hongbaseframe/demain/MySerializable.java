package com.hong.hongbaseframe.demain;

import java.io.Serializable;

/**
 * 作者：李智宏 on 2016/5/30 20:20
 * 描述：接口数据基层
 */
public class MySerializable implements Serializable{
    private static final long serialVersionUID = -4819321280028042280L;
    public String code;
    public String time;
    public String message;

    @Override
    public String toString() {
        return "MySerializable{" +
                "code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", msg='" + message + '\'' +
                '}';
    }
}
