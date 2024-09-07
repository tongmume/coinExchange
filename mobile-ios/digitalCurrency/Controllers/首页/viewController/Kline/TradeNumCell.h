//
//  TradeNumCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UIColor+Y_StockChart.h"
#import "TradeNumModel.h"
@interface TradeNumCell : UITableViewCell
-(void)configmodel:(TradeNumModel*)model widthcoinScale:(int)coinScale baseCoinScale:(int)baseCoinScale;
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;
@property (weak, nonatomic) IBOutlet UILabel *buyType;
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;
@property (weak, nonatomic) IBOutlet UILabel *amountLabel;


@end
