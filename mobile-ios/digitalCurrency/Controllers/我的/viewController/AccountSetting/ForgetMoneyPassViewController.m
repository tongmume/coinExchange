//
//  MoneyPasswordViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ForgetMoneyPassViewController.h"
#import "MineNetManager.h"
#import "AccountSettingViewController.h"
@interface ForgetMoneyPassViewController ()
@property (weak, nonatomic) IBOutlet UITextField *moneyPassword;//新密码
@property (weak, nonatomic) IBOutlet UITextField *certainPassword;//确认密码
@property (weak, nonatomic) IBOutlet UITextField *code;//验证码
@property (weak, nonatomic) IBOutlet UIButton *codeButton;//获取验证码按钮
@property (weak, nonatomic) IBOutlet UIView *codeView;//验证码视图
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *codeViewHeight;//验证码视图的高度
//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *codeLabel;
@property (weak, nonatomic) IBOutlet UILabel *latestMoneyPasswordLabel;
@property (weak, nonatomic) IBOutlet UILabel *certainMoneyPasswordLabel;

@end

@implementation ForgetMoneyPassViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = kRGBColor(18, 22, 28);
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"changeMoneyPassword" value:nil table:@"English"];
    [self setRightItem];
    self.codeButton.layer.cornerRadius = 15;
    self.codeButton.layer.borderColor = RGBOF(0xF0A70A).CGColor;
    self.codeButton.layer.borderWidth = 1;
    self.moneyPassword.secureTextEntry = YES;
    self.certainPassword.secureTextEntry = YES;

    self.codeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"verifyCode" value:nil table:@"English"];
    self.latestMoneyPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"newMoneyPassword" value:nil table:@"English"];
    self.certainMoneyPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"certainMoneyPassword" value:nil table:@"English"];
    self.code.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputCode" value:nil table:@"English"];
    self.moneyPassword.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputNewMoneyPassword" value:nil table:@"English"];
    self.certainPassword.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputCertainMoneyPassword" value:nil table:@"English"];
    [self.codeButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];

//    [_moneyPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_certainPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_code setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
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
    [self changeMoneyPassword];
}

//MARK:--获取验证码的点击事件
- (IBAction)codeBtnClick:(UIButton *)sender {
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"hardLoading" value:nil table:@"English"]];
    [MineNetManager resetMoneyPasswordCodeForCompleteHandle:^(id resPonseObj, int code) {
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
                            [self.codeButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];
                            self.codeButton.userInteractionEnabled = YES;
                        });
                    }else{
                        int seconds = timeout % 90;
                        NSString *strTime = [NSString stringWithFormat:@"%.2d", seconds];
                        dispatch_async(dispatch_get_main_queue(), ^{
                            [self.codeButton setTitle: [NSString stringWithFormat:@"%@s",strTime] forState:UIControlStateNormal];
                            self.codeButton.userInteractionEnabled = NO;
                        });
                        timeout--;
                    }
                });
                dispatch_resume(_timer);

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
               // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
    }];
}

//MARK:--修改资金密码的接口
-(void)changeMoneyPassword{

    if ([self.code.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputCode" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.moneyPassword.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputNewMoneyPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.certainPassword.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputCertainMoneyPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![self.certainPassword.text isEqualToString:self.moneyPassword.text]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"checkPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"hardUpLoading" value:nil table:@"English"]];
    [MineNetManager resetMoneyPasswordForCode:self.code.text withNewPassword:self.moneyPassword.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //上传成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{

                    for (UIViewController *controller in self.navigationController.viewControllers) {
                        if ([controller isKindOfClass:[AccountSettingViewController class]]) {
                            AccountSettingViewController *accountVC =(AccountSettingViewController *)controller;
                            [self.navigationController popToViewController:accountVC animated:YES];
                        }
                    }
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

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
