//
//  CALayer+color.m
//  horizonLoan
//
//  Created by sunliang on 2019/9/27.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "CALayer+color.h"
#import <UIKit/UIKit.h>
@implementation CALayer (color)
- (void)setBorderColorWithUIColor:(UIColor*)color
{
    self.borderColor = color.CGColor;
}

@end
