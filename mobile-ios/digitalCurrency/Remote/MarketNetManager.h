//
//  MarketNetManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseNetManager.h"

@interface MarketNetManager : BaseNetManager
//查询我的自选
+(void)queryAboutMyCollectionCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//添加自选
+(void)addMyCollectionWithsymbol:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//删除自选
+(void)deleteMyCollectionWithsymbol:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//获取USDT对CNY汇率
+(void)getusdTocnyRateCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
@end
