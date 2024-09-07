//
//  OptionCurrentTableViewCell.h
//  digitalCurrency
//
//  Created by chan on 2020/12/31.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "OptionModel.h"
#import "OptionCoinModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface OptionCurrentTableViewCell : UITableViewCell

@property (nonatomic, assign)  CGFloat progress;

@property (nonatomic, copy  )  NSString* symbol;

@property (nonatomic, copy  )  NSString* currentPrice;

@property (nonatomic, strong) OptionModel *model;

- (void)setBeAmount:(NSString *)beAmount direction:(NSInteger)direction;


@end

NS_ASSUME_NONNULL_END
