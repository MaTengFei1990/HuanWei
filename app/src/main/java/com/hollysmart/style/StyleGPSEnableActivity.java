package com.hollysmart.style;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.NetworkDetector;

/**
 * Created by cai on 15/9/30
 */
public abstract class StyleGPSEnableActivity extends StyleAnimActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GPSEnable();
    }

    private void GPSEnable() {
        Mlog.d("检查GPS");
        if (!NetworkDetector.isGpsEnable(this)) {
            Mlog.d("检查GPS  --- 未开");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("当前您未打开GPS功能，请到设置页中打开");
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setNegativeButton("设置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(intent);

                    } catch (ActivityNotFoundException ex) {
                        intent.setAction(Settings.ACTION_SETTINGS);
                        try {
                            startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }
            });
            builder.create().show();
        }
    }

}
