//
//  MoneyPasswordViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MoneyPasswordViewController.h"
#import "MineNetManager.h"
#import "ForgetMoneyPassViewController.h"
@interface MoneyPasswordViewController ()
@property (weak, nonatomic) IBOutlet UILabel *tipLabel;//提示语设置
@property (weak, nonatomic) IBOutlet UILabel *loginOrOldTitle;//旧密码或登录标题
@property (weak, nonatomic) IBOutlet UITextField *loginOrOldPassword;//旧密码或登录密码
@property (weak, nonatomic) IBOutlet UITextField *moneyPassword;//新密码
@property (weak, nonatomic) IBOutlet UITextField *certainPassword;//确认密码
@property (weak, nonatomic) IBOutlet UITextField *code;//验证码
@property (weak, nonatomic) IBOutlet UIButton *codeButton;//获取验证码按钮
@property (weak, nonatomic) IBOutlet UIView *codeView;//验证码视图
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *codeViewHeight;//验证码视图的高度
@property (weak, nonatomic) IBOutlet UIButton *forgetButton;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *forgetBtnWidth;//忘记密码的高度
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *oldPassOrLoginHeight;
@property (weak, nonatomic) IBOutlet UIView *oldPaddOrLoginView;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *codeLabel;
@property (weak, nonatomic) IBOutlet UILabel *latestMoneyPasswordLabel;
@property (weak, nonatomic) IBOutlet UILabel *certainMoneyPasswordLabel;

@end

@implementation MoneyPasswordViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    [self setRightItem];
    self.loginOrOldPassword.secureTextEntry = YES;
    self.moneyPassword.secureTextEntry = YES;
    self.certainPassword.secureTextEntry = YES;
    self.loginOrOldTitle.font =  self.codeLabel.font = self.latestMoneyPasswordLabel.font = self.certainMoneyPasswordLabel.font = [UIFont systemFontOfSize:15 * kWindowWHOne];
    self.tipLabel.font =  self.loginOrOldPassword.font = self.moneyPassword.font = self.certainPassword.font
    = self.code.font =  self.codeButton.titleLabel.font = [UIFont systemFontOfSize:15 * kWindowWHOne];

    if (self.setStatus == 1) {
        //设置过，可以修改
        self.title = [[ChangeLanguage bundle] localizedStringForKey:@"changeMoneyPassword" value:nil table:@"English"];
        self.tipLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"changeMoneyPasswordTip" value:nil table:@"English"];
        self.oldPassOrLoginHeight.constant = 50;
        self.oldPaddOrLoginView.hidden = NO;
        self.loginOrOldTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"oldMoneyPassword" value:nil table:@"English"];
        self.loginOrOldPassword.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputOldMoneyPassword" value:nil table:@"English"];
        self.codeView.hidden = YES;
        self.codeViewHeight.constant = 0;
        self.forgetButton.hidden = NO;
        self.forgetBtnWidth.constant = 30;
    }else{
        self.title = LocalizationKey(@"setMoneyPassword");
        self.tipLabel.text = LocalizationKey(@"setMoneyPasswordTip");
        self.oldPassOrLoginHeight.constant = 0;
        self.oldPaddOrLoginView.hidden = YES;
        self.codeView.hidden = YES;
        self.codeViewHeight.constant = 0;
        self.forgetButton.hidden = YES;
        self.forgetBtnWidth.constant = 0;
    }
    self.latestMoneyPasswordLabel.text =LocalizationKey(@"newMoneyPassword");
    self.certainMoneyPasswordLabel.text =LocalizationKey(@"certainMoneyPassword");
    self.moneyPassword.placeholder = LocalizationKey(@"inputNewMoneyPassword");
    self.certainPassword.placeholder = LocalizationKey(@"inputCertainMoneyPassword");
    [self.forgetButton setTitle:LocalizationKey(@"forgetPassword") forState:UIControlStateNormal];

//    [_moneyPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_certainPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_loginOrOldPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_code setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
    // Do any additional setup after loading the view from its nib.
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
    if (self.setStatus == 1) {
        //设置过，可以修改
        [self changeMoneyPassword];
    }else{
        [self setMoneyPassword];
    }
}

//MARK:--忘记密码的点击事件
- (IBAction)forgetBtnClick:(id)sender {
    ForgetMoneyPassViewController *forgetVC = [[ForgetMoneyPassViewController alloc] init];
    forgetVC.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:forgetVC animated:YES];
}
//MARK:--获取验证码的点击事件
- (IBAction)codeBtnClick:(UIButton *)sender {
    [EasyShowLodingView showLodingText:LocalizationKey(@"hardLoading")];
    [MineNetManager resetMoneyPasswordCodeForCompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:LocalizationKey(@"getMessageCodeTip") duration:1.5 position:CSToastPositionCenter];
                __block int timeout=60; //倒计时时间
                dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
                dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
                dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
                dispatch_source_set_event_handler(_timer, ^{
                    if(timeout<=0){ //倒计时结束，关闭
                        dispatch_source_cancel(_timer);
                        dispatch_async(dispatch_get_main_queue(), ^{
                            [self.codeButton setTitle:LocalizationKey(@"getVerifyCode") forState:UIControlStateNormal];
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
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
    }];
}

//MARK:--修改资金密码的接口
-(void)changeMoneyPassword{

    if ([self.loginOrOldPassword.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputOldMoneyPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.moneyPassword.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputNewMoneyPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.certainPassword.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputCertainMoneyPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![self.certainPassword.text isEqualToString:self.moneyPassword.text]) {
        [self.view makeToast:LocalizationKey(@"checkPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:LocalizationKey(@"hardUpLoading")];
    [MineNetManager resetMoneyPasswordForOldPassword:self.loginOrOldPassword.text withLatestPassword:self.moneyPassword.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //上传成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{

                    [self.navigationController popViewControllerAnimated:YES];
                });

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
               // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--设置资金密码的接口
-(void)setMoneyPassword{

    if ([self.moneyPassword.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputNewMoneyPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.certainPassword.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputCertainMoneyPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![self.certainPassword.text isEqualToString:self.moneyPassword.text]) {
        [self.view makeToast:LocalizationKey(@"checkPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
//    if (![ToolUtil matchPassword:self.moneyPassword.text]) {
//        [self.view makeToast:@"请正确输入6-20位资金密码" duration:1.5 position:CSToastPositionCenter];
//        return;
//    }
    [EasyShowLodingView showLodingText:LocalizationKey(@"hardUpLoading")];
    [MineNetManager moneyPasswordForJyPassword:self.moneyPassword.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];

        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
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
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
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
