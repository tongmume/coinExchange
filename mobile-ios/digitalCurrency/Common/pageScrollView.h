//
//  pageScrollView.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/11.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
typedef void(^clickLabelBlock)(NSInteger index,NSString *titleString);
@interface pageScrollView : UIView
/**
 *  文字数组
 */
@property (nonatomic,strong) NSArray *titleArray;
/**
 *  拼接后的文字数组
 */
@property (nonatomic,strong) NSMutableArray *titleNewArray;
/**
 *  是否可以拖拽
 */
@property (nonatomic,assign) BOOL isCanScroll;
/**
 *  block回调
 */
@property (nonatomic,copy)void(^clickLabelBlock)(NSInteger index,NSString *titleString);
/**
 *  字体颜色
 */
@property (nonatomic,strong) UIColor *titleColor;
/**
 *  背景颜色
 */
@property (nonatomic,strong) UIColor *BGColor;
/**
 *  字体大小
 */
@property (nonatomic,assign) CGFloat titleFont;

/**
 *  关闭定时器
 */
- (void)removeTimer;

/**
 *  添加定时器
 */
- (void)addTimer;

/**
 *  label的点击事件
 */

- (void) clickTitleLabel:(clickLabelBlock) clickLabelBlock;
@end
