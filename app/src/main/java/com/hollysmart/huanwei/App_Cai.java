package com.hollysmart.huanwei;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.hollysmart.utils.Mlog;
import com.tencent.bugly.Bugly;
import com.vondear.rxtools.RxTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class App_Cai extends Application {
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        Mlog.TAG = "com.test";
        Mlog.OPENLOG = true;
        initOkHttpUtils();
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

        RxTool.init(this);
        Bugly.init(getApplicationContext(), "259984273d", false);


    }

    private void initOkHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("com.http"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

}























