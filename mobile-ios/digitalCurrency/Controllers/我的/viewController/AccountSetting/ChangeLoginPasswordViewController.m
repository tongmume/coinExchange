//
//  ChangeLoginPasswordViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChangeLoginPasswordViewController.h"
#import "MineNetManager.h"
#import "ForgetViewController.h"

@interface ChangeLoginPasswordViewController ()
@property (weak, nonatomic) IBOutlet UITextField *verificationCode;//验证码输入框
@property (weak, nonatomic) IBOutlet UITextField *oldPassword;//旧登录密码
@property (weak, nonatomic) IBOutlet UITextField *latestPassword;//新登录密码
@property (weak, nonatomic) IBOutlet UITextField *certainPassword;//确认密码
@property (weak, nonatomic) IBOutlet UIButton *verifyButton;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *oldLoginPasswordLabel;
@property (weak, nonatomic) IBOutlet UILabel *latestLoginPasswordLabel;
@property (weak, nonatomic) IBOutlet UILabel *certainPasswordLabel;
@property (weak, nonatomic) IBOutlet UILabel *verifyCodeLabel;


@end

@implementation ChangeLoginPasswordViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = kRGBColor(18, 22, 28);
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"changeLoginPassword" value:nil table:@"English"];
    [self setRightItem];
    self.oldLoginPasswordLabel.font =  self.latestLoginPasswordLabel.font = self.certainPasswordLabel.font = self.oldLoginPasswordLabel.font = self.verifyCodeLabel.font =  self.verifyButton.titleLabel.font = [UIFont systemFontOfSize: 15 * kWindowWHOne];

    self.oldLoginPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"oldLoginPassword" value:nil table:@"English"];
     self.latestLoginPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"newLoginPassword" value:nil table:@"English"];
     self.certainPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"certainPassword" value:nil table:@"English"];
     self.verifyCodeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"verifyCode" value:nil table:@"English"];
    [self.verifyButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];
    self.verificationCode.placeholder =[[ChangeLanguage bundle] localizedStringForKey:@"inputCode" value:nil table:@"English"];
    self.oldPassword.placeholder =[[ChangeLanguage bundle] localizedStringForKey:@"inputOldLoginPassword" value:nil table:@"English"];
    self.latestPassword.placeholder =[[ChangeLanguage bundle] localizedStringForKey:@"inputNewLoginPassword" value:nil table:@"English"];
    self.certainPassword.placeholder =[[ChangeLanguage bundle] localizedStringForKey:@"inputCertainLoginPassword" value:nil table:@"English"];
    //[self rightBarItemWithTitle:@"忘记密码"];
//    [_verificationCode setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_oldPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_latestPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_certainPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];

}

- (void)setRightItem{
    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    btn.frame = CGRectMake(0, 0, 30, 44);
    [btn setTitle:LocalizationKey(@"save") forState:UIControlStateNormal];
    [btn setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateNormal];
    btn.titleLabel.font = [UIFont systemFontOfSize:14];
    [btn addTarget:self action:@selector(RighttouchEvent) forControlEvents:UIControlEventTouchUpInside];

    UIBarButtonItem*item=[[UIBarButtonItem alloc] initWithCustomView:btn];
    self.navigationItem.rightBarButtonItem = item;
}

- (void)RighttouchEvent{

    if ([self.oldPassword.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputNewLoginPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.latestPassword.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputNewLoginPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.certainPassword.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputCertainLoginPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![self.latestPassword.text isEqualToString:self.certainPassword.text]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"checkPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.verificationCode.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputCode" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager resetLoginPasswordForCode:self.verificationCode.text withOldPassword:self.oldPassword.text withLatestPassword:self.latestPassword.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //上传成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{

                    [self.navigationController popViewControllerAnimated:YES];
                });

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//MARK:--获取验证码的点击事件
- (IBAction)getVerificationCodeBtnClick:(UIButton *)sender {
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"hardLoading" value:nil table:@"English"]];
    [MineNetManager resetLoginPasswordCodeForCompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"getMessageCodeTip" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
                __block int timeout=60; //倒计时时间
                dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
                dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
                dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
                dispatch_source_set_event_handler(_timer, ^{
                    if(timeout<=0){ //倒计时结束，关闭
                        dispatch_source_cancel(_timer);
                        dispatch_async(dispatch_get_main_queue(), ^{
                            [self.verifyButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];
                            self.verifyButton.userInteractionEnabled = YES;
                        });
                    }else{
                        int seconds = timeout % 90;
                        NSString *strTime = [NSString stringWithFormat:@"%.2d", seconds];
                        dispatch_async(dispatch_get_main_queue(), ^{
                            [self.verifyButton setTitle: [NSString stringWithFormat:@"%@s",strTime] forState:UIControlStateNormal];
                            self.verifyButton.userInteractionEnabled = NO;
                        });
                        timeout--;
                    }
                });
                dispatch_resume(_timer);

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];

            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
    }];
}


@end
