package com.bizzan.entity;

public class SellerApply {


    /**
     * telno : 15111111111
     * wechat : 666666
     * qq : 666666
     * coinSymbol : BHB
     * amount : 10
     * assetData :
     * tradeData :
     */

    private String telno;
    private String wechat;
    private String qq;
    private String coinSymbol;
    private int amount;
    private String assetData;
    private String tradeData;

    public SellerApply(String telno, String wechat, String qq, String coinSymbol, int amount, String assetData, String tradeData) {
        this.telno = telno;
        this.wechat = wechat;
        this.qq = qq;
        this.coinSymbol = coinSymbol;
        this.amount = amount;
        this.assetData = assetData;
        this.tradeData = tradeData;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAssetData() {
        return assetData;
    }

    public void setAssetData(String assetData) {
        this.assetData = assetData;
    }

    public String getTradeData() {
        return tradeData;
    }

    public void setTradeData(String tradeData) {
        this.tradeData = tradeData;
    }
}