//
//  tradeCell.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "tradeCell.h"

@implementation tradeCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    if (kWindowW < 322) {
        self.amountLabel.font = [UIFont systemFontOfSize:9];
        self.priceLabel.font = [UIFont systemFontOfSize:9];
    }
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
