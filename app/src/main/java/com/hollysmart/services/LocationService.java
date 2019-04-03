package com.hollysmart.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.hollysmart.utils.Mlog;
import com.hollysmart.values.LocationInfo;
import com.hollysmart.values.Values;

/**
 * @author baidu
 */
public class LocationService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private LocationClient client = null;
    private LocationClientOption mOption;
    private LocationClientOption DIYoption;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new LocationClient(this);
        client.registerLocationListener(mListener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        setLocationOption(getDefaultLocationClientOption());
        start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stop();
        super.onDestroy();
    }

    /***
     * @param option
     * @return isSuccessSetOption
     */
    public boolean setLocationOption(LocationClientOption option) {
        boolean isSuccess = false;
        if (option != null) {
            if (client.isStarted())
                client.stop();
            client.setLocOption(option);
            isSuccess = true;
        }
        return isSuccess;
    }

    public LocationClientOption getDIYoption() {
        if (DIYoption == null) {
            DIYoption = new LocationClientOption();
            DIYoption.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            DIYoption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            DIYoption.setScanSpan(5 * 1000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            DIYoption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            DIYoption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            DIYoption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
            DIYoption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            DIYoption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            DIYoption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            DIYoption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            DIYoption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            DIYoption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return DIYoption;
    }

    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            mOption.setScanSpan(5 * 1000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
            mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            mOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            mOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            mOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return mOption;
    }

    public void start() {
        if (client != null && !client.isStarted()) {
            client.start();
            Mlog.d("cai.location", "开启定位服务");
        }
    }

    public void stop() {
        if (client != null && client.isStarted()) {
            client.stop();
            Mlog.d("cai.location", "关闭定位服务");
        }
    }


    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
//            Mlog.d("cai.location", "定位中.....");
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
//                Mlog.d("cai.location", " lat = " + location.getLatitude());
                LocationInfo.getInstance().setLocation(location);
                Intent UpdateLoc = new Intent(Values.IF_LOC_UPDATE_MAP + getPackageName());
                sendBroadcast(UpdateLoc);
            } else {
                BDLocation bdLocation = new BDLocation();
                bdLocation.setAddrStr("北京海淀长春桥");
                //116.30771,39.966355
                bdLocation.setLatitude(39.966355);
                bdLocation.setLongitude(116.30771);
                LocationInfo.getInstance().setLocation(bdLocation);

//                Intent UpdateLoc = new Intent(Values.IF_LOC_UPDATE1 + ConfigFiles.getConfigFiles().getAPPKEY());
//                sendBroadcast(UpdateLoc);

                Intent UpdateLoc = new Intent(Values.IF_LOC_UPDATE_MAP + getPackageName());
                sendBroadcast(UpdateLoc);
            }
        }
    };
}
