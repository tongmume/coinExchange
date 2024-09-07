//
//  HomeNetManager.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HomeNetManager.h"

@implementation HomeNetManager
//获取交易币种缩略行情
+(void)getsymbolthumbCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/symbol-thumb";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//获取交易币种缩略行情  合约的
+(void)getsymbolthumbCompleteHandleSwap:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"/swap/symbol-thumb";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//查询历史K线
+(void)historyKlineWithsymbol:(NSString*)symbol withFrom:(NSString*)formTime withTo:(NSString*)toTime withResolution:(NSString*)resolution CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/history";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"symbol"] = symbol;
    dic[@"from"] = formTime;
    dic[@"to"] = toTime;
    dic[@"resolution"] = resolution;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//系统广告
+(void)advertiseBannerCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/ancillary/system/advertise";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"sysAdvertiseLocation"] = [NSNumber numberWithInteger:0];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//首页数据
+(void)HomeDataCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/overview";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}
//获取所有的盘口信息
+(void)platefullWithsymbol:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/exchange-plate-full";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"symbol"] = symbol;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}
//获取成交记录
+(void)latesttradeWithsymbol:(NSString*)symbol withSizeSize:(int)size CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/latest-trade";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"symbol"] = symbol;
    dic[@"size"] = [NSNumber numberWithInt:size];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}
@end
