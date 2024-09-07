//
//  LeftMenuViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "UIViewController+LeftSlide.h"
#import <objc/runtime.h>

@implementation UIViewController (LeftSlide)
static const void *kIsOpenKey = &kIsOpenKey;
static const void *kMaskViewKey = &kMaskViewKey;

#pragma mark - UIPanGestureRecognizer
-(void)initSlideFoundation{

    self.view.backgroundColor = [UIColor clearColor];
    self.maskView = [[UIView alloc] initWithFrame:[UIScreen mainScreen].bounds];
    self.maskView.backgroundColor = [UIColor colorWithRed:0.184 green:0.184 blue:0.216 alpha:0.50];
    self.maskView.alpha = 0;
    self.maskView.hidden = YES;
    [[UIApplication sharedApplication].keyWindow insertSubview:self.maskView aboveSubview:self.view];

    UIPanGestureRecognizer *pan=[[UIPanGestureRecognizer alloc]initWithTarget:self action:@selector(didPanEvent:)];
    [self.view addGestureRecognizer:pan];
}

#pragma mark -- set & get
-(BOOL)isOpen
{
    return  [objc_getAssociatedObject(self, kIsOpenKey) boolValue];
}

-(void)setIsOpen:(BOOL)isOpen
{
    objc_setAssociatedObject(self, kIsOpenKey, [NSNumber numberWithBool:isOpen], OBJC_ASSOCIATION_ASSIGN);
}

-(UIView *)maskView{
    return objc_getAssociatedObject(self, kMaskViewKey);
}

-(void)setMaskView:(UIView *)maskView
{
    objc_setAssociatedObject(self, kMaskViewKey, maskView, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

#pragma mark -- 拖动隐藏
- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer {

    if ([otherGestureRecognizer.view isKindOfClass:[UICollectionView class]]||[otherGestureRecognizer.view isKindOfClass:[UITableView class]])
    {
        return NO;
    }

    return YES;
}

-(void)didPanEvent:(UIPanGestureRecognizer *)recognizer{
    CGPoint translation = [recognizer translationInView:self.view];
    //NSLog(@"translation.x == %f", translation.x);
    [recognizer setTranslation:CGPointZero inView:self.view];

    if(UIGestureRecognizerStateBegan == recognizer.state ||
       UIGestureRecognizerStateChanged == recognizer.state){

        if (translation.x > 0 ) {//SwipRight
            return;

        }else
        {//SwipLeft

            CGFloat tempX = CGRectGetMinX(self.view.frame) + translation.x;
            self.view.frame = CGRectMake(tempX, 0, CGRectGetWidth(self.view.frame), CGRectGetHeight(self.view.frame));
        }

    }else
    {

        if (CGRectGetMinX(self.view.frame) >= - CGRectGetWidth(self.view.frame) * 0.3) {

            [self show];

        }else{

            [self hide];
        }
    }
}

/**关闭左视图 */
- (void)hide
{
    [UIView animateWithDuration:0.3 delay:0 options:UIViewAnimationOptionCurveEaseInOut  animations:^{
        self.view.frame = CGRectMake(- CGRectGetHeight(self.view.frame), 0, CGRectGetWidth(self.view.frame), CGRectGetHeight(self.view.frame));
        self.maskView.alpha = 0;
    }completion:^(BOOL finished) {
        self.isOpen = NO;
        self.maskView.hidden = YES;
    }];
}

/** 打开视图 */
- (void)show
{
    self.maskView.hidden = NO;
    [UIView animateWithDuration:0.3 delay:0 options:UIViewAnimationOptionCurveEaseInOut  animations:^{
        self.view.frame = CGRectMake(0, 0, CGRectGetWidth(self.view.frame), CGRectGetHeight(self.view.frame));
        self.maskView.alpha = 0.5;
    } completion:^(BOOL finished) {
        self.isOpen = YES;
    }];
}

@end
