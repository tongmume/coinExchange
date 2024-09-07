//
//  MyBillChatViewController.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/4.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MyBillDetailInfoModel.h"
#import "ChatGroupInfoModel.h"
@interface MyBillChatViewController : BaseViewController
@property(nonatomic,strong)MyBillDetailInfoModel*model;

@property(nonatomic,assign)NSInteger clickIndex;//1从首页聊天组进入
@property(nonatomic,strong)ChatGroupInfoModel *groupModel;//聊天组对象
@property(nonatomic,copy)NSString *avatar;//头像
@end
