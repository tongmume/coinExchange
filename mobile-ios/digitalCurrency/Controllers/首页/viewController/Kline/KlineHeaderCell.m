//
//  KlineHeaderCell.m
//  digitalCurrency
//
//  Created by sunliang on 2019/5/18.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "KlineHeaderCell.h"
#import "AppDelegate.h"
@implementation KlineHeaderCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.Hlabel.text=LocalizationKey(@"highest");
    self.Llabel.text=LocalizationKey(@"minimumest");
    self.Alabel.text=LocalizationKey(@"24H");
    self.CNYLabel.font = self.changeLabel.font = [UIFont fontWithName:@"PingFang-SC-Medium" size:13 * kWindowWHOne];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
-(void)configModel:(symbolModel*)model baseCoinScale:(int)baseCoinScale CoinScale:(int)CoinScale{
    self.nowPrice.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:[model.close doubleValue] withlimit:baseCoinScale]];

    NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
    NSDecimalNumber *baseUsdRate = [NSDecimalNumber decimalNumberWithDecimal:[model.baseUsdRate decimalValue]];
    self.CNYLabel.text=[NSString stringWithFormat:@"≈%.2f CNY",[[close decimalNumberByMultiplyingBy:((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate] doubleValue]];
    self.hightPrice.text= [ToolUtil stringFromNumber:model.high withlimit:baseCoinScale];
    self.LowPrice.text= [ToolUtil stringFromNumber:model.low withlimit:baseCoinScale];
    self.numberLabel.text= [ToolUtil stringFromNumber:model.volume withlimit:CoinScale];
    if (model.change <0) {
        self.changeLabel.textColor=RedColor;
        self.nowPrice.textColor=RedColor;
        self.changeLabel.text= [NSString stringWithFormat:@"%.2f%%", model.chg*100];
    }else{
        self.changeLabel.textColor=GreenColor;
        self.nowPrice.textColor=GreenColor;
        self.changeLabel.text= [NSString stringWithFormat:@"+%.2f%%",model.chg*100];
    }

}
@end
