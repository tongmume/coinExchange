package com.bizzan.entity;

/**
 * author: wuzongjie
 * time  : 2018/4/27 0027 09:43
 * desc  :
 */

public class Money {

    /**
     * data : {"id":260,"memberId":51,"coin":{"name":"USDT","nameCn":"泰达币T","unit":"USDT","status":0,"minTxFee":2.0E-4,"cnyRate":0,"maxTxFee":2.0E-4,"usdRate":0,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":1,"withdrawThreshold":0.1,"minWithdrawAmount":0.001,"maxWithdrawAmount":5,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":null,"minerFee":0,"withdrawScale":4},"balance":715999.18984241,"frozenBalance":53609.984005,"address":"1Gmmq5yf57DAEzm2tybggApF8rbXVs8h4M","isLock":0}
     * code : 0
     * message : success
     */

    private DataBean data;
    private int code;
    private String message;

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

    public static class DataBean {
        /**
         * id : 260
         * memberId : 51
         * coin : {"name":"USDT","nameCn":"泰达币T","unit":"USDT","status":0,"minTxFee":2.0E-4,"cnyRate":0,"maxTxFee":2.0E-4,"usdRate":0,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":1,"withdrawThreshold":0.1,"minWithdrawAmount":0.001,"maxWithdrawAmount":5,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":null,"minerFee":0,"withdrawScale":4}
         * balance : 715999.18984241
         * frozenBalance : 53609.984005
         * address : 1Gmmq5yf57DAEzm2tybggApF8rbXVs8h4M
         * isLock : 0
         */

        private int id;
        private int memberId;
        private CoinBean coin;
        private double balance;
        private double frozenBalance;
        private String address;
        private int isLock;

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

        public CoinBean getCoin() {
            return coin;
        }

        public void setCoin(CoinBean coin) {
            this.coin = coin;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public double getFrozenBalance() {
            return frozenBalance;
        }

        public void setFrozenBalance(double frozenBalance) {
            this.frozenBalance = frozenBalance;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIsLock() {
            return isLock;
        }

        public void setIsLock(int isLock) {
            this.isLock = isLock;
        }

        public static class CoinBean {
            /**
             * name : USDT
             * nameCn : 泰达币T
             * unit : USDT
             * status : 0
             * minTxFee : 2.0E-4
             * cnyRate : 0.0
             * maxTxFee : 2.0E-4
             * usdRate : 0.0
             * enableRpc : 1
             * sort : 1
             * canWithdraw : 1
             * canRecharge : 1
             * canTransfer : 1
             * canAutoWithdraw : 1
             * withdrawThreshold : 0.1
             * minWithdrawAmount : 0.001
             * maxWithdrawAmount : 5.0
             * isPlatformCoin : 0
             * hasLegal : false
             * allBalance : null
             * coldWalletAddress : null
             * hotAllBalance : null
             * minerFee : 0.0
             * withdrawScale : 4
             */

            private String name;
            private String nameCn;
            private String unit;
            private int status;
            private double minTxFee;
            private double cnyRate;
            private double maxTxFee;
            private double usdRate;
            private int enableRpc;
            private int sort;
            private int canWithdraw;
            private int canRecharge;
            private int canTransfer;
            private int canAutoWithdraw;
            private double withdrawThreshold;
            private double minWithdrawAmount;
            private double maxWithdrawAmount;
            private int isPlatformCoin;
            private boolean hasLegal;
            private Object allBalance;
            private Object coldWalletAddress;
            private Object hotAllBalance;
            private double minerFee;
            private int withdrawScale;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameCn() {
                return nameCn;
            }

            public void setNameCn(String nameCn) {
                this.nameCn = nameCn;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public double getMinTxFee() {
                return minTxFee;
            }

            public void setMinTxFee(double minTxFee) {
                this.minTxFee = minTxFee;
            }

            public double getCnyRate() {
                return cnyRate;
            }

            public void setCnyRate(double cnyRate) {
                this.cnyRate = cnyRate;
            }

            public double getMaxTxFee() {
                return maxTxFee;
            }

            public void setMaxTxFee(double maxTxFee) {
                this.maxTxFee = maxTxFee;
            }

            public double getUsdRate() {
                return usdRate;
            }

            public void setUsdRate(double usdRate) {
                this.usdRate = usdRate;
            }

            public int getEnableRpc() {
                return enableRpc;
            }

            public void setEnableRpc(int enableRpc) {
                this.enableRpc = enableRpc;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getCanWithdraw() {
                return canWithdraw;
            }

            public void setCanWithdraw(int canWithdraw) {
                this.canWithdraw = canWithdraw;
            }

            public int getCanRecharge() {
                return canRecharge;
            }

            public void setCanRecharge(int canRecharge) {
                this.canRecharge = canRecharge;
            }

            public int getCanTransfer() {
                return canTransfer;
            }

            public void setCanTransfer(int canTransfer) {
                this.canTransfer = canTransfer;
            }

            public int getCanAutoWithdraw() {
                return canAutoWithdraw;
            }

            public void setCanAutoWithdraw(int canAutoWithdraw) {
                this.canAutoWithdraw = canAutoWithdraw;
            }

            public double getWithdrawThreshold() {
                return withdrawThreshold;
            }

            public void setWithdrawThreshold(double withdrawThreshold) {
                this.withdrawThreshold = withdrawThreshold;
            }

            public double getMinWithdrawAmount() {
                return minWithdrawAmount;
            }

            public void setMinWithdrawAmount(double minWithdrawAmount) {
                this.minWithdrawAmount = minWithdrawAmount;
            }

            public double getMaxWithdrawAmount() {
                return maxWithdrawAmount;
            }

            public void setMaxWithdrawAmount(double maxWithdrawAmount) {
                this.maxWithdrawAmount = maxWithdrawAmount;
            }

            public int getIsPlatformCoin() {
                return isPlatformCoin;
            }

            public void setIsPlatformCoin(int isPlatformCoin) {
                this.isPlatformCoin = isPlatformCoin;
            }

            public boolean isHasLegal() {
                return hasLegal;
            }

            public void setHasLegal(boolean hasLegal) {
                this.hasLegal = hasLegal;
            }

            public Object getAllBalance() {
                return allBalance;
            }

            public void setAllBalance(Object allBalance) {
                this.allBalance = allBalance;
            }

            public Object getColdWalletAddress() {
                return coldWalletAddress;
            }

            public void setColdWalletAddress(Object coldWalletAddress) {
                this.coldWalletAddress = coldWalletAddress;
            }

            public Object getHotAllBalance() {
                return hotAllBalance;
            }

            public void setHotAllBalance(Object hotAllBalance) {
                this.hotAllBalance = hotAllBalance;
            }

            public double getMinerFee() {
                return minerFee;
            }

            public void setMinerFee(double minerFee) {
                this.minerFee = minerFee;
            }

            public int getWithdrawScale() {
                return withdrawScale;
            }

            public void setWithdrawScale(int withdrawScale) {
                this.withdrawScale = withdrawScale;
            }
        }
    }
}
