package com.bizzan.entity;

/**
 * Created by Administrator on 2018/3/1.
 */

public class PayWay {
    private String name;
    private boolean isSelect;

    public PayWay(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
