//
//  OptionTradeTableViewCell.h
//  digitalCurrency
//
//  Created by chan on 2020/12/31.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "OptionModel.h"
#import "OptionCoinModel.h"
#import "OptionScrollView.h"

NS_ASSUME_NONNULL_BEGIN

@interface OptionTradeTableViewCell : UITableViewCell

@property (nonatomic, copy  ) NSString *symbol;
@property (nonatomic, strong) OptionModel *model;
@property (nonatomic, strong) OptionScrollView *opScrollView;

@property (nonatomic, copy  ) void(^upBlock)(NSInteger);
- (void)setBeAmount:(NSString *)beAmount direction:(NSInteger)direction;
- (void)setAmount:(NSString *)amount;



@end

NS_ASSUME_NONNULL_END
