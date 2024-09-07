//
//  ContractLeftMenuViewController.h
//  digitalCurrency
//
//  Created by ios on 2020/9/18.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "BaseViewController.h"


#define NSNotification_CONTRACTMENULIST   @"NSNotification_CONTRACTMENULIST"

NS_ASSUME_NONNULL_BEGIN
@class symbolModel;

@interface ContractLeftMenuViewController : BaseViewController

@property (nonatomic, assign) BOOL isObserverNotificantion;

@property (nonatomic, copy) void (^selcetContractcoinSymbolModelBlock)(symbolModel *model);

- (void)showLeftContractMenu;

@end

NS_ASSUME_NONNULL_END
