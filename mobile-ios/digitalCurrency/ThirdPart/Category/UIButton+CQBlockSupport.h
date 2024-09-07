//
//  UIButton+CQBlockSupport.h
//  AlertToastHUD
//
//  Created by caiqiang on 2018/12/6.
//  Copyright © 2018年 Caiqiang. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIButton (CQBlockSupport)

- (void)cq_addAction:(void(^)(UIButton *button))action forControlEvents:(UIControlEvents)controlEvents;

@end
