package com.bizzan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * data: 2020/9/3.
 */
public class CurrentEntrust implements Serializable {

    /**
     * content : [{"id":7919,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159910064881377","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11450,"entrustPrice":11419.86,"tradedPrice":0,"principalUnit":"USDT","principalAmount":1,"currentPrice":11416.01,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_ING","createTime":1599100648813,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7918,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159909873260262","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11808,"entrustPrice":11408.01,"tradedPrice":0,"principalUnit":"USDT","principalAmount":1,"currentPrice":11408.93,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_ING","createTime":1599098732602,"triggeringTime":0,"isFromSpot":1,"isBlast":0}]
     * last : true
     * totalElements : 2
     * totalPages : 1
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * first : true
     * numberOfElements : 2
     * size : 50
     * number : 0
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private boolean first;
    private int numberOfElements;
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

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
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

    public static class ContentBean implements Serializable{
        /**
         * id : 7919
         * contractId : 1
         * memberId : 10005
         * contractOrderEntrustId : CE159910064881377
         * patterns : FIXED
         * entrustType : OPEN
         * direction : BUY
         * type : SPOT_LIMIT
         * symbol : BTC/USDT
         * coinSymbol : BTC
         * baseSymbol : USDT
         * triggerPrice : 11450.0
         * entrustPrice : 11419.86
         * tradedPrice : 0.0
         * principalUnit : USDT
         * principalAmount : 1.0
         * currentPrice : 11416.01
         * openFee : 0.01
         * closeFee : null
         * shareNumber : 100.0
         * volume : 1.0
         * tradedVolume : 0.0
         * profitAndLoss : 0.0
         * status : ENTRUST_ING
         * createTime : 1599100648813
         * triggeringTime : 0
         * isFromSpot : 1
         * isBlast : 0
         */

        private int id;
        private int contractId;
        private int memberId;
        private String contractOrderEntrustId;
        private String patterns;
        private String entrustType;
        private String direction;
        private String type;
        private String symbol;
        private String coinSymbol;
        private String baseSymbol;
        private double triggerPrice;
        private double entrustPrice;
        private double tradedPrice;
        private String principalUnit;
        private double principalAmount;
        private double currentPrice;
        private double openFee;
        private Object closeFee;
        private double shareNumber;
        private double volume;
        private double tradedVolume;
        private double profitAndLoss;
        private String status;
        private long createTime;
        private int triggeringTime;
        private int isFromSpot;
        private int isBlast;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getContractId() {
            return contractId;
        }

        public void setContractId(int contractId) {
            this.contractId = contractId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getContractOrderEntrustId() {
            return contractOrderEntrustId;
        }

        public void setContractOrderEntrustId(String contractOrderEntrustId) {
            this.contractOrderEntrustId = contractOrderEntrustId;
        }

        public String getPatterns() {
            return patterns;
        }

        public void setPatterns(String patterns) {
            this.patterns = patterns;
        }

        public String getEntrustType() {
            return entrustType;
        }

        public void setEntrustType(String entrustType) {
            this.entrustType = entrustType;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public double getTriggerPrice() {
            return triggerPrice;
        }

        public void setTriggerPrice(double triggerPrice) {
            this.triggerPrice = triggerPrice;
        }

        public double getEntrustPrice() {
            return entrustPrice;
        }

        public void setEntrustPrice(double entrustPrice) {
            this.entrustPrice = entrustPrice;
        }

        public double getTradedPrice() {
            return tradedPrice;
        }

        public void setTradedPrice(double tradedPrice) {
            this.tradedPrice = tradedPrice;
        }

        public String getPrincipalUnit() {
            return principalUnit;
        }

        public void setPrincipalUnit(String principalUnit) {
            this.principalUnit = principalUnit;
        }

        public double getPrincipalAmount() {
            return principalAmount;
        }

        public void setPrincipalAmount(double principalAmount) {
            this.principalAmount = principalAmount;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getOpenFee() {
            return openFee;
        }

        public void setOpenFee(double openFee) {
            this.openFee = openFee;
        }

        public Object getCloseFee() {
            return closeFee;
        }

        public void setCloseFee(Object closeFee) {
            this.closeFee = closeFee;
        }

        public double getShareNumber() {
            return shareNumber;
        }

        public void setShareNumber(double shareNumber) {
            this.shareNumber = shareNumber;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getTradedVolume() {
            return tradedVolume;
        }

        public void setTradedVolume(double tradedVolume) {
            this.tradedVolume = tradedVolume;
        }

        public double getProfitAndLoss() {
            return profitAndLoss;
        }

        public void setProfitAndLoss(double profitAndLoss) {
            this.profitAndLoss = profitAndLoss;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getTriggeringTime() {
            return triggeringTime;
        }

        public void setTriggeringTime(int triggeringTime) {
            this.triggeringTime = triggeringTime;
        }

        public int getIsFromSpot() {
            return isFromSpot;
        }

        public void setIsFromSpot(int isFromSpot) {
            this.isFromSpot = isFromSpot;
        }

        public int getIsBlast() {
            return isBlast;
        }

        public void setIsBlast(int isBlast) {
            this.isBlast = isBlast;
        }
    }

    public static class SortBean implements Serializable{
        /**
         * direction : DESC
         * property : createTime
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

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

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }
    }
}
