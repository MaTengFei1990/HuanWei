package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hollysmart.APIs.getCarListAPI;
import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.CCM_DateTime;
import com.hollysmart.values.CarInfo;
import com.hollysmart.values.DirctroyBean;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.hollysmart.views.AreaTypePopu;
import com.hollysmart.views.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class shiPinActivity extends CaiSlidingBackActivity implements View.OnClickListener ,getCarListAPI.CarListIF,AreaTypePopu.PopupShaiXuanIF{

    private TextView tv_area;
    private List<CarInfo> carList;
    private Context mContext;
    private UserInfo userInfo;
    private AreaTypePopu areaTypePopu;
    private boolean isRefresh;
    private boolean fAdd;
    private boolean isAll;
    private int page = 1;
    private int count = 10;
    private int flag = 1;
    private String AreaType;
    private CheliangAdapter cheliangAdapter;
    @Override
    public int layoutResID() {
        return R.layout.activity_shi_pin;
    }

    private XListView lv_cheliang;

    @Override
    public void findView() {
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        lv_cheliang = (XListView) findViewById(R.id.lv_cheliang);
        lv_cheliang.setPullLoadEnable(false);
        lv_cheliang.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                initViewInfo();
            }

            @Override
            public void onLoadMore() {
            }
        });

        lv_cheliang.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (fAdd && carList.size() > count && (firstVisibleItem >= (totalItemCount - visibleItemCount))) {
                    fAdd = false;
                    nextPage();
                }
            }
        });

        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_area.setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);
        initData();
    }

    @Override
    public void init() {
        mContext=this;
        Object obj = ACache.get(getApplicationContext(), Values.CACHE_USER).getAsObject(Values.CACHE_USERINFO);
        if (obj != null) {
            userInfo = (UserInfo) obj;
        }

        areaTypePopu = new AreaTypePopu("area_type", this, userInfo);

        areaTypePopu.setPopupListener(this);
        initViewInfo();
        cheliangAdapter = new CheliangAdapter(carList);
        lv_cheliang.setAdapter(cheliangAdapter);
        tv_area.setText("所有");
    }

    private void initViewInfo() {
        page = 1;
        isRefresh = true;
        isAll = false;
        new getCarListAPI(userInfo, page+"", count+"",null,AreaType, this).request();
    }

    private void nextPage() {
        new getCarListAPI(userInfo, page+"", count+"",null,AreaType, this).request();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_area:
                areaTypePopu.showPopuWindow(tv_area);
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
        }
    }


    private void initData() {
        carList = new ArrayList<>();

    }

    @Override
    public void item(int type, int position, DirctroyBean bean) {
        tv_area.setText(bean.getLabel());
        bean.getValue();
        AreaType=bean.getValue();
        initViewInfo();
    }

    @Override
    public void getResult(List<CarInfo> list) {
        if (carList != null) {
            if (isRefresh) {
                carList.clear();
                isRefresh = false;
            }
            if (carList.size() != 0) {
                carList.remove(carList.size() - 1);
            }
            carList.addAll(list);

            if ((list).size() < count) {
                isAll = true;
            }
            if (!isAll) {
                fAdd = true;
                CarInfo info = new CarInfo();
                info.setAdd(true);
                info.setSetAllTag(false);
                carList.add(info);
                page++;
            } else {
                CarInfo info = new CarInfo();
                info.setAdd(true);
                info.setSetAllTag(true);
                carList.add(info);
            }
            cheliangAdapter.notifyDataSetChanged();
            onLoad();
        }
    }
    private void onLoad() {
        lv_cheliang.stopRefresh();
        lv_cheliang.stopLoadMore();
        lv_cheliang.setRefreshTime(new CCM_DateTime().Datetime());
    }



    private class CheliangAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<CarInfo> carList;

        public CheliangAdapter(List<CarInfo> carList) {
            inflater = LayoutInflater.from(mContext);
            this.carList = carList;
        }

        public CheliangAdapter() {
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
            convertView = inflater.inflate(R.layout.item_cheliang, null);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_titleName = (TextView) convertView.findViewById(R.id.tv_titleName);
            tv_titleName.setText("车牌号：");
            if (carList.get(position).isAdd()) {
                if (carList.get(position).isSetAllTag()) {
                    View view = inflater.inflate(R.layout.item_nodata, null);
                    return view;
                }
                View view = inflater.inflate(R.layout.item_progress, null);
                return view;
            } else {
                tv_name.setText(carList.get(position).getCarNum());
                TextView tv_quxian = (TextView) convertView.findViewById(R.id.tv_quxian);
                TextView tv_titleType = (TextView) convertView.findViewById(R.id.tv_titleType);
                TextView tv_tyep = (TextView) convertView.findViewById(R.id.tv_tyep);
                tv_quxian.setText(carList.get(position).getAreaType());
                tv_tyep.setText(carList.get(position).getCarType1());
                tv_titleType.setText("车辆类型：");
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(shiPinActivity.this, CarShiPinActivity.class);
                        intent.putExtra("value", carList.get(position));
                        startActivity(intent);

                    }
                });

                return convertView;

            }
        }

    }


}
