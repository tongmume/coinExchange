//
//  MyBillDetail1ViewController.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/3.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MyBillDetail1ViewController : BaseViewController
@property(nonatomic,copy)NSString *orderId;//订单号
@property(nonatomic,assign)NSInteger flagIndex; //1从首页聊天组进入 其他
@property(nonatomic,copy)NSString *avatar;//头像
@end
