//
//  OrderConfirmAlterView.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "OrderConfirmAlterView.h"

@implementation OrderConfirmAlterView

-(void)awakeFromNib{
    [super awakeFromNib];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.buyBGView.clipsToBounds = YES;
    self.buyBGView.layer.cornerRadius = 6;
    self.buyBGView.layer.borderWidth = 1;
    self.buyBGView.layer.borderColor = [UIColor grayColor].CGColor;
    self.backgroundColor=[[UIColor blackColor] colorWithAlphaComponent:0.4];

    self.certainPlaceOrderLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"orderConfirmation" value:nil table:@"English"];
    [self.cancelButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"cancel" value:nil table:@"English"] forState:UIControlStateNormal];
    [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"ok" value:nil table:@"English"] forState:UIControlStateNormal];
}
-(void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{

    [self removeFromSuperview];
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
