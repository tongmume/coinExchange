//
//  C2CNetManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseNetManager.h"

@interface C2CNetManager : BaseNetManager
//选择币种
+(void)selectCoinTypeForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//发布广告
+(void)advertisingForBuyOrSellWithPrice:(NSString *)price withAdvertiseType:(NSString*)advertiseType withCoinId:(NSString*)coinId withMinLimit:(NSString*)minLimit withMaxLimit:(NSString*)maxLimit withTimeLimit:(NSString*)timeLimit withCountry:(NSString*)country withPriceType:(NSString*)priceType withPremiseRate:(NSString*)premiseRate withRemark:(NSString*)remark withNumber:(NSString*)number withPayways:(NSMutableArray *)payWays withJyPassword:(NSString *)jyPassword withAuto:(NSString *)autoFlag withAutoWord:(NSString *)autoword  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
//修改广告
+(void)changeAdvertisingForBuyOrSellWithAdvertisingId:(NSString *)advertisingId WithPrice:(NSString *)price withAdvertiseType:(NSString*)advertiseType withCoinId:(NSString*)coinId withMinLimit:(NSString*)minLimit withMaxLimit:(NSString*)maxLimit withTimeLimit:(NSString*)timeLimit withCountry:(NSString*)country withPriceType:(NSString*)priceType withPremiseRate:(NSString*)premiseRate withRemark:(NSString*)remark withNumber:(NSString*)number withPayways:(NSMutableArray *)payWays withJyPassword:(NSString *)jyPassword withAuto:(NSString *)autoFlag withAutoWord:(NSString *)autoword  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//分页广告查询
+(void)advertisingQueryForPageNo:(NSString *)pageNo withPageSize:(NSString *)pageSize withAdvertiseType:(NSString *)advertiseType withAdvertiseId:(NSString *)advertiseId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//买入，卖出广告信息
+(void)buyOrSelladvertiseInfoForid:(NSString *)advertisingId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//卖出或买入货币
+(void)coinSellOrBuyForUrlString:(NSString *)urlString withAdvertisingId:(NSString *)advertisingId withCoinId:(NSString *)coinId withPrice:(NSString *)price withMoney:(NSString *)money withAmount:(NSString *)amount withRemark:(NSString *)remark withMode:(NSString *)mode CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;


@end
