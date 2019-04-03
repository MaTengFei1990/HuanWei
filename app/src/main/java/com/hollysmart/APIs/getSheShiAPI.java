package com.hollysmart.APIs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.Utils;
import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.SheShiInfo;
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
 * Created by sunpengfei on 2017/8/8.
 */

public class getSheShiAPI implements INetModel {
    private SheShiAPIIF sheShiAPIIF;
    private String Page;
    private String Count;
    private String Name;
    private String AreaType;
    private UserInfo userInfo;

    public getSheShiAPI(UserInfo userInfo, String name, String areaType, String Count, String Page, SheShiAPIIF sheShiAPIIF) {
        this.sheShiAPIIF = sheShiAPIIF;
        this.userInfo = userInfo;
        this.Name = name;
        this.Count = Count;
        this.Page = Page;
        AreaType = areaType;
    }

    @Override
    public void request() {
        String url = Values.URL + "facilities/hwFacilities/getList";
        GetBuilder getBuilder = OkHttpUtils.get().url(url)
                .addHeader("Authorization", userInfo.getAccess_token())
                .addParams("pageNo", Page)
                .addParams("pageSize", Count);
        if (!Utils.isEmpty(Name)) {
            getBuilder.addParams("facilityName", Name);
        }
        if (!Utils.isEmpty(AreaType)) {
            getBuilder.addParams("areaType", AreaType);
        }
        getBuilder.build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                    Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                    List<SheShiInfo> sheShiList = mGson.fromJson(object.getString("list"), new TypeToken<List<SheShiInfo>>() {
                    }.getType());
                    sheShiAPIIF.getSheShiResult(sheShiList);
                    Mlog.d("设施lsit.size===" + sheShiList.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public interface SheShiAPIIF {
        void getSheShiResult(List<SheShiInfo> sheShiList);

    }
}
