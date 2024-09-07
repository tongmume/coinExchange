//
//  WalletContractCoinModel.h
//  digitalCurrency
//
//  Created by ios on 2020/10/9.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN
@class WalletContractCoinInfo;

@interface WalletContractCoinModel : NSObject

@property(nonatomic,copy)NSString *cnyRate;
@property(nonatomic,copy)NSString *coinBalance;
@property(nonatomic,copy)NSString *coinBuyLeverage;
@property(nonatomic,copy)NSString *coinBuyPosition;
@property(nonatomic,copy)NSString *coinBuyPrice;
@property(nonatomic,copy)NSString *coinBuyPrincipalAmount;
@property(nonatomic,copy)NSString *coinFrozenBalance;
@property(nonatomic,copy)NSString *coinFrozenBuyPosition;
@property(nonatomic,copy)NSString *coinFrozenSellPosition;
@property(nonatomic,copy)NSString *coinPattern;
@property(nonatomic,copy)NSString *coinSellLeverage;
@property(nonatomic,copy)NSString *coinSellPosition;
@property(nonatomic,copy)NSString *coinSellPrice;
@property(nonatomic,copy)NSString *coinSellPrincipalAmount;
@property(nonatomic,copy)NSString *coinShareNumber;
@property(nonatomic,copy)NSString *coinTotalProfitAndLoss;
@property(nonatomic,strong)WalletContractCoinInfo *contractCoin;
@property(nonatomic,copy)NSString *currentPrice;
@property(nonatomic,copy)NSString *id;
@property(nonatomic,copy)NSString *memberId;
@property(nonatomic,copy)NSString *usdtBalance;
@property(nonatomic,copy)NSString *usdtBuyLeverage;
@property(nonatomic,copy)NSString *usdtBuyPosition;
@property(nonatomic,copy)NSString *usdtBuyPrice;
@property(nonatomic,copy)NSString *usdtBuyPrincipalAmount;
@property(nonatomic,copy)NSString *usdtFrozenBalance;
@property(nonatomic,copy)NSString *usdtFrozenBuyPosition;
@property(nonatomic,copy)NSString *usdtFrozenSellPosition;
@property(nonatomic,copy)NSString *usdtLoss;
@property(nonatomic,copy)NSString *usdtPattern;
@property(nonatomic,copy)NSString *usdtProfit;
@property(nonatomic,copy)NSString *usdtSellLeverage;
@property(nonatomic,copy)NSString *usdtSellPosition;
@property(nonatomic,copy)NSString *usdtSellPrice;
@property(nonatomic,copy)NSString *usdtSellPrincipalAmount;
@property(nonatomic,copy)NSString *usdtShareNumber;
@property(nonatomic,copy)NSString *usdtTotalProfitAndLoss;


@end

@interface WalletContractCoinInfo : NSObject

@property(nonatomic,copy)NSString *baseCoinScale;
@property(nonatomic,copy)NSString *baseSymbol;
@property(nonatomic,copy)NSString *closeFee;
@property(nonatomic,copy)NSString *coinScale;
@property(nonatomic,copy)NSString *coinSymbol;
@property(nonatomic,copy)NSString *currentPrice;
@property(nonatomic,copy)NSString *currentTime;
@property(nonatomic,copy)NSString *enable;
@property(nonatomic,copy)NSString *enableMarketBuy;
@property(nonatomic,copy)NSString *enableMarketSell;
@property(nonatomic,copy)NSString *enableOpenBuy;
@property(nonatomic,copy)NSString *enableOpenSell;
@property(nonatomic,copy)NSString *enableTriggerEntrust;
@property(nonatomic,copy)NSString *exchangeable;
@property(nonatomic,copy)NSString *feePercent;
@property(nonatomic,copy)NSString *id;
@property(nonatomic,copy)NSString *intervalHour;
@property(nonatomic,copy)NSString *leverage;
@property(nonatomic,copy)NSString *leverageType;
@property(nonatomic,copy)NSString *maintenanceMarginRate;
@property(nonatomic,copy)NSString *makerFee;
@property(nonatomic,copy)NSString *maxShare;
@property(nonatomic,copy)NSString *minShare;
@property(nonatomic,copy)NSString *name;
@property(nonatomic,copy)NSString *openFee;
@property(nonatomic,copy)NSString *shareNumber;
@property(nonatomic,copy)NSString *sort;
@property(nonatomic,copy)NSString *spread;
@property(nonatomic,copy)NSString *spreadType;
@property(nonatomic,copy)NSString *symbol;
@property(nonatomic,copy)NSString *takerFee;
@property(nonatomic,copy)NSString *totalCloseFee;
@property(nonatomic,copy)NSString *totalLoss;
@property(nonatomic,copy)NSString *totalOpenFee;
@property(nonatomic,copy)NSString *totalProfit;
@property(nonatomic,copy)NSString *type;
@property(nonatomic,copy)NSString *usdtRate;
@property(nonatomic,copy)NSString *visible;
@end

NS_ASSUME_NONNULL_END
