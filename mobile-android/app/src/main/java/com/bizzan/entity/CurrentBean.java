package com.bizzan.entity;

import java.util.List;

/**
 * data: 2020/10/16.
 */
public class CurrentBean {

    /**
     * data : [{"id":126,"optionId":50457,"optionNo":17110,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"OPEN","result":"WAIT","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602830724631}]
     * code : 0
     * message : SUCCESS
     * totalPage : null
     * totalElement : null
     */

    private int code;
    private String message;
    private Object totalPage;
    private Object totalElement;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Object totalPage) {
        this.totalPage = totalPage;
    }

    public Object getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Object totalElement) {
        this.totalElement = totalElement;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 126
         * optionId : 50457
         * optionNo : 17110
         * memberId : 10005
         * symbol : BTC/USDT
         * coinSymbol : BTC
         * baseSymbol : USDT
         * direction : BUY
         * status : OPEN
         * result : WAIT
         * fee : 0.0
         * winFee : 0.0
         * betAmount : 10.0
         * rewardAmount : 0.0
         * createTime : 1602830724631
         */

        private int id;
        private int optionId;
        private int optionNo;
        private int memberId;
        private String symbol;
        private String coinSymbol;
        private String baseSymbol;
        private String direction;
        private String status;
        private String result;
        private double fee;
        private double winFee;
        private double betAmount;
        private double rewardAmount;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOptionId() {
            return optionId;
        }

        public void setOptionId(int optionId) {
            this.optionId = optionId;
        }

        public int getOptionNo() {
            return optionNo;
        }

        public void setOptionNo(int optionNo) {
            this.optionNo = optionNo;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
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

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public double getWinFee() {
            return winFee;
        }

        public void setWinFee(double winFee) {
            this.winFee = winFee;
        }

        public double getBetAmount() {
            return betAmount;
        }

        public void setBetAmount(double betAmount) {
            this.betAmount = betAmount;
        }

        public double getRewardAmount() {
            return rewardAmount;
        }

        public void setRewardAmount(double rewardAmount) {
            this.rewardAmount = rewardAmount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
