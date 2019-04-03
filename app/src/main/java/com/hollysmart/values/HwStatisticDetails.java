package com.hollysmart.values;

import java.io.Serializable;

/**
 * Created by sunpengfei on 2017/8/8.
 */

public class HwStatisticDetails implements Serializable{

    private String statisticTypeName;	// 统计类型名称
    private int typeNum;	// 单个类型的数量

    public String getStatisticTypeName() {
        return statisticTypeName;
    }

    public void setStatisticTypeName(String statisticTypeName) {
        this.statisticTypeName = statisticTypeName;
    }

    public int getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }
}
