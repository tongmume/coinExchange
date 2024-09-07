//
//  C2CNetManager.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "C2CNetManager.h"

@implementation C2CNetManager

//选择币种
+(void)selectCoinTypeForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/coin/all";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//发布广告
+(void)advertisingForBuyOrSellWithPrice:(NSString *)price withAdvertiseType:(NSString*)advertiseType withCoinId:(NSString*)coinId withMinLimit:(NSString*)minLimit withMaxLimit:(NSString*)maxLimit withTimeLimit:(NSString*)timeLimit withCountry:(NSString*)country withPriceType:(NSString*)priceType withPremiseRate:(NSString*)premiseRate withRemark:(NSString*)remark withNumber:(NSString*)number withPayways:(NSMutableArray *)payWays withJyPassword:(NSString *)jyPassword withAuto:(NSString *)autoFlag withAutoWord:(NSString *)autoword  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/create";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"price"] = price;
    dic[@"advertiseType"] = advertiseType;
    dic[@"coin.id"] = coinId;
    dic[@"minLimit"] = minLimit;
    dic[@"maxLimit"] = maxLimit;
    dic[@"timeLimit"] = timeLimit;
    dic[@"country.zhName"] = country;
    dic[@"priceType"] = priceType;
    dic[@"premiseRate"] = premiseRate;
    dic[@"remark"] = remark;
    dic[@"number"] = number;
    NSString *string = [payWays componentsJoinedByString:@","];
    dic[@"pay[]"] = string;
    dic[@"jyPassword"] = jyPassword;
    dic[@"auto"] = autoFlag;
    dic[@"autoword"] = autoword;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//修改广告
+(void)changeAdvertisingForBuyOrSellWithAdvertisingId:(NSString *)advertisingId WithPrice:(NSString *)price withAdvertiseType:(NSString*)advertiseType withCoinId:(NSString*)coinId withMinLimit:(NSString*)minLimit withMaxLimit:(NSString*)maxLimit withTimeLimit:(NSString*)timeLimit withCountry:(NSString*)country withPriceType:(NSString*)priceType withPremiseRate:(NSString*)premiseRate withRemark:(NSString*)remark withNumber:(NSString*)number withPayways:(NSMutableArray *)payWays withJyPassword:(NSString *)jyPassword withAuto:(NSString *)autoFlag withAutoWord:(NSString *)autoword  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/update";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"id"] = advertisingId;
    dic[@"price"] = price;
    dic[@"advertiseType"] = advertiseType;
    dic[@"coin.id"] = coinId;
    dic[@"minLimit"] = minLimit;
    dic[@"maxLimit"] = maxLimit;
    dic[@"timeLimit"] = timeLimit;
    dic[@"country.zhName"] = country;
    dic[@"priceType"] = priceType;
    dic[@"premiseRate"] = premiseRate;
    dic[@"remark"] = remark;
    dic[@"number"] = number;
    NSString *string = [payWays componentsJoinedByString:@","];
    dic[@"pay[]"] = string;
    dic[@"jyPassword"] = jyPassword;
    dic[@"auto"] = autoFlag;
    dic[@"autoword"] = autoword;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//分页广告查询
+(void)advertisingQueryForPageNo:(NSString *)pageNo withPageSize:(NSString *)pageSize withAdvertiseType:(NSString *)advertiseType withAdvertiseId:(NSString *)advertiseId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/page";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"pageNo"] = pageNo;
    dic[@"pageSize"] = pageSize;
    dic[@"advertiseType"] = advertiseType;
    dic[@"id"] = advertiseId;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//买入，卖出广告信息
+(void)buyOrSelladvertiseInfoForid:(NSString *)advertisingId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/order/pre";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"id"] = advertisingId;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//卖出或买入货币
+(void)coinSellOrBuyForUrlString:(NSString *)urlString withAdvertisingId:(NSString *)advertisingId withCoinId:(NSString *)coinId withPrice:(NSString *)price withMoney:(NSString *)money withAmount:(NSString *)amount withRemark:(NSString *)remark withMode:(NSString *)mode CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = urlString;
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"id"] = advertisingId;
    dic[@"coinId"] = coinId;
    dic[@"price"] = price;
    dic[@"money"] = money;
    dic[@"amount"] = amount;
    dic[@"remark"] = remark;
    dic[@"mode"] = mode;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
@end
