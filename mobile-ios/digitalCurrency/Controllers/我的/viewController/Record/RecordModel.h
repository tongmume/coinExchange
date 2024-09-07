//
//  RecordModel.h
//  digitalCurrency
//
//  Created by startlink on 2019/8/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
@class Coin;
@interface RecordModel : NSObject
@property (nonatomic,copy)NSString *ID;
@property (nonatomic,copy)NSString *memberId;
@property (nonatomic,copy)NSString *totalAmount;
@property (nonatomic,copy)NSString *fee;
@property (nonatomic,copy)NSString *arrivedAmount;
@property (nonatomic,copy)NSString *createTime;
@property (nonatomic,copy)NSString *dealTime;
@property (nonatomic,copy)NSString *admin;
@property (nonatomic,copy)NSString *transactionNumber;
@property (nonatomic,copy)NSString *address;
@property (nonatomic,copy)NSString *remark;
@property (nonatomic,assign)NSInteger status;
@property (nonatomic,assign)BOOL isAuto;
@property (nonatomic,assign)BOOL canAutoWithdraw;

@property (nonatomic,strong)Coin *coin;

@end


@interface Coin : NSObject;
@property (nonatomic,copy)NSString *name;
@property (nonatomic,copy)NSString *unit;

@end
