package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.hollysmart.APIs.GetGPSAPI;
import com.hollysmart.dialog.LoadingProgressDialog;
import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.CCM_DateTime;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.GpsInfo;
import com.hollysmart.values.RangeInfo;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.hollysmart.values.hwAlarmMapInfo;

import java.util.ArrayList;
import java.util.List;

public class XianluActivity extends CaiSlidingBackActivity implements GetGPSAPI.GetGPSAPIIF ,View.OnClickListener{
    // 地图相关
    MapView mMapView;
    BaiduMap mBaiduMap;
    // 普通折线，点击时改变宽度
    Polyline mPolyline;
    Polyline mColorfulPolyline;

    // 通过设置间隔时间和距离可以控制速度和图标移动的距离
    private static final int TIME_INTERVAL = 80;
    private static final double DISTANCE = 0.00002;
    private Handler mHandler;
    private Marker mMoveMarker;
    private  MapStatus.Builder builder;
    private String plateno ;

    private List<GpsInfo> gpsInfoList;
    private List<RangeInfo> RangeList;
    private List<hwAlarmMapInfo> hwAlarmMapList;
    private List data_list;
    private UserInfo userInfo;
    private TextView tv_speed;
    private TextView tv_JingDu;
    private TextView tv_Weidu;
    private TextView tv_carNum;
    private List<LatLng> lantlngs ;
    private Context mContext;
    private boolean isStart = false;
    private LoadingProgressDialog lpd;


    private View mPopView = null;

    @Override
    public int layoutResID() {
        return R.layout.activity_xianlu;
    }

    @Override
    public void findView() {
        mContext=this;
        setLpd();
        plateno = getIntent().getStringExtra("id");
        Mlog.d(plateno + "");
        gpsInfoList = new ArrayList<>();
        RangeList = new ArrayList<>();
        hwAlarmMapList = new ArrayList<>();

        findViewById(R.id.tv_home).setOnClickListener(this);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        tv_speed = (TextView) findViewById(R.id.tv_speed);
        tv_JingDu = (TextView) findViewById(R.id.tv_JingDu);
        tv_Weidu = (TextView) findViewById(R.id.tv_Weidu);
        tv_carNum = (TextView) findViewById(R.id.tv_carNum);
        if (!Utils.isEmail(plateno)) {
            tv_carNum.setText(plateno);
        }
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 界面加载时添加绘制图层
//        addCustomElementsDemo();

        builder = new MapStatus.Builder();
        builder.zoom(19.0f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mHandler = new Handler(Looper.getMainLooper());
        mMapView.showZoomControls(false);
        Object obj = ACache.get(getApplicationContext(), Values.CACHE_USER).getAsObject(Values.CACHE_USERINFO);
        if (obj != null) {
            userInfo = (UserInfo) obj;
        }
        Mlog.d("plateno===" + plateno);
        Mlog.d("userInfo===" + userInfo.toString());
        Mlog.d("time===" + new CCM_DateTime().Date());


        new GetGPSAPI(userInfo,plateno,new CCM_DateTime().Date(),this).request();
        lpd.show();

//        new GetGPSAPI(userInfo,plateno,"2017-07-25",this).request();
    }
    private void setLpd() {
        lpd = new LoadingProgressDialog();
        lpd.setMessage("正在读取数据中，请稍等...");
        lpd.create(this, lpd.STYLE_SPINNER);
    }

    @Override
    public void init() {
//        数据
        data_list = new ArrayList<String>();
        data_list.add("1");
        data_list.add("2");
        data_list.add("3");
        data_list.add("4");
        data_list.add("5");
        data_list.add("6");

    }

    private void drawPolyLine( List<GpsInfo> latlngs) {
        if (latlngs != null && latlngs.size() > 0) {

            List<LatLng> polylines = new ArrayList<>();
            for (int index = 0; index < latlngs.size(); index++) {
                polylines.add(new LatLng(latlngs.get(index).getX(),latlngs.get(index).getY()));
            }

//            polylines.add(new LatLng(latlngs.get(0).getX(),latlngs.get(0).getY()));
            PolylineOptions polylineOptions = new PolylineOptions().points(polylines).width(10).color(Color.GREEN);

            mPolyline = (Polyline) mBaiduMap.addOverlay(polylineOptions);
            OverlayOptions markerOptions;
            markerOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.arrow))
                    .position(polylines.get(0))
                    .rotate((float) getAngle(0));
            mMoveMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
        }


    }
    /**
     * 循环进行移动逻辑
     */
    public void moveLooper() {
         lantlngs = lantlngs(gpsInfoList);
        try {
            new Thread() {
                @Override
                public synchronized void run() {
//                    while (true) {
                        if (isStart) {
                            for (int i = 0; i < gpsInfoList.size() - 1; i++) {
                                Mlog.d("-----------------i-------------",i + "");
                                Mlog.d("-----------------x-------------",gpsInfoList.get(i).getX()+"");
                                Mlog.d("-----------------Y-------------",gpsInfoList.get(i).getY()+"");
                                Mlog.d(gpsInfoList + "");
                                setUserMapCenter(gpsInfoList.get(i).getX(), gpsInfoList.get(i).getY());
                                final String x = gpsInfoList.get(i).getX() + "";
                                final String y = gpsInfoList.get(i).getY() + "";
                                final String v = gpsInfoList.get(i).getV() + "";
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_Weidu.setText(x);
                                        tv_JingDu.setText(y);
                                        tv_speed.setText(v);
                                    }
                                });
                                final LatLng startPoint = lantlngs.get(i);
                                final LatLng endPoint =  lantlngs.get(i+1);
                                if (i > 0) {
                                    final LatLng zheXianS = lantlngs.get(i-1);
                                    final LatLng zheXianE = lantlngs.get(i);

                                    List<LatLng> points = new ArrayList<LatLng>();
                                    points.add(zheXianS);
                                    points.add(zheXianE);
                                    OverlayOptions ooPolyline = new PolylineOptions().width(11)
                                            .color(Color.BLUE).points(points);
                                    mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
                                }
                                mMoveMarker.setPosition(startPoint);

                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        // refresh marker's rotate
                                        if (mMapView == null) {
                                            return;
                                        }
                                        mMoveMarker.setRotate((float) getAngle(startPoint,endPoint));
                                    }
                                });
                                double slope = getSlope(startPoint, endPoint);
                                // 是不是正向的标示
                                boolean isReverse = (startPoint.latitude > endPoint.latitude);

                                double intercept = getInterception(slope, startPoint);

                                double xMoveDistance = isReverse ? getXMoveDistance(slope) :
                                        -1 * getXMoveDistance(slope);


                                for (double j = startPoint.latitude; !((j >= endPoint.latitude) ^ isReverse);
                                     j = j - xMoveDistance) {
                                    LatLng latLng = null;
                                    if (slope == Double.MAX_VALUE) {
                                        latLng = new LatLng(j, startPoint.longitude);
                                    } else {
                                        latLng = new LatLng(j, (j - intercept) / slope);
                                    }

                                    final LatLng finalLatLng = latLng;
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (mMapView == null) {
                                                return;
                                            }
                                            mMoveMarker.setPosition(finalLatLng);
                                        }
                                    });
                                    try {
                                        Thread.sleep(TIME_INTERVAL);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }


                            }


//                        }
                    }

                }

            }.start();
        } catch (Exception e) {
            e.printStackTrace();
            Mlog.d("e.getMessage", e.getMessage());
        }

    }
    /**
     * 计算x方向每次移动的距离
     */
    private double getXMoveDistance(double slope) {
        if (slope == Double.MAX_VALUE) {
            return DISTANCE;
        }
        return Math.abs((DISTANCE * slope) / Math.sqrt(1 + slope * slope));
    }



    /**
     * 根据点和斜率算取截距
     */
    private double getInterception(double slope, LatLng point) {

        double interception = point.latitude - slope * point.longitude;
        return interception;
    }



    private  List<LatLng> lantlngs(List<GpsInfo> list) {
        List<LatLng> latlangs = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for(int i=0;i<list.size();i++) {
                latlangs.add(new LatLng(list.get(i).getX(), list.get(i).getY()));
            }
        }
        return latlangs;
    }

    /**
     * 根据点获取图标转的角度
     */
    private double getAngle(int startIndex) {
        if ((startIndex + 1) >= mPolyline.getPoints().size()) {
            throw new RuntimeException("index out of bonds");
        }
        LatLng startPoint = mPolyline.getPoints().get(startIndex);
        LatLng endPoint = mPolyline.getPoints().get(startIndex + 1);
        return getAngle(startPoint, endPoint);
    }

    /**
     * 根据两点算取图标转的角度
     */
    private double getAngle(LatLng fromPoint, LatLng toPoint) {
        double slope = getSlope(fromPoint, toPoint);
        if (slope == Double.MAX_VALUE) {
            if (toPoint.latitude > fromPoint.latitude) {
                return 0;
            } else {
                return 180;
            }
        }
        float deltAngle = 0;
        if ((toPoint.latitude - fromPoint.latitude) * slope < 0) {
            deltAngle = 180;
        }
        double radio = Math.atan(slope);
        double angle = 180 * (radio / Math.PI) + deltAngle - 90;
        return angle;
    }

    /**
     * 算斜率
     */
    private double getSlope(LatLng fromPoint, LatLng toPoint) {
        if (toPoint.longitude == fromPoint.longitude) {
            return Double.MAX_VALUE;
        }
        double slope = ((toPoint.latitude - fromPoint.latitude) / (toPoint.longitude - fromPoint.longitude));
        return slope;

    }

    @Override
    public void GetResult(List<GpsInfo> gpsList, List<RangeInfo> RgeList, List<hwAlarmMapInfo> hwMapList) {
        Mlog.d(gpsList + "");
        lpd.cancel();
        if (gpsList != null && gpsList.size() > 0) {
            gpsInfoList.addAll(gpsList);
           drawPolyLine(gpsInfoList);
            setUserMapCenter(gpsList.get(0).getX(), gpsList.get(0).getY());
        }
    }

    /**
     * 设置中心点
     */
    private void setUserMapCenter(double lat,double lon) {
        Log.v("pcw","setUserMapCenter : lat : "+ lat+" lon : " + lon);
        LatLng cenpt = new LatLng(lat,lon);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                Intent intent = new Intent(XianluActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_start:
                isStart = !isStart;
                moveLooper();
                break;
        }

    }
}
