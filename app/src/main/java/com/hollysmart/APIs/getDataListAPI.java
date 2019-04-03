package com.hollysmart.APIs;

import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by sunpengfei on 2017/8/7.
 */

public class getDataListAPI  implements INetModel{
    private String type;
    private DataListIF dataListIF;
    private UserInfo userInfo;

    public getDataListAPI(UserInfo userInfo,String type, DataListIF dataListIF) {
        this.userInfo=userInfo;
        this.type = type;
        this.dataListIF = dataListIF;
    }

    @Override
    public void request() {
        String url = Values.URL+"sys/dict/listData?";
        OkHttpUtils.get().url(url)
                .addHeader("Authorization", userInfo.getAccess_token())
                .addParams("type",type)
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(String response, int id) {

                dataListIF.getResult(response);
            }
        });

    }

    public interface DataListIF {
        void  getResult(String str);
    }
}
