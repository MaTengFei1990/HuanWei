package com.hollysmart.values;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunpengfei on 2017/8/8.
 */

public class HWStaticInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    private String areaName;	// 区县名称
    private Integer totalTypeNum;	// 总类型的数量
    private List<HwStatisticDetails> detailLists;  // 详情集合

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getTotalTypeNum() {
        return totalTypeNum;
    }

    public void setTotalTypeNum(Integer totalTypeNum) {
        this.totalTypeNum = totalTypeNum;
    }

    public List<HwStatisticDetails> getDetailLists() {
        return detailLists;
    }

    public void setDetailLists(List<HwStatisticDetails> detailLists) {
        this.detailLists = detailLists;
    }
}
