package com.hollysmart.values;

import android.os.Environment;

public class Values {
//    public static final String ServiceIp = "http://210.73.67.121";
//    public static final String URL = ServiceIp + "/hwxt_mobile/a/";
//    public static final String LOGINURL = ServiceIp + "/hwxt/a/login";
//    public static final String SERVICE_URL3 = ServiceIp + "/hwxt_mobile/oauth/token";

    public static final String URL = "http://210.73.67.121/hwxt_mobile/a/";//之前一直用的
//    public static final String URL = "http://10.2.8.241:8080/hwxt_mobile/a/";//梁工
    public static final String LOGINURL = "http:/210.73.67.121/hwxt/a/login";
    public static final String SERVICE_URL3 = "http://210.73.67.121/hwxt_mobile/oauth/token";

    public static double DS_LAT = 0;
    public static double DS_LNG = 0;

    public static String QRCODEURL = "qrcode";


    public static final String SDCARD_ROOT = "hollysmart";
    public static final String SDCARD_PIC = "pic";
    public static final String SDCARD_PIC_WODE = SDCARD_PIC + "/wode";//我的

    public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().toString() + "/" + SDCARD_ROOT + "/";

    public static String SDCARD_FILE(String FILENAME) {
        return SDCARD_DIR + FILENAME + "/";
    }

    public static final String CACHE_USER = "user";
    public static final String CACHE_USERINFO = "userInfo";

    // 注册广播
    public static final String IF_LOC_UPDATE_MAP = "com.hollysmary.locupdate.map.";
    public static final String EXITLOGIN = "com.hollysmart.ExitApp";

    //景区注意更改
    public static int W = 3600;
    public static int H = 6400;


}
