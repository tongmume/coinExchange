//
//  menuCell.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "menuCell.h"

@implementation menuCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}
-(void)configDataWithModel:(symbolModel*)model withtype:(int)type withIndex:(int)index{
    if (type==0) {
        NSArray *array = [model.symbol componentsSeparatedByString:@"/"];
        NSString*baseSymbol=[array firstObject];
        self.nameLabel.text=baseSymbol;
        self.widthconstant.constant=50;
        self.rateWidth.constant=80;
    }else{
        self.nameLabel.text=model.symbol;
        self.widthconstant.constant=90;
        self.rateWidth.constant=60;
    }
    NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
    self.moneyLabel.text=[NSString stringWithFormat:@"%@",close];
    self.changeLabel.text = [NSString stringWithFormat:@"%.2f%%", model.chg*100];

    if (model.change <0) {
        self.moneyLabel.textColor=RedColor;
        self.changeLabel.backgroundColor=RedColor;
    }else{
        self.moneyLabel.textColor=GreenColor;
        self.changeLabel.backgroundColor=GreenColor;
    }
    if (model.isCollect) {
        [self.collectBtn setBackgroundImage:UIIMAGE(@"collect") forState:UIControlStateNormal];
    }else{
        [self.collectBtn setBackgroundImage:UIIMAGE(@"uncollect") forState:UIControlStateNormal];
    }
    self.collectBtn.tag=index;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
