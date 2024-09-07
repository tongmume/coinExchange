//
//  WalletManageModel.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "WalletManageModel.h"

@implementation WalletManageModel

+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{@"ID":@"id"};
}


- (void)setValue:(id)value forKey:(NSString *)key{
    [super setValue:value forKey:key];
    if ([key isEqualToString:@"balance"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.balance = [number stringValue];
        }
    }

    if ([key isEqualToString:@"frozenBalance"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.frozenBalance = [number stringValue];
        }
    }

    if ([key isEqualToString:@"toReleased"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.toReleased = [number stringValue];
        }
    }
}

@end

@implementation WalletManageCoinInfoModel

@end
