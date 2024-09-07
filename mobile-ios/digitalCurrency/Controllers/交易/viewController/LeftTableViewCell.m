//
//  LeftTableViewCell.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/17.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "LeftTableViewCell.h"

@implementation LeftTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
//    self.changelabel.layer.masksToBounds = YES;
//    self.changelabel.layer.cornerRadius = 15;

}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
-(void)configDataWithModel:(symbolModel*)model withtype:(int)type withIndex:(int)index{

    NSArray *array = [model.symbol componentsSeparatedByString:@"/"];
    NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];

    if (type == 1) {
        self.namelabel.attributedText = [self changefondstr:array.firstObject fondstr:array.lastObject];

    }else if (type==2){
        self.namelabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:17];
        self.changelabel.textAlignment=NSTextAlignmentCenter;
        self.changelabel.layer.cornerRadius=3;
        self.changelabel.layer.masksToBounds=YES;
        
        self.namelabel.attributedText = [self contactchangefondstr:array.firstObject fondstr:array.lastObject];
       
    }else if (type==3){
        self.namelabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:17];
         self.changelabel.textAlignment=NSTextAlignmentCenter;
        self.changelabel.layer.cornerRadius=3;
        self.changelabel.layer.masksToBounds=YES;
        self.namelabel.attributedText=[self optionfondstr:array.firstObject fondstr:array.lastObject];
        self.changelabel.hidden = YES;
        self.pricelabel.hidden = YES;
       
    }else{
        self.namelabel.text=[array firstObject];

    }
    self.pricelabel.text=[NSString stringWithFormat:@"%@",[ToolUtil judgeStringForDecimalPlaces:[close stringValue]]];

    self.changelabel.text = [NSString stringWithFormat:@"%.2f%%", model.chg*100];
    if (model.change <0) {
        //        self.moneyLabel.textColor=RedColor;
        self.changelabel.textColor=RedColor;
        self.changelabel.text = [NSString stringWithFormat:@"%.2f%%", model.chg*100];

    }else{
        //        self.moneyLabel.textColor=GreenColor;
        self.changelabel.textColor=GreenColor;
        self.changelabel.text = [NSString stringWithFormat:@"+%.2f%%", model.chg*100];

    }

}

-(NSMutableAttributedString *)changefondstr:(NSString *)firststr fondstr:(NSString *)fondstr{
    NSMutableAttributedString *Str = [[NSMutableAttributedString alloc]initWithString:[NSString stringWithFormat:@"%@/%@",firststr,fondstr]];
    [Str addAttribute:NSFontAttributeName value:[UIFont fontWithName:@"PingFang SC" size:11] range:NSMakeRange(firststr.length, fondstr.length + 1)];
    [Str addAttribute:NSForegroundColorAttributeName value:RGBOF(0x999999) range:NSMakeRange(firststr.length, fondstr.length + 1)];

    return Str;

}
-(NSMutableAttributedString *)contactchangefondstr:(NSString *)firststr fondstr:(NSString *)fondstr{
    
    NSString *substring=LocalizationKey(@"constract");
    NSMutableAttributedString *Str = [[NSMutableAttributedString alloc]initWithString:[NSString stringWithFormat:@"%@/%@ %@",firststr,fondstr,substring]];
    [Str addAttribute:NSFontAttributeName value:[UIFont fontWithName:@"PingFang SC" size:11] range:NSMakeRange(firststr.length, fondstr.length + 1)];
    [Str addAttribute:NSForegroundColorAttributeName value:AppTextColor_Level_2 range:NSMakeRange(firststr.length, fondstr.length + 1)];
    [Str addAttribute:NSFontAttributeName value:[UIFont fontWithName:@"PingFang SC" size:11] range:NSMakeRange(firststr.length+fondstr.length,substring.length+2)];
    return Str;
}

-(NSMutableAttributedString *)optionfondstr:(NSString *)firststr fondstr:(NSString *)fondstr{
    
    NSString *substring=LocalizationKey(@"Option_contract");
    NSMutableAttributedString *Str = [[NSMutableAttributedString alloc]initWithString:[NSString stringWithFormat:@"%@/%@ %@",firststr,fondstr,substring]];
    [Str addAttribute:NSFontAttributeName value:[UIFont fontWithName:@"PingFang SC" size:11] range:NSMakeRange(firststr.length, fondstr.length + 1)];
    [Str addAttribute:NSForegroundColorAttributeName value:AppTextColor_Level_2 range:NSMakeRange(firststr.length, fondstr.length + 1)];
    [Str addAttribute:NSFontAttributeName value:[UIFont fontWithName:@"PingFang SC" size:11] range:NSMakeRange(firststr.length+fondstr.length,substring.length+2)];
    return Str;
}
@end
