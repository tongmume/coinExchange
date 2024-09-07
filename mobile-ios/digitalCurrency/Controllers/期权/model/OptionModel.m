//
//  OptionModel.m
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionModel.h"

@implementation OptionModel

- (instancetype)init {
    if (self = [super init]) {
        self.closeTimeGap = 300;
        self.openTimeGap = 300;
    }
    return self;
}

+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{@"ID":@"id"};
}

@end
