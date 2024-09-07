package com.bizzan.entity;

/**
 * Created by Administrator on 2018/3/7.
 */

public class C2CExchangeInfo {

    private String username;
    private int emailVerified;
    private int phoneVerified;
    private int idCardVerified;
    private int transactions;
    private int otcCoinId;
    private String unit;
    private double price;
    private double number;
    private String payMode;
    private double minLimit;
    private double maxLimit;
    private double timeLimit;
    private double remainAmount;
    private Double maxTradableAmount;

    public Double getMaxTradableAmount() {
        return maxTradableAmount;
    }

    public void setMaxTradableAmount(Double maxTradableAmount) {
        this.maxTradableAmount = maxTradableAmount;
    }

    public double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(double remainAmount) {
        this.remainAmount = remainAmount;
    }

    private String country;
    private String remark;
    private int advertiseType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(int emailVerified) {
        this.emailVerified = emailVerified;
    }

    public int getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(int phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public int getIdCardVerified() {
        return idCardVerified;
    }

    public void setIdCardVerified(int idCardVerified) {
        this.idCardVerified = idCardVerified;
    }

    public int getTransactions() {
        return transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public int getOtcCoinId() {
        return otcCoinId;
    }

    public void setOtcCoinId(int otcCoinId) {
        this.otcCoinId = otcCoinId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
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

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAdvertiseType() {
        return advertiseType;
    }

    public void setAdvertiseType(int advertiseType) {
        this.advertiseType = advertiseType;
    }
}
