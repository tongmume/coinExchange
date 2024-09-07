package com.bizzan.entity;

import java.util.List;

/**
 * data: 2020/10/19.
 */
public class OptionOrderHistoryBean {

    /**
     * data : {"content":[{"id":133,"optionId":50559,"optionNo":17144,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602840790459},{"id":132,"optionId":50553,"optionNo":17142,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602840368269},{"id":131,"optionId":50535,"optionNo":17136,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602838672753},{"id":130,"optionId":50526,"optionNo":17133,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602837573635},{"id":129,"optionId":50469,"optionNo":17114,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602831802752},{"id":128,"optionId":50466,"optionNo":17113,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"SELL","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602831614248},{"id":127,"optionId":50463,"optionNo":17112,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"SELL","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602831221200},{"id":126,"optionId":50457,"optionNo":17110,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602830724631},{"id":125,"optionId":50328,"optionNo":17067,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"SELL","status":"CLOSE","result":"WIN","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602817796107},{"id":124,"optionId":49698,"optionNo":16857,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"WIN","fee":0,"winFee":0,"betAmount":20,"rewardAmount":0,"createTime":1602754838955}],"last":false,"totalElements":89,"totalPages":9,"numberOfElements":10,"first":true,"sort":[{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}],"size":10,"number":0}
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
         * content : [{"id":133,"optionId":50559,"optionNo":17144,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602840790459},{"id":132,"optionId":50553,"optionNo":17142,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602840368269},{"id":131,"optionId":50535,"optionNo":17136,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602838672753},{"id":130,"optionId":50526,"optionNo":17133,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602837573635},{"id":129,"optionId":50469,"optionNo":17114,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602831802752},{"id":128,"optionId":50466,"optionNo":17113,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"SELL","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602831614248},{"id":127,"optionId":50463,"optionNo":17112,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"SELL","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602831221200},{"id":126,"optionId":50457,"optionNo":17110,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"LOSE","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602830724631},{"id":125,"optionId":50328,"optionNo":17067,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"SELL","status":"CLOSE","result":"WIN","fee":0,"winFee":0,"betAmount":10,"rewardAmount":0,"createTime":1602817796107},{"id":124,"optionId":49698,"optionNo":16857,"memberId":10005,"symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","direction":"BUY","status":"CLOSE","result":"WIN","fee":0,"winFee":0,"betAmount":20,"rewardAmount":0,"createTime":1602754838955}]
         * last : false
         * totalElements : 89
         * totalPages : 9
         * numberOfElements : 10
         * first : true
         * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
         * size : 10
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
             * id : 133
             * optionId : 50559
             * optionNo : 17144
             * memberId : 10005
             * symbol : BTC/USDT
             * coinSymbol : BTC
             * baseSymbol : USDT
             * direction : BUY
             * status : CLOSE
             * result : LOSE
             * fee : 0.0
             * winFee : 0.0
             * betAmount : 10.0
             * rewardAmount : 0.0
             * createTime : 1602840790459
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

        public static class SortBean {
            /**
             * direction : DESC
             * property : createTime
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
