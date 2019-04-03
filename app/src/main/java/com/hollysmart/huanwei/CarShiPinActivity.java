package com.hollysmart.huanwei;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.CarInfo;

import java.util.HashMap;

public class CarShiPinActivity extends CaiSlidingBackActivity implements View.OnClickListener{

    private final String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    public int layoutResID() {
        return R.layout.activity_car_shi_pin;
    }

    private TextView tv_chepaiHao;
    private TextView tv_quyu;
    private TextView tv_cartype1;
    private TextView tv_cartype2;
    private TextView tv_fenlei;
    private ImageView iv_qianfang;
    @Override
    public void findView() {
        tv_chepaiHao = (TextView) findViewById(R.id.tv_chepaiHao);
        tv_quyu = (TextView) findViewById(R.id.tv_quyu);
        tv_cartype1 = (TextView) findViewById(R.id.tv_cartype1);
        tv_cartype2 = (TextView) findViewById(R.id.tv_cartype2);
        tv_fenlei = (TextView) findViewById(R.id.tv_fenlei);
        iv_qianfang = (ImageView) findViewById(R.id.iv_qianfang);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.rl_qian).setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);

    }

    @Override
    public void init() {
        CarInfo value = (CarInfo)getIntent().getSerializableExtra("value");
        if (!Utils.isEmpty(value.getCarNum())) {
            tv_chepaiHao.setText(value.getCarNum());

        }
        if (!Utils.isEmpty(value.getAreaType())) {
            tv_quyu.setText(value.getAreaType());

        }
        if (!Utils.isEmpty(value.getCarType1())) {
            tv_cartype1.setText(value.getCarType1());

        }
        if (!Utils.isEmpty(value.getCarType2())) {
            tv_cartype2.setText(value.getCarType2());

        }
        if (!Utils.isEmpty(value.getStaTypes())) {
            tv_fenlei.setText(value.getStaTypes());

        }
        new DetailInfoTask(videoUrl).execute();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.rl_qian:
                Intent intent = new Intent(this, VideoPlayActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
        }

    }





    private class DetailInfoTask extends AsyncTask<Void, Void, Bitmap> {
        String Url;
        public DetailInfoTask(String Url) {
            this.Url = Url;
        }

        @Override
        protected Bitmap doInBackground(Void... bimap) {
                Bitmap bitmap = null;

                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                try {
                    //根据url获取缩略图
                    retriever.setDataSource(Url, new HashMap());
                    //获得第一帧图片
                    bitmap = retriever.getFrameAtTime();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } finally {
                    retriever.release();
                }
                return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            iv_qianfang.setImageBitmap(bitmap);
        }
    }






}
