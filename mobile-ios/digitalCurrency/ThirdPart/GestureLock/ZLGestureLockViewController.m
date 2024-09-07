//
//  ZLGestureLockViewController.m
//  GestureLockDemo
//
//  Created by ZL on 2019/4/5.
//  Copyright © 2019年 ZL. All rights reserved.
//

#import "ZLGestureLockViewController.h"
#import "ZLGestureLockView.h"
#import "ZLGestureLockIndicator.h"
#import "EasyShowView.h"
#import "EasyShowOptions.h"
#import "YLNavigationController.h"
#import "LoginNetManager.h"
#define GesturesPassword @"gesturespassword"

@interface ZLGestureLockViewController () <ZLGestureLockDelegate, UIAlertViewDelegate>

@property (strong, nonatomic) ZLGestureLockView *gestureLockView;
@property (strong, nonatomic) ZLGestureLockIndicator *gestureLockIndicator;

// 手势状态栏提示label
@property (weak, nonatomic) UILabel *statusLabel;

// 账户名
@property (weak, nonatomic) UILabel *nameLabel;
// 账户头像
@property (weak, nonatomic) UIImageView *headIcon;

// 其他账户登录按钮
@property (weak, nonatomic) UIButton *otherAcountBtn;
// 重新绘制按钮
@property (weak, nonatomic) UIButton *resetPswBtn;
// 忘记手势密码按钮
@property (weak, nonatomic) UIButton *forgetPswBtn;

// 创建的手势密码
@property (nonatomic, copy) NSString *lastGesturePsw;

@property (nonatomic) ZLUnlockType unlockType;

@end

@implementation ZLGestureLockViewController

#pragma mark - 类方法

+ (void)deleteGesturesPassword {
    [[NSUserDefaults standardUserDefaults] removeObjectForKey:GesturesPassword];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (void)addGesturesPassword:(NSString *)gesturesPassword {
    [[NSUserDefaults standardUserDefaults] setObject:gesturesPassword forKey:GesturesPassword];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (NSString *)gesturesPassword {
    return [[NSUserDefaults standardUserDefaults] objectForKey:GesturesPassword];
}

#pragma mark - inint

- (instancetype)initWithUnlockType:(ZLUnlockType)unlockType {
    if (self = [super init]) {
        _unlockType = unlockType;
    }
    return self;
}

#pragma mark - viewDidLoad
- (void)viewDidLoad {
    [super viewDidLoad];

    self.view.backgroundColor = mainColor;

    [self setupMainUI];

    self.gestureLockView.delegate = self;

    self.resetPswBtn.hidden = YES;
    UIButton*button=[[UIButton alloc]initWithFrame:CGRectMake(10, SafeIS_IPHONE_X, 30, 30)];
    [button setBackgroundImage:UIIMAGE(@"loginClose") forState:UIControlStateNormal];
    [button addTarget:self action:@selector(onclick) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:button];
    switch (_unlockType) {
        case ZLUnlockTypeCreatePsw:
        {
            self.gestureLockIndicator.hidden = NO;
            self.otherAcountBtn.hidden = self.forgetPswBtn.hidden = self.nameLabel.hidden = self.headIcon.hidden = YES;
        }
            break;
        case ZLUnlockTypeValidatePsw:
        {
             button.hidden=YES;
            self.gestureLockIndicator.hidden = YES;
            self.otherAcountBtn.hidden = self.forgetPswBtn.hidden = self.nameLabel.hidden = self.headIcon.hidden = NO;

        }
            break;
        case ZLUnlockTypeDeletePsw:
        {
            self.gestureLockIndicator.hidden = YES;
            self.otherAcountBtn.hidden = self.forgetPswBtn.hidden = self.nameLabel.hidden = self.headIcon.hidden = YES;

        }
            break;
        default:
            break;
    }

}
-(void)onclick{
    [self dismissViewControllerAnimated:YES completion:nil];
}
// 创建界面
- (void)setupMainUI {

    CGFloat maginX = 15;
    CGFloat magin = 5;
    CGFloat btnW = ([UIScreen mainScreen].bounds.size.width - maginX * 2 - magin * 2) / 3;
    CGFloat btnH = 30;
//    // 账户头像
//    UIImageView *headIcon ;
//    // 九宫格指示器 小图
//    ZLGestureLockIndicator *gestureLockIndicator;
//    if (self.unlockType==ZLUnlockTypeCreatePsw) {
//        if (kWindowW<330) {
//            headIcon = [[UIImageView alloc] initWithFrame:CGRectMake((self.view.frame.size.width - 50) * 0.5, SafeAreaTopHeight-20, 50, 70)];
//            headIcon.layer.cornerRadius=35;
//        }else{
//            headIcon = [[UIImageView alloc] initWithFrame:CGRectMake((self.view.frame.size.width - 50) * 0.5, SafeAreaTopHeight, 50, 70)];
//            headIcon.layer.cornerRadius=35;
//        }
//
//        // 九宫格指示器 小图
//        gestureLockIndicator = [[ZLGestureLockIndicator alloc]initWithFrame:CGRectMake((self.view.frame.size.width - 70) * 0.5, CGRectGetMaxY(headIcon.frame)+10, 70, 70)];
//    }else{
//        if (kWindowW<330) {
//            headIcon = [[UIImageView alloc] initWithFrame:CGRectMake((self.view.frame.size.width - 70) * 0.5, SafeAreaTopHeight, 70, 100)];
//        }
//        else{
//             headIcon = [[UIImageView alloc] initWithFrame:CGRectMake((self.view.frame.size.width - 70) * 0.5, SafeAreaTopHeight+50, 70, 100)];
//        }
//
//        headIcon.layer.cornerRadius=45;
//        // 九宫格指示器 小图
//       gestureLockIndicator = [[ZLGestureLockIndicator alloc]initWithFrame:CGRectMake((self.view.frame.size.width - 70) * 0.5, CGRectGetMaxY(headIcon.frame)+10, 70, 0)];
//    }
//    headIcon.image = [UIImage imageNamed:@"loginLogo"];
//
//    headIcon.clipsToBounds=YES;
//    [self.view addSubview:headIcon];
//    // 账户名
    UILabel *nameLabel = [[UILabel alloc]initWithFrame:CGRectMake(0, 100 * kWindowWHOne, kWindowW, 20)];
    nameLabel.textAlignment = NSTextAlignmentCenter;
    nameLabel.text = LocalizationKey(@"gesturesPassword");
    nameLabel.font = [UIFont systemFontOfSize:26 * kWindowWHOne];
    nameLabel.textColor = RGBOF(0xe6e6e6);
    [self.view addSubview:nameLabel];
//
//    [self.view addSubview:gestureLockIndicator];
//    self.gestureLockIndicator = gestureLockIndicator;

    // 手势状态栏提示label
    UILabel *statusLabel = [[UILabel alloc]initWithFrame:CGRectMake(0, CGRectGetMaxY(nameLabel.frame) + 10, self.view.frame.size.width, 30)];
    statusLabel.textAlignment = NSTextAlignmentCenter;
    if (self.unlockType==ZLUnlockTypeCreatePsw) {
        statusLabel.text =LocalizationKey(@"gesturesPassThirdPartTip1");

    }else{
        statusLabel.text = LocalizationKey(@"gesturesPassThirdPartTip2");
    }
    statusLabel.font = [UIFont systemFontOfSize:15];
    statusLabel.textColor = RGBOF(0xe6e6e6);
    [self.view addSubview:statusLabel];
    self.statusLabel = statusLabel;
    // 九宫格 手势密码页面
    ZLGestureLockView *gestureLockView = [[ZLGestureLockView alloc]initWithFrame:CGRectMake(0, statusLabel.bottom + 20 * kWindowWHOne, self.view.frame.size.width, self.view.frame.size.width)];
    gestureLockView.delegate = self;
    [self.view addSubview:gestureLockView];
    self.gestureLockView = gestureLockView;

    // 底部三个按钮
    // 其他账户登录按钮
    UIButton *otherAcountBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    otherAcountBtn.frame = CGRectMake(maginX, self.view.frame.size.height - 30 - btnH, btnW, btnH);
    otherAcountBtn.backgroundColor = [UIColor clearColor];
    [otherAcountBtn setTitle:LocalizationKey(@"gesturesPassThirdPartTip3") forState:UIControlStateNormal];
    otherAcountBtn.titleLabel.font = [UIFont systemFontOfSize:15];
    [otherAcountBtn setTitleColor:[UIColor colorWithRed:102/255.0 green:102/255.0 blue:102/255.0 alpha:1] forState:UIControlStateNormal];
    [otherAcountBtn addTarget:self action:@selector(otherAccountLogin:) forControlEvents:UIControlEventTouchUpInside];
    //[self.view addSubview:otherAcountBtn];
    self.otherAcountBtn = otherAcountBtn;

    // 重新绘制按钮
    UIButton *resetPswBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    resetPswBtn.frame = CGRectMake(CGRectGetMaxX(otherAcountBtn.frame) + magin, otherAcountBtn.frame.origin.y, btnW, btnH);
    resetPswBtn.backgroundColor = otherAcountBtn.backgroundColor;
    [resetPswBtn setTitle:LocalizationKey(@"gesturesPassThirdPartTip4") forState:UIControlStateNormal];
    resetPswBtn.titleLabel.font = [UIFont systemFontOfSize:12];
    [resetPswBtn setTitleColor:[UIColor colorWithRed:102/255.0 green:102/255.0 blue:102/255.0 alpha:1] forState:UIControlStateNormal];
    [resetPswBtn addTarget:self action:@selector(resetGesturePassword:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:resetPswBtn];
    self.resetPswBtn = resetPswBtn;

    // 忘记手势密码按钮
    UIButton *forgetPswBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    forgetPswBtn.frame = CGRectMake(CGRectGetMaxX(resetPswBtn.frame) + magin, otherAcountBtn.frame.origin.y, btnW, btnH);
    forgetPswBtn.backgroundColor = otherAcountBtn.backgroundColor;
    [forgetPswBtn setTitle:LocalizationKey(@"gesturesPassThirdPartTip5") forState:UIControlStateNormal];
    forgetPswBtn.titleLabel.font = [UIFont systemFontOfSize:15];
    [forgetPswBtn setTitleColor:[UIColor colorWithRed:102/255.0 green:102/255.0 blue:102/255.0 alpha:1] forState:UIControlStateNormal];
    [forgetPswBtn addTarget:self action:@selector(forgetGesturesPassword:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:forgetPswBtn];
    self.forgetPswBtn = forgetPswBtn;
}

#pragma mark - private

//  创建手势密码
- (void)createGesturesPassword:(NSMutableString *)gesturesPassword {

    if (self.lastGesturePsw.length == 0) {

        if (gesturesPassword.length < 4) {
            self.statusLabel.text = LocalizationKey(@"gesturesPassThirdPartTip6");
            [self shakeAnimationForView:self.statusLabel];
            return;
        }

        if (self.resetPswBtn.hidden == YES) {
            self.resetPswBtn.hidden = NO;
        }

        self.lastGesturePsw = gesturesPassword;
        [self.gestureLockIndicator setGesturePassword:gesturesPassword];
        self.statusLabel.text = LocalizationKey(@"gesturesPassThirdPartTip7");
        return;
    }

    if ([self.lastGesturePsw isEqualToString:gesturesPassword]) { // 绘制成功
        // 保存手势密码
        [ZLGestureLockViewController addGesturesPassword:gesturesPassword];
        [self dismissViewControllerAnimated:YES completion:^{

        }];

    }else {
        self.statusLabel.text = LocalizationKey(@"gesturesPassThirdPartTip8");
        [self shakeAnimationForView:self.statusLabel];
    }


}

// 验证手势密码
- (void)validateGesturesPassword:(NSMutableString *)gesturesPassword {

    static NSInteger errorCount = 5;

    if ([gesturesPassword isEqualToString:[ZLGestureLockViewController gesturesPassword]]) {
        if (self.unlockType==ZLUnlockTypeValidatePsw) {
            [self dismissViewControllerAnimated:YES completion:^{
                errorCount = 5;
            }];

        }else{
            //验证之后删除密码
            [ZLGestureLockViewController deleteGesturesPassword];
            [self dismissViewControllerAnimated:YES completion:nil];
        }

    } else {

        if (errorCount - 1 == 0) { // 你已经输错五次了！ 退出重新登陆！
            if (self.unlockType==ZLUnlockTypeDeletePsw) {
                errorCount = 5;
                 [self.view makeToast:LocalizationKey(@"gesturesPassThirdPartTip9") duration:1.5 position:CSToastPositionCenter];
                dispatch_time_t delayTime = dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2.0/*延迟执行时间*/ * NSEC_PER_SEC));
                dispatch_after(delayTime, dispatch_get_main_queue(), ^{
                   [self dismissViewControllerAnimated:YES completion:nil];
                });
                return;
            }else{
                errorCount = 5;
                EasyShowAlertView *showView = [EasyShowAlertView showAlertWithTitle:LocalizationKey(@"tips") message:LocalizationKey(@"gesturesPassThirdPartTip10")];

                [showView addItemWithTitle:LocalizationKey(@"ok") itemType:ShowAlertItemTypeBlodBlack callback:^(EasyShowAlertView *showview) {
                    //退出登录
                    [LoginNetManager LogoutForCompleteHandle:^(id resPonseObj, int code) {
                        if (code) {
                            if ([resPonseObj[@"code"] integerValue]==0) {
                                [YLUserInfo logout];
                                [ZLGestureLockViewController deleteGesturesPassword];//删除手势密码
                                LoginViewController*loginVC=[[LoginViewController alloc]init];
                                loginVC.enterType=1;
                                YLNavigationController *nav = [[YLNavigationController alloc]initWithRootViewController:loginVC];
                                APPLICATION.window.rootViewController=nav;
                            }else{
                                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                            }
                        }else{
                            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
                        }
                    }];

                }];
                [EasyShowOptions sharedEasyShowOptions].alertTintColor = [UIColor clearColor];
                [showView show];
                return;
            }
        }
        self.statusLabel.text = [NSString stringWithFormat:@"%@%ld%@",LocalizationKey(@"gesturesPassThirdPartTip11"),--errorCount,LocalizationKey(@"gesturesPassThirdPartTip12")];
        [self shakeAnimationForView:self.statusLabel];
    }
}

// 抖动动画
- (void)shakeAnimationForView:(UIView *)view {

    CALayer *viewLayer = view.layer;
    CGPoint position = viewLayer.position;
    CGPoint left = CGPointMake(position.x - 10, position.y);
    CGPoint right = CGPointMake(position.x + 10, position.y);

    CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:@"position"];
    [animation setTimingFunction:[CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut]];
    [animation setFromValue:[NSValue valueWithCGPoint:left]];
    [animation setToValue:[NSValue valueWithCGPoint:right]];
    [animation setAutoreverses:YES]; // 平滑结束
    [animation setDuration:0.08];
    [animation setRepeatCount:3];

    [viewLayer addAnimation:animation forKey:nil];
}

#pragma mark - 按钮点击事件 Anction

// 点击其他账号登陆按钮
- (void)otherAccountLogin:(id)sender {
//    NSLog(@"%s",__FUNCTION__);
}

// 点击重新绘制按钮
- (void)resetGesturePassword:(id)sender {
//    NSLog(@"%s",__FUNCTION__);

    self.lastGesturePsw = nil;
    self.statusLabel.text = LocalizationKey(@"gesturesPassThirdPartTip1");
    self.resetPswBtn.hidden = YES;
    [self.gestureLockIndicator setGesturePassword:@""];
}

// 点击忘记手势密码按钮
- (void)forgetGesturesPassword:(id)sender {
    [EasyShowOptions sharedEasyShowOptions].alertAnimationType = alertAnimationTypePush ;
    //设置主题颜色
    [EasyShowOptions sharedEasyShowOptions].alertTintColor = [UIColor cyanColor];
    EasyShowAlertView *showView = [EasyShowAlertView showAlertWithTitle:LocalizationKey(@"tips") message:LocalizationKey(@"gesturesPassThirdPartTip13")];
        [showView addItemWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"cancel" value:nil table:@"English"] itemType:ShowAlertItemTypeBlodBlack callback:^(EasyShowAlertView *showview) {

        }];
        [showView addItemWithTitle:LocalizationKey(@"ok") itemType:ShowAlertItemTypeBlodBlack callback:^(EasyShowAlertView *showview) {
            //退出登录
            [LoginNetManager LogoutForCompleteHandle:^(id resPonseObj, int code) {
                if (code) {
                    if ([resPonseObj[@"code"] integerValue]==0) {
                        [YLUserInfo logout];
                        [ZLGestureLockViewController deleteGesturesPassword];//删除手势密码
                        LoginViewController*loginVC=[[LoginViewController alloc]init];
                        loginVC.enterType=1;
                        YLNavigationController *nav = [[YLNavigationController alloc]initWithRootViewController:loginVC];
                        APPLICATION.window.rootViewController=nav;
                    }else{
                        [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                    }
                }else{
                    [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
                }
            }];

        }];
        [EasyShowOptions sharedEasyShowOptions].alertTintColor = [UIColor clearColor];

    [showView show];
}

#pragma mark - ZLgestureLockViewDelegate

- (void)gestureLockView:(ZLGestureLockView *)lockView drawRectFinished:(NSMutableString *)gesturePassword {

    switch (_unlockType) {
        case ZLUnlockTypeCreatePsw: // 创建手势密码
        {
            [self createGesturesPassword:gesturePassword];
        }
            break;
        case ZLUnlockTypeValidatePsw: // 校验手势密码
        {
            [self validateGesturesPassword:gesturePassword];
        }
            break;
        case ZLUnlockTypeDeletePsw: // 校验手势密码并删除
        {
            [self validateGesturesPassword:gesturePassword];
        }
            break;
        default:
            break;
    }
}

#pragma mark - UIAlertViewDelegate

-(void)dealloc{


}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
