//
//  marketCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/29.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "symbolModel.h"
@interface marketCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@property (weak, nonatomic) IBOutlet UILabel *moneyLabel;
@property (weak, nonatomic) IBOutlet UILabel *changeLabel;
@property (weak, nonatomic) IBOutlet UILabel *baseLabel;
@property (weak, nonatomic) IBOutlet UILabel *tradeNumbel;
@property (weak, nonatomic) IBOutlet UILabel *cnyLabel;

-(void)configDataWithModel:(symbolModel*)model withtype:(int)type withIndex:(int)index;
@end
