//
//  Adversiting3TableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/24.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "Adversiting3TableViewCell.h"

@implementation Adversiting3TableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.leftLabel.font = self.centerLabel.font = self.rightLabel.font = [UIFont systemFontOfSize:16 * kWindowWHOne];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
