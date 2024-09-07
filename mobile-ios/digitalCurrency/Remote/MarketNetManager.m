//
//  MarketNetManager.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MarketNetManager.h"

@implementation MarketNetManager
//查询我的自选
+(void)queryAboutMyCollectionCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"exchange/favor/find";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//添加自选
+(void)addMyCollectionWithsymbol:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"exchange/favor/add";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"symbol"] = symbol;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}
//删除自选
+(void)deleteMyCollectionWithsymbol:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"exchange/favor/delete";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"symbol"] = symbol;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}
//获取USDT对CNY汇率
+(void)getusdTocnyRateCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/exchange-rate/usd-cny";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
@end
