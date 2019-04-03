package com.hollysmart.utils;

import android.os.Handler;
import android.os.Message;

public class CCM_Delay {

    public interface DelayIF {
        void operate();
    }

    public CCM_Delay(final long time, final DelayIF delayIF) {

        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                delayIF.operate();
                return false;
            }
        });

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(time);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

}













