//
//  TradeNumCell.m
//  digitalCurrency
//
//  Created by sunliang on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "TradeNumCell.h"

@implementation TradeNumCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.contentView.backgroundColor=[UIColor backgroundColor];
    // Initialization code
}
-(void)configmodel:(TradeNumModel*)model widthcoinScale:(int)coinScale baseCoinScale:(int)baseCoinScale{

    if ([model.price intValue]<0) {
         self.buyType.text=@"--";
         self.priceLabel.text=@"--";
         self.amountLabel.text=@"--";
         self.timeLabel.text=@"--";
    }else{
        if ([model.direction isEqualToString:@"BUY"]) {
            self.buyType.text=LocalizationKey(@"Buy");
            self.buyType.textColor=GreenColor;
        }else{
            self.buyType.text=LocalizationKey(@"Sell");
            self.buyType.textColor=RedColor;
        }
        self.timeLabel.text=[self convertStrToTime:model.time];
        self.priceLabel.text=[ToolUtil stringFromNumber:[model.price doubleValue] withlimit:baseCoinScale];
        self.amountLabel.text=[ToolUtil stringFromNumber:[model.amount doubleValue] withlimit:coinScale];
    }

}

//MARK:--毫秒时间戳转 HH:mm MM/dd格式
- (NSString *)convertStrToTime:(NSString *)timeStr
{
    long long time=[timeStr longLongValue];
    NSDate *d = [[NSDate alloc]initWithTimeIntervalSince1970:time/1000.0];
    NSDateFormatter *formatter = [[NSDateFormatter alloc]init];
    [formatter setDateFormat:@"HH:mm:ss"];
    NSString*timeString=[formatter stringFromDate:d];
    return timeString;

}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
