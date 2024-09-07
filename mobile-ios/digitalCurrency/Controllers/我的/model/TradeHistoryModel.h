//
//  TradeHistoryModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
@class assetsourcemodel;
@interface TradeHistoryModel : NSObject
@property(nonatomic,copy)NSString *_index;
@property(nonatomic,copy)NSString *_type;
@property(nonatomic,copy)NSString *_id;
@property(nonatomic,strong)assetsourcemodel *_source;


@end

@interface assetsourcemodel :NSObject
@property(nonatomic,copy)NSString *memberId;
@property(nonatomic,copy)NSString *symbol;
@property(nonatomic,copy)NSString *amount;
@property(nonatomic,copy)NSString *address;
@property(nonatomic,copy)NSString *flag;
@property(nonatomic,copy)NSString *createTime;
@property(nonatomic,copy)NSString *realFee;
@property(nonatomic,copy)NSString *fee;
@property(nonatomic,copy)NSString *ID;
@property(nonatomic,copy)NSString *type;
@property(nonatomic,copy)NSString *discountFee;

@end
