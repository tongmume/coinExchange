//
//  listCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/14.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "symbolModel.h"
@interface listCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *titleIndex;
-(void)configModel:(NSArray*)modelArray withIndex:(int)index;

@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UILabel *pricelabel;
@property (weak, nonatomic) IBOutlet UILabel *rateLabel;
@property (weak, nonatomic) IBOutlet UILabel *TradeNum;
@property (weak, nonatomic) IBOutlet UILabel *CNYlabel;

@end
