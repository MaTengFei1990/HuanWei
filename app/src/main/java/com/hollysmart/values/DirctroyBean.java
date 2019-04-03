package com.hollysmart.values;

/**
 * Created by sunpengfei on 2017/8/7.
 */

public class DirctroyBean {
    private static final long serialVersionUID = 1L;

    private String value;       // 数据值

    private String label;	    // 标签名

    private String type;	    // 类型

    private String description; // 描述

    private Integer sort;	    // 排序

    private String parentId;    //父Id

    private String iconPath;    //图片路径

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
