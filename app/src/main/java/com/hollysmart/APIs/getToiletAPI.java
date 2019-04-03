package com.hollysmart.APIs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.Utils;
import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.CeSuoInfo;
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

public class getToiletAPI implements INetModel {
    private UserInfo userInfo;
    private String AreaType;
    private ToiletAPIIF toiletAPIIF;
    private String Page;
    private String Count;

    public getToiletAPI(UserInfo userInfo, String areaType, String Count, String Page, ToiletAPIIF toiletAPIIF) {
        this.userInfo = userInfo;
        this.AreaType = areaType;
        this.Page = Page;
        this.Count = Count;
        this.toiletAPIIF = toiletAPIIF;
    }

    @Override
    public void request() {
        String url = Values.URL + "toilet/hwToilet/getList";
        GetBuilder getBuilder = OkHttpUtils.get().url(url)
                .addHeader("Authorization", userInfo.getAccess_token())
                .addParams("pageNo", Page)
                .addParams("pageSize", Count);
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
                    List<CeSuoInfo> toiletList = mGson.fromJson(object.getString("list"), new TypeToken<List<CeSuoInfo>>() {
                    }.getType());
                    toiletAPIIF.getToiletList(toiletList);
                    Mlog.d("厕所lsit.size===" + toiletList.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }


    public interface ToiletAPIIF {
        void getToiletList(List<CeSuoInfo> list);

    }
}

