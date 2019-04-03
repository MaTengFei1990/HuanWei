package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.APIs.TongJiAPI;
import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.HWStaticInfo;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;

import java.util.ArrayList;
import java.util.List;

public class CheLiangHuiZongActivity extends CaiSlidingBackActivity implements TongJiAPI.TongJiAPIIF {

    private Context mContext;
    private ListView SheShiHuiZList;
    private TextView tv_title;
    private List<HWStaticInfo>staticList;
    private UserInfo userInfo;
    @Override
    public int layoutResID() {
        return R.layout.activity_che_liang_hui_zong;
    }

    @Override
    public void findView() {
        mContext=this;
        SheShiHuiZList = (ListView) findViewById(R.id.cheliangHuiZongList);
        tv_title = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);
    }

    @Override
    public void init() {
        String title = getIntent().getStringExtra("title");
        tv_title.setText(title);
        Object obj = ACache.get(getApplicationContext(), Values.CACHE_USER).getAsObject(Values.CACHE_USERINFO);
        if (obj != null) {
            userInfo = (UserInfo) obj;
        }
        staticList = new ArrayList<>();
        new TongJiAPI(userInfo,"cars",this).request();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, CarTuBiaoActivity.class);
                startActivity(homeintent);
                finish();
                break;

        }

    }

    @Override
    public void getReuslt(String msg) {
        Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        staticList = mGson.fromJson(msg, new TypeToken<List<HWStaticInfo>>() {
        }.getType());
        SheShiHuiZongAdapter cheliangAdapter = new SheShiHuiZongAdapter(staticList);
        SheShiHuiZList.setAdapter(cheliangAdapter);
    }


    private class SheShiHuiZongAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<HWStaticInfo> carList;

        public SheShiHuiZongAdapter(List<HWStaticInfo> carList) {
            inflater = LayoutInflater.from(mContext);
            this.carList=carList;
        }
        public SheShiHuiZongAdapter() {
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return carList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.item_key_value, null);
            TextView tv_key = (TextView) convertView.findViewById(R.id.tv_key);
            TextView tv_value = (TextView) convertView.findViewById(R.id.tv_value);
            if (!Utils.isEmpty(carList.get(position).getAreaName())) {
                tv_key.setText(carList.get(position).getAreaName());

            }
            if (!Utils.isEmpty(carList.get(position).getTotalTypeNum() + "")) {
                tv_value.setText(carList.get(position).getTotalTypeNum() + "");

            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CheLiangHuiZongActivity.this, CheLiangHuiZongDetailActivity.class);
                    intent.putExtra("value",carList.get(position) );
                    startActivity(intent);
                }
            });

            return convertView;
        }

    }

}
