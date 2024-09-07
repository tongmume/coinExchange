//
//  ChargeMoneyViewController.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "WalletManageModel.h"

@interface ChargeMoneyViewController : BaseViewController
@property(nonatomic,copy)NSString *unit;
@property(nonatomic,copy)NSString *address;
@property(nonatomic,copy)NSString *accountType;
@property(nonatomic,strong)WalletManageModel *model;
@end
