//
//  ChatTableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ChatModel.h"

@interface ChatTableViewCell : UITableViewCell
@property (nonatomic,strong) UIImageView *headImageView; // 用户头像
@property (nonatomic,strong) UIImageView *backView; // 气泡
@property (nonatomic,strong) UILabel *contentLabel; // 气泡内文本

-(void)refreshCell:(ChatModel *)model; // 安装我们的cell
@end
