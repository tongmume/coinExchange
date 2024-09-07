//
//  MyEntrustDetail1TableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/23.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyEntrustDetail1TableViewCell.h"

@implementation MyEntrustDetail1TableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.leftLabelTitleWidth.constant = (kWindowW - 20)/3;
    self.dealDetailLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"dealDetail" value:nil table:@"English"];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
