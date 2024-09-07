//
//  MyAdvertisingDetailModel.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyAdvertisingDetailModel.h"

@implementation MyAdvertisingDetailModel
+(NSDictionary *)mj_replacedKeyFromPropertyName{
    return @{@"ID":@"id",@"isAuto":@"auto"};
}
@end

