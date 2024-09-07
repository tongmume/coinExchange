//
//  MyAdvertisingAlterView.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyAdvertisingAlterView.h"

@implementation MyAdvertisingAlterView

-(void)awakeFromNib{
    [super awakeFromNib];




    self.backgroundColor=[[UIColor blackColor] colorWithAlphaComponent:0.4];
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
