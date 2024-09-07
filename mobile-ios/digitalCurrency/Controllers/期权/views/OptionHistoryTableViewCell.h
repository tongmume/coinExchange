//
//  OptionHistoryTableViewCell.h
//  digitalCurrency
//
//  Created by chan on 2021/1/1.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
@class OptionOrderModel;
@class OptionCoinModel;

NS_ASSUME_NONNULL_BEGIN

@interface OptionHistoryTableViewCell : UITableViewCell

@property (nonatomic, strong) OptionOrderModel *model;
@property (nonatomic, assign) BOOL isCurrent;

@end

NS_ASSUME_NONNULL_END
