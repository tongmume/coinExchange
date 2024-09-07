package com.bizzan.entity;

import java.util.List;

public class GuessData {


    /**
     * data : [{"itemAddress":"https://www.chulian.top/win.html","betNum":1,"quizType":{"id":1,"quizCurrency":"BTC","betCurrency":"USDT","betAmount":10,"fullQuota":0.85,"deadline":"20:00:00","state":"0","firstPrize":0.5,"secondPrize":0.025,"thirdPrize":0.0125,"createTime":"2018-09-05 19:36:39","joinPrize":30,"firstAmount":10,"secondAmount":20,"thirdAmount":30,"joinAmount":null},"maxSum":100,"periodNo":"001002"},{"itemAddress":"https://www.chulian.top/win.html","betNum":0,"quizType":{"id":2,"quizCurrency":"ETH","betCurrency":"USDT","betAmount":10,"fullQuota":0.85,"deadline":"20:00:00","state":"0","firstPrize":0.5,"secondPrize":0.025,"thirdPrize":0.0125,"createTime":"2018-09-05 19:46:13","joinPrize":null,"firstAmount":null,"secondAmount":null,"thirdAmount":null,"joinAmount":null},"maxSum":200,"periodNo":"002002"},{"itemAddress":"https://www.chulian.top/win.html","betNum":44,"quizType":{"id":1,"quizCurrency":"BTC","betCurrency":"USDT","betAmount":10,"fullQuota":0.85,"deadline":"20:00:00","state":"0","firstPrize":0.5,"secondPrize":0.025,"thirdPrize":0.0125,"createTime":"2018-09-05 19:36:39","joinPrize":30,"firstAmount":10,"secondAmount":20,"thirdAmount":30,"joinAmount":null},"maxSum":780,"periodNo":"001003"},{"itemAddress":"https://www.chulian.top/win.html","betNum":0,"quizType":{"id":2,"quizCurrency":"ETH","betCurrency":"USDT","betAmount":10,"fullQuota":0.85,"deadline":"20:00:00","state":"0","firstPrize":0.5,"secondPrize":0.025,"thirdPrize":0.0125,"createTime":"2018-09-05 19:46:13","joinPrize":null,"firstAmount":null,"secondAmount":null,"thirdAmount":null,"joinAmount":null},"maxSum":880,"periodNo":"002003"},{"itemAddress":"https://www.chulian.top/win.html","betNum":0,"quizType":{"id":2,"quizCurrency":"ETH","betCurrency":"USDT","betAmount":10,"fullQuota":0.85,"deadline":"20:00:00","state":"0","firstPrize":0.5,"secondPrize":0.025,"thirdPrize":0.0125,"createTime":"2018-09-05 19:46:13","joinPrize":null,"firstAmount":null,"secondAmount":null,"thirdAmount":null,"joinAmount":null},"maxSum":880,"periodNo":"002004"}]
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
        public int getIsOneMember() {
            return isOneMember;
        }

        public void setIsOneMember(int isOneMember) {
            this.isOneMember = isOneMember;
        }

        /**
         * itemAddress : https://www.chulian.top/win.html
         * betNum : 1
         * quizType : {"id":1,"quizCurrency":"BTC","betCurrency":"USDT","betAmount":10,"fullQuota":0.85,"deadline":"20:00:00","state":"0","firstPrize":0.5,"secondPrize":0.025,"thirdPrize":0.0125,"createTime":"2018-09-05 19:36:39","joinPrize":30,"firstAmount":10,"secondAmount":20,"thirdAmount":30,"joinAmount":null}
         * maxSum : 100
         * periodNo : 001002
         */
        private int isOneMember;
        private String itemAddress;
        private int betNum;
        private QuizTypeBean quizType;
        private int maxSum;
        private String periodNo;

        public String getItemAddress() {
            return itemAddress;
        }

        public void setItemAddress(String itemAddress) {
            this.itemAddress = itemAddress;
        }

        public int getBetNum() {
            return betNum;
        }

        public void setBetNum(int betNum) {
            this.betNum = betNum;
        }

        public QuizTypeBean getQuizType() {
            return quizType;
        }

        public void setQuizType(QuizTypeBean quizType) {
            this.quizType = quizType;
        }

        public int getMaxSum() {
            return maxSum;
        }

        public void setMaxSum(int maxSum) {
            this.maxSum = maxSum;
        }

        public String getPeriodNo() {
            return periodNo;
        }

        public void setPeriodNo(String periodNo) {
            this.periodNo = periodNo;
        }

        public static class QuizTypeBean {
            /**
             * id : 1
             * quizCurrency : BTC
             * betCurrency : USDT
             * betAmount : 10
             * fullQuota : 0.85
             * deadline : 20:00:00
             * state : 0
             * firstPrize : 0.5
             * secondPrize : 0.025
             * thirdPrize : 0.0125
             * createTime : 2018-09-05 19:36:39
             * joinPrize : 30
             * firstAmount : 10
             * secondAmount : 20
             * thirdAmount : 30
             * joinAmount : null
             */

            private int id;
            private String quizCurrency;
            private String betCurrency;
            private int betAmount;
            private double fullQuota;
            private String deadline;
            private String state;
            private double firstPrize;
            private double secondPrize;
            private double thirdPrize;
            private String createTime;
            private int joinPrize;
            private int firstAmount;
            private int secondAmount;
            private int thirdAmount;
            private Object joinAmount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuizCurrency() {
                return quizCurrency;
            }

            public void setQuizCurrency(String quizCurrency) {
                this.quizCurrency = quizCurrency;
            }

            public String getBetCurrency() {
                return betCurrency;
            }

            public void setBetCurrency(String betCurrency) {
                this.betCurrency = betCurrency;
            }

            public int getBetAmount() {
                return betAmount;
            }

            public void setBetAmount(int betAmount) {
                this.betAmount = betAmount;
            }

            public double getFullQuota() {
                return fullQuota;
            }

            public void setFullQuota(double fullQuota) {
                this.fullQuota = fullQuota;
            }

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public double getFirstPrize() {
                return firstPrize;
            }

            public void setFirstPrize(double firstPrize) {
                this.firstPrize = firstPrize;
            }

            public double getSecondPrize() {
                return secondPrize;
            }

            public void setSecondPrize(double secondPrize) {
                this.secondPrize = secondPrize;
            }

            public double getThirdPrize() {
                return thirdPrize;
            }

            public void setThirdPrize(double thirdPrize) {
                this.thirdPrize = thirdPrize;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getJoinPrize() {
                return joinPrize;
            }

            public void setJoinPrize(int joinPrize) {
                this.joinPrize = joinPrize;
            }

            public int getFirstAmount() {
                return firstAmount;
            }

            public void setFirstAmount(int firstAmount) {
                this.firstAmount = firstAmount;
            }

            public int getSecondAmount() {
                return secondAmount;
            }

            public void setSecondAmount(int secondAmount) {
                this.secondAmount = secondAmount;
            }

            public int getThirdAmount() {
                return thirdAmount;
            }

            public void setThirdAmount(int thirdAmount) {
                this.thirdAmount = thirdAmount;
            }

            public Object getJoinAmount() {
                return joinAmount;
            }

            public void setJoinAmount(Object joinAmount) {
                this.joinAmount = joinAmount;
            }
        }
    }
}
