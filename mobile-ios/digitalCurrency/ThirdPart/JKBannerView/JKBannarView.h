//
//  JKBannarView.h
//  SQBannerView
//
//  Created by 王冲 on 2019/9/8.
//  Copyright © 2019年 yangsq. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface JKBannarView : UIView

- (id)initWithFrame:(CGRect)frame viewSize:(CGSize)viewSize;

@property (strong, nonatomic) NSArray *items;


@property (copy, nonatomic) void(^imageViewClick)(JKBannarView *barnerview,NSInteger index);
//点击图片
- (void)imageViewClick:(void(^)(JKBannarView *barnerview,NSInteger index))block;


NS_ASSUME_NONNULL_END

@end
