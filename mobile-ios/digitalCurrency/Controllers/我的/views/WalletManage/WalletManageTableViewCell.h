//
//  WalletManageTableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "WalletManageModel.h"

@class WalletManageTableViewCell;

@protocol WalletManageTableViewCellDelegate <NSObject>
- (void)buttonIndex:(NSIndexPath *)indexPath withModel:(WalletManageModel *)model;
@end
@interface WalletManageTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *availableTitleWidth; //可用数量的宽度
//@property (weak, nonatomic) IBOutlet UIButton *clickButton;
@property (weak, nonatomic) IBOutlet UILabel *coinType;//货币种类
@property (weak, nonatomic) IBOutlet UILabel *availableNum;//可用数量
@property (weak, nonatomic) IBOutlet UILabel *freezeNum;//冻结数量
@property (weak, nonatomic) IBOutlet UILabel *lockingNum;//锁定数量
//@property (weak, nonatomic) IBOutlet UIButton *clickIndex;//点击按钮
//@property (weak, nonatomic) IBOutlet UIButton *chargeMoneyButton;//充币按钮
//@property (weak, nonatomic) IBOutlet NSLayoutConstraint *chargeMoneyButtonWidth;//充币按钮宽度
//@property (weak, nonatomic) IBOutlet UIButton *mentionMoneyButton;//提币按钮

@property (nonatomic,weak) id<WalletManageTableViewCellDelegate> delegate;
@property(nonatomic,strong)NSIndexPath *index;
@property(nonatomic,strong)WalletManageModel *model;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *availableLabel;
@property (weak, nonatomic) IBOutlet UILabel *freezeLabel;
@property (weak, nonatomic) IBOutlet UILabel *lockingLabel;

@end
