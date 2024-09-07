//
//  CustomButton.h
//  gt-captcha3-ios-example
//
//  Created by NikoXu on 08/04/2017.
//  Copyright © 2017 Xniko. All rights reserved.
//

#import <UIKit/UIKit.h>

@import GT3Captcha;

@protocol CaptchaButtonDelegate;

/**
 demo场景: 仅自定按钮与验证事件绑定
 */
@interface CustomButton : UIButton

@property (nonatomic, weak) id<CaptchaButtonDelegate> delegate;
- (void)setOriginaStyle;
- (void)startCaptcha;
- (void)stopCaptcha;

@end

@protocol CaptchaButtonDelegate <NSObject>

@optional
- (BOOL)captchaButtonShouldBeginTapAction:(CustomButton *)button;

- (void)delegateGtCaptcha:(GT3CaptchaManager *)manager didReceiveCaptchaCode:(NSString *)code result:(NSDictionary *)result message:(NSString *)message;


@end
