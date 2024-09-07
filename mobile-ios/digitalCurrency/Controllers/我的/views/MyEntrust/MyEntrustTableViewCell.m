//
//  MyEntrustTableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyEntrustTableViewCell.h"

@implementation MyEntrustTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}
- (IBAction)completeBtnClick:(id)sender {
    if (self.entrustBlock) {
        self.entrustBlock();
    }
}

- (void)setInfomodel:(MyEntrustInfoModel *)infomodel{
    _infomodel = infomodel;
    self.timeTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"time" value:nil table:@"English"];
    self.entrustPriceTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"type" value:nil table:@"English"];
    self.entrustNumTitle.text = [NSString stringWithFormat:@"%@(%@)",[[ChangeLanguage bundle] localizedStringForKey:@"price" value:nil table:@"English"],_infomodel.baseSymbol];
    self.dealTitle.text = [NSString stringWithFormat:@"%@(%@)",[[ChangeLanguage bundle] localizedStringForKey:@"amonut" value:nil table:@"English"],_infomodel.coinSymbol];
    self.dealPerPriceTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"tradeDeal" value:nil table:@"English"];
    self.dealNumTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"Entrustmentamount" value:nil table:@"English"];

    self.symbolLabel.text = _infomodel.symbol;
    //时间
    NSString *time ;
    if (_infomodel.time.length > 9) {
        time = [ToolUtil timeIntervalToTimeString:_infomodel.time  WithDateFormat:@"yyyy-MM-dd-HH-mm"];

    }else{
        time = @"0000-00-00-00-00";
    }
    NSArray *times = [time componentsSeparatedByString:@"-"];
    self.timeData.text = [NSString stringWithFormat:@"%@:%@ %@/%@", times[3], times[4], times[1], times[2]];

    if ([_infomodel.type isEqualToString:@"LIMIT_PRICE"]) {
        self.ntrustPriceData.text = [[ChangeLanguage bundle] localizedStringForKey:@"limitPrice" value:nil table:@"English"];
    }else{
        self.ntrustPriceData.text = [[ChangeLanguage bundle] localizedStringForKey:@"marketPrice" value:nil table:@"English"];
    }

    //价格
    if ([_infomodel.tradedAmount floatValue] <= 0) {
        self.entrustNumData.text = [NSString stringWithFormat:@"0.00"];
    }else{
        self.entrustNumData.text = _infomodel.price;
    }
    //数量
    self.dealData.text =  _infomodel.amount;
    //已成交
    self.dealPerPriceData.text = _infomodel.tradedAmount;
    //委托价格
    self.dealNumData.text = _infomodel.turnover;

    self.payStatus.font = [UIFont systemFontOfSize:14];
    if ([_infomodel.direction isEqualToString:@"BUY"]) {
        self.payStatus.text = LocalizationKey(@"buyDirection");
        self.payStatus.textColor = RGBOF(0x00B274);
    }else{
        self.payStatus.text = LocalizationKey(@"sellDirection");
        self.payStatus.textColor = RGBOF(0xF15057);
    }

    [self.statusButton setTitleColor:RGBOF(0x666666) forState:UIControlStateNormal];
    if ([_infomodel.status isEqualToString:@"COMPLETED"]) {
        [self.statusButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"completed" value:nil table:@"English"] forState:UIControlStateNormal];
    }else{
        [self.statusButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"rescinded" value:nil table:@"English"] forState:UIControlStateNormal];
    }

}

- (void)setModel:(commissionModel *)model{
    _model = model;
    self.timeTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"time" value:nil table:@"English"];
    self.entrustPriceTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"type" value:nil table:@"English"];
    self.entrustNumTitle.text = [NSString stringWithFormat:@"%@(%@)",[[ChangeLanguage bundle] localizedStringForKey:@"price" value:nil table:@"English"],model.baseSymbol];
    self.dealTitle.text = [NSString stringWithFormat:@"%@(%@)",[[ChangeLanguage bundle] localizedStringForKey:@"amonut" value:nil table:@"English"],model.coinSymbol];
    self.dealPerPriceTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"tradeDeal" value:nil table:@"English"];
    self.dealNumTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"Entrustmentamount" value:nil table:@"English"];

    self.symbolLabel.text = model.symbol;
    //时间
    NSString *time ;
    if (model.time.length > 9) {
        time = [ToolUtil timeIntervalToTimeString:model.time WithDateFormat:@"yyyy-MM-dd-HH-mm"];

    }else{
        time = @"0000-00-00-00-00";
    }
    NSArray *times = [time componentsSeparatedByString:@"-"];
    self.timeData.text = [NSString stringWithFormat:@"%@:%@ %@/%@", times[3], times[4], times[1], times[2]];

    if ([model.type isEqualToString:@"LIMIT_PRICE"]) {
        self.ntrustPriceData.text = [[ChangeLanguage bundle] localizedStringForKey:@"limitPrice" value:nil table:@"English"];
    }else{
        self.ntrustPriceData.text = [[ChangeLanguage bundle] localizedStringForKey:@"marketPrice" value:nil table:@"English"];
    }

    //价格
    self.entrustNumData.text = model.price;

    //数量
    self.dealData.text = model.amount;
    //已成交
    self.dealPerPriceData.text = model.tradedAmount;
    //委托价格
    NSDecimalNumber *price = [[NSDecimalNumber alloc] initWithString:model.price];
    NSDecimalNumber *tradedAmount = [[NSDecimalNumber alloc] initWithString:model.tradedAmount];
    self.dealNumData.text = [[price decimalNumberByMultiplyingBy:tradedAmount] stringValue];

    self.payStatus.font = [UIFont systemFontOfSize:14];
    if ([model.direction isEqualToString:@"BUY"]) {
        self.payStatus.text = LocalizationKey(@"buyDirection");
        self.payStatus.textColor = RGBOF(0x00B274);
    }else{
        self.payStatus.text = LocalizationKey(@"sellDirection");
        self.payStatus.textColor = RGBOF(0xF15057);
    }

    [self.statusButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"Revoke" value:nil table:@"English"] forState:UIControlStateNormal];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
