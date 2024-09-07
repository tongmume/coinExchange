package com.bizzan.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/8.
 */

public class Address implements Serializable{
    private String remark;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
