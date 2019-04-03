package com.hollysmart.APIs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.GpsInfo;
import com.hollysmart.values.RangeInfo;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.hollysmart.values.hwAlarmMapInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by sunpengfei on 2017/8/9.
 */

public class GetGPSAPI implements INetModel {
    private UserInfo userInfo;
    private String plateno;
    private String sendTime;
    private GetGPSAPIIF getGPSAPIIF;
    private long startime;
    private long endime;

    public GetGPSAPI(UserInfo userInfo, String plateno, String sendTime, GetGPSAPIIF getGPSAPIIF) {
        this.userInfo = userInfo;
        this.plateno = plateno;
        this.sendTime = sendTime;
        Mlog.d("-------------GetGPSAPI--------------");
        Mlog.d("plateno==" + plateno);
        Mlog.d("sendTime==" + sendTime);
        Mlog.d("userInfo.getAccess_token==" + userInfo.getAccess_token());
        startime = System.currentTimeMillis();
        this.getGPSAPIIF = getGPSAPIIF;
    }

    @Override
    public void request() {
        String url = Values.URL + "gpsinfo/gpsinfo/findMyList";
        OkHttpUtils.get().url(url)
                .addHeader("Authorization", userInfo.getAccess_token())
                .addParams("plateno", plateno)
                .addParams("sendTime", sendTime)
                .build().execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {
                e.fillInStackTrace();
            }

            @Override
            public void onResponse(String response, int id) {

                Mlog.d("GetGPSAPI.response===" + response);
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                    Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                    List<GpsInfo> gpsInfoList = mGson.fromJson(object.getString("list"), new TypeToken<List<GpsInfo>>() {
                    }.getType());
                    endime = System.currentTimeMillis();
                    Mlog.d("gettime===" + (endime - startime));

                    getGPSAPIIF.GetResult(gpsInfoList,null,null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public interface GetGPSAPIIF {
        void GetResult(List<GpsInfo> gpsInfoList, List<RangeInfo> RangeList, List<hwAlarmMapInfo> hwAlarmMapList);
    }

}

