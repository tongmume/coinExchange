//
//  BuyCoinsDetail1TableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BuyCoinsDetail1TableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIImageView *headImage;//头像
@property (weak, nonatomic) IBOutlet UILabel *userName;//昵称
@property (weak, nonatomic) IBOutlet UILabel *limitNum;//限额
@property (weak, nonatomic) IBOutlet UILabel *coinNum;//货币数量
@property (weak, nonatomic) IBOutlet UILabel *tradingNum; //交易数量
@property (weak, nonatomic) IBOutlet UILabel *message;//广告留言
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWays1Width;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWays2Width;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWays3Width;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWay1Left;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWay2Left;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payWay3Left;
@property (weak, nonatomic) IBOutlet UIImageView *payWays2;//微信支付方式
@property (weak, nonatomic) IBOutlet UIImageView *payWays3;//银行卡支付方式
@property (weak, nonatomic) IBOutlet UIImageView *payWays1;//支付宝支付方式
@property (weak, nonatomic) IBOutlet UILabel *remainAmountLabel;

@property (weak, nonatomic) IBOutlet NSLayoutConstraint *zuanshiWidthConstraints;

-(void)configLabelWithArray:(NSArray*)payArr;
@end
