//
//  TransactionDetailsTableViewCell.m
//  digitalCurrency
//
//  Created by chu on 2019/7/4.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "TransactionDetailsTableViewCell.h"

@implementation TransactionDetailsTableViewCell

- (void)setModel:(TradeModel *)model{
    _model = model;
    self.tradeTimeLabel.text = model.tradeTime;
    self.tradepairLabel.text = model.tradePair;
    self.directionLabel.text = model.tradeDirection;
    self.priceLabel.text = model.tradePrice;
    self.orderVolumeLabel.text = model.tradeOrderVolume;
    self.dealLabel.text = model.tradeDeal;
    self.serviceChargeLabel.text = model.tradeServiceCharge;
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.tradeTimeWidthConstraint.constant = 150;
}

- (void)setFrame:(CGRect)frame{
    [super setFrame:frame];
}

@end
