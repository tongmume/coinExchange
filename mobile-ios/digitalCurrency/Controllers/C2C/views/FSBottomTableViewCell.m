//
//  FSBottomTableViewCell.m
//  FSScrollViewNestTableViewDemo
//
//  Created by huim on 2019/5/23.
//  Copyright © 2019年 fengshun. All rights reserved.
//

#import "FSBottomTableViewCell.h"
#import "BuyCoinsChildViewController.h"
#import "SellCoinsChildViewController.h"

@implementation FSBottomTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.backgroundColor = mainColor;
    }
    return self;
}

#pragma mark Setter
- (void)setViewControllers:(NSMutableArray *)viewControllers
{
    _viewControllers = viewControllers;
}

- (void)setCellCanScroll:(BOOL)cellCanScroll
{
    _cellCanScroll = cellCanScroll;

//    for (FSScrollContentViewController *VC in _viewControllers) {
//        VC.vcCanScroll = cellCanScroll;
//        if (!cellCanScroll) {//如果cell不能滑动，代表到了顶部，修改所有子vc的状态回到顶部
//            VC.tableView.contentOffset = CGPointZero;
//        }
//    }

    for (UIViewController *VC in _viewControllers) {
        if ([VC isKindOfClass:[BuyCoinsChildViewController class]]) {
            BuyCoinsChildViewController *buy = (BuyCoinsChildViewController *)VC;
            buy.vcCanScroll = cellCanScroll;

        }
        if ([VC isKindOfClass:[SellCoinsChildViewController class]]) {
            SellCoinsChildViewController *buy = (SellCoinsChildViewController *)VC;
            buy.vcCanScroll = cellCanScroll;

        }
    }
}

- (void)setIsRefresh:(BOOL)isRefresh
{
    _isRefresh = isRefresh;

}
@end
