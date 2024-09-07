package com.bizzan.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/28.
 */

public class CoinInfo implements Serializable {
    private String unit;
    private String marketPrice;
    private String sell_min_amount;
    private String name;
    private String nameCn;
    private String id;
    private String sort;
    private String buy_min_amount;
    private boolean isSelected;

    public String getUnit() {
        return unit;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getSell_min_amount() {
        return sell_min_amount;
    }

    public void setSell_min_amount(String sell_min_amount) {
        this.sell_min_amount = sell_min_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getBuy_min_amount() {
        return buy_min_amount;
    }

    public void setBuy_min_amount(String buy_min_amount) {
        this.buy_min_amount = buy_min_amount;
    }
}
