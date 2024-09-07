//
//  EntrustCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/14.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "commissionModel.h"
@interface EntrustCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *tradeType;
@property (weak, nonatomic) IBOutlet UIButton *withDraw;
@property (weak, nonatomic) IBOutlet UILabel *titlePrice;
@property (weak, nonatomic) IBOutlet UILabel *titleAmount;
@property (weak, nonatomic) IBOutlet UILabel *titleDeal;
@property (weak, nonatomic) IBOutlet UILabel *price;
@property (weak, nonatomic) IBOutlet UILabel *amount;
@property (weak, nonatomic) IBOutlet UILabel *deal;
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;

-(void)configModel:(commissionModel*)model;
@end
