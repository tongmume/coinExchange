//
//  menuCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "symbolModel.h"
@interface menuCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIButton *collectBtn;
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@property (weak, nonatomic) IBOutlet UILabel *moneyLabel;
@property (weak, nonatomic) IBOutlet UILabel *changeLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *widthconstant;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *rateWidth;

-(void)configDataWithModel:(symbolModel*)model withtype:(int)type withIndex:(int)index;
@end
