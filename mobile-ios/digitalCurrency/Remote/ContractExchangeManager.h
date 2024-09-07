//
//  ContractExchangeManager.h
//  digitalCurrency
//
//  Created by ios on 2020/9/17.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "BaseNetManager.h"

NS_ASSUME_NONNULL_BEGIN

@interface ContractExchangeManager : BaseNetManager

//获取合约币种列表
+(void)getContractSymbolListCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取合约币种详情。 传币种:symbol
+ (void)getContractSymbolinfo:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

// 切换用户仓位仓位模式    参数：contractCoinId（币种详情id）  targetPattern（0：全仓  1：逐仓）
+(void)changedPatternSymbolContractId:(NSString *)contractconid targetPattern:(NSInteger)type CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//用户合约账户信息（包含合约账户资产，持仓信息） 参数：x-auth-token（token 底层请求已经处理）   contractCoinId（2 里面的id）
+(void)getWalletDatailContractConid:(NSString *)contractCoinId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//用户合约账户信息列表
+(void)getWalletListCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//修改合约持仓倍数  参数：contractCoinId（2 里面的id），leverage（要修改的倍数），direction（0：多 1：空）
+(void)changedLeverageContractConid:(NSString *)contractCoinId leverage:(NSInteger)leverage direction:(NSInteger)direction CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//用处： 开仓/平仓 (swap/order/open 或swap/order/close)  买卖
//参数：x-auth-token（token），type（0：市价，1：限价，2：计划委托），direction（0：买1：卖），contractCoinId（2 里面的id），triggerPrice（计划委托里触发价，如果不是计划委托传0），entrustPrice（价格，计划委托可以为空，如果为空就传0，默认委托价就是市价），leverage（倍数），volume（数量）
+(void)choiceOpenAndCloseOrderParam:(NSDictionary*)param isOpen:(BOOL)isopen CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//当前委托  参数：x-auth-token（token），contractCoinId（2 里面的id），pageNo（页），pageSize（数量）
+(void)getCurrentContractParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取历史委托记录 参数：x-auth-token（token），contractCoinId（2 里面的id），pageNo（页），pageSize（数量）
+(void)getHistoryContractParam:(NSDictionary*)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//撤销委托 参数：x-auth-token（token），entrustId（当前委托的id）
+(void)revokeHistoryConracEntrustld:(NSString *)entrustId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//合约成交数据 参数：symbol（1  里面的币种），size（数量 （20））
+(void)getTradeContractSymbol:(NSString *)symbol size:(NSNumber *)size CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//合约深度图数据  参数：symbol（1  里面的币种）
+(void)getConractPlateFullSymbol:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//合约查询历史K线
+(void)contracthistoryKlineWithsymbol:(NSString*)symbol withFrom:(NSString*)formTime withTo:(NSString*)toTime withResolution:(NSString*)resolution CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

@end

NS_ASSUME_NONNULL_END
