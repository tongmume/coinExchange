//
//  XBRequest.h
//  BaiDang
//
//  Created by 硕影 on 2019/7/18.
//  Copyright © 2019年 NetTest. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AFNetworking.h"

typedef void(^sendResponse)(NSDictionary *resultDic);
@interface XBRequest : NSObject

+ (instancetype)sharedInstance;

#pragma mark - POST
- (void)postDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects;
#pragma mark - GET
- (void)getDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects;
#pragma mark - PUT
- (void)putDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects;
#pragma mark - DELETE
- (void)deleteDataWithUrl:(NSString *)url Parameter:(NSDictionary *)param ResponseObject:(void(^)(NSDictionary *responseResult))responseObjects;

@end
