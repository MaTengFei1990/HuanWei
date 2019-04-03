package com.hollysmart.huanwei;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hollysmart.services.LocationService;
import com.hollysmart.style.CaiSlidingBackActivity;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.RxTool;
import com.vondear.rxtools.view.RxTitle;

public class MainActivity extends CaiSlidingBackActivity {
    @Override
    public int layoutResID() {
        return R.layout.activity_main;
    }

    private RxTitle rxTitle;
    @Override
    public void findView() {
        findViewById(R.id.iv_one).setOnClickListener(this);
        findViewById(R.id.iv_two).setOnClickListener(this);
        findViewById(R.id.iv_three).setOnClickListener(this);
        findViewById(R.id.iv_four).setOnClickListener(this);
        findViewById(R.id.iv_five).setOnClickListener(this);
        findViewById(R.id.iv_six).setOnClickListener(this);
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setRightTextOnClickListener(this);
    }

    @Override
    public void init() {
        startService(new Intent(this, LocationService.class));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_one:
                Intent oneintent = new Intent(this, CheLiangZongJiActivity.class);
                startActivity(oneintent);
                break;
            case R.id.iv_two:
                Intent twointent = new Intent(this, ShiShidingWeiActivity.class);
                startActivity(twointent);
                break;
            case R.id.iv_three:
                Intent xianLuintent = new Intent(this, CheliangJianKongActivity.class);
                startActivity(xianLuintent);
                break;
            case R.id.iv_four:
                Intent intent = new Intent(this, JiChuActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_five:
                Intent shiPinintent = new Intent(this, shiPinActivity.class);
                startActivity(shiPinintent);
                break;
            case R.id.iv_six:
                Intent tongJiintent = new Intent(this, TongJiActivity.class);
                startActivity(tongJiintent);
                break;
            case R.id.tv_right:
                Intent sheZhiintent = new Intent(this, SettingActivity.class);
                startActivity(sheZhiintent);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, LocationService.class));
        super.onDestroy();
    }
}
