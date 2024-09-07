//
//  CoinTableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/1/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CoinTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIImageView *headImage;//头像
@property (weak, nonatomic) IBOutlet UILabel *userName;//昵称
@property (weak, nonatomic) IBOutlet UIImageView *payWays2;//微信支付方式
@property (weak, nonatomic) IBOutlet UIImageView *payWays3;//银行卡支付方式
@property (weak, nonatomic) IBOutlet UIImageView *payWays1;//支付宝支付方式
@property (weak, nonatomic) IBOutlet UILabel *tradingNum;//交易量
@property (weak, nonatomic) IBOutlet UILabel *limitNum; //限额
@property (weak, nonatomic) IBOutlet UILabel *coinNum; //货币数量
@property (weak, nonatomic) IBOutlet UIButton *buyButton;//购买按钮
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWays1Width;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWays2Width;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWays3Width;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWay2Left;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWay3Left;
@property (weak, nonatomic) IBOutlet UILabel *remainAmount;//剩余数量

-(void)cellForArray:(NSArray *)payArr;
@end
