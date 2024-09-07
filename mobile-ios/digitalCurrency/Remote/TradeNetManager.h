//
//  TradeNetManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseNetManager.h"

@interface TradeNetManager : BaseNetManager
//查询盘口信息
+(void)getexchangeplate:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//提交委托
+(void)SubmissionOfentrustmentWithsymbol:(NSString*)symbol withPrice:(NSString*)price withAmount:(NSString*)amount WithDirection:(NSString*)direction withType:(NSString*)type useDiscount:(NSString *)useDiscount CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//查询当前委托
+(void)Querythecurrentdelegatesymbol:(NSString*)symbol withpageNo:(int)pageNo withpageSize:(int)pageSize  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
+(void)QuerythecurrentdelegateParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取单个钱包
+(void)getwallettWithcoin:(NSString*)coin  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//取消委托
+(void)cancelCommissionwithOrderID:(NSString*)orderId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//获取单个交易对的精确度
+(void)getSingleSymbol:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//查看历史委托
+(void)historyEntrustForParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
@end
