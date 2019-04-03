package com.hollysmart.beans;

/**
 * Created by sunpengfei on 2017/8/7.
 */

public class HwToilet {
    private static final long serialVersionUID = 1L;
    private String name;		// 厕所名称

    private String areaType;		// 所属区域

    private String address;		// 地址名称

    private Double coverdArea;		// 建筑面积

    private String propertyRight;		// 产权: 0表示无,1表示有

    private Long manPitnums;		// 坑位数（个）男

    private Long womanPitnums;		// 坑位数（个）女

    private Long otherPitnums;		// 坑位数（个）第三卫生间

    private String urinalType;		// 小便器类型

    private String streetTown;		// 所属街道乡镇

    private Long analystTypes;		// 统计分类

    private String cleaningUnit;		// 作业保洁单位

    private String toiletNums;		// 厕所编号

    private String constructMode;		// 建设方式

    private String category;		// 类别

    private String isRuralToilet;		// 是否位农村公厕

    private String isNewruralToilet;		// 是否是新农村公厕

    private String washType;		// 冲洗方式

    private String deodorizationType;		// 除臭方式

    private String indexstatus;		// 指标状况

    private String  serviceTime;		// 投入使用时间

    private String takeoverTime;		// 接管时间

    private Double invest;		// 初建投资（万元）

    private Long urinalNums;		// 小便器数量

    private String barrierFreeFacility;		// 无障碍设施: 0表示无, 1表示有

    private String disabledRoom;		// 残疾间: 0表示无, 1表示有

    private String manageRoom;		// 管理间: 0表示无, 1表示有

    private String washroom;		// 洗手间: 0表示无, 1表示有

    private String summerStarttime;		// 夏季开始时间

    private String summerEndtime;		// 夏季结束时间

    private String winterStarttime;		// 冬季开始时间

    private String winterEndtime;		// 冬季结束时间

    private String fecalTreatment;		// 粪便处理方式

    private String note;		// 备注信息

    private Double longitude;		// 经度

    private Double lat;		// 纬度

    private String shape;		// 几何对象

    private String propertyrightsNuit;		// 产权单位

}
