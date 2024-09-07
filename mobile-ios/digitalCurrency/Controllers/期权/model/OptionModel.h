//
//  OptionModel.h
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface OptionModel : NSObject

@property(nonatomic, strong) NSNumber*closePrice;

@property(nonatomic, assign) NSTimeInterval closeTime;

@property(nonatomic, assign) NSTimeInterval createTime;

@property(nonatomic, copy) NSString *ID;

@property(nonatomic, assign) int initBuy;

@property(nonatomic, assign) int initSell;

@property(nonatomic, strong) NSNumber*openPrice;

@property(nonatomic, assign) NSTimeInterval openTime;

@property(nonatomic, copy) NSString *optionNo;

@property(nonatomic, copy) NSString *result;

@property(nonatomic, copy) NSString *status;

@property(nonatomic, copy) NSString *symbol;

@property(nonatomic, copy) NSString *totalBuy;

@property(nonatomic, copy) NSString *totalBuyCount;

@property(nonatomic, copy) NSString *totalPl;

@property(nonatomic, copy) NSString *totalSell;

@property(nonatomic, copy) NSString *totalSellCount;

@property (nonatomic, assign) NSTimeInterval openTimeGap;
@property (nonatomic, assign) NSTimeInterval closeTimeGap;
//{
//    closePrice = "27861.88";
//    closeTime = 1609333500000;
//    createTime = 1609332900022;
//    id = 26910;
//    initBuy = 0;
//    initSell = 0;
//    openPrice = "27861.93";
//    openTime = 1609333200000;
//    optionNo = 8972;
//    result = LOSE;
//    status = CLOSED;
//    symbol = "BTC/USDT";
//    totalBuy = 0;
//    totalBuyCount = 0;
//    totalPl = 0;
//    totalSell = 0;
//    totalSellCount = 0;
//}

@end

NS_ASSUME_NONNULL_END
