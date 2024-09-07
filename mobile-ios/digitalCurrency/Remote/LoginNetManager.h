//
//  LoginNetManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/2/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseNetManager.h"

@interface LoginNetManager : BaseNetManager
//获取短信验证码
+(void)getMobilePhoneForTel:(NSString *)tel withcountry:(NSString*)country withgeetest_challenge:(NSString*)challenge withgeetest_validate:(NSString*)validate withgeetest_seccode:(NSString*)seccode CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取邮箱证码
+(void)getEmial:(NSString *)email  withgeetest_challenge:(NSString*)challenge withgeetest_validate:(NSString*)validate withgeetest_seccode:(NSString*)seccode CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//邮箱或者手机登录
+(void)LogInForUsername:(NSString *)username andpassword:(NSString *)password CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//极验验证的邮箱或者手机登录
+(void)challengeLogInForUsername:(NSString *)username andpassword:(NSString *)password geetest_challenge:(NSString *)challenge geetest_validate:(NSString *)validate geetest_seccode:(NSString *)seccode CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//手机号码注册
+(void)getAccountForPhone:(NSString *)phone withpassword:(NSString*)password withcode:(NSString*)code withusername:(NSString*)username withcountry:(NSString*)country withG3Result:(NSDictionary *)g3Result CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//邮箱注册
+(void)getAccountForEmail:(NSString *)email withpassword:(NSString*)password  withcode:(NSString*)code withusername:(NSString*)username withcountry:(NSString*)country withpromotion:(NSString*)promotion CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//退出
+(void)LogoutForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//支持国家
+(void)getAllCountryCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//忘记密码-获取邮箱验证码
+(void)FogetgetEmailcode:(NSString*)email CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//忘记密码-获取手机短信验证码
+(void)FogetgetMobilecode:(NSString*)mobile CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//极验验证的忘记密码-获取手机短信验证码
+(void)challengeFogetgetMobilecode:(NSString*)mobile geetest_challenge:(NSString *)challenge geetest_validate:(NSString *)validate geetest_seccode:(NSString *)seccode CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//忘记登录密码去重置登录密码
+(void)FogetToResetPasswordWithaccount:(NSString*)account withcode:(NSString*)code withMode:(int)mode Withpassword:(NSString*)password CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
@end
