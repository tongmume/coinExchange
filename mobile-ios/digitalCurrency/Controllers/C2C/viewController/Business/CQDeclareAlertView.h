//
//  CQDeclareAlertView.h
//  iDeliver
//
//  Created by 蔡强 on 2017/4/3.
//  Copyright © 2017年 Caiqiang. All rights reserved.
//

//========== 申报异常弹窗 ==========//

#import <UIKit/UIKit.h>

@class CQDeclareAlertView;

@protocol CQDeclareAlertViewDelegate <NSObject>

/** 弹窗按钮点击的回调 */
- (void)CQDeclareAlertView:(CQDeclareAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex msg:(NSString *)msg;

@end

@interface CQDeclareAlertView : UIView

/** 这个弹窗对应的orderID */
@property (nonatomic, copy) NSString *orderID;
/** 用户填写异常情况的textView */
@property (nonatomic, strong) UITextView *textView;

@property (nonatomic, weak) id <CQDeclareAlertViewDelegate> delegate;

/**
 申报异常弹窗的构造方法

 @param title 弹窗标题
 @param message 弹窗message
 @param delegate 代理方
 @param leftButtonTitle 左边按钮的title
 @param rightButtonTitle 右边按钮的title
 @return 一个申报异常的弹窗
 */
- (instancetype)initWithTitle:(NSString *)title initWithTitle:(NSString *)title2 message:(NSString *)message delegate:(id)delegate leftButtonTitle:(NSString *)leftButtonTitle rightButtonTitle:(NSString *)rightButtonTitle;

/** show出这个弹窗 */
- (void)show;

@end
