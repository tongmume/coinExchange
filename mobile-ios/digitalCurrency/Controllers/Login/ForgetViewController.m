//
//  ForgetViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ForgetViewController.h"
#import "LoginNetManager.h"
#import "CustomButton.h"
@interface ForgetViewController ()<CaptchaButtonDelegate>
{
    BOOL _isEmail;//邮箱认证
}
@property (weak, nonatomic) IBOutlet UITextField *phoneNumberTF;
@property (weak, nonatomic) IBOutlet UITextField *verifyCodeTF;
@property (weak, nonatomic) IBOutlet UITextField *passWordTF;
@property (weak, nonatomic) IBOutlet UITextField *confirmTF;
@property (weak, nonatomic) IBOutlet CustomButton *verifyButton;
@property (weak, nonatomic) IBOutlet UIButton *okBtn;

@property (weak, nonatomic) IBOutlet UILabel *phonelabel;

@property (weak, nonatomic) IBOutlet UILabel *verifylabel;
@property (weak, nonatomic) IBOutlet UILabel *newpasswordlabel;
@property (weak, nonatomic) IBOutlet UILabel *confirflabel;
@property (weak, nonatomic) IBOutlet UIButton *eyebutton1;
@property (weak, nonatomic) IBOutlet UIButton *eyebutton2;
@end

@implementation ForgetViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationItem.title=LocalizationKey(@"forgetPassword");
    [self rightBarItemWithTitle:LocalizationKey(@"emailValidate")];
    Ivar ivar =  class_getInstanceVariable([UITextField class], "_placeholderLabel");
    UILabel *phoneNumberPlaceholderLabel = object_getIvar(_phoneNumberTF, ivar);
    phoneNumberPlaceholderLabel.textColor = AppTextColor_Level_2;
    phoneNumberPlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
    
    UILabel *verifyCodePlaceholderLabel = object_getIvar(_verifyCodeTF, ivar);
    verifyCodePlaceholderLabel.textColor = AppTextColor_Level_2;
    verifyCodePlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
    UILabel *passwordPlaceholderLabel = object_getIvar(_passWordTF, ivar);
    passwordPlaceholderLabel.textColor = AppTextColor_Level_2;
    passwordPlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
    UILabel *passwordConfirmPlaceholderLabel = object_getIvar(_confirmTF, ivar);
    passwordConfirmPlaceholderLabel.textColor = AppTextColor_Level_2;
    passwordConfirmPlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];


    [self.verifyButton setOriginaStyle];
    [self.verifyButton setTitle:LocalizationKey(@"sendValidate") forState:UIControlStateNormal];
     self.verifyButton.delegate = self;
    [self.okBtn setTitle:LocalizationKey(@"save") forState:UIControlStateNormal];
    self.phoneNumberTF.placeholder = LocalizationKey(@"inputMobile");
    self.verifyCodeTF.placeholder = LocalizationKey(@"inputPhoneCode");
    self.passWordTF.placeholder = LocalizationKey(@"inputnewPwd");
    self.confirmTF.placeholder = [NSString stringWithFormat:@"%@",LocalizationKey(@"PcertainPassword")];

    self.phonelabel.text = LocalizationKey(@"phoneNum");
    self.verifylabel.text = LocalizationKey(@"verifyCode");
    self.newpasswordlabel.text = LocalizationKey(@"newpassword");
    self.confirflabel.text = LocalizationKey(@"certainPassword");

    self.okBtn.layer.masksToBounds = YES;
    self.okBtn.layer.cornerRadius = kWindowW * 0.7 * 46 / 256 / 2;
}
#pragma mark-切换邮箱认证Or手机号码认证
-(void)RighttouchEvent{
    [self finished];
    //开始动画
    [UIView beginAnimations:@"doflip" context:nil];
    //设置时常
    [UIView setAnimationDuration:0.5];
    //设置动画淡入淡出
    [UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
    //设置代理
    [UIView setAnimationDelegate:self];
    //动画结束
   // [UIView setAnimationDidStopSelector:@selector(finished)];
    //设置翻转方向
    [UIView setAnimationTransition:
     UIViewAnimationTransitionFlipFromLeft forView:self.view cache:YES];
    [UIView commitAnimations];

}
- (IBAction)openTf1:(id)sender {
    self.eyebutton1.selected = !self.eyebutton1.selected;
    self.passWordTF.secureTextEntry = !self.eyebutton1.selected;
}
- (IBAction)openTF2:(id)sender {
    self.eyebutton2.selected = !self.eyebutton2.selected;
    self.confirmTF.secureTextEntry = !self.eyebutton2.selected;
}
-(void)finished{
    _isEmail=!_isEmail;
    _phoneNumberTF.text=@"";
    _verifyCodeTF.text=@"";
    _passWordTF.text=@"";
    _confirmTF.text=@"";
    self.navigationItem.title=_isEmail==YES?LocalizationKey(@"emailValidate"):LocalizationKey(@"moblieValidate");
    self.navigationItem.rightBarButtonItem.title=_isEmail==YES?LocalizationKey(@"moblieValidate"):LocalizationKey(@"emailValidate");
    _phonelabel.text =  _isEmail==YES? LocalizationKey(@"email_lab") :LocalizationKey(@"phone_lab");
    _phoneNumberTF.placeholder=_isEmail==YES?LocalizationKey(LocalizationKey(@"email")):LocalizationKey(@"phoneNum");
    _verifyCodeTF.placeholder=_isEmail==YES?LocalizationKey(@"emailCode"):LocalizationKey(@"phoneValidate");
    [_phoneNumberTF resignFirstResponder];
    _phoneNumberTF.keyboardType=_isEmail==YES?UIKeyboardTypeNumbersAndPunctuation:UIKeyboardTypeNumberPad;
}
#pragma mark - CaptchaButtonDelegate

- (BOOL)captchaButtonShouldBeginTapAction:(CustomButton *)button {

    if (![NSString stringIsNull:self.phoneNumberTF.text]) {
        if (![ToolUtil matchEmail:self.phoneNumberTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputEmailValidateRight") duration:1.5 position:CSToastPositionCenter];
            return NO;
        }
        return NO;
    }
    else {
        if (_isEmail) {
             [self.view makeToast:LocalizationKey(@"inputEmail") duration:1.5 position:CSToastPositionCenter];
        }else{
            [self.view makeToast:LocalizationKey(@"inputMobile") duration:1.5 position:CSToastPositionCenter];
        }
    }
    return NO;
}

- (void)closeSwipeTap {
    dispatch_async(dispatch_get_main_queue(), ^{
        [EasyShowLodingView showLodingText:@"" inView:APPLICATION.window];
    });
    __block NSMutableString *postResult = [[NSMutableString alloc] init];
    [postResult appendFormat:@"%@", [NSString stringWithFormat:@"account=%@",self.phoneNumberTF.text]];
    NSDictionary *headerFields = @{@"Content-Type":@"application/x-www-form-urlencoded;charset=UTF-8"};
    NSMutableURLRequest *secondaryRequest;
    if (_isEmail) {
          secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@",HOST,@"uc/reset/email/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
    }else{
          secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@",HOST,@"uc/mobile/reset/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
    }
    secondaryRequest.HTTPMethod = @"POST";
    secondaryRequest.allHTTPHeaderFields = headerFields;
    NSString*langString= [ChangeLanguage networkLanguage];
    [secondaryRequest addValue: langString forHTTPHeaderField:@"lang"];
    secondaryRequest.HTTPBody = [postResult dataUsingEncoding:NSUTF8StringEncoding];
    NSURLSessionConfiguration *config = [NSURLSessionConfiguration defaultSessionConfiguration];
    NSURLSession *session = [NSURLSession sessionWithConfiguration:config];
    NSURLSessionDataTask *task = [session dataTaskWithRequest:secondaryRequest completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        dispatch_async(dispatch_get_main_queue(), ^{
            [EasyShowLodingView hidenLoding];
        });
//        [manager closeGTViewIfIsOpen];
        NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)response;
        if (!error && httpResponse.statusCode == 200) {
            NSError *err;
            NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&err];
            if (!err) {
                if ([dict[@"code"] integerValue] == 0) {
                   //发送验证码成功
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
                    });
                    __block int timeout=60; //倒计时时间
                    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
                    dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
                    dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
                    dispatch_source_set_event_handler(_timer, ^{
                        if(timeout<=0){ //倒计时结束，关闭
                            dispatch_source_cancel(_timer);
                            dispatch_async(dispatch_get_main_queue(), ^{
                                [self.verifyButton setTitle:LocalizationKey(@"sendValidate") forState:UIControlStateNormal];
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

                }
                else {

                    dispatch_async(dispatch_get_main_queue(), ^{
                        [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
                    });

                }
            }
        }
    }];

    [task resume];
}
#pragma mark-二次验证返回数据（获取验证码）
- (void)delegateGtCaptcha:(GT3CaptchaManager *)manager didReceiveCaptchaCode:(NSString *)code result:(NSDictionary *)result message:(NSString *)message{
    if (![code isEqualToString:@"1"]) {
        return;
    }
    dispatch_async(dispatch_get_main_queue(), ^{
        [EasyShowLodingView showLodingText:@"" inView:APPLICATION.window];
    });
    __block NSMutableString *postResult = [[NSMutableString alloc] init];
    [result enumerateKeysAndObjectsUsingBlock:^(id key, id obj, BOOL * stop) {
        [postResult appendFormat:@"%@=%@&",key,obj];
    }];
    [postResult appendFormat:@"%@", [NSString stringWithFormat:@"account=%@",self.phoneNumberTF.text]];
    NSDictionary *headerFields = @{@"Content-Type":@"application/x-www-form-urlencoded;charset=UTF-8"};
    NSMutableURLRequest *secondaryRequest;
    if (_isEmail) {
          secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@",HOST,@"uc/reset/email/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
    }else{
          secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@",HOST,@"uc/mobile/reset/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
    }
    secondaryRequest.HTTPMethod = @"POST";
    secondaryRequest.allHTTPHeaderFields = headerFields;
    NSString*langString= [ChangeLanguage networkLanguage];
    [secondaryRequest addValue: langString forHTTPHeaderField:@"lang"];
    secondaryRequest.HTTPBody = [postResult dataUsingEncoding:NSUTF8StringEncoding];
    NSURLSessionConfiguration *config = [NSURLSessionConfiguration defaultSessionConfiguration];
    NSURLSession *session = [NSURLSession sessionWithConfiguration:config];
    NSURLSessionDataTask *task = [session dataTaskWithRequest:secondaryRequest completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        dispatch_async(dispatch_get_main_queue(), ^{
            [EasyShowLodingView hidenLoding];
        });
        [manager closeGTViewIfIsOpen];
        NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)response;
        if (!error && httpResponse.statusCode == 200) {
            NSError *err;
            NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&err];
            if (!err) {
                if ([dict[@"code"] integerValue] == 0) {
                   //发送验证码成功
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
                    });
                    __block int timeout=60; //倒计时时间
                    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
                    dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
                    dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
                    dispatch_source_set_event_handler(_timer, ^{
                        if(timeout<=0){ //倒计时结束，关闭
                            dispatch_source_cancel(_timer);
                            dispatch_async(dispatch_get_main_queue(), ^{
                                [self.verifyButton setTitle:LocalizationKey(@"sendValidate") forState:UIControlStateNormal];
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

                }
                else {

                    dispatch_async(dispatch_get_main_queue(), ^{
                        [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
                    });

                }
            }
        }
    }];

    [task resume];

}

#pragma mark-确定
- (IBAction)confirm:(UIButton *)sender {
    if (_isEmail) {
        if ([NSString stringIsNull:self.phoneNumberTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputEmail") duration:1.5 position:CSToastPositionCenter];
            return;
        }
        if ([NSString stringIsNull:self.verifyCodeTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputEmailValidate") duration:1.5 position:CSToastPositionCenter];
            return;
        }

    }else{
        if ([NSString stringIsNull:self.phoneNumberTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputMobile") duration:1.5 position:CSToastPositionCenter];
            return;
        }
        if ([NSString stringIsNull:self.verifyCodeTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputMessageCode") duration:1.5 position:CSToastPositionCenter];
            return;
        }
    }
    if ([NSString stringIsNull:self.passWordTF.text]) {
        [self.view makeToast:LocalizationKey(@"inputPwd") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([NSString stringIsNull:self.confirmTF.text]) {
        [self.view makeToast:LocalizationKey(@"inputAgainPwd") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![self.passWordTF.text isEqualToString:self.confirmTF.text]) {
        [self.view makeToast:LocalizationKey(@"inputPwdNoSame") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    int mode=_isEmail==YES?1:0;
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [LoginNetManager FogetToResetPasswordWithaccount:self.phoneNumberTF.text withcode:self.verifyCodeTF.text withMode:mode Withpassword:self.passWordTF.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:@"修改成功" duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    [self.navigationController popViewControllerAnimated:YES];
                });
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
