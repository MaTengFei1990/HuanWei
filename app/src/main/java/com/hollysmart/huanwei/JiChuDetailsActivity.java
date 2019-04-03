package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.APIs.AreaAPI;
import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.tools.UtilsDialog;
import com.hollysmart.values.DirctroyBean;
import com.hollysmart.values.HuanWeiInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JiChuDetailsActivity extends CaiSlidingBackActivity implements View.OnClickListener,AreaAPI.AreaAPIF {
    private TextView tv_quxian;
    private TextView tv_leiXing;
    List<HuanWeiInfo> arealist;
    List<HuanWeiInfo> typeList;
    private Context mContext;

    @Override
    public int layoutResID() {

        return R.layout.activity_ji_chu_tong_ji;
    }

    @Override
    public void findView() {
        mContext=this;
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        tv_leiXing = (TextView) findViewById(R.id.tv_leiXing);
        tv_quxian = (TextView) findViewById(R.id.tv_quxian);
        tv_leiXing.setOnClickListener(this);
        tv_quxian.setOnClickListener(this);

    }

    @Override
    public void init() {
        new AreaAPI("area_typ",this).request();
        new AreaAPI("facility_type",this).request();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_leiXing:
                new UtilsDialog().getData(mContext, null,null, new UtilsDialog.DataCallBack() {
                    @Override
                    public void callBack(DirctroyBean bean) {
                        bean.getLabel();
                    }
                });
                break;
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_quxian:
                new UtilsDialog().getData(mContext, null,null, new UtilsDialog.DataCallBack() {
                    @Override
                    public void callBack(DirctroyBean bean) {
                        bean.getLabel();
                    }
                });


                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void getAreaList(String msg) {
        Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        arealist = mGson.fromJson(msg, new TypeToken<List<HuanWeiInfo>>() {
        }.getType());
    }

    @Override
    public void gettypeList(String msg) {
        try {
            JSONObject object = new JSONObject(msg);
            if (!object.isNull("result")) {
                Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                typeList = mGson.fromJson(object.getString("units"), new TypeToken<List<HuanWeiInfo>>() {
                }.getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCarList(String msg) {

    }
}
