//
//  NoticeTableViewCell.m
//  digitalCurrency
//
//  Created by startlink on 2019/7/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "NoticeTableViewCell.h"
#import "HelpeCenterViewController.h"
#import "NoticeCenterViewController.h"
#import "FrenchCurrencyViewController.h"

@implementation NoticeTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.transactionlabel.font = self.helplebel.font  = self.noticelabel.font = [UIFont fontWithName:@"Helvetica-Bold" size:16 * kWindowWHOne];
     self.safelabel.font = self.problemlabel.font  = self.noticecontentlabel.font = [UIFont systemFontOfSize:12 * kWindowWHOne];
    self.assestAmountLabel.font=  self.helplebel.font  = self.noticelabel.font = [UIFont fontWithName:@"Helvetica-Bold" size:16 * kWindowWHOne];


    self.transactionview.userInteractionEnabled = YES;
    self.helpView.userInteractionEnabled = YES;
    self.Noticeview.userInteractionEnabled = YES;


    UITapGestureRecognizer *tap1 = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(transactionaction)];
    [self.transactionview addGestureRecognizer:tap1];

    UITapGestureRecognizer *tap3 = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(helpaction)];
    [self.helpView addGestureRecognizer:tap3];

    UITapGestureRecognizer *tap5 = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(noticeaction)];
    [self.Noticeview addGestureRecognizer:tap5];
    self.logionButton = [[UIButton alloc] init];
    [self.transactionview addSubview:self.logionButton];
    [self.logionButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.transactionlabel);
        make.top.equalTo(self.safelabel.mas_bottom).offset(5);
        make.width.equalTo(@80);
    }];

    self.logionButton.titleLabel.font = [UIFont systemFontOfSize:12];
    self.logionButton.layer.borderColor = UIColor.grayColor.CGColor;
    self.logionButton.layer.borderWidth = 1;
    self.logionButton.layer.cornerRadius = 4;
    self.logionButton.layer.masksToBounds = YES;
    self.logionButton.titleLabel.adjustsFontSizeToFitWidth = YES;
    [self.logionButton setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateNormal];
    [self.logionButton addTarget:self action:@selector(loginAction) forControlEvents:UIControlEventTouchUpInside];
    
    self.rechangeButton.titleLabel.font = self.helplebel.font  = self.noticelabel.font = [UIFont fontWithName:@"Helvetica-Bold" size:16 * kWindowWHOne];
    self.rechangeButton.backgroundColor = RGBOF(0xF0A70A);
    self.rechangeButton.layer.cornerRadius = 2;
    [self.rechangeButton setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
    self.rechangeButton.layer.masksToBounds = YES;
    [self.rechangeButton addTarget:self action:@selector(rechangeAction) forControlEvents:UIControlEventTouchUpInside];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

- (void)rechangeAction{
    if (self.rechangeBlock) {
        self.rechangeBlock();
    }
}

- (void)loginAction {
    if (self.loginBlock) {
        self.loginBlock();
    }
}

//法币交易
-(void)transactionaction{
    if (self.CtoCBlock) {
        self.CtoCBlock();
    }
}

//帮助中心
-(void)helpaction{
    HelpeCenterViewController *help = [[HelpeCenterViewController alloc] init];
    [[AppDelegate sharedAppDelegate] pushViewController:help];
}

//公告中心
-(void)noticeaction{
    NoticeCenterViewController *NoticeVC = [NoticeCenterViewController new];
    [[AppDelegate sharedAppDelegate] pushViewController:NoticeVC];
}

@end
