//
//  MyBillDetailInfoModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/3.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
@class PayInfoModel;
@class AlipayModel;
@class BankInfoModel;
@class WechatPayModel;
@interface MyBillDetailInfoModel : NSObject
@property(nonatomic,copy)NSString *amount;
@property(nonatomic,copy)NSString *commission;
@property(nonatomic,copy)NSString *createTime;
@property(nonatomic,copy)NSString *hisId;
@property(nonatomic,copy)NSString *money;
@property(nonatomic,copy)NSString *myId;
@property(nonatomic,copy)NSString *orderSn;
@property(nonatomic,copy)NSString *otherSide;
@property(nonatomic,strong)PayInfoModel *payInfo;
@property(nonatomic,copy)NSString *payTime;
@property(nonatomic,copy)NSString *price;
@property(nonatomic,copy)NSString *status;
@property(nonatomic,copy)NSString *timeLimit;
@property(nonatomic,copy)NSString *type;
@property(nonatomic,copy)NSString *unit;
@property(nonatomic,copy)NSString *avatar;

@end

@interface PayInfoModel : NSObject
@property(nonatomic,strong)AlipayModel *alipay;
@property(nonatomic,strong)BankInfoModel *bankInfo;
@property(nonatomic,copy)NSString *realName;
@property(nonatomic,strong)WechatPayModel *wechatPay;
@end

@interface AlipayModel : NSObject
@property(nonatomic,copy)NSString *aliNo;
@property(nonatomic,copy)NSString *qrCodeUrl;

@end
@interface BankInfoModel : NSObject
@property(nonatomic,copy)NSString *bank;
@property(nonatomic,copy)NSString *branch;
@property(nonatomic,copy)NSString *cardNo;

@end
@interface WechatPayModel : NSObject
@property(nonatomic,copy)NSString *qrWeCodeUrl;
@property(nonatomic,copy)NSString *wechat;

@end
