//
//  OptionChartSegement.h
//  digitalCurrency
//
//  Created by chan on 2021/1/5.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface OptionChartSegement : UIView

- (instancetype)initWithFrame:(CGRect)frame array:(NSArray *)array;

- (void)relodData;

@property (nonatomic, copy) void(^didClick)(NSInteger);

@end

NS_ASSUME_NONNULL_END
