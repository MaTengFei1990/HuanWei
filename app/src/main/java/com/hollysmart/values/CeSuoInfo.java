package com.hollysmart.values;

import java.io.Serializable;

/**
 * Created by sunpengfei on 2017/8/2.
 */

public class CeSuoInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;		    // 厕所名称

    private String areaType;		// 所属区域

    private String address;		    // 地址名称

    private Double coverdArea;		// 建筑面积

    private String propertyRight;	// 产权: 0表示无,1表示有

    private Long manPitnums;		// 坑位数（个）男

    private Long womanPitnums;		// 坑位数（个）女

    private Long otherPitnums;		// 坑位数（个）第三卫生间

    private String urinalType;		// 小便器类型

    private String streetTown;		// 所属街道乡镇

    private Long analystTypes;		// 统计分类

    private String cleaningUnit;	// 作业保洁单位

    private String toiletNums;		// 厕所编号

    private String constructMode;	// 建设方式

    private String category;		// 类别

    private String isRuralToilet;	// 是否位农村公厕

    private String isNewruralToilet;// 是否是新农村公厕

    private String washType;		// 冲洗方式

    private String deodorizationType;// 除臭方式

    private String indexstatus;		// 指标状况

    private String  serviceTime;	// 投入使用时间

    private String takeoverTime;	// 接管时间

    private Double invest;		    // 初建投资（万元）

    private Long urinalNums;		// 小便器数量

    private String barrierFreeFacility;		// 无障碍设施: 0表示无, 1表示有

    private String disabledRoom;		    // 残疾间: 0表示无, 1表示有

    private String manageRoom;		        // 管理间: 0表示无, 1表示有

    private String washroom;		        // 洗手间: 0表示无, 1表示有

    private String summerStarttime;		    // 夏季开始时间

    private String summerEndtime;		    // 夏季结束时间

    private String winterStarttime;		    // 冬季开始时间

    private String winterEndtime;		    // 冬季结束时间

    private String fecalTreatment;		    // 粪便处理方式

    private String note;		            // 备注信息

    private Double longitude;		        // 经度

    private Double lat;		                // 纬度

    private String shape;		            // 几何对象

    private String propertyrightsNuit;		// 产权单位

    private boolean isAdd;

    private boolean setAllTag;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCoverdArea() {
        return coverdArea;
    }

    public void setCoverdArea(Double coverdArea) {
        this.coverdArea = coverdArea;
    }

    public String getPropertyRight() {
        return propertyRight;
    }

    public void setPropertyRight(String propertyRight) {
        this.propertyRight = propertyRight;
    }

    public Long getManPitnums() {
        return manPitnums;
    }

    public void setManPitnums(Long manPitnums) {
        this.manPitnums = manPitnums;
    }

    public Long getWomanPitnums() {
        return womanPitnums;
    }

    public void setWomanPitnums(Long womanPitnums) {
        this.womanPitnums = womanPitnums;
    }

    public Long getOtherPitnums() {
        return otherPitnums;
    }

    public void setOtherPitnums(Long otherPitnums) {
        this.otherPitnums = otherPitnums;
    }

    public String getUrinalType() {
        return urinalType;
    }

    public void setUrinalType(String urinalType) {
        this.urinalType = urinalType;
    }

    public String getStreetTown() {
        return streetTown;
    }

    public void setStreetTown(String streetTown) {
        this.streetTown = streetTown;
    }

    public Long getAnalystTypes() {
        return analystTypes;
    }

    public void setAnalystTypes(Long analystTypes) {
        this.analystTypes = analystTypes;
    }

    public String getCleaningUnit() {
        return cleaningUnit;
    }

    public void setCleaningUnit(String cleaningUnit) {
        this.cleaningUnit = cleaningUnit;
    }

    public String getToiletNums() {
        return toiletNums;
    }

    public void setToiletNums(String toiletNums) {
        this.toiletNums = toiletNums;
    }

    public String getConstructMode() {
        return constructMode;
    }

    public void setConstructMode(String constructMode) {
        this.constructMode = constructMode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsRuralToilet() {
        return isRuralToilet;
    }

    public void setIsRuralToilet(String isRuralToilet) {
        this.isRuralToilet = isRuralToilet;
    }

    public String getIsNewruralToilet() {
        return isNewruralToilet;
    }

    public void setIsNewruralToilet(String isNewruralToilet) {
        this.isNewruralToilet = isNewruralToilet;
    }

    public String getWashType() {
        return washType;
    }

    public void setWashType(String washType) {
        this.washType = washType;
    }

    public String getDeodorizationType() {
        return deodorizationType;
    }

    public void setDeodorizationType(String deodorizationType) {
        this.deodorizationType = deodorizationType;
    }

    public String getIndexstatus() {
        return indexstatus;
    }

    public void setIndexstatus(String indexstatus) {
        this.indexstatus = indexstatus;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getTakeoverTime() {
        return takeoverTime;
    }

    public void setTakeoverTime(String takeoverTime) {
        this.takeoverTime = takeoverTime;
    }

    public Double getInvest() {
        return invest;
    }

    public void setInvest(Double invest) {
        this.invest = invest;
    }

    public Long getUrinalNums() {
        return urinalNums;
    }

    public void setUrinalNums(Long urinalNums) {
        this.urinalNums = urinalNums;
    }

    public String getBarrierFreeFacility() {
        return barrierFreeFacility;
    }

    public void setBarrierFreeFacility(String barrierFreeFacility) {
        this.barrierFreeFacility = barrierFreeFacility;
    }

    public String getDisabledRoom() {
        return disabledRoom;
    }

    public void setDisabledRoom(String disabledRoom) {
        this.disabledRoom = disabledRoom;
    }

    public String getManageRoom() {
        return manageRoom;
    }

    public void setManageRoom(String manageRoom) {
        this.manageRoom = manageRoom;
    }

    public String getWashroom() {
        return washroom;
    }

    public void setWashroom(String washroom) {
        this.washroom = washroom;
    }

    public String getSummerStarttime() {
        return summerStarttime;
    }

    public void setSummerStarttime(String summerStarttime) {
        this.summerStarttime = summerStarttime;
    }

    public String getSummerEndtime() {
        return summerEndtime;
    }

    public void setSummerEndtime(String summerEndtime) {
        this.summerEndtime = summerEndtime;
    }

    public String getWinterStarttime() {
        return winterStarttime;
    }

    public void setWinterStarttime(String winterStarttime) {
        this.winterStarttime = winterStarttime;
    }

    public String getWinterEndtime() {
        return winterEndtime;
    }

    public void setWinterEndtime(String winterEndtime) {
        this.winterEndtime = winterEndtime;
    }

    public String getFecalTreatment() {
        return fecalTreatment;
    }

    public void setFecalTreatment(String fecalTreatment) {
        this.fecalTreatment = fecalTreatment;
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

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getPropertyrightsNuit() {
        return propertyrightsNuit;
    }

    public void setPropertyrightsNuit(String propertyrightsNuit) {
        this.propertyrightsNuit = propertyrightsNuit;
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
