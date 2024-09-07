//
//  MyEntrustTableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "commissionModel.h"
#import "MyEntrustInfoModel.h"

typedef void(^EntrustStateBlock)(void);

@interface MyEntrustTableViewCell : UITableViewCell
@property (nonatomic, strong) commissionModel *model;
@property (nonatomic, strong) MyEntrustInfoModel *infomodel;
@property (nonatomic, copy) EntrustStateBlock entrustBlock;

@property (weak, nonatomic) IBOutlet UILabel *symbolLabel;
@property (weak, nonatomic) IBOutlet UILabel *payStatus;//买入卖出状态
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *timeTitleWidth;
@property (weak, nonatomic) IBOutlet UILabel *timeTitle;//时间标题
@property (weak, nonatomic) IBOutlet UILabel *timeData;//时间数据
@property (weak, nonatomic) IBOutlet UILabel *dealTitle;//成交总额标题
@property (weak, nonatomic) IBOutlet UILabel *dealData;//成交总额数据
@property (weak, nonatomic) IBOutlet UILabel *entrustPriceTitle;//委托价标题
@property (weak, nonatomic) IBOutlet UILabel *dealPerPriceTitle;//成交均价标题
@property (weak, nonatomic) IBOutlet UILabel *dealPerPriceData;//成交均价数据
@property (weak, nonatomic) IBOutlet UILabel *ntrustPriceData;//委托价数据
@property (weak, nonatomic) IBOutlet UILabel *entrustNumTitle;//委托量标题
@property (weak, nonatomic) IBOutlet UILabel *entrustNumData;//委托量数据
@property (weak, nonatomic) IBOutlet UILabel *dealNumTitle;//成交量标题
@property (weak, nonatomic) IBOutlet UILabel *dealNumData;//成交量数据
@property (weak, nonatomic) IBOutlet UIButton *statusButton;//状态按钮

@end
