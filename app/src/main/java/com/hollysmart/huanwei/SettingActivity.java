package com.hollysmart.huanwei;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.Values;

public class SettingActivity extends CaiSlidingBackActivity implements View.OnClickListener{


    @Override
    public int layoutResID() {
        return R.layout.activity_setting;
    }

    private String versName;
    private TextView tv_versionName;
    @Override
    public void findView() {
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.tuiChu).setOnClickListener(this);
        findViewById(R.id.tv_banbeng).setOnClickListener(this);
        tv_versionName = (TextView) findViewById(R.id.tv_versionName);
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versName = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() {
        tv_versionName.setText("V" + versName);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tuiChu:
                ACache.get(getApplicationContext(), Values.CACHE_USER).clear();
                ACache.get(getApplicationContext(), Values.CACHE_USER).clear();
                Intent exitIntent = new Intent(Values.EXITLOGIN);
                sendBroadcast(exitIntent);
                finish();
                Intent intent = new Intent(this, LoginActivity.class);

                startActivity(intent);
                break;
            case R.id.tv_banbeng:
//                Utils.showToast(this, "补丁测试");
                break;

        }

    }
}
