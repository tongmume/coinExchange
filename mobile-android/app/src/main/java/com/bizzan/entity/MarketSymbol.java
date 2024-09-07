package com.bizzan.entity;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class MarketSymbol {

    /**
     * symbol : BTC/USDT
     * coinSymbol : BTC
     * baseSymbol : USDT
     * enable : 1
     * fee : 0.001
     * sort : 1
     * coinScale : 2
     * baseCoinScale : 2
     * minSellPrice : 0
     * enableMarketSell : 1
     * enableMarketBuy : 1
     * maxTradingTime : 0
     * maxTradingOrder : 0
     * flag : 1
     */

    private String symbol;
    private String coinSymbol;
    private String baseSymbol;
    private int enable;
    private double fee;
    private int sort;
    private int coinScale;
    private int baseCoinScale;
    private double minSellPrice;
    private int enableMarketSell;
    private int enableMarketBuy;
    private int maxTradingTime;
    private int maxTradingOrder;
    private int flag;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public void setBaseSymbol(String baseSymbol) {
        this.baseSymbol = baseSymbol;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getCoinScale() {
        return coinScale;
    }

    public void setCoinScale(int coinScale) {
        this.coinScale = coinScale;
    }

    public int getBaseCoinScale() {
        return baseCoinScale;
    }

    public void setBaseCoinScale(int baseCoinScale) {
        this.baseCoinScale = baseCoinScale;
    }

    public double getMinSellPrice() {
        return minSellPrice;
    }

    public void setMinSellPrice(double minSellPrice) {
        this.minSellPrice = minSellPrice;
    }

    public int getEnableMarketSell() {
        return enableMarketSell;
    }

    public void setEnableMarketSell(int enableMarketSell) {
        this.enableMarketSell = enableMarketSell;
    }

    public int getEnableMarketBuy() {
        return enableMarketBuy;
    }

    public void setEnableMarketBuy(int enableMarketBuy) {
        this.enableMarketBuy = enableMarketBuy;
    }

    public int getMaxTradingTime() {
        return maxTradingTime;
    }

    public void setMaxTradingTime(int maxTradingTime) {
        this.maxTradingTime = maxTradingTime;
    }

    public int getMaxTradingOrder() {
        return maxTradingOrder;
    }

    public void setMaxTradingOrder(int maxTradingOrder) {
        this.maxTradingOrder = maxTradingOrder;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
