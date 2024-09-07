package com.bizzan.entity;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class PromotionReward {

    /**
     * symbol : GCX
     * remark : 币币推广交易
     * amount : 1.0E-8
     * createTime : 2018-04-28 17:19:40
     */

    private String symbol;
    private String remark;
    private double amount;
    private String createTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
