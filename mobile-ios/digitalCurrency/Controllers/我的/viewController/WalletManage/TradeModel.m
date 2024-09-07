//
//  TradeModel.m
//  digitalCurrency
//
//  Created by chu on 2019/7/4.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "TradeModel.h"

@implementation TradeModel

- (void)setValue:(id)value forUndefinedKey:(NSString *)key{

}

- (instancetype)initWithDictionary:(NSDictionary *)dic{
    if (self) {
        [self setValuesForKeysWithDictionary:dic];
    }
    return self;
}

+ (instancetype)modelWithDictionary:(NSDictionary *)dic{
    return [[self alloc] initWithDictionary:dic];
}

@end
