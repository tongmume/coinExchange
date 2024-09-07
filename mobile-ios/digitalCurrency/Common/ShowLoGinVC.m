//
//  ShowLoGinVC.m
//  BaseProject
//
//  Created by YLCai on 2019/6/15.
//  Copyright © 2019年 YLCai. All rights reserved.
//

#import "ShowLoGinVC.h"
#import "LoginViewController.h"
#import "YLNavigationController.h"
@implementation ShowLoGinVC

//显示登录控制器  //登录失效，重新登录
+(void)showLoginVc:(UIViewController *)vc withTipMessage:(NSString *)tipMessage{
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:tipMessage message:@"" preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *action1 = [UIAlertAction actionWithTitle:@"确认" style:UIAlertActionStyleDestructive handler:^(UIAlertAction * _Nonnull action) {
        LoginViewController *loginVC = [[LoginViewController alloc] init];
        YLNavigationController *logNavi = [[YLNavigationController alloc] initWithRootViewController:loginVC];
        loginVC.hidesBottomBarWhenPushed = YES;
        [vc presentViewController:logNavi animated:YES completion:nil];

    }];

    [alert addAction:action1];
    [vc presentViewController:alert animated:YES completion:nil];
}

@end
