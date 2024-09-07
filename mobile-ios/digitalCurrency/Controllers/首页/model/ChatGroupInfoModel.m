//
//  ChatGroupInfoModel.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/16.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChatGroupInfoModel.h"

@implementation ChatGroupInfoModel
+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{@"avatar":@"fromAvatar"};
}
@end
