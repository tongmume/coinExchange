package com.bizzan.entity;

/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class VolumeInfo {
    //    "price": 7587.620000000000000000,
//            "amount": 0.001400000000000000,
//            "buyTurnover": null,
//            "sellTurnover": null,
//            "direction": "BUY",
//            "buyOrderId": null,
//            "sellOrderId": null,
//            "time": 1528103466073
    private double price;
    private double amount;
    private double buyTurnover;
    private double sellTurnover;
    private String direction;
    private long time;
    private String sellOrderId;
    private String buyOrderId;
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBuyTurnover(double buyTurnover) {
        this.buyTurnover = buyTurnover;
    }

    public void setSellTurnover(double sellTurnover) {
        this.sellTurnover = sellTurnover;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    public double getBuyTurnover() {
        return buyTurnover;
    }

    public double getSellTurnover() {
        return sellTurnover;
    }

    public String getDirection() {
        return direction;
    }

    public long getTime() {
        return time;
    }

    public String getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(String sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(String buyOrderId) {
        this.buyOrderId = buyOrderId;
    }
}
