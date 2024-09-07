package com.bizzan.entity;

import java.io.Serializable;

public class CTCOrderDetail implements Serializable {
    private Long id;
    private String realName;
    private String orderSn;
    private String unit;
    private int status;
    private double price;
    private double money;
    private double amount;
    private String remark;
    private String cancelReason;
    private String createTime;
    private String confirmTime;
    private String cancelTime;
    private String payTime;
    private String completeTime;
    private String currentTime;
    private int direction = 0;
    private String payMode = "";

    private CTCOrderDetail.AlipayBean alipay;
    private CTCOrderDetail.WechatPayBean wechatPay;
    private CTCOrderDetail.BankInfoBean bankInfo;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public CTCOrderDetail.AlipayBean getAlipay() {
        return alipay;
    }

    public void setAlipay(CTCOrderDetail.AlipayBean alipay) {
        this.alipay = alipay;
    }

    public CTCOrderDetail.WechatPayBean getWechatPay() {
        return wechatPay;
    }

    public void setWechatPay(CTCOrderDetail.WechatPayBean wechatPay) {
        this.wechatPay = wechatPay;
    }

    public CTCOrderDetail.BankInfoBean getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(CTCOrderDetail.BankInfoBean bankInfo) {
        this.bankInfo = bankInfo;
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
