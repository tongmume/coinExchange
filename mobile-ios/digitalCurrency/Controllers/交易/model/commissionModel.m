//
//  commissionModel.m
//  digitalCurrency
//
//  Created by sunliang on 2019/2/25.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "commissionModel.h"

@implementation commissionModel

- (void)setValue:(id)value forKey:(NSString *)key{
    [super setValue:value forKey:key];
    if ([key isEqualToString:@"amount"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.amount = [number stringValue];
        }
    }

    if ([key isEqualToString:@"price"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.price = [number stringValue];
        }
    }
}

@end
