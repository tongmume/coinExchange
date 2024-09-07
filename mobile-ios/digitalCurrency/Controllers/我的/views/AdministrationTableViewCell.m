//
//  AdministrationTableViewCell.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "AdministrationTableViewCell.h"

@implementation AdministrationTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    [self.Advertisementbut setTitle:LocalizationKey(@"myAdvertising") forState:UIControlStateNormal];
    [self.orderbut setTitle:LocalizationKey(@"myBill") forState:UIControlStateNormal];
    self.butview1.layer.masksToBounds = YES;
    self.butview1.layer.cornerRadius = self.butview1.height / 2;

    self.butview2.layer.masksToBounds = YES;
    self.butview2.layer.cornerRadius = self.butview2.height / 2;

    self.backview.layer.masksToBounds = YES;
    self.backview.layer.cornerRadius = 3;

    if (kWindowW == 320) {
        self.bottomspec.constant = 10;

    }
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

- (IBAction)Advertisementaction:(id)sender {

    self.butBlock(0);
}
- (IBAction)orderaction:(id)sender {

    self.butBlock(1);
}
@end
