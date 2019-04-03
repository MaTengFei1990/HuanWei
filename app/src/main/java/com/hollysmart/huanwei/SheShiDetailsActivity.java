package com.hollysmart.huanwei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hollysmart.utils.Utils;
import com.hollysmart.values.SheShiInfo;

public class SheShiDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_facilityName;
    private TextView tv_areaType;
    private TextView tv_useTime;
    private TextView tv_receivingTime;
    private TextView tv_streetTowntype;
    private TextView tv_investment;
    private TextView tv_statistical;
    private TextView tv_propertyrightsNuit;
    private TextView tv_homeworkUnit;
    private TextView tv_buildingSize;
    private TextView tv_facilityNumber;
    private TextView tv_facilityType;
    private TextView tv_address;
    private TextView tv_indicatorsState;
    private TextView tv_containerNumber;
    private TextView tv_maintainNumber;
    private TextView tv_restNumber;
    private TextView tv_maintainArea;
    private TextView tv_mangeAbility;
    private TextView tv_manageWay;
    private TextView tv_garbageNumber;
    private TextView tv_saltpoolSize;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_shi_details);
        findvew();
        intdata();
    }

    private void intdata() {
        findViewById(R.id.tv_home).setOnClickListener(this);
        SheShiInfo value = (SheShiInfo) getIntent().getSerializableExtra("value");

        if (!Utils.isEmpty(value.getFacilityName())) {

            tv_facilityName.setText(value.getFacilityName());
        }
        if (!Utils.isEmpty(value.getAreaType())) {

            tv_areaType.setText(value.getAreaType());
        }
        if (!Utils.isEmpty(value.getUseTime())) {

            tv_useTime.setText(value.getUseTime());
        }
        if (!Utils.isEmpty(value.getReceivingTime())) {

            tv_receivingTime.setText(value.getReceivingTime());
        }
        if (!Utils.isEmpty(value.getStreetTown())) {

            tv_streetTowntype.setText(value.getStreetTown());
        }

        if (!Utils.isEmpty(value.getInvestment()+"")) {
            tv_investment.setText(value.getInvestment()+"");
        }
        if (!Utils.isEmpty(value.getStatistical())) {
            tv_statistical.setText(value.getStatistical());
        }
        if (!Utils.isEmpty(value.getPropertyrightsNuit())) {
            tv_propertyrightsNuit.setText(value.getPropertyrightsNuit());
        }
        if (!Utils.isEmpty(value.getHomeworkUnit())) {
            tv_homeworkUnit.setText(value.getHomeworkUnit());
        }
        if (!Utils.isEmpty(value.getBuildingSize()+"")) {
            tv_buildingSize.setText(value.getBuildingSize()+"");
        }
        if (!Utils.isEmpty(value.getFacilityNumber())) {
            tv_facilityNumber.setText(value.getFacilityNumber());
        }
        if (!Utils.isEmpty(value.getFacilityType())) {
            tv_facilityType.setText(value.getFacilityType());
        }
        if (!Utils.isEmpty(value.getAddress())) {
            tv_address.setText(value.getAddress());
        }
        if (!Utils.isEmpty(value.getIndicatorsState())) {
            tv_indicatorsState.setText(value.getIndicatorsState());
        }
        if (!Utils.isEmpty(value.getContainerNumber()+"")) {
            tv_containerNumber.setText(value.getContainerNumber()+"");
        }
        if (!Utils.isEmpty(value.getMaintainNumber()+"")) {
            tv_maintainNumber.setText(value.getMaintainNumber()+"");
        }
        if (!Utils.isEmpty(value.getRestNumber()+"")) {
            tv_restNumber.setText(value.getRestNumber()+"");
        }
        if (!Utils.isEmpty(value.getMaintainArea()+"")) {
            tv_maintainArea.setText(value.getMaintainArea()+"");
        }
        if (!Utils.isEmpty(value.getMangeAbility()+"")) {
            tv_mangeAbility.setText(value.getMangeAbility()+"");
        }
        if (!Utils.isEmpty(value.getManageWay())) {
            tv_manageWay.setText(value.getManageWay());
        }
        if (!Utils.isEmpty(value.getGarbageNumber())) {
            tv_garbageNumber.setText(value.getGarbageNumber());
        }
        if (!Utils.isEmpty(value.getSaltpoolSize()+"")) {
            tv_saltpoolSize.setText(value.getSaltpoolSize()+"");
        }
    }

    private void findvew() {

        tv_facilityName = (TextView) findViewById(R.id.tv_facilityName);
        tv_areaType = (TextView) findViewById(R.id.tv_areaType);
        tv_useTime = (TextView) findViewById(R.id.tv_useTime);
        tv_receivingTime = (TextView) findViewById(R.id.tv_receivingTime);
        tv_streetTowntype = (TextView) findViewById(R.id.tv_streetTown);
        tv_investment = (TextView) findViewById(R.id.tv_investment);
        tv_statistical = (TextView) findViewById(R.id.tv_statistical);
        tv_propertyrightsNuit = (TextView) findViewById(R.id.tv_propertyrightsNuit);
        tv_homeworkUnit = (TextView) findViewById(R.id.tv_homeworkUnit);
        tv_buildingSize = (TextView) findViewById(R.id.tv_buildingSize);
        tv_facilityNumber = (TextView) findViewById(R.id.tv_facilityNumber);
        tv_facilityType = (TextView) findViewById(R.id.tv_facilityType);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_indicatorsState = (TextView) findViewById(R.id.tv_indicatorsState);
        tv_containerNumber = (TextView) findViewById(R.id.tv_containerNumber);
        tv_maintainNumber = (TextView) findViewById(R.id.tv_maintainNumber);
        tv_restNumber = (TextView) findViewById(R.id.tv_restNumber);
        tv_maintainArea = (TextView) findViewById(R.id.tv_maintainArea);
        tv_mangeAbility = (TextView) findViewById(R.id.tv_mangeAbility);
        tv_manageWay = (TextView) findViewById(R.id.tv_manageWay);
        tv_garbageNumber = (TextView) findViewById(R.id.tv_garbageNumber);
        tv_saltpoolSize = (TextView) findViewById(R.id.tv_saltpoolSize);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
        }
    }
}
