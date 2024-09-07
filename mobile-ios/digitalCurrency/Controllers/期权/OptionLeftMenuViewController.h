//
//  OptionLeftMenuViewController.h
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "BaseViewController.h"

#define NSNotification_CONTRACTMENULIST   @"NSNotification_CONTRACTMENULIST"

NS_ASSUME_NONNULL_BEGIN
@class OptionCoinModel;

@interface OptionLeftMenuViewController : BaseViewController

@property (nonatomic, assign) BOOL isObserverNotificantion;

@property (nonatomic, copy) void (^selcetContractcoinSymbolModelBlock)(OptionCoinModel *model);

- (void)showLeftContractMenu;

@end

NS_ASSUME_NONNULL_END
