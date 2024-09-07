//
//  CurrencyrecordTableViewCell.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "CurrencyrecordTableViewCell.h"

@implementation CurrencyrecordTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.Presenttime.text = LocalizationKey(@"Presenttime");
    self.Presentmoney.text = LocalizationKey(@"poundage");
    self.Presentaddress.text = LocalizationKey(@"PresentAdd");
    self.PresentNum.text = LocalizationKey(@"PresentNum");

}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
