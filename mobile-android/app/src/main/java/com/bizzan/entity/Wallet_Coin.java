package com.bizzan.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/27.
 */

public class Wallet_Coin implements Serializable {


    /**
     * id : 9113
     * memberId : 10005
     * coin : {"name":"USDT","nameCn":"泰达币","unit":"USDT","status":0,"minTxFee":4,"cnyRate":0,"maxTxFee":10,"usdRate":0,"enableRpc":1,"sort":100,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":1,"minWithdrawAmount":10,"maxWithdrawAmount":10000,"minRechargeAmount":1,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":"14QZK9h8udeB6riNfrNRYhK5ptHzJTmjM5","hotAllBalance":null,"blockHeight":null,"minerFee":null,"withdrawScale":0,"infolink":"","information":"","accountType":0,"depositAddress":""}
     * balance : 2068.850184930159
     * frozenBalance : 21.96624
     * toReleased : null
     * address :
     * isLock : 0
     * memo : null
     */

    private int id;
    private int memberId;
    private CoinBean coin;
    private double balance;
    private double frozenBalance;
    private Object toReleased;
    private String address;
    private int isLock;
    private Object memo;

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

    public Object getToReleased() {
        return toReleased;
    }

    public void setToReleased(Object toReleased) {
        this.toReleased = toReleased;
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

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public static class CoinBean {
        /**
         * name : USDT
         * nameCn : 泰达币
         * unit : USDT
         * status : 0
         * minTxFee : 4.0
         * cnyRate : 0.0
         * maxTxFee : 10.0
         * usdRate : 0.0
         * enableRpc : 1
         * sort : 100
         * canWithdraw : 1
         * canRecharge : 1
         * canTransfer : 1
         * canAutoWithdraw : 0
         * withdrawThreshold : 1.0
         * minWithdrawAmount : 10.0
         * maxWithdrawAmount : 10000.0
         * minRechargeAmount : 1.0
         * isPlatformCoin : 0
         * hasLegal : false
         * allBalance : null
         * coldWalletAddress : 14QZK9h8udeB6riNfrNRYhK5ptHzJTmjM5
         * hotAllBalance : null
         * blockHeight : null
         * minerFee : null
         * withdrawScale : 0
         * infolink :
         * information :
         * accountType : 0
         * depositAddress :
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
        private double minRechargeAmount;
        private int isPlatformCoin;
        private boolean hasLegal;
        private Object allBalance;
        private String coldWalletAddress;
        private Object hotAllBalance;
        private Object blockHeight;
        private Object minerFee;
        private int withdrawScale;
        private String infolink;
        private String information;
        private int accountType;
        private String depositAddress;

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

        public double getMinRechargeAmount() {
            return minRechargeAmount;
        }

        public void setMinRechargeAmount(double minRechargeAmount) {
            this.minRechargeAmount = minRechargeAmount;
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

        public Object getBlockHeight() {
            return blockHeight;
        }

        public void setBlockHeight(Object blockHeight) {
            this.blockHeight = blockHeight;
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

        public String getInfolink() {
            return infolink;
        }

        public void setInfolink(String infolink) {
            this.infolink = infolink;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public String getDepositAddress() {
            return depositAddress;
        }

        public void setDepositAddress(String depositAddress) {
            this.depositAddress = depositAddress;
        }
    }
}

