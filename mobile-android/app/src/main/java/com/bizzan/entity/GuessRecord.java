package com.bizzan.entity;

import java.util.List;

public class GuessRecord {


    /**
     * data : {"content":[{"id":7,"periodNo":"001006","quizTypeId":1,"quizType":"1BTC/10.0USDT","numForLottery":"17565","lotteryNo":"08910","lotteryTime":"2018-09-09 20:00:00","betMaxSum":760,"betAmount":"10.0","betSum":723,"betMemberSum":3,"createTime":"2018-09-10 10:01:28","updateTime":"2018-08-10 14:50:50","itemAddress":"https://www.chulian.top/win.html","joinPrice":null,"quizCoinInstantPrice":null,"bonusCoinInstantPrice":null,"periodState":"1"},{"id":6,"periodNo":"002001","quizTypeId":2,"quizType":"1ETH/1.0USDT","numForLottery":"17565","lotteryNo":"08910","lotteryTime":"2018-09-07 23:00:00","betMaxSum":270,"betAmount":"1.0","betSum":270,"betMemberSum":1,"createTime":"2018-09-08 18:14:56","updateTime":"2018-09-08 18:24:51","itemAddress":"https://www.chulian.top/win.html","joinPrice":null,"quizCoinInstantPrice":null,"bonusCoinInstantPrice":null,"periodState":"1"},{"id":2,"periodNo":"001002","quizTypeId":1,"quizType":"1BTC/10.0USDT","numForLottery":"17565","lotteryNo":"08910","lotteryTime":"2018-09-07 23:00:00","betMaxSum":780,"betAmount":"10.0","betSum":663,"betMemberSum":1,"createTime":"2018-09-08 16:55:13","updateTime":"2018-09-08 17:22:45","itemAddress":"https://www.chulian.top/win.html","joinPrice":null,"quizCoinInstantPrice":null,"bonusCoinInstantPrice":null,"periodState":"1"}],"last":true,"totalElements":3,"totalPages":1,"numberOfElements":3,"first":true,"sort":[{"direction":"DESC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}],"size":20,"number":0}
     * code : 0
     * message : SUCCESS
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
         * content : [{"id":7,"periodNo":"001006","quizTypeId":1,"quizType":"1BTC/10.0USDT","numForLottery":"17565","lotteryNo":"08910","lotteryTime":"2018-09-09 20:00:00","betMaxSum":760,"betAmount":"10.0","betSum":723,"betMemberSum":3,"createTime":"2018-09-10 10:01:28","updateTime":"2018-08-10 14:50:50","itemAddress":"https://www.chulian.top/win.html","joinPrice":null,"quizCoinInstantPrice":null,"bonusCoinInstantPrice":null,"periodState":"1"},{"id":6,"periodNo":"002001","quizTypeId":2,"quizType":"1ETH/1.0USDT","numForLottery":"17565","lotteryNo":"08910","lotteryTime":"2018-09-07 23:00:00","betMaxSum":270,"betAmount":"1.0","betSum":270,"betMemberSum":1,"createTime":"2018-09-08 18:14:56","updateTime":"2018-09-08 18:24:51","itemAddress":"https://www.chulian.top/win.html","joinPrice":null,"quizCoinInstantPrice":null,"bonusCoinInstantPrice":null,"periodState":"1"},{"id":2,"periodNo":"001002","quizTypeId":1,"quizType":"1BTC/10.0USDT","numForLottery":"17565","lotteryNo":"08910","lotteryTime":"2018-09-07 23:00:00","betMaxSum":780,"betAmount":"10.0","betSum":663,"betMemberSum":1,"createTime":"2018-09-08 16:55:13","updateTime":"2018-09-08 17:22:45","itemAddress":"https://www.chulian.top/win.html","joinPrice":null,"quizCoinInstantPrice":null,"bonusCoinInstantPrice":null,"periodState":"1"}]
         * last : true
         * totalElements : 3
         * totalPages : 1
         * numberOfElements : 3
         * first : true
         * sort : [{"direction":"DESC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
         * size : 20
         * number : 0
         */

        private boolean last;
        private int totalElements;
        private int totalPages;
        private int numberOfElements;
        private boolean first;
        private int size;
        private int number;
        private List<ContentBean> content;
        private List<SortBean> sort;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<SortBean> getSort() {
            return sort;
        }

        public void setSort(List<SortBean> sort) {
            this.sort = sort;
        }

        public static class ContentBean {
            /**
             * id : 7
             * periodNo : 001006
             * quizTypeId : 1
             * quizType : 1BTC/10.0USDT
             * numForLottery : 17565
             * lotteryNo : 08910
             * lotteryTime : 2018-09-09 20:00:00
             * betMaxSum : 760
             * betAmount : 10.0
             * betSum : 723
             * betMemberSum : 3
             * createTime : 2018-09-10 10:01:28
             * updateTime : 2018-08-10 14:50:50
             * itemAddress : https://www.chulian.top/win.html
             * joinPrice : null
             * quizCoinInstantPrice : null
             * bonusCoinInstantPrice : null
             * periodState : 1
             */

            private int id;
            private String periodNo;
            private int quizTypeId;
            private String quizType;
            private String numForLottery;
            private String lotteryNo;
            private String lotteryTime;
            private int betMaxSum;
            private String betAmount;
            private int betSum;
            private int betMemberSum;
            private String createTime;
            private String updateTime;
            private String itemAddress;
            private Object joinPrice;
            private Object quizCoinInstantPrice;
            private Object bonusCoinInstantPrice;
            private String periodState;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPeriodNo() {
                return periodNo;
            }

            public void setPeriodNo(String periodNo) {
                this.periodNo = periodNo;
            }

            public int getQuizTypeId() {
                return quizTypeId;
            }

            public void setQuizTypeId(int quizTypeId) {
                this.quizTypeId = quizTypeId;
            }

            public String getQuizType() {
                return quizType;
            }

            public void setQuizType(String quizType) {
                this.quizType = quizType;
            }

            public String getNumForLottery() {
                return numForLottery;
            }

            public void setNumForLottery(String numForLottery) {
                this.numForLottery = numForLottery;
            }

            public String getLotteryNo() {
                return lotteryNo;
            }

            public void setLotteryNo(String lotteryNo) {
                this.lotteryNo = lotteryNo;
            }

            public String getLotteryTime() {
                return lotteryTime;
            }

            public void setLotteryTime(String lotteryTime) {
                this.lotteryTime = lotteryTime;
            }

            public int getBetMaxSum() {
                return betMaxSum;
            }

            public void setBetMaxSum(int betMaxSum) {
                this.betMaxSum = betMaxSum;
            }

            public String getBetAmount() {
                return betAmount;
            }

            public void setBetAmount(String betAmount) {
                this.betAmount = betAmount;
            }

            public int getBetSum() {
                return betSum;
            }

            public void setBetSum(int betSum) {
                this.betSum = betSum;
            }

            public int getBetMemberSum() {
                return betMemberSum;
            }

            public void setBetMemberSum(int betMemberSum) {
                this.betMemberSum = betMemberSum;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getItemAddress() {
                return itemAddress;
            }

            public void setItemAddress(String itemAddress) {
                this.itemAddress = itemAddress;
            }

            public Object getJoinPrice() {
                return joinPrice;
            }

            public void setJoinPrice(Object joinPrice) {
                this.joinPrice = joinPrice;
            }

            public Object getQuizCoinInstantPrice() {
                return quizCoinInstantPrice;
            }

            public void setQuizCoinInstantPrice(Object quizCoinInstantPrice) {
                this.quizCoinInstantPrice = quizCoinInstantPrice;
            }

            public Object getBonusCoinInstantPrice() {
                return bonusCoinInstantPrice;
            }

            public void setBonusCoinInstantPrice(Object bonusCoinInstantPrice) {
                this.bonusCoinInstantPrice = bonusCoinInstantPrice;
            }

            public String getPeriodState() {
                return periodState;
            }

            public void setPeriodState(String periodState) {
                this.periodState = periodState;
            }
        }

        public static class SortBean {
            /**
             * direction : DESC
             * property : id
             * ignoreCase : false
             * nullHandling : NATIVE
             * descending : true
             * ascending : false
             */

            private String direction;
            private String property;
            private boolean ignoreCase;
            private String nullHandling;
            private boolean descending;
            private boolean ascending;

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public boolean isIgnoreCase() {
                return ignoreCase;
            }

            public void setIgnoreCase(boolean ignoreCase) {
                this.ignoreCase = ignoreCase;
            }

            public String getNullHandling() {
                return nullHandling;
            }

            public void setNullHandling(String nullHandling) {
                this.nullHandling = nullHandling;
            }

            public boolean isDescending() {
                return descending;
            }

            public void setDescending(boolean descending) {
                this.descending = descending;
            }

            public boolean isAscending() {
                return ascending;
            }

            public void setAscending(boolean ascending) {
                this.ascending = ascending;
            }
        }
    }
}
