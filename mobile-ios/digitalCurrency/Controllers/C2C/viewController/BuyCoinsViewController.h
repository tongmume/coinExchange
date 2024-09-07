//
//  BuyCoinsViewController.h
//  digitalCurrency
//
//  Created by iDog on 2019/1/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SelectCoinTypeModel.h"

@interface BuyCoinsViewController : UIViewController
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property(nonatomic,strong)SelectCoinTypeModel *model;

@property (nonatomic, assign) BOOL vcCanScroll;

@end
