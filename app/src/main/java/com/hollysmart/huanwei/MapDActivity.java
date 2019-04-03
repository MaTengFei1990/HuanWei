package com.hollysmart.huanwei;

import android.content.Context;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.hollysmart.services.ILocationUpdate;
import com.hollysmart.services.LocationUpdateReceiver;
import com.hollysmart.style.StyleAnimActivity;
import com.hollysmart.utils.BaiDuLatLng;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.OtherMap;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.LocationInfo;
import com.hollysmart.values.UnitDetailInfo;
import com.hollysmart.values.Values;

import java.util.ArrayList;
import java.util.List;

public class MapDActivity extends StyleAnimActivity implements ILocationUpdate{
    // 定位相关
    private LatLng mLatLng;
    BaiduMap mBaiduMap;
    MapView mMapView = null;
    private Context mContext;

    @Override
    public int layoutResID() {
        return R.layout.activity_map_d;
    }
    private List<BitmapDescriptor> bdLists;
    private List<BitmapDescriptor> bdLists_b;

    private void setBD_a() {
        bdLists = new ArrayList<>();
        bdLists.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_aa_01));
        bdLists.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_aa_02));
        bdLists.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_aa_03));
        bdLists.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_aa_04));
        bdLists.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_aa_05));
        bdLists.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_aa_06));
    }

    private void setBD_b() {
        bdLists_b = new ArrayList<>();
        bdLists_b.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_b_01));
        bdLists_b.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_b_02));
        bdLists_b.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_b_03));
        bdLists_b.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_b_04));
        bdLists_b.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_b_05));
        bdLists_b.add(BitmapDescriptorFactory.fromResource(R.mipmap.biao_b_06));
    }

    private ImageButton ib_map_back;
    private TextView tv_map_title;
    private TextView fangda;
    private TextView suoxiao;
    private TextView dingwei;
    private LinearLayout ll_bottom;

    // 详情栏view
    private View mDetail;
    private TextView tv_name;
    private TextView tv_dianzan;
    private LinearLayout ll_detail;
    private LinearLayout ll_daohang;

    @Override
    public void findView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        ib_map_back = (ImageButton) findViewById(R.id.ib_map_back);
        ib_map_back.setOnClickListener(this);
        tv_map_title = (TextView) findViewById(R.id.tv_map_title);

        fangda = (TextView) findViewById(R.id.fangda);
        suoxiao = (TextView) findViewById(R.id.suoxiao);
        dingwei = (TextView) findViewById(R.id.dingwei);
        fangda.setOnClickListener(this);
        suoxiao.setOnClickListener(this);
        dingwei.setOnClickListener(this);

        ll_bottom = (LinearLayout) findViewById(R.id.ll_bm);

        // 详情栏view
        mDetail = inflater.inflate(R.layout.item_map, null);
        tv_name = (TextView) mDetail.findViewById(R.id.tv_name);
        tv_dianzan = (TextView) mDetail.findViewById(R.id.tv_dianzan);
        ll_detail = (LinearLayout) mDetail.findViewById(R.id.ll_detail);
        ll_daohang = (LinearLayout) mDetail.findViewById(R.id.ll_daohang);
        ll_bottom.addView(mDetail);
    }

    public static final String INFO = "info";
    private UnitDetailInfo info;
    private int enterType; //1、详情进入2、行程进入
    private OtherMap otherMap;

    @Override
    public void init() {
        mContext = this;
        otherMap = new OtherMap(mContext);
        register(this);
        setBD_a();
        setBD_b();
        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        if (Values.DS_LAT != 0) {
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(
                    new LatLng(Values.DS_LAT, Values.DS_LNG), 10f);
            mBaiduMap.setMapStatus(u);
        }


        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMyLocationConfigeration(
                new MyLocationConfiguration(LocationMode.NORMAL, false,
                        BitmapDescriptorFactory.fromResource(R.mipmap.me02)));

        MyLocationData locData = new MyLocationData.Builder().direction(100).latitude(LocationInfo.getInstance().getLat()).longitude(LocationInfo.getInstance().getLng()).build();
        mBaiduMap.setMyLocationData(locData);
        mLatLng = new LatLng(locData.latitude, locData.longitude);


        enterType = getIntent().getIntExtra("enterType", 1);
        info = (UnitDetailInfo) getIntent().getSerializableExtra(INFO);

        putOverlay();
        tv_name.setText(info.getUnit_name());
        tv_map_title.setText(info.getUnit_name());
        tv_dianzan.setText(Utils.dianZan(info.getSaygood()));

        OnClickListener detailOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (enterType == 1) {
                    finish();
                } else {
//                    Intent intent = new Intent(mContext, UnitDetailActivity.class);
//                    intent.putExtra(UnitDetailActivity.ID, info.getUnit_id());
//                    intent.putExtra(UnitDetailActivity.TITLE, info.getUnit_name());
//                    startActivity(intent);
                }
            }
        };
        tv_name.setOnClickListener(detailOnClickListener);
        ll_detail.setOnClickListener(detailOnClickListener);

        ll_daohang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                otherMap.selectDialog(new OtherMap.MapIf() {
                    @Override
                    public void selectMap(int tag) {
                        String unitName = info.getUnit_name();
                        if (tag == otherMap.BAIDUTAG) {
                            otherMap.startBaiduMap(info.getLatitude(), info.getLongitude(), unitName);
                        }
                        if (tag == otherMap.GAODETAG) {
                            String[] latlans = new BaiDuLatLng().bToG(info.getLatitude(), info.getLongitude());
                            double mlat = Double.parseDouble(latlans[0]);
                            double mlng = Double.parseDouble(latlans[1]);
                            otherMap.startGaoDeMap(mlat, mlng, unitName);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        if (id == R.id.ib_map_back) {
            finish();
            overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_right);
        } else if (id == R.id.fangda) {
            ZoomChange(true);
        } else if (id == R.id.suoxiao) {
            ZoomChange(false);
        } else if (id == R.id.dingwei) {
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(mLatLng);
            mBaiduMap.animateMapStatus(u);
        }
    }




    private LocationUpdateReceiver locationUpdateReceiver;// 定位点更新广播

    private void register(ILocationUpdate locationUpdate) {
        locationUpdateReceiver = new LocationUpdateReceiver(locationUpdate);
        IntentFilter locUpdateFilter = new IntentFilter(Values.IF_LOC_UPDATE_MAP + getPackageName());
        registerReceiver(locationUpdateReceiver, locUpdateFilter);
    }

    private void unRegister() {
        if (locationUpdateReceiver != null) {
            unregisterReceiver(locationUpdateReceiver);
        }
    }

    @Override
    public void locationUpdate() {
        MyLocationData locData = new MyLocationData.Builder()
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(LocationInfo.getInstance().getLat())
                .longitude(LocationInfo.getInstance().getLng()).build();
        mBaiduMap.setMyLocationData(locData);
        mLatLng = new LatLng(locData.latitude, locData.longitude);
    }


    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
        unRegister();
    }

    private void putOverlay() {
        LatLng llA = new LatLng(info.getLatitude(), info.getLongitude());
        BitmapDescriptor bitmapDescriptor = null;
        int type = info.getUnit_type();
        if (type == 1) {
            bitmapDescriptor = bdLists.get(0);
        } else if (type == 2) {
            bitmapDescriptor = bdLists.get(1);
        } else if (type == 3) {
            bitmapDescriptor = bdLists.get(2);
        } else if (type == 4) {
            bitmapDescriptor = bdLists.get(3);
        } else if (type == 5) {
            bitmapDescriptor = bdLists.get(4);
        } else if (type == 6) {
            bitmapDescriptor = bdLists.get(5);
        } else {
            bitmapDescriptor = bdLists.get(5);
        }
        OverlayOptions ooA = new MarkerOptions().position(llA).icon(bitmapDescriptor).draggable(true);
        Marker marker = (Marker) (mBaiduMap.addOverlay(ooA));
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(llA, 16f);
        mBaiduMap.animateMapStatus(u);
    }

    private float zoomLevel;

    // b true 为放大 false 为缩小
    private void ZoomChange(boolean b) {
        zoomLevel = mBaiduMap.getMapStatus().zoom;
        Mlog.d("zoom:" + zoomLevel);
        if (b) {
            if (zoomLevel < mBaiduMap.getMaxZoomLevel()) {
                zoomLevel = zoomLevel + 1;
            }
        } else {
            if (zoomLevel > mBaiduMap.getMinZoomLevel()) {
                zoomLevel = zoomLevel - 1;
            }
        }
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(zoomLevel);
        mBaiduMap.animateMapStatus(u);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_right);
    }
}
