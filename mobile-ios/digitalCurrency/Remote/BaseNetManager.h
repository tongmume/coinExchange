//
//  BaseNetManager.h
//  BaseProject
//
//  Created by jiyingxin on 15/10/21.
//  Copyright © 2015年 Tarena. All rights reserved.
//

#import <Foundation/Foundation.h>

#define kCompletionHandle completionHandle:(void(^)(id model, int code))completionHandle

@interface BaseNetManager : NSObject

+(void)ylRequestWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;

+(void)ylNonTokenRequestWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;

/**
 *  Get请求
 *
 *  @param urlString   请求路径url
 *  @param parameter   请求参数（字典形式）
 *  @param resultBlock 返回数据
 */
+(void)requestWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;
/**
 *  get请求方式，此方法针对一些已经在url拼接部分参数的链接
 *
 *  @param urlString   已经拼接部分参数的url
 *  @param parameter   参数字典
 *  @param resultBlock 返回数据
 */
+(void)requesByAppendtWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;

/**
 *  POST请求(不能设置header)
 *
 *  @param urlStr      请求路径url
 *  @param parameter   请求参数（字典形式）
 *  @param resultBlock 返回数据
 */
+(void)requestWithPost:(NSString *)urlStr parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;

/**
 *  POST请求 （能设置header）
 *
 *  @param urlStr      请求路径url
 *  @param parameter   请求参数（字典形式）
 *  @param resultBlock 返回数据
 */
+(void)requestWithPost:(NSString *)urlStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;

/**
 *  上传图片到服务器
 *
 *  @param urlStr      请求路径url
 *  @param imageData   上传图片的数据
 *  @param resultBlock 返回结果的block
 */
+(void)uploadImageWith:(NSString *)urlStr imageData:(NSData *)imageData successBlock:(ResultBlock)resultBlock;


/**
 Post 加密请求
 
 @param apiStr      请求路径api
 @param parameter   请求参数(字典形式)
 @param resultBlock 返回数据
 */
+(void)requestByDesWithPost:(NSString *)apiStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;

+(void)POSTrequestByDesWithPost:(NSString *)apiStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock;

//带token的请求
+ (void)requestByDesWithPost1:(NSString *)apiStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter andToken:(NSString *)token successBlock:(ResultBlock)resultBlock;



@end
