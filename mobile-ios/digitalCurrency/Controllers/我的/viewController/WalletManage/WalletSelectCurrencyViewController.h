//
//  WalletSelectCurrencyViewController.h
//  digitalCurrency
//
//  Created by ios on 2020/10/9.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "BaseViewController.h"

NS_ASSUME_NONNULL_BEGIN

@interface WalletSelectCurrencyViewController : BaseViewController
@property (nonatomic, strong) NSMutableArray *dataArray;

@property (nonatomic, assign) NSInteger type;  // 0 充币。1 提币  2 划转

@property (nonatomic, copy) void (^selectCellItemBlock)(NSInteger type,id data);

@end

NS_ASSUME_NONNULL_END
