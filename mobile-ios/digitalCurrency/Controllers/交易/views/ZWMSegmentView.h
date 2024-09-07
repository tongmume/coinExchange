//
//  ZWMSegmentView.h
//  ZWMSegmentController
//
//  Created by 伟明 on 2019/11/23.
//  Copyright © 2019年 伟明. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef NS_ENUM(NSUInteger, ZWMSegmentStyle) {
    ZWMSegmentStyleDefault,    /**< 指示杆和按钮的标题齐平*/
    ZWMSegmentStyleFlush,      /**< 指示杆和按钮宽度齐平*/
};
NS_ASSUME_NONNULL_BEGIN
FOUNDATION_EXPORT int const ZWMSegmentHeight;

@interface ZWMSegmentView : UIView
+ (__nullable instancetype)segmentViewWithFrame:(CGRect)frame titles:(NSArray <NSString *>*)titles;
- (__nullable instancetype)initWithFrame:(CGRect)frame titles:(NSArray <NSString *>*)titles;

@property (nonatomic) ZWMSegmentStyle style;
@property (nonatomic, strong, nullable) UIImage *backgroundImage;
@property (nonatomic, strong, nullable) UIColor *segmentTintColor;   /**< 选中时的字体颜色，默认黑色*/
@property (nonatomic, strong, nullable) UIColor *segmentNormalColor;
@property (nonatomic, strong, nullable) UIColor *separateColor;   /**< 设置分割线的颜色，默认和segementView的颜色一致*/
@property (nonatomic, strong, readonly) NSArray <UIButton *>*buttons;
@property (nonatomic, strong, readonly) UIScrollView *contentView;
@property (nonatomic, strong, readonly) UIButton *selectedButton;
@property (nonatomic, getter=isScrollEnabled) BOOL scrollEnabled; /**< 默认YES*/
@property (nonatomic, getter=isShowSeparateLine) BOOL showSeparateLine; /**< 默认NO*/
@property (nonatomic, readonly) CGFloat buttonSpace;
@property (nonatomic, readonly) NSUInteger index;
@property (nonatomic, readonly) NSTimeInterval duration;  /**< 滑动时间*/

- (void)setSelectedAtIndex:(NSUInteger)index;
- (void)selectedAtIndex:(void(^)(NSUInteger index, UIButton *button))indexBlock;

/// offset
- (void)adjustOffsetXToFixIndicatePosition:(CGFloat)offsetX;

@end
NS_ASSUME_NONNULL_END
