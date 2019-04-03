package com.hollysmart.huanwei;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.CeSuoInfo;

public class CeSuoDetailActivity extends CaiSlidingBackActivity implements View.OnClickListener {

    private TextView tv_name;
    private TextView tv_areaType;
    private TextView tv_address;
    private TextView tv_coverdArea;
    private TextView tv_statistical;
    private TextView tv_propertyRight;
    private TextView tv_manPitnums;
    private TextView tv_womanPitnums;
    private TextView tv_otherPitnums;
    private TextView tv_urinalType;
    private TextView tv_streetTown;
    private TextView tv_fecalTreatment;
    private TextView tv_cleaningUnit;
    private TextView tv_constructMode;
    private TextView tv_toiletNums;
    private TextView tv_isRuralToilet;
    private TextView tv_category;
    private TextView tv_isNewruralToilet;
    private TextView tv_washType;
    private TextView tv_deodorizationType;
    private TextView tv_serviceTime;
    private TextView tv_takeoverTime;
    private TextView tv_indexstatus;
    private TextView tv_invest;
    private TextView tv_barrierFreeFacility;
    private TextView tv_disabledRoom;
    private TextView tv_manageRoom;
    private TextView tv_washroom;
    private TextView tv_summerStarttime;
    private TextView tv_summerEndtime;
    private TextView tv_winterStarttime;
    private TextView tv_winterEndtime;
    private TextView tv_urinalNums;


    @Override

    public int layoutResID() {
        return R.layout.activity_ce_suo_detail;
    }

    @Override
    public void findView() {


        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_areaType = (TextView) findViewById(R.id.tv_areaType);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_coverdArea = (TextView) findViewById(R.id.tv_coverdArea);
        tv_statistical = (TextView) findViewById(R.id.tv_statistical);
        tv_propertyRight = (TextView) findViewById(R.id.tv_propertyRight);
        tv_manPitnums = (TextView) findViewById(R.id.tv_manPitnums);
        tv_womanPitnums = (TextView) findViewById(R.id.tv_womanPitnums);
        tv_otherPitnums = (TextView) findViewById(R.id.tv_otherPitnums);
        tv_urinalType = (TextView) findViewById(R.id.tv_urinalType);
        tv_streetTown = (TextView) findViewById(R.id.tv_streetTown);
        tv_fecalTreatment = (TextView) findViewById(R.id.tv_fecalTreatment);
        tv_cleaningUnit = (TextView) findViewById(R.id.tv_cleaningUnit);
        tv_constructMode = (TextView) findViewById(R.id.tv_constructMode);
        tv_toiletNums = (TextView) findViewById(R.id.tv_toiletNums);
        tv_isRuralToilet = (TextView) findViewById(R.id.tv_isRuralToilet);
        tv_category = (TextView) findViewById(R.id.tv_category);
        tv_isNewruralToilet = (TextView) findViewById(R.id.tv_isNewruralToilet);
        tv_washType = (TextView) findViewById(R.id.tv_washType);
        tv_deodorizationType = (TextView) findViewById(R.id.tv_deodorizationType);
        tv_serviceTime = (TextView) findViewById(R.id.tv_serviceTime);
        tv_takeoverTime = (TextView) findViewById(R.id.tv_takeoverTime);
        tv_indexstatus = (TextView) findViewById(R.id.tv_indexstatus);
        tv_invest = (TextView) findViewById(R.id.tv_invest);
        tv_barrierFreeFacility = (TextView) findViewById(R.id.tv_barrierFreeFacility);
        tv_disabledRoom = (TextView) findViewById(R.id.tv_disabledRoom);
        tv_manageRoom = (TextView) findViewById(R.id.tv_manageRoom);
        tv_washroom = (TextView) findViewById(R.id.tv_washroom);
        tv_summerStarttime = (TextView) findViewById(R.id.tv_summerStarttime);
        tv_summerEndtime = (TextView) findViewById(R.id.tv_summerEndtime);
        tv_winterStarttime = (TextView) findViewById(R.id.tv_winterStarttime);
        tv_winterEndtime = (TextView) findViewById(R.id.tv_winterEndtime);
        tv_urinalNums = (TextView) findViewById(R.id.tv_urinalNums);


        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);
    }

    @Override
    public void init() {
        CeSuoInfo  value = (CeSuoInfo) getIntent().getSerializableExtra("value");
        if (!Utils.isEmpty(value.getName())) {
            tv_name.setText(value.getName());
        }
        if (!Utils.isEmpty(value.getAreaType())) {
            tv_areaType.setText(value.getName());
        }
        if (!Utils.isEmpty(value.getAddress())) {
            tv_address.setText(value.getAddress());
        }
        if (!Utils.isEmpty(value.getCoverdArea()+"")) {
            tv_coverdArea.setText(value.getCoverdArea()+"");
        }
//        if (!Utils.isEmpty(value.getsta)) {
//            tv_statistical.setText(value.getName());
//        }
        if (!Utils.isEmpty(value.getPropertyRight())) {
            tv_propertyRight.setText(value.getPropertyRight());
        }
        if (!Utils.isEmpty(value.getManPitnums()+"")) {
            tv_manPitnums.setText(value.getManPitnums()+"");
        }
        if (!Utils.isEmpty(value.getWomanPitnums()+"")) {
            tv_womanPitnums.setText(value.getWomanPitnums()+"");
        }
        if (!Utils.isEmpty(value.getOtherPitnums()+"")) {
            tv_otherPitnums.setText(value.getOtherPitnums()+"");
        }
        if (!Utils.isEmpty(value.getUrinalType())) {
            tv_urinalType.setText(value.getUrinalType());
        }
        if (!Utils.isEmpty(value.getStreetTown())) {
            tv_streetTown.setText(value.getStreetTown());
        }
        if (!Utils.isEmpty(value.getFecalTreatment())) {
            tv_fecalTreatment.setText(value.getFecalTreatment());
        }
        if (!Utils.isEmpty(value.getCleaningUnit())) {
            tv_cleaningUnit.setText(value.getCleaningUnit());
        }
        if (!Utils.isEmpty(value.getConstructMode())) {
            tv_constructMode.setText(value.getConstructMode());
        }
        if (!Utils.isEmpty(value.getToiletNums())) {
            tv_toiletNums.setText(value.getToiletNums());
        }
        if (!Utils.isEmpty(value.getIsRuralToilet())) {
            tv_isRuralToilet.setText(value.getIsRuralToilet());
        }
        if (!Utils.isEmpty(value.getCategory())) {
            tv_category.setText(value.getCategory());
        }
        if (!Utils.isEmpty(value.getIsNewruralToilet())) {
            tv_isNewruralToilet.setText(value.getIsNewruralToilet());
        }
        if (!Utils.isEmpty(value.getWashType())) {
            tv_washType.setText(value.getWashType());
        }
        if (!Utils.isEmpty(value.getDeodorizationType())) {
            tv_deodorizationType.setText(value.getDeodorizationType());
        }
        if (!Utils.isEmpty(value.getServiceTime())) {
            tv_serviceTime.setText(value.getServiceTime());
        }
        if (!Utils.isEmpty(value.getTakeoverTime())) {
            tv_takeoverTime.setText(value.getTakeoverTime());
        }
        if (!Utils.isEmpty(value.getIndexstatus())) {
            tv_indexstatus.setText(value.getIndexstatus());
        }
        if (!Utils.isEmpty(value.getInvest()+"")) {
            tv_invest.setText(value.getInvest()+"");
        }
        if (!Utils.isEmpty(value.getBarrierFreeFacility())) {
            tv_barrierFreeFacility.setText(value.getBarrierFreeFacility());
        }
        if (!Utils.isEmpty(value.getDisabledRoom())) {
            tv_disabledRoom.setText(value.getDisabledRoom());
        }
        if (!Utils.isEmpty(value.getManageRoom())) {
            tv_manageRoom.setText(value.getManageRoom());
        }
        if (!Utils.isEmpty(value.getWashroom())) {
            tv_washroom.setText(value.getWashroom());
        }
        if (!Utils.isEmpty(value.getSummerStarttime())) {
            tv_summerStarttime.setText(value.getSummerStarttime());
        }
        if (!Utils.isEmpty(value.getSummerEndtime())) {
            tv_summerEndtime.setText(value.getSummerEndtime());
        }
        if (!Utils.isEmpty(value.getWinterStarttime())) {
            tv_winterStarttime.setText(value.getWinterStarttime());
        }
        if (!Utils.isEmpty(value.getWinterEndtime())) {
            tv_winterEndtime.setText(value.getWinterEndtime());
        }
        if (!Utils.isEmpty(value.getUrinalNums()+"")) {
            tv_urinalNums.setText(value.getUrinalNums()+"");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }

    }
}
