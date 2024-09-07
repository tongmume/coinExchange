//
//  MyEntrustDetail2TableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/23.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyEntrustDetail2TableViewCell.h"

@implementation MyEntrustDetail2TableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.leftLabelTitleWidth.constant = (kWindowW-20)/4;
    self.timeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"time" value:nil table:@"English"];
    self.dealPriceLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"dealPrice" value:nil table:@"English"];
    self.dealNum.text = [[ChangeLanguage bundle] localizedStringForKey:@"dealNum" value:nil table:@"English"];
    self.feeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"poundage" value:nil table:@"English"];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
