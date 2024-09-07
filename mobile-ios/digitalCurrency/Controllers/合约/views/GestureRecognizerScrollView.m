//
//  GestureRecognizerScrollView.m
//  digitalCurrency
//
//  Created by ios on 2020/9/23.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "GestureRecognizerScrollView.h"

@interface GestureRecognizerScrollView()<UIGestureRecognizerDelegate>



@end

@implementation GestureRecognizerScrollView

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
