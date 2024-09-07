//
//  MyAdvertisingDetailModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "countryModel.h"
@interface MyAdvertisingDetailModel : NSObject
@property(nonatomic,copy)NSString *advertiseType;
@property(nonatomic,copy)NSString *isAuto;
@property(nonatomic,copy)NSString *autoword;
@property(nonatomic,copy)NSString *coinId;
@property(nonatomic,copy)NSString *coinName;
@property(nonatomic,copy)NSString *coinNameCn;
@property(nonatomic,copy)NSString *coinUnit;
@property(nonatomic,strong)countryModel *country;
@property(nonatomic,copy)NSString *ID;
@property(nonatomic,copy)NSString *marketPrice;
@property(nonatomic,copy)NSString *maxLimit;
@property(nonatomic,copy)NSString *minLimit;
@property(nonatomic,copy)NSString *number;
@property(nonatomic,copy)NSString *payMode;
@property(nonatomic,copy)NSString *premiseRate;
@property(nonatomic,copy)NSString *price;
@property(nonatomic,copy)NSString *priceType;
@property(nonatomic,copy)NSString *remark;
@property(nonatomic,copy)NSString *status;
@property(nonatomic,copy)NSString *timeLimit;
@end
