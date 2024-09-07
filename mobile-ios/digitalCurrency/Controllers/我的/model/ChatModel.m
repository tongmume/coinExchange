//
//  ChatModel.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChatModel.h"

@implementation ChatModel
+ (NSDictionary *)mj_replacedKeyFromPropertyName {
    return @{@"avatar" : @"fromAvatar",
             };
}
@end
