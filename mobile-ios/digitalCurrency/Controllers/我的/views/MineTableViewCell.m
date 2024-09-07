//
//  MineTableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/29.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MineTableViewCell.h"

@implementation MineTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.rightLabel.clipsToBounds = YES;
    self.rightLabel.layer.cornerRadius = 10;
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
