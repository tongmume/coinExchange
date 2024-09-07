//
//  TradeHistoryModel.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "TradeHistoryModel.h"

@implementation TradeHistoryModel
+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{@"ID":@"id"};
}

@end


@implementation assetsourcemodel
- (void)setValue:(id)value forKey:(NSString *)key{
    [super setValue:value forKey:key];

    if ([key isEqualToString:@"amount"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.amount = [number stringValue];
        }
    }

    if ([key isEqualToString:@"discount_fee"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.discountFee = [number stringValue];
        }
    }

    if ([key isEqualToString:@"real_fee"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.realFee = [number stringValue];
        }
    }
}

@end
