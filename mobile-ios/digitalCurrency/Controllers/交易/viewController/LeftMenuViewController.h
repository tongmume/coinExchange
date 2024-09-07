//
//  LeftMenuViewController.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseViewController.h"

@interface LeftMenuViewController : BaseViewController
typedef enum : NSUInteger {
    ChildViewType_USDT=0,
    ChildViewType_BTC,
    ChildViewType_ETH,
    ChildViewType_Collection
} ChildViewType;
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property(assign,nonatomic)ChildViewType viewType;
- (void)showFromLeft;
@end
