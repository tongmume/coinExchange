//
//  commissionModel.h
//  digitalCurrency
//
//  Created by sunliang on 2019/2/25.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseModel.h"

@interface commissionModel : BaseModel
@property(nonatomic,copy)NSString *orderId;
@property(nonatomic,copy)NSString *memberId;
@property(nonatomic,copy)NSString *type;
@property(nonatomic,copy)NSString *amount;
@property(nonatomic,copy)NSString *symbol;
@property(nonatomic,copy)NSString *tradedAmount;
@property(nonatomic,copy)NSString *turnover;
@property(nonatomic,copy)NSString *coinSymbol;
@property(nonatomic,copy)NSString *baseSymbol;
@property(nonatomic,copy)NSString *status;
@property(nonatomic,copy)NSString *direction;
@property(nonatomic,copy)NSString *price;
@property(nonatomic,copy)NSString *time;
@property(nonatomic,copy)NSString *completedTime;
@property(nonatomic,copy)NSString *canceledTime;
@property(nonatomic,copy)NSString *useDiscount;//0 不使用 1使用
@end
