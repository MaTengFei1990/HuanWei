package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.values.TongJIInfo;

import java.util.ArrayList;
import java.util.List;

public class TongJiActivity extends CaiSlidingBackActivity implements View.OnClickListener {
    private ListView listView;
    private Context mContext;
    private List<TongJIInfo> tongJIInfoList;

    @Override
    public int layoutResID() {
        return R.layout.activity_tong_ji;
    }

    @Override
    public void findView() {
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.tongJiList);
        findViewById(R.id.tv_home).setOnClickListener(this);
    }

    @Override
    public void init() {
        mContext = this;
        initData();
        listView.setAdapter(new TongJiAdapter(tongJIInfoList));

    }

    private void initData() {
        tongJIInfoList = new ArrayList<>();
        TongJIInfo tongJIInfo = new TongJIInfo();
        tongJIInfo.setId("1");
        tongJIInfo.setName("设施分类统计");
        tongJIInfoList.add(tongJIInfo);
        tongJIInfo = new TongJIInfo();
        tongJIInfo.setId("2");
        tongJIInfo.setName("车辆统计");
        tongJIInfoList.add(tongJIInfo);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
        }

    }


    private class TongJiAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<TongJIInfo> carList;

        public TongJiAdapter(List<TongJIInfo> carList) {
            inflater = LayoutInflater.from(mContext);
            this.carList = carList;
        }

        public TongJiAdapter() {
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
            convertView = inflater.inflate(R.layout.item_tongji, null);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_name.setText(carList.get(position).getName());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        Intent intent = new Intent(TongJiActivity.this, SheShiHuiZongActivity.class);
                        intent.putExtra("title", "设施分类统计");
                        startActivity(intent);

                    }
                    if (position == 1) {
                        Intent intent = new Intent(TongJiActivity.this, CheLiangHuiZongActivity.class);
                        intent.putExtra("title", "车辆统计");
                        startActivity(intent);

                    }

                }
            });

            return convertView;
        }

    }

}
