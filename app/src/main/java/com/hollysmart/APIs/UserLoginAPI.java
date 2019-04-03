package com.hollysmart.APIs;

import com.hollysmart.utils.Mlog;
import com.hollysmart.utils.taskpool.INetModel;
import com.hollysmart.values.Values;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by cai on 2017/7/17. 登录接口
 */

public class UserLoginAPI implements INetModel {

    private String username;
    private String password;
    private LoginInfoIF loginInfoIF;

    public UserLoginAPI(String username, String password, LoginInfoIF loginInfoIF) {
        this.username = username;
        this.password = password;
        this.loginInfoIF = loginInfoIF;
    }

    @Override
    public void request() {
        String url = Values.SERVICE_URL3;
        OkHttpUtils.post().url(url)
                .addParams("client_id", "mobile-client")
                .addParams("client_secret", "mobile")
                .addParams("grant_type", "password")
                .addParams("scope", "read")
                .addParams("username", username)
                .addParams("password", password)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                loginInfoIF.loginResult(false, null);
            }

            @Override
            public void onResponse(String response, int id) {
                Mlog.d("登录result = " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (!object.isNull("access_token")) {
                        loginInfoIF.loginResult(true, object.getString("access_token"));
                    }else{
                        loginInfoIF.loginResult(false, null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public interface LoginInfoIF{
        void loginResult(boolean isOk, String msg);
    }

}
