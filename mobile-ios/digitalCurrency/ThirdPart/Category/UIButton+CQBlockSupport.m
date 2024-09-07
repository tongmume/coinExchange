//
//  UIButton+CQBlockSupport.m
//  AlertToastHUD
//
//  Created by caiqiang on 2018/12/6.
//  Copyright © 2018年 Caiqiang. All rights reserved.
//

#import "UIButton+CQBlockSupport.h"
#import <objc/runtime.h>

typedef void(^CQButtonEventBlock)(UIButton *button);

@interface UIButton ()

@property (nonatomic, copy) CQButtonEventBlock cq_buttonEventBlock;

@end

@implementation UIButton (CQBlockSupport)

static void *cq_buttonEventsBlockKey = &cq_buttonEventsBlockKey;

- (CQButtonEventBlock)cq_buttonEventBlock {
    return objc_getAssociatedObject(self, &cq_buttonEventsBlockKey);
}

- (void)setCq_buttonEventBlock:(CQButtonEventBlock)cq_buttonEventBlock {
    objc_setAssociatedObject(self, &cq_buttonEventsBlockKey, cq_buttonEventBlock, OBJC_ASSOCIATION_COPY);
}

- (void)cq_addAction:(void (^)(UIButton *))action forControlEvents:(UIControlEvents)controlEvents {
    self.cq_buttonEventBlock = action;
    [self addTarget:self action:@selector(cq_callButtonEventBlock) forControlEvents:controlEvents];
}

- (void)cq_callButtonEventBlock {
    !self.cq_buttonEventBlock ?: self.cq_buttonEventBlock(self);
}

@end
