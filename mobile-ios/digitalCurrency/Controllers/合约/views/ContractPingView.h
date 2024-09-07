//
//  ContractPingView.h
//  digitalCurrency
//
//  Created by chan on 2021/1/6.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ContractRefreshModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface ContractPingView : UIView

+ (ContractPingView *)showWithModel:(ContractRefreshModel *)model;

@property (nonatomic, copy) void(^doneBlock)(NSMutableDictionary *);


@end

NS_ASSUME_NONNULL_END
