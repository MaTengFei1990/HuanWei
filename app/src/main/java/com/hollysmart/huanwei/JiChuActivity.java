package com.hollysmart.huanwei;

import android.content.Intent;
import android.view.View;

import com.hollysmart.style.StyleAnimActivity;

/**
 * Created by cai on 2017/8/1.  基础信息模块
 */

public class JiChuActivity extends StyleAnimActivity{

    @Override
    public int layoutResID() {
        return R.layout.activity_mokuai_jichu;
    }

    @Override
    public void findView() {
        findViewById(R.id.tv_home).setOnClickListener(this);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.bn_one).setOnClickListener(this);
        findViewById(R.id.bn_two).setOnClickListener(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.bn_one:
                startActivity(new Intent(mContext, SheshiListActivity.class));
                break;
            case R.id.bn_two:
                startActivity(new Intent(mContext, CheLiangActivity.class));
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
        }
    }
}
