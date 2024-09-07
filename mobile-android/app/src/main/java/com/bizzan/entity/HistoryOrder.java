package com.bizzan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/18 0018 15:20
 * desc  :
 */

public class HistoryOrder implements Serializable {

    /**
     * orderId : E152203410573335
     * memberId : 23
     * type : MARKET_PRICE
     * amount : 0.1
     * symbol : BTC/USDT
     * tradedAmount : 0
     * turnover : 0
     * coinSymbol : BTC
     * baseSymbol : USDT
     * status : COMPLETED
     * direction : BUY
     * price : 0
     * time : 1522034105733
     * completedTime : 1522034105762
     * canceledTime : null
     * detail : [{"orderId":"E152188651711465","price":7151.11,"amount":0.01,"fee":0.0715111,"time":1523241014524},{"orderId":"E152188651711465","price":7104.12,"amount":0.14,"fee":0.9945768,"time":1523242681699}]
     */

    private String orderId;
    private int memberId;
    private String type;
    private double amount;
    private String symbol;
    private double tradedAmount;
    private double turnover;
    private String coinSymbol;
    private String baseSymbol;
    private String status;
    private String direction;
    private double price;
    private long time;
    private long completedTime;
    private Object canceledTime;
    private List<DetailBean> detail;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getTradedAmount() {
        return tradedAmount;
    }

    public void setTradedAmount(int tradedAmount) {
        this.tradedAmount = tradedAmount;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(long completedTime) {
        this.completedTime = completedTime;
    }

    public Object getCanceledTime() {
        return canceledTime;
    }

    public void setCanceledTime(Object canceledTime) {
        this.canceledTime = canceledTime;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean implements Serializable{
        /**
         * orderId : E152188651711465
         * price : 7151.11
         * amount : 0.01
         * fee : 0.0715111
         * time : 1523241014524
         */

        private String orderId;
        private double price;
        private double amount;
        private double fee;
        private long time;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
