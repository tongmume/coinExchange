//
//  BindingEmailViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BindingEmailViewController.h"
#import "MineNetManager.h"

@interface BindingEmailViewController ()
@property (weak, nonatomic) IBOutlet UITextField *emailAccount;
@property (weak, nonatomic) IBOutlet UITextField *emailCode;
@property (weak, nonatomic) IBOutlet UITextField *loginPassword;
@property (weak, nonatomic) IBOutlet UIButton *verifyButton;

@property (weak, nonatomic) IBOutlet NSLayoutConstraint *certainBindingViewHeight;//绑定邮箱
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *cancelBindingViewHeight; //解绑邮箱
@property (weak, nonatomic) IBOutlet UIView *certainBindingView;
@property (weak, nonatomic) IBOutlet UIView *cancelBindingView;
@property (weak, nonatomic) IBOutlet UILabel *emailNum;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *emailAccountLabel;//邮箱账号
@property (weak, nonatomic) IBOutlet UILabel *emailCodeLabel;//邮箱验证码
@property (weak, nonatomic) IBOutlet UILabel *loginPasswordLabel; //登录密码
@property (weak, nonatomic) IBOutlet UILabel *emailLabel;//邮箱


@end

@implementation BindingEmailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    self.emailAccountLabel.font =  self.emailCodeLabel.font = self.loginPasswordLabel.font = self.emailLabel.font = self.emailNum.font = [UIFont systemFontOfSize:15 * kWindowWHOne];
    self.emailAccount.font = self.emailCode.font = self.loginPassword.font = [UIFont systemFontOfSize:15 * kWindowWHOne];

    if (self.bindingStatus == 1) {
        //绑定状态，需要解绑
        self.title = [[ChangeLanguage bundle] localizedStringForKey:@"unBundlingEmail" value:nil table:@"English"];
        self.certainBindingViewHeight.constant = 0;
        self.certainBindingView.hidden = YES;
        self.emailNum.text = self.emailStr;
    }else{
        self.title = [[ChangeLanguage bundle] localizedStringForKey:@"bindingEmail" value:nil table:@"English"];
        self.cancelBindingViewHeight.constant = 0;
        self.cancelBindingView.hidden = YES;
        [self setRightItem];
    }
    self.emailAccountLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"emailAccount" value:nil table:@"English"];
    self.emailCodeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"emailCode" value:nil table:@"English"];
    self.loginPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"loginPassword" value:nil table:@"English"];
    self.emailLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"email" value:nil table:@"English"];
    [self.verifyButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];

     self.emailAccount.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputEmailAccount" value:nil table:@"English"];
    self.emailCode.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputMessageCode" value:nil table:@"English"];
    self.loginPassword.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputLoginPassword" value:nil table:@"English"];

//    [_emailAccount setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_emailCode setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_loginPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
}


//MARK:---获取验证码的点击事件
- (IBAction)getEmailCodeBtnClick:(UIButton *)sender {

    if ([self.emailAccount.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputEmailAccount") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![ToolUtil matchEmail:self.emailAccount.text]) {
        [self.view makeToast:LocalizationKey(@"inputEmailValidateRight") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:LocalizationKey(@"hardLoading")];
    [MineNetManager bindingEmailCodeForEmail:self.emailAccount.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:LocalizationKey(@"getEmailCodeTip") duration:1.5 position:CSToastPositionCenter];
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
    if (self.bindingStatus == 0) {
        //绑定
        [self certainBinding];
    }else{
        //解绑
    }
}
//MARK:--绑定邮箱接口调用
-(void)certainBinding{
    if ([self.emailAccount.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputEmailAccount") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![ToolUtil matchEmail:self.emailAccount.text]) {
        [self.view makeToast:LocalizationKey(@"inputEmailValidateRight") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.emailCode.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputEmailValidate") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.loginPassword.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputLoginPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:LocalizationKey(@"hardUpLoading")];
    [MineNetManager bindingEmailForCode:self.emailCode.text withPassword:self.loginPassword.text withEmail:self.emailAccount.text CompleteHandle:^(id resPonseObj, int code) {
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
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}


@end
