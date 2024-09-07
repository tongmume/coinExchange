//
//  UIView+ZWMBadge.h
//  ZWMSegmentController
//
//  Created by 伟明 on 2019/11/23.
//  Copyright © 2019年 伟明. All rights reserved.
//
/*
 *专门是给ZWMSegmentController使用的计数类
 *并不适用于其他场景
 */

#import <UIKit/UIKit.h>

@interface UIView (ZWMBadge)

- (void)addNormalBadgeWithBadgeOffsetSize:(CGSize)size;
- (void)addNormalBadgeWithColor:(UIColor *)color borderColor:(UIColor *)bColor badgeOffsetSize:(CGSize)size;

- (void)addNumberBadge:(NSInteger)number badgeOffsetSize:(CGSize)size;
- (void)addNumberBadge:(NSInteger)number badgeOffsetSize:(CGSize)size color:(UIColor *)color borderColor:(UIColor *)bColor;
- (void)addNumber_1;
- (void)reduceNumber_1;

- (void)clearNumberBadge;

@end
