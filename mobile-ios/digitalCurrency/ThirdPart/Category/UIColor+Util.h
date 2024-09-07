//
//  UIColor+Util.h
//  自定义弹窗
//
//  Created by 蔡强 on 16/5/4.
//  Copyright © 2016年 蔡强. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIColor (Util)

/* 从十六进制字符串获取颜色 */
+ (UIColor *)colorWithHexString:(NSString *)color;

/* 从十六进制字符串获取颜色 */
+ (UIColor *)colorWithHexString:(NSString *)color alpha:(CGFloat)alpha;

@end
