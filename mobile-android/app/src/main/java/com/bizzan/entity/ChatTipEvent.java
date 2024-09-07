package com.bizzan.entity;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class ChatTipEvent {
    private boolean hasNew;
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
}
