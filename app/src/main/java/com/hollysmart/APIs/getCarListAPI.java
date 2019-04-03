package com.hollysmart.APIs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.Utils;
import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.CarInfo;
import com.hollysmart.values.HWStaticInfo;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by sunpengfei on 2017/8/7.
 */

public class getCarListAPI implements INetModel {
    private String Page;
    private String Count;
    private String carNum;
    private String areaType;
    private UserInfo userInfo;

    private CarListIF carListIF;

    public getCarListAPI(UserInfo userInfo, String page, String count, String carNum, String areaType, CarListIF carListIF) {
        this.userInfo = userInfo;
        Page = page;
        Count = count;
        this.carNum = carNum;
        this.areaType = areaType;
        this.carListIF = carListIF;
    }

    public getCarListAPI(UserInfo userInfo, String page, String count, CarListIF carListIF) {
        this.userInfo = userInfo;
        Page = page;
        Count = count;
        this.carListIF = carListIF;
    }

    @Override
    public void request() {
        String url = Values.URL + "cars/hwCars/getList";
        GetBuilder getBuilder = OkHttpUtils.get().url(url)
                .addHeader("Authorization", userInfo.getAccess_token())
                .addParams("pageNo", Page)
                .addParams("pageSize", Count);

        if (!Utils.isEmpty(carNum)) {
            getBuilder.addParams("CarNum", carNum);
        }
        if (!Utils.isEmpty(areaType)) {
            getBuilder.addParams("areaType", areaType);
        }
        getBuilder.build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Mlog.d("车辆信息" + response);
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                    Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                    List<CarInfo> sheShiList = mGson.fromJson(object.getString("list"), new TypeToken<List<CarInfo>>() {
                    }.getType());
                    carListIF.getResult(sheShiList);
                    Mlog.d("车辆信息lsit.size===" + sheShiList.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public interface CarListIF {
        void getResult(List<CarInfo> sheShiList);
    }
}
