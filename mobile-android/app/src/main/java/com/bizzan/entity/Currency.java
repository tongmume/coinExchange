package com.bizzan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */

public class Currency implements Serializable {
    private boolean isCollect; // 是否收藏
    private String symbol; // 币种
    private Double open; // 开盘价
    private Double high; // 最高价
    private Double low; // 最低价
    private Double close; // 现价
    private Double chg; // 涨幅
    private Double change; // 改变量24h
    private BigDecimal volume; // 交易量
    private Double turnover; // 交易额
    private Double lastDayClose; // 昨日收盘价
    private Double usdRate;
    private String baseCoin; //1 个BTC 等于多少个USDT
    private String otherCoin;
    private Double baseUsdRate = 1.0;

    @Override
    public String toString() {
        return "Currency{" +
                "isCollect=" + isCollect +
                ", symbol='" + symbol + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", chg=" + chg +
                ", change=" + change +
                ", volume=" + volume +
                ", turnover=" + turnover +
                ", lastDayClose=" + lastDayClose +
                ", usdRate=" + usdRate +
                ", baseCoin='" + baseCoin + '\'' +
                ", otherCoin='" + otherCoin + '\'' +
                ", baseUsdRate=" + baseUsdRate +
                '}';
    }

    public Double getBaseUsdRate() {
        return baseUsdRate == 0 ? 1 : baseUsdRate;
    }

    public void setBaseUsdRate(Double baseUsdRate) {
        this.baseUsdRate = baseUsdRate;
    }

    public Double getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(Double usdRate) {
        this.usdRate = usdRate;
    }

    public Currency() {

    }

    public static Currency shallowClone(Currency origin, Currency target) {
        origin.symbol = target.symbol;
        origin.open = target.open;
        origin.high = target.high;
        origin.low = target.low;
        origin.close = target.close;
        origin.chg = target.chg;
        origin.change = target.change;
        origin.volume = target.volume;
        origin.turnover = target.turnover;
        origin.lastDayClose = target.lastDayClose;
        return origin;
    }

    public Currency(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public String getBaseCoin() {
        return baseCoin;
    }

    public String getOtherCoin() {
        return otherCoin;
    }

    public void setLastDayClose(Double lastDayClose) {
        this.lastDayClose = lastDayClose;
    }

    public void setBaseCoin(String baseCoin) {
        this.baseCoin = baseCoin;
    }

    public void setOtherCoin(String otherCoin) {
        this.otherCoin = otherCoin;
    }

    public static void buildCurrency(List<Currency> currencies) {
        if (currencies == null)
            return;
        for (Currency currency : currencies) {
            String[] coins = currency.getSymbol().split("/");
            currency.baseCoin = coins[1];
            currency.otherCoin = coins[0];
        }
    }

    public static List<Currency> baseCurrencies(List<Currency> currencies, String baseCoin) {
        List<Currency> result = new ArrayList<>();
        buildCurrency(currencies);
        for (Currency currency : currencies) {
            if (currency.baseCoin.equals(baseCoin)) result.add(currency);
        }
        return result;
    }

    public double getLastDayClose() {
        return lastDayClose;
    }

    public void setLastDayClose(double lastDayClose) {
        this.lastDayClose = lastDayClose;
    }
}
