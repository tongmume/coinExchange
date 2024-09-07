package com.bizzan.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ExtractInfo {

    private String unit;
    private double threshold;
    private double minAmount;
    private double maxAmount;
    private double minTxFee;
    private double maxTxFee;
    private String nameCn;
    private String name;
    private double canAutoWithdraw;
    private double balance;
    private ArrayList<Address> addresses;
    private int accountType;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getMinTxFee() {
        return minTxFee;
    }

    public void setMinTxFee(double minTxFee) {
        this.minTxFee = minTxFee;
    }

    public double getMaxTxFee() {
        return maxTxFee;
    }

    public void setMaxTxFee(double maxTxFee) {
        this.maxTxFee = maxTxFee;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCanAutoWithdraw() {
        return canAutoWithdraw;
    }

    public void setCanAutoWithdraw(double canAutoWithdraw) {
        this.canAutoWithdraw = canAutoWithdraw;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public int getAccountType(){ return accountType; }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
