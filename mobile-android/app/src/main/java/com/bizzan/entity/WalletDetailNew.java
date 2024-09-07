package com.bizzan.entity;

import java.util.List;

public class WalletDetailNew {


    /**
     * content : [{"id":6767773,"memberId":69289,"amount":99.9,"createTime":"2018-10-15 17:45:22","type":3,"symbol":"BNB","address":"","fee":0.1,"flag":0,"realFee":"0.10000000","discountFee":"0"},{"id":6767774,"memberId":69289,"amount":-120,"createTime":"2018-10-15 17:45:22","type":3,"symbol":"ETH","address":"","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":6767775,"memberId":69289,"amount":119.88,"createTime":"2018-10-15 17:45:22","type":3,"symbol":"ETH","address":"","fee":0.12,"flag":0,"realFee":"0.1200000000000000","discountFee":"0"},{"id":6767776,"memberId":69289,"amount":-100,"createTime":"2018-10-15 17:45:22","type":3,"symbol":"BNB","address":"","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":6767769,"memberId":69289,"amount":5,"createTime":"2018-10-15 10:39:32","type":1,"symbol":"USDT","address":"1234","fee":1.0E-4,"flag":0,"realFee":"0.00010000","discountFee":"0"},{"id":6767758,"memberId":69289,"amount":0.00202871,"createTime":"2018-10-12 11:07:17","type":4,"symbol":"BTC","address":null,"fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3725879,"memberId":69289,"amount":10,"createTime":"2018-08-24 16:33:59","type":0,"symbol":"HTL","address":"0xda87d6829c6c411347b3faace9263c93b529dcd8","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3722994,"memberId":69289,"amount":10,"createTime":"2018-08-24 15:54:11","type":0,"symbol":"HTL","address":"0xdb07710c8d512e1300a0db103d2018aae0344972","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3722905,"memberId":69289,"amount":10,"createTime":"2018-08-24 15:52:40","type":0,"symbol":"HTL","address":"0xd0b2e948dfcae3aa2682f35b8529a566b88191c6","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3721088,"memberId":69289,"amount":20,"createTime":"2018-08-24 15:28:10","type":0,"symbol":"HTL","address":"0xc68349bb33f141364dcf6d5c63403de604f13dfd","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3718491,"memberId":69289,"amount":20,"createTime":"2018-08-24 14:55:03","type":0,"symbol":"HTL","address":"0xf6bae3eb514eb91fa35fd88adb80e7482ff93529","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3713442,"memberId":69289,"amount":50,"createTime":"2018-08-24 13:46:37","type":0,"symbol":"HTL","address":"0x96190d5c9cd5389fd7bc1a23a31e957ae72bd4fb","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3700353,"memberId":69289,"amount":50,"createTime":"2018-08-24 10:54:14","type":0,"symbol":"HTL","address":"0x4afa73f1d09204c6bfc3ee98538c4e2c315f5bc7","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3699872,"memberId":69289,"amount":300,"createTime":"2018-08-24 10:47:49","type":0,"symbol":"HTL","address":"0xaac3103a447856471f96abf62717b28502a7206b","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3621443,"memberId":69289,"amount":0.02,"createTime":"2018-08-23 15:54:31","type":0,"symbol":"ETH","address":"0x267faa3a6b233bf01dc561405e331484eae3266d","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3510846,"memberId":69289,"amount":0.01,"createTime":"2018-08-22 10:14:44","type":0,"symbol":"ETH","address":"0x1e2e484c6397b0887c10b7c89e2c18d448daaddb","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":3454323,"memberId":69289,"amount":0.01,"createTime":"2018-08-20 16:13:46","type":0,"symbol":"ETH","address":"0x1e2e484c6397b0887c10b7c89e2c18d448daaddb","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":2740897,"memberId":69289,"amount":10,"createTime":"2018-08-14 15:25:24","type":0,"symbol":"USDT","address":"1GU6vF5GYLHGd7Wum5Xd17M7XCkgKkjgYu","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":2740884,"memberId":69289,"amount":0.9,"createTime":"2018-08-13 20:05:36","type":0,"symbol":"WICC","address":"0xb4c9909b1a39211f46014a309e745b98de222b33","fee":0,"flag":0,"realFee":"0","discountFee":"0"},{"id":2740883,"memberId":69289,"amount":0.01,"createTime":"2018-08-13 19:39:24","type":0,"symbol":"ETH","address":"0x54d3f0db16cf7cc22e31fd827b4e732f8ad75318","fee":0,"flag":0,"realFee":"0","discountFee":"0"}]
     * last : false
     * totalPages : 3
     * totalElements : 44
     * numberOfElements : 20
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
     * first : true
     * size : 20
     * number : 0
     */

    private boolean last;
    private int totalPages;
    private int totalElements;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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
         * id : 6767773
         * memberId : 69289
         * amount : 99.9
         * createTime : 2018-10-15 17:45:22
         * type : 3
         * symbol : BNB
         * address :
         * fee : 0.1
         * flag : 0
         * realFee : 0.10000000
         * discountFee : 0
         */

        private int id;
        private int memberId;
        private double amount;
        private String createTime;
        private int type;
        private String symbol;
        private String address;
        private double fee;
        private int flag;
        private String realFee;
        private String discountFee;

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

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getRealFee() {
            return realFee;
        }

        public void setRealFee(String realFee) {
            this.realFee = realFee;
        }

        public String getDiscountFee() {
            return discountFee;
        }

        public void setDiscountFee(String discountFee) {
            this.discountFee = discountFee;
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