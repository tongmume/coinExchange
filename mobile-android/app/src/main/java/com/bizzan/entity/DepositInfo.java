package com.bizzan.entity;

public class DepositInfo {


    /**
     * id : 2
     * coin : {"name":"BHB","nameCn":"","unit":"BHB","status":0,"minTxFee":0.01,"cnyRate":0.2,"maxTxFee":0.09,"usdRate":0.022,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":0.9,"minWithdrawAmount":1,"maxWithdrawAmount":30,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":"","hotAllBalance":null,"minerFee":null,"withdrawScale":8}
     * amount : 10
     * createTime : 2018-07-05 19:53:17
     * admin : null
     * status : 0
     */

    private int id;
    private CoinBean coin;
    private int amount;
    private String createTime;
    private Object admin;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoinBean getCoin() {
        return coin;
    }

    public void setCoin(CoinBean coin) {
        this.coin = coin;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getAdmin() {
        return admin;
    }

    public void setAdmin(Object admin) {
        this.admin = admin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class CoinBean {
        /**
         * name : BHB
         * nameCn :
         * unit : BHB
         * status : 0
         * minTxFee : 0.01
         * cnyRate : 0.2
         * maxTxFee : 0.09
         * usdRate : 0.022
         * enableRpc : 0
         * sort : 1
         * canWithdraw : 0
         * canRecharge : 0
         * canTransfer : 1
         * canAutoWithdraw : 0
         * withdrawThreshold : 0.9
         * minWithdrawAmount : 1
         * maxWithdrawAmount : 30
         * isPlatformCoin : 0
         * hasLegal : false
         * allBalance : null
         * coldWalletAddress :
         * hotAllBalance : null
         * minerFee : null
         * withdrawScale : 8
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
        private String coldWalletAddress;
        private Object hotAllBalance;
        private Object minerFee;
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

        public String getColdWalletAddress() {
            return coldWalletAddress;
        }

        public void setColdWalletAddress(String coldWalletAddress) {
            this.coldWalletAddress = coldWalletAddress;
        }

        public Object getHotAllBalance() {
            return hotAllBalance;
        }

        public void setHotAllBalance(Object hotAllBalance) {
            this.hotAllBalance = hotAllBalance;
        }

        public Object getMinerFee() {
            return minerFee;
        }

        public void setMinerFee(Object minerFee) {
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