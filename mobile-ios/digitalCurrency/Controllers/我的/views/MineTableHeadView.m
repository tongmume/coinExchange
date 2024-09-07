//
//  MineTableHeadView.m
//  ATC
//
//  Created by iDog on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MineTableHeadView.h"

@implementation MineTableHeadView

-(void)awakeFromNib{
    [super awakeFromNib];

}
-(MineTableHeadView *)instancetableHeardViewWithFrame:(CGRect)Rect
{
    NSArray* nibView =  [[NSBundle mainBundle] loadNibNamed:@"MineTableHeadView" owner:nil options:nil];
    MineTableHeadView *tableView=[nibView objectAtIndex:0];
    tableView.frame=Rect;
    tableView.imageheight.constant = 70 * kWindowWHOne;
    tableView.headImage.layer.cornerRadius = 35 * kWindowWHOne;
    tableView.safetop.constant = kWindowH == 812.0 ? 44 : 30;
    return tableView;
}


@end
