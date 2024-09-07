//
//  symbolModel.m
//  digitalCurrency
//
//  Created by sunliang on 2019/2/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "symbolModel.h"

@implementation symbolModel

- (void)setValue:(id)value forKey:(NSString *)key{
    [super setValue:value forKey:key];


    if ([key isEqualToString:@"close"]) {
        if ([value isKindOfClass:[NSNumber class]]) {

            NSString *balance = [NSString stringWithFormat:@"%@",value];
            //            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.close = number;
        }
    }

}

@end
