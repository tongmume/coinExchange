//
//  AccountImageTableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/29.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "AccountImageTableViewCell.h"

@implementation AccountImageTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.headTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"HeadPortrait" value:nil table:@"English"];
    self.headTitle.font = [UIFont systemFontOfSize:15 * kWindowWHOne];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
