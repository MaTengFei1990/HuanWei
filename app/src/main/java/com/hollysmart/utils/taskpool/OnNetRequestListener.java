package com.hollysmart.utils.taskpool;

/**
 * Created by cai on 16/6/6.
 */
public interface OnNetRequestListener {
    void onSuccess();
    void OnNext(int taskTag, Object data, int total);
    void onFailed(String msg);
}
