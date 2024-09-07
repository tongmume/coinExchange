//
//  Advertising1TableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "Advertising1TableViewCell.h"

@implementation Advertising1TableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    [self.centerTextFileld addTarget:self action:@selector(textfieldAction:) forControlEvents:UIControlEventEditingDidEnd];
    [self.centerTextFileld addTarget:self action:@selector(textfieldValueChange:) forControlEvents:UIControlEventEditingChanged];
    self.leftLabel.font = self.centerTextFileld.font = self.rightLabel.font = [UIFont systemFontOfSize:16 * kWindowWHOne];
//    [_centerTextFileld setValue:RGBOF(0x444444) forKeyPath:@"_placeholderLabel.textColor"];
}
- (void)textfieldValueChange:(UITextField *)textField
{
    if (self.index.row == 12) {
        //资金密码无需判断
    }else{
        if ([textField.text isEqualToString:@""]) {
            //为空不处理
        }else{
            NSString *firstStr = [textField.text substringToIndex:1];
            if ([firstStr isEqualToString:@"."]) {
                textField.text = @"0.";
                return ;
            }else{
                if (textField.text.length == 2) {
                    NSString *firstStr = [textField.text substringToIndex:1];
                    if ([firstStr isEqualToString:@"0"]) {
                        if ([textField.text isEqualToString:@"0."]) {
                            //不处理
                        }else{
                            textField.text = @"";
                            return ;
                        }
                    }
                }else{
                    NSArray *array = [textField.text componentsSeparatedByString:@"."];
                    if (array.count >= 3) {
                        [self makeToast:LocalizationKey(@"advertisingCell1Tip1")  duration:1.5 position:CSToastPositionCenter];
                        textField.text = [NSString stringWithFormat:@"%@.%@",array[0],array[1]];
                        return ;
                    }else{
                        if (array.count == 2) {
                            NSString *titleStr = array[1];
                            if (_index.row == 5 || _index.row == 9) {
                                //溢价和买入数量
                                if (titleStr.length > 8) {
                                    [self makeToast:LocalizationKey(@"advertisingCell1Tip2")  duration:1.5 position:CSToastPositionCenter];
                                    titleStr = [titleStr substringToIndex:8];
                                    textField.text = [NSString stringWithFormat:@"%@.%@",array[0],titleStr];
                                    return ;
                                }
                            }else{
                                if (titleStr.length > 2) {
                                    [self makeToast:LocalizationKey(@"advertisingCell1Tip3") duration:1.5 position:CSToastPositionCenter];
                                    titleStr = [titleStr substringToIndex:2];
                                    textField.text = [NSString stringWithFormat:@"%@.%@",array[0],titleStr];
                                    return ;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
- (void)textfieldAction:(UITextField *)textField
{
    if (_index.row == 9) {
        //买入数量
        if ([textField.text floatValue] <= 0 ) {
            textField.text = @"";
        }
    }else if(_index.row == 6 ||_index.row == 7 ||_index.row == 8 ){
        //交易价格 最大量 最小量
        if ([textField.text floatValue] <= 0) {
            textField.text = @"";
            if (_index.row == 6) {
                [self makeToast:LocalizationKey(@"livePriceTip") duration:1.5 position:CSToastPositionCenter];
            }
        }
    }
    if (self.delegate && [self.delegate respondsToSelector:@selector(textFieldIndex:TextFieldString:)]) {
        [self.delegate textFieldIndex:self.index TextFieldString:textField.text];
    }
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
