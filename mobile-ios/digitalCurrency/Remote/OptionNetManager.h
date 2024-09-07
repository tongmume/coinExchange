//
//  OptionNetManager.h
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "BaseNetManager.h"

NS_ASSUME_NONNULL_BEGIN

@interface OptionNetManager : BaseNetManager

//获取合约币种列表
+(void)getOptionSymbolListCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取合约币详情
+(void)getOptionSymbolInfo:(NSString*)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取往期结果数据
+(void)getOptionHistorySymbol:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取正在开奖中的合约 正在进行中的预测
+(void)getOptionOpeningSymbol:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取正在投注中的合约 要开始的预测
+(void)getOptionStartingSymbol:(NSString *)symbol CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取历史记录
+ (void)getOptionOrderSymbol:(NSString *)symbol
                      pageNo:(NSInteger)pageNo
              CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//获取当前币种指定期数ID的参与记录 获取我的开仓
+ (void)getOptionOrderCurrentSymbol:(NSString *)symbol
                           optionId:(NSString *)optionId
                     CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;

//预测合约订单添加 看涨或者看跌
+ (void)getOptionOrderAddSymbol:(NSString *)symbol
                       optionId:(NSString *)optionId
                      direction:(NSInteger)direction
                         amount:(NSString *)amount
                 CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
@end

NS_ASSUME_NONNULL_END
