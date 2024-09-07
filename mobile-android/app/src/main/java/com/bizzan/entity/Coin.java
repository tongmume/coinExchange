package com.bizzan.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/27.
 */

public class Coin implements Serializable {

    private int id;
    private int memberId;
    private CoinBean coin;
    private double balance;
    private double frozenBalance;
    private double toReleased;
    private String address;
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public CoinBean getCoin() {
        return coin;
    }

    public void setCoin(CoinBean coin) {
        this.coin = coin;
    }

    public double getBalance() {
        return balance;
    }
    public double getToReleased(){return toReleased;};
    public void setToReleased(double toReleased){
        this.toReleased=toReleased;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(double frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class CoinBean implements Serializable {
        private String name;
        private String nameCn;
        private String unit;
        private int status;
        private double minTxFee;
        private double cnyRate;
        private double maxTxFee;
        private double usdRate;
        private int enableRpc;
        private int sort;
        private int canWithdraw;
        private int canRecharge;
        private int canAutoWithdraw;
        private String withdrawThreshold;
        private String minWithdrawAmount;
        private String maxWithdrawAmount;
        private String depositAddress;
        private int accountType;
        private double minRechargeAmount;

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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getMinTxFee() {
            return minTxFee;
        }

        public void setMinTxFee(double minTxFee) {
            this.minTxFee = minTxFee;
        }

        public double getCnyRate() {
            return cnyRate;
        }

        public void setCnyRate(double cnyRate) {
            this.cnyRate = cnyRate;
        }

        public double getMaxTxFee() {
            return maxTxFee;
        }

        public void setMaxTxFee(double maxTxFee) {
            this.maxTxFee = maxTxFee;
        }

        public double getUsdRate() {
            return usdRate;
        }

        public void setUsdRate(double usdRate) {
            this.usdRate = usdRate;
        }

        public int getEnableRpc() {
            return enableRpc;
        }

        public void setEnableRpc(int enableRpc) {
            this.enableRpc = enableRpc;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCanWithdraw() {
            return canWithdraw;
        }

        public void setCanWithdraw(int canWithdraw) {
            this.canWithdraw = canWithdraw;
        }

        public int getCanRecharge() {
            return canRecharge;
        }

        public void setCanRecharge(int canRecharge) {
            this.canRecharge = canRecharge;
        }

        public int getCanAutoWithdraw() {
            return canAutoWithdraw;
        }

        public void setCanAutoWithdraw(int canAutoWithdraw) {
            this.canAutoWithdraw = canAutoWithdraw;
        }

        public String getWithdrawThreshold() {
            return withdrawThreshold;
        }

        public void setWithdrawThreshold(String withdrawThreshold) {
            this.withdrawThreshold = withdrawThreshold;
        }

        public String getMinWithdrawAmount() {
            return minWithdrawAmount;
        }

        public void setMinWithdrawAmount(String minWithdrawAmount) {
            this.minWithdrawAmount = minWithdrawAmount;
        }

        public String getMaxWithdrawAmount() {
            return maxWithdrawAmount;
        }

        public void setMaxWithdrawAmount(String maxWithdrawAmount) {
            this.maxWithdrawAmount = maxWithdrawAmount;
        }

        public String getDepositAddress(){return depositAddress;}
        public void setDepositAddress(String depositAddress){this.depositAddress = depositAddress;}

        public int getAccountType(){return accountType;}
        public void setAccountType(int accountType){
            this.accountType = accountType;
        }

        public double getMinRechargeAmount(){return minRechargeAmount;}
        public void setMinRechargeAmount(double minRechargeAmount){
            this.minRechargeAmount = minRechargeAmount;
        }
    }
}

