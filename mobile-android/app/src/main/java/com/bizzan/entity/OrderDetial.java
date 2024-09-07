package com.bizzan.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/3.
 */

public class OrderDetial implements Serializable {

    private String orderSn;
    private int type;
    private String unit;
    private int status;
    private double price;
    private double money;
    private double amount;
    private double commission;
    private PayInfoBean payInfo;
    private String createTime;
    private String payTime;
    private double timeLimit;
    private String otherSide;
    private String myId;
    private String hisId;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOtherSide() {
        return otherSide;
    }

    public void setOtherSide(String otherSide) {
        this.otherSide = otherSide;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public PayInfoBean getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfoBean payInfo) {
        this.payInfo = payInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public static class PayInfoBean implements Serializable {
        private String realName;
        private AlipayBean alipay;
        private WechatPayBean wechatPay;
        private BankInfoBean bankInfo;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public AlipayBean getAlipay() {
            return alipay;
        }

        public void setAlipay(AlipayBean alipay) {
            this.alipay = alipay;
        }

        public WechatPayBean getWechatPay() {
            return wechatPay;
        }

        public void setWechatPay(WechatPayBean wechatPay) {
            this.wechatPay = wechatPay;
        }

        public BankInfoBean getBankInfo() {
            return bankInfo;
        }

        public void setBankInfo(BankInfoBean bankInfo) {
            this.bankInfo = bankInfo;
        }
    }

    public static class BankInfoBean implements Serializable {

        private String bank;
        private String branch;
        private String cardNo;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }
    }

    public static class AlipayBean implements Serializable {

        private String aliNo;
        private String qrCodeUrl;

        public String getAliNo() {
            return aliNo;
        }

        public void setAliNo(String aliNo) {
            this.aliNo = aliNo;
        }

        public String getQrCodeUrl() {
            return qrCodeUrl;
        }

        public void setQrCodeUrl(String qrCodeUrl) {
            this.qrCodeUrl = qrCodeUrl;
        }
    }

    public static class WechatPayBean implements Serializable {

        private String wechat;
        private String qrWeCodeUrl;

        public String getWechatNo() {
            return wechat;
        }

        public void setWechatNo(String wechatNo) {
            this.wechat = wechatNo;
        }

        public String getQrCodeUrl() {
            return qrWeCodeUrl;
        }

        public void setQrCodeUrl(String qrCodeUrl) {
            this.qrWeCodeUrl = qrCodeUrl;
        }
    }
}
