//
//  TCWebCodesBridge.h
//  TCWebCodesSDK
//
//  Created by yoyohao on 17/05/2018.
//  Copyright © 2018 tencent. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface TCWebCodesBridge : NSObject <UIWebViewDelegate>

/**
 返回单例
 */
+ (instancetype)sharedBridge;

/**
 加载H5验证码
 @note  该接口为接入验证码必须实现的接口
 @param view，需要加载验证码的视图
 @param appid，业务申请接入验证码时分配的appid
 @param callback，验证码验证结果回调， 成功/失败可以通过 resultJSON[@"ret"] 判断，0为成功，非0为失败
 */
- (void) loadTencentCaptcha:(UIView*)view appid:(NSString*)appid  callback:(void (^)(NSDictionary *resultJSON))callback;

/**
 设置自定义属性
 @note 该接口为可选接口，主要为了后续扩展。需在loadTencentCaptcha之前调用
 @param options，属性字典（可扩展属性列表可参考相关接入文档）
 */
- (void)setCapOptions:(NSDictionary*)options;

/**
 设置验证码的显示位置
 @note 该接口为可选接口，需在loadTencentCaptcha之前调用。不调该接口时，验证码的中心点将默认为<loadTencentCaptcha>中view的中心位置
 @param center，验证码的中心点坐标
 */
- (void)setCaptchaPosition:(CGPoint)center;

/**
 设置NSLog开关，默认开关为关闭状态，打开后可在控制台输出一些验证码加载过程或出错信息
 @note 该接口为可选接口，一般在接入遇到问题时打开用于辅助调试。需在loadTencentCaptcha之前调用
 @param enable，开关状态，YES时打开，NO为关闭
 */
- (void)setLogState:(BOOL)enable;

@end
