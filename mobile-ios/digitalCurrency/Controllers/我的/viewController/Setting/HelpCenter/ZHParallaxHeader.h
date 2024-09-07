//
//  ZHParallaxHeader.h
//  ParallaxTableView
//
//  Created by aleck on 2019/7/28.
//  Copyright © 2019年 Xmly. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@protocol ZHParallaxHeaderDelegate;

@interface ZHParallaxHeader : NSObject

@property (nonatomic, strong) UIView *headerView;
@property (nonatomic, strong) UIView *contentView;
@property (nonatomic, assign) CGFloat ratio;
@property (nonatomic, readonly) CGFloat progress;
@property (nonatomic) CGFloat height;
@property (nonatomic) CGFloat minimumHeight; // 待实现

@property (nonatomic,weak,nullable) id<ZHParallaxHeaderDelegate> delegate;

@end

@protocol ZHParallaxHeaderDelegate <NSObject>

@optional

- (void)parallaxHeaderDidScroll:(ZHParallaxHeader *)parallaxHeader;

@end

/**
 A UIScrollView category with a ZHParallaxHeader.
 */
@interface UIScrollView (ZHParallaxHeader)

/**
 The parallax header.
 */
@property (nonatomic, strong) ZHParallaxHeader *parallaxHeader;

@end

NS_ASSUME_NONNULL_END
