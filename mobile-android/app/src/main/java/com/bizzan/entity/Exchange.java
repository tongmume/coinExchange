package com.bizzan.entity;

/**
 * Created by Administrator on 2018/1/30.
 */

public class Exchange {
    private String idText;
    private int type;// 1 买入记录 2 卖出记录\
    private String price;
    private String amount;
    private String totalAmount;
    private int position;
    public Exchange(String price, String amount) {
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "idText='" + idText + '\'' +
                ", type=" + type +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", position=" + position +
                '}';
    }

    public Exchange(int position, String price, String amount){
        this.position = position;
        this.price = price;
        this.amount = amount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getIdText() {
        return idText;
    }

    public void setIdText(String idText) {
        this.idText = idText;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Exchange(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
