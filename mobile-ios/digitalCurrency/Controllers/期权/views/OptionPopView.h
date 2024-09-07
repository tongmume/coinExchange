//
//  OptionPopView.h
//  digitalCurrency
//
//  Created by chan on 2021/1/5.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "OptionModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface OptionPopView : UIView

+ (void)showWithModel:(OptionModel *)model;

@end

NS_ASSUME_NONNULL_END
