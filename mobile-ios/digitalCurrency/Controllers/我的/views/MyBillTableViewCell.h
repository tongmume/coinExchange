//
//  MyBillTableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/1/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MyBillTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIImageView *headImage;
@property (weak, nonatomic) IBOutlet UILabel *userName;
@property (weak, nonatomic) IBOutlet UILabel *billStatus;//BTC出售或购买状态
@property (weak, nonatomic) IBOutlet UILabel *coinNum;//BTC个数
@property (weak, nonatomic) IBOutlet UILabel *priceNum;//CNY个数
@property (weak, nonatomic) IBOutlet UILabel *payStatus;//支付状态

@end
