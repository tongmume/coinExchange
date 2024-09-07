//
//  CurrencyexchangeTableViewCell.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "CurrencyexchangeTableViewCell.h"

@implementation CurrencyexchangeTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code

    self.title1.adjustsFontSizeToFitWidth = YES;
    self.title.adjustsFontSizeToFitWidth = YES;
    UIView *bg = [[UIView alloc] init];
    bg.backgroundColor = RGBOF(0xF0A70A);
    [self.contentView addSubview:bg];
    [bg mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.title1).offset(-10);
        make.right.equalTo(self.title1).offset(10);
        make.top.bottom.equalTo(self.title1);
    }];
    bg.layer.cornerRadius = 13.5;
    bg.layer.masksToBounds = true;
    [self.contentView bringSubviewToFront:self.title1];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
