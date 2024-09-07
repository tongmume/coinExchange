//
//  KlineHeaderCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/5/18.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "symbolModel.h"
@interface KlineHeaderCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *nowPrice;
@property (weak, nonatomic) IBOutlet UILabel *CNYLabel;
@property (weak, nonatomic) IBOutlet UILabel *changeLabel;
@property (weak, nonatomic) IBOutlet UILabel *hightPrice;
@property (weak, nonatomic) IBOutlet UILabel *LowPrice;
@property (weak, nonatomic) IBOutlet UILabel *numberLabel;
@property (weak, nonatomic) IBOutlet UILabel *Hlabel;
@property (weak, nonatomic) IBOutlet UILabel *Llabel;
@property (weak, nonatomic) IBOutlet UILabel *Alabel;

-(void)configModel:(symbolModel*)model baseCoinScale:(int)baseCoinScale CoinScale:(int)CoinScale;
@end
