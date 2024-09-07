//
//  ZYInputAlertView.m
//  share
//
//  Created by 郑遥 on 16/6/23.
//  Copyright © 2016年 郑遥. All rights reserved.
//

#import "ZYInputAlertView.h"
#import "UITextView+Placeholder.h"
#define TopWindow [UIApplication sharedApplication].keyWindow

@interface ZYInputAlertView ()

/** 确认按钮回调 */
@property (nonatomic, copy) confirmCallback confirmBlock;
/** 蒙版 */
@property (nonatomic, weak) UIView *becloudView;

@end

@implementation ZYInputAlertView

+ (instancetype)alertView
{
    return [[[NSBundle mainBundle] loadNibNamed:NSStringFromClass(self) owner:nil options:nil] lastObject];
}

- (void)awakeFromNib
{
    [super awakeFromNib];
    
    [self setBtnDisabled];
    [self setCornerRadius:self];
    [self setCornerRadius:self.inputTextView];
    [self setCornerRadius:self.confirmBtn];
    
    // 添加点击手势
    UITapGestureRecognizer *tapGR = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(exitKeyboard)];
    [self addGestureRecognizer:tapGR];
    
    // 设置输入框边线
    self.inputTextView.layer.borderWidth = 1;
    self.inputTextView.layer.borderColor = [UIColor colorWithRed:18/255.0 green:22/255.0 blue:28/255.0 alpha:1].CGColor;
    // 发送键盘通知
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(inputTextViewDidChange) name:UITextViewTextDidChangeNotification object:nil];
}

- (void)inputTextViewDidChange
{
    if (self.inputTextView.text.length == 0) {
        [self setBtnDisabled];
    } else {
        self.confirmBtn.enabled = YES;
        if (self.confirmBgColor) {
            self.confirmBtn.backgroundColor = self.confirmBgColor;
            self.confirmBtn.layer.opacity = 1.0;
        } else {
            self.confirmBtn.backgroundColor = kRGBColor(205, 142, 41);
            self.confirmBtn.layer.opacity = 1.0;
        }
    }
}

#pragma mark - 设置无效状态
- (void)setBtnDisabled
{
    self.inputTextView.placeholder = self.placeholder;
    self.inputTextView.placeholderColor = RGBOF(0x999999);
    self.confirmBtn.enabled = NO;
    self.confirmBtn.backgroundColor = [UIColor grayColor];
    self.confirmBtn.layer.opacity = 0.5;
}

#pragma mark - 设置控件圆角
- (void)setCornerRadius:(UIView *)view
{
    if (self.radius) {
        view.layer.cornerRadius = self.radius;
    } else {
        view.layer.cornerRadius = 5.0;
    }
    
    view.layer.masksToBounds = YES;
}

- (void)setPlaceholder:(NSString *)placeholder
{
    _placeholder = [placeholder copy];
    self.inputTextView.placeholder = self.placeholder;
}

- (void)show
{
    // 蒙版
    UIView *becloudView = [[UIView alloc] initWithFrame:[UIScreen mainScreen].bounds];
    becloudView.backgroundColor = [UIColor blackColor];
    becloudView.layer.opacity = 0.3;
    UITapGestureRecognizer *tapGR = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(closeAlertView:)];
    [becloudView addGestureRecognizer:tapGR];
    // 是否显示蒙版
    if (_hideBecloud) {
        becloudView.hidden = YES;
    } else {
        becloudView.hidden = NO;
    }
    [TopWindow addSubview:becloudView];
    self.becloudView = becloudView;
    
    self.confirmBtn.backgroundColor = [UIColor lightGrayColor];
    self.confirmBtn.layer.opacity = 0.5;
    
    // 输入框
    self.frame = CGRectMake(0, 0, becloudView.frame.size.width * 0.8, becloudView.frame.size.height * 0.3);
    self.center = CGPointMake(becloudView.center.x, becloudView.frame.size.height * 0.4);
    [TopWindow addSubview:self];
    
}

- (void)exitKeyboard
{
    [self endEditing:YES];
}

#pragma mark - 移除ZYInputAlertView
- (void)dismiss
{
    [self removeFromSuperview];
    [self.becloudView removeFromSuperview];
}

#pragma mark - 点击关闭按钮
- (IBAction)closeAlertView:(UIButton *)sender {
    [self dismiss];
}

#pragma mark - 接收传过来的block
- (void)confirmBtnClickBlock:(confirmCallback)block
{
    self.confirmBlock = block;
}

#pragma mark - 点击确认按钮
- (IBAction)confirmBtnClick:(UIButton *)sender {
    [self dismiss];
    if (self.confirmBlock) {
        self.confirmBlock(self.inputTextView.text);
    }
}

- (void)dealloc
{
     // 移除监听者
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

@end
