//
//  HomeNetManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseNetManager.h"

@interface HomeNetManager : BaseNetManager
//获取交易币种缩略行情
+(void)getsymbolthumbCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//获取交易币种缩略行情  合约的
+(void)getsymbolthumbCompleteHandleSwap:(void(^)(id resPonseObj,int code))completeHandle;
//查询历史K线
+(void)historyKlineWithsymbol:(NSString*)symbol withFrom:(NSString*)formTime withTo:(NSString*)toTime withResolution:(NSString*)resolution CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//系统广告
+(void)advertiseBannerCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//首页数据
+(void)HomeDataCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//获取所有的盘口信息
+(void)platefullWithsymbol:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//获取成交记录
+(void)latesttradeWithsymbol:(NSString*)symbol withSizeSize:(int)size CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

@end
