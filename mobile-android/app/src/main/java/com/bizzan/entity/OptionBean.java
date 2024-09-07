package com.bizzan.entity;

import java.util.List;

/**
 * data: 2020/10/13.
 */
public class OptionBean {

    /**
     * data : [{"symbol":"BTC/USDT","name":"BTC/USDT预测合约","coinSymbol":"BTC","baseSymbol":"USDT","maxOptionNo":16209,"sort":0,"coinScale":4,"baseCoinScale":4,"enable":1,"visible":1,"tiedType":1,"enableBuy":1,"enableSell":1,"amount":"10,20,50,100,200,500,1000,2000","feePercent":0,"winFeePercent":0.001,"ngnorePercent":1.0E-4,"initBuyReward":0,"initSellReward":0,"totalProfit":0,"openTimeGap":300,"closeTimeGap":300,"createTime":null,"currentTime":null},{"symbol":"LTC/USDT","name":"LTC/USDT预测合约","coinSymbol":"LTC","baseSymbol":"USDT","maxOptionNo":15349,"sort":0,"coinScale":4,"baseCoinScale":4,"enable":1,"visible":1,"tiedType":1,"enableBuy":1,"enableSell":1,"amount":"10,20,50,100,200,500,1000","feePercent":0,"winFeePercent":0.001,"ngnorePercent":null,"initBuyReward":0,"initSellReward":0,"totalProfit":0,"openTimeGap":300,"closeTimeGap":300,"createTime":"2020-08-21 03:54:25","currentTime":null},{"symbol":"ETH/USDT","name":"ETH/USDT预测合约","coinSymbol":"ETH","baseSymbol":"USDT","maxOptionNo":16198,"sort":1,"coinScale":4,"baseCoinScale":4,"enable":1,"visible":1,"tiedType":1,"enableBuy":1,"enableSell":1,"amount":"10,20,50,100,200,500,1000,2000","feePercent":0,"winFeePercent":0.001,"ngnorePercent":1.0E-4,"initBuyReward":0,"initSellReward":0,"totalProfit":0,"openTimeGap":300,"closeTimeGap":300,"createTime":null,"currentTime":null}]
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
         * symbol : BTC/USDT
         * name : BTC/USDT预测合约
         * coinSymbol : BTC
         * baseSymbol : USDT
         * maxOptionNo : 16209
         * sort : 0
         * coinScale : 4
         * baseCoinScale : 4
         * enable : 1
         * visible : 1
         * tiedType : 1
         * enableBuy : 1
         * enableSell : 1
         * amount : 10,20,50,100,200,500,1000,2000
         * feePercent : 0.0
         * winFeePercent : 0.001
         * ngnorePercent : 1.0E-4
         * initBuyReward : 0.0
         * initSellReward : 0.0
         * totalProfit : 0.0
         * openTimeGap : 300
         * closeTimeGap : 300
         * createTime : null
         * currentTime : null
         */

        private String symbol;
        private String name;
        private String coinSymbol;
        private String baseSymbol;
        private int maxOptionNo;
        private int sort;
        private int coinScale;
        private int baseCoinScale;
        private int enable;
        private int visible;
        private int tiedType;
        private int enableBuy;
        private int enableSell;
        private String amount;
        private double feePercent;
        private double winFeePercent;
        private double ngnorePercent;
        private double initBuyReward;
        private double initSellReward;
        private double totalProfit;
        private int openTimeGap;
        private int closeTimeGap;
        private Object createTime;
        private Object currentTime;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public int getMaxOptionNo() {
            return maxOptionNo;
        }

        public void setMaxOptionNo(int maxOptionNo) {
            this.maxOptionNo = maxOptionNo;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCoinScale() {
            return coinScale;
        }

        public void setCoinScale(int coinScale) {
            this.coinScale = coinScale;
        }

        public int getBaseCoinScale() {
            return baseCoinScale;
        }

        public void setBaseCoinScale(int baseCoinScale) {
            this.baseCoinScale = baseCoinScale;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getTiedType() {
            return tiedType;
        }

        public void setTiedType(int tiedType) {
            this.tiedType = tiedType;
        }

        public int getEnableBuy() {
            return enableBuy;
        }

        public void setEnableBuy(int enableBuy) {
            this.enableBuy = enableBuy;
        }

        public int getEnableSell() {
            return enableSell;
        }

        public void setEnableSell(int enableSell) {
            this.enableSell = enableSell;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public double getFeePercent() {
            return feePercent;
        }

        public void setFeePercent(double feePercent) {
            this.feePercent = feePercent;
        }

        public double getWinFeePercent() {
            return winFeePercent;
        }

        public void setWinFeePercent(double winFeePercent) {
            this.winFeePercent = winFeePercent;
        }

        public double getNgnorePercent() {
            return ngnorePercent;
        }

        public void setNgnorePercent(double ngnorePercent) {
            this.ngnorePercent = ngnorePercent;
        }

        public double getInitBuyReward() {
            return initBuyReward;
        }

        public void setInitBuyReward(double initBuyReward) {
            this.initBuyReward = initBuyReward;
        }

        public double getInitSellReward() {
            return initSellReward;
        }

        public void setInitSellReward(double initSellReward) {
            this.initSellReward = initSellReward;
        }

        public double getTotalProfit() {
            return totalProfit;
        }

        public void setTotalProfit(double totalProfit) {
            this.totalProfit = totalProfit;
        }

        public int getOpenTimeGap() {
            return openTimeGap;
        }

        public void setOpenTimeGap(int openTimeGap) {
            this.openTimeGap = openTimeGap;
        }

        public int getCloseTimeGap() {
            return closeTimeGap;
        }

        public void setCloseTimeGap(int closeTimeGap) {
            this.closeTimeGap = closeTimeGap;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(Object currentTime) {
            this.currentTime = currentTime;
        }
    }
}
