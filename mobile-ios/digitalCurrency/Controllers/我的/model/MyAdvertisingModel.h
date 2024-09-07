//
//  MyAdvertisingModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>


@class CoinInfoModel;

@interface MyAdvertisingModel : NSObject
@property(nonatomic,copy)NSString *advertiseType;
@property(nonatomic,copy)NSString *coinUnit;
@property(nonatomic,copy)NSString *createTime;
@property(nonatomic,copy)NSString *maxLimit;
@property(nonatomic,copy)NSString *ID;
@property(nonatomic,copy)NSString *minLimit;
@property(nonatomic,copy)NSString *remainAmount;
@property(nonatomic,copy)NSString *status;
@property(nonatomic,copy)NSString *number;
@property(nonatomic,strong)CoinInfoModel *coin;
@end
@interface CoinInfoModel : NSObject
@property(nonatomic,copy)NSString *buyMinAmount;
@property(nonatomic,copy)NSString *ID;//能否充币
@property(nonatomic,copy)NSString *isPlatformCoin;//能否提币
@property(nonatomic,copy)NSString *jyRate;
@property(nonatomic,copy)NSString *name;
@property(nonatomic,copy)NSString *nameCn;
@property(nonatomic,copy)NSString *sellMinAmount;
@property(nonatomic,copy)NSString *sort;
@property(nonatomic,copy)NSString *status;
@property(nonatomic,copy)NSString *unit;
@end
