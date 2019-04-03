package com.hollysmart.huanwei;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.CarInfo;

public class CarDetailActivity extends CaiSlidingBackActivity implements View.OnClickListener {


    @Override
    public int layoutResID() {
        return R.layout.activity_car_detail;
    }
    private TextView tv_carNum;
    private TextView tv_carNumInunit;
    private TextView tv_staTypes;
    private TextView tv_plateModel;
    private TextView tv_fuelType;
    private TextView tv_loadsPeople;


    private TextView tv_emissions;
    private TextView tv_regTime;
    private TextView tv_receiveTime;
    private TextView tv_carUse;
    private TextView tv_carSim;
    private TextView tv_vehiclUnits;
    private TextView tv_office;
    private TextView tv_areaType;
    private TextView tv_carType1;
    private TextView tv_vehiclDepartment;
    private TextView tv_vehiclInUnits;
    private TextView tv_carType2;
    private TextView tv_vinNum;
    private TextView tv_onlineTime;
    private TextView tv_indicators;
    @Override
    public void findView() {
        tv_carNum = (TextView) findViewById(R.id.tv_carNum);
        tv_carNumInunit = (TextView) findViewById(R.id.tv_carNumInunit);
        tv_staTypes = (TextView) findViewById(R.id.tv_staTypes);
        tv_plateModel = (TextView) findViewById(R.id.tv_plateModel);
        tv_emissions = (TextView) findViewById(R.id.tv_emissions);

        tv_fuelType = (TextView) findViewById(R.id.tv_fuelType);
        tv_loadsPeople = (TextView) findViewById(R.id.tv_loadsPeople);
        tv_indicators = (TextView) findViewById(R.id.tv_indicators);
        tv_regTime = (TextView) findViewById(R.id.tv_regTime);
        tv_receiveTime = (TextView) findViewById(R.id.tv_receiveTime);
        tv_carUse = (TextView) findViewById(R.id.tv_carUse);
        tv_carSim = (TextView) findViewById(R.id.tv_carSim);
        tv_vehiclUnits = (TextView) findViewById(R.id.tv_vehiclUnits);
        tv_office = (TextView) findViewById(R.id.tv_office);
        tv_areaType = (TextView) findViewById(R.id.tv_areaType);
        tv_carType1 = (TextView) findViewById(R.id.tv_carType1);
        tv_vehiclDepartment = (TextView) findViewById(R.id.tv_vehiclDepartment);
        tv_vehiclInUnits = (TextView) findViewById(R.id.tv_vehiclInUnits);
        tv_carType2 = (TextView) findViewById(R.id.tv_carType2);
        tv_vinNum = (TextView) findViewById(R.id.tv_vinNum);
        tv_onlineTime = (TextView) findViewById(R.id.tv_onlineTime);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);

    }

    @Override
    public void init() {
        CarInfo value = (CarInfo) getIntent().getSerializableExtra("value");

        if (!Utils.isEmpty(value.getCarNum())) {
            tv_carNum.setText(value.getCarNum());
        }
        if (!Utils.isEmpty(value.getCarNumInunit())) {
            tv_carNumInunit.setText(value.getCarNumInunit());
        }
        if (!Utils.isEmpty(value.getStaTypes())) {
            tv_staTypes.setText(value.getStaTypes());
        }
        if (!Utils.isEmpty(value.getPlateModel())) {
            tv_plateModel.setText(value.getPlateModel());
        }
        if (!Utils.isEmpty(value.getEmissions())) {
            tv_emissions.setText(value.getEmissions());
        }
        if (!Utils.isEmpty(value.getFuelType())) {
            tv_fuelType.setText(value.getFuelType());
        }

        if (!Utils.isEmpty(value.getIndicators())) {
            tv_indicators.setText(value.getIndicators());
        }
        if (!Utils.isEmpty(value.getRegTime())) {
            tv_regTime.setText(value.getRegTime());
        }
        if (!Utils.isEmpty(value.getReceiveTime())) {
            tv_receiveTime.setText(value.getReceiveTime());
        }
        if (!Utils.isEmpty(value.getCarUse())) {
            tv_carUse.setText(value.getCarUse());
        }
        if (!Utils.isEmpty(value.getCarSim())) {
            tv_carSim.setText(value.getCarSim());
        }
        if (!Utils.isEmpty(value.getVehiclUnits())) {
            tv_vehiclUnits.setText(value.getVehiclUnits());
        }
        if (value.getOffice() != null) {
            if (!Utils.isEmpty(value.getOffice().getName())) {
                tv_office.setText(value.getOffice().getName());
            }

        }
        if (!Utils.isEmpty(value.getAreaType())) {
            tv_areaType.setText(value.getAreaType());
        }
        if (!Utils.isEmpty(value.getCarType1())) {
            tv_carType1.setText(value.getCarType1());
        }
        if (!Utils.isEmpty(value.getVehiclDepartment())) {
            tv_vehiclDepartment.setText(value.getVehiclDepartment());
        }
        if (!Utils.isEmpty(value.getVehiclInUnits())) {
            tv_vehiclInUnits.setText(value.getVehiclInUnits());
        }
        if (!Utils.isEmpty(value.getCarType2())) {
            tv_carType2.setText(value.getCarType2());
        }
        if (!Utils.isEmpty(value.getVinNum())) {
            tv_vinNum.setText(value.getVinNum());
        }
        if (!Utils.isEmpty(value.getOnlineTime())) {
            tv_onlineTime.setText(value.getOnlineTime());
        }
        if (!Utils.isEmpty(value.getLoadsPeople() + "")) {
            tv_loadsPeople.setText(value.getLoadsPeople() + "");
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
