package com.bizzan.entity;

import java.io.Serializable;

/**
 * data: 2020/9/9.
 */
public class WalletConstract implements Serializable {

    /**
     * id : 1011
     * memberId : 10005
     * contractCoin : {"id":2,"name":"","symbol":"ETH/USDT","coinSymbol":"ETH","baseSymbol":"USDT","sort":1,"coinScale":4,"baseCoinScale":4,"type":"PERPETUAL","enable":1,"visible":1,"exchangeable":1,"enableOpenSell":1,"enableOpenBuy":1,"enableMarketSell":1,"enableMarketBuy":1,"enableTriggerEntrust":1,"spreadType":1,"spread":0,"leverageType":2,"leverage":"10,100","shareNumber":100,"minShare":1,"maxShare":1000,"intervalHour":1,"feePercent":0.001,"maintenanceMarginRate":0.005,"openFee":1.0E-4,"closeFee":1.0E-4,"takerFee":1.0E-4,"makerFee":1.0E-4,"totalProfit":191.366625,"totalLoss":262.36515,"totalOpenFee":0.2,"totalCloseFee":0.4,"currentTime":null,"currentPrice":347.27,"usdtRate":7}
     * usdtBalance : 270.398525
     * usdtFrozenBalance : 0.0
     * usdtPattern : FIXED
     * usdtBuyLeverage : 53.0
     * usdtSellLeverage : 100.0
     * usdtBuyPosition : 0.0
     * usdtFrozenBuyPosition : 0.0
     * usdtBuyPrice : 341.45
     * usdtBuyPrincipalAmount : 0.0
     * usdtSellPosition : 0.0
     * usdtFrozenSellPosition : 0.0
     * usdtShareNumber : 100.0
     * usdtSellPrice : 339.6
     * usdtSellPrincipalAmount : 0.0
     * usdtProfit : 262.36515
     * usdtLoss : 171.366625
     * coinBalance : 0.0
     * coinFrozenBalance : 0.0
     * coinPattern : FIXED
     * coinBuyLeverage : 1.0
     * coinSellLeverage : 1.0
     * coinBuyPosition : 0.0
     * coinFrozenBuyPosition : 0.0
     * coinBuyPrice : 0.0
     * coinBuyPrincipalAmount : 0.0
     * coinSellPosition : 0.0
     * coinFrozenSellPosition : 0.0
     * coinShareNumber : 0.0
     * coinSellPrice : 0.0
     * coinSellPrincipalAmount : 0.0
     * usdtTotalProfitAndLoss : 0.0
     * coinTotalProfitAndLoss : 0
     * currentPrice : null
     */

    private int id;
    private int memberId;
    private ContractCoinBean contractCoin;
    private double usdtBalance;
    private double usdtFrozenBalance;
    private String usdtPattern;
    private double usdtBuyLeverage;
    private double usdtSellLeverage;
    private double usdtBuyPosition;
    private double usdtFrozenBuyPosition;
    private double usdtBuyPrice;
    private double usdtBuyPrincipalAmount;
    private double usdtSellPosition;
    private double usdtFrozenSellPosition;
    private double usdtShareNumber;
    private double usdtSellPrice;
    private double usdtSellPrincipalAmount;
    private double usdtProfit;
    private double usdtLoss;
    private double coinBalance;
    private double coinFrozenBalance;
    private String coinPattern;
    private double coinBuyLeverage;
    private double coinSellLeverage;
    private double coinBuyPosition;
    private double coinFrozenBuyPosition;
    private double coinBuyPrice;
    private double coinBuyPrincipalAmount;
    private double coinSellPosition;
    private double coinFrozenSellPosition;
    private double coinShareNumber;
    private double coinSellPrice;
    private double coinSellPrincipalAmount;
    private double usdtTotalProfitAndLoss;
    private int coinTotalProfitAndLoss;
    private Object currentPrice;

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

    public ContractCoinBean getContractCoin() {
        return contractCoin;
    }

    public void setContractCoin(ContractCoinBean contractCoin) {
        this.contractCoin = contractCoin;
    }

    public double getUsdtBalance() {
        return usdtBalance;
    }

    public void setUsdtBalance(double usdtBalance) {
        this.usdtBalance = usdtBalance;
    }

    public double getUsdtFrozenBalance() {
        return usdtFrozenBalance;
    }

    public void setUsdtFrozenBalance(double usdtFrozenBalance) {
        this.usdtFrozenBalance = usdtFrozenBalance;
    }

    public String getUsdtPattern() {
        return usdtPattern;
    }

    public void setUsdtPattern(String usdtPattern) {
        this.usdtPattern = usdtPattern;
    }

    public double getUsdtBuyLeverage() {
        return usdtBuyLeverage;
    }

    public void setUsdtBuyLeverage(double usdtBuyLeverage) {
        this.usdtBuyLeverage = usdtBuyLeverage;
    }

    public double getUsdtSellLeverage() {
        return usdtSellLeverage;
    }

    public void setUsdtSellLeverage(double usdtSellLeverage) {
        this.usdtSellLeverage = usdtSellLeverage;
    }

    public double getUsdtBuyPosition() {
        return usdtBuyPosition;
    }

    public void setUsdtBuyPosition(double usdtBuyPosition) {
        this.usdtBuyPosition = usdtBuyPosition;
    }

    public double getUsdtFrozenBuyPosition() {
        return usdtFrozenBuyPosition;
    }

    public void setUsdtFrozenBuyPosition(double usdtFrozenBuyPosition) {
        this.usdtFrozenBuyPosition = usdtFrozenBuyPosition;
    }

    public double getUsdtBuyPrice() {
        return usdtBuyPrice;
    }

    public void setUsdtBuyPrice(double usdtBuyPrice) {
        this.usdtBuyPrice = usdtBuyPrice;
    }

    public double getUsdtBuyPrincipalAmount() {
        return usdtBuyPrincipalAmount;
    }

    public void setUsdtBuyPrincipalAmount(double usdtBuyPrincipalAmount) {
        this.usdtBuyPrincipalAmount = usdtBuyPrincipalAmount;
    }

    public double getUsdtSellPosition() {
        return usdtSellPosition;
    }

    public void setUsdtSellPosition(double usdtSellPosition) {
        this.usdtSellPosition = usdtSellPosition;
    }

    public double getUsdtFrozenSellPosition() {
        return usdtFrozenSellPosition;
    }

    public void setUsdtFrozenSellPosition(double usdtFrozenSellPosition) {
        this.usdtFrozenSellPosition = usdtFrozenSellPosition;
    }

    public double getUsdtShareNumber() {
        return usdtShareNumber;
    }

    public void setUsdtShareNumber(double usdtShareNumber) {
        this.usdtShareNumber = usdtShareNumber;
    }

    public double getUsdtSellPrice() {
        return usdtSellPrice;
    }

    public void setUsdtSellPrice(double usdtSellPrice) {
        this.usdtSellPrice = usdtSellPrice;
    }

    public double getUsdtSellPrincipalAmount() {
        return usdtSellPrincipalAmount;
    }

    public void setUsdtSellPrincipalAmount(double usdtSellPrincipalAmount) {
        this.usdtSellPrincipalAmount = usdtSellPrincipalAmount;
    }

    public double getUsdtProfit() {
        return usdtProfit;
    }

    public void setUsdtProfit(double usdtProfit) {
        this.usdtProfit = usdtProfit;
    }

    public double getUsdtLoss() {
        return usdtLoss;
    }

    public void setUsdtLoss(double usdtLoss) {
        this.usdtLoss = usdtLoss;
    }

    public double getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(double coinBalance) {
        this.coinBalance = coinBalance;
    }

    public double getCoinFrozenBalance() {
        return coinFrozenBalance;
    }

    public void setCoinFrozenBalance(double coinFrozenBalance) {
        this.coinFrozenBalance = coinFrozenBalance;
    }

    public String getCoinPattern() {
        return coinPattern;
    }

    public void setCoinPattern(String coinPattern) {
        this.coinPattern = coinPattern;
    }

    public double getCoinBuyLeverage() {
        return coinBuyLeverage;
    }

    public void setCoinBuyLeverage(double coinBuyLeverage) {
        this.coinBuyLeverage = coinBuyLeverage;
    }

    public double getCoinSellLeverage() {
        return coinSellLeverage;
    }

    public void setCoinSellLeverage(double coinSellLeverage) {
        this.coinSellLeverage = coinSellLeverage;
    }

    public double getCoinBuyPosition() {
        return coinBuyPosition;
    }

    public void setCoinBuyPosition(double coinBuyPosition) {
        this.coinBuyPosition = coinBuyPosition;
    }

    public double getCoinFrozenBuyPosition() {
        return coinFrozenBuyPosition;
    }

    public void setCoinFrozenBuyPosition(double coinFrozenBuyPosition) {
        this.coinFrozenBuyPosition = coinFrozenBuyPosition;
    }

    public double getCoinBuyPrice() {
        return coinBuyPrice;
    }

    public void setCoinBuyPrice(double coinBuyPrice) {
        this.coinBuyPrice = coinBuyPrice;
    }

    public double getCoinBuyPrincipalAmount() {
        return coinBuyPrincipalAmount;
    }

    public void setCoinBuyPrincipalAmount(double coinBuyPrincipalAmount) {
        this.coinBuyPrincipalAmount = coinBuyPrincipalAmount;
    }

    public double getCoinSellPosition() {
        return coinSellPosition;
    }

    public void setCoinSellPosition(double coinSellPosition) {
        this.coinSellPosition = coinSellPosition;
    }

    public double getCoinFrozenSellPosition() {
        return coinFrozenSellPosition;
    }

    public void setCoinFrozenSellPosition(double coinFrozenSellPosition) {
        this.coinFrozenSellPosition = coinFrozenSellPosition;
    }

    public double getCoinShareNumber() {
        return coinShareNumber;
    }

    public void setCoinShareNumber(double coinShareNumber) {
        this.coinShareNumber = coinShareNumber;
    }

    public double getCoinSellPrice() {
        return coinSellPrice;
    }

    public void setCoinSellPrice(double coinSellPrice) {
        this.coinSellPrice = coinSellPrice;
    }

    public double getCoinSellPrincipalAmount() {
        return coinSellPrincipalAmount;
    }

    public void setCoinSellPrincipalAmount(double coinSellPrincipalAmount) {
        this.coinSellPrincipalAmount = coinSellPrincipalAmount;
    }

    public double getUsdtTotalProfitAndLoss() {
        return usdtTotalProfitAndLoss;
    }

    public void setUsdtTotalProfitAndLoss(double usdtTotalProfitAndLoss) {
        this.usdtTotalProfitAndLoss = usdtTotalProfitAndLoss;
    }

    public int getCoinTotalProfitAndLoss() {
        return coinTotalProfitAndLoss;
    }

    public void setCoinTotalProfitAndLoss(int coinTotalProfitAndLoss) {
        this.coinTotalProfitAndLoss = coinTotalProfitAndLoss;
    }

    public Object getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Object currentPrice) {
        this.currentPrice = currentPrice;
    }

    public static class ContractCoinBean implements Serializable{
        /**
         * id : 2
         * name :
         * symbol : ETH/USDT
         * coinSymbol : ETH
         * baseSymbol : USDT
         * sort : 1
         * coinScale : 4
         * baseCoinScale : 4
         * type : PERPETUAL
         * enable : 1
         * visible : 1
         * exchangeable : 1
         * enableOpenSell : 1
         * enableOpenBuy : 1
         * enableMarketSell : 1
         * enableMarketBuy : 1
         * enableTriggerEntrust : 1
         * spreadType : 1
         * spread : 0.0
         * leverageType : 2
         * leverage : 10,100
         * shareNumber : 100.0
         * minShare : 1.0
         * maxShare : 1000.0
         * intervalHour : 1
         * feePercent : 0.001
         * maintenanceMarginRate : 0.005
         * openFee : 1.0E-4
         * closeFee : 1.0E-4
         * takerFee : 1.0E-4
         * makerFee : 1.0E-4
         * totalProfit : 191.366625
         * totalLoss : 262.36515
         * totalOpenFee : 0.2
         * totalCloseFee : 0.4
         * currentTime : null
         * currentPrice : 347.27
         * usdtRate : 7
         */

        private int id;
        private String name;
        private String symbol;
        private String coinSymbol;
        private String baseSymbol;
        private int sort;
        private int coinScale;
        private int baseCoinScale;
        private String type;
        private int enable;
        private int visible;
        private int exchangeable;
        private int enableOpenSell;
        private int enableOpenBuy;
        private int enableMarketSell;
        private int enableMarketBuy;
        private int enableTriggerEntrust;
        private int spreadType;
        private double spread;
        private int leverageType;
        private String leverage;
        private double shareNumber;
        private double minShare;
        private double maxShare;
        private int intervalHour;
        private double feePercent;
        private double maintenanceMarginRate;
        private double openFee;
        private double closeFee;
        private double takerFee;
        private double makerFee;
        private double totalProfit;
        private double totalLoss;
        private double totalOpenFee;
        private double totalCloseFee;
        private Object currentTime;
        private double currentPrice;
        private double usdtRate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public int getExchangeable() {
            return exchangeable;
        }

        public void setExchangeable(int exchangeable) {
            this.exchangeable = exchangeable;
        }

        public int getEnableOpenSell() {
            return enableOpenSell;
        }

        public void setEnableOpenSell(int enableOpenSell) {
            this.enableOpenSell = enableOpenSell;
        }

        public int getEnableOpenBuy() {
            return enableOpenBuy;
        }

        public void setEnableOpenBuy(int enableOpenBuy) {
            this.enableOpenBuy = enableOpenBuy;
        }

        public int getEnableMarketSell() {
            return enableMarketSell;
        }

        public void setEnableMarketSell(int enableMarketSell) {
            this.enableMarketSell = enableMarketSell;
        }

        public int getEnableMarketBuy() {
            return enableMarketBuy;
        }

        public void setEnableMarketBuy(int enableMarketBuy) {
            this.enableMarketBuy = enableMarketBuy;
        }

        public int getEnableTriggerEntrust() {
            return enableTriggerEntrust;
        }

        public void setEnableTriggerEntrust(int enableTriggerEntrust) {
            this.enableTriggerEntrust = enableTriggerEntrust;
        }

        public int getSpreadType() {
            return spreadType;
        }

        public void setSpreadType(int spreadType) {
            this.spreadType = spreadType;
        }

        public double getSpread() {
            return spread;
        }

        public void setSpread(double spread) {
            this.spread = spread;
        }

        public int getLeverageType() {
            return leverageType;
        }

        public void setLeverageType(int leverageType) {
            this.leverageType = leverageType;
        }

        public String getLeverage() {
            return leverage;
        }

        public void setLeverage(String leverage) {
            this.leverage = leverage;
        }

        public double getShareNumber() {
            return shareNumber;
        }

        public void setShareNumber(double shareNumber) {
            this.shareNumber = shareNumber;
        }

        public double getMinShare() {
            return minShare;
        }

        public void setMinShare(double minShare) {
            this.minShare = minShare;
        }

        public double getMaxShare() {
            return maxShare;
        }

        public void setMaxShare(double maxShare) {
            this.maxShare = maxShare;
        }

        public int getIntervalHour() {
            return intervalHour;
        }

        public void setIntervalHour(int intervalHour) {
            this.intervalHour = intervalHour;
        }

        public double getFeePercent() {
            return feePercent;
        }

        public void setFeePercent(double feePercent) {
            this.feePercent = feePercent;
        }

        public double getMaintenanceMarginRate() {
            return maintenanceMarginRate;
        }

        public void setMaintenanceMarginRate(double maintenanceMarginRate) {
            this.maintenanceMarginRate = maintenanceMarginRate;
        }

        public double getOpenFee() {
            return openFee;
        }

        public void setOpenFee(double openFee) {
            this.openFee = openFee;
        }

        public double getCloseFee() {
            return closeFee;
        }

        public void setCloseFee(double closeFee) {
            this.closeFee = closeFee;
        }

        public double getTakerFee() {
            return takerFee;
        }

        public void setTakerFee(double takerFee) {
            this.takerFee = takerFee;
        }

        public double getMakerFee() {
            return makerFee;
        }

        public void setMakerFee(double makerFee) {
            this.makerFee = makerFee;
        }

        public double getTotalProfit() {
            return totalProfit;
        }

        public void setTotalProfit(double totalProfit) {
            this.totalProfit = totalProfit;
        }

        public double getTotalLoss() {
            return totalLoss;
        }

        public void setTotalLoss(double totalLoss) {
            this.totalLoss = totalLoss;
        }

        public double getTotalOpenFee() {
            return totalOpenFee;
        }

        public void setTotalOpenFee(double totalOpenFee) {
            this.totalOpenFee = totalOpenFee;
        }

        public double getTotalCloseFee() {
            return totalCloseFee;
        }

        public void setTotalCloseFee(double totalCloseFee) {
            this.totalCloseFee = totalCloseFee;
        }

        public Object getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(Object currentTime) {
            this.currentTime = currentTime;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getUsdtRate() {
            return usdtRate;
        }

        public void setUsdtRate(double usdtRate) {
            this.usdtRate = usdtRate;
        }
    }
}
