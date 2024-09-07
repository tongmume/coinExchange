package com.bizzan.entity;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ChatEntity {

    private String content;
    private String messageType;
    private String nameFrom;
    private String nameTo;
    private String orderId;
    private long sendTime;
    private String sendTimeStr;
    private String uidFrom;
    private String uidTo;
    private Type type;
    private String fromAvatar;


    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public String getUidFrom() {
        return uidFrom;
    }

    public void setUidFrom(String uidFrom) {
        this.uidFrom = uidFrom;
    }

    public String getUidTo() {
        return uidTo;
    }

    public void setUidTo(String uidTo) {
        this.uidTo = uidTo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

   public enum Type {
        LEFT, RIGHT;
    }
}
