package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hollysmart.APIs.getToiletAPI;
import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.CCM_DateTime;
import com.hollysmart.values.CeSuoInfo;
import com.hollysmart.values.DirctroyBean;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.hollysmart.views.AreaTypePopu;
import com.hollysmart.views.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class CeSuoActivity extends CaiSlidingBackActivity implements getToiletAPI.ToiletAPIIF,AreaTypePopu.PopupShaiXuanIF, View.OnClickListener{
    private TextView tv_area;
    private List<CeSuoInfo> toiletList;
    private Context mContext;
    private XListView lv_CeSuo;
    private CeSuoAdapter ceSuoAdapter;
    private UserInfo userInfo;
    private boolean isRefresh;
    private boolean fAdd;
    private boolean isAll;
    private int page = 1;
    private int count = 10;
    private int flag = 1;
    private String AreaType;
    private AreaTypePopu areaTypePopu;

    @Override
    public int layoutResID() {
        return R.layout.activity_ce_suo;
    }

    @Override
    public void findView() {
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_area.setOnClickListener(this);
        lv_CeSuo = (XListView) findViewById(R.id.lv_CeSuo);
        lv_CeSuo.setPullLoadEnable(false);
        lv_CeSuo.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                initViewInfo();
            }

            @Override
            public void onLoadMore() {
            }
        });

        lv_CeSuo.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (fAdd && toiletList.size() > count && (firstVisibleItem >= (totalItemCount - visibleItemCount))) {
                    fAdd = false;
                    nextPage();
                }
            }
        });

        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);

    }

    @Override
    public void init() {
        mContext=this;
        toiletList = new ArrayList<>();
        tv_area.setText("所有");
        ceSuoAdapter = new CeSuoAdapter(toiletList);
        lv_CeSuo.setAdapter(ceSuoAdapter);
        Object obj = ACache.get(getApplicationContext(), Values.CACHE_USER).getAsObject(Values.CACHE_USERINFO);
        if (obj != null) {
            userInfo = (UserInfo) obj;
        }
        areaTypePopu=new AreaTypePopu("area_type",this,userInfo);
        areaTypePopu.setPopupListener(this);
       initViewInfo();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_area:
                areaTypePopu.showPopuWindow(tv_area);
                break;
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
    private void initViewInfo() {
        page = 1;
        isRefresh = true;
        isAll = false;
        new getToiletAPI(userInfo, AreaType, count+"", page+"", this).request();
    }

    private void nextPage() {
        new getToiletAPI(userInfo, AreaType, count+"", page+"", this).request();
    }




    @Override
    public void getToiletList(List<CeSuoInfo> list) {
        if (toiletList != null) {
            if (isRefresh) {
                toiletList.clear();
                isRefresh = false;
            }
            if (toiletList.size() != 0) {
                toiletList.remove(toiletList.size() - 1);
            }
            toiletList.addAll(list);

            if ((list).size() < count) {
                isAll = true;
            }
            if (!isAll) {
                fAdd = true;
                CeSuoInfo info = new CeSuoInfo();
                info.setAdd(true);
                info.setSetAllTag(false);
                toiletList.add(info);
                page++;
            } else {
                CeSuoInfo info = new CeSuoInfo();
                info.setAdd(true);
                info.setSetAllTag(true);
                toiletList.add(info);
            }
            ceSuoAdapter.notifyDataSetChanged();
            onLoad();
        }


    }

    @Override
    public void item(int type, int position, DirctroyBean bean) {
        tv_area.setText(bean.getLabel());
        bean.getValue();
        AreaType=bean.getValue();
        initViewInfo();
    }

    private class CeSuoAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<CeSuoInfo> carList;

        public CeSuoAdapter(List<CeSuoInfo> carList) {
            inflater = LayoutInflater.from(mContext);
            this.carList=carList;
        }
        public CeSuoAdapter() {
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
            TextView tv_titleType = (TextView) convertView.findViewById(R.id.tv_titleType);
            TextView tv_tyep = (TextView) convertView.findViewById(R.id.tv_tyep);
            CeSuoInfo ceSuoInfo = carList.get(position);
            if (ceSuoInfo.isAdd()) {
                if (ceSuoInfo.isSetAllTag()) {
                    View view = inflater.inflate(R.layout.item_nodata, null);
                    return view;
                }
                View view = inflater.inflate(R.layout.item_progress, null);
                return view;
            }else {
                tv_tyep.setVisibility(View.GONE);
                tv_titleType.setVisibility(View.GONE);
                tv_name.setText(carList.get(position).getName());
                tv_quxian.setText(carList.get(position).getAreaType());

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CeSuoActivity.this, CeSuoDetailActivity.class);
                        intent.putExtra("value", carList.get(position));
                        startActivity(intent);

                    }
                });

                return convertView;

            }
        }

    }
    private void onLoad() {
        lv_CeSuo.stopRefresh();
        lv_CeSuo.stopLoadMore();
        lv_CeSuo.setRefreshTime(new CCM_DateTime().Datetime());
    }

}
