//
//  TransactionDetailsTableViewCell.h
//  digitalCurrency
//
//  Created by chu on 2019/7/4.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TradeModel.h"
@interface TransactionDetailsTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *tradeTimeLabel;
@property (weak, nonatomic) IBOutlet UILabel *tradepairLabel;
@property (weak, nonatomic) IBOutlet UILabel *directionLabel;
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;
@property (weak, nonatomic) IBOutlet UILabel *orderVolumeLabel;
@property (weak, nonatomic) IBOutlet UILabel *dealLabel;
@property (weak, nonatomic) IBOutlet UILabel *serviceChargeLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *tradeTimeWidthConstraint;
@property (weak, nonatomic) IBOutlet UIView *backView;

@property (nonatomic, strong) TradeModel *model;

@end
