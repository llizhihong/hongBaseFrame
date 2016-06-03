package com.hong.hongbaseframe.callback;

/**
 * 作者：李智宏 on 2016/4/25 16:06
 * 描述：网络返回接口回调
 */
public interface OnResponseListener {
    /**
     * @param tag        接口标志(目前是url，判断URL去做相应的操作)
     * @param code       状态码
     * @param msg        状态信息
     * @param resultData 返回数据
     */
    void onSuccuss(String tag, String code, String msg, String resultData);

    /**
     * @param tag  接口标志(目前是url，判断URL去做相应的操作)
     * @param code 状态码
     * @param msg  状态信息
     */
    void onFailure(String tag, String code, String msg);

    /**
     * @param progress 请求进度
     */
    void onProgress(float progress);
}
