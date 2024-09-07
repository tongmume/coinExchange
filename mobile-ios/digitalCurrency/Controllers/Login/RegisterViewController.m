//
//  RegisterViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/29.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "RegisterViewController.h"
#import "countryViewController.h"
#import "LoginNetManager.h"
#import "ImageBtn.h"
#import "countryModel.h"
#import <GT3Captcha/GT3Captcha.h>//极验验证
#import "CustomButton.h"
#import "PlatformMessageDetailViewController.h"
#import "MineNetManager.h"

#define api_2 [NSString stringWithFormat:@"%@%@",HOST,@"cn/uc/mobile/code"]

@interface RegisterViewController ()<CaptchaButtonDelegate>
{
     BOOL _isEmail;//邮箱注册
    NSDictionary *_g3Result;
}
@property (weak, nonatomic) IBOutlet UITextField *phoneNumberTF;
@property (weak, nonatomic) IBOutlet UITextField *verifyCodeTF;
@property (weak, nonatomic) IBOutlet UITextField *userNameTF;
@property (weak, nonatomic) IBOutlet UITextField *passwordTF;
@property (weak, nonatomic) IBOutlet CustomButton *verifyButton;

@property (weak, nonatomic) IBOutlet UIView *lineView;

@property (weak, nonatomic) IBOutlet CustomButton *registerBtn;
@property (weak, nonatomic) IBOutlet UIButton *agressButton;//同意协议按钮
@property (weak, nonatomic) IBOutlet UIButton *AgreementButton;//查看协议
@property (weak, nonatomic) IBOutlet UITextField *ConfirmpasswordTF;//确认密码
@property (weak, nonatomic) IBOutlet UILabel *phonelabel;
@property (weak, nonatomic) IBOutlet UILabel *usernamelabel;
@property (weak, nonatomic) IBOutlet UILabel *verifycodelabel;

@property (weak, nonatomic) IBOutlet UILabel *passwordlabel;
@property (weak, nonatomic) IBOutlet UILabel *confirmlabel;
@property (weak, nonatomic) IBOutlet UIButton *gotologinbut;
@property (weak, nonatomic) IBOutlet UILabel *haveAccountnumber;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *tfHeight;

@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomheight;
@property (nonatomic,assign)BOOL isAgree;

@property (weak, nonatomic) IBOutlet UIButton *eyebutton1;
@property (weak, nonatomic) IBOutlet UIButton *eyebutton2;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *email_top;

//用户协议
@property (nonatomic,copy  ) NSString *contentstr;
@property (nonatomic,copy  ) NSString *contryCode;
@property (nonatomic,strong) UILabel  *contryLabel;
@property (nonatomic,strong) UIImageView  *contryimageView;
@end

@implementation RegisterViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationItem.title=LocalizationKey(@"register");
    self.isAgree = NO;
    [self getUserprotocol];
    self.contryCode = @"日本";
    [self rightBarItemWithTitle:LocalizationKey(@"emailRegister")];

    Ivar ivar =  class_getInstanceVariable([UITextField class], "_placeholderLabel");
    UILabel *phoneNumberPlaceholderLabel = object_getIvar(_phoneNumberTF, ivar);
    phoneNumberPlaceholderLabel.textColor = AppTextColor_Level_2;
    phoneNumberPlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
    
    UILabel *verifyCodePlaceholderLabel = object_getIvar(_verifyCodeTF, ivar);
    verifyCodePlaceholderLabel.textColor = AppTextColor_Level_2;
    verifyCodePlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
    UILabel *userNamePlaceholderLabel = object_getIvar(_userNameTF, ivar);
    userNamePlaceholderLabel.textColor = AppTextColor_Level_2;
    userNamePlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
    UILabel *passwordPlaceholderLabel = object_getIvar(_passwordTF, ivar);
    passwordPlaceholderLabel.textColor = AppTextColor_Level_2;
    passwordPlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
    UILabel *passwordConfirmPlaceholderLabel = object_getIvar(_ConfirmpasswordTF, ivar);
    passwordConfirmPlaceholderLabel.textColor = AppTextColor_Level_2;
    passwordConfirmPlaceholderLabel.font = [UIFont boldSystemFontOfSize:12];
    
//    [_phoneNumberTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_verifyCodeTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_userNameTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_passwordTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_ConfirmpasswordTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//
//    [_phoneNumberTF setValue:[UIFont boldSystemFontOfSize:12] forKeyPath:@"_placeholderLabel.font"];
//    [_verifyCodeTF setValue:[UIFont boldSystemFontOfSize:12] forKeyPath:@"_placeholderLabel.font"];
//    [_userNameTF setValue:[UIFont boldSystemFontOfSize:12] forKeyPath:@"_placeholderLabel.font"];
//    [_passwordTF setValue:[UIFont boldSystemFontOfSize:12] forKeyPath:@"_placeholderLabel.font"];
//    [_ConfirmpasswordTF setValue:[UIFont boldSystemFontOfSize:12] forKeyPath:@"_placeholderLabel.font"];


//    UITapGestureRecognizer *tapRecognizerWeibo=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(selectCountry)];
//    [self.boardView addGestureRecognizer:tapRecognizerWeibo];
    self.tfHeight.constant = 30 * kWindowWHOne;
    if (kWindowW == 320) {
        self.bottomheight.constant = 10;
    }else{
        self.bottomheight.constant = SafeAreaBottomHeight + 20 * kWindowWHOne;

    }
    [self.verifyButton setOriginaStyle];
    self.verifyButton.delegate = self;
    [self.verifyButton setTitle:LocalizationKey(@"sendValidate") forState:UIControlStateNormal];

    [self.registerBtn setOriginaStyle];
    self.registerBtn.delegate=self;
    [self.registerBtn setTitle:LocalizationKey(@"register") forState:UIControlStateNormal];
    self.phoneNumberTF.placeholder = LocalizationKey(@"inputMobile");
    self.verifyCodeTF.placeholder = LocalizationKey(@"inputPhoneCode");
    self.userNameTF.placeholder = LocalizationKey(@"inputUsername");
    self.passwordTF.placeholder = LocalizationKey(@"inputPwd");
    self.ConfirmpasswordTF.placeholder = LocalizationKey(@"PcertainPassword");

    [self.agressButton setTitle:LocalizationKey(@"readandagree") forState:UIControlStateNormal];
    [self.AgreementButton setTitle:LocalizationKey(@"Userprotocol") forState:UIControlStateNormal];
    self.usernamelabel.text = LocalizationKey(@"username");
    self.phonelabel.text = LocalizationKey(@"phoneNum");
    self.verifycodelabel.text = LocalizationKey(@"verifyCode");
    self.passwordlabel.text = LocalizationKey(@"pwd");
    self.confirmlabel.text = LocalizationKey(@"certainPassword");
    self.haveAccountnumber.text = LocalizationKey(@"Existingaccounts");
    [self.gotologinbut setTitle:LocalizationKey(@"Loginimmediately") forState:UIControlStateNormal];

    self.registerBtn.layer.masksToBounds = YES;
    self.registerBtn.layer.cornerRadius = kWindowW * 0.7 * 46 / 256 / 2;
    
    UIView *leftView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 60, 45)];

    UILabel *leftLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 30, 45)];
    leftLabel.text = @"+ 81";
    leftLabel.textColor = UIColor.whiteColor;
    leftLabel.font = [UIFont systemFontOfSize:14];
    [leftView addSubview:leftLabel];
    self.contryLabel = leftLabel;

    UIImageView *imageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"icon_up"]];
    imageView.frame = CGRectMake(CGRectGetMaxX(leftLabel.frame),16, 13, 13);
    [leftView addSubview:imageView];
    self.contryimageView = imageView;
    
    UITapGestureRecognizer *tapRecognizerWeibo=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(selectCountry)];
    [leftView addGestureRecognizer:tapRecognizerWeibo];
    
    self.phoneNumberTF.leftView = leftView;
    self.phoneNumberTF.leftViewMode = UITextFieldViewModeAlways;

}

//立即登录
- (IBAction)LoginImmediatelyaction:(id)sender {
    [self.navigationController popViewControllerAnimated:YES];
}

//阅读并同意
- (IBAction)Agreeaction:(id)sender {
    self.agressButton.selected = !self.agressButton.selected;
}

- (IBAction)openeyeTF1:(id)sender {
    self.eyebutton1.selected = !self.eyebutton1.selected;
    self.passwordTF.secureTextEntry = !self.eyebutton1.selected;
}
- (IBAction)openeyeTF2:(id)sender {
    self.eyebutton2.selected = !self.eyebutton2.selected;
    self.ConfirmpasswordTF.secureTextEntry = !self.eyebutton2.selected;
}

//用户协议
- (IBAction)Agreementaction:(id)sender {

    PlatformMessageDetailViewController *detailVC = [[PlatformMessageDetailViewController alloc] init];
    detailVC.content = self.contentstr;
    detailVC.navtitle = LocalizationKey(@"Userprotocol");
    detailVC.ID = @"5";
    [self.navigationController pushViewController:detailVC animated:YES];
}


//获取用户协议
-(void)getUserprotocol{

    [MineNetManager Userprotocol:@{@"id":@"17"} CompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {

                self.contentstr = resPonseObj[@"data"][@"content"];
            }else{
                [UIApplication.sharedApplication.keyWindow makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [UIApplication.sharedApplication.keyWindow makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:YES];
    [self setNavigationControllerStyle];
}

- (void)selectCountry{
    __weak RegisterViewController*weakSelf=self;
    countryViewController *countryVC = [[countryViewController alloc]init];
    countryVC.hidesBottomBarWhenPushed = YES;
    countryVC.returnValueBlock = ^(countryModel *model){
        weakSelf.contryCode = model.zhName;
        weakSelf.contryLabel.text = [NSString stringWithFormat:@"+%@",model.areaCode];
        [weakSelf.contryLabel sizeToFit];
        weakSelf.contryLabel.frame = CGRectMake(0,0, weakSelf.contryLabel.frame.size.width + 3, 45);
        weakSelf.contryimageView.frame = CGRectMake(CGRectGetMaxX(weakSelf.contryLabel.frame),16, 13, 13);
    };
    [self.navigationController pushViewController:countryVC animated:YES];
}
#pragma mark-切换邮箱注册Or手机号码注册
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
     //[UIView setAnimationDidStopSelector:@selector(finished)];
    //设置翻转方向
    [UIView setAnimationTransition:
     UIViewAnimationTransitionFlipFromLeft forView:self.view cache:YES];
    [UIView commitAnimations];
}

-(void)finished{
    
    _isEmail=!_isEmail;
    if (_isEmail) {
//        self.verifycodelabel.hidden = YES;
//        self.verifyCodeTF.hidden=YES;
//        self.verifyButton.hidden=YES;
        self.lineView.hidden=YES;
        self.phoneNumberTF.leftViewMode = UITextFieldViewModeNever;
//        self.email_top.constant = -51;
    }else{
//        self.email_top.constant = 10;
//        self.verifycodelabel.hidden = NO;
//        self.verifyCodeTF.hidden=NO;
//        self.verifyButton.hidden=NO;
        self.lineView.hidden=NO;
        self.phoneNumberTF.leftViewMode = UITextFieldViewModeAlways;
    }
    _phoneNumberTF.text=@"";
    _verifyCodeTF.text=@"";
    _userNameTF.text=@"";
    _passwordTF.text=@"";
    NSString * mobileRegister = LocalizationKey(@"moblieRegister");
    NSString * emailRegister = LocalizationKey(@"emailRegister");
    self.navigationItem.title=_isEmail==YES?emailRegister:mobileRegister;
    self.navigationItem.rightBarButtonItem.title=_isEmail==YES?mobileRegister:emailRegister;
    _phoneNumberTF.placeholder=_isEmail==YES?LocalizationKey(@"email"):LocalizationKey(@"phoneNum");
    [_phoneNumberTF resignFirstResponder];
    _phonelabel.text =  _isEmail==YES? LocalizationKey(@"email_lab") :LocalizationKey(@"phone_lab");
    _verifyCodeTF.placeholder = _isEmail == YES? LocalizationKey(@"inputEmailValidate"): LocalizationKey(@"inputPhoneCode");
    [_phoneNumberTF resignFirstResponder];
    _phoneNumberTF.keyboardType=_isEmail==YES?UIKeyboardTypeNumbersAndPunctuation:UIKeyboardTypeNumberPad;
}

#pragma mark - CaptchaButtonDelegate

- (BOOL)captchaButtonShouldBeginTapAction:(CustomButton *)button {

    if (button.tag==100) {/*发送验证码按钮*/

        if (![NSString stringIsNull:self.phoneNumberTF.text]) {
            if (_isEmail && ![ToolUtil matchEmail:self.phoneNumberTF.text]) {
                [self.view makeToast:LocalizationKey(@"inputEmailValidateRight") duration:1.5 position:CSToastPositionCenter];
                return NO;
            } else {
                [self closeSwipeTap];
            }
            return NO;
        }else {
            if(_isEmail) {
                [self.view makeToast:LocalizationKey(@"inputEmail") duration:1.5 position:CSToastPositionCenter];
            }else {
                [self.view makeToast:LocalizationKey(@"inputMobile") duration:1.5 position:CSToastPositionCenter];
            }
        }
        return NO;
    }else{
        [self goRegister];
        return NO;
    }
}

- (void)closeSwipeTap {
    dispatch_async(dispatch_get_main_queue(), ^{
        [EasyShowLodingView showLodingText:@"" inView:APPLICATION.window];
    });

//        _g3Result = result;
        __block NSMutableString *postResult = [[NSMutableString alloc] init];
//        [result enumerateKeysAndObjectsUsingBlock:^(id key, id obj, BOOL * stop) {
//            [postResult appendFormat:@"%@=%@&",key,obj];
//        }];
    if (_isEmail) {
        [postResult appendFormat:@"%@", [NSString stringWithFormat:@"email=%@&country=%@",self.phoneNumberTF.text,@"日本"]];
    }else {
        [postResult appendFormat:@"%@", [NSString stringWithFormat:@"phone=%@&country=%@",self.phoneNumberTF.text,self.contryCode]];
        
    }
        NSDictionary *headerFields = @{@"Content-Type":@"application/x-www-form-urlencoded;charset=UTF-8"};

        NSMutableURLRequest *secondaryRequest;
        if (_isEmail) {
            secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString: [NSString stringWithFormat:@"%@%@",HOST,@"uc/reg/email/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
        }else{
            secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString: [NSString stringWithFormat:@"%@%@",HOST,@"uc/mobile/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
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
//            [manager closeGTViewIfIsOpen];
            NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)response;
            if (!error && httpResponse.statusCode == 200) {
                NSError *err;
                NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&err];
                if (!err) {
                    if ([dict[@"code"] integerValue] == 0) {
                        //发送短信验证码成功
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

//获取手机验证码
//-(void)getphonncode{
//    [self.view endEditing:YES];
//    // 加载腾讯验证码
//    [[TCWebCodesBridge sharedBridge] loadTencentCaptcha:self.view appid:@"2040846200" callback:^(NSDictionary *resultJSON) { // appid在验证码接入平台注册申请，
//        if(0==[resultJSON[@"ret"] intValue]) {
//            /**
//             验证成功
//             返回内容：
//             resultJSON[@"appid"]为回传的业务appid
//             resultJSON[@"ticket"]为验证码票据
//             resultJSON[@"randstr"]为随机串
//             */
//            _g3Result = resultJSON;
//            __block NSMutableString *postResult = [[NSMutableString alloc] init];
//
//            [postResult appendFormat:@"%@", [NSString stringWithFormat:@"phone=%@&country=%@",self.phoneNumberTF.text,LocalizationKey(@"china")]];
//            NSDictionary *headerFields = @{@"Content-Type":@"application/x-www-form-urlencoded;charset=UTF-8"};
//            NSMutableURLRequest *secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString: [NSString stringWithFormat:@"%@%@",HOST,@"uc/mobile/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
//            secondaryRequest.HTTPMethod = @"POST";
//            secondaryRequest.allHTTPHeaderFields = headerFields;
//            secondaryRequest.HTTPBody = [postResult dataUsingEncoding:NSUTF8StringEncoding];
//            NSURLSessionConfiguration *config = [NSURLSessionConfiguration defaultSessionConfiguration];
//            NSURLSession *session = [NSURLSession sessionWithConfiguration:config];
//            NSURLSessionDataTask *task = [session dataTaskWithRequest:secondaryRequest completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
//                dispatch_async(dispatch_get_main_queue(), ^{
//                    [EasyShowLodingView hidenLoding];
//                });
//                NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)response;
//                if (!error && httpResponse.statusCode == 200) {
//                    NSError *err;
//                    NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&err];
//                    if (!err) {
//                        if ([dict[@"code"] integerValue] == 0) {
//                            //发送短信验证码成功
//                            dispatch_async(dispatch_get_main_queue(), ^{
//                                [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
//                            });
//                            __block int timeout=60; //倒计时时间
//                            dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
//                            dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
//                            dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
//                            dispatch_source_set_event_handler(_timer, ^{
//                                if(timeout<=0){ //倒计时结束，关闭
//                                    dispatch_source_cancel(_timer);
//                                    dispatch_async(dispatch_get_main_queue(), ^{
//                                        [self.verifyButton setTitle:LocalizationKey(@"sendValidate") forState:UIControlStateNormal];
//                                        self.verifyButton.userInteractionEnabled = YES;
//                                    });
//                                }else{
//                                    int seconds = timeout % 90;
//                                    NSString *strTime = [NSString stringWithFormat:@"%.2d", seconds];
//                                    dispatch_async(dispatch_get_main_queue(), ^{
//                                        [self.verifyButton setTitle: [NSString stringWithFormat:@"%@s",strTime] forState:UIControlStateNormal];
//                                        self.verifyButton.userInteractionEnabled = NO;
//                                    });
//                                    timeout--;
//                                }
//                            });
//                            dispatch_resume(_timer);
//                        }
//                        else {
//                            dispatch_async(dispatch_get_main_queue(), ^{
//                                [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
//                            });
//
//                        }
//                    }
//                }
//            }];
//
//            [task resume];
//
//        }
//
//
//    }];
//}

#pragma mark-二次验证返回数据（获取验证码）
- (void)delegateGtCaptcha:(GT3CaptchaManager *)manager didReceiveCaptchaCode:(NSString *)code result:(NSDictionary *)result message:(NSString *)message{
    NSLog(@"result ---- %@",result);
    
    if (![code isEqualToString:@"1"]) {
        return;
    }
    
    dispatch_async(dispatch_get_main_queue(), ^{
        [EasyShowLodingView showLodingText:@"" inView:APPLICATION.window];
    });

        _g3Result = result;
        __block NSMutableString *postResult = [[NSMutableString alloc] init];
        [result enumerateKeysAndObjectsUsingBlock:^(id key, id obj, BOOL * stop) {
            [postResult appendFormat:@"%@=%@&",key,obj];
        }];
    if (_isEmail) {
        [postResult appendFormat:@"%@", [NSString stringWithFormat:@"email=%@&country=%@",self.phoneNumberTF.text,@"日本"]];
    }else {
        [postResult appendFormat:@"%@", [NSString stringWithFormat:@"phone=%@&country=%@",self.phoneNumberTF.text,self.contryCode]];
        
    }
        NSDictionary *headerFields = @{@"Content-Type":@"application/x-www-form-urlencoded;charset=UTF-8"};

        NSMutableURLRequest *secondaryRequest;
        if (_isEmail) {
            secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString: [NSString stringWithFormat:@"%@%@",HOST,@"uc/reg/email/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
        }else{
            secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString: [NSString stringWithFormat:@"%@%@",HOST,@"uc/mobile/code"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
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
                        //发送短信验证码成功
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
    
//    else{//邮箱注册
//        __block NSMutableString *postResult = [[NSMutableString alloc] init];
//        [result enumerateKeysAndObjectsUsingBlock:^(id key, id obj, BOOL * stop) {
//            [postResult appendFormat:@"%@=%@&",key,obj];
//        }];
//        [postResult appendFormat:@"%@", [NSString stringWithFormat:@"email=%@&password=%@&username=%@&country=%@&promotion=%@",self.phoneNumberTF.text,self.passwordTF.text,self.userNameTF.text,LocalizationKey(@"china"),self.inviteTF.text]];
//        NSDictionary *headerFields = @{@"Content-Type":@"application/x-www-form-urlencoded;charset=UTF-8"};
//        NSMutableURLRequest *secondaryRequest = [NSMutableURLRequest requestWithURL:[NSURL URLWithString: [NSString stringWithFormat:@"%@%@",HOST,@"uc/register/email"]] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:15.0];
//        secondaryRequest.HTTPMethod = @"POST";
//        secondaryRequest.allHTTPHeaderFields = headerFields;
//        secondaryRequest.HTTPBody = [postResult dataUsingEncoding:NSUTF8StringEncoding];
//        NSURLSessionConfiguration *config = [NSURLSessionConfiguration defaultSessionConfiguration];
//        NSURLSession *session = [NSURLSession sessionWithConfiguration:config];
//        NSURLSessionDataTask *task = [session dataTaskWithRequest:secondaryRequest completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
//            dispatch_async(dispatch_get_main_queue(), ^{
//                [EasyShowLodingView hidenLoding];
//            });
//            [manager closeGTViewIfIsOpen];
//            NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)response;
//            if (!error && httpResponse.statusCode == 200) {
//                NSError *err;
//                NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&err];
//                if (!err) {
//                    if ([dict[@"code"] integerValue] == 0) {
//                        //邮箱注册成功
//                        dispatch_async(dispatch_get_main_queue(), ^{
//                            [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
//                        });
//                        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
//                            [self.navigationController popViewControllerAnimated:YES];
//                        });
//                    }
//                    else {
//                        dispatch_async(dispatch_get_main_queue(), ^{
//                            [APPLICATION.window makeToast:dict[@"message"] duration:1.5 position:CSToastPositionCenter];
//                        });
//
//                    }
//                }
//            }
//        }];
//
//        [task resume];
//    }
}

#pragma mark-手机号码注册

- (void)goRegister{
  
     if (_isEmail) {
         if ([NSString stringIsNull:self.phoneNumberTF.text]) {
             [self.view makeToast:LocalizationKey(@"inputEmail") duration:1.5 position:CSToastPositionCenter];
             return ;
         }
         if (![ToolUtil matchEmail:self.phoneNumberTF.text]) {
             [self.view makeToast:LocalizationKey(@"inputEmailValidateRight") duration:1.5 position:CSToastPositionCenter];
             return;
         }
    }else {
        if ([NSString stringIsNull:self.phoneNumberTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputMobile") duration:1.5 position:CSToastPositionCenter];
            return;
        }
    }
    
        if ([NSString stringIsNull:self.verifyCodeTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputCode") duration:1.5 position:CSToastPositionCenter];
            return;
        }
//        if ([NSString stringIsNull:self.userNameTF.text]) {
//            [self.view makeToast:LocalizationKey(@"inputUsername") duration:1.5 position:CSToastPositionCenter];
//            return;
//        }
        if ([NSString stringIsNull:self.passwordTF.text]) {
            [self.view makeToast:LocalizationKey(@"inputPwd") duration:1.5 position:CSToastPositionCenter];
            return;
        }
       if ([NSString stringIsNull:self.ConfirmpasswordTF.text]) {
            [self.view makeToast:LocalizationKey(@"PcertainPassword") duration:1.5 position:CSToastPositionCenter];
           return;
       }

    if (![self.passwordTF.text isEqualToString:self.ConfirmpasswordTF.text] ) {
        [self.view makeToast:LocalizationKey(@"inputPwdNoSame") duration:1.5 position:CSToastPositionCenter];
        return;
    }

    if (!self.agressButton.selected) {
        [self.view makeToast:LocalizationKey(@"PleaseUserprotocol") duration:1.5 position:CSToastPositionCenter];
        return;
    }

    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    NSMutableDictionary *codeDic = [NSMutableDictionary dictionary];
    [codeDic setValue:_g3Result[@"ticket"] forKey:@"ticket"];
    [codeDic setValue:_g3Result[@"randstr"] forKey:@"randStr"];
    if (_isEmail) {
        [LoginNetManager getAccountForEmail:self.phoneNumberTF.text withpassword:self.passwordTF.text   withcode:self.verifyCodeTF.text withusername:self.userNameTF.text withcountry:@"日本" withpromotion:@"" CompleteHandle:^(id resPonseObj, int code) {
            [EasyShowLodingView hidenLoding];
            if (code) {
                if ([resPonseObj[@"code"] integerValue] == 0) {
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
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
    }else {
        [LoginNetManager getAccountForPhone:self.phoneNumberTF.text withpassword:self.passwordTF.text withcode:self.verifyCodeTF.text withusername:self.userNameTF.text withcountry:self.contryCode withG3Result:codeDic CompleteHandle:^(id resPonseObj, int code) {

            [EasyShowLodingView hidenLoding];
            if (code) {
                if ([resPonseObj[@"code"] integerValue] == 0) {
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
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
}
- (CGFloat)calculateRowWidth:(NSString *)string {
    NSDictionary *dic = @{NSFontAttributeName:[UIFont systemFontOfSize:15]};  //指定字号
    CGRect rect = [string boundingRectWithSize:CGSizeMake(0, 40)/*计算宽度时要确定高度*/ options:NSStringDrawingUsesLineFragmentOrigin |
                   NSStringDrawingUsesFontLeading attributes:dic context:nil];
    return rect.size.width;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:YES];
    [self cancelNavigationControllerStyle];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)dealloc{

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
