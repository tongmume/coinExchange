//
//  MentionCoinInfoModel.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MentionCoinInfoModel.h"

@implementation MentionCoinInfoModel
+ (NSDictionary *)mj_objectClassInArray
{
    return @{@"addresses":[AddressInfo class]};
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
}

@end
@implementation AddressInfo

@end
