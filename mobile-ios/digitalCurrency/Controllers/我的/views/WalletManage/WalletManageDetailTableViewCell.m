//
//  WalletManageDetailTableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "WalletManageDetailTableViewCell.h"

@implementation WalletManageDetailTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];

    self.tradTimeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"tradTime" value:nil table:@"English"];
    self.typeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"type" value:nil table:@"English"];
    self.amountLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"amount" value:nil table:@"English"];
    self.poundage.text = [[ChangeLanguage bundle] localizedStringForKey:@"Feepayable" value:nil table:@"English"];
    self.Deductible.text = [[ChangeLanguage bundle] localizedStringForKey:@"Deductiblefee" value:nil table:@"English"];
    self.Actual.text = [[ChangeLanguage bundle] localizedStringForKey:@"Actualhandlingfee" value:nil table:@"English"];


}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
