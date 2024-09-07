//
//  DepthmapCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DepthmapCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIView *depthView;
@property (weak, nonatomic) IBOutlet UIView *TradeView;
@property (weak, nonatomic) IBOutlet UILabel *tradePrice;
@property (weak, nonatomic) IBOutlet UILabel *tradeNum;
@property (weak, nonatomic) IBOutlet UILabel *DepthNum;
@property (weak, nonatomic) IBOutlet UILabel *DepthPrice;
@property (weak, nonatomic) IBOutlet UILabel *DepthSellNum;
@property (weak, nonatomic) IBOutlet UILabel *timeLbel;
@property (weak, nonatomic) IBOutlet UILabel *directionLabel;

@end
