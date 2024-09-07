//
//  MentionCoinInfoModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
@class AddressInfo;
@interface MentionCoinInfoModel : NSObject
@property(nonatomic,copy)NSArray *addresses;
@property(nonatomic,copy)NSString *balance;
@property(nonatomic,copy)NSString *canAutoWithdraw;
@property(nonatomic,copy)NSString *maxAmount;
@property(nonatomic,copy)NSString *maxTxFee;
@property(nonatomic,copy)NSString *minAmount;
@property(nonatomic,copy)NSString *minTxFee;
@property(nonatomic,copy)NSString *name;
@property(nonatomic,copy)NSString *nameCn;
@property(nonatomic,copy)NSString *threshold;
@property(nonatomic,copy)NSString *unit;
@property(nonatomic,copy)NSString *accountType;
@end


@interface AddressInfo : NSObject
@property(nonatomic,copy)NSString *address;
@property(nonatomic,copy)NSString *remark;
@end
