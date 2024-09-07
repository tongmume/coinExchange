//
//  PlatformMessageCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/1/29.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PlatformMessageCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UIImageView *messageImage;//消息图片
@property (weak, nonatomic) IBOutlet UILabel *messageTitle;//消息标题
@property (weak, nonatomic) IBOutlet UILabel *messageCreateTime;//消息创建时间

@end
