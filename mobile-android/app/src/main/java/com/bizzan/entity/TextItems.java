package com.bizzan.entity;

import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/24 0024 15:47
 * desc  :
 */

public class TextItems {

    /**
     * direction : SELL
     * items : [{"amount":17.7,"price":6748.71},{"amount":1,"price":6762.71},{"amount":1,"price":7354.84},{"amount":1,"price":7381.04},{"amount":1.02,"price":8341.57},{"amount":90.97,"price":8515.6},{"amount":0.94,"price":8900},{"amount":1,"price":8905.03}]
     * maxDepth : 100
     */

    private String direction;
    private int maxDepth;
    private List<Exchange> items;
    private String symbol;

    public String getDirection() {
        return direction;
    }
    public String getSymbol(){
        return symbol;
    }
    public void setSymbol(String symbol){
        this.symbol=symbol;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public List<Exchange> getItems() {
        return items;
    }

    public void setItems(List<Exchange> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * amount : 17.7
         * price : 6748.71
         */

        private double amount;
        private double price;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
