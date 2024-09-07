//
//  CQDeclareAlertView.m
//  iDeliver
//
//  Created by 蔡强 on 2017/4/3.
//  Copyright © 2017年 Caiqiang. All rights reserved.
//

//========== 申报异常弹窗 ==========//

#import "CQDeclareAlertView.h"
#import "UIColor+Util.h"
#import "UIView+frameAdjust.h"

@interface CQDeclareAlertView ()<UITextViewDelegate>

/** 弹窗主内容view */
@property (nonatomic,strong) UIView   *contentView;
/** 弹窗标题 */
@property (nonatomic,copy)   NSString *title;
/** 弹窗标题下面的提示文字 */
@property (nonatomic,copy)   NSString *title2;
/** 弹窗message */
@property (nonatomic,copy)   NSString *message;
/** message label */
@property (nonatomic,strong) UILabel  *messageLabel;
/** 左边按钮title */
@property (nonatomic,copy)   NSString *leftButtonTitle;
/** 右边按钮title */
@property (nonatomic,copy)   NSString *rightButtonTitle;

@end

#define SCREEN_WIDTH [UIScreen mainScreen].bounds.size.width
#define SCREEN_HEIGHT [UIScreen mainScreen].bounds.size.height

@implementation CQDeclareAlertView

#pragma mark - 构造方法
/**
 申报异常弹窗的构造方法
 
 @param title 弹窗标题
 @param message 弹窗message
 @param delegate 确定代理方
 @param leftButtonTitle 左边按钮的title
 @param rightButtonTitle 右边按钮的title
 @return 一个申报异常的弹窗
 */
- (instancetype)initWithTitle:(NSString *)title initWithTitle:(NSString *)title2 message:(NSString *)message delegate:(id)delegate leftButtonTitle:(NSString *)leftButtonTitle rightButtonTitle:(NSString *)rightButtonTitle {
    if (self = [super init]) {
        self.title = title;
        self.title2 = title2;
        self.message = message;
        self.delegate = delegate;
        self.leftButtonTitle = leftButtonTitle;
        self.rightButtonTitle = rightButtonTitle;
        
        // 接收键盘显示隐藏的通知
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleKeyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleKeyboardWillHide:) name:UIKeyboardWillHideNotification object:nil];
        
        // UI搭建
        [self setupUI];
    }
    return self;
}

#pragma mark - UI搭建
/** UI搭建 */
- (void)setupUI {
    self.frame = [UIScreen mainScreen].bounds;
    self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.5];
    
    //------- 弹窗主内容 -------//
    self.contentView = [[UIView alloc] init];
    self.contentView.frame = CGRectMake((SCREEN_WIDTH - 285) / 2, (SCREEN_HEIGHT - 215) / 2, 285, 215);
    self.contentView.center = self.center;
    [self addSubview:self.contentView];
    self.contentView.backgroundColor = mainColor;
    self.contentView.layer.cornerRadius = 6;
    
    // 标题
    UILabel *titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 10, self.contentView.width, 22)];
    [self.contentView addSubview:titleLabel];
    titleLabel.font = [UIFont boldSystemFontOfSize:18];
    titleLabel.textAlignment = NSTextAlignmentCenter;
    titleLabel.text = self.title;
    titleLabel.textColor = VCBackgroundColor;
    
    // 提示文字
    UILabel *titleLabe2 = [[UILabel alloc] initWithFrame:CGRectMake(0, titleLabel.maxY + 10, self.contentView.width, 66)];
    [self.contentView addSubview:titleLabe2];
    titleLabe2.font = [UIFont boldSystemFontOfSize:14];
    titleLabe2.textAlignment = NSTextAlignmentCenter;
    titleLabe2.numberOfLines = 0;
    titleLabe2.text = self.title2;
    titleLabe2.textColor = VCBackgroundColor;
    
    // 填写异常情况描述的textView
    self.textView = [[UITextView alloc]initWithFrame:CGRectMake(22, titleLabe2.maxY + 5, self.contentView.width - 44, 103)];
    [self.contentView addSubview:self.textView];
    self.textView.layer.cornerRadius = 6;
    self.textView.backgroundColor = [UIColor colorWithHexString:@"e0e0e0"];
    self.textView.font = [UIFont systemFontOfSize:12];
    self.textView.layer.backgroundColor = [[UIColor clearColor] CGColor];
    self.textView.layer.borderColor = AppTextColor_Level_2.CGColor;
    self.textView.layer.borderWidth = 1.0;
    self.textView.textColor = VCBackgroundColor;
    [self.textView.layer setMasksToBounds:YES];
    [self.textView becomeFirstResponder];
    self.textView.delegate = self;
    
    // textView里面的占位label
    self.messageLabel = [[UILabel alloc] initWithFrame:CGRectMake(8, 8, self.textView.width - 16, self.textView.height - 16)];
    self.messageLabel.text = self.message;
    self.messageLabel.textColor = AppTextColor_Level_2;
    self.messageLabel.numberOfLines = 0;
    self.messageLabel.font = [UIFont systemFontOfSize:12];
    self.messageLabel.textColor = [UIColor colorWithHexString:@"484848"];
    [self.messageLabel sizeToFit];
    [self.textView addSubview:self.messageLabel];
    
    // 申报异常按钮
    UIButton *abnormalButton = [[UIButton alloc]initWithFrame:CGRectMake(self.textView.minX, self.textView.maxY + 5, 100, 40)];
    [self.contentView addSubview:abnormalButton];
    abnormalButton.backgroundColor = baseColor;
    [abnormalButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [abnormalButton setTitle:_leftButtonTitle forState:UIControlStateNormal];
    [abnormalButton.titleLabel setFont:[UIFont systemFontOfSize:18]];
    abnormalButton.layer.cornerRadius = 6;
    [abnormalButton addTarget:self action:@selector(abnormalButtonClicked) forControlEvents:UIControlEventTouchUpInside];
    
    // 取消按钮
    UIButton *cancelButton = [[UIButton alloc] initWithFrame:CGRectMake(self.textView.maxX - 100, abnormalButton.minY, 100, 40)];
    [self.contentView addSubview:cancelButton];
    [cancelButton setTitle:_rightButtonTitle forState:UIControlStateNormal];
    cancelButton.backgroundColor = AppTextColor_CCCCCC;
    [cancelButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [cancelButton.titleLabel setFont:[UIFont systemFontOfSize:18]];
    cancelButton.layer.cornerRadius = 6;
    [cancelButton addTarget:self action:@selector(cancelButtonClicked) forControlEvents:UIControlEventTouchUpInside];
    
    //------- 调整弹窗高度和中心 -------//
    self.contentView.height = cancelButton.maxY + 10;
    self.contentView.center = self.center;
}

#pragma mark - 弹窗展示/隐藏

/** 弹出此弹窗 */
- (void)show {
    // 出场动画
    self.alpha = 0;
    self.contentView.transform = CGAffineTransformScale(self.contentView.transform, 1.3, 1.3);
    [UIView animateWithDuration:0.2 animations:^{
        self.alpha = 1;
        self.contentView.transform = CGAffineTransformIdentity;
    }];
    
    UIWindow *window = [UIApplication sharedApplication].delegate.window;
    [window addSubview:self];
}

/** 移除此弹窗 */
- (void)dismiss {
    [self removeFromSuperview];
}

#pragma mark - User Action

/** 申报异常按钮点击 */
- (void)abnormalButtonClicked {
    if ([self.delegate respondsToSelector:@selector(CQDeclareAlertView:clickedButtonAtIndex:msg:)]) {
        [self.delegate CQDeclareAlertView:self clickedButtonAtIndex:0 msg:self.textView.text];
    }
    [self dismiss];
}

/** 取消按钮点击 */
- (void)cancelButtonClicked {
    if ([self.delegate respondsToSelector:@selector(CQDeclareAlertView:clickedButtonAtIndex:msg:)]) {
        [self.delegate CQDeclareAlertView:self clickedButtonAtIndex:1 msg:self.textView.text];
    }
    [self dismiss];
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self endEditing:YES];
}

#pragma mark - 处理键盘显示/隐藏

/** 处理键盘将要显示 */
- (void)handleKeyboardWillShow:(NSNotification *)notification {
    // 获取到了键盘frame
    CGRect frame = [[[notification userInfo] objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue];
    CGFloat keyboardHeight = frame.size.height;
    
    self.contentView.maxY = SCREEN_HEIGHT - keyboardHeight - 10;
}

- (void)handleKeyboardWillHide:(NSNotification *)notification {
    // 弹窗回到屏幕正中
    self.contentView.centerY = SCREEN_HEIGHT / 2;
}

#pragma mark - UITextView Delegate

- (void)textViewDidChange:(UITextView *)textView {
    self.messageLabel.hidden = ![textView.text isEqualToString:@""];
}

@end
