//
//  OptionNetManager.m
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionNetManager.h"

@implementation OptionNetManager

//获取期权币种列表
+(void)getOptionSymbolListCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    
    NSString *path = @"option/coin/coin-list";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

+(void)getOptionSymbolInfo:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    
    NSString *path = @"option/coin/coin-info";
    NSDictionary *dic = @{@"symbol":symbol};
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取往期结果数据
+(void)getOptionHistorySymbol:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    
    NSString *path = @"option/option/history";
    NSDictionary *dic = @{@"symbol":symbol,@"count":@"45"};
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}


+(void)getOptionOpeningSymbol:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle {
    
    NSString *path = @"option/option/opening";
    NSDictionary *dic = @{@"symbol":symbol};
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

+(void)getOptionStartingSymbol:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle {
    NSString *path = @"option/option/starting";
    NSDictionary *dic = @{@"symbol":symbol};
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}


//往期结果数据 获取当前币种历史参与记录
+ (void)getOptionOrderSymbol:(NSString *)symbol pageNo:(NSInteger)pageNo  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle {
    NSString *path = @"option/order/history";
    NSDictionary *dic = @{@"symbol":symbol,
                          @"pageNo":@(pageNo),
                          @"pageSize":@10};
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

+ (void)getOptionOrderCurrentSymbol:(NSString *)symbol
                           optionId:(NSString *)optionId
                     CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle {
    
    NSString *path = @"option/order/current";
    NSDictionary *dic = @{@"symbol":symbol,@"optionId":optionId};
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
   
}

+ (void)getOptionOrderAddSymbol:(NSString *)symbol
                       optionId:(NSString *)optionId
                      direction:(NSInteger)direction
                         amount:(NSString *)amount
                     CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle {
    NSString *path = @"option/order/add";


    NSDictionary *dic = @{@"symbol":symbol,
                          @"optionId":optionId,
                          @"direction":@(direction),
                          @"amount":amount};
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
   
}

@end
