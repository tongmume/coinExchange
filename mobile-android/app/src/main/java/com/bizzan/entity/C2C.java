package com.bizzan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */

public class C2C {

    private int currentPage;
    private int totalPage;
    private int pageNumber;
    private int totalElement;

    private List<C2CBean> context;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public List<C2CBean> getContext() {
        return context;
    }

    public void setContext(List<C2CBean> context) {
        this.context = context;
    }

    public static class C2CBean implements Serializable {
        private String advertiseType;
        private String memberName;
        private String avatar;
        private int advertiseId;
        private int transactions;
        private double price;
        private double minLimit;
        private double maxLimit;
        private double remainAmount;
        private String createTime;
        private String payMode;
        private int coinId;
        private String unit;
        private String coinName;
        private String coinNameCn;


        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAdvertiseType() {
            return advertiseType;
        }

        public void setAdvertiseType(String advertiseType) {
            this.advertiseType = advertiseType;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public int getAdvertiseId() {
            return advertiseId;
        }

        public void setAdvertiseId(int advertiseId) {
            this.advertiseId = advertiseId;
        }

        public int getTransactions() {
            return transactions;
        }

        public void setTransactions(int transactions) {
            this.transactions = transactions;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getMinLimit() {
            return minLimit;
        }

        public void setMinLimit(double minLimit) {
            this.minLimit = minLimit;
        }

        public double getMaxLimit() {
            return maxLimit;
        }

        public void setMaxLimit(double maxLimit) {
            this.maxLimit = maxLimit;
        }

        public double getRemainAmount() {
            return remainAmount;
        }

        public void setRemainAmount(double remainAmount) {
            this.remainAmount = remainAmount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPayMode() {
            return payMode;
        }

        public void setPayMode(String payMode) {
            this.payMode = payMode;
        }

        public int getCoinId() {
            return coinId;
        }

        public void setCoinId(int coinId) {
            this.coinId = coinId;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getCoinName() {
            return coinName;
        }

        public void setCoinName(String coinName) {
            this.coinName = coinName;
        }

        public String getCoinNameCn() {
            return coinNameCn;
        }

        public void setCoinNameCn(String coinNameCn) {
            this.coinNameCn = coinNameCn;
        }
    }
}
