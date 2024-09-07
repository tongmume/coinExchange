package com.bizzan.entity;

import java.util.List;

/**
 * data: 2020/10/15.
 */
public class ForecaseBean {

    /**
     * data : [{"id":49578,"optionNo":16817,"symbol":"BTC/USDT","openPrice":11395.23,"openTime":1602742980007,"closePrice":null,"closeTime":null,"totalBuy":0,"initBuy":0,"totalBuyCount":0,"totalSell":0,"initSell":0,"totalSellCount":0,"status":"OPENING","result":"WAIT","totalPl":0,"createTime":1602742680059}]
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
         * id : 49578
         * optionNo : 16817
         * symbol : BTC/USDT
         * openPrice : 11395.23
         * openTime : 1602742980007
         * closePrice : null
         * closeTime : null
         * totalBuy : 0.0
         * initBuy : 0.0
         * totalBuyCount : 0
         * totalSell : 0.0
         * initSell : 0.0
         * totalSellCount : 0
         * status : OPENING
         * result : WAIT
         * totalPl : 0.0
         * createTime : 1602742680059
         */

        private int id;
        private int optionNo;
        private String symbol;
        private double openPrice;
        private long openTime;
        private Object closePrice;
        private Object closeTime;
        private double totalBuy;
        private double initBuy;
        private int totalBuyCount;
        private double totalSell;
        private double initSell;
        private int totalSellCount;
        private String status;
        private String result;
        private double totalPl;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOptionNo() {
            return optionNo;
        }

        public void setOptionNo(int optionNo) {
            this.optionNo = optionNo;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getOpenPrice() {
            return openPrice;
        }

        public void setOpenPrice(double openPrice) {
            this.openPrice = openPrice;
        }

        public long getOpenTime() {
            return openTime;
        }

        public void setOpenTime(long openTime) {
            this.openTime = openTime;
        }

        public Object getClosePrice() {
            return closePrice;
        }

        public void setClosePrice(Object closePrice) {
            this.closePrice = closePrice;
        }

        public Object getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(Object closeTime) {
            this.closeTime = closeTime;
        }

        public double getTotalBuy() {
            return totalBuy;
        }

        public void setTotalBuy(double totalBuy) {
            this.totalBuy = totalBuy;
        }

        public double getInitBuy() {
            return initBuy;
        }

        public void setInitBuy(double initBuy) {
            this.initBuy = initBuy;
        }

        public int getTotalBuyCount() {
            return totalBuyCount;
        }

        public void setTotalBuyCount(int totalBuyCount) {
            this.totalBuyCount = totalBuyCount;
        }

        public double getTotalSell() {
            return totalSell;
        }

        public void setTotalSell(double totalSell) {
            this.totalSell = totalSell;
        }

        public double getInitSell() {
            return initSell;
        }

        public void setInitSell(double initSell) {
            this.initSell = initSell;
        }

        public int getTotalSellCount() {
            return totalSellCount;
        }

        public void setTotalSellCount(int totalSellCount) {
            this.totalSellCount = totalSellCount;
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

        public double getTotalPl() {
            return totalPl;
        }

        public void setTotalPl(double totalPl) {
            this.totalPl = totalPl;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
