//
//  XBRequest.m
//  BaiDang
//
//  Created by 硕影 on 2019/7/18.
//  Copyright © 2019年 NetTest. All rights reserved.
//

#import "XBRequest.h"
#import <CommonCrypto/CommonDigest.h>
static XBRequest *_sharedInstance = nil;

@interface XBRequest()

@end

@implementation XBRequest

+ (instancetype)sharedInstance{

    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _sharedInstance = [[self alloc] init];
    });
    return _sharedInstance;
}

#pragma mark - POST
- (void)postDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects{

    AFHTTPSessionManager *sessionManager = [AFHTTPSessionManager manager];
    sessionManager.responseSerializer = [AFHTTPResponseSerializer serializer];
    // 2.设置非校验证书模式
    sessionManager.securityPolicy = [AFSecurityPolicy policyWithPinningMode:AFSSLPinningModeNone];
    sessionManager.securityPolicy.allowInvalidCertificates = YES;
    [sessionManager.securityPolicy setValidatesDomainName:NO];

    //设置header内容
    [sessionManager.requestSerializer setValue:@"XMLHttpRequest" forHTTPHeaderField:@"X-Requested-With"];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSString *executableFile = [[[NSBundle mainBundle] infoDictionary] objectForKey:(NSString *)kCFBundleExecutableKey];//获取项目名称
    NSString *firstName = [defaults objectForKey:executableFile];
    if (!firstName) {
        [sessionManager.requestSerializer setValue:@"" forHTTPHeaderField:@"x-auth-token"];
    }else{
        [sessionManager.requestSerializer setValue:firstName forHTTPHeaderField:@"x-auth-token"];
    }
    [sessionManager.requestSerializer setValue:[ChangeLanguage networkLanguage] forHTTPHeaderField:@"lang"];
    NSString*md5Str= [MD5 md5:[NSString stringWithFormat:@"%@%@",[keychianTool readUserUUID],[keychianTool getUserNameAndPasswordFromKeychain]]];
    [sessionManager.requestSerializer setValue:md5Str forHTTPHeaderField:@"access-auth-token"];
    sessionManager.requestSerializer.timeoutInterval = 30.f;
    sessionManager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json",@"text/json",@"text/html",@"text/javascript",@"application/x-www-form-urlencoded", nil];

    NSString *str = [url stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];

    [sessionManager POST:str parameters:param progress:^(NSProgress * _Nonnull downloadProgress) {

    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        if ([self showResponseCode:task.response] == 200) {
            id result = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingAllowFragments error:nil];
            if ([result isKindOfClass:[NSDictionary class]]) {
                NSDictionary *resultDic = (NSDictionary *)result;
                responseObjects(resultDic);
            }
        }

    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSDictionary *temDic = @{@"resError":error};
        responseObjects(temDic);
    }];
}
#pragma mark - GET
- (void)getDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects{

    AFHTTPSessionManager *sessionManager = [AFHTTPSessionManager manager];
    // 2.设置非校验证书模式
    sessionManager.securityPolicy = [AFSecurityPolicy policyWithPinningMode:AFSSLPinningModeNone];
    sessionManager.securityPolicy.allowInvalidCertificates = YES;
    [sessionManager.securityPolicy setValidatesDomainName:NO];
    sessionManager.responseSerializer = [AFHTTPResponseSerializer serializer];
    [sessionManager.requestSerializer setValue:@"XMLHttpRequest" forHTTPHeaderField:@"x-requested-with"];

    //设置header内容
    [sessionManager.requestSerializer setValue:@"XMLHttpRequest" forHTTPHeaderField:@"X-Requested-With"];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSString *executableFile = [[[NSBundle mainBundle] infoDictionary] objectForKey:(NSString *)kCFBundleExecutableKey];//获取项目名称
    NSString *firstName = [defaults objectForKey:executableFile];
    if (!firstName) {
        [sessionManager.requestSerializer setValue:@"" forHTTPHeaderField:@"x-auth-token"];
    }else{
        [sessionManager.requestSerializer setValue:firstName forHTTPHeaderField:@"x-auth-token"];
    }
    NSString*md5Str= [MD5 md5:[NSString stringWithFormat:@"%@%@",[keychianTool readUserUUID],[keychianTool getUserNameAndPasswordFromKeychain]]];

    [sessionManager.requestSerializer setValue:md5Str forHTTPHeaderField:@"access-auth-token"];

    sessionManager.requestSerializer.timeoutInterval = 30.f;
    sessionManager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json",@"text/json",@"text/html",@"text/javascript",@"text/plain",@"image/png",@"application/x-www-form-urlencoded", nil];

    NSString *str = [url stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];

    [sessionManager GET:str parameters:param progress:^(NSProgress * _Nonnull downloadProgress) {

    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        if ([self showResponseCode:task.response] == 200) {
            id result = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingAllowFragments error:nil];
            if ([result isKindOfClass:[NSDictionary class]]) {
                NSDictionary *resultDic = (NSDictionary *)result;
                responseObjects(resultDic);
            }
        }

    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSDictionary *temDic = @{@"resError":error};
        responseObjects(temDic);
    }];
}
#pragma mark - PUT
- (void)putDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects{

    AFHTTPSessionManager *sessionManager = [AFHTTPSessionManager manager];
    sessionManager.responseSerializer = [AFHTTPResponseSerializer serializer];

    sessionManager.requestSerializer.timeoutInterval = 10.f;
    sessionManager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json",@"text/json",@"text/html",@"text/javascript", nil];

    NSString *str = [url stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    [sessionManager PUT:str parameters:param success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        id result = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingAllowFragments error:nil];
        if ([result isKindOfClass:[NSDictionary class]]) {
            NSDictionary *resultDic = (NSDictionary *)result;
            responseObjects(resultDic);
        }
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSDictionary *temDic = @{@"resError":error};
        responseObjects(temDic);
    }];

}
#pragma mark - DELETE
- (void)deleteDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects{

    AFHTTPSessionManager *sessionManager = [AFHTTPSessionManager manager];
    sessionManager.responseSerializer = [AFHTTPResponseSerializer serializer];

    sessionManager.requestSerializer.timeoutInterval = 10.f;
    sessionManager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json",@"text/json",@"text/html",@"text/javascript", nil];

    NSString *str = [url stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    [sessionManager DELETE:str parameters:param success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        id result = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingAllowFragments error:nil];
        if ([result isKindOfClass:[NSDictionary class]]) {
            NSDictionary *resultDic = (NSDictionary *)result;
            responseObjects(resultDic);
        }
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSDictionary *temDic = @{@"resError":error};
        responseObjects(temDic);
    }];

}

- (void)dealloc{
    NSLog(@"HTTPRequest dealloc");
}

/* 输出http响应的状态码 */
- (int)showResponseCode:(NSURLResponse *)response {
    NSString *executableFile = [[[NSBundle mainBundle] infoDictionary] objectForKey:(NSString *)kCFBundleExecutableKey];//获取项目名称
    NSHTTPURLResponse* httpResponse = (NSHTTPURLResponse*)response;
    NSInteger responseStatusCode = [httpResponse statusCode];
    NSDictionary *allHeaderFields = httpResponse.allHeaderFields;
    NSString*tokenstring=allHeaderFields[@"x-auth-token"];
    if (tokenstring) {
        NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
        [defaults setObject:tokenstring forKey:executableFile];
        [defaults synchronize];
    };
    return (int)responseStatusCode;
}

@end
