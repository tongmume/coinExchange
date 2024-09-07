//
//  TouchBottomTableView.m
//  digitalCurrency
//
//  Created by ios on 2020/10/8.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "TouchBottomTableView.h"

@interface TouchBottomTableView()<UIGestureRecognizerDelegate>


@end

@implementation TouchBottomTableView

- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer{
    
    return YES;
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
