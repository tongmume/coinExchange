//
//  GTCaptchanActivityIndicator.h
//  GT3Captcha
//
//  Created by NikoXu on 9/27/16.
//  Copyright © 2016 Geetest. All rights reserved.
//

#import "GT3Utils.h"

@class GT3CaptchaManager;

@protocol GT3CaptchaButtonDelegate;

@interface GT3CaptchaButton : UIControl

/** Captcha Manager */
@property (nonatomic, readonly, strong) GT3CaptchaManager *captchaManager;

/** Captcha Button Delegate */
@property (nonatomic, weak) id<GT3CaptchaButtonDelegate> delegate;

/** Captcha State */
@property (nonatomic, readonly, assign) GT3CaptchaState captchaState;

/** Defines Inset for Captcha Button. */
@property (nonatomic, assign) UIEdgeInsets captchaEdgeInsets;

/**
 *  @abstract
 *  Use thoes keys to config title label in different
 *  state on the captcha button.
 *
 *  @discussion
 *  - contain keys: 'inactive', 'active', 'initial',
 *  'waiting', 'collecting', 'computing', 'success', 
 *  'fail', 'error', 'cancel'.
 */
@property (nonatomic, strong) NSMutableDictionary<NSString *, NSAttributedString *> *tipsDict;

/**
 *  Captcha Button `backgroundColor`. Defaults to
 *  0xf3f3f3. Animatable.
 */
@property (nonatomic, strong) UIColor *mainColor;

/**
 *  Defines color for Captcha Indicator View background.
 */
@property (nonatomic, strong) UIColor *indicatorColor;

/**
 *  Captcha Button `layer.borderColor`. Defaults 
 *  to 0xcccccc. Animatable.
 */
@property (nonatomic, strong) UIColor *borderColor;

/**
 *  Captcha Button `layer.borderWidth` Defaults to
 *  1.0. Animatable.
 */
@property (nonatomic, assign) CGFloat borderWidth;

/**
 *  Captcha Button `layer.cornerRadius`. Defaults
 *  to 3.0. Animatable.
 */
@property (nonatomic, assign) CGFloat cornerRadius;

/**
 *  Logo Image View, just work in same configuration 
 *  as back-end.
 */
@property (nonatomic, strong) UIImage *logoImage;

/**
 *  @abstract
 *  Initializes and returns a newly allocated captcha
 *  button object with the specified frame rectangle
 *
 *  @param frame            The frame rectangle for the button, measured in points.
 *  @param captchaManager   GT3CaptchaManager instance.
 *  @return A initialized GTCaptchaButton object.
 */
- (instancetype)initWithFrame:(CGRect)frame captchaManager:(GT3CaptchaManager *)captchaManager;

/**
 *  @abstract Start Captcha.
 *
 *  @discussion
 *  Depending on captcha state, call GT3CaptchaManager
 *  instance method `startGTCaptchaWithAnimated:`, 
 *  `requestGTCaptcha`, `showGTViewIfRegiested` inner.
 */
- (void)startCaptcha;

/**
 *  @abstract Stop Captcha.
 *
 *  @discussion Call GT3CaptchaManager instance method
 *  `stopGTCaptcha` inner.
 */
- (void)stopCaptcha;

/**
 *  @abstract Reset Captcha.
 *
 *  @discussion Call GT3CaptchaManager instance method
 *  `resetCaptcha` inner.
 */
- (void)resetCaptcha;

/**
 *  @abstract Update captcha button tips label instantly.
 *
 *  @param title An attributed string for title
 */
- (void)updateTitleLabel:(NSAttributedString *)title;

@end

@protocol GT3CaptchaButtonDelegate <NSObject>

@optional
/** Return NO to disallow captcha event. Default YES. */
- (BOOL)captchaButtonShouldBeginTapAction:(GT3CaptchaButton *)button;

/** Called this method after GT3CaptchaButton's property 'cpatchaState' did change. */
- (void)captchaButton:(GT3CaptchaButton *)button didChangeState:(GT3CaptchaState)state;

@end
