//
//  ChargerecordTableViewCell.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChargerecordTableViewCell.h"

@implementation ChargerecordTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.Paymentdate.text = LocalizationKey(@"Paymenttime");
    self.Chargeaddress.text = LocalizationKey(@"Address");
    self.Amountrecharge.text = LocalizationKey(@"Amountrecharge");

}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
