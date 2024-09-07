//
//  PayAccountInfoModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/5/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
@class PayAccountAlipayModel;
@class PayAccountBankInfoModel;
@class PayAccountWechatPayModel;
@interface PayAccountInfoModel : NSObject
@property(nonatomic,copy)NSString *aliVerified;
@property(nonatomic,copy)NSString *bankVerified;
@property(nonatomic,copy)NSString *realName;
@property(nonatomic,copy)NSString *wechatVerified;
@property(nonatomic,strong)PayAccountAlipayModel *alipay;
@property(nonatomic,strong)PayAccountWechatPayModel *wechatPay;
@property(nonatomic,strong)PayAccountBankInfoModel *bankInfo;
@end
@interface PayAccountAlipayModel : NSObject
@property(nonatomic,copy)NSString *aliNo;
@property(nonatomic,copy)NSString *qrCodeUrl;

@end
@interface PayAccountBankInfoModel : NSObject
@property(nonatomic,copy)NSString *bank;
@property(nonatomic,copy)NSString *branch;
@property(nonatomic,copy)NSString *cardNo;

@end
@interface PayAccountWechatPayModel : NSObject
@property(nonatomic,copy)NSString *qrWeCodeUrl;
@property(nonatomic,copy)NSString *wechat;
@end
