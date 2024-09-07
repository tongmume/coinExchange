package com.bizzan.entity;

/**
 * Created by Administrator on 2018/3/9.
 */

public class SafeSetting {
    private String username;
    private int id;
    private String createTime;
    private int realVerified;
    private int realAuditing;
    private int emailVerified;
    private int phoneVerified; //是否已经绑定手机 1表示是，0表示没有
    private int loginVerified;
    private int fundsVerified;
    private int accountVerified;
    private String mobilePhone;
    private String email;
    private String realName;
    private String idCard;
    private String avatar;
    private String realNameRejectReason;


    public int getAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(int accountVerified) {
        this.accountVerified = accountVerified;
    }


    public String getRealNameRejectReason() {
        return realNameRejectReason;
    }

    public void setRealNameRejectReason(String realNameRejectReason) {
        this.realNameRejectReason = realNameRejectReason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getRealVerified() {
        return realVerified;
    }

    public void setRealVerified(int realVerified) {
        this.realVerified = realVerified;
    }

    public int getRealAuditing() {
        return realAuditing;
    }

    public void setRealAuditing(int realAuditing) {
        this.realAuditing = realAuditing;
    }

    public int getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(int emailVerified) {
        this.emailVerified = emailVerified;
    }

    public int getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(int phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public int getLoginVerified() {
        return loginVerified;
    }

    public void setLoginVerified(int loginVerified) {
        this.loginVerified = loginVerified;
    }

    public int getFundsVerified() {
        return fundsVerified;
    }

    public void setFundsVerified(int fundsVerified) {
        this.fundsVerified = fundsVerified;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
