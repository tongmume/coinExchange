//
//  WalletManageDetailTableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface WalletManageDetailTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *dateLabel;//日期
@property (weak, nonatomic) IBOutlet UILabel *mentionMoneyNum;//提币数量
@property (weak, nonatomic) IBOutlet UILabel *mentionMoneyType;//提币的货币种类
@property (weak, nonatomic) IBOutlet UILabel *statusLabel;//完成状态
@property (weak, nonatomic) IBOutlet UILabel *coinType;//提币或充币的状态
@property (weak, nonatomic) IBOutlet UILabel *feeLabel;//应付手续费
@property (weak, nonatomic) IBOutlet UILabel *deductiblelabel;//抵扣手续费

@property (weak, nonatomic) IBOutlet UILabel *Actuallabel;//实际手续费

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *tradTimeLabel;
@property (weak, nonatomic) IBOutlet UILabel *typeLabel;
@property (weak, nonatomic) IBOutlet UILabel *amountLabel;
@property (weak, nonatomic) IBOutlet UILabel *poundage; //应付手续费标题
@property (weak, nonatomic) IBOutlet UILabel *Deductible;//抵扣手续费标题
@property (weak, nonatomic) IBOutlet UILabel *Actual;//实际手续费标题
@end
