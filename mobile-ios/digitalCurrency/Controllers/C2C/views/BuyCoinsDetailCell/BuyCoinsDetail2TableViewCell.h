//
//  BuyCoinsDetail2TableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BuyCoinsDetail2TableViewCell;

@protocol BuyCoinsDetail2TableViewCellDelegate <NSObject>
- (void)textFieldTag:(NSInteger)index TextFieldString: (NSString *)textString;
@end

@interface BuyCoinsDetail2TableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *tipstring; //提示语

@property (weak, nonatomic) IBOutlet UILabel *coinType1;//第一种货币类型
@property (weak, nonatomic) IBOutlet UITextField *coinType1Num;//第一种货币类型数量
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *coinType1Width;//第一种货币宽度
@property (weak, nonatomic) IBOutlet UILabel *coinType2;//第2种货币类型
@property (weak, nonatomic) IBOutlet UITextField *coinType2Num;//第2种货币类型数量
@property (weak, nonatomic) IBOutlet UILabel *contentLabel;//交易提醒内容
@property (nonatomic,weak) id<BuyCoinsDetail2TableViewCellDelegate> delegate;
@property (weak, nonatomic) IBOutlet UILabel *tradTipLabel;

@end
