package com.hollysmart.huanwei;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.hollysmart.APIs.getCarListAPI;
import com.hollysmart.APIs.getDataListAPI;
import com.hollysmart.style.StyleAnimActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.CCM_DateTime;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.CarInfo;
import com.hollysmart.values.DirctroyBean;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.hollysmart.views.AreaTypePopu;
import com.hollysmart.views.xlistview.XListView;
import com.vondear.rxtools.view.dialog.RxDialogLoading;
import com.vondear.rxtools.view.dialog.RxDialogShapeLoading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cai on 2017/8/1.  基础信息模块
 */

public class CheLiangActivity extends StyleAnimActivity implements getCarListAPI.CarListIF, AreaTypePopu.PopupShaiXuanIF,
        getDataListAPI.DataListIF, View.OnClickListener {
    private TextView tv_area;
    private EditText et_ChePaiHao;
    private List<CarInfo> carList;
    private CheliangAdapter cheliangAdapter;
    private String AreaType;
    private UserInfo userInfo;
    private AreaTypePopu areaTypePopu;
    private boolean isRefresh;
    private boolean fAdd;
    private boolean isAll;
    private int page = 1;
    private int count = 10;
    private int flag = 1;
    @Override
    public int layoutResID() {
        return R.layout.activity_fenlei_cheliang;
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
                if (fAdd && carList.size() >= count &&(firstVisibleItem >= (totalItemCount - visibleItemCount))) {
                    fAdd = false;
                    nextPage();
                }
            }
        });


        tv_area = (TextView) findViewById(R.id.tv_area);
        et_ChePaiHao = (EditText) findViewById(R.id.et_ChePaiHao);
        tv_area.setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);
        initData();
    }

    private void initData() {
        carList = new ArrayList<>();
        Object obj = ACache.get(getApplicationContext(), Values.CACHE_USER).getAsObject(Values.CACHE_USERINFO);
        if (obj != null) {
            userInfo = (UserInfo) obj;
        }
        areaTypePopu = new AreaTypePopu("area_type", this, userInfo);
        areaTypePopu.setPopupListener(this);
        initViewInfo();
    }

    private void initViewInfo() {
        page = 1;
        isRefresh = true;
        isAll = false;
        new getCarListAPI(userInfo, page + "", count + "", et_ChePaiHao.getText().toString(), AreaType, this).request();
    }

    private void nextPage() {
        new getCarListAPI(userInfo, page + "", count + "", et_ChePaiHao.getText().toString(), AreaType, this).request();
    }

    @Override
    public void init() {
        cheliangAdapter = new CheliangAdapter(carList);
        lv_cheliang.setAdapter(cheliangAdapter);
        tv_area.setText("所有");
        et_ChePaiHao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Utils.isEmpty(editable.toString())) {
                    initViewInfo();
                }

            }

        });
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

    @Override
    public void item(int type, int position, DirctroyBean bean) {
        tv_area.setText(bean.getLabel());
        bean.getValue();
        AreaType = bean.getValue();
        initViewInfo();
    }

    @Override
    public void getResult(String str) {

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
            CarInfo carInfo = carList.get(position);
            if (carInfo.isAdd()) {
                if (carInfo.isSetAllTag()) {
                    View view = inflater.inflate(R.layout.item_nodata, null);
                    return view;
                }
                View view = inflater.inflate(R.layout.item_progress, null);
                return view;
            } else {
                tv_titleName.setText("车牌号：");
                Mlog.d("区县名：" + carList.get(position).getAreaType());
                tv_name.setText(carList.get(position).getCarNum());
                TextView tv_quxian = (TextView) convertView.findViewById(R.id.tv_quxian);
                tv_quxian.setText(carList.get(position).getAreaType());
                TextView tv_tyep = (TextView) convertView.findViewById(R.id.tv_tyep);
                TextView tv_titleType = (TextView) convertView.findViewById(R.id.tv_titleType);
                tv_titleType.setText("车辆类型:");
                tv_tyep.setText(carList.get(position).getCarType1());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CheLiangActivity.this, CarDetailActivity.class);
                        intent.putExtra("value", carList.get(position));
                        startActivity(intent);

                    }
                });

                return convertView;

            }

        }

    }


    private void onLoad() {
        lv_cheliang.stopRefresh();
        lv_cheliang.stopLoadMore();
        lv_cheliang.setRefreshTime(new CCM_DateTime().Datetime());
    }


}















