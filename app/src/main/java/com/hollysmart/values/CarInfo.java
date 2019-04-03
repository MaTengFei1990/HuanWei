package com.hollysmart.values;

import java.io.Serializable;

/**
 * Created by sunpengfei on 2017/8/7.
 */

public class CarInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String carNum;              // 车牌号
    private String carNumInunit;        // 本单位车辆编号
    private String staTypes;            // 统计分类
    private String plateModel;          // 厂牌型号
    private String emissions;           // 尾气排放标准
    private String fuelType;            // 燃料情况

    private Long loadsPeople;           // 核载人数（人）
    private Long carLoads;              // 载质量（吨）

    private String indicators;          // 指标情况
    private String regTime;             // 注册登记日期

    private Double purchaseCost;        // 购置费用

    private String receiveTime;         // 接收时间
    private String carUse;              // 车辆用途
    private String carSim;              // 终端SIM卡号

    private String vehiclUnits;         // 车辆使用单位
    private String vehiclCompany;       // 车辆所属公司id
    private OfficeBean office;          //车辆所属公司
    private String areaType;            // 所属区域
    private String carType1;            // 车辆类别1
    private String vehiclDepartment;    // 车辆所属部门
    private String vehiclInUnits;       // 车辆所属单位
    private String carType2;            // 车辆类别2
    private String vinNum;              // VIN编码

    private String onlineTime;          // 最新在线时间
    private String note;                // 备注

    private String terminalModel;       // 车载终端型号
    private Double oilWear;             // 百公里油耗
    private String color;               // 车辆颜色
    //private Long seatNum;		        // 驾驶室座位数量
    private String carState;            // 车辆状态

    private String offlineTime;         // 上次离线时间
    private Double beginPurchaseCost;   // 开始 购置费用
    private Double endPurchaseCost;     // 结束 购置费用
    private Integer t;
    private boolean isAdd;
    private boolean setAllTag;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarNumInunit() {
        return carNumInunit;
    }

    public void setCarNumInunit(String carNumInunit) {
        this.carNumInunit = carNumInunit;
    }

    public String getStaTypes() {
        return staTypes;
    }

    public void setStaTypes(String staTypes) {
        this.staTypes = staTypes;
    }

    public String getPlateModel() {
        return plateModel;
    }

    public void setPlateModel(String plateModel) {
        this.plateModel = plateModel;
    }

    public String getEmissions() {
        return emissions;
    }

    public void setEmissions(String emissions) {
        this.emissions = emissions;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getLoadsPeople() {
        return loadsPeople;
    }

    public void setLoadsPeople(Long loadsPeople) {
        this.loadsPeople = loadsPeople;
    }

    public Long getCarLoads() {
        return carLoads;
    }

    public void setCarLoads(Long carLoads) {
        this.carLoads = carLoads;
    }

    public String getIndicators() {
        return indicators;
    }

    public void setIndicators(String indicators) {
        this.indicators = indicators;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getCarUse() {
        return carUse;
    }

    public void setCarUse(String carUse) {
        this.carUse = carUse;
    }

    public String getCarSim() {
        return carSim;
    }

    public void setCarSim(String carSim) {
        this.carSim = carSim;
    }

    public String getVehiclUnits() {
        return vehiclUnits;
    }

    public void setVehiclUnits(String vehiclUnits) {
        this.vehiclUnits = vehiclUnits;
    }

    public String getVehiclCompany() {
        return vehiclCompany;
    }

    public void setVehiclCompany(String vehiclCompany) {
        this.vehiclCompany = vehiclCompany;
    }

    public OfficeBean getOffice() {
        return office;
    }

    public void setOffice(OfficeBean office) {
        office = office;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getCarType1() {
        return carType1;
    }

    public void setCarType1(String carType1) {
        this.carType1 = carType1;
    }

    public String getVehiclDepartment() {
        return vehiclDepartment;
    }

    public void setVehiclDepartment(String vehiclDepartment) {
        this.vehiclDepartment = vehiclDepartment;
    }

    public String getVehiclInUnits() {
        return vehiclInUnits;
    }

    public void setVehiclInUnits(String vehiclInUnits) {
        this.vehiclInUnits = vehiclInUnits;
    }

    public String getCarType2() {
        return carType2;
    }

    public void setCarType2(String carType2) {
        this.carType2 = carType2;
    }

    public String getVinNum() {
        return vinNum;
    }

    public void setVinNum(String vinNum) {
        this.vinNum = vinNum;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public void setTerminalModel(String terminalModel) {
        this.terminalModel = terminalModel;
    }

    public Double getOilWear() {
        return oilWear;
    }

    public void setOilWear(Double oilWear) {
        this.oilWear = oilWear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Double getBeginPurchaseCost() {
        return beginPurchaseCost;
    }

    public void setBeginPurchaseCost(Double beginPurchaseCost) {
        this.beginPurchaseCost = beginPurchaseCost;
    }

    public Double getEndPurchaseCost() {
        return endPurchaseCost;
    }

    public void setEndPurchaseCost(Double endPurchaseCost) {
        this.endPurchaseCost = endPurchaseCost;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public boolean isSetAllTag() {
        return setAllTag;
    }

    public void setSetAllTag(boolean setAllTag) {
        this.setAllTag = setAllTag;
    }
}




