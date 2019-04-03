package com.hollysmart.values;

import java.io.Serializable;

/**
 * Created by cai on 2017/8/1
 */

public class SheShiFenLeiBean implements Serializable{
    private int id;
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
