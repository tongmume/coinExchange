//
//  BuyOrSellCoinInfo.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/24.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BuyOrSellCoinInfo : NSObject
@property(nonatomic,copy)NSString *advertiseType;
@property(nonatomic,copy)NSString *country;
@property(nonatomic,copy)NSString *emailVerified;
@property(nonatomic,copy)NSString *idCardVerified;
@property(nonatomic,copy)NSString *maxLimit;
@property(nonatomic,copy)NSString *minLimit;
@property(nonatomic,copy)NSString *maxTradableAmount;
@property(nonatomic,copy)NSString *number;
@property(nonatomic,copy)NSString *otcCoinId;
@property(nonatomic,copy)NSString *payMode;
@property(nonatomic,copy)NSString *phoneVerified;
@property(nonatomic,copy)NSString *price;
@property(nonatomic,copy)NSString *remark;
@property(nonatomic,copy)NSString *timeLimit;
@property(nonatomic,copy)NSString *transactions;
@property(nonatomic,copy)NSString *username;
@property(nonatomic,copy)NSString *unit;
@end
