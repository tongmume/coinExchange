//
//  ZYInputAlertView.h
//  share
//
//  Created by 郑遥 on 16/6/23.
//  Copyright © 2016年 郑遥. All rights reserved.
//

#import <UIKit/UIKit.h>

/** 确认按钮回调的block */
typedef void(^confirmCallback)(NSString *inputString);

@interface ZYInputAlertView : UIView

/** 确认按钮 */
@property (weak, nonatomic) IBOutlet UIButton *confirmBtn;
/** 输入框 */
@property (weak, nonatomic) IBOutlet UITextView *inputTextView;

/** 是否显示蒙版，默认显示 */
@property (nonatomic, assign) BOOL hideBecloud;
/** 圆角半径，默认5.0 */
@property (nonatomic, assign) CGFloat radius;
/** 确认按钮颜色 */
@property (nonatomic, strong) UIColor *confirmBgColor;
/** placeholder */
@property (nonatomic, strong) NSString *placeholder;

/** 类方法创建ZYInputAlertView */
+ (instancetype)alertView;
/** 弹出输入框 */
- (void)show;
/** 移除输入框 */
- (void)dismiss;
/** 点击确认按钮回调 */
- (void)confirmBtnClickBlock:(confirmCallback) block;

@end