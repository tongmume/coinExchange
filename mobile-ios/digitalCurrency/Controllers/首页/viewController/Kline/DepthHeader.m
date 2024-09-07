//
//  DepthHeader.m
//  digitalCurrency
//
//  Created by sunliang on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "DepthHeader.h"

@implementation DepthHeader
+(DepthHeader *)instancetableHeardViewWithFrame:(CGRect)Rect{

    NSArray* nibView =  [[NSBundle mainBundle] loadNibNamed:@"DepthHeader" owner:nil options:nil];
    DepthHeader*headerView=[nibView objectAtIndex:0];
    headerView.frame=Rect;

    return headerView;

}
//如果你要加点什么东西  就重载 initWithCoder
-(id)initWithCoder:(NSCoder *)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if(self)
    {
    }
    return self;
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
