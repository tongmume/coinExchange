//
//  BaseNetManager.m
//  BaseProject
//
//  Created by jiyingxin on 15/10/21.
//  Copyright © 2015年 Tarena. All rights reserved.
//

#import "BaseNetManager.h"


@implementation BaseNetManager

+(void)ylRequestWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
   
    NSMutableDictionary *para = [parameter mutableCopy];
    NSString *urlStr = urlStr = [NSString stringWithFormat:@"%@%@",HOST,urlString];
//    NSLog(@"接口：%@ %@",urlStr,para);
    [BaseNetManager requestWithPost:urlStr parameters:para successBlock:^(id resultObject, int isSuccessed) {
        if (isSuccessed) {
            resultBlock(resultObject,1);
        }else{
            resultBlock(nil,0);
        }
    }];
}


//post--
+(void)ylNonTokenRequestWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
      NSMutableDictionary *para = [parameter mutableCopy];
    NSString *urlStr = urlStr = [NSString stringWithFormat:@"%@%@",HOST,urlString];
//    NSLog(@"接口&&参数%@%@",urlStr,para);
    NSMutableDictionary *dict=[NSMutableDictionary dictionaryWithCapacity:0];
    [dict setValue:@"application/x-www-form-urlencoded" forKey:@"Content-Type"];
    [BaseNetManager requestWithPost:urlStr header:dict parameters:para successBlock:^(id resultObject, int isSuccessed) {
        if (isSuccessed) {
            resultBlock(resultObject,1);
            
        }else{
            resultBlock(nil,0);
        }
    }];
}
//get请求
+(void)requestWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
    
    for (int i =0; i<parameter.allKeys.count; i++) {
        NSString *key = parameter.allKeys[i];
        NSString *string = [NSString stringWithFormat:@"%@=%@",key,parameter[key]];
        if (i<parameter.allKeys.count-1) {
            string = [string stringByAppendingString:@"&"];
        }
        string = (NSString *)CFBridgingRelease(CFURLCreateStringByAddingPercentEscapes(NULL,(CFStringRef)string, NULL, (CFStringRef)@"!*'();:@+$,/?%#[]", kCFStringEncodingUTF8));
        urlString = [urlString stringByAppendingString:string];
    }
    
    NSString *urlStr = urlStr = [NSString stringWithFormat:@"%@%@",HOST,urlString];
    
    NSURL *url = [NSURL URLWithString:urlStr];
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url cachePolicy:0 timeoutInterval:10];
    //设置header内容
    [request addValue:@"XMLHttpRequest" forHTTPHeaderField:@"X-Requested-With"];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSString *executableFile = [[[NSBundle mainBundle] infoDictionary] objectForKey:(NSString *)kCFBundleExecutableKey];//获取项目名称
    NSString *firstName = [defaults objectForKey:executableFile];
    if (!firstName) {
        [request addValue:@"" forHTTPHeaderField:@"x-auth-token"];
    }else{
        [request addValue:firstName forHTTPHeaderField:@"x-auth-token"];
    }
    NSString*md5Str= [MD5 md5:[NSString stringWithFormat:@"%@%@",[keychianTool readUserUUID],[keychianTool getUserNameAndPasswordFromKeychain]]];
    [request addValue: md5Str forHTTPHeaderField:@"access-auth-token"];
    
    NSURLSession *session = [NSURLSession sharedSession];
    NSURLSessionTask *task = [session dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        if (data && [BaseNetManager showResponseCode:response] == 200) {
            id result = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&error];
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(result,1);
            }];
        }else if ([BaseNetManager showResponseCode:response] <= 0){
            NSLog(@"请求超时");
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(nil,0);
            }];
        }else if ([BaseNetManager showResponseCode:response] > 399 && [BaseNetManager showResponseCode:response] < 500){
//            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
//                resultBlock(nil,0);
//                [LEEAlert alert].config
//                .LeeTitle(LocalizationKey(@"warmPrompt"))
//                .LeeAddContent(^(UILabel *label) {
//                    label.text = LocalizationKey(@"GestureMessage");
//                    label.font = [UIFont systemFontOfSize:16];
//                })
//                .LeeAddAction(^(LEEAction *action) {
//                    action.title = LocalizationKey(@"ok");
//                    action.titleColor = RGBCOLOR(43, 43, 43);
//                    action.font = [UIFont systemFontOfSize:16];
//                    action.clickBlock = ^{
//                        abort();
//                    };
//                })
//                .LeeShow();
//            }];
            
        }else{
        }
    }];
    NSLog(@"接口：%@ %@",url,parameter);
    [task resume];
    
}

//get请求2
+(void)requesByAppendtWithGET:(NSString *)urlString parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
  
    for (int i =0; i<parameter.allKeys.count; i++) {
        NSString *key = parameter.allKeys[i];
        NSString *string = [NSString stringWithFormat:@"%@=%@",key,parameter[key]];
        if (i<parameter.allKeys.count-1) {
            string = [string stringByAppendingString:@"&"];
        }
        string = (NSString *)CFBridgingRelease(CFURLCreateStringByAddingPercentEscapes(NULL,(CFStringRef)string, NULL, (CFStringRef)@"!*'();:@+$,/?%#[]", kCFStringEncodingUTF8));
        urlString = [urlString stringByAppendingString:string];
    }
    NSURL *url = [NSURL URLWithString:urlString];
    NSURLRequest *request = [NSURLRequest requestWithURL:url cachePolicy:0 timeoutInterval:10];
    NSURLSession *session = [NSURLSession sharedSession];
    NSURLSessionTask *task = [session dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        if (data && [BaseNetManager showResponseCode:response] == 200) {
            id result = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&error];
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(result,1);
            }];
            
        }else if ([BaseNetManager showResponseCode:response] <= 0){
            NSLog(@"请求超时");
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(nil,0);
            }];
        }else if ([BaseNetManager showResponseCode:response] > 399 && [BaseNetManager showResponseCode:response] < 500){
//            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
//                resultBlock(nil,0);
//                [LEEAlert alert].config
//                .LeeTitle(LocalizationKey(@"warmPrompt"))
//                .LeeAddContent(^(UILabel *label) {
//                    label.text = LocalizationKey(@"GestureMessage");
//                    label.font = [UIFont systemFontOfSize:16];
//                })
//                .LeeAddAction(^(LEEAction *action) {
//                    action.title = LocalizationKey(@"ok");
//                    action.titleColor = RGBCOLOR(43, 43, 43);
//                    action.font = [UIFont systemFontOfSize:16];
//                    action.clickBlock = ^{
//                        abort();
//                    };
//                })
//                .LeeShow();
//            }];
            
        }else{
            
        }
        
    }];
//    NSLog(@"GetURL：%@",url);
    [task resume];
}

//post请求1(不设置header)
+(void)requestWithPost:(NSString *)urlStr parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
    
    NSURL *url = [NSURL URLWithString:urlStr];
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc]initWithURL:url cachePolicy:0 timeoutInterval:10];
    request.HTTPMethod = @"POST";
    [request setValue:@"XMLHttpRequest" forHTTPHeaderField:@"X-Requested-With"];
    NSString *para = @"";
    for (int i =0; i<parameter.allKeys.count; i++) {
        NSString *key = parameter.allKeys[i];
        NSString *string = [NSString stringWithFormat:@"%@=%@",key,parameter[key]];
        if (i<parameter.allKeys.count-1) {
            string = [string stringByAppendingString:@"&"];
        }
        para = [para stringByAppendingString:string];
    }
    
    para = (NSString *)CFBridgingRelease(CFURLCreateStringByAddingPercentEscapes(NULL,(CFStringRef)para, NULL, (CFStringRef)@"!*'();:@+$,/?%#[]", kCFStringEncodingUTF8));
    request.HTTPBody = [para dataUsingEncoding:NSUTF8StringEncoding];
    NSURLSession *session = [NSURLSession sharedSession];
    NSURLSessionDataTask *task = [session dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        
        if (data && [BaseNetManager showResponseCode:response] == 200) {
            id result = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&error];
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(result,1);
            }];
        }else if ([BaseNetManager showResponseCode:response] <= 0){
            NSLog(@"请求超时");
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(nil,0);
            }];
        }else if ([BaseNetManager showResponseCode:response] > 399 && [BaseNetManager showResponseCode:response] < 500){
//            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
//                resultBlock(nil,0);
//                [LEEAlert alert].config
//                .LeeTitle(LocalizationKey(@"warmPrompt"))
//                .LeeAddContent(^(UILabel *label) {
//                    label.text = LocalizationKey(@"GestureMessage");
//                    label.font = [UIFont systemFontOfSize:16];
//                })
//                .LeeAddAction(^(LEEAction *action) {
//                    action.title = LocalizationKey(@"ok");
//                    action.titleColor = RGBCOLOR(43, 43, 43);
//                    action.font = [UIFont systemFontOfSize:16];
//                    action.clickBlock = ^{
//                        abort();
//                    };
//                })
//                .LeeShow();
//            }];
            
        }else{
            
        }
    }];
    [task resume];
    
}

//post请求2（设置header）
+(void)requestWithPost:(NSString *)urlStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
    NSString *executableFile = [[[NSBundle mainBundle] infoDictionary] objectForKey:(NSString *)kCFBundleExecutableKey];//获取项目名称
    NSURL *url = [NSURL URLWithString:urlStr];
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc]initWithURL:url cachePolicy:0 timeoutInterval:30];
    request.HTTPMethod = @"POST";
    //设置header内容
    [request addValue:@"XMLHttpRequest" forHTTPHeaderField:@"X-Requested-With"];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSString *firstName = [defaults objectForKey:executableFile];
    if (!firstName) {
        [request addValue:@"" forHTTPHeaderField:@"x-auth-token"];
    }else{
        [request addValue:firstName forHTTPHeaderField:@"x-auth-token"];
    }
   NSString*md5Str= [MD5 md5:[NSString stringWithFormat:@"%@%@",[keychianTool readUserUUID],[keychianTool getUserNameAndPasswordFromKeychain]]];
    [request addValue: md5Str forHTTPHeaderField:@"access-auth-token"];
    NSString*langString= [ChangeLanguage networkLanguage];
    [request addValue: langString forHTTPHeaderField:@"lang"];
    //设置body内容
    NSString *para = @"";
    for (int i =0; i<parameter.allKeys.count; i++) {
        NSString *key = parameter.allKeys[i];
        NSString *string = [NSString stringWithFormat:@"%@=%@",key,parameter[key]];
        if (i<parameter.allKeys.count-1) {
            string = [string stringByAppendingString:@"&"];
        }
        para = [para stringByAppendingString:string];
    }

    para = (NSString *)CFBridgingRelease(CFURLCreateStringByAddingPercentEscapes(NULL,(CFStringRef)para, NULL, (CFStringRef)@"!*'();:@+$,/?%#[]", kCFStringEncodingUTF8));
    request.HTTPBody = [para dataUsingEncoding:NSUTF8StringEncoding];
     NSURLSessionConfiguration *config = [NSURLSessionConfiguration defaultSessionConfiguration];
    NSURLSession *session = [NSURLSession sessionWithConfiguration:config];
    //NSURLSession *session = [NSURLSession sharedSession];
    NSURLSessionDataTask *task = [session dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        if (data && [BaseNetManager showResponseCode:response] == 200) {
            id result = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&error];
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(result,1);
            }];
        }else if ([BaseNetManager showResponseCode:response] <= 0){
            NSLog(@"请求超时");
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(nil,0);
            }];
        }else if ([BaseNetManager showResponseCode:response] > 399 && [BaseNetManager showResponseCode:response] < 500){
//            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
//                resultBlock(nil,0);
//                [LEEAlert alert].config
//                .LeeTitle(LocalizationKey(@"warmPrompt"))
//                .LeeAddContent(^(UILabel *label) {
//                    label.text = LocalizationKey(@"GestureMessage");
//                    label.font = [UIFont systemFontOfSize:16];
//                })
//                .LeeAddAction(^(LEEAction *action) {
//                    action.title = LocalizationKey(@"ok");
//                    action.titleColor = RGBCOLOR(43, 43, 43);
//                    action.font = [UIFont systemFontOfSize:16];
//                    action.clickBlock = ^{
//                        abort();
//                    };
//                })
//                .LeeShow();
//            }];
            
        }else{
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(nil,0);
            }];
        }
    }];
    NSLog(@"接口：%@ %@",url,parameter);
    [task resume];
    
}


//上传图片
+(void)uploadImageWith:(NSString *)urlStr imageData:(NSData *)imageData successBlock:(ResultBlock)resultBlock{
    NSMutableURLRequest * request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:urlStr]];
    [request addValue:@"image/jpeg" forHTTPHeaderField:@"Content-Type"];
    [request addValue:@"text/html" forHTTPHeaderField:@"Accept"];
    [request setHTTPMethod:@"POST"];
    [request setCachePolicy:NSURLRequestReloadIgnoringCacheData];
    [request setTimeoutInterval:20];
    
    NSURLSession *session = [NSURLSession sharedSession];
    NSURLSessionUploadTask * uploadtask = [session uploadTaskWithRequest:request fromData:imageData completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
        if (data && [BaseNetManager showResponseCode:response]) {
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(data,1);
            }];
        }else{
            [[NSOperationQueue mainQueue] addOperationWithBlock:^{
                resultBlock(nil,0);
            }];
        }
    }];
    [uploadtask resume];
}

+(void)POSTrequestByDesWithPost:(NSString *)apiStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
    NSString * urlString = [HOST stringByAppendingString:apiStr];
    NSTimeInterval interval = [[NSDate date] timeIntervalSince1970] * 1000;
    NSString *timeString = [NSString stringWithFormat:@"%.f",interval];
    NSLog(@"timeString:%@",timeString);
//    printf("---------------------------------------\n");
//
//    //NSString *UDID = [[[UIDevice currentDevice] identifierForVendor] UUIDString];
//
//    printf("---------------------------------------\n");
//
//    NSLog(@"para:%@",parameter);
//    printf("---------------------------------------\n");
//
//
//    if (parameter) {
//        data = [NSJSONSerialization dataWithJSONObject:parameter options:0 error:nil];
//    }
 
    NSMutableDictionary *dic = [parameter mutableCopy];
  
    [BaseNetManager requestWithPost:urlString header:header parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        if (isSuccessed) {
            resultBlock(resultObject,1);
        }else{
            resultBlock(nil,0);
        }
    }];

}

//网络请求
+ (void)requestByDesWithPost:(NSString *)apiStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter successBlock:(ResultBlock)resultBlock{
    NSString * urlString = [HOST stringByAppendingString:apiStr];
    NSTimeInterval interval = [[NSDate date] timeIntervalSince1970] * 1000;
    NSString *timeString = [NSString stringWithFormat:@"%.f",interval];
//    NSLog(@"timeString:%@",timeString);
//    printf("---------------------------------------\n");
//
    //NSString *UDID = [[[UIDevice currentDevice] identifierForVendor] UUIDString];

    NSData *data = nil;
    if (parameter) {
        data = [NSJSONSerialization dataWithJSONObject:parameter options:0 error:nil];
    }
    NSString *json = [[NSString alloc]initWithData:data encoding:NSUTF8StringEncoding];
//    NSLog(@"json:%@",json);
//    printf("---------------------------------------\n");
//
//
//    printf("---------------------------------------\n");
    
    NSMutableDictionary *dic = [NSMutableDictionary dictionary];
   
    [BaseNetManager requestWithPost:urlString header:header parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        if (isSuccessed) {
            resultBlock(resultObject,1);
        }else{
            resultBlock(nil,0);
        }
    }];
}

//网络请求
+ (void)requestByDesWithPost1:(NSString *)apiStr header:(NSDictionary *)header parameters:(NSDictionary *)parameter andToken:(NSString *)token successBlock:(ResultBlock)resultBlock{
    NSString * urlString = [HOST stringByAppendingString:apiStr];
    NSTimeInterval interval = [[NSDate date] timeIntervalSince1970] * 1000;
    NSString *timeString = [NSString stringWithFormat:@"%.f",interval];
//    NSLog(@"timeString:%@",timeString);
//    printf("---------------------------------------\n");
    
  
    NSMutableDictionary *dic = [NSMutableDictionary dictionary];
   
    [BaseNetManager requestWithPost:urlString header:header parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        if (isSuccessed) {
            resultBlock(resultObject,1);
        }else{
            resultBlock(nil,0);
        }
    }];
}

/* 输出http响应的状态码 */
+ (int)showResponseCode:(NSURLResponse *)response {
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
    NSLog(@"responseStatusCode -- %ld",(long)responseStatusCode);
    return (int)responseStatusCode;
}

@end
