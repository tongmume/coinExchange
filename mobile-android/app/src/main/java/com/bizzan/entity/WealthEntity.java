package com.bizzan.entity;

public class WealthEntity {


    /**
     * data : {"id":12,"memberId":40,"memberName":"赵海鹏","memberMobile":"13215996470","inviterId":69289,"memberRate":"0","giveBhb":10000,"accumulatedMine":0,"releaseRate":"1","feeAmount":null,"promotionTime":"2018-08-03 16:19:44.0","overTime":null,"bonusAmount":null,"releaseAmount":null}
     * code : 0
     * message : success
     * totalPage : null
     * totalElement : null
     */

    private DataBean data;
    private int code;
    private String message;
    private Object totalPage;
    private Object totalElement;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * id : 12
         * memberId : 40
         * memberName : 赵海鹏
         * memberMobile : 13215996470
         * inviterId : 69289
         * memberRate : 0
         * giveBhb : 10000
         * accumulatedMine : 0
         * releaseRate : 1
         * feeAmount : null
         * promotionTime : 2018-08-03 16:19:44.0
         * overTime : null
         * bonusAmount : null
         * releaseAmount : null
         */

        private int id;
        private int memberId;
        private String memberName;
        private String memberMobile;
        private int inviterId;
        private String memberRate;
        private double giveBhb;
        private double accumulatedMine;
        private String releaseRate;
        private Object feeAmount;
        private String promotionTime;
        private Object overTime;
        private Object bonusAmount;
        private Object releaseAmount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMemberMobile() {
            return memberMobile;
        }

        public void setMemberMobile(String memberMobile) {
            this.memberMobile = memberMobile;
        }

        public int getInviterId() {
            return inviterId;
        }

        public void setInviterId(int inviterId) {
            this.inviterId = inviterId;
        }

        public String getMemberRate() {
            return memberRate;
        }

        public void setMemberRate(String memberRate) {
            this.memberRate = memberRate;
        }

        public double getGiveBhb() {
            return giveBhb;
        }

        public void setGiveBhb(int giveBhb) {
            this.giveBhb = giveBhb;
        }

        public double getAccumulatedMine() {
            return accumulatedMine;
        }

        public void setAccumulatedMine(int accumulatedMine) {
            this.accumulatedMine = accumulatedMine;
        }

        public String getReleaseRate() {
            return releaseRate;
        }

        public void setReleaseRate(String releaseRate) {
            this.releaseRate = releaseRate;
        }

        public Object getFeeAmount() {
            return feeAmount;
        }

        public void setFeeAmount(Object feeAmount) {
            this.feeAmount = feeAmount;
        }

        public String getPromotionTime() {
            return promotionTime;
        }

        public void setPromotionTime(String promotionTime) {
            this.promotionTime = promotionTime;
        }

        public Object getOverTime() {
            return overTime;
        }

        public void setOverTime(Object overTime) {
            this.overTime = overTime;
        }

        public Object getBonusAmount() {
            return bonusAmount;
        }

        public void setBonusAmount(Object bonusAmount) {
            this.bonusAmount = bonusAmount;
        }

        public Object getReleaseAmount() {
            return releaseAmount;
        }

        public void setReleaseAmount(Object releaseAmount) {
            this.releaseAmount = releaseAmount;
        }
    }
}