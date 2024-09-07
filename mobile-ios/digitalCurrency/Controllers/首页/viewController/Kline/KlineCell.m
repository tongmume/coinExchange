//
//  KlineCell.m
//  BTC-Kline
//
//  Created by sunliang on 2019/5/17.
//  Copyright © 2019年 yate1996. All rights reserved.
//

#import "KlineCell.h"
#import "UIColor+Y_StockChart.h"
@implementation KlineCell

- (void)awakeFromNib {
    [super awakeFromNib];
    [self.btn1 setTitle:LocalizationKey(@"line") forState:UIControlStateNormal];
    [self.timeLineBtn setTitle:LocalizationKey(@"min") forState:UIControlStateNormal];
    [self.btn3 setTitle:LocalizationKey(@"fivemin") forState:UIControlStateNormal];
    [self.btn4 setTitle:LocalizationKey(@"hours") forState:UIControlStateNormal];
    [self.btn5 setTitle:LocalizationKey(@"days") forState:UIControlStateNormal];
    [self.btn6 setTitle:LocalizationKey(@"mainKline") forState:UIControlStateNormal];
    [self.btn7 setTitle:LocalizationKey(@"subKline") forState:UIControlStateNormal];
    [self.btn8 setTitle:LocalizationKey(@"hideKline") forState:UIControlStateNormal];
    [self.btn9 setTitle:LocalizationKey(@"hideKline") forState:UIControlStateNormal];
    [self.btn10 setTitle:LocalizationKey(@"weeks") forState:UIControlStateNormal];
    [self.btn11 setTitle:LocalizationKey(@"monthkline") forState:UIControlStateNormal];
    [self.btn12 setTitle:LocalizationKey(@"fivethmin") forState:UIControlStateNormal];
    [self.btn13 setTitle:LocalizationKey(@"thirtymin") forState:UIControlStateNormal];
    [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
    [self.indexBtn setTitle:LocalizationKey(@"index") forState:UIControlStateNormal];
    self.moreView.backgroundColor=mainColor;
    self.indView.backgroundColor=mainColor;
    self.mainCurrentBtn=self.maBtn;
  //  self.subCurrentBtn=self.macdBtn;
    self.KlineCurrentBtn=self.timeLineBtn;
    [self.maBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
   // [self.macdBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
    [self.timeLineBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
    self.lineView.backgroundColor=mainColor;
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        self.klineView=[[UIView alloc]initWithFrame:CGRectMake(0, 30, self.timeLineBtn.mj_w/2, 1)];
        self.klineView.centerX=self.timeLineBtn.centerX;
        self.klineView.backgroundColor=[UIColor ma30Color];
        [self addSubview:self.klineView];
    });
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(hideindex)name:@"HIDEINDEX" object:nil];
    // Initialization code
}
-(void)hideindex{
    self.moreView.hidden=YES;
    self.indView.hidden=YES;
    self.moreBtn.backgroundColor=[UIColor clearColor];
    self.indexBtn.backgroundColor=[UIColor clearColor];
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
//K线按钮点击事件
- (IBAction)btnClick:(UIButton *)sender {
    if (sender.tag!=10086&&sender.tag!=10087) {
        [self.KlineCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
        self.KlineCurrentBtn=sender;
        [UIView animateWithDuration:0.2 animations:^{
           self.klineView.centerX=sender.centerX;
        }];
    }

    switch (sender.tag) {
        case 1:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
             [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
        }
            break;
        case 2:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
             [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
        }
            break;
        case 3:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
            [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
        }
            break;
        case 4:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
             [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
        }
            break;
        case 5:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
             [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
        }
            break;

        case 6:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
            [self.moreBtn setTitle:LocalizationKey(@"fivethmin")forState:UIControlStateNormal];
            [self.moreBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [UIView animateWithDuration:0.2 animations:^{
                self.klineView.centerX=self.moreBtn.centerX;
            }];
        }
            break;
        case 7:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
            [self.moreBtn setTitle:LocalizationKey(@"thirtymin") forState:UIControlStateNormal];
             [self.moreBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [UIView animateWithDuration:0.2 animations:^{
                self.klineView.centerX=self.moreBtn.centerX;
            }];
        }
            break;
        case 8:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
            [self.moreBtn setTitle:LocalizationKey(@"weeks") forState:UIControlStateNormal];
            [self.moreBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [UIView animateWithDuration:0.2 animations:^{
                self.klineView.centerX=self.moreBtn.centerX;
            }];
        }
            break;
        case 9:
        {
            self.moreView.hidden=YES;
            self.indView.hidden=YES;
            [self.moreBtn setTitle:LocalizationKey(@"monthkline") forState:UIControlStateNormal];
            [self.moreBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
            self.moreBtn.backgroundColor=[UIColor clearColor];
            self.indexBtn.backgroundColor=[UIColor clearColor];
            [UIView animateWithDuration:0.2 animations:^{
                self.klineView.centerX=self.moreBtn.centerX;
            }];
        }
            break;
        case 10086:
        {
            if (self.moreView.hidden) {
                self.moreView.hidden=NO;
            }else{
                 self.moreView.hidden=YES;
            }
            self.indView.hidden=YES;
//            self.moreBtn.backgroundColor=[[UIColor whiteColor] colorWithAlphaComponent:0.8];
//            self.indexBtn.backgroundColor=[UIColor clearColor];

            return;
        }
            break;
        case 10087:
        {
            if (self.indView.hidden) {
                self.indView.hidden=NO;
            }else{
                self.indView.hidden=YES;
            }
            self.moreView.hidden=YES;
//            self.moreBtn.backgroundColor=[UIColor clearColor];
//            self.indexBtn.backgroundColor=[[UIColor whiteColor] colorWithAlphaComponent:0.8];
            return;
        }
            break;
        default:
            break;
    }
    NSDictionary *dict =[[NSDictionary alloc]initWithObjectsAndKeys:[NSNumber numberWithInteger:sender.tag],@"buttonTag",nil];
    NSNotification *notification =[NSNotification notificationWithName:@"tongzhi" object:nil userInfo:dict];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];

}
//主图指标按钮
- (IBAction)indexBtn:(UIButton *)sender {
    self.indexBtn.backgroundColor=[UIColor clearColor];
    if (sender.tag==106) {//隐藏
        [self.mainCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
    }else{
        [self.mainCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
        self.mainCurrentBtn=sender;
    }
    self.moreView.hidden=YES;
    self.indView.hidden=YES;
    NSDictionary *dict =[[NSDictionary alloc]initWithObjectsAndKeys:[NSNumber numberWithInteger:sender.tag],@"buttonTag",nil];
    NSNotification *notification =[NSNotification notificationWithName:@"tongzhi" object:nil userInfo:dict];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];

}
//副图指标按钮
- (IBAction)Subgraph:(UIButton *)sender {
    self.indexBtn.backgroundColor=[UIColor clearColor];
    if (sender.tag==102) {//隐藏
        [self.subCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
    }else{
        [self.subCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
        self.subCurrentBtn=sender;
    }
    self.moreView.hidden=YES;
    self.indView.hidden=YES;
    NSDictionary *dict =[[NSDictionary alloc]initWithObjectsAndKeys:[NSNumber numberWithInteger:sender.tag],@"buttonTag",nil];
    NSNotification *notification =[NSNotification notificationWithName:@"tongzhi" object:nil userInfo:dict];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];

}



@end
