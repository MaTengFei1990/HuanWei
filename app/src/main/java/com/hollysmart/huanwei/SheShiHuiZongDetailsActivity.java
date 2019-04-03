package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.HWStaticInfo;
import com.hollysmart.values.HwStatisticDetails;

import java.util.List;

public class SheShiHuiZongDetailsActivity extends CaiSlidingBackActivity implements View.OnClickListener{


    private Context mContext;
    private SheShiDetailAdapter sheShiDetailAdapter;
    private ListView listView;
    private TextView tv_area;

        @Override
        public int layoutResID() {
            return R.layout.activity_she_shi_hui_zong_details;
        }

        @Override
        public void findView() {
            mContext=this;
            tv_area= (TextView) findViewById(R.id.text_area);
            findViewById(R.id.tv_home).setOnClickListener(this);
            findViewById(R.id.iv_fanhui).setOnClickListener(this);
            listView = (ListView) findViewById(R.id.lv_view);

        }

        @Override
        public void init() {
            HWStaticInfo value = (HWStaticInfo) getIntent().getSerializableExtra("value");
            tv_area.setText(value.getAreaName()+"设施汇总");
            List<HwStatisticDetails> detailLists = value.getDetailLists();
            sheShiDetailAdapter = new SheShiDetailAdapter(detailLists);
            listView.setAdapter(sheShiDetailAdapter);
            Mlog.d(value.toString());
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

    private class SheShiDetailAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<HwStatisticDetails> carList;

        public SheShiDetailAdapter(List<HwStatisticDetails> carList) {
            inflater = LayoutInflater.from(mContext);
            this.carList=carList;
        }
        public SheShiDetailAdapter() {
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
            ImageView headerLeftButton = (ImageView) convertView.findViewById(R.id.headerLeftButton);
            headerLeftButton.setVisibility(View.GONE);
            if (!Utils.isEmpty(carList.get(position).getStatisticTypeName())) {
                tv_key.setText(carList.get(position).getStatisticTypeName());
            }
            if (!Utils.isEmpty(carList.get(position).getTypeNum()+"")) {
                tv_value.setText(carList.get(position).getTypeNum() + "");
            }

            return convertView;
        }

    }




    }

