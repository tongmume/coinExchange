//
//  OptionCoinModel.m
//  digitalCurrency
//
//  Created by chan on 2021/1/4.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "OptionCoinModel.h"

@implementation OptionCoinModel

+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{@"ID":@"id",
             @"buyReward":@"initBuyReward",
             @"sellReward":@"initSellReward"
    };
}

@end
