//
//  ShowLoGinVC.h
//  BaseProject
//
//  Created by YLCai on 2019/6/15.
//  Copyright © 2019年 YLCai. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface ShowLoGinVC : NSObject

//显示登录控制器   2:根视图控制器进入present  3:普通控制器进入pop
+(void)showLoginVc:(UIViewController *)vc withTipMessage:(NSString *)tipMessage;

@end
