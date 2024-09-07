package com.bizzan.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class AccountSetting implements Serializable{


    private String realName;
    private int bankVerified;
    private int aliVerified;
    private int wechatVerified;
    private BankInfoBean bankInfo;
    private AlipayBean alipay;
    private WeChatBean wechatPay;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getBankVerified() {
        return bankVerified;
    }

    public void setBankVerified(int bankVerified) {
        this.bankVerified = bankVerified;
    }

    public int getAliVerified() {
        return aliVerified;
    }

    public void setAliVerified(int aliVerified) {
        this.aliVerified = aliVerified;
    }

    public int getWechatVerified() {
        return wechatVerified;
    }

    public void setWechatVerified(int wechatVerified) {
        this.wechatVerified = wechatVerified;
    }

    public BankInfoBean getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfoBean bankInfo) {
        this.bankInfo = bankInfo;
    }

    public AlipayBean getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayBean alipay) {
        this.alipay = alipay;
    }

    public WeChatBean getWechatPay() {
        return wechatPay;
    }

    public void setWechatPay(WeChatBean wechatPay) {
        this.wechatPay = wechatPay;
    }

    public static class BankInfoBean implements Serializable{
        /**
         * bank : 中国工商银行
         * branch : 4156453123132
         * cardNo : 123456789632145678
         */

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

    public static class AlipayBean implements Serializable{
        /**
         * aliNo : 18255478978
         * qrCodeUrl : null
         */

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

    public static class WeChatBean implements Serializable{
        /**
         * aliNo : 18255478978
         * qrCodeUrl : null
         */

        private String wechat;
        private String qrCodeUrl;

        public String getweChat() {
            return wechat;
        }

        public void setweChat(String weChatNo) {
            this.wechat = weChatNo;
        }

        public String getQrCodeUrl() {
            return qrCodeUrl;
        }

        public void setQrCodeUrl(String qrCodeUrl) {
            this.qrCodeUrl = qrCodeUrl;
        }
    }

}
