package com.bizzan.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

@Table(name = "chat_table")
public class ChatTable {
    @Column(name = "content")
    public String content;

    @Column(name = "fromAvatar")
    public String fromAvatar;

    @Column(name = "nameFrom")
    public String nameFrom;

    @Column(name = "nameTo")
    public String nameTo;

    @Column(name = "uidFrom")
    public String uidFrom;

    @Column(name = "uidTo")
    public String uidTo;

    @Column(name = "orderId")
    public String orderId;

    @Column(name = "isRead")
    public boolean isRead;

    @Column(name = "hasNew")
    public boolean hasNew;

    @Column(name = "sendTimeStr")
    public String sendTimeStr;

    @Column(name = "sendTime")
    public long sendTime;

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }

    @Column(name = "id",isId = true)
    public int id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public ChatTable(){

    }

    @Override
    public String toString() {
        return "ChatTable{" +
                "content='" + content + '\'' +
                ", fromAvatar='" + fromAvatar + '\'' +
                ", nameFrom='" + nameFrom + '\'' +
                ", nameTo='" + nameTo + '\'' +
                ", uidFrom='" + uidFrom + '\'' +
                ", uidTo='" + uidTo + '\'' +
                ", orderId='" + orderId + '\'' +
                ", isRead=" + isRead +
                ", id=" + id +
                '}';
    }
}
