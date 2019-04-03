package com.hollysmart.huanwei;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.hollysmart.style.StyleAnimActivity;
import com.hollysmart.values.SheShiFenLeiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cai on 2017/8/1.  基础信息模块
 */

public class SheshiListActivity extends StyleAnimActivity{

    @Override
    public int layoutResID() {
        return R.layout.activity_fenlei_sheshi;
    }

    private ListView lv_sheshi;
    @Override
    public void findView() {
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        lv_sheshi = (ListView) findViewById(R.id.lv_sheshi);
        findViewById(R.id.tv_home).setOnClickListener(this);
    }

    @Override
    public void init() {
        initData();
        lv_sheshi.setAdapter(new SheshiAdapter());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

    private List<SheShiFenLeiBean> sheShiBeenList;
    private void initData(){
        sheShiBeenList =new ArrayList<>();
        SheShiFenLeiBean sheShiBean = new SheShiFenLeiBean();
        sheShiBean.setId(1);
        sheShiBean.setName("环卫设施");
        sheShiBeenList.add(sheShiBean);
        sheShiBean = new SheShiFenLeiBean();
        sheShiBean.setId(2);
        sheShiBean.setName("厕所管理");
        sheShiBeenList.add(sheShiBean);

    }

    private class SheshiAdapter extends BaseAdapter{

        private LayoutInflater inflater;

        public SheshiAdapter() {
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return sheShiBeenList.size();
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
            convertView = inflater.inflate(R.layout.item_sheshi, null);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_name.setText(sheShiBeenList.get(position).getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position ==0) {
                        Intent intent = new Intent(SheshiListActivity.this, SheShiActivity.class);
                        intent.putExtra("title", sheShiBeenList.get(position).getName());
                        intent.putExtra("falg", position);
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(SheshiListActivity.this, CeSuoActivity.class);
//                        intent.putExtra("title", sheShiBeenList.get(position).getName());
//                        intent.putExtra("falg", position);
                        startActivity(intent);
                    } 
                }
            });
            return convertView;
        }

    }



}















