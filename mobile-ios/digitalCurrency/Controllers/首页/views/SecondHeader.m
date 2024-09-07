//
//  SecondHeader.m
//  digitalCurrency
//
//  Created by sunliang on 2019/4/14.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SecondHeader.h"

@implementation SecondHeader
+(SecondHeader *)instancesectionHeaderViewWithFrame:(CGRect)Rect{
    NSArray* nibView =  [[NSBundle mainBundle] loadNibNamed:@"SecondHeader" owner:nil options:nil];
    SecondHeader*headerView=[nibView objectAtIndex:0];
    headerView.frame=Rect;
    [headerView.upbutton setTitle:LocalizationKey(@"Top") forState:UIControlStateNormal];


    return headerView;


}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
