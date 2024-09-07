//
//  ChangePhoneDetailViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/19.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChangePhoneDetailViewController.h"
#import "MineNetManager.h"
#import "AccountSettingViewController.h"

@interface ChangePhoneDetailViewController ()
@property (weak, nonatomic) IBOutlet UILabel *tipLabel; //提示语
@property (weak, nonatomic) IBOutlet UIButton *verifyButton;
@property (weak, nonatomic) IBOutlet UITextField *latestPhoneNum;
@property (weak, nonatomic) IBOutlet UITextField *loginPassword;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property (weak, nonatomic) IBOutlet UITextField *codeTextField;//短信验证码
// 国际化需要
@property (weak, nonatomic) IBOutlet UILabel *messageCode;
@property (weak, nonatomic) IBOutlet UILabel *latestPhoneNumLabel;
@property (weak, nonatomic) IBOutlet UILabel *loginPasswordLabel;
@property (weak, nonatomic) IBOutlet UIButton *submitButton;

@end

@implementation ChangePhoneDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = kRGBColor(18, 22, 28);
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"changeBindPhoneNum" value:nil table:@"English"];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
     NSString *backStr = [self.phoneNum substringFromIndex:self.phoneNum.length- 4 ];
    self.tipLabel.text = [NSString stringWithFormat:@"%@%@%@",[[ChangeLanguage bundle] localizedStringForKey:@"handsetTailNumber" value:nil table:@"English"],backStr,[[ChangeLanguage bundle] localizedStringForKey:@"receiveMessageCode" value:nil table:@"English"]];
    self.messageCode.text =[[ChangeLanguage bundle] localizedStringForKey:@"messageCode" value:nil table:@"English"];
    self.latestPhoneNumLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"newPhoneNum" value:nil table:@"English"];
    self.loginPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"loginPassword" value:nil table:@"English"];
     [_verifyButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];
     [self.submitButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"changeBindPhoneNum" value:nil table:@"English"] forState:UIControlStateNormal];

    self.codeTextField.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputCode" value:nil table:@"English"];
    self.latestPhoneNum.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputNewPhoneNum" value:nil table:@"English"];
    self.loginPassword.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputLoginPassword" value:nil table:@"English"];

//    [_codeTextField setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_latestPhoneNum setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_loginPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];

    // Do any additional setup after loading the view from its nib.
}
//按钮的点击事件
- (IBAction)btnBlick:(UIButton *)sender {
    if (sender.tag == 1) {
        //发送验证码

        [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"hardLoading" value:nil table:@"English"]];
        [MineNetManager changePhoneNumCodeForCompleteHandle:^(id resPonseObj, int code) {
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
                                [_verifyButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];
                                _verifyButton.userInteractionEnabled = YES;
                            });
                        }else{
                            int seconds = timeout % 90;
                            NSString *strTime = [NSString stringWithFormat:@"%.2d", seconds];
                            dispatch_async(dispatch_get_main_queue(), ^{
                                [_verifyButton setTitle: [NSString stringWithFormat:@"%@s",strTime] forState:UIControlStateNormal];
                                _verifyButton.userInteractionEnabled = NO;
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
    }else if (sender.tag == 2){
        //修改绑定手机号
        [self changeBindingPhone];
    }
}
//MARK:--修改绑定手机号
-(void)changeBindingPhone{

    if ([self.codeTextField.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputMessageCode" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.latestPhoneNum.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputNewPhoneNum" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.loginPassword.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputLoginPassword" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager changePhoneNumForPassword:self.loginPassword.text withPhone:self.latestPhoneNum.text withCode:self.codeTextField.text CompleteHandle:^(id resPonseObj, int code) {
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
