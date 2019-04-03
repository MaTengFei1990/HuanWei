package com.hollysmart.APIs;

import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.HWStaticInfo;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import okhttp3.Call;

/**
 * Created by sunpengfei on 2017/8/8.
 */

public class TongJiAPI implements INetModel{
    private TongJiAPIIF tongJiAPIIF;
    private String type;//facility,cars
    private UserInfo userInfo;

    public TongJiAPI(UserInfo userInfo, String type, TongJiAPIIF tongJiAPIIF) {
        this.userInfo=userInfo;
        this.tongJiAPIIF = tongJiAPIIF;
        this.type = type;
    }

    @Override
    public void request() {
        String url = Values.URL + "statistic/hwStatistic/" + type;
        OkHttpUtils.get().url(url)
                .addHeader("Authorization", userInfo.getAccess_token())
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                tongJiAPIIF.getReuslt(response);
            }
        });


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Values.URL )
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        GitHubService gitHubService = retrofit.create(GitHubService.class);
//        Call<List<HWStaticInfo>> listCall = gitHubService.listRepos( userInfo.getAccess_token(),type);
//
//        listCall.enqueue(new Callback<List<HWStaticInfo>>() {
//            @Override
//            public void onResponse(Call<List<HWStaticInfo>> call, Response<List<HWStaticInfo>> response) {
//                tongJiAPIIF.getReuslt(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<HWStaticInfo>> call, Throwable t) {
//
//            }
//        });
    }

//    public interface GitHubService {
//        @GET("statistic/hwStatistic/{type}")
//        Call<List<HWStaticInfo>> listRepos(
//                @Header("Authorization") String token,
//                @Path("type") String type);
//    }

    public interface TongJiAPIIF {
        void getReuslt(String msg);
    }
//    public interface TongJiAPIIF {
//        void getReuslt(List<HWStaticInfo> msg);
//    }


}
