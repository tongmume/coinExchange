package com.bizzan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class EntrustHistory implements Serializable {


    /**
     * content : [{"orderId":"E152464256123665","memberId":51,"type":"LIMIT_PRICE","amount":1,"symbol":"BTC/USDT","tradedAmount":1,"turnover":6748.71,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":15555,"time":1524642561236,"completedTime":1524642562856,"canceledTime":null,"detail":[{"orderId":"E152464256123665","price":6748.71,"amount":1,"fee":0.001,"time":1524642561695}]},{"orderId":"E152464056437498","memberId":51,"type":"MARKET_PRICE","amount":888,"symbol":"BTC/USDT","tradedAmount":0.13,"turnover":877.33,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":0,"time":1524640564374,"completedTime":1524640566079,"canceledTime":null,"detail":[{"orderId":"E152464056437498","price":6748.71,"amount":0.13,"fee":1.3E-4,"time":1524640564862}]},{"orderId":"E152463523878564","memberId":51,"type":"MARKET_PRICE","amount":12,"symbol":"BTC/USDT","tradedAmount":0,"turnover":0,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":0,"time":1524635238785,"completedTime":1524635239180,"canceledTime":null,"detail":[]},{"orderId":"E152463507411760","memberId":51,"type":"MARKET_PRICE","amount":12,"symbol":"BTC/USDT","tradedAmount":3,"turnover":3,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":0,"time":1524635074117,"completedTime":1524635079033,"canceledTime":null,"detail":[{"orderId":"E152463507411760","price":1,"amount":1,"fee":0.001,"time":1524635074582},{"orderId":"E152463507411760","price":1,"amount":1,"fee":0.001,"time":1524635075760},{"orderId":"E152463507411760","price":1,"amount":1,"fee":0.001,"time":1524635076869}]},{"orderId":"E152463497700665","memberId":51,"type":"MARKET_PRICE","amount":255,"symbol":"BTC/USDT","tradedAmount":0,"turnover":0,"coinSymbol":"BTC","baseSymbol":"USDT","status":"CANCELED","direction":"SELL","price":0,"time":1524634977006,"completedTime":null,"canceledTime":1524642636099,"detail":[]},{"orderId":"E152463468591355","memberId":51,"type":"LIMIT_PRICE","amount":1,"symbol":"BTC/USDT","tradedAmount":1,"turnover":1,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":1,"time":1524634685913,"completedTime":1524634687827,"canceledTime":null,"detail":[{"orderId":"E152463468591355","price":1,"amount":1,"fee":0.001,"time":1524634686377}]},{"orderId":"E152462827532999","memberId":51,"type":"LIMIT_PRICE","amount":5,"symbol":"BTC/USDT","tradedAmount":5,"turnover":5,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":1,"time":1524628275329,"completedTime":1524628276840,"canceledTime":null,"detail":[{"orderId":"E152462827532999","price":1,"amount":5,"fee":0.005,"time":1524628275750}]},{"orderId":"E152462589467321","memberId":51,"type":"LIMIT_PRICE","amount":2,"symbol":"BTC/USDT","tradedAmount":0,"turnover":0,"coinSymbol":"BTC","baseSymbol":"USDT","status":"CANCELED","direction":"SELL","price":6748.99,"time":1524625894673,"completedTime":null,"canceledTime":1524642616833,"detail":[]},{"orderId":"E152462584630077","memberId":51,"type":"LIMIT_PRICE","amount":10,"symbol":"BTC/USDT","tradedAmount":10,"turnover":67487.1,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":9713,"time":1524625846300,"completedTime":1524625847840,"canceledTime":null,"detail":[{"orderId":"E152462584630077","price":6748.71,"amount":10,"fee":0.01,"time":1524625846762}]},{"orderId":"E152462580272798","memberId":51,"type":"LIMIT_PRICE","amount":1,"symbol":"BTC/USDT","tradedAmount":1,"turnover":1,"coinSymbol":"BTC","baseSymbol":"USDT","status":"COMPLETED","direction":"BUY","price":1,"time":1524625802727,"completedTime":1524625804438,"canceledTime":null,"detail":[{"orderId":"E152462580272798","price":1,"amount":1,"fee":0.001,"time":1524625803200}]}]
     * last : false
     * totalElements : 61
     * totalPages : 7
     * size : 10
     * number : 0
     * sort : [{"direction":"DESC","property":"time","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
     * first : true
     * numberOfElements : 10
     */
        /**
         * orderId : E152464256123665
         * memberId : 51
         * type : LIMIT_PRICE
         * amount : 1
         * symbol : BTC/USDT
         * tradedAmount : 1
         * turnover : 6748.71
         * coinSymbol : BTC
         * baseSymbol : USDT
         * status : COMPLETED
         * direction : BUY
         * price : 15555
         * time : 1524642561236
         * completedTime : 1524642562856
         * canceledTime : null
         * detail : [{"orderId":"E152464256123665","price":6748.71,"amount":1,"fee":0.001,"time":1524642561695}]
         */

        private String orderId;
        private int memberId;
        private String type;
        private double amount;
        private String symbol;
        private double tradedAmount;
        private double turnover;
        private String coinSymbol;
        private String baseSymbol;
        private String status;
        private String direction;
        private double price;
        private long time;
        private long completedTime;
        private Object canceledTime;
        private List<DetailBean> detail;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getTradedAmount() {
            return tradedAmount;
        }

        public void setTradedAmount(double tradedAmount) {
            this.tradedAmount = tradedAmount;
        }

        public double getTurnover() {
            return turnover;
        }

        public void setTurnover(double turnover) {
            this.turnover = turnover;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getCompletedTime() {
            return completedTime;
        }

        public void setCompletedTime(long completedTime) {
            this.completedTime = completedTime;
        }

        public Object getCanceledTime() {
            return canceledTime;
        }

        public void setCanceledTime(Object canceledTime) {
            this.canceledTime = canceledTime;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean implements Serializable{
            /**
             * orderId : E152464256123665
             * price : 6748.71
             * amount : 1
             * fee : 0.001
             * time : 1524642561695
             */

            private String orderId;
            private double price;
            private double amount;
            private double fee;
            private long time;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }
        }

}
