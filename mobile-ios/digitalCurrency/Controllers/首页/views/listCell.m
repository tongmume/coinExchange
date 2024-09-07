//
//  listCell.m
//  digitalCurrency
//
//  Created by sunliang on 2019/4/14.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "listCell.h"
#import "AppDelegate.h"
@implementation listCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.backgroundColor = mainColor;
}
-(void)configModel:(NSArray*)modelArray withIndex:(int)index{
    symbolModel*model = [modelArray objectAtIndex:index];

    NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
    NSDecimalNumber *baseUsdRate = [NSDecimalNumber decimalNumberWithDecimal:[model.baseUsdRate decimalValue]];

    self.titleIndex.text=[NSString stringWithFormat:@"%d",index+1];
    self.titleLabel.text=model.symbol;
    self.pricelabel.text = [NSString stringWithFormat:@"%@",[ToolUtil judgeStringForDecimalPlaces:[close stringValue]]];
    self.TradeNum.text=[NSString stringWithFormat:@"%@ %.2f",LocalizationKey(@"hourvol"),model.volume];


    if (((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate) {

        NSDecimalNumber *result = [[close decimalNumberByMultiplyingBy:baseUsdRate] decimalNumberByMultiplyingBy:((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate];
        NSString *resultStr = [result stringValue];
        if ([resultStr containsString:@"."]) {
            NSArray *arr = [resultStr componentsSeparatedByString:@"."];
            if (arr.count > 1 && [arr[1] length] > 2) {
                resultStr = [NSString stringWithFormat:@"%@.%@",arr[0],[arr[1] substringWithRange:NSMakeRange(0, 2)]];
            }
        }
        self.CNYlabel.text=[NSString stringWithFormat:@"¥ %@",resultStr];
    }else{
        self.CNYlabel.text = @"¥ 0.00";
    }
    if (model.change <0) {
        self.pricelabel.textColor=RedColor;
        self.rateLabel.backgroundColor=RedColor;
        self.rateLabel.text = [NSString stringWithFormat:@"%.2f%%", model.chg*100];
    }else{
        self.pricelabel.textColor=GreenColor;
        self.rateLabel.backgroundColor=GreenColor;
        self.rateLabel.text = [NSString stringWithFormat:@"+%.2f%%", model.chg*100];
    }
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
