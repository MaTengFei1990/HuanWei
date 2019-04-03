package com.hollysmart.huanwei;

import android.content.Context;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.APIs.getDataListAPI;
import com.hollysmart.APIs.getSheShiAPI;
import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.CCM_DateTime;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.DirctroyBean;
import com.hollysmart.values.SheShiInfo;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.hollysmart.views.AreaTypePopu;
import com.hollysmart.views.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class SheShiActivity extends CaiSlidingBackActivity implements View.OnClickListener, AreaTypePopu.PopupShaiXuanIF, getSheShiAPI.SheShiAPIIF {
    private TextView tv_area;
    private EditText et_SheShiName;
    private List<DirctroyBean> areaList;
    private List<SheShiInfo> sheShiList;
    private Context mContext;
    private XListView lv_sheShi;
    private String title;
    private int falg;
    private SheShiAdapter sheShiAdapter;
    private UserInfo userInfo;
    private AreaTypePopu areaTypePopu;
    private String AreaType;
    private boolean isRefresh;
    private boolean fAdd;
    private boolean isAll;
    private int page = 1;
    private int count = 10;

    @Override
    public int layoutResID() {
        return R.layout.activity_she_shi;
    }

    @Override
    public void findView() {
        title = getIntent().getStringExtra("title");
        TextView tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(title);
        falg = getIntent().getIntExtra("falg", -1);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        tv_area = (TextView) findViewById(R.id.tv_area);
        lv_sheShi = (XListView) findViewById(R.id.lv_sheShi);
        lv_sheShi.setPullLoadEnable(false);
        lv_sheShi.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                initViewInfo();
            }

            @Override
            public void onLoadMore() {
            }
        });

        lv_sheShi.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (fAdd && sheShiList.size() > count && firstVisibleItem >= (totalItemCount - visibleItemCount)) {
                    fAdd = false;
                    nextPage();
                }
            }
        });

        et_SheShiName = (EditText) findViewById(R.id.et_SheShiName);
        tv_area.setOnClickListener(this);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);
        et_SheShiName.addTextChangedListener(new TextWatcher() {
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
    public void init() {
        mContext = this;
        areaList = new ArrayList<>();
        sheShiList = new ArrayList<>();
        sheShiAdapter = new SheShiAdapter(sheShiList);
        lv_sheShi.setAdapter(sheShiAdapter);
        tv_area.setText("所有");
        Object obj = ACache.get(getApplicationContext(), Values.CACHE_USER).getAsObject(Values.CACHE_USERINFO);
        if (obj != null) {
            userInfo = (UserInfo) obj;
        }
        areaTypePopu = new AreaTypePopu("area_type", this, userInfo);
        areaTypePopu.setPopupListener(this);

        new getDataListAPI(userInfo, "area_type", new getDataListAPI.DataListIF() {
            @Override
            public void getResult(String str) {
                Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                DirctroyBean dirctroyBean = new DirctroyBean();
                dirctroyBean.setLabel("所有");
                dirctroyBean.setValue(null);
                areaList.add(dirctroyBean);

                List<DirctroyBean> dirList = mGson.fromJson(str, new TypeToken<List<DirctroyBean>>() {
                }.getType());
                areaList.addAll(dirList);

                new getSheShiAPI(userInfo, et_SheShiName.getText().toString(), AreaType, count + "", page + "", SheShiActivity.this).request();

            }
        }).request();
        initViewInfo();

    }

    private void initViewInfo() {
        page = 1;
        isRefresh = true;
        isAll = false;
        new getSheShiAPI(userInfo, et_SheShiName.getText().toString(), AreaType, count + "", page + "", SheShiActivity.this).request();
    }

    private void nextPage() {
        new getSheShiAPI(userInfo, et_SheShiName.getText().toString(), AreaType, count + "", page + "", SheShiActivity.this).request();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_area:
                areaTypePopu.showPopuWindow(tv_area);
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
            case R.id.iv_fanhui:
                finish();
                break;
        }

    }

    @Override
    public void getSheShiResult(List<SheShiInfo> list) {
        if (sheShiList != null) {
            if (isRefresh) {
                sheShiList.clear();
                isRefresh = false;
            }
            if (sheShiList.size() != 0) {
                sheShiList.remove(sheShiList.size() - 1);
            }
            sheShiList.addAll(list);

            if ((list).size() < count) {
                isAll = true;
            }
            if (!isAll) {
                fAdd = true;
                SheShiInfo info = new SheShiInfo();
                info.setAdd(true);
                info.setSetAllTag(false);
                sheShiList.add(info);
                page++;
            } else {
                SheShiInfo info = new SheShiInfo();
                info.setAdd(true);
                info.setSetAllTag(true);
                sheShiList.add(info);
            }
            sheShiAdapter.notifyDataSetChanged();
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


    private class SheShiAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<SheShiInfo> carList;

        public SheShiAdapter(List<SheShiInfo> carList) {
            inflater = LayoutInflater.from(mContext);
            this.carList = carList;
        }

        public SheShiAdapter() {
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
            TextView tv_quxian = (TextView) convertView.findViewById(R.id.tv_quxian);
            TextView tv_titleName = (TextView) convertView.findViewById(R.id.tv_titleName);
            TextView tv_tyep = (TextView) convertView.findViewById(R.id.tv_tyep);
            SheShiInfo sheShiInfo = carList.get(position);
            if (sheShiInfo.isAdd()) {
                if (sheShiInfo.isSetAllTag()) {
                    View view = inflater.inflate(R.layout.item_nodata, null);
                    return view;
                }
                View view = inflater.inflate(R.layout.item_progress, null);
                return view;
            } else {
                tv_name.setText(carList.get(position).getFacilityName());
                tv_quxian.setText(carList.get(position).getAreaType());
                tv_tyep.setText(carList.get(position).getFacilityType());
                tv_titleName.setText("设施名称：");

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SheShiActivity.this, SheShiDetailsActivity.class);
                        intent.putExtra("value", carList.get(position));
                        startActivity(intent);

                    }
                });

            }

            return convertView;
        }

    }

    private void onLoad() {
        lv_sheShi.stopRefresh();
        lv_sheShi.stopLoadMore();
        lv_sheShi.setRefreshTime(new CCM_DateTime().Datetime());
    }

}
