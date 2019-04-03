package com.hollysmart.values;

import java.io.Serializable;

/**
 * Created by sunpengfei on 2017/8/7.
 */

public class SheShiInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String facilityName;		// 设施名称
    private String areaType;		    // 所属区域
    private String streetTown;		    // 所在街道乡镇

    private String statistical;		    // 统计分类
    private String homeworkUnit;		// 作业单位
    private String facilityNumber;		// 设施编号
    private String facilityType;		// 设施类型
    private String address;		        // 地址名称
    private String indicatorsState;		// 指标状况

    private String  useTime;		    // 投入使用日期

    private String receivingTime;		// 接管日期

    private Double investment;		    // 初建投资
    private Long buildingSize;		    // 建筑面积

    private String propertyrightsNuit;	// 产权单位

    private Double maintainArea;		// 车辆停放保养场占地面积
    private Long maintainNumber;		// 车辆停放保养场停放车辆数
    private Long restNumber;		    // 工人休息点-供休息工人数
    private Long piCompressNumber;		// 地坑或压缩机个数
    private Long containerNumber;		// 集装箱个数
    private String garbageNumber;		// 垃圾分类功能
    private Double saltpoolSize;		// 化盐池容积
    private String manageWay;		    // 处理方式

    private String asthelevel;		    // 认定级别
    private Double mangeAbility;		// 日处理能力（吨/日）

    private String  startData;		    // 开始、结束时间

    private String  endData;		    // 开始、结束时间
    private String note;		        // 备注
    private Double longitude;		    // 经度
    private Double lat;		            // 纬度

    private String remarksType;		    // 用于设施分模块

    private boolean isAdd;

    private boolean setAllTag;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getStreetTown() {
        return streetTown;
    }

    public void setStreetTown(String streetTown) {
        this.streetTown = streetTown;
    }

    public String getStatistical() {
        return statistical;
    }

    public void setStatistical(String statistical) {
        this.statistical = statistical;
    }

    public String getHomeworkUnit() {
        return homeworkUnit;
    }

    public void setHomeworkUnit(String homeworkUnit) {
        this.homeworkUnit = homeworkUnit;
    }

    public String getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityNumber(String facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndicatorsState() {
        return indicatorsState;
    }

    public void setIndicatorsState(String indicatorsState) {
        this.indicatorsState = indicatorsState;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(String receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Double getInvestment() {
        return investment;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public Long getBuildingSize() {
        return buildingSize;
    }

    public void setBuildingSize(Long buildingSize) {
        this.buildingSize = buildingSize;
    }

    public String getPropertyrightsNuit() {
        return propertyrightsNuit;
    }

    public void setPropertyrightsNuit(String propertyrightsNuit) {
        this.propertyrightsNuit = propertyrightsNuit;
    }

    public Double getMaintainArea() {
        return maintainArea;
    }

    public void setMaintainArea(Double maintainArea) {
        this.maintainArea = maintainArea;
    }

    public Long getMaintainNumber() {
        return maintainNumber;
    }

    public void setMaintainNumber(Long maintainNumber) {
        this.maintainNumber = maintainNumber;
    }

    public Long getRestNumber() {
        return restNumber;
    }

    public void setRestNumber(Long restNumber) {
        this.restNumber = restNumber;
    }

    public Long getPiCompressNumber() {
        return piCompressNumber;
    }

    public void setPiCompressNumber(Long piCompressNumber) {
        this.piCompressNumber = piCompressNumber;
    }

    public Long getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(Long containerNumber) {
        this.containerNumber = containerNumber;
    }

    public String getGarbageNumber() {
        return garbageNumber;
    }

    public void setGarbageNumber(String garbageNumber) {
        this.garbageNumber = garbageNumber;
    }

    public Double getSaltpoolSize() {
        return saltpoolSize;
    }

    public void setSaltpoolSize(Double saltpoolSize) {
        this.saltpoolSize = saltpoolSize;
    }

    public String getManageWay() {
        return manageWay;
    }

    public void setManageWay(String manageWay) {
        this.manageWay = manageWay;
    }

    public String getAsthelevel() {
        return asthelevel;
    }

    public void setAsthelevel(String asthelevel) {
        this.asthelevel = asthelevel;
    }

    public Double getMangeAbility() {
        return mangeAbility;
    }

    public void setMangeAbility(Double mangeAbility) {
        this.mangeAbility = mangeAbility;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getRemarksType() {
        return remarksType;
    }

    public void setRemarksType(String remarksType) {
        this.remarksType = remarksType;
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
