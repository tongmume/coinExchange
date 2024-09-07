package com.bizzan.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/5.
 */

public class Ads implements Serializable {

    private int id;//
    private int advertiseType;// 0 BUY 1  SELL
    private double minLimit;//
    private double maxLimit;//
    private int status;//0 上架 1 下架 2 关闭
    private double remainAmount;
    private String coinUnit;//
    private String createTime;
    private int coinId;
    private String coinName;
    private String coinNameCn;
    private com.bizzan.entity.Country country;
    private int priceType; // 0  固定 1 变动
    private double price;
    private String remark;
    private int timeLimit;
    private double premiseRate;
    private String payMode;
    private double number;
    private double marketPrice;
    private int auto;
    private String autoword;
    private Coin coin;

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdvertiseType() {
        return advertiseType;
    }

    public void setAdvertiseType(int advertiseType) {
        this.advertiseType = advertiseType;
    }

    public double getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(double minLimit) {
        this.minLimit = minLimit;
    }

    public double getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(double maxLimit) {
        this.maxLimit = maxLimit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(double remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getCoinUnit() {
        return coinUnit;
    }

    public void setCoinUnit(String coinUnit) {
        this.coinUnit = coinUnit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinNameCn() {
        return coinNameCn;
    }

    public void setCoinNameCn(String coinNameCn) {
        this.coinNameCn = coinNameCn;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public double getPremiseRate() {
        return premiseRate;
    }

    public void setPremiseRate(double premiseRate) {
        this.premiseRate = premiseRate;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public String getAutoword() {
        return autoword;
    }

    public void setAutoword(String autoword) {
        this.autoword = autoword;
    }

    public class Coin implements Serializable {
        private String unit;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
