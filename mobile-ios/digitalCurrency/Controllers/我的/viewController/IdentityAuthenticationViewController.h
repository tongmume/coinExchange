//
//  IdentityAuthenticationViewController.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface IdentityAuthenticationViewController : BaseViewController
@property(nonatomic,copy)NSString *identifyStatus;
@property(nonatomic,copy)NSString *realAuditing; //该项表示实名认证是否正在审核中，0表示是，1表示否
@property(nonatomic,copy)NSString *realNameRejectReason;//拒绝理由

@end
