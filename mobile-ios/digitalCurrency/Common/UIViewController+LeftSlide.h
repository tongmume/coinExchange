//
//  LeftMenuViewController.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//
#import <UIKit/UIKit.h>

@interface UIViewController (LeftSlide)<UIGestureRecognizerDelegate>
@property (nonatomic,strong) UIView *maskView;
@property (nonatomic,assign) BOOL isOpen;

-(void)show;
-(void)hide;
-(void)initSlideFoundation;
@end
