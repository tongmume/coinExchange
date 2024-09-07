//
//  CToCTableViewCell.h
//  digitalCurrency
//
//  Created by chu on 2019/8/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CoinUserInfoModel.h"

typedef void(^buyBtnBlock)(void);

@interface CToCTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIImageView *headImageView;
@property (weak, nonatomic) IBOutlet UILabel *nicknameLabel;
@property (weak, nonatomic) IBOutlet UIImageView *vipImageView;
@property (weak, nonatomic) IBOutlet UILabel *tradeNum;//交易量
@property (weak, nonatomic) IBOutlet UILabel *coinNum;//货币数量
@property (weak, nonatomic) IBOutlet UILabel *limitNum;//限额
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;
@property (weak, nonatomic) IBOutlet UIButton *buyBtn;
@property (weak, nonatomic) IBOutlet UIView *payTypeView;
@property (weak, nonatomic) IBOutlet UIImageView *payImageView1;
@property (weak, nonatomic) IBOutlet UIImageView *payImageView2;
@property (weak, nonatomic) IBOutlet UIImageView *payImageView3;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payImageView1WidthConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payImageView2WidthCOnstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *payImageView3WidthConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *leftConstraint1;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *left2Constraint;
@property (nonatomic, strong) CoinUserInfoModel *coinUserInfoModel;
-(void)cellForArray:(NSArray *)payArr;
@property (weak, nonatomic) IBOutlet UILabel *danjiaPriceLabel;
@property (nonatomic, copy) buyBtnBlock block;
@end
