//
//  CustomSegmentView.h
//  digitalCurrency
//
//  Created by ios on 2020/9/16.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface CustomSegmentView : UIControl

- (instancetype)initWithFrame:(CGRect)frame   Items:(NSArray *)array;

@property (nonatomic, assign) NSInteger selectIndex;

@property (nonatomic, strong) UIColor *selectColor;

@property (nonatomic, strong) UIColor *unselectColor;

@property (nonatomic, assign,readonly) NSInteger unselectIndex;


@property (nonatomic, copy) void(^selectitemblock)(NSInteger selectIndex);


@end

NS_ASSUME_NONNULL_END
