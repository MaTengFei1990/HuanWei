package com.hollysmart.APIs;

import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.Values;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by sunpengfei on 2017/8/1.
 */

public class AreaAPI  implements INetModel {
    private String type;
    private AreaAPIF areaAPIF;

    public AreaAPI(String type, AreaAPIF areaAPIF) {
        this.type = type;
        this.areaAPIF = areaAPIF;
    }

    @Override
    public void request() {
        String Url = Values.URL + "sys/dict/listData";
        OkHttpUtils.get().url(Url)
                .addParams("type",type)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (type.equals("area_typ")) {
                    areaAPIF.getAreaList(response);
                } else if (type.equals("car_type1")) {
                    areaAPIF.getCarList(response);
                } else {
                    areaAPIF.gettypeList(response);
                }
            }
        });
    }


    public interface AreaAPIF{
        void getAreaList( String msg);
        void gettypeList( String msg);
        void getCarList( String msg);
    }
}
