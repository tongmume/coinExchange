//
//  OrderConfirmAlterView.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface OrderConfirmAlterView : UIView
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;//取消按钮
@property (weak, nonatomic) IBOutlet UILabel *buyPrice;//购买价格
@property (weak, nonatomic) IBOutlet UILabel *buyNum;//购买数量
@property (weak, nonatomic) IBOutlet UILabel *buyTotal;//购买总额
@property (weak, nonatomic) IBOutlet UIButton *certainButton;//确认按钮
@property (weak, nonatomic) IBOutlet UILabel *remindContent;//提醒内容
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property (weak, nonatomic) IBOutlet UIView *buyBGView; //购买的View，主要设置外边框
@property (weak, nonatomic) IBOutlet UIView *boardView;
@property (weak, nonatomic) IBOutlet UILabel *titleLabel1;
@property (weak, nonatomic) IBOutlet UILabel *titleLabel2;
@property (weak, nonatomic) IBOutlet UILabel *titleLabel3;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *certainPlaceOrderLabel;
@end
