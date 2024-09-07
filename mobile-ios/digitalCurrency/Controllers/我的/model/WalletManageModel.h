//
//  WalletManageModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
@class WalletManageCoinInfoModel;

@interface WalletManageModel : NSObject
@property(nonatomic,copy)NSString *address;
@property(nonatomic,copy)NSString *balance;
@property(nonatomic,strong)WalletManageCoinInfoModel *coin;
@property(nonatomic,copy)NSString *frozenBalance;
@property(nonatomic,copy)NSString *ID;
@property(nonatomic,copy)NSString *memberId;
@property(nonatomic,copy)NSString *clickIndex;
@property(nonatomic,copy)NSString *toReleased;
@property(nonatomic,copy)NSString *memo;

@end

@interface WalletManageCoinInfoModel : NSObject
@property(nonatomic,copy)NSString *canAutoWithdraw;
@property(nonatomic,copy)NSString *canRecharge;//能否充币
@property(nonatomic,copy)NSString *canWithdraw;//能否提币
@property(nonatomic,copy)NSString *cnyRate;
@property(nonatomic,copy)NSString *enableRpc;
@property(nonatomic,copy)NSString *maxTxFee;
@property(nonatomic,copy)NSString *maxWithdrawAmount;
@property(nonatomic,copy)NSString *minTxFee;
@property(nonatomic,copy)NSString *minWithdrawAmount;
@property(nonatomic,copy)NSString *name;
@property(nonatomic,copy)NSString *nameCn;
@property(nonatomic,copy)NSString *sort;
@property(nonatomic,copy)NSString *status;
@property(nonatomic,copy)NSString *unit;
@property(nonatomic,copy)NSString *usdRate;
@property(nonatomic,copy)NSString *withdrawThreshold;
@property(nonatomic,copy)NSString *accountType;
@property(nonatomic,copy)NSString *minRechargeAmount;
@property(nonatomic,copy)NSString *depositAddress;
@end
