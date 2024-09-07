//
//  CustomSectionHeader.m
//  digitalCurrency
//
//  Created by sunliang on 2019/4/11.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "CustomSectionHeader.h"

@implementation CustomSectionHeader
+(CustomSectionHeader *)instancesectionHeaderViewWithFrame:(CGRect)Rect{
    NSArray* nibView =  [[NSBundle mainBundle] loadNibNamed:@"CustomSectionHeader" owner:nil options:nil];
    CustomSectionHeader*headerView=[nibView objectAtIndex:0];
    headerView.frame=Rect;
    return headerView;
}
- (IBAction)moreaction:(id)sender {
    self.moreBlock();
}

@end
