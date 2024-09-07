//
//  BuyCoinsDetail1TableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BuyCoinsDetail1TableViewCell.h"

@implementation BuyCoinsDetail1TableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];

    self.headImage.clipsToBounds = YES;
    self.headImage.layer.cornerRadius = 36/2.0;

}
-(void)configLabelWithArray:(NSArray*)payArr{
    if (payArr.count == 1) {
        if ([payArr[0] isEqualToString:@"支付宝"]) {
            self.payWays1Width.constant = 20;
            self.payWay1Left.constant = 6;
            self.payWays2Width.constant = 0;
            self.payWay2Left.constant = 0;
            self.payWays3Width.constant = 0;
            self.payWay3Left.constant = 0;
        }else if ([payArr[0] isEqualToString:@"微信"]){
            self.payWays1Width.constant = 0;
            self.payWay1Left.constant = 0;
            self.payWays2Width.constant = 20;
            self.payWay2Left.constant = 6;
            self.payWays3Width.constant = 0;
            self.payWay3Left.constant = 0;
        }else{
            self.payWays1Width.constant = 0;
            self.payWay1Left.constant = 0;
            self.payWays2Width.constant = 0;
            self.payWay2Left.constant = 0;
            self.payWays3Width.constant = 20;
            self.payWay3Left.constant = 6;
        }
    }else if (payArr.count == 2){
        if ([payArr[0] isEqualToString:@"支付宝"]) {
            self.payWays1Width.constant = 20;
            self.payWay1Left.constant = 6;
            if ([payArr[1] isEqualToString:@"微信"] ) {
                self.payWays2Width.constant = 20;
                self.payWay2Left.constant = 6;
                self.payWays3Width.constant = 0;
                self.payWay3Left.constant = 0;
            }else {
                self.payWays2Width.constant = 0;
                self.payWay2Left.constant = 0;
                self.payWays3Width.constant = 20;
                self.payWay3Left.constant = 6;
            }
        }else if ([payArr[0] isEqualToString:@"微信"]){
            self.payWays2Width.constant = 20;
            self.payWay2Left.constant = 6;
            if ([payArr[1] isEqualToString:@"支付宝"]) {
                self.payWays1Width.constant = 20;
                self.payWay1Left.constant = 6;
                self.payWays3Width.constant = 0;
                self.payWay3Left.constant = 0;
            }else{
                self.payWays1Width.constant = 0;
                self.payWay1Left.constant = 0;
                self.payWays3Width.constant = 20;
                self.payWay3Left.constant = 6;
            }
        }else{
            self.payWays3Width.constant = 20;
            self.payWay3Left.constant = 6;
            if ([payArr[1] isEqualToString:@"支付宝"]) {
                self.payWays1Width.constant = 20;
                self.payWay1Left.constant = 6;
                self.payWays2Width.constant = 0;
                self.payWay2Left.constant = 0;
            }else{
                self.payWays1Width.constant = 0;
                self.payWay1Left.constant = 0;
                self.payWays2Width.constant = 20;
                self.payWay2Left.constant = 6;
            }
        }
    }else if (payArr.count == 3){
        self.payWays1Width.constant = 20;
        self.payWay1Left.constant = 6;
        self.payWays2Width.constant = 20;
        self.payWay2Left.constant = 6;
        self.payWays3Width.constant = 20;
        self.payWay3Left.constant = 6;

    }else{
        self.payWays1Width.constant = 0;
        self.payWay1Left.constant = 0;
        self.payWays2Width.constant = 0;
        self.payWay2Left.constant = 0;
        self.payWays3Width.constant = 0;
        self.payWay3Left.constant = 0;
    }
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
