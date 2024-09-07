//
//  DepthCell.m
//  digitalCurrency
//
//  Created by sunliang on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "DepthCell.h"
#import "UIColor+Y_StockChart.h"

@implementation DepthCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.contentView.backgroundColor=[UIColor backgroundColor];
    self.buyView.backgroundColor=[ToolUtil colorWithHexString:@"00B274"];
    self.buyView.alpha = 0.1;
    self.SellView.backgroundColor= [ToolUtil colorWithHexString:@"F15057"];
    self.SellView.alpha = 0.1;
    // Initialization code
}
-(void)config:(plateModel*)buymodel withmodel:(plateModel*)Sellmodel widthcoinScale:(int)coinScale baseCoinScale:(int)baseCoinScale{
    if (buymodel.amount<0) {
        self.buyNum.text=@"--";
        self.BuyPrice.text=@"--";
    }else{
        self.buyNum.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:buymodel.amount withlimit:coinScale]];
        self.BuyPrice.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:buymodel.price withlimit:baseCoinScale]];

    }
    if (Sellmodel.amount<0) {
        self.SellPrice.text=@"--";
        self.SellNum.text=@"--";
    }else{
        self.SellNum.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:Sellmodel.amount withlimit:coinScale]];
        self.SellPrice.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:Sellmodel.price withlimit:baseCoinScale]];
    }

}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
